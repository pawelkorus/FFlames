package fflames.base;

import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Transform implements IPointTransform {
	private final AffineTransform _affineTransform;
	private final ArrayList<IVariation> _variations;
	
	/**
	 * Creates empty transform with probability 0.0
	 */
	public Transform() {
		_affineTransform = new AffineTransform();
		_variations = new ArrayList<>();
	}

	/**
	 * Creates a new instance of Transform
	 *
	 * @param _affineTr affine transform
	 * @param _wariations list of assigned variations
	 */
	public Transform(AffineTransform _affineTr, ArrayList<IVariation> _wariations) {
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
	}

	/**
	 * @param point point that will be transformed by this transformation
	 * @return object that contains transformed point coordinations
	 */
	public Point2D transform(Point2D point) {
		_affineTransform.transform(point, point);
		
		Point2D sum = new Point2D.Double(0.0, 0.0);
		Point2D temp = new Point2D.Double(0.0, 0.0);
		
		_variations.stream().forEach((_variation) -> {
			_variation.transform(point, temp);
			pointSum(sum, temp);
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
	
	@Override
	public void transform(Point2D source, Point2D out) {
		Point2D result = transform(source);
		out.setLocation(result);
	}

	private Point2D pointSum(Point2D a, Point2D b) {
		a.setLocation(a.getX() + b.getX(), a.getY() + b.getY());
		return a;
	}
}
