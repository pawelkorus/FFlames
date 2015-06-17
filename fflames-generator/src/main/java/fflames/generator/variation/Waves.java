package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Waves variation.
 *
 * This variation depends on affine transform parameters. Additional parameters
 * that come from affine transform will be set by setParameters method. Those
 * two additional parameters will not be counted as normal parameters and method
 * getParametersQuantity will return 0.
 */
public class Waves extends AbstractWariation {

	/**
	 * Creates a new instance of Waves
	 *
	 * @param _coefficient variation coefficient
	 */
	public Waves(Double _coefficient) {
		coefficient = (double) _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double precision = 0.0000001;
		double x = point.getX();
		double y = point.getY();
		
		double c = param.get(4);
		if(c < precision) c = precision;
		
		double f = param.get(5);
		if(f < precision) f = precision;
		
		point.setLocation(
				(x + param.get(2) * Math.sin(y / (c * c))) * coefficient,
				(y + param.get(3) * Math.sin(x / (f * f))) * coefficient
		);
		
		return point;
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}