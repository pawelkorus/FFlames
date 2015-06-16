package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Perspective variation
 */
public class Perspective extends AbstractWariation {

	/**
	 * Creates a new instance of Perspective
	 *
	 * @param _coefficient coefficient value
	 */
	public Perspective(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double p = param.get(1) / (param.get(1) - y * Math.sin(param.get(0)));
		point.setLocation(p * x * coefficient, p * y * Math.cos(param.get(0)) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Perspective";
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
