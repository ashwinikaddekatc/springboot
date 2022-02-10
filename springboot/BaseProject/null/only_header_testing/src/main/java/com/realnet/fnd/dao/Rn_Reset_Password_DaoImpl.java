package com.realnet.fnd.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.realnet.fnd.model.Rn_Users1;

@Repository("Rn_Reset_Password_Dao")
public class Rn_Reset_Password_DaoImpl  implements Rn_Reset_Password_Dao {
	
	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int CreateUser(Rn_Users1 Koel_user,int userid) {
		int userid1=Koel_user.getUser_id();
		
		if(userid1==0)
		{
		hibernateTemplate.saveOrUpdate(Koel_user);
		}
		else
		{
			Koel_user.setUser_id(userid);
			hibernateTemplate.saveOrUpdate(Koel_user);
		
		}
		return Koel_user.getUser_id();
	}
	
	public List<Rn_Users1> SearchUser(String user_name)
	 {
		 System.out.println("in dao");

		 String name;
		 
		 ArrayList<Rn_Users1> dml = (ArrayList<Rn_Users1>)hibernateTemplate.find("FROM Koel_Users1 where user_name =?",user_name);
		
		 int len =dml.size();
		 System.out.println("in dao="+len);
		 for(int i=0; i<dml.size(); i++)
			{
				name = dml.get(0).getUser_name();
				System.out.println("user name exist"+name);				
				System.out.println("user name  "+name);
				
			}
		return dml;
	 }

}