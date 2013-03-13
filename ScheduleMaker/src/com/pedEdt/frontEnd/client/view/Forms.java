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
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
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
//		startDate.addBlurHandler(new BlurHandler() {
//			
//			@Override
//			public void onBlur(BlurEvent event) {
//				startDatePicker.setVisible(false);
//			}
//		});
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
//		endDate.addBlurHandler(new BlurHandler() {
//			
//			@Override
//			public void onBlur(BlurEvent event) {
//				endDatePicker.setVisible(false);
//			}
//		});
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
				semester.setStartDate(startDatePicker.getValue());
				semester.setEndDate(endDatePicker.getValue());

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
		startDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(semester.getStartDate()));
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
//		startDate.addBlurHandler(new BlurHandler() {
//			
//			@Override
//			public void onBlur(BlurEvent event) {
//				startDatePicker.setVisible(false);
//			}
//		});
		startDatePanel.add(startDate);
		startDatePanel.add(startDatePicker);
		holder.add(startDatePanel);

		//endDate
		HorizontalPanel endDatePanel = new HorizontalPanel();
		endDatePanel.add(new Label("Date de fin : "));
		final TextBox endDate = new TextBox();
		endDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(semester.getEndDate()));
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
//		endDate.addBlurHandler(new BlurHandler() {
//			
//			@Override
//			public void onBlur(BlurEvent event) {
//				endDatePicker.setVisible(false);
//			}
//		});
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
				semester.setStartDate(startDatePicker.getValue());
				semester.setEndDate(endDatePicker.getValue());

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

		//semester
		holder.add(new Label("Liste deroulante semestre ?"));

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
				teachingUnit.setSemester(semester);

				Window.alert(TeachingUnit.toXML.toXml(teachingUnit));
				//ServerCommunication.getInstance().createTeachingUnit(teachingUnit);
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

		//semester
		holder.add(new Label("Liste deroulante semestre ?"));

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

		//semester
		holder.add(new Label("Liste deroulante UE ?"));

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
				module.setTeachingUnit(teachingUnit);

				Window.alert(Module.toXML.toXml(module));
				//ServerCommunication.getInstance().createModule(module);
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

		//semester
		holder.add(new Label("Liste deroulante UE ?"));

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
}
