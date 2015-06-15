package fflames.interfaces;

import java.io.File;

public interface IMainWindowController {

	public abstract void loadFractalFile(String filePath);

	public abstract void saveFractalFile();

	public abstract void saveFractalFile(String filePath);

	public abstract void saveImageFile(File file);

	public abstract void drawFractal();

	public abstract void addTransform();

	public abstract void removeTransform();

	public abstract void newFractal();

}