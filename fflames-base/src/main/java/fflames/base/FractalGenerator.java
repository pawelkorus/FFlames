package fflames.base;

import fflames.base.supersampling.NoSuperSampling;
import fflames.base.supersampling.SuperSampling;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class FractalGenerator { 
	
	public FractalGenerator(
			ArrayList<Transform> transforms, 
			IColoring coloringMethod, 
			int[] size,
			int numberOfIterations,
			int samples,
			int rotations,
			ExecutorService executor 
	) {
		super();
		
		_future = null;
		
		_transforms = transforms;
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
			
			_transforms.stream().forEach((transform) -> {
			    _algorithmTransforms.add(new TransformProxy(transform, transform.getPropability()*transformPropability));
			});
			
			Double rotationAngle = 2*Math.PI/(rotationsNumber + 1);
			for(int i = 1; i <= rotationsNumber; i++) {
				_algorithmTransforms.add(new RotationalSymmetryTransform(i * rotationAngle, transformPropability));
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
		Point2D.Double point = new Point2D.Double(randomNumberGenerator.nextDouble(), randomNumberGenerator.nextDouble());
		Double meanx = (double) 0;
		Double meany = (double) 0;
		Double stdDevx = (double) 0;
		Double stdDevy = (double) 0;
		for(int i = 0; i < sampleSize; i++) {
			int index = selectFunctionIndex();
			calculateNextPoint(point, index);
			if(i >= 20) {
				meanx +=  point.getX();
				meany += point.getY();
				points.add((Point2D.Double)point.clone());
			}
		}
		
		meanx = meanx/sampleSize;
		meany = meany/sampleSize;
		
		Iterator<Point2D.Double> it = points.iterator();
		while(it.hasNext()) {
			point = it.next();
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

		Point2D.Double point = new Point2D.Double(randomNumberGenerator.nextDouble(), randomNumberGenerator.nextDouble());
		Point imagePoint = new Point();

		int i = 0;
		int index;

		while(i <= _numberOfIterations/_jobs) {
			index = selectFunctionIndex();
			calculateNextPoint(point, index);			
			Double valX = (point.getX() - minx)/(maxx - minx) * width;
			Double valY = (point.getY() - miny)/(maxy - miny) * height;
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

			i++;
			_progress.addAndGet(1);
		}
	}
	
	private void calculateNextPoint(Point2D.Double point, int index) {
		_algorithmTransforms.get(index).transform(point);
	}
	
	private int selectFunctionIndex() {
		double random = ThreadLocalRandom.current().nextDouble();
		double currentPr = 0.0;
		for(int i = 0; i < _algorithmTransforms.size() - 1; i++) {
			currentPr += _algorithmTransforms.get(i).getPropability();
			if(random <= currentPr) return i;
		}
		return _algorithmTransforms.size() - 1;
	}
	
	ArrayList<Transform> _transforms;
	ArrayList<Transform> _algorithmTransforms;
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
}
