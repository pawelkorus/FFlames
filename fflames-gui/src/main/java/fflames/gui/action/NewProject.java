/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fflames.gui.action;

import fflames.gui.model.ApplicationState;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Action that creates new empty fractal project
 * 
 * @author Pawel Korus
 */
public class NewProject extends AbstractAction {
	private final ApplicationState _appState;
	
	public NewProject(ApplicationState appState) {
		putValue(NAME, "New");
		putValue(SHORT_DESCRIPTION, "Create new fractal project");
		
		_appState = appState;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		_appState.reset();
	}
	
}
