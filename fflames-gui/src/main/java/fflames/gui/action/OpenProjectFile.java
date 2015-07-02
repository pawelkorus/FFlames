package fflames.gui.action;

import fflames.base.Transform;
import fflames.gui.ImportXMLFractalFile;
import fflames.gui.exceptions.ImportXMLFractalFileException;
import fflames.gui.model.ApplicationState;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Action that opens project file.
 * 
 * @author Pawel Korus
 */
public class OpenProjectFile extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final String FILE_PATH = "file_path";
	private final ApplicationState _appState;
	private final Component _dialogsParent;
	
	public OpenProjectFile(ApplicationState appState, Component dialogsParent){
		putValue(NAME, "Open");
		putValue(SHORT_DESCRIPTION, "Open project");
		putValue(LONG_DESCRIPTION, "Open fractal flame project file");
		
		_appState = appState;
		_dialogsParent = dialogsParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = getFilePath();
		
		if(filePath.isEmpty()) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setApproveButtonText("Open");
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
			int returnValue = fileChooser.showOpenDialog(_dialogsParent);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				filePath = fileChooser.getSelectedFile().getAbsolutePath();
			} else {
				return;
			}
		}

		_appState.reset();
		
		ArrayList<Transform> transforms = new ArrayList<>();
		ImportXMLFractalFile importer = new ImportXMLFractalFile();
		
		try {
			importer.load(transforms, filePath);
			
			transforms.stream().forEach((t) -> {
				_appState.getTransformsModel().add(t);
			});
			
			_appState.setParam(ApplicationState.LOADED_FRACTAL_FILE_PATH, filePath);
		
		} catch (ImportXMLFractalFileException exception) {
			JOptionPane.showMessageDialog(_dialogsParent, "Error occured when parsing choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_dialogsParent, "Error when reading from choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	protected String getFilePath() {
		String v = (String)getValue(FILE_PATH);
		if(v == null) {
			return "";
		} else {
			return v;
		}
	}
	
	protected void setFilePath(String filePath) {
		putValue(FILE_PATH, filePath);
	}
}
