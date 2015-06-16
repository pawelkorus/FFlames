package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Spiral variation
 */
public class Spiral extends AbstractWariation {

	/**
	 * Creates a new instance of Spiral
	 *
	 * @param _coefficient coefficient value
	 */
	public Spiral(Double _coefficient) {
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
		double r = Math.sqrt(x * x + y * y) + 0.000001;
		double t = Math.atan2(x, y);
		point.setLocation(1 / r * (Math.cos(t) + Math.sin(r)) * coefficient,
				1 / r * (Math.sin(t) - Math.cos(r)) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Spiral";
	}
}
