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
import com.google.gwt.user.client.ui.FlexTable;
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);
		
		//year
		holderTab.setWidget(0, 0, new Label("Annee : "));
		final TextBox year = new TextBox();
		holderTab.setWidget(0, 1, year);

		//number
		holderTab.setWidget(1, 0, new Label("Numero (1 ou 2) : "));
		final TextBox number = new TextBox();
		holderTab.setWidget(1, 1, number);

		//startDate
		holderTab.setWidget(2, 0, new Label("Date de debut (jj/mm/aaaa) : "));
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
		holderTab.setWidget(2, 1, startDate);
		holderTab.setWidget(2, 2, startDatePicker);

		//endDate
		holderTab.setWidget(3, 0, new Label("Date de fin (jj/mm/aaaa) : "));
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
		holderTab.setWidget(3, 1, endDate);
		holderTab.setWidget(3, 2, endDatePicker);
		
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
				if (!year.getText().trim().equals("") && !number.getText().trim().equals("") && !startDate.getText().trim().equals("") && !endDate.getText().trim().equals("")) {
					if (year.getText().trim().length() == 4) {
						for (int i = 0; i < year.getText().trim().length(); i++) {
							if (!java.lang.Character.isDigit(year.getText().trim().charAt(i))) {
								Window.alert("Annee incorrecte.");
								return;
							}
						}
						
						if (number.getText().trim().equals("1") || number.getText().trim().equals("2")) {
							if (startDate.getText().trim().matches("^[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}$")) {
								if (endDate.getText().trim().matches("^[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}$")) {
									Date dateStart = new Date(Date.parse(startDate.getText().trim()));
									Date dateEnd = new Date(Date.parse(endDate.getText().trim()));
									
									Semester semester = new Semester();
									semester.setYear(Integer.parseInt(year.getText().trim()));
									semester.setNumber(Integer.parseInt(number.getText().trim()));
									semester.setStartDate(dateStart.getTime() / 1000);
									semester.setEndDate(dateEnd.getTime() / 1000);

									Window.alert(Semester.toXML.toXml(semester));
									//ServerCommunication.getInstance().createSemester(semester);
									popupPanel.hide();
								} else {
									Window.alert("Date de fin incorrecte.");
									return;
								}
							} else {
								Window.alert("Date de debut incorrecte.");
								return;
							}
						} else {
							Window.alert("Numero incorrect.");
							return;
						}
					} else {
						Window.alert("Annee incorrecte.");
						return;
					}
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);

		//year
		holderTab.setWidget(0, 0, new Label("Annee : "));
		final TextBox year = new TextBox();
		year.setText("" + semester.getYear());
		holderTab.setWidget(0, 1, year);

		//number
		holderTab.setWidget(1, 0, new Label("Numero : "));
		final TextBox number = new TextBox();
		number.setText("" + semester.getNumber());
		holderTab.setWidget(1, 1, number);

		//startDate
		holderTab.setWidget(2, 0, new Label("Date de debut (jj/mm/aaaa) : "));
		final TextBox startDate = new TextBox();
		startDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(new Date(semester.getStartDate() * 1000)));
		final DatePicker startDatePicker = new DatePicker();
		startDatePicker.setValue(new Date(semester.getStartDate() * 1000));
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
		holderTab.setWidget(2, 1, startDate);
		holderTab.setWidget(2, 2, startDatePicker);

		//endDate
		holderTab.setWidget(3, 0, new Label("Date de fin (jj/mm/aaaa) : "));
		final TextBox endDate = new TextBox();
		endDate.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(new Date(semester.getEndDate() * 1000)));
		final DatePicker endDatePicker = new DatePicker();
		endDatePicker.setValue(new Date(semester.getEndDate() * 1000));
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
		holderTab.setWidget(3, 1, endDate);
		holderTab.setWidget(3, 2, endDatePicker);

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
				if (!year.getText().trim().equals("") && !number.getText().trim().equals("") && !startDate.getText().trim().equals("") && !endDate.getText().trim().equals("")) {
					if (year.getText().trim().length() == 4) {
						for (int i = 0; i < year.getText().trim().length(); i++) {
							if (!java.lang.Character.isDigit(year.getText().trim().charAt(i))) {
								Window.alert("Annee incorrecte.");
								return;
							}
						}
						
						if (number.getText().trim().equals("1") || number.getText().trim().equals("2")) {
							if (startDate.getText().trim().matches("^[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}$")) {
								if (endDate.getText().trim().matches("^[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}$")) {
										Date dateStart = new Date(Date.parse(startDate.getText().trim()));
										Date dateEnd = new Date(Date.parse(endDate.getText().trim()));
										
										semester.setYear(Integer.parseInt(year.getText().trim()));
										semester.setNumber(Integer.parseInt(number.getText().trim()));
										semester.setStartDate(dateStart.getTime() / 1000);
										semester.setEndDate(dateEnd.getTime() / 1000);

										Window.alert(Semester.toXML.toXml(semester));
										//ServerCommunication.getInstance().updateSemester(semester);
										popupPanel.hide();
								} else {
									Window.alert("Date de fin incorrecte.");
									return;
								}
							} else {
								Window.alert("Date de debut incorrecte.");
								return;
							}
						} else {
							Window.alert("Numero incorrect.");
							return;
						}
					} else {
						Window.alert("Annee incorrecte.");
						return;
					}
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);
		
		//Error
		final Label error = new Label("");
		holder.add(error);

		//code
		holderTab.setWidget(0, 0, new Label("Code : "));
		final TextBox code = new TextBox();
		holderTab.setWidget(0, 1, code);

		//title
		holderTab.setWidget(1, 0, new Label("Titre : "));
		final TextBox title = new TextBox();
		holderTab.setWidget(1, 1, title);

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
				if (!code.getText().trim().equals("") && !title.getText().trim().equals("")) {
					TeachingUnit teachingUnit = new TeachingUnit();
					teachingUnit.setCode(code.getText().trim());
					teachingUnit.setTitle(title.getText().trim());

					Window.alert(TeachingUnit.toXML.toXml(teachingUnit));
					//ServerCommunication.getInstance().createTeachingUnit(semester.getId(), teachingUnit);
					popupPanel.hide();
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);

		//code
		holderTab.setWidget(0, 0, new Label("Code : "));
		final TextBox code = new TextBox();
		code.setText(teachingUnit.getCode());
		holderTab.setWidget(0, 1, code);

		//title
		holderTab.setWidget(1, 0, new Label("Titre : "));
		final TextBox title = new TextBox();
		title.setText(teachingUnit.getTitle());
		holderTab.setWidget(1, 1, title);

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
				if (!code.getText().trim().equals("") && !title.getText().trim().equals("")) {
					teachingUnit.setCode(code.getText().trim());
					teachingUnit.setTitle(title.getText().trim());

					Window.alert(TeachingUnit.toXML.toXml(teachingUnit));
					//ServerCommunication.getInstance().updateTeachingUnit(teachingUnit);
					popupPanel.hide();
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);

		//code
		holderTab.setWidget(0, 0, new Label("Code : "));
		final TextBox code = new TextBox();
		holderTab.setWidget(0, 1, code);

		//title
		holderTab.setWidget(1, 0, new Label("Titre : "));
		final TextBox title = new TextBox();
		holderTab.setWidget(1, 1, title);

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
				if (!code.getText().trim().equals("") && !title.getText().trim().equals("")) {
					Module module = new Module();
					module.setCode(code.getText().trim());
					module.setTitle(title.getText().trim());

					Window.alert(Module.toXML.toXml(module));
					//ServerCommunication.getInstance().createModule(teachingUnit.getId(), module);
					popupPanel.hide();
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);

		//code
		holderTab.setWidget(0, 0, new Label("Code : "));
		final TextBox code = new TextBox();
		code.setText(module.getCode());
		holderTab.setWidget(0, 1, code);

		//title
		holderTab.setWidget(1, 0, new Label("Titre : "));
		final TextBox title = new TextBox();
		title.setText(module.getTitle());
		holderTab.setWidget(1, 1, title);

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
				if (!code.getText().trim().equals("") && !title.getText().trim().equals("")) {
					module.setCode(code.getText().trim());
					module.setTitle(title.getText().trim());
	
					Window.alert(Module.toXML.toXml(module));
					//ServerCommunication.getInstance().updateModule(module);
					popupPanel.hide();
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);

		//type
		holderTab.setWidget(0, 0, new Label("Type : "));
		final ListBox type = new ListBox();
		for (TeachingType t : TeachingType.values())
			type.addItem(t.toString(), t.toString());
		holderTab.setWidget(0, 1, type);
		
		//teacher
		holderTab.setWidget(1, 0, new Label("Professeur(s) : "));
		final TextBox teacher = new TextBox();
		holderTab.setWidget(1, 1, teacher);
		
		//nbHour
		holderTab.setWidget(2, 0, new Label("Nombre d'heures : "));
		final TextBox nbHour = new TextBox();
		holderTab.setWidget(2, 1, nbHour);
		
		//nbSeance
		holderTab.setWidget(3, 0, new Label("Nombre de seances : "));
		final TextBox nbSeance = new TextBox();
		holderTab.setWidget(3, 1, nbSeance);
		
		//numGroup
		holderTab.setWidget(4, 0, new Label("Numero du groupe : "));
		final TextBox numGroup = new TextBox();
		holderTab.setWidget(4, 1, numGroup);

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
				if (!teacher.getText().trim().equals("") && !nbHour.getText().trim().equals("") && !nbSeance.getText().trim().equals("") && !numGroup.getText().trim().equals("")) {
					for (int i = 0; i < nbHour.getText().trim().length(); i++) {
						if (!java.lang.Character.isDigit(nbHour.getText().trim().charAt(i))) {
							Window.alert("Nombre d'heures incorrect.");
							return;
						}
					}
					for (int i = 0; i < nbSeance.getText().trim().length(); i++) {
						if (!java.lang.Character.isDigit(nbSeance.getText().trim().charAt(i))) {
							Window.alert("Nombre de seances incorrect.");
							return;
						}
					}
					for (int i = 0; i < numGroup.getText().trim().length(); i++) {
						if (!java.lang.Character.isDigit(numGroup.getText().trim().charAt(i))) {
							Window.alert("Numero de groupe incorrect.");
							return;
						}
					}
					
					Teaching teaching = new Teaching();
					teaching.setType(TeachingType.getTeachingType(type.getValue(type.getSelectedIndex())));
					teaching.setTeacher(teacher.getText().trim());
					teaching.setNbHour(Integer.parseInt(nbHour.getText().trim()));
					teaching.setNbSeance(Integer.parseInt(nbSeance.getText().trim()));
					teaching.setNumGroup(Integer.parseInt(numGroup.getText().trim()));

					Window.alert(Teaching.toXML.toXml(teaching));
					//ServerCommunication.getInstance().createTeaching(module.getId(), teaching);
					popupPanel.hide();
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
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

		FlexTable holderTab = new FlexTable();
		holder.add(holderTab);

		//type
		holderTab.setWidget(0, 0, new Label("Type : "));
		final ListBox type = new ListBox();
		for (TeachingType t : TeachingType.values()) {
			type.addItem(t.toString(), t.toString());
			if (t.toString().equals(teaching.getType().toString()))
				type.setSelectedIndex(type.getItemCount() - 1);
		}
		holderTab.setWidget(0, 1, type);
		
		//teacher
		holderTab.setWidget(1, 0, new Label("Professeur(s) : "));
		final TextBox teacher = new TextBox();
		teacher.setText(teaching.getTeacher());
		holderTab.setWidget(1, 1, teacher);
		
		//nbHour
		holderTab.setWidget(2, 0, new Label("Nombre d'heures : "));
		final TextBox nbHour = new TextBox();
		nbHour.setText("" + teaching.getNbHour());
		holderTab.setWidget(2, 1, nbHour);
		
		//nbSeance
		holderTab.setWidget(3, 0, new Label("Nombre de seances : "));
		final TextBox nbSeance = new TextBox();
		nbSeance.setText("" + teaching.getNbSeance());
		holderTab.setWidget(3, 1, nbSeance);
		
		//numGroup
		holderTab.setWidget(4, 0, new Label("Numero du groupe : "));
		final TextBox numGroup = new TextBox();
		numGroup.setText("" + teaching.getNumGroup());
		holderTab.setWidget(4, 1, numGroup);

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
				if (!teacher.getText().trim().equals("") && !nbHour.getText().trim().equals("") && !nbSeance.getText().trim().equals("") && !numGroup.getText().trim().equals("")) {
					for (int i = 0; i < nbHour.getText().trim().length(); i++) {
						if (!java.lang.Character.isDigit(nbHour.getText().trim().charAt(i))) {
							Window.alert("Nombre d'heures incorrect.");
							return;
						}
					}
					for (int i = 0; i < nbSeance.getText().trim().length(); i++) {
						if (!java.lang.Character.isDigit(nbSeance.getText().trim().charAt(i))) {
							Window.alert("Nombre de seances incorrect.");
							return;
						}
					}
					for (int i = 0; i < numGroup.getText().trim().length(); i++) {
						if (!java.lang.Character.isDigit(numGroup.getText().trim().charAt(i))) {
							Window.alert("Numero de groupe incorrect.");
							return;
						}
					}

					teaching.setType(TeachingType.getTeachingType(type.getValue(type.getSelectedIndex())));
					teaching.setTeacher(teacher.getText().trim());
					teaching.setNbHour(Integer.parseInt(nbHour.getText().trim()));
					teaching.setNbSeance(Integer.parseInt(nbSeance.getText().trim()));
					teaching.setNumGroup(Integer.parseInt(numGroup.getText().trim()));

					Window.alert(Teaching.toXML.toXml(teaching));
					//ServerCommunication.getInstance().updateTeaching(teaching);
					popupPanel.hide();
				} else {
					Window.alert("Vous devez remplir tous les champs.");
					return;
				}
			}
		});

		popupPanel.setWidget(form);
		return popupPanel;
	} // popupUpdateTeaching(final Teaching teaching)
}
