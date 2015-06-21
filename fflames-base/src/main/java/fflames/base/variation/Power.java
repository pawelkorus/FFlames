package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Power variation
 */
public class Power extends AbstractWariation {

	/**
	 * Creates a new instance of Power
	 *
	 * @param _coefficient _coefficient value
	 */
	public Power(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double t = Math.atan2(x, y);
		double pot = Math.pow(r, Math.sin(t));
		point.setLocation(pot * Math.cos(t) * _coefficient, pot * Math.sin(t) * _coefficient);
		return point;
	}
}
