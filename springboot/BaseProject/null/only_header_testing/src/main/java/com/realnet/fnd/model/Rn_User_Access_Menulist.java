package com.realnet.fnd.model;

public class Rn_User_Access_Menulist {

	int	menu_group_header_id;
	String menu_name;
	
	String col_name;
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}
	public String getCol_table() {
		return col_table;
	}
	public void setCol_table(String col_table) {
		this.col_table = col_table;
	}
	String col_table;
	
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
	
	
}
