package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * Julia variation
 */
public class Julia extends AbstractWariation {

	/**
	 * Creates a new instance of Julia
	 *
	 * @param _coefficient _coefficient value
	 */
	public Julia(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		//Dla tej wariacji potrzebny jest pierwiastek z r
		double r = Math.pow(x * x + y * y, 0.25);
		double t = Math.atan2(x, y);
		/* Obliczanie parametru omega: jesli liczba losowa z przedzialu (0, 1)
		 * mniejsza ni� 0.5 to omega = 0. Inaczej omega = pi */
		double o = 0;
		if (Math.random() > 0.5) {
			o = Math.PI;
		}
		point.setLocation(r * Math.cos(t / 2 + o) * _coefficient, r * Math.sin(t / 2 + o) * _coefficient);
		return point;
	}
}
