package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Blur variation
 */
public class Blur extends AbstractWariation {

	/**
	 * Creates a new instance of Blur
	 *
	 * @param _coefficient _coefficient value
	 */
	public Blur(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double psi1 = Math.random() * 2 * Math.PI;
		double psi2 = Math.random();
		point.setLocation(psi2 * Math.cos(psi1) * _coefficient,
				psi2 * Math.sin(psi1) * _coefficient);
		return point;
	}
}
