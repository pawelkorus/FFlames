package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Swirl variation
 */
public class Swirl extends AbstractVariation {

	/**
	 * Creates a new instance of Swirl
	 *
	 * @param _coefficient _coefficient value
	 */
	public Swirl(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		
		Double r = new Double(x * x + y * y);
		point.setLocation((x * Math.sin(r) - y * Math.cos(r)) * _coefficient,
				(x * Math.cos(r) + y * Math.sin(r)) * _coefficient);
		return point;
	}
}
