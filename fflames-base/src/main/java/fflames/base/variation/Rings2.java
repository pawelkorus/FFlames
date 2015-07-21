package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Rings2 variation
 */
public class Rings2 extends AbstractVariation {

	/**
	 * Creates a new instance of Rings2
	 *
	 * @param _coefficient _coefficient value
	 */
	public Rings2(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double p = _parameters.get(0) * _parameters.get(0) + 0.000001;
		double theta = Math.atan2(x, y);
		double t = r - 2 * p * Math.floor((r + p) / 2 * p) + r * (1 - p);
		point.setLocation(t * Math.sin(theta) * _coefficient, t * Math.cos(theta) * _coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 1;
	}
}
