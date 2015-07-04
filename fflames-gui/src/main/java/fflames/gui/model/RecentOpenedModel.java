package fflames.gui.model;

import javax.swing.AbstractListModel;

import prefs.SettingArray;

public class RecentOpenedModel extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;
	SettingArray _node = null;
	int _maxSize = 0;
	
	public RecentOpenedModel(SettingArray node, int maxSize) {
		super();
		_node = node;
		_maxSize = maxSize;
	}

	@Override
	public int getSize() {
		return _node.size();
	}

	@Override
	public String getElementAt(int index) {
		return _node.getElementAt(index);
	}
	
	public void add(String path) {
		int elements = _node.size();
		for(int i = 0; i < elements; i++) {
			if(_node.getElementAt(i).compareTo(path) == 0) return;
		}
		_node.addElementAt(0, path);
		fireIntervalAdded(this, 0, 0);
		if(_maxSize > 0 && _node.size() > _maxSize) {
			_node.removeLast();
			fireIntervalRemoved(this, _maxSize, _maxSize);
		}
	}
}
