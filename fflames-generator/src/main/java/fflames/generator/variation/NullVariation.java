package fflames.generator.variation;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Null variation
 */
public class NullVariation extends AbstractWariation {

	@Override
	public double getCoefficient() {
		return 0;
	}

	@Override
	public void setCoefficient(double _coefficient) {
	}

	@Override
	public Point2D calculate(Point2D point) {
		point.setLocation(0, 0);
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 0;
	}

	@Override
	public void setParameters(List<Double> parameters) {
	}

	@Override
	public List<Double> getParameters() {
		return new ArrayList<>();
	}

	@Override
	public String getName() {
		return "Null";
	}

	@Override
	public boolean isDependent() {
		return false;
	}

	@Override
	public boolean isValid() {
		return false;
	}
}
