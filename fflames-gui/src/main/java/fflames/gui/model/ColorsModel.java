/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fflames.gui.model;

import fflames.gui.IVisitable;
import fflames.gui.IVisitor;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Model that stores colors assigned by the user to the transforms.
 * 
 * @author Pawel Korus
 */
public class ColorsModel implements ListModel<float[]>, IVisitable {
	private final float[] _defaultComponents;
	private final ArrayList<float[]> _values;
	private final ArrayList<ListDataListener> _listeners;
	
	public ColorsModel() {
		_values = new ArrayList<>();
		_listeners = new ArrayList<>();
		_defaultComponents = new float[]{0.0f, 0.0f, 0.0f};
	}
	
	@Override
	public int getSize() {
		return _values.size();
	}
	
	public void setSize(int newSize) {
		int currentSize = _values.size();
		if(currentSize > 0) {
			_values.clear();
			fireListDataEvent(new ListDataEvent(
					this, 
					ListDataEvent.INTERVAL_REMOVED,
					0, currentSize - 1));
		}
		
		if(newSize > 0) {
			for(int i = 0; i < newSize; i++ ) {
				_values.add(_defaultComponents.clone());
			}
			fireListDataEvent(new ListDataEvent(
					this,
					ListDataEvent.INTERVAL_ADDED,
					0, newSize - 1));
		}
	}
	
	@Override
	public float[] getElementAt(int index) {
		return _values.get(index);
	}
	
	public void setElementAt(int index, float[] components) {
		_values.set(index, components);
		fireListDataEvent(new ListDataEvent(
				this, ListDataEvent.CONTENTS_CHANGED, index, index));
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		_listeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		_listeners.remove(l);
	}
	
	protected void fireListDataEvent(ListDataEvent e) {
		iteraterOverListeners((listener) -> {
			switch(e.getType()) {
				case ListDataEvent.CONTENTS_CHANGED:
					listener.contentsChanged(e);
					break;
				case ListDataEvent.INTERVAL_ADDED:
					listener.intervalAdded(e);
					break;
				case ListDataEvent.INTERVAL_REMOVED:
					listener.intervalRemoved(e);
					break;
			}
		});
	}
	
	private void iteraterOverListeners(Consumer<ListDataListener> c) {
		for(int i = _listeners.size() - 1; i >= 0; i--) {
			c.accept(_listeners.get(i));
		}
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.handle(this);
	}
}
