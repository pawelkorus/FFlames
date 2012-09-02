package fflames;
import java.io.File;
import java.io.IOException;

import fflames.forms.MyFractals;
import fflames.interfaces.IMainWindowController;

public final class MainWindowController implements IMainWindowController {
	
	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyFractals(new MainWindowController()).setVisible(true);
            }
        });
    }

	@Override
	public boolean openProject(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveProject(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadFractalFile(File file, Functions functions) throws IOException {
		ImportXMLFractalFile importer = new ImportXMLFractalFile(functions);
		return importer.load(file);
	}

	@Override
	public boolean saveFractalFile(File file, Functions functions) {
		// TODO Auto-generated method stub
		return false;
	}
}
