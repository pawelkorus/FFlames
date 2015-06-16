package fflames.generator.variation;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Pie variation
 */
public class Pie extends AbstractWariation {

	/**
	 * Creates a new instance of Pie
	 *
	 * @param _coefficient coefficient value
	 */
	public Pie(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double s1 = Math.floor(Math.random() * param.get(0) + 0.5);
		double a = param.get(1) + 2 * Math.PI * (s1 + Math.random() * param.get(2)) / param.get(0);
		double r = coefficient * Math.random();
		point.setLocation(r * Math.cos(a), r * Math.sin(a));
		return point;
	}

	@Override
	public String getName() {
		return "Pie";
	}

	@Override
	public int getParametersQuantity() {
		return 3;
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
