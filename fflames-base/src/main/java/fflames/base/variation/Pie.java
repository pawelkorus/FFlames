package fflames.base.variation;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Pie variation
 */
public class Pie extends AbstractVariation {

	/**
	 * Creates a new instance of Pie
	 *
	 * @param _coefficient _coefficient value
	 */
	public Pie(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double s1 = Math.floor(Math.random() * _parameters.get(0) + 0.5);
		double a = _parameters.get(1) + 2 * Math.PI * (s1 + Math.random() * _parameters.get(2)) / _parameters.get(0);
		double r = _coefficient * Math.random();
		point.setLocation(r * Math.cos(a), r * Math.sin(a));
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 3;
	}
}
