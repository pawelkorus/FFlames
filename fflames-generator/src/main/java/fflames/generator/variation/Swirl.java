package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Swirl variation
 */
public class Swirl extends AbstractWariation {

	/**
	 * Creates a new instance of Swirl
	 *
	 * @param _coefficient coefficient value
	 */
	public Swirl(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		
		Double r = new Double(x * x + y * y);
		point.setLocation((x * Math.sin(r) - y * Math.cos(r)) * coefficient,
				(x * Math.cos(r) + y * Math.sin(r)) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Swirl";
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
