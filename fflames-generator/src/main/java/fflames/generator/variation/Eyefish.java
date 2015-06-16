package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Eyefish variation
 */
public class Eyefish extends AbstractWariation {

	/**
	 * Creates a new instance of Eyefish
	 *
	 * @param _coefficient coefficient value
	 */
	public Eyefish(Double _coefficient) {
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
		double r = Math.sqrt(x * x + y * y);
		point.setLocation(2.0 / (r + 1.0) * x * coefficient, 2.0 / (r + 1.0) * y * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Eyefish";
	}
}
