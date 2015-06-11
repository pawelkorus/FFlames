package prefs;

import java.util.prefs.Preferences;

public class Settings {
	Preferences _settings;
	
	private static Settings _instance = null;
	
	public enum NodeName {
		RecentOpened("recentOpened");
		final private String name;
		
		private NodeName(String value) {
			name = value;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	protected Settings() {
		_settings = Preferences.userRoot().node("/fflames/settings");
	}
	
	public SettingArray getRecentOpenedPaths() {
		return new SettingArray(_settings.node(NodeName.RecentOpened.toString()));
	}
	
	public static Settings getInstance() {
		if(_instance == null) {
			_instance = new Settings();
		}
		
		return _instance;
	}
}
