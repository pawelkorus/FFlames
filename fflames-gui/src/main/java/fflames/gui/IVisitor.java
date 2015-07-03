/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fflames.gui;

import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.ColorsModel;
import fflames.gui.model.RenderedImageModel;
import fflames.gui.model.TransformTableModel;

/**
 *
 * @author Pawel Korus
 */
public interface IVisitor {
	public void handle(AlgorithmConfigurationModel model);
	public void handle(TransformTableModel model);
	public void handle(ColorsModel model);
	public void handle(RenderedImageModel model);
	public void handle(ApplicationState model);
}
