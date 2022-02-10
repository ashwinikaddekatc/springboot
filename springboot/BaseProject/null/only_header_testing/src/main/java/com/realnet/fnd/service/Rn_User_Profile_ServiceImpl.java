package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_User_Profile_Dao;
import com.realnet.fnd.model.Rn_Users;

@Service
public class Rn_User_Profile_ServiceImpl implements Rn_User_Profile_Service {

	@Autowired
	private Rn_User_Profile_Dao rn_user_profile_dao; 


	public List<Rn_Users> getUserData(int user_id) {
		return rn_user_profile_dao.getUserData(user_id);
	}

	@Override
	public List<Rn_Users> getUsers() {
		return rn_user_profile_dao.getUsers();
	}

	@Override
	public String updateuser(int user_id, int id, String s1) {
		return rn_user_profile_dao.updateuser(user_id, id, s1);

	}
}
