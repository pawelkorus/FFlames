package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Bubble variation
 */
public class Bubble extends AbstractVariation {

	/**
	 * Creates a new instance of Bubble
	 *
	 * @param _coefficient _coefficient value
	 */
	public Bubble(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = ((x * x + y * y) / 4.0 + 1.0) * _coefficient;
		point.setLocation(r * x, r * y);
		return point;
	}
}
