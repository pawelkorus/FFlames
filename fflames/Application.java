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
		_mainWindow.setTransformTableModel(_transformsModel);
		
		_mainWindowController = new MainWindowController(_transformsModel, _mainWindow);
		
		_mainWindow.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Application());
	}
	
	private TransformTableModel _transformsModel;
	private MyFractals _mainWindow;
	private MainWindowController _mainWindowController;
}
