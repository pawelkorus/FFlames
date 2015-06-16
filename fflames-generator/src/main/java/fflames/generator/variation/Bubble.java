package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Bubble variation
 */
public class Bubble extends AbstractWariation {

	/**
	 * Creates a new instance of Bubble
	 *
	 * @param _coefficient coefficient value
	 */
	public Bubble(Double _coefficient) {
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
		double r = ((x * x + y * y) / 4.0 + 1.0) * coefficient;
		point.setLocation(r * x, r * y);
		return point;
	}

	@Override
	public String getName() {
		return "Bubble";
	}
}
