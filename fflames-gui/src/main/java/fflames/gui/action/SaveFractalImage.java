/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fflames.gui.action;

import fflames.gui.model.IRenderedImageModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Action that saves generated fractal flame as image
 * 
 * @author Pawel Korus
 */
public class SaveFractalImage extends AbstractAction {
	private final IRenderedImageModel _renderedImageModel;
	private final Component _dialogsRoot;
	
	public SaveFractalImage(IRenderedImageModel renderedImageModel, 
			Component dialogsRoot) {
		putValue(NAME, "Save Image");
		putValue(SHORT_DESCRIPTION, "Save fractal to file");
		
		_renderedImageModel = renderedImageModel;
		_dialogsRoot = dialogsRoot;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setApproveButtonText("Save");
		fileChooser.setCurrentDirectory(null);
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG files", "png"));
		int returnValue = fileChooser.showSaveDialog(_dialogsRoot);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			try {
				ImageIO.write((RenderedImage) _renderedImageModel.getImage(), "png", file);
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(_dialogsRoot, "Error when saving image file", "Export error", JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
		}
	}
	
}
