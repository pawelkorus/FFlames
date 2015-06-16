package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Heart variation
 */
public class Heart extends AbstractWariation {

	/**
	 * Creates a new instance of Heart
	 *
	 * @param _coefficient coefficient value
	 */
	public Heart(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double theta = Math.atan2(x, y);
		point.setLocation(r * Math.sin(theta * r) * coefficient,
				(-r) * Math.cos(theta * r) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Heart";
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
