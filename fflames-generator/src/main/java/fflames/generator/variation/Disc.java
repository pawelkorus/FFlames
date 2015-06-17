package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Disc variation
 */
public class Disc extends AbstractWariation {

	/**
	 * Creates a new instance of Disc
	 *
	 * @param _coefficient _coefficient value
	 */
	public Disc(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		//Zmiany za wiki electricsheep
		double r = Math.PI * Math.sqrt(x * x + y * y);
		double theta = Math.atan2(Math.PI * x, Math.PI * y) / Math.PI;
		point.setLocation(Math.sin(r) * theta * _coefficient,
				Math.cos(r) * theta * _coefficient);
		return point;
	}
}
