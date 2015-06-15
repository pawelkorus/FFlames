package fflames;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fflames.events.LoadProject;

public class MainWindowActions extends FormActions {
	public MainWindowActions(Object parent) {
		super(parent);
	}
	
	public SaveImageAction createSaveImageAction() {
		return new SaveImageAction();
	}
	
	public SaveAction createSaveAction() {
		return new SaveAction();
	}
	
	public OpenAction createOpenAction(String name) {
		if(name.isEmpty()) {
			return new OpenAction();
		} else {
			return new OpenAction(name);
		}
	}
	
	public ExitAction createExitAction() {
		return new ExitAction();
	}
	
	public ShowAboutAction createShowAboutAction() {
		return new ShowAboutAction();
	}
	
	public DrawAction createDrawAction() {
		return new DrawAction();
	}
	
	public AddTransformAction createAddTransformAction() {
		return new AddTransformAction();
	}
	
	public RemoveTransformAction createRemoveTransformAction() {
		return new RemoveTransformAction();
	}
	
	public NewFractalAction createNewFractalAction() {
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
			fireActionEvent(fflames.events.Action.Actions.NewProject);
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
			fireActionEvent(fflames.events.Action.Actions.SaveProject);
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
			fireActionEvent(fflames.events.Action.Actions.SaveProject);
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
			fireActionEvent(fflames.events.Action.Actions.ExitApplication);
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
			fireActionEvent(fflames.events.Action.Actions.ShowAbout);
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
			fireActionEvent(fflames.events.Action.Actions.AddTransform);
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
			fireActionEvent(fflames.events.Action.Actions.RemoveTransform);
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
			fireActionEvent(fflames.events.Action.Actions.Draw);
		}
		
	}
	
	private static final long serialVersionUID = 1L;
}
