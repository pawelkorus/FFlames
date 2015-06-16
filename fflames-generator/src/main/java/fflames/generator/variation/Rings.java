package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Rings variation
 */
public class Rings extends AbstractWariation {

	/**
	 * Creates a new instance of Rings
	 *
	 * @param _coefficient coefficient value
	 */
	public Rings(Double _coefficient) {
		coefficient = new Double(_coefficient);
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}

	@Override
	public Point2D calculate(Point2D point) {
		double x = point.getX();
		double y = point.getY();
		double r = Math.sqrt(x * x + y * y);
		double t = Math.atan(x / y);
		double c = param.get(4) * param.get(4) + 0.000001;
		double mn = (Math.IEEEremainder(r + c, 2 * c) - c + r * (1 - c)) * coefficient;
		point.setLocation(mn * Math.cos(t), mn * Math.sin(t));
		return point;
	}

	@Override
	public String getName() {
		return "Rings";
	}

	@Override
	public boolean isDependent() {
		return true;
	}
}
