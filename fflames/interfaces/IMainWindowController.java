package fflames.interfaces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

public interface IMainWindowController {
	boolean openProject(String fileName);
	boolean saveProject(String fileName);
	
	void loadFractalFile(File file);
	void saveFractalFile(File file);
	
	void draw(IColour coloringMethod, int numberOfIterations, BufferedImage image);
	
	void addTransformation(double[] affineTransformParams, Vector<IVariation> variations, Double propability);
	void removeTransformation(int[] rows);
}
