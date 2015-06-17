package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Bent variation
 */
public class Bent extends AbstractWariation {

	/**
	 * Creates a new instance of Bent
	 *
	 * @param _coefficient _coefficient value
	 */
	public Bent(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		if (x < 0) {
			x *= 2.0;
		}
		if (y < 0) {
			y /= 2.0;
		}
		point.setLocation(x * _coefficient, y * _coefficient);
		return point;
	}
}
