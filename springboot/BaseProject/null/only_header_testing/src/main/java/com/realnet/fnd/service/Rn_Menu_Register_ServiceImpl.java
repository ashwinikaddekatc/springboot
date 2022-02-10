package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Menu_Register_Dao;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Lookup_Autofill;

@Service
public class Rn_Menu_Register_ServiceImpl implements Rn_Menu_Register_Service {
	
	@Autowired
	Rn_Menu_Register_Dao menuRegisterDao;
	
	@Override
	public List<Rn_Lookup_Autofill> MenuRegister_List() {
		return menuRegisterDao.MenuRegister_List();
	}

	@Override
	public int addMenuGroupHeader(Rn_Menu_Group_Header menuHeader) {
		return menuRegisterDao.addMenuGroupHeader(menuHeader);
	}

	@Override
	public int addMenuGroupLine(int rowcount, int group_header_id, String[] group_line_id, String[] name,
			String[] active) {
		return menuRegisterDao.addMenuGroupLine(rowcount, group_header_id, group_line_id, name, active);
	}
	@Override
	public List<Rn_Lookup_Autofill> Menu_List(int SR_num) {
		return menuRegisterDao.Menu_List(SR_num);
	}
	
	
	@Override
	public int addMenuGroupHeader1(Rn_Menu_Group_Header groupHeader)
	{
	return menuRegisterDao.addMenuGroupHeader1(groupHeader);
	}
	
	
	@Override
	public int addMenuGroupLine1(int rowcount, int group_header_id, String[] menu_group_line_id, String[] name,
			String[] active)
	{
	return menuRegisterDao.addMenuGroupLine1(rowcount,group_header_id,menu_group_line_id,name,active);
	}

}
