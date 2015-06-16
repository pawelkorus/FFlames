/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Horseshoe variation
 */
public class Horseshoe extends AbstractWariation {

	/**
	 * Creates a new instance of Horseshoe
	 *
	 * @param _coefficient coefficient value
	 */
	public Horseshoe(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		// Zmiana za wiki electricsheep
		double r = 1 / (Math.sqrt(x * x + y * y) + 0.000001);
		point.setLocation(r * (x - y) * (x + y) * coefficient,
				r * 2 * x * y * coefficient);
		return point;
	}

	@Override
	public String getName() {
		return "Horseshoe";
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
