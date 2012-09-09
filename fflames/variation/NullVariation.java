package fflames.variation;

import java.awt.geom.Point2D;
import java.util.Vector;

public class NullVariation extends AbstractWariation {

	@Override
	public Double getCoefficient() {
		return Double.valueOf(0);
	}

	@Override
	public void setCoefficient(Double _coefficient) {
		return;
	}

	@Override
	public Point2D oblicz(Point2D point) {
		point.setLocation(Double.valueOf(0), Double.valueOf(0));
		return point;
	}

	@Override
	public int getParametersQuantity() {
		return 0;
	}

	@Override
	public void setParameters(Vector<Double> parameters) {
		return;
	}

	@Override
	public Vector<Double> getParameters() {
		return new Vector<Double>();
	}

	@Override
	public String getWariationName() {
		return "Null";
	}

	@Override
	public boolean isDependent() {
		return false;
	}
	
	public boolean isValid() {
		return false;
	}
}
