package prefs;

import java.util.ListIterator;

public class SettingArrayIterator implements ListIterator<String> {
	private SettingArray _elements = null;
	private Cursor _cursor = null;
	
	public SettingArrayIterator(SettingArray elements) {
		_elements = elements;
		_cursor = new Cursor();
	}
	
	@Override
	public boolean hasNext() {
		return _cursor.nextIndex() < _elements.size();
	}

	@Override
	public String next() {
		if(hasNext()) {
			_cursor.forward();
			return _elements.getElementAt(_cursor.index);
		}
		
		return new String();
	}

	@Override
	public boolean hasPrevious() {
		return _cursor.previousIndex() >= 0;
	}

	@Override
	public String previous() {
		if(hasPrevious()) {
			_cursor.backward();
			return _elements.getElementAt(_cursor.index);
		}
		
		return new String();
	}

	@Override
	public int nextIndex() {
		return _cursor.nextIndex();
	}

	@Override
	public int previousIndex() {
		return _cursor.previousIndex();
	}

	@Override
	public void remove() {
		if(_cursor.isValid()) {
			if(_cursor.actionCalled) throw new IllegalStateException();
			
			_elements.removeElementAt(_cursor.index);
			_cursor.setActionCalled();
		}
	}

	@Override
	public void set(String e) {
		if(_cursor.isValid() && !_cursor.actionCalled) {
			_elements.setElementAt(_cursor.index, e);
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public void add(String e) {
		_elements.addElementAt(_cursor.index, e);
		_cursor.setActionCalled();
	}
	
	private class Cursor {
		public int index = -1;
		public boolean actionCalled = false;
		
		public int nextIndex() {
			return index+1;
		}
		
		public int previousIndex() {
			return index-1;
		}
		
		public void forward() {
			index++;
			actionCalled = false;
		}
		
		public void backward() {
			index--;
			actionCalled = false;
		}
		
		public void setActionCalled() {
			actionCalled = true;
		}
		
		public boolean isValid() {
			return index >= 0;
		}
	}
}
