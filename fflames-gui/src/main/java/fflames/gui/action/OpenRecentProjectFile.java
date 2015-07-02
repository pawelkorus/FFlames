package fflames.gui.action;

import fflames.gui.model.ApplicationState;
import fflames.gui.model.RecentOpenedModel;
import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * Action that reopens recent project file.
 * 
 * @author Pawel Korus
 */
public class OpenRecentProjectFile extends OpenProjectFile {
	private final RecentOpenedModel _recentOpened;
	
	public OpenRecentProjectFile(
			ApplicationState appState, 
			Component dialogsParent, 
			RecentOpenedModel recentOpenedModel) {
		super(appState, dialogsParent);
		_recentOpened = recentOpenedModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setFilePath(_recentOpened.getElementAt(0));
		super.actionPerformed(e);
	}
}
