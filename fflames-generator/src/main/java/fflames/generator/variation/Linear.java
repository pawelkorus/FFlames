package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Linear variation
 */
public class Linear extends AbstractWariation {

	/**
	 * Creates a new instance of Linear
	 *
	 * @param _coefficient coefficient value
	 */
	public Linear(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		point.setLocation(point.getX() * coefficient, point.getY() * coefficient);
		return point;
	}
}
