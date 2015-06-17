package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Cylinder variation
 */
public class Cylinder extends AbstractWariation {

	/**
	 * Creates a new instance of Cylinder
	 *
	 * @param _coefficient coefficient value
	 */
	public Cylinder(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation(Math.sin(x) * coefficient, y * coefficient);
		return point;
	}
}
