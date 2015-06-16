package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Popcorn variation
 */
public class Popcorn extends AbstractWariation {

	/**
	 * Creates a new instance of Popcorn
	 *
	 * @param _coefficient coefficient value
	 */
	public Popcorn(Double _coefficient) {
		coefficient = (double) _coefficient;
	}

	@Override
	public String toString() {
		String parameterValueStr;
		if (getParameters().size() > 0) {
			parameterValueStr = getParameters().get(0).toString();
		} else {
			parameterValueStr = "Undefined";
		}

		return getName() + " " + parameterValueStr;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation((x + param.get(4) * Math.sin(Math.tan(3 * y))) * coefficient,
				(y + param.get(5) * Math.sin(Math.tan(3 * x))) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Popcorn";
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}
