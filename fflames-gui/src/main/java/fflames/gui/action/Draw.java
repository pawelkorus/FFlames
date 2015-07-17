package fflames.gui.action;

import fflames.gui.model.ApplicationState;
import fflames.gui.model.IProgressModel;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import javax.swing.AbstractAction;
import util.DrawingWorker;

/**
 *
 * @author Pawel Korus
 */
public class Draw extends AbstractAction {
	private final ApplicationState _state;
	private final ExecutorService _threadPool;
	private final IProgressModel _progress;
	
	public Draw(ApplicationState state, 
			ExecutorService threadPool, IProgressModel progress) {
		putValue(NAME, "Draw");
		putValue(SHORT_DESCRIPTION, "Draw fractal flame");
		
		_state = state;
		_threadPool = threadPool;
		_progress = progress;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DrawingWorker task = new DrawingWorker(_state, _threadPool);
		task.setProgressModel(_progress);
		task.setRenderedImageModel(_state.getRenderedImageModel());
		task.execute();
	}
	
}
