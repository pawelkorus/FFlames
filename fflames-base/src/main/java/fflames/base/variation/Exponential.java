package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Exponential variation
 */
public class Exponential extends AbstractWariation {

	/**
	 * Creates a new instance of Exponential
	 *
	 * @param _coefficient _coefficient value
	 */
	public Exponential(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation(Math.exp(x - 1.0) * Math.cos(Math.PI * y) * _coefficient,
				Math.exp(x - 1.0) * Math.sin(Math.PI * y) * _coefficient);
		return point;
	}
}
