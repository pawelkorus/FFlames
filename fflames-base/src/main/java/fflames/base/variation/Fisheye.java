package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Fisheye variation
 */
public class Fisheye extends AbstractVariation {

	/**
	 * Creates a new instance of Fisheye
	 *
	 * @param _coefficient _coefficient value
	 */
	public Fisheye(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double a = Math.atan2(x, y);
		point.setLocation(2.0 * r / (r + 1.0) * Math.cos(a) * _coefficient,
				2.0 * r / (r + 1.0) * Math.sin(a) * _coefficient);
		return point;
	}
}
