package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Polar variation
 */
public class Polar extends AbstractWariation {

	/**
	 * Konstruktor obiektu klasy Polar
	 *
	 * @param _coefficient coefficient value
	 */
	public Polar(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		point.setLocation(Math.atan2(x, y) / Math.PI * coefficient,
				r - 1.0 * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Polar";
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
