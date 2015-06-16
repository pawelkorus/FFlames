package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * PDJ variation
 */
public class PDJ extends AbstractWariation {

	/**
	 * Creates a new instance of PDJ
	 *
	 * @param _coefficient coefficient value
	 */
	public PDJ(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation((Math.sin(param.get(0) * y) - Math.cos(param.get(1) * x)) * coefficient,
				(Math.sin(param.get(2) * x) - Math.cos(param.get(3) * y)) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "PDJ";
	}

	@Override
	public int getParametersQuantity() {
		return 4;
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
