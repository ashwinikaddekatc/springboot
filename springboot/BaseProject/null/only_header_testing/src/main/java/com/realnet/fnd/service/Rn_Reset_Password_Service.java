package com.realnet.fnd.service;

import java.util.List;

import com.realnet.fnd.model.Rn_Users1;

public interface Rn_Reset_Password_Service {
	
	public int CreateUser( Rn_Users1 Koel_user,int userid);
	
	 List<Rn_Users1> SearchUser(String user_name);
}
