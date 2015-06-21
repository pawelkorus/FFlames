package fflames.base;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class TransformProxy extends Transform {
	private final Transform _sourceTransform;
	private Double _propability;
	
	public TransformProxy(Transform sourceTransform, Double pr) {
		_sourceTransform = sourceTransform;
		_propability = pr;
	}
	
	@Override
	public Point2D transform(Point2D point) {
		return _sourceTransform.transform(point);
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
	public ArrayList<IVariation> getVariations() {
		return _sourceTransform.getVariations();
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
