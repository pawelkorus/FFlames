package fflames.base.variation;

import java.awt.geom.Point2D;

public class Arch extends AbstractVariation {

	/**
	 * Creates a new instance of Arch
	 *
	 * @param _coefficient _coefficient value
	 */
	public Arch(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		double ang = Math.random() * Math.PI * _coefficient;
		point.setLocation(_coefficient * Math.sin(ang),
				_coefficient * Math.sin(ang) * Math.sin(ang) / Math.cos(ang));
		return point;
	}
}
