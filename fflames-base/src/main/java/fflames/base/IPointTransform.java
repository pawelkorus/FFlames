package fflames.base;

import java.awt.geom.Point2D;

/**
 * Transforms point coordinates.
 * 
 * @author Pawel Korus
 */
public interface IPointTransform {
	/**
	 * Transforms source point coordinations and stores them in out instance.
	 * Source point coordinates are not changed.
	 * 
	 * @param source point coordinates to be transformed
	 * @param out stores transformed coordinates
	 */
	public void transform(Point2D source, Point2D out);
}
