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
	private ArrayList<Transform> _transforms;
	private ArrayList<Color> _colors;
	private int _coloringId;
	private int _width;
	private int _height;
	private int _iterationsNumber;
	private int _superSampling;
	private int _rotationsNumber;
	private IProgressModel _progress;
	private IRenderedImageModel _renderedImageModel;
	private ExecutorService _threadPool;
	
	public DrawingWorker(ApplicationState _state, ExecutorService threadPool) {
		_transforms = new ArrayList<>();
		_progress = new NullProgress();
		_renderedImageModel = new NullRenderedImageModel();
		
		_threadPool = threadPool;
		
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
		int[] size = { 
			_width,
			_height
		};

		ColoringFactory colorsFactory = new ColoringFactory();
		IColoring coloring = colorsFactory.getColoring(_coloringId, _colors);
		
		FractalGenerator.Builder builder = new FractalGenerator.Builder(
				_threadPool);
		builder.numberOfIterations(_iterationsNumber);
		builder.numberOfRotations(_rotationsNumber);
		builder.samples(_superSampling);
		builder.width(_width);
		builder.height(_width);
		builder.coloringMethod(coloring);
		for(Transform t : _transforms) {
			builder.addTransform(t);
		}
		
		FractalGenerator fractalGenerator = builder.build();
		
		long startTime = System.nanoTime();

		int p = 0;
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
		private ModelVisitor() {
		}
		
		@Override
		public void handle(AlgorithmConfigurationModel model) {
			_width = model.getImageWidth();
			_height = model.getImageHeight();
			_iterationsNumber = model.getIterationsNumber();
			_rotationsNumber = model.getRotationsNumber();
			_superSampling = model.getSuperSampling();
		}

		@Override
		public void handle(TransformTableModel model) {
			_transforms.addAll(model.getTransforms());
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
			
			_coloringId = 0;
			if(selectedColoringId != IndexValue.InvalidValue) {
				_coloringId = selectedColoringId.toInt();
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
