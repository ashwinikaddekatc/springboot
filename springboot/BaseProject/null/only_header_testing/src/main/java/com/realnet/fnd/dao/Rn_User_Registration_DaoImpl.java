package com.realnet.fnd.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.cfg.Environment;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_OTP;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.wfb.model.Rn_Fb_Header;
import com.realnet.wfb.model.Rn_Fb_Line;

@Repository("Rn_User_Registration_Dao")
public class Rn_User_Registration_DaoImpl implements Rn_User_Registration_Dao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Environment environment;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HibernateConfiguration hibernateConfiguration;

	@Override
	public int createUser(Rn_Users user, int userid) {
		System.out.println("======================DAO++userregistration dao//methode==CreateUser======================");
		int userid1 = user.getUser_id();

		if (userid1 == 0) {
			hibernateTemplate.saveOrUpdate(user);
		} else {
			user.setUser_id(userid);
			hibernateTemplate.saveOrUpdate(user);

		}
		return user.getUser_id();
	}

	public List<Rn_Users> SearchUser(String user_name) {
		System.out.println("======================DAO++userregistration/SearchUser==============");
		System.out.println("in dao");

		String name = null;

		ArrayList<Rn_Users> rn_users = (ArrayList<Rn_Users>) hibernateTemplate.find("FROM RN_USERS_T where USER_NAME =?",
				user_name);

		int len = rn_users.size();
		System.out.println("in dao=" + len);
		for (int i = 0; i < rn_users.size(); i++) {
			name = rn_users.get(0).getUser_name();

			System.out.println("user name  " + name);
			System.out.println("Password " + rn_users.get(0).getPassword());
			System.out.println("Email  " + rn_users.get(0).getEmail_address());
			System.out.println("user name  " + name);
			System.out.println("user name sujit  " + rn_users.get(0).getIs_active());

		}
		return rn_users;
	}

	public List<Rn_Lookup_Autofill> findrole(int newuserId) {
		System.out.println("======================DAO+++userregistration/findrole==============");

		String name = "Roles";
		String sql = "SELECT USER_ROLE_ASSIGNMENT_ID,USER_ID,ROLE,ENABLE_FLAG,END_DATE FROM RN_USER_ROLE_ASSIGNMENTS_T WHERE USER_ID ='"
				+ newuserId + "'";

		List<Rn_Lookup_Autofill> rn_lookup_autofill_list = jdbcTemplate.query(sql, new RowMapper<Rn_Lookup_Autofill>() {

			@Override
			public Rn_Lookup_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {

				System.out.println("in dao impl");

				Rn_Lookup_Autofill rn_lookup_autofill = new Rn_Lookup_Autofill();

				rn_lookup_autofill.setUser_role_assignment_id(rs.getInt("USER_ROLE_ASSIGNMENT_ID"));
				rn_lookup_autofill.setUser_id(rs.getInt("USER_ID"));
				rn_lookup_autofill.setRole(rs.getString("ROLE"));
				rn_lookup_autofill.setEnable_flag(rs.getString("ENABLE_FLAG"));

				if (rs.getDate("END_DATE") != null) {
					DateFormat ser33 = new SimpleDateFormat("dd-MM-yyyy");
					String dateStr33 = ser33.format(rs.getDate("END_DATE"));
					rn_lookup_autofill.setEnd_date(dateStr33);
					try {
						System.out.println(ser33.parse(rs.getString("END_DATE")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println("datttttttttttttttttttttt=" + rn_lookup_autofill.getEnd_date());
				}

				return rn_lookup_autofill;
			}

		});

		return rn_lookup_autofill_list;
	}

	

	@Override
	public int CreateUser2(Rn_OTP rn_otp, int id) {
		int id1 = rn_otp.getOtp_id();
		if (id1 == 0) {
			hibernateTemplate.save(rn_otp);
		} else {
			rn_otp.setOtp_id(id);
			hibernateTemplate.save(rn_otp);
		}

		return rn_otp.getOtp_id();
	}

	@Override
	public List<Rn_Ext_Fields> ext_userlist() {
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,EXT_DEPENDENT_ID,CALCULATED_FIELD,ADD_TO_GRID,SP_NAME,EXT_DD_ID,SP_NAME_FOR_AUTOCOMPLETE,EXT_AUTO_ID,RADIO,RADIO_OPTION FROM RN_EXT_FIELD_T WHERE  FORM_CODE= 'insertfield'";

		List<Rn_Ext_Fields> rn_ext_fields_list = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields = new Rn_Ext_Fields();

				rn_ext_fields.setId(rs.getInt("ID"));
				rn_ext_fields.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields.setMapping(rs.getString("MAPPING"));
				rn_ext_fields.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields.setReadonly(rs.getString("READONLY"));

				rn_ext_fields.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields.setExt_dd_id(rs.getString("EXT_DD_ID"));
				rn_ext_fields.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));
				rn_ext_fields.setExt_auto_id(rs.getString("EXT_AUTO_ID"));
				rn_ext_fields.setRadio(rs.getString("RADIO"));
				rn_ext_fields.setRadio_option(rs.getString("RADIO_OPTION"));

				return rn_ext_fields;
			}

		});

		return rn_ext_fields_list;
	}

	@Override
	public List<Rn_Ext_Fields> ext_userlist_non_radio() {
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,EXT_DEPENDENT_ID,CALCULATED_FIELD,ADD_TO_GRID,SP_NAME,EXT_DD_ID,SP_NAME_FOR_AUTOCOMPLETE,EXT_AUTO_ID,RADIO,RADIO_OPTION FROM RN_EXT_FIELD_T WHERE DATA_TYPE!='radiobutton' AND FORM_CODE= 'insertfield'";

		List<Rn_Ext_Fields> rn_ext_fields_list = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields = new Rn_Ext_Fields();

				rn_ext_fields.setId(rs.getInt("ID"));
				rn_ext_fields.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields.setMapping(rs.getString("MAPPING"));
				rn_ext_fields.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields.setReadonly(rs.getString("READONLY"));

				rn_ext_fields.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields.setExt_dd_id(rs.getString("EXT_DD_ID"));
				rn_ext_fields.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));
				rn_ext_fields.setExt_auto_id(rs.getString("EXT_AUTO_ID"));
				rn_ext_fields.setRadio(rs.getString("RADIO"));
				rn_ext_fields.setRadio_option(rs.getString("RADIO_OPTION"));

				return rn_ext_fields;
			}

		});

		return rn_ext_fields_list;
	}

	@Override
	public List<Rn_Ext_Fields> ext_userlist_radio() {
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,EXT_DEPENDENT_ID,CALCULATED_FIELD,ADD_TO_GRID,SP_NAME,EXT_DD_ID,SP_NAME_FOR_AUTOCOMPLETE,EXT_AUTO_ID,RADIO,RADIO_OPTION FROM RN_EXT_FIELD_T WHERE DATA_TYPE='radiobutton' AND FORM_CODE= 'insertfield'";

		List<Rn_Ext_Fields> rn_ext_fields_list = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields = new Rn_Ext_Fields();

				rn_ext_fields.setId(rs.getInt("ID"));
				rn_ext_fields.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields.setMapping(rs.getString("MAPPING"));
				rn_ext_fields.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields.setReadonly(rs.getString("READONLY"));

				rn_ext_fields.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields.setExt_dd_id(rs.getString("EXT_DD_ID"));
				rn_ext_fields.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));
				rn_ext_fields.setExt_auto_id(rs.getString("EXT_AUTO_ID"));
				rn_ext_fields.setRadio(rs.getString("RADIO"));
				rn_ext_fields.setRadio_option(rs.getString("RADIO_OPTION"));

				return rn_ext_fields;
			}

		});

		return rn_ext_fields_list;
	}

	@Override
	public List<Rn_Ext_Fields> ext_userlist1(String f_code) {
		System.out.println("value of form code---------" + f_code);

		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,EXT_DEPENDENT_ID,CALCULATED_FIELD,ADD_TO_GRID,SP_NAME,EXT_DD_ID,SP_NAME_FOR_AUTOCOMPLETE,EXT_AUTO_ID FROM RN_EXT_FIELD_T WHERE (TYPE='HEADER' OR TYPE='LINE')  AND FORM_CODE= '"
				+ f_code + "'";

		List<Rn_Ext_Fields> rn_ext_fields_list = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields = new Rn_Ext_Fields();

				rn_ext_fields.setId(rs.getInt("ID"));
				rn_ext_fields.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields.setMapping(rs.getString("MAPPING"));
				rn_ext_fields.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields.setReadonly(rs.getString("READONLY"));

				rn_ext_fields.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields.setExt_dd_id(rs.getString("EXT_DD_ID"));
				rn_ext_fields.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));
				rn_ext_fields.setExt_auto_id(rs.getString("EXT_AUTO_ID"));

				return rn_ext_fields;
			}

		});

		return rn_ext_fields_list;
	}

	@Override
	public List<Rn_Ext_Fields> ext_userlist2(String f_code) {
		System.out.println("value of form code---------" + f_code);

		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,TYPE, MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,EXT_DEPENDENT_ID,CALCULATED_FIELD,ADD_TO_GRID,SP_NAME,EXT_DD_ID,SP_NAME_FOR_AUTOCOMPLETE,EXT_AUTO_ID FROM RN_EXT_FIELD_T WHERE TYPE='HEADER'  AND FORM_CODE= '"
				+ f_code + "'";

		List<Rn_Ext_Fields> rn_ext_fields_list = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields = new Rn_Ext_Fields();

				rn_ext_fields.setId(rs.getInt("ID"));
				rn_ext_fields.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields.setMapping(rs.getString("MAPPING"));
				rn_ext_fields.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields.setReadonly(rs.getString("READONLY"));

				rn_ext_fields.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields.setExt_dd_id(rs.getString("EXT_DD_ID"));
				rn_ext_fields.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));
				rn_ext_fields.setExt_auto_id(rs.getString("EXT_AUTO_ID"));

				rn_ext_fields.setType(rs.getString("TYPE"));
				
				return rn_ext_fields;
			}

		});

		return rn_ext_fields_list;
	}

	@Override
	public List<Rn_Ext_Fields> ext_userlist3(String f_code) {
		System.out.println("value of form code---------" + f_code);

		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,TYPE,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,EXT_DEPENDENT_ID,CALCULATED_FIELD,ADD_TO_GRID,SP_NAME,EXT_DD_ID,SP_NAME_FOR_AUTOCOMPLETE,EXT_AUTO_ID FROM RN_EXT_FIELD_T WHERE TYPE='LINE' AND FORM_CODE= '"
				+ f_code + "'";

		List<Rn_Ext_Fields> rn_ext_fields_list = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields = new Rn_Ext_Fields();

				rn_ext_fields.setId(rs.getInt("ID"));
				rn_ext_fields.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields.setMapping(rs.getString("MAPPING"));
				rn_ext_fields.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields.setReadonly(rs.getString("READONLY"));

				rn_ext_fields.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields.setExt_dd_id(rs.getString("EXT_DD_ID"));
				rn_ext_fields.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));
				rn_ext_fields.setExt_auto_id(rs.getString("EXT_AUTO_ID"));
				rn_ext_fields.setType(rs.getString("TYPE"));
				return rn_ext_fields;
			}

		});

		return rn_ext_fields_list;
	}

	public String writeViewData() {

		Session session = sessionFactory.getCurrentSession();

		StringBuilder form_view = new StringBuilder();

		form_view.append("HELLO");
		String ViewName = "insertFields";
		String path1 = environment.getRequiredProperty("JspPath");
		String tomcatRoot1 = request.getServletContext().getRealPath("/WEB-INF/jsp");
		System.out.println(" tomcatRoot path=" + new File(tomcatRoot1).getAbsolutePath());

		File file4 = new File(tomcatRoot1 + "/" + ViewName + "_ext.jsp");

		if (!file4.exists()) {
			try {
				file4.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw4 = null;
		;
		try {
			fw4 = new FileWriter(file4.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw4 = new BufferedWriter(fw4);
		try {
			bw4.write(form_view.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			bw4.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");

		System.out.println("path=" + new File(path1).getAbsolutePath());
		File file5 = new File(path1 + "/" + ViewName + "_ext.jsp");

		if (!file5.exists()) {
			try {
				file5.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileWriter fw5 = null;
		;
		try {
			fw5 = new FileWriter(file5.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw5 = new BufferedWriter(fw5);
		try {
			bw5.write(form_view.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bw5.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
		System.out.println("advance search view Done");

		return null;

	}

	// --------code for header & line table autocomplete-----------

	public List<Rn_Fb_Header> get_Autocompelete(String itemvalauto)

	{
		List<Rn_Fb_Header> autodata = new ArrayList<Rn_Fb_Header>();
		System.out.println("term in doa" + itemvalauto);

		String para = itemvalauto.toUpperCase() + "%";
		System.out.println(para);

		ArrayList<String> list = new ArrayList<String>();
		String data;
		int data2 = 0;
		try {
			CallableStatement Stmt = hibernateConfiguration.dataSource().getConnection()
					.prepareCall("{call sp_autocomplete(?)}");

			Stmt.setString(1, para);

			ResultSet result2 = Stmt.executeQuery();
			ResultSet rs12 = (ResultSet) Stmt.getObject(1);
			autodata.clear();
			while (rs12.next()) {
				data = rs12.getString(1);
				System.out.println("data" + data);
				data2++;
				System.out.println("id" + data2);
				autodata.add(new Rn_Fb_Header());
				list.add(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return autodata;

	}

	@Override
	public List<Rn_Users> rn_reportlist(String id) {

		System.out.println("dao start::\nSql query in Dao::" + id);

		String sql = id;

		List<Rn_Users> rn_userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {

			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users rn = new Rn_Users();

				rn.setProfile_id(rs.getInt("PROFILE_ID"));
				rn.setUser_name(rs.getString("USER_NAME"));
				rn.setFirst_name(rs.getString("FIRST_NAME"));
				
				rn.setField_name(rs.getString("FIELD_NAME"));
				rn.setForm_code(rs.getString("FORM_CODE"));

				return rn;
			}

		});

		return rn_userlist;
	}

	@Override
	public List<Rn_Users> rn_reportlist() {

		String sql = "SELECT PROFILE_ID,USER_NAME,FIRST_NAME,MIDDLE_NAME,LAST_NAME,CONTACT_NUMBER,EMAIL_ADDRESS,START_DATE,END_DATE FROM RN_USERS_T ";
		List<Rn_Users> rn_users_list = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {

			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users rn_users = new Rn_Users();

				rn_users.setProfile_id(rs.getInt("PROFILE_ID"));
				rn_users.setUser_name(rs.getString("USER_NAME"));
				rn_users.setFirst_name(rs.getString("FIRST_NAME"));
				rn_users.setMiddle_name(rs.getString("MIDDLE_NAME"));
				rn_users.setLast_name(rs.getString("LAST_NAME"));
				rn_users.setContact_number(rs.getString("CONTACT_NUMBER"));
				rn_users.setEmail_address(rs.getString("EMAIL_ADDRESS"));
				rn_users.setStart_date(rs.getDate("START_DATE"));
				rn_users.setEnd_date(rs.getDate("END_DATE"));

				return rn_users;
			}

		});

		return rn_users_list;
	}

	


	@Override
	public List<Rn_Fb_Line> property_field(int f_id) {
		System.out.println("----------------upadte-----------------------");
		System.out.println("dao id  " + f_id);
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE FROM rn_fb_lines_t WHERE ID ="
				+ f_id + "";

		List<Rn_Fb_Line> rn_fb_line_list = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Line>() {

			@Override
			public Rn_Fb_Line mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Line rn_fb_line = new Rn_Fb_Line();

				rn_fb_line.setId(rs.getInt("ID"));
				rn_fb_line.setField_name(rs.getString("FIELD_NAME"));
				rn_fb_line.setMapping(rs.getString("MAPPING"));
				rn_fb_line.setData_type(rs.getString("DATA_TYPE"));

				rn_fb_line.setType_field(rs.getString("TYPE_FIELD"));

				rn_fb_line.setForm_code(rs.getString("FORM_CODE"));

				rn_fb_line.setKey1(rs.getString("KEY1"));
				rn_fb_line.setType1(rs.getString("TYPE1"));

				rn_fb_line.setMandatory(rs.getString("MANDATORY"));
				rn_fb_line.setHidden(rs.getString("HIDDEN"));
				rn_fb_line.setReadonly(rs.getString("READONLY"));

				rn_fb_line.setDependent(rs.getString("DEPENDENT"));
				rn_fb_line.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_fb_line.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_fb_line.setDependent_sp_param(rs.getString("DEPENDENT_SP_PARAM"));

				rn_fb_line.setSequence(rs.getString("SEQUENCE"));
				rn_fb_line.setSeq_name(rs.getString("SEQ_NAME"));
				rn_fb_line.setSeq_sp(rs.getString("SEQ_SP"));
				rn_fb_line.setSeq_sp_param(rs.getString("SEQ_SP_PARAM"));

				rn_fb_line.setValidation_1(rs.getString("VALIDATION_1"));
				rn_fb_line.setVal_type(rs.getString("VAL_TYPE"));
				rn_fb_line.setVal_sp(rs.getString("VAL_SP"));
				rn_fb_line.setVal_sp_param(rs.getString("VAL_SP_PARAM"));

				rn_fb_line.setDefault_1(rs.getString("DEFAULT_1"));
				rn_fb_line.setDefault_type(rs.getString("DEFAULT_TYPE"));
				rn_fb_line.setDefault_value(rs.getString("DEFAULT_VALUE"));
				rn_fb_line.setDefault_sp(rs.getString("DEFAULT_SP"));
				rn_fb_line.setDefault_sp_param(rs.getString("DEFAULT_SP_PARAM"));

				rn_fb_line.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_fb_line.setCal_sp(rs.getString("CAL_SP"));
				rn_fb_line.setCal_sp_param(rs.getString("CAL_SP_PARAM"));

				rn_fb_line.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				
				rn_fb_line.setSp_for_autocomplete(rs.getString("SP_FOR_AUTOCOMPLETE"));
				
				rn_fb_line.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));

				rn_fb_line.setSp_name_for_dropdown(rs.getString("SP_NAME_FOR_DROPDOWN"));

				return rn_fb_line;
			}

		});

		return rn_fb_line_list;

	}

	@Override
	@Transactional
	public int addFields1(Rn_Fb_Line rn_fb_line) {
		hibernateTemplate.saveOrUpdate(rn_fb_line);
		System.out.println(rn_fb_line.getId());
		return rn_fb_line.getId();
	}

	@Override
	public int addtables(int rowcount, int report_id, String[] tables_id, String[] table_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addcolumns(int rowcount2, int report_id, String[] column_id, String[] column_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addmenuGroupLine(int rowcount, int id, String[] tables_id, String[] table_name,
			String[] table_allias_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addmenuGroupLine2(int rowcount2, int id, String[] column_id, String[] column_name,
			String[] table_allies_name, String[] functions, String[] column_alias_name_query, String[] desc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addmenuGroupLine3(int rowcount3, int id, String[] where_id, String[] explecity,
			String[] table_column_name1, String[] column1, String[] condition, String[] switch_control,
			String[] table_column_name2, String[] column2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addmenuGroupLine4(int rowcount4, int id, String[] date_id, String[] table_allias_name3,
			String[] column_name3, String[] column_allias_name3) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addmenuGroupLine5(int rowcount5, int id, String[] adhoc_id, String[] table_allias_name4,
			String[] column_name4, String[] column_allias_name4) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addmenuGroupLine6(int rowcount6, int id, String[] std_id, String[] table_alias_name,
			String[] column_name5, String[] field_type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUpdate(int id, String[] date_string, String[] report_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUpdate2(int id, String[] date_string, String[] report_name, String[] add_param_string) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUpdate3(int id, String[] date_string, String[] report_name, String[] add_param_string,
			String[] grid_headers, String[] grid_values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUpdateReport3(int id, String[] report_name, String[] description, String[] report_tags,
			String[] date_string, String[] add_param_string, String[] master_select, String[] grid_headers,
			String[] std_param_view, String[] grid_values, String[] model_string, String[] query) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}