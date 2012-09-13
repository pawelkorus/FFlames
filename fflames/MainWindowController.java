package fflames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import fflames.exceptions.ImportXMLFractalFileException;
import fflames.forms.MyFractals;
import fflames.interfaces.IColour;
import fflames.interfaces.IVariation;
import fflames.model.Transform;
import fflames.model.TransformTableModel;

public final class MainWindowController {
	TransformTableModel _transformsModel;
	MyFractals _view;
	JFileChooser _xmlFileChooser;
	JFileChooser _imageFileChooser;
	
	MainWindowController(TransformTableModel transformsModel, MyFractals view) {
    	_transformsModel = transformsModel;
    	_view = view;
    	
    	_xmlFileChooser = new JFileChooser();
    	_xmlFileChooser.setApproveButtonText("Open");
		_xmlFileChooser.setCurrentDirectory(null);
		_xmlFileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
		
		_imageFileChooser = new JFileChooser();
		_imageFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
		_imageFileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG files", "png"));
		
		_view.addLoadFractalFileXmlActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFractalFile();
				
			}	
		});
		_view.addSaveFractalFileXmlActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFractalFile();
			}
		});
		_view.addFunctionActionListener(new AddFunctionListener());
		_view.addRemoveActionListener(new RemoveFunctionListener());
		_view.addDrawActionListener(new DrawImageListener());
		_view.addSaveImageActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveImageFile();
				
			}
			
		});	
	}

	public void loadFractalFile() {
		XmlLoadFileListener listener = new XmlLoadFileListener();
		_xmlFileChooser.addActionListener(listener);
		_xmlFileChooser.showOpenDialog(_view);
		_xmlFileChooser.removeActionListener(listener);
	}

	public void saveFractalFile() {
		XmlSaveFileListener listener = new XmlSaveFileListener();
		_xmlFileChooser.addActionListener(listener);
		_xmlFileChooser.showSaveDialog(_view);
		_xmlFileChooser.removeActionListener(listener);
	}
	
	public void saveImageFile() {
		SaveImageListener listener = new SaveImageListener();
		_imageFileChooser.addActionListener(listener);
		_imageFileChooser.showSaveDialog(_view);
		_imageFileChooser.removeActionListener(listener);
	}
	
	class SaveImageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == JFileChooser.APPROVE_SELECTION) {
				JFileChooser fileChooser = (JFileChooser) e.getSource();
				File file = fileChooser.getSelectedFile();		
				
				try {
					ImageIO.write((RenderedImage) _view.getRysunekJPanel().getImage(), "png", file);
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(_view, "Error when saving image file", "Export error", JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		}
		
	}
	
	class DrawImageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer numberOfIterations = _view.getIterationsNumber();
			IColour coloringMethod = _view.getColoringMethod();
			
			BufferedImage image = new BufferedImage(_view.getWidth(), _view.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			FractalGenerator fractalGenerator = new FractalGenerator(_transformsModel.getTransforms(), coloringMethod, image);
			fractalGenerator.setNumberOfIterations(numberOfIterations);
			fractalGenerator.execute();
			
			_view.getRysunekJPanel().resetPoints();
			_view.getRysunekJPanel().setImage(image);
		}
		
	}

	class AddFunctionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			double[] affineTransformParams = _view.getAffineTransformEditor().getValues();
			Vector<IVariation> variations = _view.getVariations();
			Double propability = _view.getFunctionPropability();
			_transformsModel.add(new Transform(new AffineTransform(affineTransformParams), variations, propability));
		}
		
	}
	
	class RemoveFunctionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] rows = _view.getSelectedFunctions();
			_transformsModel.remove(rows);
		}
		
	}
	
	class XmlSaveFileListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == JFileChooser.APPROVE_SELECTION) {
				JFileChooser fileChooser = (JFileChooser) e.getSource();
				File file = fileChooser.getSelectedFile();		
				
				ExportXMLFileFractal exporter = new ExportXMLFileFractal(_transformsModel.getTransforms());
				
				try {
					exporter.save(file);
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(_view, "Error when exporting to choosen file", "Export error", JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
		}
		
	}
	
	class XmlLoadFileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == JFileChooser.APPROVE_SELECTION) {
				JFileChooser fileChooser = (JFileChooser) e.getSource();
				File file = fileChooser.getSelectedFile();
				
				ArrayList<Transform> transforms = new ArrayList<Transform>();
				
				ImportXMLFractalFile importer = new ImportXMLFractalFile();
				try {
					importer.load(transforms, file);
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
		}
		
	}
}
