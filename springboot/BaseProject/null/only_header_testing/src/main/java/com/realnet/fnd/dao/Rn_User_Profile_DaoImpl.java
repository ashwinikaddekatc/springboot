package com.realnet.fnd.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Users;


@Repository("Rn_User_Profile_Dao")
public class Rn_User_Profile_DaoImpl implements Rn_User_Profile_Dao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HibernateConfiguration hibernateConfiguration;
	
	public List<Rn_Users> getUserData(int user_id)
	{
				
		String sql="SELECT USER_ID,USER_NAME,USER_TYPE,FIRST_NAME,MIDDLE_NAME,LAST_NAME,EMAIL_ADDRESS,CONTACT_NUMBER,CREATION_DATE FROM RN_USERS_T WHERE USER_ID=";

		List<Rn_Users> user_data = jdbcTemplate.query(sql+user_id, new RowMapper<Rn_Users>() {

			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				
				Rn_Users rn_users = new Rn_Users();
				
				rn_users.setUser_id(rs.getInt("USER_ID"));
				rn_users.setUser_type(rs.getString("USER_TYPE"));
				rn_users.setUser_name(rs.getString("USER_NAME"));
				rn_users.setFirst_name(rs.getString("FIRST_NAME"));
				rn_users.setMiddle_name(rs.getString("MIDDLE_NAME"));
				rn_users.setLast_name(rs.getString("LAST_NAME"));
				rn_users.setEmail_address(rs.getString("EMAIL_ADDRESS"));
				rn_users.setCreation_date(rs.getDate("CREATION_DATE"));
				return rn_users;
				
				
			}
			
		});
		
		return user_data;
		
	}
	
	@Override
	public String updateuser(int user_id,int id,String s1)
	{
		String message=null;
					
			CallableStatement cStmt;
			try {
			cStmt = hibernateConfiguration.dataSource().getConnection()
				.prepareCall("{call RN_SP_ACT_DEACT_USER(?,?,?,?)}");
			cStmt.setInt(1, user_id );		
			cStmt.setInt(2, id );	
			cStmt.setString(3,s1 );
			cStmt.registerOutParameter(4,Types.VARCHAR);  
			

			ResultSet result = cStmt.executeQuery();
			message=cStmt.getString(4);		

				
			} catch (SQLException e) {
			e.printStackTrace();
			}
			catch (Exception e) {
			System.out.println(e.getMessage());
			}
			
	return message;
		
	}
	

	
	/////////////////////////////////////my code start from here////////////////////////////////////////////
	@Override
	public List<Rn_Users> getUsers()
	{
		
		String sql="SELECT USER_ID,USER_NAME,ATTRIBUTE1,FIRST_NAME,LAST_NAME,EMAIL_ADDRESS,CONTACT_NUMBER,IS_ACTIVE FROM RN_USERS_T";

		List<Rn_Users> user_data = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {

			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				
				Rn_Users rn_users = new Rn_Users();
				
				rn_users.setUser_id(rs.getInt("USER_ID"));
				rn_users.setUser_name(rs.getString("USER_NAME"));
				rn_users.setSource(rs.getString("ATTRIBUTE1"));
				rn_users.setFirst_name(rs.getString("FIRST_NAME"));
				rn_users.setLast_name(rs.getString("LAST_NAME"));
				rn_users.setEmail_address(rs.getString("EMAIL_ADDRESS"));
				rn_users.setIs_active(rs.getString("IS_ACTIVE"));
				return rn_users;
				
				
			}
			
		});
		
		return user_data;

	}

}
