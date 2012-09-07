package fflames;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import fflames.exceptions.ImportXMLFractalFileException;
import fflames.forms.MyFractals;
import fflames.interfaces.IColour;
import fflames.interfaces.IMainWindowController;
import fflames.interfaces.IVariation;
import fflames.model.Transform;
import fflames.model.TransformTableModel;

public final class MainWindowController implements IMainWindowController {
	TransformTableModel _transformsModel;
	MyFractals _view;
	
	MainWindowController(TransformTableModel transformsModel, MyFractals view) {
    	_transformsModel = transformsModel;
    	_view = view;
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
	public void loadFractalFile(File file) {
		ArrayList<Transform> transforms = new ArrayList<Transform>();
		
		ImportXMLFractalFile importer = new ImportXMLFractalFile();
		try {
			importer.load(transforms, file);
		} catch (ImportXMLFractalFileException e) {
			transforms.clear();
			JOptionPane.showMessageDialog(_view, "Error occured when parsing choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			transforms.clear();
			JOptionPane.showMessageDialog(_view, "Error when reading from choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		_transformsModel.setTransforms(transforms);
	}

	@Override
	public void saveFractalFile(File file) {
		ExportXMLFileFractal exporter = new ExportXMLFileFractal(_transformsModel.getTransforms());
		
		try {
			exporter.save(file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(_view, "Error when exporting to choosen file", "Export error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void draw(IColour coloringMethod, int numberOfIterations, BufferedImage image) {
		FractalGenerator fractalGenerator = new FractalGenerator(_transformsModel.getTransforms(), coloringMethod, image);
		fractalGenerator.setNumberOfIterations(numberOfIterations);
		fractalGenerator.execute();
	}

	@Override
	public void addTransformation(double[] affineTransformParams, Vector<IVariation> variations, Double propability) {
		_transformsModel.add(new Transform(new AffineTransform(affineTransformParams), variations, propability));
	}

	@Override
	public void removeTransformation(int[] rows) {
		_transformsModel.remove(rows);
	}
}
