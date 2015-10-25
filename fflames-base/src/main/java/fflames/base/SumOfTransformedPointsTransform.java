package fflames.base;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Calculates sum of transformed points.
 * 
 * Takes source point and transforms it by every assigned transform. 
 * Then calculates sum of all transformed points.
 * 
 * In order to build this transform use {@link Builder}.
 * 
 * @author pawel
 */
public class SumOfTransformedPointsTransform implements IPointTransform {

	private final Collection<Term> _terms = new ArrayList<>();
	
	/**
	 *
	 * @param terms
	 */
	private SumOfTransformedPointsTransform(Collection<Term> terms) {
		_terms.addAll(terms);
	}

	/**
	 * Transforms source point by applying all transforms to it and summing
	 * their results.
	 * 
	 * @param source point that will be transformed
	 * @param out result of the transformation
	 */
	@Override
	public void transform(Point2D source, Point2D out) {		
		Point2D transformResult = (Point2D) out.clone();
		
		for(Term t : _terms) {
			t.transform.transform(source, transformResult);
			out.setLocation(
					out.getX() + t.coefficient * transformResult.getX(),
					out.getY() + t.coefficient * transformResult.getY());
		}
	}

	/**
	 * Builds instances of {@link SumOfTransformedPointsTransform}.
	 * 
	 * In order to add terms call {@link addTerm} method.
	 * 
	 * When all terms were added call build to get new instance of
	 * {@link SumOfTransformedPointsTransform}.
	 */
	public static class Builder {

		private final Collection<Term> _terms = new ArrayList<>();
		
		public Builder() {}
		
		/**
		 * Adds term to combination.
		 * 
		 * @param coefficient
		 * @param transform
		 * @todo Problem: passing IPointTransform don't guarantee that the class
		 * will be immutable. In fact, there can be class which implements this
		 * interface and offers mutable methods.
		 */
		public void addTerm(double coefficient, IPointTransform transform) {
			Term t = new Term();
			t.coefficient = coefficient;
			t.transform = transform;
			_terms.add(t);
		}

		/**
		 * Builds object of {@link SumOfTransformedPointsTransform} consisting of 
		 * all added terms.
		 * 
		 * @return {@link SumOfTransformedPointsTransform} instance.
		 */
		public SumOfTransformedPointsTransform build() {
			return new SumOfTransformedPointsTransform(_terms);
		}

	}

	private static class Term {
		private double coefficient;
		private IPointTransform transform;
	}
}
