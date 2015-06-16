package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Exponential variation
 */
public class Exponential extends AbstractWariation {

	/**
	 * Creates a new instance of Exponential
	 *
	 * @param _coefficient coefficient value
	 */
	public Exponential(Double _coefficient) {
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
		point.setLocation(Math.exp(x - 1.0) * Math.cos(Math.PI * y) * coefficient,
				Math.exp(x - 1.0) * Math.sin(Math.PI * y) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Exponential";
	}
}
