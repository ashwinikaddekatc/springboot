package com.realnet.fnd.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.controller.Rn_User_Registration_Controller;
import com.realnet.fnd.model.Rn_Main_Menu;
import com.realnet.fnd.model.Rn_Sub_Menu;
import com.realnet.fnd.model.Rn_Users;

import sun.misc.BASE64Encoder;

@Repository("Rn_Login_Dao")
public class Rn_Login_DaoImpl extends AbstractDao<Integer, Rn_Users> implements Rn_Login_Dao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	HibernateTemplate hibernatetemplate;

	@Autowired
	private HibernateConfiguration hibernateConfiguration;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static final String ALGORITHM = "AES";
    public static final String KEY = "1Hbfh667adfDEJ78";
	
    /*get user*/
	@Override
	@Transactional
	public List<Rn_Users> getUser(Rn_Users kwm_users) {

		String username = kwm_users.getUser_name();
		String password = kwm_users.getPassword();
		System.out.println("login doa " + username + " " + password);
	///////////////////// edit code for check encrypted password ////////////////////////////////////
		
		 Key key=null;
		   Cipher cipher=null;
		try {
			key = generateKey();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	      
			try {
				cipher = Cipher.getInstance(Rn_User_Registration_Controller.ALGORITHM);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			}
	        try {
				cipher.init(Cipher.ENCRYPT_MODE, key);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
	        byte[] encryptedByteValue=null;
			try {
				encryptedByteValue = cipher.doFinal(password.getBytes("utf-8"));
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        String encryptedValue = new BASE64Encoder().encode(encryptedByteValue);	               
		System.out.println(encryptedValue);
	/////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
        
	      String sql = "SELECT USER_ID, USER_NAME,FIRST_NAME,LAST_NAME, PROFILE_ID,USER_TYPE  FROM rn_users_t WHERE USER_NAME = '"+kwm_users.getUser_name()+"' AND PASSWORD = '"+kwm_users.getPassword()+"' AND IS_ACTIVE = 'Y'";
		List<Rn_Users> Koel_User = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {

			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users koel_Users = new Rn_Users();
				
				koel_Users.setUser_id(rs.getInt("USER_ID"));
				koel_Users.setUser_name(rs.getString("USER_NAME"));
				koel_Users.setProfile_id(rs.getInt("PROFILE_ID"));
				koel_Users.setUser_type(rs.getString("USER_TYPE"));
				koel_Users.setFirst_name(rs.getString("FIRST_NAME"));
				koel_Users.setLast_name(rs.getString("LAST_NAME"));
				
				return koel_Users;
			}
			
		});
		
		return Koel_User;
	}

	
	/*----------- get users -------------------*/
	@Override
	public List<Rn_Users> list(Rn_Users kwm_users) {
		String sql = "SELECT USER_ID, USER_NAME,  PROFILE_ID,USER_TYPE  FROM RN_USERS_T WHERE USER_NAME = '"+kwm_users.getUser_name()+"' AND PASSWORD = '"+kwm_users.getPassword()+"'";
		List<Rn_Users> listContact = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {

			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users koel_Users = new Rn_Users();
				
				koel_Users.setUser_id(rs.getInt("USER_ID"));
				koel_Users.setUser_name(rs.getString("USER_NAME"));
				koel_Users.setProfile_id(rs.getInt("PROFILE_ID"));
				koel_Users.setUser_type(rs.getString("USER_TYPE"));
	
				return koel_Users;
			}
			
		});
		
		return listContact;
	}
	
	
	/*------------- get Menu Group ------------*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Rn_Main_Menu> getMenuGroup(int profile_id) {
		String sql = "SELECT MAIN_MENU_ID, PROFILE_ID, "
				+ "MAIN_MENU_NAME, MAIN_MENU_CTRLR_NAME, MAIN_MENU_ACTION_NAME,"
				+ " MAIN_MENU_ICON FROM rn_main_menu_t KMN WHERE KMN.PROFILE_ID = "
				+ " "+Integer.toString(profile_id)+" ORDER BY KMN.MAIN_MENU_ID";
		
		List<Rn_Main_Menu> kwm_menu_groups = jdbcTemplate.query(sql, new RowMapper<Rn_Main_Menu>() {

			@Override
			public Rn_Main_Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Main_Menu kwm_menu_group = new Rn_Main_Menu();
				
				kwm_menu_group.setMain_menu_id(rs.getInt("MAIN_MENU_ID"));
				kwm_menu_group.setMain_menu_name(rs.getString("MAIN_MENU_NAME"));
				kwm_menu_group.setMain_menu_action_name(rs.getString("MAIN_MENU_ACTION_NAME"));
				kwm_menu_group.setMain_menu_icon(rs.getString("MAIN_MENU_ICON"));

				System.out.println(kwm_menu_group.getMain_menu_name());

				return kwm_menu_group;
			}
		});

		return kwm_menu_groups;

	}

	
	/*------------- get Sub Menu Line -----------------*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Rn_Sub_Menu> getSubMenuLine(int menu_group_id) {
		ArrayList<Rn_Sub_Menu> list=new ArrayList<Rn_Sub_Menu>();
		String sql="SELECT SUB_MENU_ID, MAIN_MENU_ID, SUB_MENU_NAME,"
				+ " SUB_MENU_CTRLR_NAME, SUB_MENU_ACTION_NAME, "
				+ "SUB_MENU_ICON FROM rn_sub_menu_t KSM WHERE MAIN_MENU_ID"
				+ "= "+Integer.toString(menu_group_id)+" "
						+ "ORDER BY KSM.SUB_MENU_ID";

		List<Rn_Sub_Menu> kwm_menu_group_lines = jdbcTemplate.query(sql, new RowMapper<Rn_Sub_Menu>() {

			@Override
			public Rn_Sub_Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Sub_Menu kwm_menu_group_line = new Rn_Sub_Menu();
				
				kwm_menu_group_line.setSub_menu_id(rs.getInt("SUB_MENU_ID"));
				kwm_menu_group_line.setMain_menu_id(rs.getInt("MAIN_MENU_ID"));
				kwm_menu_group_line.setSub_menu_name(rs.getString("SUB_MENU_NAME"));
				kwm_menu_group_line.setSub_menu_action_name(rs.getString("SUB_MENU_ACTION_NAME"));
				
				System.out.println(kwm_menu_group_line.getSub_menu_name());

				return kwm_menu_group_line;
			}
		});

		
		
		return kwm_menu_group_lines;
	}
	
	/*------------- Encrypt password -------------- */
	private static Key generateKey() throws Exception 
    {
        Key key = new SecretKeySpec(Rn_User_Registration_Controller.KEY.getBytes(),Rn_User_Registration_Controller.ALGORITHM);
        return key;
    }
	
	/*--------- get user main menu -----------*/
	@Override
	public List<Rn_Main_Menu> get_user_main_menu(int user_id) {
		
		List<Rn_Main_Menu> list=new ArrayList<Rn_Main_Menu>();
        CallableStatement cStmt;
        try {
        cStmt = hibernateConfiguration.dataSource().getConnection()
        		.prepareCall("{call RN_SP_DERIVE_MAIN_MENU(?)}");
		cStmt.setInt(1, user_id );
        ResultSet result = cStmt.executeQuery();
       
 		ResultSet rs1 = (ResultSet) cStmt.getObject(1);
 		while (rs1.next()) {
 			
			Rn_Main_Menu kwm_menu_group = new Rn_Main_Menu();
			
			kwm_menu_group.setMain_menu_id(rs1.getInt("MAIN_MENU_ID"));
			kwm_menu_group.setMain_menu_name(rs1.getString("MAIN_MENU_NAME"));
			kwm_menu_group.setMain_menu_action_name(rs1.getString("MAIN_MENU_ACTION_NAME"));
			kwm_menu_group.setMain_menu_icon(rs1.getString("MAIN_MENU_ICON"));
			kwm_menu_group.setMenu_type(rs1.getString("MENU_TYPE"));
			System.out.println(kwm_menu_group.getMain_menu_name());


 			
			list.add(kwm_menu_group);
 		}
        } catch (SQLException e) {
        e.printStackTrace();
        }
        catch (Exception e) {
        System.out.println(e.getMessage());
        }
        return list;
	}

	/*--------- get user sub menu -----------*/
	@Override
	public List<Rn_Sub_Menu> get_user_sub_menu(int main_men_id, String menu_type) {
		
		List<Rn_Sub_Menu> list=new ArrayList<Rn_Sub_Menu>();
        CallableStatement cStmt;
        try {
        cStmt = hibernateConfiguration.dataSource().getConnection()
        		.prepareCall("{call RN_SP_DERIVE_SUB_MENU(?,?)}");
		cStmt.setInt(1, main_men_id );
		cStmt.setString(2, menu_type );
        ResultSet result = cStmt.executeQuery();
       
 		ResultSet rs1 = (ResultSet) cStmt.getObject(2);
 		while (rs1.next()) {
 			
 			Rn_Sub_Menu kwm_menu_group_line = new Rn_Sub_Menu();
			
			kwm_menu_group_line.setSub_menu_id(rs1.getInt("SUB_MENU_ID"));
			kwm_menu_group_line.setMain_menu_id(rs1.getInt("MAIN_MENU_ID"));
			kwm_menu_group_line.setSub_menu_name(rs1.getString("SUB_MENU_NAME"));
			kwm_menu_group_line.setSub_menu_action_name(rs1.getString("SUB_MENU_ACTION_NAME"));
			
			System.out.println(kwm_menu_group_line.getSub_menu_name());


 			
			list.add(kwm_menu_group_line);
 		}
        } catch (SQLException e) {
        e.printStackTrace();
        }
        catch (Exception e) {
        System.out.println(e.getMessage());
        }
        return list;
	}


}
