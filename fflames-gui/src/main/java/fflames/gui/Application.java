package fflames.gui;

import fflames.gui.action.ExitApplication;
import fflames.gui.action.NewProject;
import fflames.gui.action.OpenProjectFile;
import fflames.gui.action.OpenRecentProjectFile;
import fflames.gui.action.SaveFractalImage;
import fflames.gui.action.SaveProjectFile;
import fflames.gui.action.ShowAboutDialog;
import fflames.gui.forms.MainWindow;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.RecentOpenedModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.ActionMap;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import prefs.Settings;

public final class Application implements Runnable {
	private MainWindow _mainWindow;
	private JFrame _mainFrame;
	private JMenuBar _menuBar;
	private JMenu _mnFile;
	private JMenu _mnOpenRecent;
	private JMenu _mnAbout;
	private ExecutorService _threadPool;
	private final ApplicationState _appState;
	private final RecentOpenedModel _recentOpenedModel;
	private final ActionMap _actions;
	
	public Application() {
		_appState = new ApplicationState();
		_appState.addPropertyChangeListener(new ModelChangeListener());
		_recentOpenedModel = 
				new RecentOpenedModel(
						Settings.getInstance().getRecentOpenedPaths(), 10);
		_actions = new ActionMap();
	}

	@Override
	public void run() {
		UIManager.getDefaults().put("FFlamesMainWidnowUI", "fflames.gui.ui.BasicMainWindowUI");
		
		_threadPool = Executors.newFixedThreadPool(6);
		
		_mainFrame = new JFrame();
		_mainFrame.setTitle(_appState.getApplicationName());
		_mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_mainFrame.setMinimumSize(new java.awt.Dimension(800, 600));
		_mainFrame.setName("mainFrame"); // NOI18N
		
		_actions.put(Actions.Id.OpenProjectFile, new OpenProjectFile(_appState, _mainFrame));
		_actions.put(Actions.Id.ShowAboutDialog, new ShowAboutDialog());
		_actions.put(Actions.Id.OpenRecentProjectFile, 
				new OpenRecentProjectFile(_appState, _mainFrame, _recentOpenedModel));
		_actions.put(Actions.Id.SaveProjectFile, new SaveProjectFile(_appState, _mainFrame));
		_actions.put(Actions.Id.ExitApplication, new ExitApplication(_mainFrame));
		_actions.put(Actions.Id.NewProject, new NewProject(_appState));
		_actions.put(Actions.Id.SaveFractalImage,
				new SaveFractalImage(_appState.getRenderedImageModel(), _mainWindow));

		_menuBar = new JMenuBar();
		_mainFrame.setJMenuBar(_menuBar);

		_mnFile = new JMenu("File");
		_menuBar.add(_mnFile);

		_mnFile.add(_actions.get(Actions.Id.NewProject));

		JSeparator separator = new JSeparator();
		_mnFile.add(separator);

		_mnFile.add(_actions.get(Actions.Id.OpenProjectFile));

		/*_mnOpenRecent = new JMenu("Open recent");
		_mnOpenRecent.add(_actions.getAction(MainWindowActions.ActionId.OpenRecentProjectFile));
		
		_recentOpenedModel.addListDataListener(new ListDataListener() {

			@Override
			public void intervalAdded(ListDataEvent e) {
				for (int i = e.getIndex0(); i <= e.getIndex1(); i++) {
					_mnOpenRecent.add(
							new JMenuItem(
									_actions.createOpenAction(
											_recentOpenedModel.getElementAt(i))), i);
				}
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				for (int i = e.getIndex0(); i <= e.getIndex1(); i++) {
					_mnOpenRecent.remove(e.getIndex0());
				}
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
			}

		});
		_mnFile.add(_mnOpenRecent);*/

		JSeparator separator_1 = new JSeparator();
		_mnFile.add(separator_1);

		_mnFile.add(_actions.get(Actions.Id.SaveProjectFile));

		_mnFile.add(_actions.get(Actions.Id.SaveFractalImage));

		JSeparator separator_2 = new JSeparator();
		_mnFile.add(separator_2);

		_mnFile.add(_actions.get(Actions.Id.ExitApplication));

		_mnAbout = new JMenu("About");
		_menuBar.add(_mnAbout);

		_mnAbout.add(_actions.get(Actions.Id.ShowAboutDialog));
		
		_mainWindow = new MainWindow(_appState, _threadPool, _actions);
		/*_mainWindow.setAction(MainWindow.ActionId.NewProject,
				_actions.get(ActionId.NewProject));
		_mainWindow.setAction(MainWindow.ActionId.ExitApplication,
				_actions.get(ActionId.ExitApplication));
		_mainWindow.setAction(MainWindow.ActionId.OpenProjectFile,
				_actions.get(ActionId.OpenProjectFile));
		_mainWindow.setAction(MainWindow.ActionId.OpenRecentProjectFile,
				_actions.get(ActionId.OpenRecentProjectFile));
		_mainWindow.setAction(MainWindow.ActionId.SaveFractalImage,
				_actions.get(ActionId.SaveFractalImage));
		_mainWindow.setAction(MainWindow.ActionId.SaveProjectFile,
				_actions.get(ActionId.SaveProjectFile));*/
		
		_mainFrame.add(_mainWindow);
		
		_mainFrame.pack();
		
		_mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				try {
					_threadPool.shutdown();
					_threadPool.awaitTermination(2, TimeUnit.SECONDS);
				} catch(InterruptedException e) {

				} finally {
					if(!_threadPool.isTerminated()) {
						_threadPool.shutdownNow();
					}
				}
			}
		});
		
		_mainFrame.setVisible(true);
	}
	
	private class ModelChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			switch(evt.getPropertyName()) {
				case ApplicationState.LOADED_FRACTAL_FILE_PATH:
					if(_appState.isFractalFileLoaded()) {
						String filePath = _appState.getLoadedFractalFilePath();
						if(filePath.isEmpty()) break;
						
						_recentOpenedModel.add(_appState.getLoadedFractalFilePath());
					}
				
					break;
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Application());
	}
}
