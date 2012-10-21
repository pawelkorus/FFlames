package fflames;

import fflames.forms.MyFractals;
import fflames.model.TransformTableModel;

public final class Application implements Runnable {

	public Application() {
		
	}

	@Override
	public void run() {
		_transformsModel = new TransformTableModel();
		
		_mainWindow = new MyFractals();
		
		MainWindowController _mainWindowController = new MainWindowController(_transformsModel, _mainWindow);
		_mainWindowController.showMainWindow();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Application());
	}
	
	private TransformTableModel _transformsModel;
	private MyFractals _mainWindow;
	@SuppressWarnings("unused")
	private MainWindowController _mainWindowController;
}
