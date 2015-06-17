package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Cross variation
 */
public class Cross extends AbstractWariation {

	/**
	 * Creates a new instance of Cross
	 *
	 * @param _coefficient _coefficient value
	 */
	public Cross(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = (x * x - y * y);
		double wsp = _coefficient * Math.sqrt(1.0 / (r * r + 0.000001));
		point.setLocation(wsp * x, wsp * y);
		return point;
	}
}
