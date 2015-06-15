package fflames.events;

public class Action extends java.awt.event.ActionEvent {
	public static int EVENT_ID = RESERVED_ID_MAX + 100;
	
	public enum Actions {
		LoadProject(EVENT_ID + 1),
		SaveProject(EVENT_ID + 2),
		SaveGeneratedImage(EVENT_ID + 3),
		NewProject(EVENT_ID + 4),
		PropertyChanged(EVENT_ID + 5),
		AddTransform(EVENT_ID + 6),
		RemoveTransform(EVENT_ID + 7),
		Draw(EVENT_ID + 9),
		ShowAbout(EVENT_ID + 10),
		ExitApplication(EVENT_ID + 8);
		
		final private int _value;
		
		private Actions(int value) {
			_value = value;
		}
		
		public int getValue() {
			return _value;
		}
		
		public boolean equals(int value) {
			return _value == value;
		}
	}
	
	public Action(Object source, Actions id) {
		super(source, id.getValue(), "");
	}
	
	public Action(Object source, Actions id, String command) {
		super(source, id.getValue(), command);
	}

	private static final long serialVersionUID = 1L;
}
