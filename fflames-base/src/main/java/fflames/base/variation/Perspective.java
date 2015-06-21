package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Perspective variation
 */
public class Perspective extends AbstractWariation {

	/**
	 * Creates a new instance of Perspective
	 *
	 * @param _coefficient _coefficient value
	 */
	public Perspective(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double p = _parameters.get(1) / (_parameters.get(1) - y * Math.sin(_parameters.get(0)));
		point.setLocation(p * x * _coefficient, p * y * Math.cos(_parameters.get(0)) * _coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
