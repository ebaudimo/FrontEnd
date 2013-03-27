package com.pedEdt.frontEnd.client.view;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.pedEdt.frontEnd.client.controller.ServerCommunication;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.model.TeachingUnit;
import com.pedEdt.frontEnd.client.util.DebugPanel;

public class ScheduleMenuBar extends Composite {

	protected static MenuBar menuBar;
	protected static MenuBar selectedOption;

	public ScheduleMenuBar(final Semester semester) {
		menuBar = new MenuBar();

//		//debug
//		MenuBar debugMenu = new MenuBar(true);
//		debugMenu.addItem("Afficher/Cacher", new ScheduledCommand() {
//
//			@Override
//			public void execute() {
//				DebugPanel.getInstance().setVisible(!DebugPanel.getInstance().isVisible());
//			}
//		});
//		debugMenu.addItem("Clear", new ScheduledCommand() {
//
//			@Override
//			public void execute() {
//				DebugPanel.getInstance().vpan.clear();	
//			}
//		});
//		menuBar.addItem("Debug", debugMenu);
//		//end debug

		//semester
		MenuBar semesterMenu = new MenuBar(true);
		semesterMenu.addItem("Nouveau Semestre", new ScheduledCommand() {

			@Override
			public void execute() {
				//				DebugPanel.getInstance().vpan.add(new Label("Semester->New callback"));
				Forms.popupCreateSemester().center();
			}
		});
		semesterMenu.addItem("Ouvrir Semestre", new ScheduledCommand() {

			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("Semester->Open callback"));
			}
		});
		semesterMenu.addItem("Modifier Semestre", new ScheduledCommand() {

			@Override
			public void execute() {
//				DebugPanel.getInstance().vpan.add(new Label("Semester->Open callback"));
				Forms.popupUpdateSemester(semester).center();
			}
		});
		semesterMenu.addItem("Ajouter UE", new ScheduledCommand() {

			@Override
			public void execute() {
				//				DebugPanel.getInstance().vpan.add(new Label("Semester->Close callback"));
				Forms.popupCreateTeachingUnit(semester).center();
			}
		});
		semesterMenu.addItem("Supprimer Semestre", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer ce semestre?")) {
					RootPanel.get().clear();
					StartWindow startPopup = new StartWindow();
					RootPanel.get().add(startPopup);
					startPopup.center();
					ServerCommunication.getInstance().deleteSemester(semester.getId());
				}
			}
		});
		menuBar.addItem("Semestre", semesterMenu);

		//selectedOption
		selectedOption = new MenuBar(true);
		menuBar.addItem("Options", selectedOption);
		initWidget(menuBar);
	}

	public static void updateToTeachingUnitOptions(final TeachingUnit teachingUnit) {
		selectedOption.clearItems();

		selectedOption.addItem("Ajouter Module", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupCreateModule(teachingUnit).center();
			}
		});

		selectedOption.addItem("Modifier UE", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupUpdateTeachingUnit(teachingUnit).center();
			}
		});

		selectedOption.addItem("Supprimer UE", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer cette UE?")) {
					MainGUI.getInstance().getSemester().removeTeachingUnit(teachingUnit);
					ServerCommunication.getInstance().deleteTeachingUnit(teachingUnit.getId());
				}
			}
		});
	}

	public static void updateToModuleOptions(final Module module) {
		selectedOption.clearItems();

		selectedOption.addItem("Ajouter Enseignement", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupCreateTeaching(module).center();
			}
		});

		selectedOption.addItem("Modifier Module", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupUpdateModule(module).center();
			}
		});

		selectedOption.addItem("Supprimer Module", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer ce module?")) {
					MainGUI.getInstance().schedTree.semesterTree.getParentTeachingUnit(module).removeModule(module);
					ServerCommunication.getInstance().deleteModule(module.getId());
				}
			}
		});
	}

	public static void updateToTeachingOptions(final Teaching teaching) {
		selectedOption.clearItems();

		selectedOption.addItem("Modifier Enseignement", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupUpdateTeaching(teaching).center();
			}
		});

		selectedOption.addItem("Supprimer Enseignement", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer cet enseignement?")) {
					MainGUI.getInstance().schedTree.semesterTree.getParentModule(teaching).removeTeaching(teaching);
					ServerCommunication.getInstance().deleteTeaching(teaching.getId());
				}
			}
		});
	}
}