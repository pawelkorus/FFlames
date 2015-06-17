package fflames.generator.variation;

import java.awt.geom.Point2D;

public class Curl extends AbstractWariation {

	/**
	 * Creates a new instance of Curl
	 *
	 * @param _coefficient coefficient value
	 */
	public Curl(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double t1 = 1 + param.get(0) * x + param.get(1) * (x * x + y * y);
		double t2 = param.get(0) * y + 2.0 * param.get(1) * x * y;
		double k = 1 / (t1 * t1 + t2 * t2);
		point.setLocation(k * (t1 * x + y * t2) * coefficient, k * (y * t1 - x * t2) * coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
