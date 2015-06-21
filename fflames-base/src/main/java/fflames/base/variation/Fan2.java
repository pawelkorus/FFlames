package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Fan2 variation
 */
public class Fan2 extends AbstractWariation {

	/**
	 * Creates a new instance of Fan2
	 *
	 * @param _coefficient _coefficient value
	 */
	public Fan2(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y) * _coefficient;
		double p1 = Math.PI * (_parameters.get(0) * _parameters.get(0) + 0.000001);
		double p2 = _parameters.get(1);
		double theta = Math.atan2(x, y);
		double t = theta + p2 - p1 * Math.floor(theta + p2 / p1);
		if (t > p1 / 2.0) {
			theta -= p1 / 2.0;
		} else {
			theta += p1 / 2.0;
		}

		point.setLocation(r * Math.sin(theta), r * Math.cos(theta));
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
