package fflames.generator.variation;

import java.awt.geom.Point2D;

public class Blob extends AbstractWariation {

	/**
	 * Creates a new instance of Blob
	 *
	 * @param _coefficient coefficient value
	 */
	public Blob(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public Point2D calculate(Point2D point) {
		Double r = Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
		Double teta = Math.atan2(point.getX(), point.getY());
		Double wsp = r * (param.get(1) + ((param.get(0) - param.get(1)) / 2.0)) * (Math.sin(param.get(2) * teta) + 1);
		point.setLocation(wsp * Math.cos(teta) * coefficient, wsp * Math.sin(teta) * coefficient);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 3;
	}
}
