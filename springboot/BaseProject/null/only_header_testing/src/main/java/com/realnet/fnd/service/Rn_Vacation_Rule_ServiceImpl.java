package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Vacation_Rule_Dao;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Group_Autofill;

@Service
public class Rn_Vacation_Rule_ServiceImpl implements Rn_Vacation_Rule_Service {

	@Autowired
	private Rn_Vacation_Rule_Dao vacationRuleDao;


	public List<Rn_Menu_Group_Autofill> get_group_menu()
	{
		return vacationRuleDao.get_group_menu();
	}
	
	public int savemenu(Rn_Menu_Group_Header menugrouph)
	{
		return vacationRuleDao.savemenu(menugrouph);
	}
	
	public int addmenuGroupLine(int rowcount,int menu_group_id, String report_group_line_id1[], String name1[],String active1[])
	{
		return vacationRuleDao.addmenuGroupLine(rowcount, menu_group_id, report_group_line_id1, name1, active1);
	}


	public List<Rn_Menu_Group_Autofill> update_group_menu(int menu_grp_id)
	{
		return vacationRuleDao.update_group_menu(menu_grp_id);
	}
	
	public List<Rn_Menu_Group_Autofill> update_group_menu_line(int menu_grp_id)
	{
		return vacationRuleDao.update_group_menu_line(menu_grp_id);
	}
}
