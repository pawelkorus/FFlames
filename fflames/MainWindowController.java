package fflames;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import fflames.forms.MyFractals;
import fflames.interfaces.IColour;
import fflames.interfaces.IMainWindowController;
import fflames.model.Functions;

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
	public void loadFractalFile(File file, Functions functions) throws IOException {
		ImportXMLFractalFile importer = new ImportXMLFractalFile(functions);
		importer.load(file);
	}

	@Override
	public void saveFractalFile(File file, Functions functions) throws IOException {
		ExportXMLFileFractal exporter = new ExportXMLFileFractal(functions);
		exporter.save(file);
	}

	@Override
	public void draw(Functions functions, IColour coloringMethod, int numberOfIterations, BufferedImage image) {
		FractalGenerator fractalGenerator = new FractalGenerator(functions, coloringMethod, image);
		fractalGenerator.setNumberOfIterations(numberOfIterations);
		fractalGenerator.execute();
	}
}
