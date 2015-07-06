package fflames.gui.model;

/**
 * Represents array indexes. Array index is an integer value greater
 * than or equal 0. If invalid array index value is given then internal
 * state of the object is marked as invalid.
 * 
 * @author Pawel Korus
 */
public class IndexValue {
	public final static IndexValue InvalidValue = new IndexValue();
	private final int _v;
	
	public IndexValue(int index) {
		if(index < 0) {
			throw new IllegalArgumentException();
		} else {
			_v = index; 
		}
	}
	
	public int toInt() {
		return _v;
	}
	
	private IndexValue() {
		_v = -1;
	}
}
