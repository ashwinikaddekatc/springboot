package com.realnet.fnd.dao;

import java.util.List;

import com.realnet.fnd.model.Rn_Main_Menu;
import com.realnet.fnd.model.Rn_Sub_Menu;
import com.realnet.fnd.model.Rn_Users;


public interface Rn_Login_Dao {

	List<Rn_Users> getUser(Rn_Users kwm_users);

	List<Rn_Main_Menu> getMenuGroup(int profile_id);

	List<Rn_Sub_Menu> getSubMenuLine(int menu_group_id);
	
	public List<Rn_Users> list(Rn_Users kwm_users);

	List<Rn_Main_Menu> get_user_main_menu(int user_id);

	List<Rn_Sub_Menu> get_user_sub_menu(int main_men_id, String menu_type);	


}
