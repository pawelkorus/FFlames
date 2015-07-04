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
	private final int _index;
	
	public OpenRecentProjectFile(
			ApplicationState appState, 
			Component dialogsParent, 
			RecentOpenedModel recentOpenedModel) {
		super(appState, dialogsParent);
		_recentOpened = recentOpenedModel;
		_index = 0;
		
		putValue(NAME, "Open recent");
	}

	public OpenRecentProjectFile(
			ApplicationState appState,
			Component dialogsParent,
			RecentOpenedModel recentOpenedModel,
			int index ) {
		super(appState, dialogsParent);
		_recentOpened = recentOpenedModel;
		_index = index;
		
		putValue(NAME, _recentOpened.getElementAt(index));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setFilePath(_recentOpened.getElementAt(_index));
		super.actionPerformed(e);
	}
}
