package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Diamond variation
 */
public class Diamond extends AbstractWariation {

	/**
	 * Creates a new instance of Diamond
	 *
	 * @param _coefficient coefficient value
	 */
	public Diamond(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double t = Math.atan2(x, y);
		point.setLocation(Math.sin(t) * Math.cos(r) * coefficient,
				Math.cos(t) * Math.sin(r) * coefficient);
		return point;
	}
}
