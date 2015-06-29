package fflames.gui;

import fflames.gui.forms.MyFractals;
import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.TransformTableModel;

public final class Application implements Runnable {

	public Application() {
		
	}

	@Override
	public void run() {
		ApplicationState state = new ApplicationState();
		TransformTableModel transformsModel = new TransformTableModel();
		AlgorithmConfigurationModel algorithmConfigurationModel = new AlgorithmConfigurationModel();
		
		_mainWindow = new MyFractals(state);
		
		_mainWindow.getAlgorithmConfigurationEditor().setModel(algorithmConfigurationModel);
		
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
