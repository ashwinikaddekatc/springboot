package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Reset_Password_Dao;
import com.realnet.fnd.model.Rn_Users1;

@Service
public class Rn_Reset_Password_ServiceImpl implements Rn_Reset_Password_Service {
	
	@Autowired
	private Rn_Reset_Password_Dao rn_reset_password_dao;
	

	public int CreateUser( Rn_Users1 Koel_user,int userid)
	{
		return rn_reset_password_dao.CreateUser(Koel_user,userid);
	}
	
	public List<Rn_Users1> SearchUser(String user_name)
	 {
		 return rn_reset_password_dao.SearchUser(user_name);
	 }
}
