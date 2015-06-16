package fflames.gui;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import fflames.gui.events.Action;

public class FormActions extends PropertyChangeSupport {
	public FormActions(Object sourceBean) {
		super(sourceBean);
		_source = sourceBean;
	}

	public void addActionListener(ActionListener listener) {
		_listeners.add(listener);
	}
	
	public void removeActionListener(ActionListener listener) {
		_listeners.remove(listener);
	}
	
	public void fireActionEvent(Action.Actions action) {
		fireActionEvent(new Action(_source, action));
	}
	
	public void fireActionEvent(Action event) {
		for(ActionListener listener : _listeners) {
			listener.actionPerformed(event);
		}
	}
	
	private static final long serialVersionUID = 1L;
	private ArrayList<ActionListener> _listeners = new ArrayList<ActionListener>();
	protected Object _source;
}
