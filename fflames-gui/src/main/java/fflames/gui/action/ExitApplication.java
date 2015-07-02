package fflames.gui.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 * Action that tries to close the application
 *
 * @author Pawel Korus
 */
public class ExitApplication extends AbstractAction {
	public final JFrame _mainFrame;
	
	public ExitApplication(JFrame mainFrame) {
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exits application");
		
		_mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		_mainFrame.dispose();
	}
	
}
