package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Power variation
 */
public class Power extends AbstractWariation {

	/**
	 * Creates a new instance of Power
	 *
	 * @param _coefficient coefficient value
	 */
	public Power(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double t = Math.atan2(x, y);
		double pot = Math.pow(r, Math.sin(t));
		point.setLocation(pot * Math.cos(t) * coefficient, pot * Math.sin(t) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Power";
	}
}
