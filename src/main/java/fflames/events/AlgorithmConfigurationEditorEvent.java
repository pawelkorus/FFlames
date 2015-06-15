package fflames.events;

public class AlgorithmConfigurationEditorEvent extends Action {
	public enum Property {
		RotationsNumber("rotationsNumber"),
		IterationsNumber("iterationsNumber"),
		ImageWidth("imageWidth"),
		ImageHeight("imageHeight");
		
		private final String _name;
		
		private Property(String name) {
			_name = name;
		}
		
		public String toString() {
			return _name;
		}
	}
	
	public AlgorithmConfigurationEditorEvent(Object source, Actions id,
			String command) {
		super(source, id, command);
	}

	private static final long serialVersionUID = 1L;

}
