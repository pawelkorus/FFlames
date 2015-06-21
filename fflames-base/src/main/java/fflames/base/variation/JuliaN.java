package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * JuliaN variation
 */
public class JuliaN extends AbstractWariation {

	/**
	 * Creates a new instance of JuliaN
	 *
	 * @param _coefficient _coefficient value
	 */
	public JuliaN(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double phi = Math.atan2(y, x);
		double r = Math.sqrt(x * x + y * y);
		double t = (phi + 2 * Math.PI * Math.abs(_parameters.get(0)) * Math.random()) / _parameters.get(0);
		point.setLocation(Math.pow(r, _parameters.get(1) / _parameters.get(0)) * Math.cos(t) * _coefficient,
				Math.pow(r, _parameters.get(1) / _parameters.get(0)) * Math.sin(t) * _coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
