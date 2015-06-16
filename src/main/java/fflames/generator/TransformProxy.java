package fflames.generator;

import fflames.generator.Transform;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.OutputStreamWriter;
import java.util.Vector;


public class TransformProxy extends Transform {
	private Transform _sourceTransform;
	private Double _propability;
	
	public TransformProxy(Transform sourceTransform, Double pr) {
		_sourceTransform = sourceTransform;
		_propability = pr;
	}
	
	@Override
	public Point2D oblicz(Point2D point) {
		return _sourceTransform.oblicz(point);
	}

	@Override
	public String toString() {
		return _sourceTransform.toString();
	}

	@Override
	public AffineTransform getAffineTr() {
		return _sourceTransform.getAffineTr();
	}

	@Override
	public Vector<IVariation> getWariations() {
		return _sourceTransform.getWariations();
	}

	@Override
	public Double getPropability() {
		return _propability;
	}

	@Override
	public void setPropability(Double value) {
		_propability = value;
	}

	@Override
	public void writeXML(OutputStreamWriter out) {
		_sourceTransform.writeXML(out);
	}
	
}
