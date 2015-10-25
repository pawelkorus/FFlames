package fflames.base;

import fflames.base.coloring.BlackWhite;
import fflames.base.supersampling.NoSuperSampling;
import fflames.base.supersampling.SuperSampling;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class FractalGenerator { 
	
	private FractalGenerator(
			IColoring coloringMethod, 
			int[] size,
			int numberOfIterations,
			int samples,
			int rotations,
			ExecutorService executor 
	) {
		super();
		
		_future = null;
		
		_transforms = new ArrayList<>();
		
		_coloringMethod = coloringMethod;
		
		_numberOfIterations = numberOfIterations;
		_numberOfRotations = rotations;
		
		_algorithmTransforms = new ArrayList<>();
		
		_executor = executor;
		
		_progress = new AtomicInteger(0);
		
		_width = size[0];
		_height = size[1];
		
		_jobs = 4;
		
		_lock = new Object();
		
		_bounds = new ArrayList<>();
		
		_superSampling = new NoSuperSampling(_width, _height);
		if(samples > 1) {
			_superSampling = new SuperSampling(_width, _height, samples);
		}
		int requiredWidth = _superSampling.getRequiredWidth();
		int requiredHeight = _superSampling.getRequiredHeight();
		
		ColorModel colorModel = _coloringMethod.getColorModel();
		_output = new BufferedImage(
				colorModel, 
				colorModel.createCompatibleWritableRaster(
						requiredWidth, 
						requiredHeight
				), 
				false, 
				new Hashtable<>()
		);
	}
	
	/**
	 * Function executes process of generating fractal flame asynchronously.
	 * It returns object representing future results of this algorithm.
	 * One instance of class FractalGenerator can produce one fractal, 
	 * so calling execute method many times will always return the same 
	 * object containing future results of algorithm.
	 * 
	 * @return object representing future calculations results
	 */
	public Future<BufferedImage> execute() {
		if(_future == null) {
			_coloringMethod.initialize(_output.getRaster());

			prepareAlgorithmTransforms();

			_future = 
			CompletableFuture.supplyAsync(this::calculateBounds, _executor)
			.thenApply( bounds -> {
				_bounds = bounds;
				return _bounds;
			})
			.thenCompose((ArrayList<Double> arg1) -> { 
				CompletableFuture<Void> j1 = CompletableFuture.runAsync(this::generateFractal, _executor);
				CompletableFuture<Void> j2 = CompletableFuture.runAsync(this::generateFractal, _executor);
				CompletableFuture<Void> j3 = CompletableFuture.runAsync(this::generateFractal, _executor);
				CompletableFuture<Void> j4 = CompletableFuture.runAsync(this::generateFractal, _executor);

				return CompletableFuture.allOf(j1, j2, j3, j4);
			})
			.thenApplyAsync( (arg) -> {
				WritableRaster raster = _output.getRaster();
				_coloringMethod.finalize(raster);
				return _superSampling.processImage(_output);
			})
			.exceptionally((ex) -> {
				ex.printStackTrace();
				return _output;
			});
		}
			
		return _future;
	}
	
	/**
	 * Prepares algorithm transforms from the algorithm
	 * parameters and existing transforms.
	 */
	protected void prepareAlgorithmTransforms() {
		_algorithmTransforms.clear();
		int rotationsNumber = _numberOfRotations;
		
		if(rotationsNumber > 0) {
			Double transformPropability = 1.0/(rotationsNumber + 1);
			
			for(TransformPropability tp : _transforms) {
				
				TransformPropability newTp = new TransformPropability();
				newTp.propability = tp.propability * transformPropability;
			    newTp.transform = new TransformProxy(
						(Transform) tp.transform, tp.propability);
				
				_algorithmTransforms.add(newTp);	
			}
			
			Double rotationAngle = 2*Math.PI/(rotationsNumber + 1);
			for(int i = 1; i <= rotationsNumber; i++) {
				TransformPropability tp = new TransformPropability();
				tp.propability = transformPropability;
				tp.transform = new RotationalSymmetryTransform(i * rotationAngle);
				_algorithmTransforms.add(tp);
			}
		} else {
			_algorithmTransforms.addAll(_transforms);
		}
	}

	public int getProgress() {
		return _progress.get();
	}
	
	private ArrayList<Double> calculateBounds() {
		ThreadLocalRandom randomNumberGenerator = ThreadLocalRandom.current();
		int sampleSize = 200000;
		
		ArrayList<Point2D.Double> points = new ArrayList<>();
		Point2D.Double sourcePoint = new Point2D.Double(randomNumberGenerator.nextDouble(), randomNumberGenerator.nextDouble());
		Point2D.Double outPoint = new Point2D.Double();
		Double meanx = (double) 0;
		Double meany = (double) 0;
		Double stdDevx = (double) 0;
		Double stdDevy = (double) 0;
		for(int i = 0; i < sampleSize; i++) {
			int index = selectFunctionIndex();
			calculateNextPoint(
					_algorithmTransforms.get(index).transform, 
					sourcePoint, 
					outPoint);
			
			if(i >= 20) {
				meanx +=  outPoint.getX();
				meany += outPoint.getY();
				points.add((Point2D.Double)outPoint.clone());
			}
			
			sourcePoint.setLocation(outPoint);
		}
		
		meanx = meanx/sampleSize;
		meany = meany/sampleSize;
		
		Iterator<Point2D.Double> it = points.iterator();
		while(it.hasNext()) {
			Point2D point = it.next();
			stdDevx += (point.getX() - meanx) * (point.getX() - meanx);
			stdDevy += (point.getY() - meanx) * (point.getY() - meanx);
		}
		stdDevx = Math.sqrt(stdDevx/(sampleSize - 1));  
		stdDevy = Math.sqrt(stdDevy/(sampleSize - 1));
		
		ArrayList<Double> result;
		result = new ArrayList<>();
		result.add(meanx - 3*stdDevx);
		result.add(meanx + 3*stdDevx);
		result.add(meany - 3*stdDevy);
		result.add(meany + 3*stdDevy);
		return result;
	}
	
	private void generateFractal() {
		ThreadLocalRandom randomNumberGenerator = ThreadLocalRandom.current();
		WritableRaster raster = _output.getRaster();
			
		Double minx = (double) Math.round(_bounds.get(0));
		Double maxx = (double) Math.round(_bounds.get(1));
		Double miny = (double) Math.round(_bounds.get(2));
		Double maxy = (double) Math.round(_bounds.get(3));

		int width = raster.getWidth();
		int height = raster.getHeight();

		Point2D sourcePoint = new Point2D.Double(randomNumberGenerator.nextDouble(), randomNumberGenerator.nextDouble());
		Point2D outPoint = new Point2D.Double();
		Point imagePoint = new Point();

		int i = 0;
		int index;

		while(i <= _numberOfIterations/_jobs) {
			index = selectFunctionIndex();
			
			calculateNextPoint(_algorithmTransforms.get(index).transform, sourcePoint, outPoint);			
			
			Double valX = (outPoint.getX() - minx)/(maxx - minx) * width;
			Double valY = (outPoint.getY() - miny)/(maxy - miny) * height;
			imagePoint.setLocation(valX.intValue(), valY.intValue());

			if(imagePoint.x < width && imagePoint.x >= 0 && imagePoint.y >= 0 && imagePoint.y < height) {

				synchronized(_lock) {
					if(index < _transforms.size()) { 
						_coloringMethod.writeColor(raster, i, imagePoint.x, imagePoint.y, index);
					} else {
						_coloringMethod.writeColor(raster, i, imagePoint.x, imagePoint.y);
					}
				}

			}

			sourcePoint.setLocation(outPoint);
			i++;
			_progress.addAndGet(1);
		}
	}
	
	private void calculateNextPoint(
			IPointTransform transform, 
			Point2D source,
			Point2D out) {
		transform.transform(source, out);
	}
	
	private int selectFunctionIndex() {
		double random = ThreadLocalRandom.current().nextDouble();
		double currentPr = 0.0;
		for(int index = 0; index < _algorithmTransforms.size(); index++) {
			TransformPropability tp = _algorithmTransforms.get(index);
			currentPr += tp.propability;
			if(random <= currentPr) return index;
		}
		
		assert false : "It looks like all transform propabilities do not sum to 1";
		return -1;
	}
	
	List<TransformPropability> _transforms;
	List<TransformPropability> _algorithmTransforms;
	IColoring _coloringMethod;
	BufferedImage _output;
	int _numberOfIterations, _numberOfRotations, _width, _height;
	Random globalRandomNumberGenerator;
	ExecutorService _executor;
	ArrayList<Future> _jobResults;
	AtomicInteger _progress;
	ISuperSampling _superSampling;
	int _jobs;
	Object _lock;
	ArrayList<Double> _bounds;
	Future<BufferedImage> _future;
	
	private void addTransform(TransformPropability t) {
		_transforms.add(t);
	}
	
	public static class Builder {
		private final Collection<TransformPropability> transforms = new ArrayList<>();
		private final ExecutorService executorService;
		private int numberOfIterations = 1000000;
		private int numberOfRotations = 0;
		private int width = 800;
		private int height = 600;
		private int samples = 1;
		private IColoring coloringMethod = new BlackWhite();
		
		public Builder(ExecutorService executorService) {
			this.executorService = executorService;
		}
		
		public Builder width(int v) { 
			width = v; return this;
		}
		
		public Builder height(int v) { 
			height = v; return this;
		}
		
		public Builder numberOfIterations(int v) {
			numberOfIterations = v; return this;
		}
		
		public Builder numberOfRotations(int v) {
			numberOfRotations = v; return this;
		}
		
		public Builder samples(int s) {
			samples = s; return this;
		}
		
		public Builder coloringMethod(IColoring coloring) { 
			coloringMethod = coloring; return this;
		}
		
		public Builder addTransform(double propability, Transform transform) {
			TransformPropability t = new TransformPropability();
			t.propability = propability;
			t.transform = transform;
			
			transforms.add(t);
			
			return this;
		}
		
		public FractalGenerator build() {
			int[] size = { 
				width,
				height
			};
			
			FractalGenerator fg = new FractalGenerator(coloringMethod, size, 
					numberOfIterations, samples, numberOfRotations, 
					executorService);
			
			for(TransformPropability t : transforms) {
				fg.addTransform(t);
			}
			
			return fg;
		}
	}
	
	private static class TransformPropability {
		double propability;
		IPointTransform transform;
	}
}
