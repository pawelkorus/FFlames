package prefs;

import java.util.Iterator;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SettingArray implements Iterable<String> {
	private Preferences _node = null;
	
	public SettingArray(Preferences node) {
		_node = node;
	}
	
	public int size() {
		return getKeys().length;
	}
	
	public void append(String value) {
		addElementAt(size(), value);
	}
	
	public void prepend(String value) {
		addElementAt(0, value);
	}
	
	public void addElementAt(int index, String value) {
		try {
			String[] keys = _node.keys();
			
			int lastIndex = keys.length - 1;
			
			for(int i = lastIndex; i >= index && i <= lastIndex; i--) {
				String tmp = _node.get(keys[i], "");
				_node.put(keyFromIndex(i + 1), tmp);
			}
			_node.put(keyFromIndex(index), value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getElementAt(int index) {
		return _node.get(keyFromIndex(index), new String());
	}
	
	public void setElementAt(int index, String newValue) {
		String[] keys = getKeys();
		if(index < 0 || index >= keys.length) return;
		_node.put(keyFromIndex(index), newValue);
	}
	
	public void removeElementAt(int index) {
		try {
			String[] keys = _node.keys();
			
			int lastIndex = keys.length - 1;
			
			for(int i = index; i >= 0 && i < lastIndex; i++) {
				String value = _node.get(keys[i + 1], "");
				_node.put(keyFromIndex(i), value);
			}
			_node.remove(keyFromIndex(lastIndex));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeLast() {
		String[] keys = getKeys();
		removeElementAt(keys.length - 1);
	}
	
	@Override
	public Iterator<String> iterator() {
		return new SettingArrayIterator(this);
	}
	
	private String[] getKeys() {
		try {
			return _node.keys();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		
		return new String[0];
	}
	
	private String keyFromIndex(int index) {
		return String.format("%06d", index);
	}
}
