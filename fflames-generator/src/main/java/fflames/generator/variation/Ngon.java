package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Ngon variation
 */
public class Ngon extends AbstractWariation {

	/**
	 * Creates a new instance of Ngon
	 *
	 * @param _coefficient coefficient value
	 */
	public Ngon(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r_factor = Math.pow(x * x + y * y, param.get(0) / 2.0);
		double theta = Math.atan2(y, x);
		double b = 2 * Math.PI / (param.get(1) + 0.000001);
		double phi = theta - (b * Math.floor(theta / b));
		if (phi > b / 2.0) {
			phi -= b;
		}
		double amp = param.get(2) * (1.0 / (Math.cos(phi) + 0.000001) - 1.0) + param.get(3);
		amp = amp / (r_factor + 0.000001);
		point.setLocation(coefficient * x * amp,
				coefficient * y * amp);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 4;
	}
}
