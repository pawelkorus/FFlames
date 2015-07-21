package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Popcorn variation
 */
public class Popcorn extends AbstractVariation {

	/**
	 * Creates a new instance of Popcorn
	 *
	 * @param _coefficient _coefficient value
	 */
	public Popcorn(Double _coefficient) {
		this._coefficient = (double) _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation((x + _parameters.get(4) * Math.sin(Math.tan(3 * y))) * _coefficient,
				(y + _parameters.get(5) * Math.sin(Math.tan(3 * x))) * _coefficient);
		return point;
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}
