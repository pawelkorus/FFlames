package fflames.gui;

/**
 * Host interface for visitors
 * 
 * @author Pawel Korus
 */
public interface IVisitableModel {

	/**
	 * Hosts visitor
	 * @param visitor
	 */
	public void accept(IModelVisitor visitor);
}
