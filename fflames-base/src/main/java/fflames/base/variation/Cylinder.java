package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Cylinder variation
 */
public class Cylinder extends AbstractWariation {

	/**
	 * Creates a new instance of Cylinder
	 *
	 * @param _coefficient _coefficient value
	 */
	public Cylinder(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation(Math.sin(x) * _coefficient, y * _coefficient);
		return point;
	}
}
