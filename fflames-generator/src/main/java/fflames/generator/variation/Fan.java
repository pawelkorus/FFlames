package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Fan variation
 */
public class Fan extends AbstractWariation {

	/**
	 * Creates a new instance of Fan
	 *
	 * @param _coefficient _coefficient value
	 */
	public Fan(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double theta = Math.atan(x / y);
		double c = _parameters.get(4) * _parameters.get(4);
		double f = _parameters.get(5);
		double t = Math.PI * c;

		if (Math.IEEEremainder(theta + f, t) > t / 2.0) {
			point.setLocation(r * Math.cos(theta - t / 2.0) * _coefficient,
					r * Math.sin(theta - t / 2.0) * _coefficient);
		} else {
			point.setLocation(r * Math.cos(theta + t / 2.0) * _coefficient,
					r * Math.sin(theta + t / 2.0) * _coefficient);
		}

		return point;
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}
