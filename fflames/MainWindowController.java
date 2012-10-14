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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fflames.exceptions.ImportXMLFractalFileException;
import fflames.forms.AboutDialog;
import fflames.forms.AffineTransformEditor;
import fflames.forms.MyFractals;
import fflames.interfaces.IColour;
import fflames.interfaces.IVariation;
import fflames.model.AffineTransformModel;
import fflames.model.ColorsFactory;
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
		
		_view.getColoringEditor().addListSelectionListener(new ColoringMethodChangeListener());
		
		_view.addTransformsListSelectionListener(new TransformListSelectionListener());
		
		_view.addShowAboutInfoActionListener(new ShowAboutDialog());
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
			
			ColorsFactory colorsFactory = new ColorsFactory();
			IColour coloringMethod = colorsFactory.getColoring(_view.getColoringEditor().getSelectedIndex(), _view.getColoringEditor().getSelectedColors()); 
			
			//BufferedImage image = new BufferedImage(_view.getImageWidth(), _view.getImageHeight(), BufferedImage.TYPE_INT_ARGB);
			
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
	
	class ShowAboutDialog implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog dialog = new AboutDialog();
			dialog.setVisible(true);
		}
		
	}
}
