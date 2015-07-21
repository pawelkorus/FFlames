package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Rectangles variation
 */
public class Rectangles extends AbstractVariation {

	/**
	 * Creates a new instance of Rectangles
	 *
	 * @param _coefficient _coefficient value
	 */
	public Rectangles(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		if (_parameters.get(0) == 0) {
			x *= _coefficient;
		} else {
			x = _coefficient * (2.0 * Math.floor(x / _parameters.get(0)) + 1.0) * _parameters.get(0) - x;
		}
		if (_parameters.get(1) == 0) {
			y *= _coefficient;
		} else {
			y = _coefficient * (2.0 * Math.floor(y / _parameters.get(1)) + 1.0) * _parameters.get(1) - y;
		}
		point.setLocation(x, y);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
