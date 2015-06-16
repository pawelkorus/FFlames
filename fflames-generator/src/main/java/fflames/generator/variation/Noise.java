package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Noise variation
 */
public class Noise extends AbstractWariation {

	/**
	 * Creates a new instance of Noise
	 *
	 * @param _coefficient coefficient value
	 */
	public Noise(Double _coefficient) {
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
		double u1 = Math.random();
		double u2 = Math.random();
		point.setLocation(u1 * x * Math.cos(2 * Math.PI * u2) * coefficient, u1 * y * Math.sin(2 * Math.PI * u2) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Noise";
	}
}
