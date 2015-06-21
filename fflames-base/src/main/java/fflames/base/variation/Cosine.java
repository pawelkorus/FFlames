package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Cosine variation
 */
public class Cosine extends AbstractWariation {

	/**
	 * Creates a new instance of Cosine
	 *
	 * @param _coefficient _coefficient value
	 */
	public Cosine(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}
	
	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation(Math.cos(Math.PI * x) * Math.cosh(y) * _coefficient,
				-Math.sin(Math.PI * x) * Math.sinh(y) * _coefficient);
		return point;
	}
}
