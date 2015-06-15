package fflames;

import fflames.forms.MyFractals;
import fflames.model.AlgorithmConfigurationModel;
import fflames.model.ApplicationState;
import fflames.model.TransformTableModel;

public final class Application implements Runnable {

	public Application() {
		
	}

	@Override
	public void run() {
		ApplicationState state = new ApplicationState();
		TransformTableModel transformsModel = new TransformTableModel();
		AlgorithmConfigurationModel algorithmConfigurationModel = new AlgorithmConfigurationModel();
		
		_mainWindow = new MyFractals(state);
		
		AlgorithmConfigurationEditorController algorithmConfigurationEditorController = new AlgorithmConfigurationEditorController(algorithmConfigurationModel);
		_mainWindow.getAlgorithmConfigurationEditor().setModel(algorithmConfigurationModel);
		_mainWindow.getAlgorithmConfigurationEditor().getActions().addPropertyChangeListener(algorithmConfigurationEditorController);
		
		MainWindowController mainWindowController = new MainWindowController(state, algorithmConfigurationModel, transformsModel, _mainWindow);
		mainWindowController.showMainWindow();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Application());
	}
	
	private MyFractals _mainWindow;
}
