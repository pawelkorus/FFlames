package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Rays variation
 */
public class Rays extends AbstractWariation {

	/**
	 * Creates a new instance of Rays
	 *
	 * @param _coefficient coefficient value
	 */
	public Rays(Double _coefficient) {
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
		double ang = Math.random() * Math.PI * coefficient;
		double r = coefficient / (x * x + y * y + 0.000001);
		double tanr = coefficient * Math.tan(ang) * r;
		point.setLocation(tanr * Math.cos(x), tanr * Math.sin(y));
		return point;
	}

	@Override
	public String getName() {
		return "Rays";
	}
}
