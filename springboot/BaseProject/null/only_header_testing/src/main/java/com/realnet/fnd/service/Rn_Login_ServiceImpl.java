package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realnet.fnd.dao.Rn_Login_Dao;
import com.realnet.fnd.model.Rn_Main_Menu;
import com.realnet.fnd.model.Rn_Sub_Menu;
import com.realnet.fnd.model.Rn_Users;

@Service("Rn_Login_Service")
@Transactional
public class Rn_Login_ServiceImpl implements Rn_Login_Service {

	@Autowired
	Rn_Login_Dao rn_login_dao;

	
	@Override
	public List<Rn_Users> getUser(Rn_Users kwm_users) {

		return rn_login_dao.getUser(kwm_users);
	}

	@Override
	public List<Rn_Main_Menu> getMenuGroup(int profile_id) {
		// TODO Auto-generated method stub
		return rn_login_dao.getMenuGroup(profile_id);
	}

	@Override
	public List<Rn_Sub_Menu> getSubMenuLine(int menu_group_id) {
		// TODO Auto-generated method stub
		return rn_login_dao.getSubMenuLine(menu_group_id);
	}

	@Override
	public List<Rn_Main_Menu> get_user_main_menu(int user_id) {
		// TODO Auto-generated method stub
		return rn_login_dao.get_user_main_menu(user_id);
	}

	@Override
	public List<Rn_Sub_Menu> get_user_sub_menu(int main_men_id, String menu_type) 
	{
		// TODO Auto-generated method stub
		return rn_login_dao.get_user_sub_menu(main_men_id, menu_type);
	}

}
