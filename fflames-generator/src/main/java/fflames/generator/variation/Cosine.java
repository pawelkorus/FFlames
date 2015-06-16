package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Cosine variation
 */
public class Cosine extends AbstractWariation {

	/**
	 * Creates a new instance of Cosine
	 *
	 * @param _coefficient coefficient value
	 */
	public Cosine(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		point.setLocation(Math.cos(Math.PI * x) * Math.cosh(y) * coefficient,
				-Math.sin(Math.PI * x) * Math.sinh(y) * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Cosine";
	}
}
