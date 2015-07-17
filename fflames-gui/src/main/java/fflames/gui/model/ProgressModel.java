package fflames.gui.model;

import java.io.Serializable;

public class ProgressModel extends AbstractModel 
	implements Serializable, IProgressModel {
	
	public ProgressModel() {
		super();
		initParam(PROGRESS, (Integer) 0);
		initParam(START_PROGRESS_VALUE, (Integer) 0);
		initParam(END_PROGRESS_VALUE, (Integer) 100);
	}
	
	@Override
	public int getStartProgressValue() {
		return (Integer)getParam(START_PROGRESS_VALUE);
	}
	
	@Override
	public void setStartProgressValue(int value) {
		setParam(START_PROGRESS_VALUE, value);
	}
	
	@Override
	public int getEndProgressValue() {
		return (Integer)getParam(END_PROGRESS_VALUE);
	}
	
	@Override
	public void setEndProgressValue(int value) {
		setParam(END_PROGRESS_VALUE, value);
	} 
	
	@Override
	public int getProgress() {
		return (Integer)getParam(PROGRESS);
	}
	
	@Override
	public void setProgress(int value) {
		if(value < getStartProgressValue()) {
			setParam(PROGRESS, getStartProgressValue());
		} else if(value > getEndProgressValue()) {
			setParam(PROGRESS, getEndProgressValue());
		} else {
			setParam(PROGRESS, value);
		}
	}
	
	@Override
	public void reset() {
		setProgress(getStartProgressValue());
	}
}
