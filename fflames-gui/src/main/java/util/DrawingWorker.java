package util;

import fflames.base.FractalGenerator;
import fflames.base.IColoring;
import fflames.base.Transform;
import fflames.base.coloring.ColoringFactory;
import fflames.gui.IModelVisitor;
import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.ColorsModel;
import fflames.gui.model.IProgressModel;
import fflames.gui.model.IRenderedImageModel;
import fflames.gui.model.IndexValue;
import fflames.gui.model.RenderedImageModel;
import fflames.gui.model.TransformTableModel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.SwingWorker;

/**
 *
 * @author Pawel Korus
 */
public class DrawingWorker extends SwingWorker<BufferedImage, Integer> {
	private IProgressModel _progress;
	private IRenderedImageModel _renderedImageModel;
	private final FractalGenerator.Builder _generatorBuilder;
	private int _iterationsNumber = 0;
	
	public DrawingWorker(ApplicationState _state, ExecutorService threadPool) {
		_generatorBuilder = new FractalGenerator.Builder(threadPool);
		
		_progress = new NullProgress();
		_renderedImageModel = new NullRenderedImageModel();
		
		ModelVisitor visitor = new ModelVisitor();
		_state.accept(visitor);
	}
	
	public void setProgressModel(IProgressModel progressModel) {
		_progress = progressModel;
	}
	
	public void setRenderedImageModel(IRenderedImageModel renderedImage) {
		_renderedImageModel = renderedImage;
	}
	
	@Override
	protected BufferedImage doInBackground() {		
		FractalGenerator fractalGenerator = _generatorBuilder.build();
		
		long startTime = System.nanoTime();

		BufferedImage out = new BufferedImage(1, 1, 1);

		_progress.setStartProgressValue(1);
		_progress.setEndProgressValue(_iterationsNumber);
		_progress.reset();

		try {
			Future<BufferedImage> f = fractalGenerator.execute();
			while(!f.isDone()) {
				try {
					f.get(10, TimeUnit.MILLISECONDS);
				} catch(TimeoutException e) {
					_progress.setProgress(fractalGenerator.getProgress());
				}
			}
			_progress.setProgress(100);
			out = f.get();
		} catch(InterruptedException | ExecutionException e) {

		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000; // miliseconds
		System.out.println("Generator execution time: " + duration );

		return out;
	}

	@Override
	protected void done() { 
		// this method is executed on the Dispatch Event thread
		try {
			BufferedImage result = get();

			_renderedImageModel.setImage(result);
		} catch(InterruptedException | ExecutionException e) {

		}
	}
	
	private class ModelVisitor implements IModelVisitor {
		private final ArrayList<Color> _colors = new ArrayList<>();
		
		private ModelVisitor() {
		}
		
		@Override
		public void handle(AlgorithmConfigurationModel model) {
			_generatorBuilder
			.width(model.getImageWidth())
			.height(model.getImageHeight())
			.numberOfIterations(model.getIterationsNumber())
			.numberOfRotations(model.getRotationsNumber())
			.samples(model.getSuperSampling());
			
			_iterationsNumber = model.getIterationsNumber();
		}

		@Override
		public void handle(TransformTableModel model) {
			for(int i = 0; i < model.getRowCount(); i++) {
				Transform t = new Transform(model.getAffineTransformAt(i),
					model.getVariationsAt(i));
				
				_generatorBuilder
				.addTransform(model.getPropabilityAt(i), t);
			}
		}

		@Override
		public void handle(ColorsModel model) {
			for(int i = 0; i < model.getSize(); i++) {
				float[] components = model.getElementAt(i);
				_colors.add(new Color(
						components[0],
						components[1],
						components[2]
				));
			}
		}

		@Override
		public void handle(RenderedImageModel model) {
			// do nothing for this model
		}

		@Override
		public void handle(ApplicationState model) {
			IndexValue selectedColoringId = model.getSelectedColoringIndex();
			
			int _coloringId = 0;
			if(selectedColoringId != IndexValue.InvalidValue) {
				_coloringId = selectedColoringId.toInt();
				
				ColoringFactory colorsFactory = new ColoringFactory();
				IColoring coloring = colorsFactory.getColoring(_coloringId, _colors);
				
				_generatorBuilder.coloringMethod(coloring);
			}
		}
	}
	
	private class NullProgress implements IProgressModel {

		@Override
		public int getStartProgressValue() { 
			// nothing to be done
			return 0; 
		}

		@Override
		public void setStartProgressValue(int value) {
			// nothing to be done
		}

		@Override
		public int getEndProgressValue() {
			// nothing to be done
			return 0;
		}

		@Override
		public void setEndProgressValue(int value) {
			// nothing to be done
		}

		@Override
		public int getProgress() {
			// nothing to be done
			return 0;
		}

		@Override
		public void setProgress(int value) {
			// nothing to be done
		}

		@Override
		public void reset() {
			// nothing to be done
		}
		
	}
	private class NullRenderedImageModel implements IRenderedImageModel {

		@Override
		public BufferedImage getImage() {
			// nothing to be done
			return null;
		}

		@Override
		public void setImage(BufferedImage image) {
			// nothing to be done
		}
		
	}
}
