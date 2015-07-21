package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Ngon variation
 */
public class Ngon extends AbstractVariation {

	/**
	 * Creates a new instance of Ngon
	 *
	 * @param _coefficient _coefficient value
	 */
	public Ngon(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r_factor = Math.pow(x * x + y * y, _parameters.get(0) / 2.0);
		double theta = Math.atan2(y, x);
		double b = 2 * Math.PI / (_parameters.get(1) + 0.000001);
		double phi = theta - (b * Math.floor(theta / b));
		if (phi > b / 2.0) {
			phi -= b;
		}
		double amp = _parameters.get(2) * (1.0 / (Math.cos(phi) + 0.000001) - 1.0) + _parameters.get(3);
		amp = amp / (r_factor + 0.000001);
		point.setLocation(_coefficient * x * amp,
				_coefficient * y * amp);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 4;
	}
}
