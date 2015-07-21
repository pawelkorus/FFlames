package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Secant variation
 */
public class Secant extends AbstractVariation {

	/**
	 * Creates a new instance of Secant
	 *
	 * @param _coefficient _coefficient value
	 */
	public Secant(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());
		point.setLocation(point.getX(), 1.0 / Math.cos(r * _coefficient));
		return point;
	}
}
