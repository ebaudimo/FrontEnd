package com.pedEdt.frontEnd.client.view;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.model.TeachingType;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public class Forms {

	public static PopupPanel popupCreateSemester() {

		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Creer semestre"));

		//year
		HorizontalPanel yearPanel = new HorizontalPanel();
		yearPanel.add(new Label("Annee : "));
		final TextBox year = new TextBox();
		yearPanel.add(year);
		holder.add(yearPanel);

		//number
		HorizontalPanel numberPanel = new HorizontalPanel();
		numberPanel.add(new Label("Numero : "));
		final TextBox number = new TextBox();
		numberPanel.add(number);
		holder.add(numberPanel);

		//startDate
		HorizontalPanel startDatePanel = new HorizontalPanel();
		startDatePanel.add(new Label("Date de debut : "));
		final TextBox startDate = new TextBox();
		final DatePicker startDatePicker = new DatePicker();
		startDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				startDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(date));
				startDatePicker.setVisible(false);
			}
		});
		startDatePicker.setVisible(false);
		startDate.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				startDatePicker.setVisible(true);
			}
		});
		startDatePanel.add(startDate);
		startDatePanel.add(startDatePicker);
		holder.add(startDatePanel);

		//endDate
		HorizontalPanel endDatePanel = new HorizontalPanel();
		endDatePanel.add(new Label("Date de fin : "));
		final TextBox endDate = new TextBox();
		final DatePicker endDatePicker = new DatePicker();
		endDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				endDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(date));
				endDatePicker.setVisible(false);
			}
		});
		endDatePicker.setVisible(false);
		endDate.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				endDatePicker.setVisible(true);
			}
		});
		endDatePanel.add(endDate);
		endDatePanel.add(endDatePicker);
		holder.add(endDatePanel);
		
		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				Semester semester = new Semester();
				semester.setYear((year.getText().trim().equals("")) ? 0 : Integer.parseInt(year.getText().trim()));
				semester.setNumber((number.getText().trim().equals("")) ? 0 : Integer.parseInt(number.getText().trim()));
				semester.setStartDate(startDatePicker.getValue().getTime() / 1000);
				semester.setEndDate(endDatePicker.getValue().getTime() / 1000);

				Window.alert(Semester.toXML.toXml(semester));
				//ServerCommunication.getInstance().createSemester(semester);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupCreateSemester()

	
	
	public static PopupPanel popupUpdateSemester(final Semester semester) {

		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Modifier semestre"));

		//year
		HorizontalPanel yearPanel = new HorizontalPanel();
		yearPanel.add(new Label("Annee : "));
		final TextBox year = new TextBox();
		year.setText("" + semester.getYear());
		yearPanel.add(year);
		holder.add(yearPanel);

		//number
		HorizontalPanel numberPanel = new HorizontalPanel();
		numberPanel.add(new Label("Numero : "));
		final TextBox number = new TextBox();
		number.setText("" + semester.getNumber());
		numberPanel.add(number);
		holder.add(numberPanel);

		//startDate
		HorizontalPanel startDatePanel = new HorizontalPanel();
		startDatePanel.add(new Label("Date de debut : "));
		final TextBox startDate = new TextBox();
		startDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(new Date(semester.getStartDate())));
		final DatePicker startDatePicker = new DatePicker();
		startDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				startDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(date));
				startDatePicker.setVisible(false);
			}
		});
		startDatePicker.setVisible(false);
		startDate.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				startDatePicker.setVisible(true);
			}
		});
		startDatePanel.add(startDate);
		startDatePanel.add(startDatePicker);
		holder.add(startDatePanel);

		//endDate
		HorizontalPanel endDatePanel = new HorizontalPanel();
		endDatePanel.add(new Label("Date de fin : "));
		final TextBox endDate = new TextBox();
		endDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(new Date(semester.getEndDate())));
		final DatePicker endDatePicker = new DatePicker();
		endDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				endDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(date));
				endDatePicker.setVisible(false);
			}
		});
		endDatePicker.setVisible(false);
		endDate.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				endDatePicker.setVisible(true);
			}
		});
		endDatePanel.add(endDate);
		endDatePanel.add(endDatePicker);
		holder.add(endDatePanel);

		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				semester.setYear((year.getText().trim().equals("")) ? 0 : Integer.parseInt(year.getText().trim()));
				semester.setNumber((number.getText().trim().equals("")) ? 0 : Integer.parseInt(number.getText().trim()));
				semester.setStartDate(startDatePicker.getValue().getTime() /1000);
				semester.setEndDate(endDatePicker.getValue().getTime() / 1000);

				Window.alert(Semester.toXML.toXml(semester));
				//ServerCommunication.getInstance().updateSemester(semester);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupUpdateSemester(Semester semester)
	
	
	
	public static PopupPanel popupCreateTeachingUnit(final Semester semester) {

		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Creer UE"));

		//code
		HorizontalPanel codePanel = new HorizontalPanel();
		codePanel.add(new Label("Code : "));
		final TextBox code = new TextBox();
		codePanel.add(code);
		holder.add(codePanel);

		//title
		HorizontalPanel titlePanel = new HorizontalPanel();
		titlePanel.add(new Label("Titre : "));
		final TextBox title = new TextBox();
		titlePanel.add(title);
		holder.add(titlePanel);

		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				TeachingUnit teachingUnit = new TeachingUnit();
				teachingUnit.setCode(code.getText().trim());
				teachingUnit.setTitle(title.getText().trim());

				Window.alert(TeachingUnit.toXML.toXml(teachingUnit));
				//ServerCommunication.getInstance().createTeachingUnit(semester.getId(), teachingUnit);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupCreateTeachingUnit(Semester semester)
	
	
	
	public static PopupPanel popupUpdateTeachingUnit(final TeachingUnit teachingUnit) {
		
		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Modifier UE"));

		//code
		HorizontalPanel codePanel = new HorizontalPanel();
		codePanel.add(new Label("Code : "));
		final TextBox code = new TextBox();
		code.setText(teachingUnit.getCode());
		codePanel.add(code);
		holder.add(codePanel);

		//title
		HorizontalPanel titlePanel = new HorizontalPanel();
		titlePanel.add(new Label("Titre : "));
		final TextBox title = new TextBox();
		title.setText(teachingUnit.getTitle());
		titlePanel.add(title);
		holder.add(titlePanel);

		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				teachingUnit.setCode(code.getText().trim());
				teachingUnit.setTitle(title.getText().trim());

				Window.alert(TeachingUnit.toXML.toXml(teachingUnit));
				//ServerCommunication.getInstance().updateTeachingUnit(teachingUnit);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupUpdateTeachingUnit(TeachintUnit teachingUnit)
	
	
	
	public static PopupPanel popupCreateModule(final TeachingUnit teachingUnit) {

		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Creer module"));

		//code
		HorizontalPanel codePanel = new HorizontalPanel();
		codePanel.add(new Label("Code : "));
		final TextBox code = new TextBox();
		codePanel.add(code);
		holder.add(codePanel);

		//title
		HorizontalPanel titlePanel = new HorizontalPanel();
		titlePanel.add(new Label("Titre : "));
		final TextBox title = new TextBox();
		titlePanel.add(title);
		holder.add(titlePanel);

		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				Module module = new Module();
				module.setCode(code.getText().trim());
				module.setTitle(title.getText().trim());

				Window.alert(Module.toXML.toXml(module));
				//ServerCommunication.getInstance().createModule(teachingUnit.getId(), module);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupCreateModule(TeachingUnit teachingUnit)
	
	
	
	public static PopupPanel popupUpdateModule(final Module module) {
		
		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Creer Module"));

		//code
		HorizontalPanel codePanel = new HorizontalPanel();
		codePanel.add(new Label("Code : "));
		final TextBox code = new TextBox();
		code.setText(module.getCode());
		codePanel.add(code);
		holder.add(codePanel);

		//title
		HorizontalPanel titlePanel = new HorizontalPanel();
		titlePanel.add(new Label("Titre : "));
		final TextBox title = new TextBox();
		title.setText(module.getTitle());
		titlePanel.add(title);
		holder.add(titlePanel);

		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				module.setCode(code.getText().trim());
				module.setTitle(title.getText().trim());

				Window.alert(Module.toXML.toXml(module));
				//ServerCommunication.getInstance().updateModule(module);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupUpdateModule(Module module)
	
	
	
	public static PopupPanel popupCreateTeaching(final Module module) {

		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Creer Enseignement"));

		//type
		HorizontalPanel typePanel = new HorizontalPanel();
		typePanel.add(new Label("Type : "));
		final ListBox type = new ListBox();
		for (TeachingType t : TeachingType.values())
			type.addItem(t.toString(), t.toString());
		typePanel.add(type);
		holder.add(typePanel);
		
		//teacher
		HorizontalPanel teacherPanel = new HorizontalPanel();
		teacherPanel.add(new Label("Professeur(s) : "));
		final TextBox teacher = new TextBox();
		teacherPanel.add(teacher);
		holder.add(teacherPanel);
		
		//nbHour
		HorizontalPanel nbHourPanel = new HorizontalPanel();
		nbHourPanel.add(new Label("Nombre d'heures : "));
		final TextBox nbHour = new TextBox();
		nbHourPanel.add(nbHour);
		holder.add(nbHourPanel);
		
		//nbSeance
		HorizontalPanel nbSeancePanel = new HorizontalPanel();
		nbSeancePanel.add(new Label("Nombre de seances : "));
		final TextBox nbSeance = new TextBox();
		nbSeancePanel.add(nbSeance);
		holder.add(nbSeancePanel);
		
		//numGroup
		HorizontalPanel numGroupPanel = new HorizontalPanel();
		numGroupPanel.add(new Label("Numero du groupe : "));
		final TextBox numGroup = new TextBox();
		numGroupPanel.add(numGroup);
		holder.add(numGroupPanel);

		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				Teaching teaching = new Teaching();
				teaching.setType(TeachingType.getTeachingType(type.getValue(type.getSelectedIndex())));
				teaching.setTeacher(teacher.getText().trim());
				teaching.setNbHour(Integer.parseInt(nbHour.getText().trim()));
				teaching.setNbSeance(Integer.parseInt(nbSeance.getText().trim()));
				teaching.setNumGroup(Integer.parseInt(numGroup.getText().trim()));

				Window.alert(Teaching.toXML.toXml(teaching));
				//ServerCommunication.getInstance().createTeaching(module.getId(), teaching);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupCreateTeaching(final Module module)
	
	
	
	public static PopupPanel popupUpdateTeaching(final Teaching teaching) {

		final PopupPanel popupPanel = new PopupPanel(false, true);

		final FormPanel form = new FormPanel();

		VerticalPanel holder = new VerticalPanel();
		
		holder.add(new Label("Modifier Enseignement"));

		//type
		HorizontalPanel typePanel = new HorizontalPanel();
		typePanel.add(new Label("Type : "));
		final ListBox type = new ListBox();
		for (TeachingType t : TeachingType.values())
			type.addItem(t.toString(), t.toString());
		typePanel.add(type);
		holder.add(typePanel);
		
		//teacher
		HorizontalPanel teacherPanel = new HorizontalPanel();
		teacherPanel.add(new Label("Professeur(s) : "));
		final TextBox teacher = new TextBox();
		teacher.setText(teaching.getTeacher());
		teacherPanel.add(teacher);
		holder.add(teacherPanel);
		
		//nbHour
		HorizontalPanel nbHourPanel = new HorizontalPanel();
		nbHourPanel.add(new Label("Nombre d'heures : "));
		final TextBox nbHour = new TextBox();
		nbHour.setText("" + teaching.getNbHour());
		nbHourPanel.add(nbHour);
		holder.add(nbHourPanel);
		
		//nbSeance
		HorizontalPanel nbSeancePanel = new HorizontalPanel();
		nbSeancePanel.add(new Label("Nombre de seances : "));
		final TextBox nbSeance = new TextBox();
		nbSeance.setText("" + teaching.getNbSeance());
		nbSeancePanel.add(nbSeance);
		holder.add(nbSeancePanel);
		
		//numGroup
		HorizontalPanel numGroupPanel = new HorizontalPanel();
		numGroupPanel.add(new Label("Numero du groupe : "));
		final TextBox numGroup = new TextBox();
		numGroup.setText("" + teaching.getNumGroup());
		numGroupPanel.add(numGroup);
		holder.add(numGroupPanel);

		//submit
		Button submitButton = new Button("Valider", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		//cancel
		Button cancelButton = new Button("Annuler", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popupPanel.hide();
			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		holder.add(buttonsPanel);

		form.add(holder);
		form.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				Teaching teaching = new Teaching();
				teaching.setType(TeachingType.getTeachingType(type.getValue(type.getSelectedIndex())));
				teaching.setTeacher(teacher.getText().trim());
				teaching.setNbHour(Integer.parseInt(nbHour.getText().trim()));
				teaching.setNbSeance(Integer.parseInt(nbSeance.getText().trim()));
				teaching.setNumGroup(Integer.parseInt(numGroup.getText().trim()));

				Window.alert(Teaching.toXML.toXml(teaching));
				//ServerCommunication.getInstance().updateTeaching(teaching);
				popupPanel.hide();
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupUpdateTeaching(final Teaching teaching)
}
