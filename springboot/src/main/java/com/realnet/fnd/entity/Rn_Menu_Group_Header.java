package com.realnet.fnd.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "RN_MENU_GROUP_HEADER")
public class Rn_Menu_Group_Header extends Rn_Who_Columns {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "MENU_NAME")
	private String menu_name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ACTIVE")
	private boolean active;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name = "START_DATE")
	private LocalDate start_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name = "END_DATE")
	private LocalDate end_date;

	// header
	@OneToMany(mappedBy = "menu_group_header", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Rn_Menu_Group_Line> menu_group_lines;

	
	public Rn_Menu_Group_Header() {
		super();
	}

	public Rn_Menu_Group_Header(int id, String menu_name, String description, boolean active, LocalDate start_date,
			LocalDate end_date, List<Rn_Menu_Group_Line> menu_group_lines) {
		super();
		this.id = id;
		this.menu_name = menu_name;
		this.description = description;
		this.active = active;
		this.start_date = start_date;
		this.end_date = end_date;
		this.menu_group_lines = menu_group_lines;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public List<Rn_Menu_Group_Line> getMenu_group_lines() {
		return menu_group_lines;
	}

	public void setMenu_group_lines(List<Rn_Menu_Group_Line> menu_group_lines) {
		this.menu_group_lines = menu_group_lines;
	}
	
}
