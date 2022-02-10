package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.realnet.fnd.model.Rn_OTP;
import com.realnet.fnd.model.Rn_Users;

@Repository("Rn_Forgot_Password_Dao")
public class Rn_Forgot_Password_DaoImpl implements Rn_Forgot_Password_Dao 
{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
int i=1;

	@Override
	public List<Rn_Users> ForgotPassword_List(String user_name)  {
		System.out.println("daoooooooooo");
		Rn_Users rn_users=new Rn_Users();
		String sql = "select EMAIL_ADDRESS,password,user_id from RN_USERS_T where user_name='"+user_name+"'";
	
		List<Rn_Users> rn_users2 = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {			
			
			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users rn_users1 = new Rn_Users();
				
				 i++;
				
				 rn_users1.setEmail_address(rs.getString("EMAIL_ADDRESS"));
				 rn_users1.setUser_id(rs.getInt("USER_ID"));
				 rn_users1.setPassword(rs.getString("PASSWORD"));
					
					
				
				return rn_users1;
			}
					
		});
		
		if(i==1)
		{
			rn_users.setPassword("no_password");
			rn_users.setEmail_address("no_email");
		
			
		}
		rn_users2.add(rn_users);
		
		System.out.println("forgot password run");
		return rn_users2;
	}
	
	
	
	
	@Override
	public List<Rn_OTP> otp_report(int otp_id) 
	{
		System.out.println("----------------upadte-----------------------");

		System.out.println("dao id  "+otp_id);
		String sql="SELECT OTP_ID FROM RN_OTP_T WHERE OTP_ID="+otp_id+"";
		System.out.println("dao id  "+sql);
		List<Rn_OTP> rn_otp = jdbcTemplate.query(sql, new RowMapper<Rn_OTP>() {
					@Override
					public Rn_OTP mapRow(ResultSet rs, int rowNum) throws SQLException {
						Rn_OTP rn_otp_t = new Rn_OTP();
						System.out.println("==============NEW_VIEW_RPORT STARTED===============");
						rn_otp_t.setOtp_id(rs.getInt("OTP_ID"));
						return rn_otp_t;
					}
					
				});
				for (int i = 0; i < rn_otp.size(); i++) {
					System.out.println("asdfhjasdhfjksadf  "+rn_otp.get(i));
				}
				return rn_otp;
	}
	
	
	
	
	
	@Override
	@Transactional
	public List<Rn_OTP> getUser3(int otp_id,int phone_otp,int email_otp) 
	{
		Rn_OTP rn_otp= new Rn_OTP();
		
	      String sql = "SELECT USER_ID FROM RN_OTP_T WHERE OTP_ID = '"+rn_otp.getOtp_id()+"'AND PHONE_OTP='"+rn_otp.getPhone_otp()+"' AND EMAIL_OTP='"+rn_otp.getEmail_otp()+"' ";
		  List<Rn_OTP> rn_otp_t = jdbcTemplate.query(sql, new RowMapper<Rn_OTP>() {

			@Override
			public Rn_OTP mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_OTP rn_otp = new Rn_OTP();
				
				rn_otp.setOtp_id(rs.getInt("OTP_ID"));
				rn_otp.setPhone_otp(rs.getString("PHONE_OTP"));
				rn_otp.setEmail_otp(rs.getString("EMAIL_OTP"));
				
				return rn_otp;
			}
			
		});
		
		return rn_otp_t;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public List<Rn_OTP> getUid() 
	{
		System.out.println("----------------upadte-----------------------");
		int u_id=61;
		System.out.println("dao id  "+u_id);
		String sql = "SELECT USER_ID,PHONE_OTP,EMAIL_OTP FROM RN_OTP_T WHERE OTP_ID = "+u_id+"";
		List<Rn_OTP> rn_userlist = jdbcTemplate.query(sql, new RowMapper<Rn_OTP>() {
					@Override
					public Rn_OTP mapRow(ResultSet rs, int rowNum) throws SQLException {
						Rn_OTP rn = new Rn_OTP();
						System.out.println("==============NEW_VIEW_RPORT STARTED===============");
						
						rn.setUser_id(rs.getInt("USER_ID"));
						rn.setPhone_otp(rs.getString("PHONE_OTP"));
						rn.setEmail_otp(rs.getString("EMAIL_OTP"));
						return rn;
					}
					
				});
				for (int i = 0; i < rn_userlist.size(); i++) {
					System.out.println("asdfhjasdhfjksadf  "+rn_userlist.get(i));
				}
				return rn_userlist;
	}
	
	
	
	
	
	
	@Override
	@Transactional
    public int resetpass(int rowcount, String[] user_id, String[] user_name,String[] password) 
	{

		int infonum = 0;
		
		
		for (int i = 0; i < rowcount; i++) {
			
				Rn_Users rn = new Rn_Users();
				if (user_id != null && user_id.length > 0) {
					infonum = Integer.parseInt(user_id[i]);
				
				} 
				else 
				{
					infonum = rn.getUser_id();
				}

				
				rn.setUser_id(infonum);
				rn.setPassword(password[i]);
				

				hibernateTemplate.saveOrUpdate(rn);
			}
		
		return 1;
	}
	

}
