package fflames.gui;

import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.ColorsModel;
import fflames.gui.model.RenderedImageModel;
import fflames.gui.model.TransformTableModel;

/**
 * Interface for classes that want to go over models representing
 * state of the application.
 * 
 * @author Pawel Korus
 */
public interface IModelVisitor {
	public void handle(AlgorithmConfigurationModel model);
	public void handle(TransformTableModel model);
	public void handle(ColorsModel model);
	public void handle(RenderedImageModel model);
	public void handle(ApplicationState model);
}
