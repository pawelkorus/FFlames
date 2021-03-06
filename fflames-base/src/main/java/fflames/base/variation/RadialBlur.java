package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * RadialBlur variation
 */
public class RadialBlur extends AbstractVariation {

	/**
	 * Creates a new instance of RadialBlur
	 *
	 * @param _coefficient _coefficient value
	 */
	public RadialBlur(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	/**
	 * @todo check if this calculations are valid.
	 */
	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double spinvar = Math.sin(_parameters.get(0) * Math.PI / 2.0);
		double zoomvar = Math.cos(_parameters.get(0) * Math.PI / 2.0);

		double rndG = Math.random() + Math.random() + Math.random() + Math.random() - 2.0;
		rndG *= _coefficient;
		double ra = Math.sqrt(x * x + y * y);
		double tmpa = Math.atan2(y, x) + spinvar * rndG;
		double sa = Math.sin(tmpa);
		double ca = Math.cos(tmpa);
		double rz = zoomvar * rndG - 1.0;

		point.setLocation(ra * ca + x * rz, ra * sa + y * rz);

		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 1;
	}
}
