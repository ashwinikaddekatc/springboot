package com.realnet.fnd.dao;

import java.util.List;

import com.realnet.fnd.model.Rn_Users;

public interface Rn_User_Profile_Dao {
	
	public List<Rn_Users> getUserData(int user_id);

	public List<Rn_Users> getUsers();
	
	public String updateuser(int user_id, int id,String s1);

}
