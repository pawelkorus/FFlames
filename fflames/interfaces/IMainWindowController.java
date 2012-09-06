package fflames.interfaces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import fflames.model.Functions;

public interface IMainWindowController {
	boolean openProject(String fileName);
	boolean saveProject(String fileName);
	
	void loadFractalFile(File file, Functions functions) throws IOException;
	void saveFractalFile(File file, Functions functions) throws IOException;
	
	void draw(Functions functions, IColour coloringMethod, int numberOfIterations, BufferedImage image);
}
