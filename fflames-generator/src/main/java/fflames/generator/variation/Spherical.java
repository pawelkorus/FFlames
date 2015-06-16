package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Spherical variation
 */
public class Spherical extends AbstractWariation {

	/**
	 * Creates a new instance of Spherical
	 *
	 * @param _coefficient coefficient value
	 */
	public Spherical(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		// Zmiana wprowadzona za wiki electicsheep
		double r = 1 / (x * x + y * y + 0.000001);
		point.setLocation(x * r * coefficient, y * r * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Spherical";
	}
}
