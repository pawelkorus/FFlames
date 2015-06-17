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
	 * @param _coefficient variation _coefficient
	 */
	public Waves(Double _coefficient) {
		this._coefficient = (double) _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double precision = 0.0000001;
		double x = point.getX();
		double y = point.getY();
		
		double c = _parameters.get(4);
		if(c < precision) c = precision;
		
		double f = _parameters.get(5);
		if(f < precision) f = precision;
		
		point.setLocation((x + _parameters.get(2) * Math.sin(y / (c * c))) * _coefficient,
				(y + _parameters.get(3) * Math.sin(x / (f * f))) * _coefficient
		);
		
		return point;
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}
