package fflames.interfaces;

import java.io.File;
import java.io.IOException;

import fflames.Functions;

public interface IMainWindowController {
	boolean openProject(String fileName);
	boolean saveProject(String fileName);
	
	boolean loadFractalFile(File file, Functions functions) throws IOException;
	boolean saveFractalFile(File file, Functions functions);
}
