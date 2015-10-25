package fflames.base;

import fflames.base.variation.VariationsFactory;

public class RotationalSymmetryTransform extends Transform {

	public RotationalSymmetryTransform(Double radians) {
		super();

		getAffineTr().setToRotation(radians);
		getVariations().add(VariationsFactory.getVariation("Linear", 1.0));
	}

}
