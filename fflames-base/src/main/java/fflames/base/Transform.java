package fflames.base;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transform {

	/**
	 * Creates empty transform with probability 0.0
	 */
	public Transform() {
		_affineTransform = new AffineTransform();
		_variations = new ArrayList<>();
		_probability = (double) 0.0;
	}

	/**
	 * Creates a new instance of Transform
	 *
	 * @param _affineTr affine transform
	 * @param _wariations list of assigned variations
	 * @param pr probability that this transform will be chosen
	 */
	public Transform(AffineTransform _affineTr, ArrayList<IVariation> _wariations, Double pr) {
		_affineTransform = new AffineTransform(_affineTr);
		_variations = new ArrayList<>(_wariations);

		// checking if there are variations that depend on affine transform
		// coefficients
		_variations
				.stream()
				.filter((_variation) -> (_variation.isDependent()))
				.forEach((IVariation _variation) -> {
			ArrayList<Double> temp = new ArrayList<>();
			double[] parameters = new double[6];
			_affineTransform.getMatrix(parameters);
			for (double par : parameters) {
				temp.add(par);
			}
			temp.addAll(_variation.getParameters());
			_variation.setParameters(temp);
		});
		
		_probability = (double) pr;
	}

	/**
	 * @param point point that will be transformed by this transformation
	 * @return object that contains transformed point coordinations
	 */
	public Point2D transform(Point2D point) {
		_affineTransform.transform(point, point);
		Point2D temp = new Point2D.Double(0.0, 0.0);
		_variations.stream().forEach((_variation) -> {
			temp.setLocation(pointSum(temp, _variation.calculate(point)));
		});
		return temp;
	}

	@Override
	public String toString() {
		return _affineTransform.toString() + _variations.toString();
	}

	/**
	 * Returns affine transform object associated with this transform
	 *
	 * @return instance of affine transform
	 */
	public AffineTransform getAffineTr() {
		return _affineTransform;
	}
	
	/**
	 * Returns variations associated with this transform
	 *
	 * @return list containing associated variations
	 */
	public ArrayList<IVariation> getVariations() {
		return _variations;
	}

	public Double getPropability() {
		return _probability;
	}

	public void setPropability(Double value) {
		_probability = value;
	}

	public void writeXML(java.io.OutputStreamWriter out) {
		double[] temp = new double[6];
		_affineTransform.getMatrix(temp);
		
		try {
			out.write("<Propability>" + _probability.toString() + "</Propability>\r\n");
			out.write("<AffineTransform>\r\n");
			for (int i = 0; i < temp.length; i++) {
				out.write("<Wsp>" + temp[i] + "</Wsp>\r\n");
			}
			out.write("</AffineTransform>\r\n");

			out.write("<Wariations>\r\n");
			for (IVariation variation : _variations) {
				out.write("<Wariation>\r\n");
				
				Double coefficient = variation.getCoefficient();
				out.write("<Coefficient>" + coefficient + "</Coefficient>\r\n");
				
				List<Double> parameters = variation.getParameters();
				for(Double par : parameters) {
					out.write("<Par>" + par + "</Par>\r\n");
				}
				
				out.write("<Name>" + variation.getName() + "</Name>\r\n");
				out.write("</Wariation>\r\n");
			}
			out.write("</Wariations>\r\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private Point2D pointSum(Point2D a, Point2D b) {
		a.setLocation(a.getX() + b.getX(), a.getY() + b.getY());
		return a;
	}
	
	private final AffineTransform _affineTransform;
	private final ArrayList<IVariation> _variations;
	private Double _probability;
}