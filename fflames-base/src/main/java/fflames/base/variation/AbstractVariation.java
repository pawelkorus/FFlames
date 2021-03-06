package fflames.base.variation;

import java.util.List;
import java.util.ArrayList;

import fflames.base.IVariation;
import java.awt.geom.Point2D;

/**
 * Variation abstract class. Provides basic implementation of IVariation 
 * interface. Default implementation has 0 additional parameters and its
 * coefficient is set to 0 .
 *
 */
public abstract class AbstractVariation implements IVariation {

	protected ArrayList<Double> _parameters = new ArrayList<>();
	protected double _coefficient = 0.0;

	@Override
	public double getCoefficient() {
		return _coefficient;
	}

	@Override
	public void setCoefficient(double _coefficient) {
		this._coefficient = _coefficient;
	}

	@Override
	public int getParametersQuantity() {
		return 0;
	}

	@Override
	public void setParameters(List<Double> parameters) {
		_parameters = new ArrayList<>(parameters);
	}

	@Override
	public List<Double> getParameters() {
		return _parameters;
	}

	@Override
	public boolean isDependent() {
		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}
	
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	
	@Override
	public String toString() {
		return getName() + " " + getCoefficient() + " " + getParameters().toString();
	}

	@Override
	public void transform(Point2D source, Point2D out) {
		out.setLocation(source);
		calculate(out);
	}
	
	/**
	 * Calculates new coordinates for the given point. It doesn't change given 
	 * object. It returns new instance of Point2D object.
	 * 
	 * @param point source point
	 * @return new Point2D instance containing calculated coordinates
	 */
	public abstract Point2D calculate(Point2D point);
}