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
	public String toString() {
		String parameterValueStr;

		if (getParameters().size() > 0) {
			parameterValueStr = getParameters().get(0).toString();
		} else {
			parameterValueStr = "Unknown";
		}

		return getName() + " " + parameterValueStr;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		//Ma�e oszustwo jezeli c i f s� r�wne 0
		double c = param.get(4);// if(c == 0) c = 0.0000001;
		double f = param.get(5);// if(f == 0) f = 0.0000001;
		point.setLocation((x + param.get(2) * Math.sin(y / ((c * c) + 0.000001))) * coefficient,
				(y + param.get(3) * Math.sin(x / ((f * f) + 0.000001)) * coefficient));
		return point;
	}

	@Override
	public String getName() {
		return "Waves";
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}
