package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Ex variation
 */
public class Ex extends AbstractWariation {

	/**
	 * Creates a new instance of Ex
	 *
	 * @param _coefficient _coefficient value
	 */
	public Ex(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double t = Math.atan2(x, y);
		point.setLocation(r * Math.pow(Math.sin(t + r), 3) * _coefficient,
				r * Math.pow(Math.cos(t - r), 3) * _coefficient);
		return point;
	}
}
