package fflames.generator;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Variation interface. Classes implementing this interface provides
 * are used to transform point coordinates.
 */
public interface IVariation {

	/**
	 * Returns coefficient value
	 *
	 * @return coefficient value
	 */
	public Double getCoefficient();

	/**
	 * Sets variation coefficient
	 *
	 * @param _coefficient new coefficient value
	 */
	public void setCoefficient(Double _coefficient);

	/**
	 * Calculates new coordinates for the given point. It doesn't change given 
	 * object. It returns new instance of Point2D object.
	 *
	 * @param point source point
	 * @return new Point2D instance containing calculated coordinates
	 */
	public Point2D calculate(Point2D point);

	/**
	 * Returns number of additional parameters.
	 *
	 * @return number of additional parameters.
	 */
	public int getParametersQuantity();

	/**
	 * Set variation parameters
	 *
	 * @param parameters collection containing parameters
	 */
	public void setParameters(List<Double> parameters);

	/**
	 * Returns list of additional parameters.
	 *
	 * @return List containing additional parameters
	 */
	public List<Double> getParameters();

	/**
	 * Returns the name of variation
	 *
	 * @return String
	 */
	public String getName();

	/**
	 * Checks if variation depends on affine transform coefficients.
	 * 
	 * @return true if variation depends on affine transform coefficients.
	 *				Otherwise false.
	 */
	public boolean isDependent();

	/**
	 * Checks validity of the variation
	 *
	 * @return true if variation is valid. Otherwise false.
	 */
	public boolean isValid();
}
