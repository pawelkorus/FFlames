package fflames.gui;

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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import prefs.Settings;

public final class Application implements Runnable {

	public Application() {
		_appState = new ApplicationState();
		_appState.addPropertyChangeListener(new ModelChangeListener());
		_recentOpenedModel = 
				new RecentOpenedModel(
						Settings.getInstance().getRecentOpenedPaths(), 10);
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
		
		_actions = new MainWindowActions(_mainFrame);

		_menuBar = new JMenuBar();
		_mainFrame.setJMenuBar(_menuBar);

		_mnFile = new JMenu("File");
		_menuBar.add(_mnFile);

		_mnFile.add(_actions.createNewFractalAction());

		JSeparator separator = new JSeparator();
		_mnFile.add(separator);

		_mnFile.add(_actions.createOpenAction(""));

		_mnOpenRecent = new JMenu("Open recent");
		for (int i = 0; i < _recentOpenedModel.getSize(); i++) {
			_mnOpenRecent.add(
					_actions.createOpenAction(
							_recentOpenedModel.getElementAt(i)));
		}
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
		_mnFile.add(_mnOpenRecent);

		JSeparator separator_1 = new JSeparator();
		_mnFile.add(separator_1);

		_mnFile.add(_actions.createSaveAction());

		_mnFile.add(_actions.createSaveImageAction());

		JSeparator separator_2 = new JSeparator();
		_mnFile.add(separator_2);

		_mnFile.add(_actions.createExitAction());

		_mnAbout = new JMenu("About");
		_menuBar.add(_mnAbout);

		_mnAbout.add(_actions.createShowAboutAction());
		
		_mainWindow = new MainWindow(_appState, _threadPool);
		_actions.addActionListener(_mainWindow);
		
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
	
	//private MyFractals _mainWindow;
	private MainWindow _mainWindow;
	private JFrame _mainFrame;
	private JMenuBar _menuBar;
	private JMenu _mnFile;
	private JMenu _mnOpenRecent;
	private JMenu _mnAbout;
	private MainWindowActions _actions;
	private ExecutorService _threadPool;
	private final ApplicationState _appState;
	private final RecentOpenedModel _recentOpenedModel;
}
