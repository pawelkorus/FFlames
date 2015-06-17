package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Tangent variation
 */
public class Tangent extends AbstractWariation {

	/**
	 * Creates a new instance of Tangent
	 *
	 * @param _coefficient coefficient value
	 */
	public Tangent(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		point.setLocation(coefficient * Math.sin(point.getX()) / Math.cos(point.getY()),
				coefficient * Math.sin(point.getY()) / Math.cos(point.getX()));
		return point;
	}
}
