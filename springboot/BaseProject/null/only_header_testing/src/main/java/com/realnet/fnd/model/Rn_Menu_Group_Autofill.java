package com.realnet.fnd.model;

import java.util.Date;

public class Rn_Menu_Group_Autofill {
	
	//header
	private int menu_group_header_id;	
	private String menu_name;
	private String description;
	private String header_active;
	private String header_start_date;
	private String header_end_date;
	
	//line
	
	private int menu_group_line_id;
	private String _linedescription;
	private String active;
	private String start_date;
	private String end_date;
	private int menu_id;
	
	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	private int report_id;
	private String report_name;
	
	
	private int main_menu_id;
	private String main_menu_name;
	
	public Rn_Menu_Group_Autofill(int menu_id,String name)
	{
		this.main_menu_id=menu_id;
		this.main_menu_name=name;
	}
	
	public int getMain_menu_id() {
		return main_menu_id;
	}

	public void setMain_menu_id(int main_menu_id) {
		this.main_menu_id = main_menu_id;
	}

	public String getMain_menu_name() {
		return main_menu_name;
	}

	public void setMain_menu_name(String main_menu_name) {
		this.main_menu_name = main_menu_name;
	}

	public int getMenu_group_header_id() {
		return menu_group_header_id;
	}
	public void setMenu_group_header_id(int menu_group_header_id) {
		this.menu_group_header_id = menu_group_header_id;
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
	public String getHeader_active() {
		return header_active;
	}
	public void setHeader_active(String header_active) {
		this.header_active = header_active;
	}
	public String getHeader_start_date() {
		return header_start_date;
	}
	public void setHeader_start_date(String header_start_date) {
		this.header_start_date = header_start_date;
	}
	public String getHeader_end_date() {
		return header_end_date;
	}
	public void setHeader_end_date(String header_end_date) {
		this.header_end_date = header_end_date;
	}
	public int getMenu_group_line_id() {
		return menu_group_line_id;
	}
	public void setMenu_group_line_id(int menu_group_line_id) {
		this.menu_group_line_id = menu_group_line_id;
	}
	public String get_linedescription() {
		return _linedescription;
	}
	public void set_linedescription(String _linedescription) {
		this._linedescription = _linedescription;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	
}
