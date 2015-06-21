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


public class FractalGenerator { 
	
	public FractalGenerator(ArrayList<Transform> transforms, IColoring _coloringMethod, int width, int height) {
		super();
		_width = width;
		_height = height;
		_transforms = transforms;
		_samples = 1;
		this._coloringMethod = _coloringMethod;
		this._randomNumberGenerator = new Random();
		
		_numberOfIterations = 100000;
		_numberOfRotations = 0;
		
		_algorithmTransforms = new ArrayList<Transform>();
	}
	
	public void execute() {
		ColorModel colorModel = _coloringMethod.getColorModel();
		
		ISuperSampling superSampling = new NoSuperSampling(_width, _height);
		if(_samples > 1) {
			superSampling = new SuperSampling(_width, _height, _samples);
		}
		
		int width = superSampling.getRequiredWidth();
		int height = superSampling.getRequiredHeight();
		
		BufferedImage output = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(width, height), false, new Hashtable<>());
		WritableRaster raster = output.getRaster();
		
		_coloringMethod.initialize(raster);
		
		prepareAlgorithmTransforms();
		
		ArrayList<Double> bounds = calculateBounds();
		
		Object lock = new Object();
		
		try {
		
		Thread t1 = new Thread(new ExecutionUnit(bounds, _algorithmTransforms, raster, _transforms.size(), _numberOfIterations/4, lock));
		Thread t2 = new Thread(new ExecutionUnit(bounds, _algorithmTransforms, raster, _transforms.size(), _numberOfIterations/4, lock));
		Thread t3 = new Thread(new ExecutionUnit(bounds, _algorithmTransforms, raster, _transforms.size(), _numberOfIterations/4, lock));
		Thread t4 = new Thread(new ExecutionUnit(bounds, _algorithmTransforms, raster, _transforms.size(), _numberOfIterations/4, lock));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		} catch(InterruptedException e) {
		
		}
		
		_coloringMethod.finalize(raster);
		_output = superSampling.processImage(output);
	}
	
	
	/**
	 * Prepares algorithm transforms from the algorithm
	 * parameters and existing transforms.
	 */
	protected void prepareAlgorithmTransforms() {
		_algorithmTransforms.clear();
		int rotationsNumber = getRotationsNumber();
		
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

	public int getNumberOfIterations() {
		return _numberOfIterations;
	}
	
	public void setNumberOfIterations(int numberOfIterations) {
		_numberOfIterations = numberOfIterations;
	}
	
	public void setNumberOfRotations(int numberOfRotations) {
		_numberOfRotations = numberOfRotations;
	}
	
	public int getRotationsNumber() {
		return _numberOfRotations;
	}
	
	public void setSamples(int number) {
		_samples = number;
	}
	
	public int getSample() {
		return _samples;
	}
	
	public BufferedImage getOutput() {
		return _output;
	}
	
	private class ExecutionUnit implements Runnable {
		private ArrayList<Double> _bounds;
		private Random _randomNumberGenerator;
		private ArrayList<Transform> _algorithmTransforms;
		private WritableRaster _raster;
		private int _numberOfRealTransforms;
		private int _numberOfIterations;
		private	Object _lock;
		
		ExecutionUnit(
				ArrayList<Double> bounds,
				ArrayList<Transform> transforms,
				WritableRaster raster,
				int numberOfRealTransforms,
				int numberOfIteractions,
				Object lock) {
			
			_bounds = bounds;
			_algorithmTransforms = transforms;
			_raster = raster;
			_numberOfRealTransforms = numberOfRealTransforms;
			_lock = lock;
			_numberOfIterations = numberOfIteractions;
			
			_randomNumberGenerator = new Random();
		}
		
		@Override
		public void run() {
			Double minx = (double) Math.round(_bounds.get(0));
			Double maxx = (double) Math.round(_bounds.get(1));
			Double miny = (double) Math.round(_bounds.get(2));
			Double maxy = (double) Math.round(_bounds.get(3));
			
			int width = _raster.getWidth();
			int height = _raster.getHeight();
			
			int index = 0;
			
			Point2D.Double point = new Point2D.Double(_randomNumberGenerator.nextDouble(), _randomNumberGenerator.nextDouble());
			Point imagePoint = new Point();
					
			int i = 0;
			
			while(i <= _numberOfIterations) {
				index = selectFunctionIndex();
				calculateNextPoint(point, index);			
				Double valX = (point.getX() - minx)/(maxx - minx) * width;
				Double valY = (point.getY() - miny)/(maxy - miny) * height;
				imagePoint.setLocation(valX.intValue(), valY.intValue());

				if(imagePoint.x < width && imagePoint.x >= 0 && imagePoint.y >= 0 && imagePoint.y < height) {
					
					synchronized(_lock) {
						if(index < _numberOfRealTransforms) { 
							_coloringMethod.writeColor(_raster, i, imagePoint.x, imagePoint.y, index);
						} else {
							_coloringMethod.writeColor(_raster, i, imagePoint.x, imagePoint.y);
						}
					}
				
				}

				i++;
			}
		}
		
		private int selectFunctionIndex() {
			double random = _randomNumberGenerator.nextDouble();
			double currentPr = 0.0;
			for(int i = 0; i < _algorithmTransforms.size() - 1; i++) {
				currentPr += _algorithmTransforms.get(i).getPropability();
				if(random <= currentPr) return i;
			}
			return _algorithmTransforms.size() - 1;
		}
		
		private void calculateNextPoint(Point2D.Double point, int index) {
			_algorithmTransforms.get(index).transform(point);
		}
	}
	
	private ArrayList<Double> calculateBounds() {
		int sampleSize = 200000;
		
		ArrayList<Point2D.Double> points = new ArrayList<>();
		Point2D.Double point = new Point2D.Double(_randomNumberGenerator.nextDouble(), _randomNumberGenerator.nextDouble());
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
	
	private void calculateNextPoint(Point2D.Double point, int index) {
		_algorithmTransforms.get(index).transform(point);
	}
	
	private int selectFunctionIndex() {
		double random = _randomNumberGenerator.nextDouble();
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
	int _numberOfIterations, _width, _height, _numberOfRotations, _samples;
	Random _randomNumberGenerator;
}