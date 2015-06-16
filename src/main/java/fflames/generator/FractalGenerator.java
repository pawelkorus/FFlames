package fflames.generator;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import fflames.interfaces.IColour;
import fflames.interfaces.ISuperSampling;

public class FractalGenerator { 
	
	public FractalGenerator(ArrayList<Transform> transforms, IColour _coloringMethod, int width, int height) {
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
		Point2D.Double point = new Point2D.Double();
		Point imagePoint = new Point();
		point.setLocation(_randomNumberGenerator.nextDouble(), _randomNumberGenerator.nextDouble());
		int i = 1, index = 0;
		
		ColorModel colorModel = _coloringMethod.getColorModel();
		
		ISuperSampling superSampling = new NoSuperSampling(_width, _height);
		if(_samples > 1) {
			superSampling = new SuperSampling(_width, _height, _samples);
		}
		
		int width = superSampling.getRequiredWidth();
		int height = superSampling.getRequiredHeight();
		
		BufferedImage output = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(superSampling.getRequiredWidth(), superSampling.getRequiredHeight()), false, new Hashtable<>());
		WritableRaster raster = output.getRaster();
		
		_coloringMethod.initialize(raster);
		
		prepareAlgorithmTransforms();
		
		ArrayList<Double> bounds = calculateBounds();
		Double minx = (double) Math.round(bounds.get(0));
		Double maxx = (double) Math.round(bounds.get(1));
		Double miny = (double) Math.round(bounds.get(2));
		Double maxy = (double) Math.round(bounds.get(3));
		
		while(i <= _numberOfIterations) {
			index = selectFunctionIndex();
			calculateNextPoint(point, index);			
			Double valX = (point.getX() - minx)/(maxx - minx) * width;
			Double valY = (point.getY() - miny)/(maxy - miny) * height;
			imagePoint.setLocation(valX.intValue(), valY.intValue());
			
			if(imagePoint.x < width && imagePoint.x >= 0 && imagePoint.y >= 0 && imagePoint.y < height) {
				if(index < _transforms.size()) { 
					_coloringMethod.writeColour(raster, i, imagePoint.x, imagePoint.y, index);
				} else {
					_coloringMethod.writeColour(raster, i, imagePoint.x, imagePoint.y);
				}
			}
			
			i++;
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
		_algorithmTransforms.get(index).oblicz(point);
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
	IColour _coloringMethod;
	BufferedImage _output;
	int _numberOfIterations, _width, _height, _numberOfRotations, _samples;
	Random _randomNumberGenerator;
}
