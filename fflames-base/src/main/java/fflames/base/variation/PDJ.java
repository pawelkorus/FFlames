package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * PDJ variation
 */
public class PDJ extends AbstractWariation {

	/**
	 * Creates a new instance of PDJ
	 *
	 * @param _coefficient _coefficient value
	 */
	public PDJ(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation((Math.sin(_parameters.get(0) * y) - Math.cos(_parameters.get(1) * x)) * _coefficient,
				(Math.sin(_parameters.get(2) * x) - Math.cos(_parameters.get(3) * y)) * _coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 4;
	}
}
