package fflames.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fflames.gui.events.LoadProject;

public class MainWindowActions extends FormActions {
	public MainWindowActions(Object parent) {
		super(parent);
	}
	
	public AbstractAction createSaveImageAction() {
		return new SaveImageAction();
	}
	
	public AbstractAction createSaveAction() {
		return new SaveAction();
	}
	
	public AbstractAction createOpenAction(String name) {
		if(name.isEmpty()) {
			return new OpenAction();
		} else {
			return new OpenAction(name);
		}
	}
	
	public AbstractAction createExitAction() {
		return new ExitAction();
	}
	
	public AbstractAction createShowAboutAction() {
		return new ShowAboutAction();
	}
	
	public AbstractAction createDrawAction() {
		return new DrawAction();
	}
	
	public AbstractAction createAddTransformAction() {
		return new AddTransformAction();
	}
	
	public AbstractAction createRemoveTransformAction() {
		return new RemoveTransformAction();
	}
	
	public AbstractAction createNewFractalAction() {
		return new NewFractalAction();
	}
	
	private class NewFractalAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public NewFractalAction() {
			putValue(NAME, "New");
			putValue(SHORT_DESCRIPTION, "Create new fractal project");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.NewProject);
		}	
	}
	
	private class SaveImageAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveImageAction() {
			putValue(NAME, "Save Image");
			putValue(SHORT_DESCRIPTION, "Save fractal to file");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.SaveProject);
		}	
	}
	
	private class SaveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveAction() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Save fractal to file");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.SaveProject);
		}	
	}
	
	private class OpenAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public OpenAction() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Open fractal file");
			putValue(ACTION_COMMAND_KEY, "");
		}
		
		public OpenAction(String name) {
			putValue(NAME, name);
			putValue(SHORT_DESCRIPTION, "Open fractal from file");
			putValue(ACTION_COMMAND_KEY, name);
		}
		
                @Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(new LoadProject(_source, e.getActionCommand()));
		}
	}

	private class ExitAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ExitAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.ExitApplication);
		}
		
	}

	private class ShowAboutAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ShowAboutAction() {
			putValue(NAME, "About FFlames");
			putValue(SHORT_DESCRIPTION, "About FFlames");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.ShowAbout);
		}
	}
	
	private class AddTransformAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public AddTransformAction() {
			putValue(NAME, "Add transform");
			putValue(SHORT_DESCRIPTION, "Add transform to the algorithm");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.AddTransform);
		}
	}
	
	private class RemoveTransformAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public RemoveTransformAction() {
			putValue(NAME, "Remove transform");
			putValue(SHORT_DESCRIPTION, "Remove transform from the algorithm");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.RemoveTransform);
		}
	}
	
	private class DrawAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public DrawAction() {
			putValue(NAME, "Draw");
			putValue(SHORT_DESCRIPTION, "Draw current fractal");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fireActionEvent(fflames.gui.events.Action.Actions.Draw);
		}
		
	}
	
	private static final long serialVersionUID = 1L;
}
