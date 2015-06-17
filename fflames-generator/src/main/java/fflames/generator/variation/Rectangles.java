package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Rectangles variation
 */
public class Rectangles extends AbstractWariation {

	/**
	 * Creates a new instance of Rectangles
	 *
	 * @param _coefficient coefficient value
	 */
	public Rectangles(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		if (param.get(0) == 0) {
			x *= coefficient;
		} else {
			x = coefficient * (2.0 * Math.floor(x / param.get(0)) + 1.0) * param.get(0) - x;
		}
		if (param.get(1) == 0) {
			y *= coefficient;
		} else {
			y = coefficient * (2.0 * Math.floor(y / param.get(1)) + 1.0) * param.get(1) - y;
		}
		point.setLocation(x, y);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 2;
	}
}
