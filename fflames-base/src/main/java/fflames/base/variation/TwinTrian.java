package fflames.base.variation;

import java.awt.geom.Point2D;

/**
 * TwinTrian variation
 */
public class TwinTrian extends AbstractVariation {

	/**
	 * Creates a new instance of TwinTrian
	 *
	 * @param _coefficient _coefficient value
	 */
	public TwinTrian(Double _coefficient) {
		this._coefficient = new Double(_coefficient);
	}
	
	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double sin = _coefficient * Math.random() * r;
		double t = Math.log10(sin * sin) + Math.cos(_coefficient * Math.random() * r);
		point.setLocation(x * t * _coefficient, _coefficient * x * (t - Math.PI * sin));
		return point;
	}
}
