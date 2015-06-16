package fflames.generator.variation;

import java.util.ArrayList;

import fflames.generator.IVariation;

/**
 * Variation abstract class.
 *
 * Provides basic implementation of IVariation interface. Default implemention
 * has 0 additional parameters.
 *
 */
public abstract class AbstractWariation implements IVariation {

	protected ArrayList<Double> param = null;
	protected Double coefficient;

	@Override
	public Double getCoefficient() {
		return coefficient;
	}

	@Override
	public void setCoefficient(Double _coefficient) {
		coefficient = _coefficient;
	}

	@Override
	public int getParametersQuantity() {
		return 0;
	}

	@Override
	public void setParameters(ArrayList<Double> parameters) {
		param = new ArrayList<>(parameters);
	}

	@Override
	public ArrayList<Double> getParameters() {
		ArrayList<Double> temp = new ArrayList<>();
		temp.add(coefficient);
		if (param != null) {
			temp.addAll(param);
		}
		return temp;
	}

	@Override
	public boolean isDependent() {
		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}
}
