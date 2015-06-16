package fflames.generator;

import java.io.IOException;
import java.util.Vector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transform {

	/**
	 * Creates empty transform with probability 0.0
	 */
	public Transform() {
		_affineTransform = new AffineTransform();
		_variations = new Vector<>();
		_probability = (double) 0.0;
	}

	/**
	 * Creates a new instance of Transform
	 *
	 * @param _affineTr affine transform
	 * @param _wariations list of assigned variations
	 * @param pr probability that this transform will be chosen
	 */
	public Transform(AffineTransform _affineTr, Vector<IVariation> _wariations, Double pr) {
		_affineTransform = new AffineTransform(_affineTr);
		_variations = new Vector<>(_wariations);

		// checking if there are variations that depend on affine transform
		// coefficients
		for (int i = 0; i < _variations.size(); i++) {
			if (_variations.get(i).isDependent()) {
				Vector<Double> temp = new Vector<>();
				double[] parameters = new double[6];
				_affineTransform.getMatrix(parameters);
				for (double par : parameters) {
					temp.add(par);
				}
				if (_variations.get(i).getParametersQuantity() > 0) {
					temp.addAll(_variations.get(i).getParameters().subList(2,
							_variations.get(i).getParameters().size()));
				}
				_variations.get(i).setParameters(temp);
			}
		}
		_probability = (double) pr;
	}

	/**
	 * @param point point that will be transformed by this transformation
	 * @return object that contains transformed point coordinations
	 */
	public Point2D transform(Point2D point) {
		_affineTransform.transform(point, point);
		Point2D temp = new Point2D.Double(0.0, 0.0);
		for (int i = 0; i < _variations.size(); i++) {
			temp.setLocation(pointSum(temp, _variations.get(i).oblicz(point)));
		}
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
	 * @return vector containing associated variations
	 * @deprecated
	 */
	public Vector<IVariation> getWariations() {
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
		String name = null;
		Vector<Double> par = null;
		try {
			out.write("<Propability>" + _probability.toString() + "</Propability>\r\n");
			out.write("<AffineTransform>\r\n");
			for (int i = 0; i < temp.length; i++) {
				out.write("<Wsp>" + temp[i] + "</Wsp>\r\n");
			}
			out.write("</AffineTransform>\r\n");

			out.write("<Wariations>\r\n");
			for (int i = 0; i < _variations.size(); i++) {
				out.write("<Wariation>\r\n");
				par = _variations.get(i).getParameters();
				out.write("<Coefficient>" + par.firstElement() + "</Coefficient>\r\n");
				if (par.size() > 1) {
					for (int j = 1; j < par.size(); j++) {
						out.write("<Par>" + par.get(j) + "</Par>\r\n");
					}
				}
				name = _variations.get(i).getName();
				out.write("<Name>" + name + "</Name>\r\n");
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
	private final Vector<IVariation> _variations;
	private Double _probability;
}
