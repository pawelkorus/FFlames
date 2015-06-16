package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Square variation
 */
public class Square extends AbstractWariation {

	/**
	 * Creates a new instance of Square
	 *
	 * @param _coefficient coefficient value
	 */
	public Square(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}

	@Override
	public Point2D calculate(Point2D point) {
		point.setLocation((Math.random() - 0.5) * coefficient, (Math.random() - 0.5) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Square";
	}
}
