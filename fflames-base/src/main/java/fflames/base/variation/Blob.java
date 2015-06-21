package fflames.base.variation;

import java.awt.geom.Point2D;

public class Blob extends AbstractWariation {

	/**
	 * Creates a new instance of Blob
	 *
	 * @param _coefficient _coefficient value
	 */
	public Blob(Double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		Double r = Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
		Double teta = Math.atan2(point.getX(), point.getY());
		Double wsp = r * (_parameters.get(1) + ((_parameters.get(0) - _parameters.get(1)) / 2.0)) * (Math.sin(_parameters.get(2) * teta) + 1);
		point.setLocation(wsp * Math.cos(teta) * _coefficient, wsp * Math.sin(teta) * _coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 3;
	}
}
