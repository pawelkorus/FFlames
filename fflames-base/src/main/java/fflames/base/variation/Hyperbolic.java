/*
 * Hyperbolic.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Hyperbolic variation
 */
public class Hyperbolic extends AbstractWariation {

	/**
	 * Creates a new instance of Hyperbolic
	 *
	 * @param _coefficient _coefficient value
	 */
	public Hyperbolic(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}
	
	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y) + 0.000001;
		double t = Math.atan2(x, y);
		point.setLocation(Math.sin(t) / r * _coefficient,
				r * Math.cos(t) * _coefficient);
		return point;
	}
}
