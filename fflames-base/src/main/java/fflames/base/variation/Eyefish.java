package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Eyefish variation
 */
public class Eyefish extends AbstractWariation {

	/**
	 * Creates a new instance of Eyefish
	 *
	 * @param _coefficient _coefficient value
	 */
	public Eyefish(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		point.setLocation(2.0 / (r + 1.0) * x * _coefficient, 2.0 / (r + 1.0) * y * _coefficient);
		return point;
	}
}
