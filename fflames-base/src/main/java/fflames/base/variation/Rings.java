package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Rings variation
 */
public class Rings extends AbstractWariation {

	/**
	 * Creates a new instance of Rings
	 *
	 * @param _coefficient _coefficient value
	 */
	public Rings(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double t = Math.atan(x / y);
		double c = _parameters.get(4) * _parameters.get(4) + 0.000001;
		double mn = (Math.IEEEremainder(r + c, 2 * c) - c + r * (1 - c)) * _coefficient;
		point.setLocation(mn * Math.cos(t), mn * Math.sin(t));
		return point;
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}
