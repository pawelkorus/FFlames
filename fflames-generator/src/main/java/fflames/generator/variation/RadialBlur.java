package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * RadialBlur variation
 */
public class RadialBlur extends AbstractWariation {

	/**
	 * Creates a new instance of RadialBlur
	 *
	 * @param _coefficient coefficient value
	 */
	public RadialBlur(Double _coefficient) {
		coefficient = _coefficient;
	}

	/**
	 * @todo check if this calculations are valid.
	 */
	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double spinvar = Math.sin(param.get(0) * Math.PI / 2.0);
		double zoomvar = Math.cos(param.get(0) * Math.PI / 2.0);

		double rndG = Math.random() + Math.random() + Math.random() + Math.random() - 2.0;
		rndG *= coefficient;
		double ra = Math.sqrt(x * x + y * y);
		double tmpa = Math.atan2(y, x) + spinvar * rndG;
		double sa = Math.sin(tmpa);
		double ca = Math.cos(tmpa);
		double rz = zoomvar * rndG - 1.0;

		point.setLocation(ra * ca + x * rz, ra * sa + y * rz);

		return point;
	}

	@Override
	public String getName() {
		return "RadialBlur";
	}

	@Override
	public int getParametersQuantity() {
		return 1;
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
