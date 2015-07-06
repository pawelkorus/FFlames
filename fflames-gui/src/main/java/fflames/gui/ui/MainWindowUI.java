package fflames.gui.ui;

import javax.swing.plaf.ComponentUI;

/**
 * UI for Main window
 * 
 * @author Pawel Korus
 */
public abstract class MainWindowUI extends ComponentUI {
	
	public abstract Double getFunctionPropability();
	
	public abstract void setFunctionPropability(Double v);
	
}
