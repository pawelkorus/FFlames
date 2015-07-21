package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Blade variation
 */
public class Blade extends AbstractVariation {

	/**
	 * Creates a new instance of Blade
	 *
	 * @param _coefficient _coefficient value
	 */
	public Blade(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.random() * _coefficient * Math.sqrt(x * x + y * y);
		point.setLocation(_coefficient * x * (Math.cos(r) + Math.sin(r)),
				x * 1.0 / Math.cos(r));
		return point;
	}
}
