package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * JuliaScope variation
 */
public class JuliaScope extends AbstractWariation {

	/**
	 * Creates a new instance of JuliaScope
	 *
	 * @param _coefficient coefficient value
	 */
	public JuliaScope(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double psi = Math.round(Math.random() * 10.0);
		double rand = Math.random() * 2 - 1.0;
		double phi = Math.atan2(y, x);
		double t = (rand * phi + 2.0 * Math.PI * Math.abs(param.get(0)) * psi) / param.get(1);
		double r = Math.sqrt(x * x + y * y);
		point.setLocation(Math.pow(r, param.get(0) / param.get(1)) * Math.cos(t) * coefficient,
				Math.pow(r, param.get(0) / param.get(1)) * Math.sin(t) * coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
