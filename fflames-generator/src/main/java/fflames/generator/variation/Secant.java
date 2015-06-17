package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Secant variation
 */
public class Secant extends AbstractWariation {

	/**
	 * Creates a new instance of Secant
	 *
	 * @param _coefficient coefficient value
	 */
	public Secant(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double r = Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY());
		point.setLocation(point.getX(), 1.0 / Math.cos(r * coefficient));
		return point;
	}
}
