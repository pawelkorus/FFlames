package fflames.gui;

import fflames.base.IVariation;
import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.ColorsModel;
import fflames.gui.model.RenderedImageModel;
import fflames.gui.model.TransformTableModel;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Project state exporter. Project is exported into XML format.
 * 
 * @author Pawel Korus
 */
public class ProjectExporter implements IModelVisitor {
	private final OutputStream _out;
	private boolean _exportSucceeded;
	
	public ProjectExporter(OutputStream out) {
		_exportSucceeded = false;
		_out = out;
	}
	
	public void begin() {
		_exportSucceeded = false;
		
		try {
			write(_out, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			write(_out, "<Fractal>\r\n");
			write(_out, "<Functions>\r\n");
		} catch(IOException e) {
			_exportSucceeded = false;
		}
	}
	
	public void end() throws IOException {
		boolean _exceptionCaught = false;
		
		try {
			write(_out, "</Functions>\r\n");
			write(_out, "</Fractal>\r\n");
			_out.flush();
		} catch(IOException e) {
			_exceptionCaught = true;
		}
		
		_exportSucceeded = _exceptionCaught == false;
	}
	
	public boolean succeeded() {
		return _exportSucceeded;
	}
	
	@Override
	public void handle(AlgorithmConfigurationModel model) {
		// do nothing at the moment
	}

	@Override
	public void handle(TransformTableModel model) {
		try {
			for(int i = 0; i < model.getRowCount(); i++) {
				write(_out, "<Function>\r\n");
				
				double probability = model.getPropabilityAt(i);
				write(_out, "<Propability>" + probability + "</Propability>\r\n");
				
				writeAffineTransform(_out, model.getAffineTransformAt(i));

				writeVariations(_out, model.getVariationsAt(i));
	
				write(_out, "</Function>\r\n");
			}
		} catch(IOException e) {
			_exportSucceeded = false;
		}
	}

	@Override
	public void handle(ColorsModel models) {
		// do nothing at the moment
	}

	@Override
	public void handle(ApplicationState model) {
		// do nothing at the moment
	}
	
	@Override
	public void handle(RenderedImageModel model) {
		// do nothing at the moment
	}
	
	private void write(OutputStream o, String data) throws IOException {
		o.write(data.getBytes("UTF-8"));
	}
	
	private void writeAffineTransform(OutputStream out,
			AffineTransform affineTransform)
			throws IOException
	{
		double[] temp = new double[6];
		affineTransform.getMatrix(temp);
		write(out, "<AffineTransform>\r\n");
		for (int i = 0; i < temp.length; i++) {
			write(out, "<Wsp>" + temp[i] + "</Wsp>\r\n");
		}
		write(out, "</AffineTransform>\r\n");
	}
	
	private void writeVariations(OutputStream out, 
			Collection<IVariation> variations)
			throws IOException
	{
		write(_out, "<Wariations>\r\n");
		
		for (IVariation variation : variations) {
			write(_out, "<Wariation>\r\n");

			Double coefficient = variation.getCoefficient();
			write(_out, "<Coefficient>" + coefficient + "</Coefficient>\r\n");

			List<Double> parameters = variation.getParameters();
			for(Double par : parameters) {
				write(_out, "<Par>" + par + "</Par>\r\n");
			}

			write(_out, "<Name>" + variation.getName() + "</Name>\r\n");
			write(_out, "</Wariation>\r\n");
		}

		write(_out, "</Wariations>\r\n");
	}
}
