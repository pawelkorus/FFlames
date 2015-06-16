package fflames.generator.variation;

import java.awt.geom.Point2D;

public class Arch extends AbstractWariation {

	/**
	 * Creates a new instance of Arch
	 *
	 * @param _coefficient coefficient value
	 */
	public Arch(Double _coefficient) {
		coefficient = _coefficient;
	}

	public Point2D calculate(Point2D point) {
		double ang = Math.random() * Math.PI * coefficient;
		point.setLocation(coefficient * Math.sin(ang),
				coefficient * Math.sin(ang) * Math.sin(ang) / Math.cos(ang));
		return point;
	}

	public String getName() {
		return "Arch";
	}

	@Override
	public String toString() {
		return getName() + getParameters().toString();
	}
}
