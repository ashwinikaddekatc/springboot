package com.realnet.fnd.service;

import java.util.List;

import com.realnet.fnd.model.Rn_Main_Menu;
import com.realnet.fnd.model.Rn_Sub_Menu;
import com.realnet.fnd.model.Rn_Users;

public interface Rn_Login_Service {

	public List<Rn_Users> getUser(Rn_Users kwm_users);

	public List<Rn_Main_Menu> getMenuGroup(int profile_id);

	public List<Rn_Sub_Menu> getSubMenuLine(int menu_group_id);
	
	List<Rn_Main_Menu> get_user_main_menu(int user_id);

	List<Rn_Sub_Menu> get_user_sub_menu(int main_men_id, String menu_type);	


}
