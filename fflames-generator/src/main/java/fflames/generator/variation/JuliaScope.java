package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * JuliaScope variation
 */
public class JuliaScope extends AbstractWariation {

	/**
	 * Creates a new instance of JuliaScope
	 *
	 * @param _coefficient _coefficient value
	 */
	public JuliaScope(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double psi = Math.round(Math.random() * 10.0);
		double rand = Math.random() * 2 - 1.0;
		double phi = Math.atan2(y, x);
		double t = (rand * phi + 2.0 * Math.PI * Math.abs(_parameters.get(0)) * psi) / _parameters.get(1);
		double r = Math.sqrt(x * x + y * y);
		point.setLocation(Math.pow(r, _parameters.get(0) / _parameters.get(1)) * Math.cos(t) * _coefficient,
				Math.pow(r, _parameters.get(0) / _parameters.get(1)) * Math.sin(t) * _coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
