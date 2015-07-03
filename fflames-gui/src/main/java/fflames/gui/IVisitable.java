/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fflames.gui;

/**
 * Host interface for visitors
 * 
 * @author Pawel Korus
 */
public interface IVisitable {

	/**
	 * Hosts visitor
	 * @param visitor
	 */
	public void accept(IVisitor visitor);
}
