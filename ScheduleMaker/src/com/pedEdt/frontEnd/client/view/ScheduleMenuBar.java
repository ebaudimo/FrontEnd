package com.pedEdt.frontEnd.client.view;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
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
		semesterMenu.addItem("Nouveau semestre", new ScheduledCommand() {

			@Override
			public void execute() {
				//				DebugPanel.getInstance().vpan.add(new Label("Semester->New callback"));
				Forms.popupCreateSemester().center();
			}
		});
		semesterMenu.addItem("Nouvelle UE", new ScheduledCommand() {

			@Override
			public void execute() {
				//				DebugPanel.getInstance().vpan.add(new Label("Semester->Close callback"));
				Forms.popupCreateTeachingUnit(semester).center();
			}
		});
		semesterMenu.addItem("Ouvrir semestre", new ScheduledCommand() {

			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("Semester->Open callback"));
			}
		});
		semesterMenu.addItem("Supprimer semestre", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer ce semestre?"))
					ServerCommunication.getInstance().deleteSemester(semester.getId());
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

		selectedOption.addItem("Nouveau Module", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupCreateModule(teachingUnit).center();
			}
		});

		selectedOption.addItem("Modifier l'UE", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupUpdateTeachingUnit(teachingUnit).center();
			}
		});

		selectedOption.addItem("Supprimer l'UE", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer cette UE?"))
					ServerCommunication.getInstance().deleteTeachingUnit(teachingUnit.getId());
			}
		});
	}

	public static void updateToModuleOptions(final Module module) {
		selectedOption.clearItems();

		selectedOption.addItem("Nouvel Enseignement", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupCreateTeaching(module).center();
			}
		});

		selectedOption.addItem("Modifier le Module", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupUpdateModule(module).center();
			}
		});

		selectedOption.addItem("Supprimer le Module", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer ce module?"))
					ServerCommunication.getInstance().deleteModule(module.getId());
			}
		});
	}

	public static void updateToTeachingOptions(final Teaching teaching) {
		selectedOption.clearItems();

		selectedOption.addItem("Modifier l'enseignement", new ScheduledCommand() {

			@Override
			public void execute() {
				Forms.popupUpdateTeaching(teaching).center();
			}
		});

		selectedOption.addItem("Supprimer l'enseignement", new ScheduledCommand() {

			@Override
			public void execute() {
				if (Window.confirm("Voulez-vous supprimer cet enseignement?"))
					ServerCommunication.getInstance().deleteTeaching(teaching.getId());
			}
		});
	}
}