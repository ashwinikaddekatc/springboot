package com.realnet.fnd.service;

import java.util.List;

import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Group_Autofill;

public interface Rn_Vacation_Rule_Service {
//	public int addVacationRule(Vacation_Rule vacation_rule);
	
	//public List<String> get_username();
	public List<Rn_Menu_Group_Autofill> get_group_menu();
	public List<Rn_Menu_Group_Autofill> update_group_menu(int menu_grp_id);
	public List<Rn_Menu_Group_Autofill> update_group_menu_line(int menu_grp_id);
	public int savemenu(Rn_Menu_Group_Header menugrouph); 
	
	public int addmenuGroupLine(int rowcount,int menu_group_id, String report_group_line_id1[], String name1[],String active1[]);
	
//	public List<Vacation_Rule> view_vacation(int user_id);
}
