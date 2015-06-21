package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Handkerchief variation
 */
public class Handkerchief extends AbstractWariation {

	/**
	 * Creates a new instance of Handkerchief
	 *
	 * @param _coefficient _coefficient value
	 */
	public Handkerchief(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double theta = Math.atan2(x, y);
		point.setLocation(r * Math.sin(theta + r) * _coefficient,
				r * Math.cos(theta - r) * _coefficient);
		return point;
	}
}
