package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Noise variation
 */
public class Noise extends AbstractWariation {

	/**
	 * Creates a new instance of Noise
	 *
	 * @param _coefficient _coefficient value
	 */
	public Noise(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double u1 = Math.random();
		double u2 = Math.random();
		point.setLocation(u1 * x * Math.cos(2 * Math.PI * u2) * _coefficient, u1 * y * Math.sin(2 * Math.PI * u2) * _coefficient);
		return point;
	}
}
