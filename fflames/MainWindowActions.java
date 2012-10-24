package fflames;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import fflames.forms.AboutDialog;

public class MainWindowActions {
	private Component _parent;
	private ArrayList<MainWindowController> _controllers = new ArrayList<MainWindowController>();
	
	public MainWindowActions(Component parent) {
		_parent = parent;
	}
	
	public void addController(MainWindowController controller) {
		_controllers.add(controller);
	}
	
	public SaveImageAction createSaveImageAction() {
		return new SaveImageAction();
	}
	
	public SaveAction createSaveAction() {
		return new SaveAction();
	}
	
	public OpenAction createOpenAction(String name) {
		if(name.isEmpty()) {
			return new OpenAction();
		} else {
			return new OpenAction(name);
		}
	}
	
	public ExitAction createExitAction() {
		return new ExitAction();
	}
	
	public ShowAboutAction createShowAboutAction() {
		return new ShowAboutAction();
	}
	
	public DrawAction createDrawAction() {
		return new DrawAction();
	}
	
	public AddTransformAction createAddTransformAction() {
		return new AddTransformAction();
	}
	
	public RemoveTransformAction createRemoveTransformAction() {
		return new RemoveTransformAction();
	}
	
	private class SaveImageAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveImageAction() {
			putValue(NAME, "Save Image");
			putValue(SHORT_DESCRIPTION, "Save fractal to file");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setApproveButtonText("Save");
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG files", "png"));
			int returnValue = fileChooser.showSaveDialog(_parent);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				for(MainWindowController controller : _controllers) {
					controller.saveImageFile(fileChooser.getSelectedFile());
				}
			} else {
				return;
			}
		}	
	}
	
	private class SaveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveAction() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Save fractal to file");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setApproveButtonText("Save");
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
			int returnValue = fileChooser.showSaveDialog(_parent);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				for(MainWindowController controller : _controllers) {
					controller.saveFractalFile(fileChooser.getSelectedFile().getAbsolutePath());
				}
			} else {
				return;
			}
		}	
	}
	
	private class OpenAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public OpenAction() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Open fractal file");
			putValue(ACTION_COMMAND_KEY, "");
		}
		
		public OpenAction(String name) {
			putValue(NAME, name);
			putValue(SHORT_DESCRIPTION, "Open fractal from file");
			putValue(ACTION_COMMAND_KEY, name);
		}
		
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.isEmpty()) {
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setApproveButtonText("Open");
				fileChooser.setCurrentDirectory(null);
				fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
				int returnValue = fileChooser.showOpenDialog(_parent);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					command = fileChooser.getSelectedFile().getAbsolutePath();
				} else {
					return;
				}
			}
			
			for(MainWindowController controller : _controllers) {
				controller.loadFractalFile(command);
			}
		}
	}

	/**
	 * @todo wrong casting
	 * @author pawel
	 *
	 */
	private class ExitAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ExitAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			((Window) _parent).dispose();
		}
		
	}

	private class ShowAboutAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ShowAboutAction() {
			putValue(NAME, "About FFlames");
			putValue(SHORT_DESCRIPTION, "About FFlames");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog dialog = new AboutDialog();
			dialog.setVisible(true);
		}
	}
	
	private class AddTransformAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public AddTransformAction() {
			putValue(NAME, "Add transform");
			putValue(SHORT_DESCRIPTION, "Add transform to the algorithm");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(MainWindowController controller : _controllers) {
				controller.addTransform();
			}
		}
	}
	
	private class RemoveTransformAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public RemoveTransformAction() {
			putValue(NAME, "Remove transform");
			putValue(SHORT_DESCRIPTION, "Remove transform from the algorithm");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(MainWindowController controller : _controllers) {
				controller.removeTransform();
			}
		}
	}
	
	private class DrawAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public DrawAction() {
			putValue(NAME, "Draw");
			putValue(SHORT_DESCRIPTION, "Draw current fractal");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(MainWindowController controller : _controllers) {
				controller.drawFractal();
			}
		}
		
	}
}
