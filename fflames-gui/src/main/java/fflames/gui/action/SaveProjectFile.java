package fflames.gui.action;

import fflames.gui.ProjectExporter;
import fflames.gui.model.ApplicationState;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Action that saves project
 * 
 * @author Pawel Korus
 */
public class SaveProjectFile extends AbstractAction {
	private final ApplicationState _appState;
	private final Component _dialogsRoot;
	
	public SaveProjectFile(ApplicationState appState, Component dialogsRoot) {
		putValue(NAME, "Save");
		putValue(SHORT_DESCRIPTION, "Save fractal to file");
		
		_appState = appState;
		_dialogsRoot = dialogsRoot;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setApproveButtonText("Save");
		fileChooser.setCurrentDirectory(null);
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
		int returnValue = fileChooser.showSaveDialog(_dialogsRoot);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			
			try(FileOutputStream fileStream = new FileOutputStream(filePath)) {
				
				ProjectExporter exporter = new ProjectExporter(fileStream);
				exporter.begin();
				_appState.accept(exporter);
				exporter.end();
				
			} catch(IOException exception) {
				JOptionPane.showMessageDialog(_dialogsRoot, "Error when exporting to choosen file", "Export error", JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
		} else {
			return;
		}
	}
	
}
