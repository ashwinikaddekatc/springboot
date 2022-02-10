package com.realnet.fnd.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.realnet.fnd.model.Rn_Users1;

@Transactional(readOnly = false)
public interface Rn_Reset_Password_Dao {
	
	public int CreateUser( Rn_Users1 Koel_user,int userid);

	 List<Rn_Users1> SearchUser(String user_name);
}
