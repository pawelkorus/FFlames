package fflames;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import fflames.interfaces.IColour;
import fflames.model.Transform;

public class FractalGenerator {
	
	@Deprecated
	public FractalGenerator(ArrayList<Transform> transforms, IColour _coloringMethod, BufferedImage _output) {
		super();
		_transforms = transforms;
		this._coloringMethod = _coloringMethod;
		this._randomNumberGenerator = new Random();
		
		_numberOfIterations = 100000;
	}
	
	public FractalGenerator(ArrayList<Transform> transforms, IColour _coloringMethod, int width, int height) {
		super();
		_width = width;
		_height = height;
		_transforms = transforms;
		this._coloringMethod = _coloringMethod;
		this._randomNumberGenerator = new Random();
		
		_numberOfIterations = 100000;
	}
	
	public void execute() {
		Point2D.Double point = new Point2D.Double();
		Point imagePoint = new Point();
		point.setLocation(_randomNumberGenerator.nextDouble(), _randomNumberGenerator.nextDouble());
		int i = 1, index = 0;
		
		ColorModel colorModel = _coloringMethod.getColorModel();
		
		_output = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(_width, _height), false, new Hashtable<String, Object>());
		WritableRaster raster = _output.getRaster();
		
		_coloringMethod.initialize(raster);
		
		Vector<Double> bounds = calculateBounds();
		Double minx = bounds.elementAt(0);
		Double maxx = bounds.elementAt(1);
		Double miny = bounds.elementAt(2);
		Double maxy = bounds.elementAt(3);
		
		while(i <= _numberOfIterations) {
			index = selectFunctionIndex();
			calculateNextPoint(point, index);			
			Double valX = new Double((point.getX() - minx)/(maxx - minx) * _width);
			Double valY = new Double((point.getY() - miny)/(maxy - miny) * _height);
			imagePoint.setLocation(valX.intValue(), valY.intValue());
			
			if(imagePoint.x < _width && imagePoint.x >= 0 && imagePoint.y >= 0 && imagePoint.y < _height) {
				_coloringMethod.writeColour(raster, i, imagePoint.x, imagePoint.y, index);
			}
			
			i++;
		}
		
		_coloringMethod.finalize(raster);
	}
	
	public int getNumberOfIterations() {
		return _numberOfIterations;
	}
	
	public void setNumberOfIterations(int _numberOfIterations) {
		this._numberOfIterations = _numberOfIterations;
	}
	
	public BufferedImage getOutput() {
		return _output;
	}
	
	private Vector<Double> calculateBounds() {
		int sampleSize = 20000;
		int index = 0;
		
		Vector<Point2D.Double> points = new Vector<Point2D.Double>();
		Point2D.Double point = new Point2D.Double(_randomNumberGenerator.nextDouble(), _randomNumberGenerator.nextDouble());
		Double meanx = new Double(0);
		Double meany = new Double(0);
		Double stdDevx = new Double(0);
		Double stdDevy = new Double(0);
		for(int i = 0; i < sampleSize; i++) {
			index = selectFunctionIndex();
			calculateNextPoint(point, index);
			if(i >= 20) {
				meanx += point.getX();
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
		
		Vector<Double> result = new Vector<Double>();
		result.add(meanx - 3*stdDevx);
		result.add(meanx + 3*stdDevx);
		result.add(meany - 3*stdDevy);
		result.add(meany + 3*stdDevy);
		return result;
	}
	
	private void calculateNextPoint(Point2D.Double point, int index) {
		
		_transforms.get(index).oblicz(point);
	}
	
	private int selectFunctionIndex() {
		double random = _randomNumberGenerator.nextDouble();
		double currentPr = 0.0;
		for(int i = 0; i < _transforms.size() - 1; i++) {
			currentPr += _transforms.get(i).getPropability();
			if(random <= currentPr) return i;
		}
		return _transforms.size() - 1;
	}
	
	ArrayList<Transform> _transforms;
	IColour _coloringMethod;
	BufferedImage _output;
	int _numberOfIterations, _width, _height;
	Random _randomNumberGenerator;
}
