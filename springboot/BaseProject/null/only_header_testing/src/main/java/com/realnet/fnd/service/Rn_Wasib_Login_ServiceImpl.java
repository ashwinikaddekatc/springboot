package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realnet.fnd.dao.Rn_Wasib_Login_Dao;
import com.realnet.fnd.model.Rn_Main_Menu;
import com.realnet.fnd.model.Rn_Sub_Menu;
import com.realnet.fnd.model.Rn_Users;

@Service("Rn_Wasib_Login_Service")
@Transactional
public class Rn_Wasib_Login_ServiceImpl implements Rn_Wasib_Login_Service {

	@Autowired
	Rn_Wasib_Login_Dao rn_wasib_login_dao;

	@Override
	public List<Rn_Users> getUser(Rn_Users kwm_users) {

		return rn_wasib_login_dao.getUser(kwm_users);
	}

	@Override
	public List<Rn_Main_Menu> getMenuGroup(int profile_id) {
		return rn_wasib_login_dao.getMenuGroup(profile_id);
	}

	@Override
	public List<Rn_Sub_Menu> getSubMenuLine(int menu_group_id) {
		return rn_wasib_login_dao.getSubMenuLine(menu_group_id);
	}

	@Override
	public List<Rn_Main_Menu> get_user_main_menu(int user_id) {
		return rn_wasib_login_dao.get_user_main_menu(user_id);
	}

	@Override
	public List<Rn_Sub_Menu> get_user_sub_menu(int main_men_id, String menu_type) 
	{
		return rn_wasib_login_dao.get_user_sub_menu(main_men_id, menu_type);
	}

}
