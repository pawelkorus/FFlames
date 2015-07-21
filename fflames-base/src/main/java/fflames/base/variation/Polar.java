package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Polar variation
 */
public class Polar extends AbstractVariation {

	/**
	 * Creates a new instance of Polar
	 *
	 * @param _coefficient _coefficient value
	 */
	public Polar(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		point.setLocation(Math.atan2(x, y) / Math.PI * _coefficient,
				r - 1.0 * _coefficient);
		return point;
	}
}
