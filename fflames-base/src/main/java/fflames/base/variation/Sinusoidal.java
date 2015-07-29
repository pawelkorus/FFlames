package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Sinusoidal variation
 */
public class Sinusoidal extends AbstractVariation {

	/**
	 * Creates a new instance of Sinusoidal
	 *
	 * @param _coefficient _coefficient value
	 */
	public Sinusoidal(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D punkt) {
		punkt.setLocation(Math.sin(punkt.getX()) * _coefficient, Math.sin(punkt.getY()) * _coefficient);
		return punkt;
	}
}
