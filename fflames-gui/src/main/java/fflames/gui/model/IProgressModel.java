package fflames.gui.model;

/**
 *
 * @author Pawel Korus
 */
public interface IProgressModel {
	public static final String PROGRESS = "progress";
	public static final String START_PROGRESS_VALUE = "start_progress_value";
	public static final String END_PROGRESS_VALUE = "end_progress_value";
	
	public int getStartProgressValue();
	
	public void setStartProgressValue(int value);
	
	public int getEndProgressValue();
	
	public void setEndProgressValue(int value);
	
	public int getProgress();
	
	public void setProgress(int value);
	
	public void reset();
}
