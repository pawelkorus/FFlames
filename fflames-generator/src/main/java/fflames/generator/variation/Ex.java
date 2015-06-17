package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Ex variation
 */
public class Ex extends AbstractWariation {

	/**
	 * Creates a new instance of Ex
	 *
	 * @param _coefficient coefficient value
	 */
	public Ex(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double t = Math.atan2(x, y);
		point.setLocation(r * Math.pow(Math.sin(t + r), 3) * coefficient,
				r * Math.pow(Math.cos(t - r), 3) * coefficient);
		return point;
	}
}
