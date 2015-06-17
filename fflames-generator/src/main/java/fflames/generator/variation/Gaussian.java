package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Gaussian variation
 */
public class Gaussian extends AbstractWariation {

	/**
	 * Creates a new instance of Gaussian
	 *
	 * @param _coefficient coefficient value
	 */
	public Gaussian(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double sumpsi = 0.0;
		for (int i = 0; i < 4; i++) {
			sumpsi += Math.random();
		}
		sumpsi -= 2.0;
		sumpsi *= coefficient;
		double angle = Math.random() * 2 * Math.PI;
		double sina = Math.sin(angle);
		double cosa = Math.cos(angle);
		point.setLocation(sumpsi * cosa, sumpsi * sina);
		return point;
	}
}
