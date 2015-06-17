package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * JuliaN variation
 */
public class JuliaN extends AbstractWariation {

	/**
	 * Creates a new instance of JuliaN
	 *
	 * @param _coefficient coefficient value
	 */
	public JuliaN(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double phi = Math.atan2(y, x);
		double r = Math.sqrt(x * x + y * y);
		double t = (phi + 2 * Math.PI * Math.abs(param.get(0)) * Math.random()) / param.get(0);
		point.setLocation(Math.pow(r, param.get(1) / param.get(0)) * Math.cos(t) * coefficient,
				Math.pow(r, param.get(1) / param.get(0)) * Math.sin(t) * coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
