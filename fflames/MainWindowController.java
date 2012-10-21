package fflames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import prefs.Settings;

import fflames.exceptions.ImportXMLFractalFileException;
import fflames.forms.AffineTransformEditor;
import fflames.forms.MyFractals;
import fflames.interfaces.IColour;
import fflames.interfaces.IVariation;
import fflames.model.AffineTransformModel;
import fflames.model.ColorsFactory;
import fflames.model.RecentOpenedModel;
import fflames.model.Transform;
import fflames.model.TransformTableModel;

public final class MainWindowController {
	TransformTableModel _transformsModel;
	RecentOpenedModel _recentOpenedModel;
	MyFractals _view;
	
	MainWindowController(TransformTableModel transformsModel, MyFractals view) {
    	_transformsModel = transformsModel;
    	_recentOpenedModel = new RecentOpenedModel(Settings.getInstance().getRecentOpenedPaths(), 10);
    	
    	_view = view;
    	_view.setRecentOpened(_recentOpenedModel);
    	_view.setController(this);
		
		_view.addFunctionActionListener(new AddFunctionListener());
		_view.addRemoveActionListener(new RemoveFunctionListener());
		_view.addDrawActionListener(new DrawImageListener());
		
		_view.getColoringEditor().addListSelectionListener(new ColoringMethodChangeListener());
		
		_view.addTransformsListSelectionListener(new TransformListSelectionListener());
	}

	public void loadFractalFile(String filePath) {
		ArrayList<Transform> transforms = new ArrayList<Transform>();
		
		ImportXMLFractalFile importer = new ImportXMLFractalFile();
		try {
			importer.load(transforms, filePath);
			_recentOpenedModel.add(filePath);
		} catch (ImportXMLFractalFileException exception) {
			transforms.clear();
			JOptionPane.showMessageDialog(_view, "Error occured when parsing choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		} catch (IOException exception) {
			transforms.clear();
			JOptionPane.showMessageDialog(_view, "Error when reading from choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
		
		_transformsModel.setTransforms(transforms);
	}
	
	public void saveFractalFile(String filePath) {
		ExportXMLFileFractal exporter = new ExportXMLFileFractal(_transformsModel.getTransforms());
		
		try {
			exporter.save(filePath);
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_view, "Error when exporting to choosen file", "Export error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	public void saveImageFile(File file) {
		try {
			ImageIO.write((RenderedImage) _view.getRysunekJPanel().getImage(), "png", file);
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_view, "Error when saving image file", "Export error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	class ColoringMethodChangeListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) {
				int selectedIndex = _view.getColoringEditor().getSelectedIndex();
				ColorsFactory factory = new ColorsFactory();
				int buttonsNumber = factory.getColoring(selectedIndex, null).getParametersQuantity();
				if(buttonsNumber == 1) {
					_view.getColoringEditor().setNumberOfColorSlots(1);	
				} else if(buttonsNumber == 2) {
					_view.getColoringEditor().setNumberOfColorSlots(_transformsModel.getRowCount());
				} else {
					_view.getColoringEditor().setNumberOfColorSlots(0);
				}
			}
		}
	}
	
	class DrawImageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer numberOfIterations = _view.getIterationsNumber();
			
			ColorsFactory colorsFactory = new ColorsFactory();
			IColour coloringMethod = colorsFactory.getColoring(_view.getColoringEditor().getSelectedIndex(), _view.getColoringEditor().getSelectedColors()); 
			
			FractalGenerator fractalGenerator = new FractalGenerator(_transformsModel.getTransforms(), coloringMethod, _view.getImageWidth(), _view.getImageHeight());
			fractalGenerator.setNumberOfIterations(numberOfIterations);
			fractalGenerator.execute();
			
			_view.getRysunekJPanel().resetPoints();
			_view.getRysunekJPanel().setImage(fractalGenerator.getOutput());
		}
		
	}

	class AddFunctionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AffineTransformModel affineTransformModel = _view.getAffineTransformEditor().getModel();
			Vector<IVariation> variations = _view.getVariations();
			Double propability = _view.getFunctionPropability();
			_transformsModel.add(new Transform(affineTransformModel.getTransform(), variations, propability));
		}
		
	}
	
	class RemoveFunctionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIndex = _view.getSelectedTransform();
			_transformsModel.remove(selectedIndex);
		}
		
	}
	
	class TransformListSelectionListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			int selectedTransform = _view.getSelectedTransform();
			_view.setFunctionPropability((Double) _transformsModel.getValueAt(selectedTransform, 0));
			AffineTransform transform = (AffineTransform) _transformsModel.getAffineTransformAt(selectedTransform);
			AffineTransformEditor affineTransformEditor = _view.getAffineTransformEditor();
			affineTransformEditor.getModel().setTransform(transform);
			_view.getVariationsEditor().setVariations(_transformsModel.getVariationsAt(selectedTransform));
		}
		
	}
}
