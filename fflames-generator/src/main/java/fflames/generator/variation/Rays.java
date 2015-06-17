package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Rays variation
 */
public class Rays extends AbstractWariation {

	/**
	 * Creates a new instance of Rays
	 *
	 * @param _coefficient _coefficient value
	 */
	public Rays(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double ang = Math.random() * Math.PI * _coefficient;
		double r = _coefficient / (x * x + y * y + 0.000001);
		double tanr = _coefficient * Math.tan(ang) * r;
		point.setLocation(tanr * Math.cos(x), tanr * Math.sin(y));
		return point;
	}
}
