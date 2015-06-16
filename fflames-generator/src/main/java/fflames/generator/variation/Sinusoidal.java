package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Sinusoidal variation
 */
public class Sinusoidal extends AbstractWariation {

	/**
	 * Creates a new instance of Sinusoidal
	 *
	 * @param _coefficient coefficient value
	 */
	public Sinusoidal(double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}

	@Override
	public Point2D calculate(Point2D punkt) {
		punkt.setLocation(Math.sin(punkt.getX()) * coefficient, Math.sin(punkt.getY()) * coefficient);
		return punkt;
	}

	@Override
	public String getName() {
		return "Sinusoidal";
	}
}
