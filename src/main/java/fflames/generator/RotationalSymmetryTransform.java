package fflames.generator;

import fflames.generator.Transform;
import fflames.generator.variation.VariationsFactory;

public class RotationalSymmetryTransform extends Transform {

	public RotationalSymmetryTransform(Double radians, Double pr) {
		super();
		
		getAffineTr().setToRotation(radians);
		getWariations().add(VariationsFactory.getWariation("Linear", 1.0));
		
		setPropability(pr);
	}

}
