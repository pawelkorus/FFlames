package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Spherical variation
 */
public class Spherical extends AbstractVariation {

	/**
	 * Creates a new instance of Spherical
	 *
	 * @param _coefficient _coefficient value
	 */
	public Spherical(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		// Zmiana wprowadzona za wiki electicsheep
		double r = 1 / (x * x + y * y + 0.000001);
		point.setLocation(x * r * _coefficient, y * r * _coefficient);
		return point;
	}
}
