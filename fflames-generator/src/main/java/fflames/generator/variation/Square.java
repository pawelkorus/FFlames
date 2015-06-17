package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Square variation
 */
public class Square extends AbstractWariation {

	/**
	 * Creates a new instance of Square
	 *
	 * @param _coefficient _coefficient value
	 */
	public Square(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		point.setLocation((Math.random() - 0.5) * _coefficient, (Math.random() - 0.5) * _coefficient);
		return point;
	}
}
