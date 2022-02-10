package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Lookup_Values;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Test12;
import com.realnet.fnd.model.Rn_Two_Jsp;
import com.realnet.fnd.model.Rn_User_Assignment;
import com.realnet.fnd.model.Rn_User_Role_Assignment;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.model.Rn_state_t;
import com.realnet.rb.model.Rn_Report_Group_Header;
import com.realnet.wfb.model.Rn_Fb_Header;
import com.realnet.wfb.model.Rn_Fb_Line;


/**
 * An implementation of the ContactDAO interface.
 * 
 * @author www.codejava.net
 *
 */
@Repository("Rn_Distributor_Details_Dao")
public class Rn_Distributor_Details_DaoImpl implements Rn_Distributor_Details_Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private HibernateConfiguration hibernateConfiguration;


	@Override
	public List<Rn_Users> rn_userlist() {
		String sql = "SELECT USER_ID,PROFILE_ID,USER_NAME,FIRST_NAME,MIDDLE_NAME,"
				+ "LAST_NAME,CONTACT_NUMBER,EMAIL_ADDRESS,START_DATE,END_DATE " + "FROM RN_USERS_T";
		List<Rn_Users> rn_users = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {

			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users rn_users_t = new Rn_Users();

				rn_users_t.setUser_id(rs.getInt("USER_ID"));
				rn_users_t.setProfile_id(rs.getInt("PROFILE_ID"));
				rn_users_t.setUser_name(rs.getString("USER_NAME"));
				rn_users_t.setFirst_name(rs.getString("FIRST_NAME"));
				rn_users_t.setMiddle_name(rs.getString("MIDDLE_NAME"));
				rn_users_t.setLast_name(rs.getString("LAST_NAME"));
				rn_users_t.setContact_number(rs.getString("CONTACT_NUMBER"));
				rn_users_t.setEmail_address(rs.getString("EMAIL_ADDRESS"));
				rn_users_t.setStart_date(rs.getDate("START_DATE"));
				rn_users_t.setEnd_date(rs.getDate("END_DATE"));

				return rn_users_t;
			}

		});

		return rn_users;
	}

	/*
	@Override
	public List<Rn_Customer_Details_Grid> CustomerDetails_list(String id) {
		String sql = "SELECT CUSTOMER_ID,  CUSTOMER_NAME, BILL_ADDRESS_LINE1,BILL_ADDRESS_LINE2,BILL_ADDRESS_LINE3,BILL_CITY,BILL_PROVINCE,BILL_COUNTRY,BILL_ZIP_CODE,BILL_EMAIL_ADDRESS,BILL_PRI_CONTACT_NUMBER FROM RN_CUSTOMER_MASTER_T WHERE DISTRIBUTOR_ID="
				+ id + "";
		List<Rn_Customer_Details_Grid> rn_customer_details_grid = jdbcTemplate.query(sql + id,
				new RowMapper<Rn_Customer_Details_Grid>() {

					@Override
					public Rn_Customer_Details_Grid mapRow(ResultSet rs, int rowNum) throws SQLException {
						Rn_Customer_Details_Grid rn_customer_details_grid_t = new Rn_Customer_Details_Grid();

						rn_customer_details_grid_t.setCustomer_id(rs.getInt("CUSTOMER_ID"));
						rn_customer_details_grid_t.setCustomer_name(rs.getString("CUSTOMER_NAME"));
						rn_customer_details_grid_t.setBill_address_line1(rs.getString("BILL_ADDRESS_LINE1"));
						rn_customer_details_grid_t.setBill_address_line2(rs.getString("BILL_ADDRESS_LINE2"));
						rn_customer_details_grid_t.setBill_address_line3(rs.getString("BILL_ADDRESS_LINE3"));
						rn_customer_details_grid_t.setBill_city(rs.getString("BILL_CITY"));
						rn_customer_details_grid_t.setBill_province(rs.getString("BILL_PROVINCE"));
						rn_customer_details_grid_t.setBill_country(rs.getString("BILL_COUNTRY"));
						rn_customer_details_grid_t.setBill_zip_code(rs.getString("BILL_ZIP_CODE"));
						rn_customer_details_grid_t.setBill_email_address(rs.getString("BILL_EMAIL_ADDRESS"));
						rn_customer_details_grid_t.setBill_pri_contact_number(rs.getString("BILL_PRI_CONTACT_NUMBER"));

						return rn_customer_details_grid_t;
					}

				});

		return rn_customer_details_grid;
	}*/

	/*@Override
	public List<Rn_Customer_Site_Details_Grid> CustomrSiteDetails_List(String id) {

		String sql = "SELECT KCS.CUSTOMER_SITE_ID," + "KCS.INSTALL_TO_SITE," + "KCS.ADDRESS_LINE1,"
				+ "KCS.ADDRESS_LINE2," + "KCS.ADDRESS_LINE3," + "KCS.CITY," + "KCS.STATE," + "KCS.COUNTRY,"
				+ "KCS.ZIP_CODE," + "KCS.PRI_CONTACT_NUMBER," + "KCS.ALT_CONTACT_NUMBER," + "KCS.EMAIL_ADDRESS "
				+ "FROM RN_CUSTOMER_SITES_T KCS," + "  RN_CUSTOMER_MASTER_T KCM "
				+ "WHERE KCS.CUSTOMER_ID = KCM.CUSTOMER_ID " + "AND KCS.CUSTOMER_ID";

		List<Rn_Customer_Site_Details_Grid> rn_customer_site_details_grid = jdbcTemplate.query(sql + id,
				new RowMapper<Rn_Customer_Site_Details_Grid>() {

					@Override
					public Rn_Customer_Site_Details_Grid mapRow(ResultSet rs, int rowNum) throws SQLException {
						Rn_Customer_Site_Details_Grid rn_customer_site_details_grid_t = new Rn_Customer_Site_Details_Grid();
						rn_customer_site_details_grid_t.setCustomer_site_id(rs.getInt("CUSTOMER_SITE_ID"));
						rn_customer_site_details_grid_t.setInstall_to_site(rs.getString("INSTALL_TO_SITE"));
						rn_customer_site_details_grid_t.setAddress_line1(rs.getString("ADDRESS_LINE1"));
						rn_customer_site_details_grid_t.setAddress_line2(rs.getString("ADDRESS_LINE2"));
						rn_customer_site_details_grid_t.setAddress_line3(rs.getString("ADDRESS_LINE3"));
						rn_customer_site_details_grid_t.setCity(rs.getString("CITY"));
						rn_customer_site_details_grid_t.setState(rs.getString("STATE"));
						rn_customer_site_details_grid_t.setCountry(rs.getString("COUNTRY"));
						rn_customer_site_details_grid_t.setZip_code(rs.getString("ZIP_CODE"));
						rn_customer_site_details_grid_t.setPrimary_contact_number(rs.getString("PRI_CONTACT_NUMBER"));
						rn_customer_site_details_grid_t.setPrimary_contact_number(rs.getString("ALT_CONTACT_NUMBER"));
						rn_customer_site_details_grid_t.setEmail_address(rs.getString("EMAIL_ADDRESS"));

						return rn_customer_site_details_grid_t;
					}

				});

		return rn_customer_site_details_grid;
	}*/

	// for autofil list by id
	@Override
	public List<Rn_Users> new_view_report(int u_id) {
		System.out.println("----------------upadte-----------------------");

		System.out.println("dao id  " + u_id);
		String sql = "SELECT USER_ID,USER_NAME,IS_ACTIVE,START_DATE, END_DATE,FIRST_NAME,MIDDLE_NAME,LAST_NAME,CONTACT_NUMBER,EMAIL_ADDRESS FROM RN_USERS_T WHERE USER_ID = "
				+ u_id + "";
		List<Rn_Users> rn_users = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {
			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users rn_users_t = new Rn_Users();
				System.out.println("==============NEW_VIEW_RPORT STARTED===============");

				rn_users_t.setUser_id(rs.getInt("USER_ID"));
				rn_users_t.setUser_name(rs.getString("USER_NAME"));
				rn_users_t.setIs_active(rs.getString("IS_ACTIVE"));
				rn_users_t.setStart_date(rs.getDate("START_DATE"));
				rn_users_t.setEnd_date(rs.getDate("END_DATE"));
				rn_users_t.setFirst_name(rs.getString("FIRST_NAME"));
				rn_users_t.setMiddle_name(rs.getString("MIDDLE_NAME"));
				rn_users_t.setLast_name(rs.getString("LAST_NAME"));
				rn_users_t.setContact_number(rs.getString("CONTACT_NUMBER"));
				rn_users_t.setEmail_address(rs.getString("EMAIL_ADDRESS"));

				// koel.set_icon(rs.getString("REPORT_ICON"));

				// System.out.println("kdsjfksdjf fsadfasfsd "+reps.getReport_id());

				// System.out.println("reports "+reps);
				return rn_users_t;
			}

		});
		for (int i = 0; i < rn_users.size(); i++) {
			System.out.println("asdfhjasdhfjksadf  " + rn_users.get(i));
		}
		return rn_users;
	}

	// update the list

	@Override
	@Transactional
	public int addReport(int rowcount, String[] user_id, String[] user_name, Date start_date, Date end_date,
			String[] first_name, String[] middle_name, String[] last_name, String[] contact_number,
			String[] email_address) {

		int infonum = 0;

		for (int i = 0; i < rowcount; i++) {

			Rn_Users rn_users = new Rn_Users();
			if (user_id != null && user_id.length > 0) {
				infonum = Integer.parseInt(user_id[i]);

			} else {
				infonum = rn_users.getUser_id();
			}

			rn_users.setUser_id(infonum);
			rn_users.setUser_name(user_name[i]);
			rn_users.setStart_date(start_date);
			rn_users.setEnd_date(end_date);
			rn_users.setFirst_name(first_name[i]);
			rn_users.setMiddle_name(middle_name[i]);
			rn_users.setLast_name(last_name[i]);
			rn_users.setContact_number(contact_number[i]);
			rn_users.setEmail_address(email_address[i]);

			// koel.setCreated_by(user_id);
			// koel.setLast_updated_by(user_id);
			hibernateTemplate.saveOrUpdate(rn_users);
		}

		return 1;
	}

	// for autofil user_name list by id
	@Override
	public List<Rn_Users> role_new_view_report(int u_id) {
		System.out.println("----------------upadte-----------------------");

		System.out.println("dao id  " + u_id);
		String sql = "SELECT USER_ID,USER_NAME FROM RN_USERS_T WHERE USER_ID=" + u_id + "";
		List<Rn_Users> rn_users = jdbcTemplate.query(sql, new RowMapper<Rn_Users>() {
			@Override
			public Rn_Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Users rn_users_t = new Rn_Users();
				System.out.println("==============NEW_VIEW_RPORT STARTED===============");
				rn_users_t.setUser_id(rs.getInt("USER_ID"));
				rn_users_t.setUser_name(rs.getString("USER_NAME"));
				return rn_users_t;
			}

		});
		for (int i = 0; i < rn_users.size(); i++) {
			System.out.println("asdfhjasdhfjksadf  " + rn_users.get(i));
		}
		return rn_users;
	}

	// for autofill menu group
	@Override
	public List<Rn_Menu_Group_Header> view_menu_group(int u_id) {
		String sql = "select mgh.menu_name from rn_menu_group_header_t mgh,rn_user_assignments_t ua where mgh.menu_group_header_id=ua.menu_group_id and  ua.user_id='"
				+ u_id + "'";
		List<Rn_Menu_Group_Header> rn_menu_group_header = jdbcTemplate.query(sql, new RowMapper<Rn_Menu_Group_Header>() {
			@Override
			public Rn_Menu_Group_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Menu_Group_Header rn_menu_group_header_t = new Rn_Menu_Group_Header();
				// System.out.println("sdsfgsdfgdfgdfg");

				// grps.setReport_group_line_id(rs.getInt("REPORT_GROUP_LINE_ID"));

				rn_menu_group_header_t.setMenu_name(rs.getString("MENU_NAME"));
				// grps.setReport_name(rs.getString("REPORT_GROUP_ID"));
				// grps.setActive(rs.getString("ACTIVE"));

				return rn_menu_group_header_t;
			}

		});

		return rn_menu_group_header;
	}

	/// for autofill report_group

	@Override
	public List<Rn_Report_Group_Header> view_report_group(int u_id) {
		String sql = "select rgh.report_name from rn_report_group_header_t rgh,rn_user_assignments_t ua where rgh.report_group_header_id=ua.report_group_id and  ua.user_id= '"
				+ u_id + "'";
		List<Rn_Report_Group_Header> rn_report_group_header = jdbcTemplate.query(sql, new RowMapper<Rn_Report_Group_Header>() {
			@Override
			public Rn_Report_Group_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Report_Group_Header rn_report_group_header_t = new Rn_Report_Group_Header();

				rn_report_group_header_t.setReport_name(rs.getString("REPORT_NAME"));

				return rn_report_group_header_t;
			}

		});

		return rn_report_group_header;
	}

	@Override
	public List<Rn_User_Role_Assignment> view_role_group(int u_id) {
		String sql = "select ura.role,ura.user_id,ura.user_role_assignment_id,ura.enable_flag,ura.end_date from rn_user_role_assignments_t ura,rn_user_assignments_t ua where  ura.user_id=ua.user_id and ua.user_id= '"
				+ u_id + "'";
		List<Rn_User_Role_Assignment> user_role_assignment = jdbcTemplate.query(sql, new RowMapper<Rn_User_Role_Assignment>() {
			@Override
			public Rn_User_Role_Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_User_Role_Assignment user_role_assignment_t = new Rn_User_Role_Assignment();

				user_role_assignment_t.setUser_id(rs.getInt("USER_ID"));
				user_role_assignment_t.setUser_role_assignment_id(rs.getInt("USER_ROLE_ASSIGNMENT_ID"));
				user_role_assignment_t.setRole(rs.getString("ROLE"));
				user_role_assignment_t.setEnable_flag(rs.getString("ENABLE_FLAG"));
				user_role_assignment_t.setEnd_date(rs.getDate("END_DATE"));

				return user_role_assignment_t;
			}

		});

		return user_role_assignment;
	}

	

	@Transactional
	public int saveroll(Rn_User_Assignment userassign) {
		hibernateTemplate.saveOrUpdate(userassign);
		System.out.println(userassign.getUser_id());
		return userassign.getUser_id();

	}

	@Override
	@Transactional

	public int addRoll(int rowcount, String[] user_id, int menu_group_id, int report_group_id) {

		int infonum = 0;
		for (int i = 0; i < rowcount; i++) {

			Rn_User_Assignment uss = new Rn_User_Assignment();
			if (user_id != null && user_id.length > 0) {
				infonum = Integer.parseInt(user_id[i]);

			} else {
				infonum = uss.getUser_id();
			}

			uss.setUser_id(infonum);
			uss.setMenu_group_id(menu_group_id);
			uss.setReport_group_id(report_group_id);
			hibernateTemplate.saveOrUpdate(uss);
		}

		return 1;
	}

	@Override
	public List<Rn_User_Assignment> view_assignment_id(int u_id) {
		String sql = "select user_assignment_id from rn_user_assignments_t where user_id='" + u_id + "'";
		List<Rn_User_Assignment> rn_user_assignment = jdbcTemplate.query(sql, new RowMapper<Rn_User_Assignment>() {
			@Override
			public Rn_User_Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_User_Assignment rn_user_assignment_t = new Rn_User_Assignment();

				rn_user_assignment_t.setUser_assignment_id(rs.getInt("USER_ASSIGNMENT_ID"));

				return rn_user_assignment_t;
			}

		});

		return rn_user_assignment;
	}

	

	@Override
	public List<Rn_Ext_Fields> update_fields(int f_id) {
		System.out.println("----------------upadte-----------------------");

		System.out.println("dao id  " + f_id);
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,MANDATORY,HIDDEN,READONLY,DEPENDENT FROM RN_EXT_FIELD_T WHERE ID = "
				+ f_id + "";
		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {
			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setId(rs.getInt("ID"));
				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields_t.setMapping(rs.getString("MAPPING"));
				rn_ext_fields_t.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields_t.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields_t.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields_t.setReadonly(rs.getString("READONLY"));
				rn_ext_fields_t.setDependent(rs.getString("DEPENDENT"));
				return rn_ext_fields_t;
			}

		});
		for (int i = 0; i < rn_ext_fields.size(); i++) {
			System.out.println("asdfhjasdhfjksadf  " + rn_ext_fields.get(i));
		}
		return rn_ext_fields;
	}

	@Override
	public List<Rn_Ext_Fields> update_fields_2(int f_id) {
		System.out.println("----------------upadte-----------------------");

		System.out.println("dao id  " + f_id);

		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,MANDATORY,HIDDEN,READONLY,DEPENDENT,SEQUENCE FROM RN_EXT_FIELD_T WHERE ID = "
				+ f_id + "";

		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {
			
			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setId(rs.getInt("ID"));
				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields_t.setMapping(rs.getString("MAPPING"));
				rn_ext_fields_t.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields_t.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields_t.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields_t.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields_t.setReadonly(rs.getString("READONLY"));
				rn_ext_fields_t.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields_t.setSequence(rs.getString("SEQUENCE"));

				return rn_ext_fields_t;
			}

		});

		return rn_ext_fields;

	}

	// ----------------------------for peroperty field update dao
	// methode---------------------------------------------

	@Override
	public List<Rn_Ext_Fields> property_field(int f_id) {
		System.out.println("----------------upadte-----------------------");
		System.out.println("dao id  " + f_id);
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,DROPDOWN,EXT_DD_ID,SP_FOR_AUTOCOMPLETE,SP_NAME,EXT_DEPENDENT_ID,RADIO,RADIO_OPTION FROM RN_EXT_FIELD_T WHERE ID = "
				+ f_id + "";

		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setId(rs.getInt("ID"));
				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields_t.setMapping(rs.getString("MAPPING"));
				rn_ext_fields_t.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields_t.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields_t.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields_t.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields_t.setReadonly(rs.getString("READONLY"));

				rn_ext_fields_t.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields_t.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields_t.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields_t.setDependent_sp_param(rs.getString("DEPENDENT_SP_PARAM"));

				rn_ext_fields_t.setSequence(rs.getString("SEQUENCE"));
				rn_ext_fields_t.setSeq_name(rs.getString("SEQ_NAME"));
				rn_ext_fields_t.setSeq_sp(rs.getString("SEQ_SP"));
				rn_ext_fields_t.setSeq_sp_param(rs.getString("SEQ_SP_PARAM"));

				rn_ext_fields_t.setValidation_1(rs.getString("VALIDATION_1"));
				rn_ext_fields_t.setVal_type(rs.getString("VAL_TYPE"));
				rn_ext_fields_t.setVal_sp(rs.getString("VAL_SP"));
				rn_ext_fields_t.setVal_sp_param(rs.getString("VAL_SP_PARAM"));

				rn_ext_fields_t.setDefault_1(rs.getString("DEFAULT_1"));
				rn_ext_fields_t.setDefault_type(rs.getString("DEFAULT_TYPE"));
				rn_ext_fields_t.setDefault_value(rs.getString("DEFAULT_VALUE"));
				rn_ext_fields_t.setDefault_sp(rs.getString("DEFAULT_SP"));
				rn_ext_fields_t.setDefault_sp_param(rs.getString("DEFAULT_SP_PARAM"));

				rn_ext_fields_t.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields_t.setCal_sp(rs.getString("CAL_SP"));
				rn_ext_fields_t.setCal_sp_param(rs.getString("CAL_SP_PARAM"));

				rn_ext_fields_t.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields_t.setDropdown(rs.getString("DROPDOWN"));
				rn_ext_fields_t.setExt_dd_id(rs.getString("EXT_DD_ID"));

				rn_ext_fields_t.setSp_for_autocomplete(rs.getString("SP_FOR_AUTOCOMPLETE"));
				rn_ext_fields_t.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields_t.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields_t.setRadio(rs.getString("RADIO"));
				rn_ext_fields_t.setRadio_option(rs.getString("RADIO_OPTION"));

				return rn_ext_fields_t;
			}

		});

		return rn_ext_fields;

	}

	@Override
	public List<Rn_Ext_Fields> property_field2(int f_id) {
		System.out.println("----------------upadte-----------------------");
		System.out.println("dao id  " + f_id);
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,FORM_CODE,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,DROPDOWN,EXT_DD_ID,SP_FOR_AUTOCOMPLETE,SP_NAME,EXT_DEPENDENT_ID,TYPE FROM RN_EXT_FIELD_T WHERE ID = "
				+ f_id + "";

		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setId(rs.getInt("ID"));
				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields_t.setMapping(rs.getString("MAPPING"));
				rn_ext_fields_t.setData_type(rs.getString("DATA_TYPE"));
				rn_ext_fields_t.setForm_code(rs.getString("FORM_CODE"));
				rn_ext_fields_t.setMandatory(rs.getString("MANDATORY"));
				rn_ext_fields_t.setHidden(rs.getString("HIDDEN"));
				rn_ext_fields_t.setReadonly(rs.getString("READONLY"));

				rn_ext_fields_t.setDependent(rs.getString("DEPENDENT"));
				rn_ext_fields_t.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_ext_fields_t.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_ext_fields_t.setDependent_sp_param(rs.getString("DEPENDENT_SP_PARAM"));

				rn_ext_fields_t.setSequence(rs.getString("SEQUENCE"));
				rn_ext_fields_t.setSeq_name(rs.getString("SEQ_NAME"));
				rn_ext_fields_t.setSeq_sp(rs.getString("SEQ_SP"));
				rn_ext_fields_t.setSeq_sp_param(rs.getString("SEQ_SP_PARAM"));

				rn_ext_fields_t.setValidation_1(rs.getString("VALIDATION_1"));
				rn_ext_fields_t.setVal_type(rs.getString("VAL_TYPE"));
				rn_ext_fields_t.setVal_sp(rs.getString("VAL_SP"));
				rn_ext_fields_t.setVal_sp_param(rs.getString("VAL_SP_PARAM"));

				rn_ext_fields_t.setDefault_1(rs.getString("DEFAULT_1"));
				rn_ext_fields_t.setDefault_type(rs.getString("DEFAULT_TYPE"));
				rn_ext_fields_t.setDefault_value(rs.getString("DEFAULT_VALUE"));
				rn_ext_fields_t.setDefault_sp(rs.getString("DEFAULT_SP"));
				rn_ext_fields_t.setDefault_sp_param(rs.getString("DEFAULT_SP_PARAM"));

				rn_ext_fields_t.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_ext_fields_t.setCal_sp(rs.getString("CAL_SP"));
				rn_ext_fields_t.setCal_sp_param(rs.getString("CAL_SP_PARAM"));

				rn_ext_fields_t.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				rn_ext_fields_t.setDropdown(rs.getString("DROPDOWN"));
				rn_ext_fields_t.setExt_dd_id(rs.getString("EXT_DD_ID"));

				rn_ext_fields_t.setSp_for_autocomplete(rs.getString("SP_FOR_AUTOCOMPLETE"));
				rn_ext_fields_t.setSp_name(rs.getString("SP_NAME"));
				rn_ext_fields_t.setExt_dependent_id(rs.getString("EXT_DEPENDENT_ID"));

				rn_ext_fields_t.setType(rs.getString("TYPE"));

				;

				return rn_ext_fields_t;
			}

		});

		return rn_ext_fields;

	}

	

	@Override
	@Transactional
	public int addFields1(Rn_Ext_Fields rn_ext_fields) {
		hibernateTemplate.saveOrUpdate(rn_ext_fields);
		System.out.println(rn_ext_fields.getId());
		return rn_ext_fields.getId();
	}

	@Override
	@Transactional
	public int addFields2(Rn_Ext_Fields rn_ext_fields) {
		hibernateTemplate.saveOrUpdate(rn_ext_fields);
		System.out.println(rn_ext_fields.getId());
		return rn_ext_fields.getId();
	}

	
	@Override
	public int addFields(int rowcount, String[] id, String[] field_name, String[] mapping, String[] data_type) {
		return 0;
	}

	@Override
	public int addFields(Rn_Ext_Fields rn_ext_fields) {
		return 0;
	}

	@Override
	@Transactional
	public int addRn(Rn_Ext_Fields rn_ext_fields) {
		hibernateTemplate.saveOrUpdate(rn_ext_fields);
		return rn_ext_fields.getId();
	}

	@Override
	public List<Rn_Two_Jsp> gridview_three() {
		String sql = "SELECT ID,USER_NAME,FIRST_NAME,LAST_NAME,ATTRIBUTE1,ATTRIBUTE2,ATTRIBUTE3,ATTRIBUTE4,ATTRIBUTE5,ATTRIBUTE6,ATTRIBUTE7,ATTRIBUTE8,ATTRIBUTE9,ATTRIBUTE10,ATTRIBUTE11,ATTRIBUTE12,ATTRIBUTE13,ATTRIBUTE14,ATTRIBUTE15,FLEX1,FLEX2,FLEX3,FLEX4,FLEX5 FROM RN_TWO_JSP_T";
		List<Rn_Two_Jsp> rn_two_jsp = jdbcTemplate.query(sql, new RowMapper<Rn_Two_Jsp>() {

			@Override
			public Rn_Two_Jsp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Two_Jsp rn = new Rn_Two_Jsp();

				rn.setId(rs.getInt("ID"));
				rn.setUser_name(rs.getString("USER_NAME"));
				rn.setFirst_name(rs.getString("FIRST_NAME"));
				rn.setLast_name(rs.getString("LAST_NAME"));
				rn.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn.setAttribute5(rs.getString("ATTRIBUTE5"));

				rn.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn.setAttribute10(rs.getString("ATTRIBUTE10"));

				rn.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn.setAttribute15(rs.getString("ATTRIBUTE15"));

				rn.setFlex1(rs.getString("FLEX1"));
				rn.setFlex2(rs.getString("FLEX2"));
				rn.setFlex3(rs.getString("FLEX3"));
				rn.setFlex4(rs.getString("FLEX4"));
				rn.setFlex5(rs.getString("FLEX5"));

				return rn;
			}

		});

		return rn_two_jsp;
	}


	/*@Override
	public List<Rn_Two_Jsp> grid_view(int u_id) {
		String sql = "SELECT ID,USER_NAME,FIRST_NAME,LAST_NAME,ATTRIBUTE1,ATTRIBUTE2,ATTRIBUTE3,ATTRIBUTE4,ATTRIBUTE5,ATTRIBUTE6,ATTRIBUTE7,ATTRIBUTE8,ATTRIBUTE9,ATTRIBUTE10,ATTRIBUTE11,ATTRIBUTE12,ATTRIBUTE13,ATTRIBUTE14,ATTRIBUTE15 FROM RN_TWO_JSP_T WHERE ID = "
				+ u_id + " ";
		List<Rn_Two_Jsp> rn_two_jsp = jdbcTemplate.query(sql, new RowMapper<Rn_Two_Jsp>()

		{

			@Override
			public Rn_Two_Jsp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Two_Jsp rn_two_jsp_t = new Rn_Two_Jsp();

				rn_two_jsp_t.setId(rs.getInt("ID"));
				rn_two_jsp_t.setUser_name(rs.getString("USER_NAME"));
				rn_two_jsp_t.setFirst_name(rs.getString("FIRST_NAME"));
				rn_two_jsp_t.setLast_name(rs.getString("LAST_NAME"));
				rn_two_jsp_t.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_two_jsp_t.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_two_jsp_t.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_two_jsp_t.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_two_jsp_t.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_two_jsp_t.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_two_jsp_t.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_two_jsp_t.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_two_jsp_t.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_two_jsp_t.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_two_jsp_t.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_two_jsp_t.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_two_jsp_t.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_two_jsp_t.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_two_jsp_t.setAttribute15(rs.getString("ATTRIBUTE15"));

				return rn_two_jsp_t;
			}

		});
		for (int i = 0; i < rn_two_jsp.size(); i++) {
			System.out.println("asdfhjasdhfjksadf  " + rn_two_jsp.get(i));
		}
		return rn_two_jsp;
	}*/

	///////////////////// for entry grid////////////

	@Override
	public List<Rn_Fb_Header> entry_list() {
		System.out.println("--------------dao begins-----------");
		String tablename = "rn_users_t";
		String sql = "SELECT COLUMN_NAME FROM information_schema.columns WHERE table_schema='realnet' AND table_name="
				+ tablename;
		System.out.println("--------------query execute-----------::" + sql);

		List<Rn_Fb_Header> rn_fb_header = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Header>() {

			@Override
			public Rn_Fb_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Header rn_fb_header_t = new Rn_Fb_Header();

				return rn_fb_header_t;
			}

		});

		return rn_fb_header;
	}

	@Override
	public List<Rn_Fb_Header> fb_headerlist(int id) {
		String sql = "SELECT FORM_CODE,JSP_NAME,TABLE_NAME,LINE_TABLE_NAME FROM RN_FB_HEADER_T WHERE PROJECT_ID='" + id
				+ "'";

		List<Rn_Fb_Header> rn_fb_header = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Header>() {

			@Override
			public Rn_Fb_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Header rn_fb_header_t = new Rn_Fb_Header();

				// rn.setId(rs.getInt("ID"));

				rn_fb_header_t.setForm_code(rs.getString("FORM_CODE"));
				rn_fb_header_t.setJsp_name(rs.getString("JSP_NAME"));
				rn_fb_header_t.setTable_name(rs.getString("TABLE_NAME"));
				rn_fb_header_t.setLine_table_name(rs.getString("LINE_TABLE_NAME"));
				return rn_fb_header_t;
			}

		});

		return rn_fb_header;
	}
	
	@Override
	public List<Rn_Fb_Header> fb_headerlist(int project_id,int module_id) {
		String sql = "SELECT PROJECT_ID,MODULE_ID, FORM_CODE,JSP_NAME,TABLE_NAME,LINE_TABLE_NAME FROM RN_FB_HEADER_T WHERE  project_id="+project_id+" and module_id = "+module_id+"";
		List<Rn_Fb_Header> rn_fb_header = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Header>() {

			@Override
			public Rn_Fb_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Header rn_fb_header_t = new Rn_Fb_Header();

				// rn.setId(rs.getInt("ID"));
				rn_fb_header_t.setProject_id(rs.getInt("PROJECT_ID"));
				rn_fb_header_t.setModule_id(rs.getInt("MODULE_ID"));

				rn_fb_header_t.setForm_code(rs.getString("FORM_CODE"));
				rn_fb_header_t.setJsp_name(rs.getString("JSP_NAME"));
				rn_fb_header_t.setTable_name(rs.getString("TABLE_NAME"));
				rn_fb_header_t.setLine_table_name(rs.getString("LINE_TABLE_NAME"));
				return rn_fb_header_t;
			}

		});

		return rn_fb_header;
	}

	@Override
	public List<Rn_Fb_Header> fb_headerlistByProjectId(int p_id) {
		String sql = "SELECT ID,CONTROLLER_NAME,DAO_NAME,DAO_IMPL_NAME,SERVICE_NAME,SERVICE_IMPL_NAME, FORM_CODE,JSP_NAME,TABLE_NAME,LINE_TABLE_NAME FROM RN_FB_HEADER_T WHERE IS_BUILD!='Y' AND PROJECT_ID='"
				+ p_id + "'";

		List<Rn_Fb_Header> rn_fb_header = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Header>() {

			@Override
			public Rn_Fb_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Header rn_fb_header_t = new Rn_Fb_Header();

				rn_fb_header_t.setId(rs.getInt("ID"));
				rn_fb_header_t.setTable_name(rs.getString("TABLE_NAME"));
				rn_fb_header_t.setLine_table_name(rs.getString("LINE_TABLE_NAME"));
				rn_fb_header_t.setForm_code(rs.getString("FORM_CODE"));
				rn_fb_header_t.setJsp_name(rs.getString("JSP_NAME"));
				rn_fb_header_t.setController_name(rs.getString("CONTROLLER_NAME"));
				rn_fb_header_t.setDao_name(rs.getString("DAO_NAME"));
				rn_fb_header_t.setDao_impl_name(rs.getString("DAO_IMPL_NAME"));
				rn_fb_header_t.setService_name(rs.getString("SERVICE_NAME"));
				rn_fb_header_t.setService_impl_name(rs.getString("SERVICE_IMPL_NAME"));

				return rn_fb_header_t;
			}

		});

		return rn_fb_header;
	}

	@Override
	public List<Rn_Fb_Header> fbHeaderlistByProjectIdSource(int p_id) {
		String sql = "SELECT ID,CONTROLLER_NAME,DAO_NAME,DAO_IMPL_NAME,SERVICE_NAME,SERVICE_IMPL_NAME, FORM_CODE,JSP_NAME,TABLE_NAME,LINE_TABLE_NAME FROM RN_FB_HEADER_T WHERE PROJECT_ID='"
				+ p_id + "'";

		List<Rn_Fb_Header> rn_fb_header = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Header>() {

			@Override
			public Rn_Fb_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Header rn_fb_header_t = new Rn_Fb_Header();

				rn_fb_header_t.setId(rs.getInt("ID"));
				rn_fb_header_t.setTable_name(rs.getString("TABLE_NAME"));
				rn_fb_header_t.setLine_table_name(rs.getString("LINE_TABLE_NAME"));
				rn_fb_header_t.setForm_code(rs.getString("FORM_CODE"));
				rn_fb_header_t.setJsp_name(rs.getString("JSP_NAME"));
				rn_fb_header_t.setController_name(rs.getString("CONTROLLER_NAME"));
				rn_fb_header_t.setDao_name(rs.getString("DAO_NAME"));
				rn_fb_header_t.setDao_impl_name(rs.getString("DAO_IMPL_NAME"));
				rn_fb_header_t.setService_name(rs.getString("SERVICE_NAME"));
				rn_fb_header_t.setService_impl_name(rs.getString("SERVICE_IMPL_NAME"));

				return rn_fb_header_t;
			}

		});

		return rn_fb_header;
	}

	@Override
	public List<Rn_Ext_Fields> fb_linelist(String fc) {
		System.out.println("-----fc value-------" + fc);

		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE FROM RN_FB_LINES_T WHERE  FORM_CODE='" + fc + "'";

		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setId(rs.getInt("ID"));
				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields_t.setMapping(rs.getString("MAPPING"));
				rn_ext_fields_t.setData_type(rs.getString("DATA_TYPE"));

				return rn_ext_fields_t;
			}

		});

		return rn_ext_fields;
	}

	@Override
	public List<Rn_Fb_Line> fb_linelist2(String fc) {
		System.out.println("-----fc value-------" + fc);

		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE FROM rn_fb_lines_t WHERE KEY1!='PRI' AND DATA_TYPE!='DATETIME' AND DATA_TYPE!='INT' AND TYPE1='HEADER' AND FORM_CODE='"
				+ fc + "'";

		List<Rn_Fb_Line> rn_fb_line = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Line>() {

			@Override
			public Rn_Fb_Line mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Line rn_fb_line_t = new Rn_Fb_Line();

				rn_fb_line_t.setId(rs.getInt("ID"));
				rn_fb_line_t.setField_name(rs.getString("FIELD_NAME"));
				rn_fb_line_t.setMapping(rs.getString("MAPPING"));
				rn_fb_line_t.setData_type(rs.getString("DATA_TYPE"));

				rn_fb_line_t.setType_field(rs.getString("TYPE_FIELD"));

				rn_fb_line_t.setForm_code(rs.getString("FORM_CODE"));

				rn_fb_line_t.setKey1(rs.getString("KEY1"));
				rn_fb_line_t.setType1(rs.getString("TYPE1"));


				rn_fb_line_t.setMandatory(rs.getString("MANDATORY"));
				rn_fb_line_t.setHidden(rs.getString("HIDDEN"));
				rn_fb_line_t.setReadonly(rs.getString("READONLY"));

				rn_fb_line_t.setDependent(rs.getString("DEPENDENT"));
				rn_fb_line_t.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_fb_line_t.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_fb_line_t.setDependent_sp_param(rs.getString("DEPENDENT_SP_PARAM"));

				rn_fb_line_t.setSequence(rs.getString("SEQUENCE"));
				rn_fb_line_t.setSeq_name(rs.getString("SEQ_NAME"));
				rn_fb_line_t.setSeq_sp(rs.getString("SEQ_SP"));
				rn_fb_line_t.setSeq_sp_param(rs.getString("SEQ_SP_PARAM"));

				rn_fb_line_t.setValidation_1(rs.getString("VALIDATION_1"));
				rn_fb_line_t.setVal_type(rs.getString("VAL_TYPE"));
				rn_fb_line_t.setVal_sp(rs.getString("VAL_SP"));
				rn_fb_line_t.setVal_sp_param(rs.getString("VAL_SP_PARAM"));

				rn_fb_line_t.setDefault_1(rs.getString("DEFAULT_1"));
				rn_fb_line_t.setDefault_type(rs.getString("DEFAULT_TYPE"));
				rn_fb_line_t.setDefault_value(rs.getString("DEFAULT_VALUE"));
				rn_fb_line_t.setDefault_sp(rs.getString("DEFAULT_SP"));
				rn_fb_line_t.setDefault_sp_param(rs.getString("DEFAULT_SP_PARAM"));

				rn_fb_line_t.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_fb_line_t.setCal_sp(rs.getString("CAL_SP"));
				rn_fb_line_t.setCal_sp_param(rs.getString("CAL_SP_PARAM"));

				rn_fb_line_t.setAdd_to_grid(rs.getString("ADD_TO_GRID"));

				rn_fb_line_t.setSp_for_autocomplete(rs.getString("SP_FOR_AUTOCOMPLETE"));
				
				rn_fb_line_t.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));

				rn_fb_line_t.setSp_name_for_dropdown(rs.getString("SP_NAME_FOR_DROPDOWN"));

				return rn_fb_line_t;
			}

		});

		return rn_fb_line;
	}

	// -----------for line part ID PRIMARY--------------------------

	@Override
	public List<Rn_Ext_Fields> line_primary_id(String fc) {
		String sql = "SELECT FIELD_NAME FROM RN_FB_LINES_T WHERE   KEY1='PRI' AND TYPE1='LINE' AND FORM_CODE='" + fc
				+ "'";

		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));

				return rn_ext_fields_t;
			}

		});

		return rn_ext_fields;
	}

	// ---------------------- for line part ONLY VARCHAR
	// -----------------------------------
	@Override
	public List<Rn_Fb_Line> for_line_part(String fc) {
		System.out.println("-----fc value-------" + fc);


		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE FROM RN_FB_LINES_T WHERE  KEY1!='PRI' AND DATA_TYPE!='INT' AND TYPE1='LINE' AND FORM_CODE='"
				+ fc + "'";

		List<Rn_Fb_Line> rn_fb_line = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Line>() {

			@Override
			public Rn_Fb_Line mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Line rn_fb_line_t = new Rn_Fb_Line();

				rn_fb_line_t.setId(rs.getInt("ID"));
				rn_fb_line_t.setField_name(rs.getString("FIELD_NAME"));
				rn_fb_line_t.setMapping(rs.getString("MAPPING"));
				rn_fb_line_t.setData_type(rs.getString("DATA_TYPE"));

				rn_fb_line_t.setType_field(rs.getString("TYPE_FIELD"));

				rn_fb_line_t.setForm_code(rs.getString("FORM_CODE"));

				rn_fb_line_t.setKey1(rs.getString("KEY1"));
				rn_fb_line_t.setType1(rs.getString("TYPE1"));

				rn_fb_line_t.setMandatory(rs.getString("MANDATORY"));
				rn_fb_line_t.setHidden(rs.getString("HIDDEN"));
				rn_fb_line_t.setReadonly(rs.getString("READONLY"));

				rn_fb_line_t.setDependent(rs.getString("DEPENDENT"));
				rn_fb_line_t.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_fb_line_t.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_fb_line_t.setDependent_sp_param(rs.getString("DEPENDENT_SP_PARAM"));

				rn_fb_line_t.setSequence(rs.getString("SEQUENCE"));
				rn_fb_line_t.setSeq_name(rs.getString("SEQ_NAME"));
				rn_fb_line_t.setSeq_sp(rs.getString("SEQ_SP"));
				rn_fb_line_t.setSeq_sp_param(rs.getString("SEQ_SP_PARAM"));

				rn_fb_line_t.setValidation_1(rs.getString("VALIDATION_1"));
				rn_fb_line_t.setVal_type(rs.getString("VAL_TYPE"));
				rn_fb_line_t.setVal_sp(rs.getString("VAL_SP"));
				rn_fb_line_t.setVal_sp_param(rs.getString("VAL_SP_PARAM"));

				rn_fb_line_t.setDefault_1(rs.getString("DEFAULT_1"));
				rn_fb_line_t.setDefault_type(rs.getString("DEFAULT_TYPE"));
				rn_fb_line_t.setDefault_value(rs.getString("DEFAULT_VALUE"));
				rn_fb_line_t.setDefault_sp(rs.getString("DEFAULT_SP"));
				rn_fb_line_t.setDefault_sp_param(rs.getString("DEFAULT_SP_PARAM"));

				rn_fb_line_t.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_fb_line_t.setCal_sp(rs.getString("CAL_SP"));
				rn_fb_line_t.setCal_sp_param(rs.getString("CAL_SP_PARAM"));

				rn_fb_line_t.setAdd_to_grid(rs.getString("ADD_TO_GRID"));

				rn_fb_line_t.setSp_for_autocomplete(rs.getString("SP_FOR_AUTOCOMPLETE"));

				rn_fb_line_t.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));

				rn_fb_line_t.setSp_name_for_dropdown(rs.getString("SP_NAME_FOR_DROPDOWN"));

				return rn_fb_line_t;
			}

		});

		return rn_fb_line;
	}

	// ------------------FOR LINE PART ID NOT PRIMARY----------------------------

	@Override
	public List<Rn_Ext_Fields> lineForId_not_pri(String fc) {
		String sql = "SELECT FIELD_NAME,DATA_TYPE FROM RN_FB_LINES_T WHERE   DATA_TYPE='INT' AND KEY1!='PRI' AND TYPE1='LINE' AND FORM_CODE='"
				+ fc + "'";
		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));
				rn_ext_fields_t.setData_type(rs.getString("DATA_TYPE"));

				return rn_ext_fields_t;
			}

		});

		return rn_ext_fields;
	}

	// -----------ONLY FOR ID--------------------------

	@Override
	public List<Rn_Ext_Fields> fb_listForId(String fc) {
		String sql = "SELECT FIELD_NAME FROM RN_FB_LINES_T WHERE   KEY1='PRI' AND TYPE1='HEADER' AND FORM_CODE='" + fc
				+ "'";

		List<Rn_Ext_Fields> rn_ext_fields = jdbcTemplate.query(sql, new RowMapper<Rn_Ext_Fields>() {

			@Override
			public Rn_Ext_Fields mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Ext_Fields rn_ext_fields_t = new Rn_Ext_Fields();

				rn_ext_fields_t.setField_name(rs.getString("FIELD_NAME"));

				return rn_ext_fields_t;
			}

		});

		return rn_ext_fields;
	}

	@Override
	public List<Rn_Fb_Line> fb_listForId_not_pri(String fc) {
		
		String sql = "SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE FROM rn_fb_lines_t WHERE DATA_TYPE='INT' AND KEY1!='PRI' AND TYPE1='HEADER' and FORM_CODE='"
				+ fc + "'";

		List<Rn_Fb_Line> rn_fb_line = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Line>() {

			@Override
			public Rn_Fb_Line mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Line rn_fb_line_t = new Rn_Fb_Line();

				rn_fb_line_t.setId(rs.getInt("ID"));
				rn_fb_line_t.setField_name(rs.getString("FIELD_NAME"));
				rn_fb_line_t.setMapping(rs.getString("MAPPING"));
				rn_fb_line_t.setData_type(rs.getString("DATA_TYPE"));

				rn_fb_line_t.setType_field(rs.getString("TYPE_FIELD"));

				rn_fb_line_t.setForm_code(rs.getString("FORM_CODE"));

				rn_fb_line_t.setKey1(rs.getString("KEY1"));
				rn_fb_line_t.setType1(rs.getString("TYPE1"));

				rn_fb_line_t.setMandatory(rs.getString("MANDATORY"));
				rn_fb_line_t.setHidden(rs.getString("HIDDEN"));
				rn_fb_line_t.setReadonly(rs.getString("READONLY"));

				rn_fb_line_t.setDependent(rs.getString("DEPENDENT"));
				rn_fb_line_t.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_fb_line_t.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_fb_line_t.setDependent_sp_param(rs.getString("DEPENDENT_SP_PARAM"));

				rn_fb_line_t.setSequence(rs.getString("SEQUENCE"));
				rn_fb_line_t.setSeq_name(rs.getString("SEQ_NAME"));
				rn_fb_line_t.setSeq_sp(rs.getString("SEQ_SP"));
				rn_fb_line_t.setSeq_sp_param(rs.getString("SEQ_SP_PARAM"));

				rn_fb_line_t.setValidation_1(rs.getString("VALIDATION_1"));
				rn_fb_line_t.setVal_type(rs.getString("VAL_TYPE"));
				rn_fb_line_t.setVal_sp(rs.getString("VAL_SP"));
				rn_fb_line_t.setVal_sp_param(rs.getString("VAL_SP_PARAM"));

				rn_fb_line_t.setDefault_1(rs.getString("DEFAULT_1"));
				rn_fb_line_t.setDefault_type(rs.getString("DEFAULT_TYPE"));
				rn_fb_line_t.setDefault_value(rs.getString("DEFAULT_VALUE"));
				rn_fb_line_t.setDefault_sp(rs.getString("DEFAULT_SP"));
				rn_fb_line_t.setDefault_sp_param(rs.getString("DEFAULT_SP_PARAM"));

				rn_fb_line_t.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_fb_line_t.setCal_sp(rs.getString("CAL_SP"));
				rn_fb_line_t.setCal_sp_param(rs.getString("CAL_SP_PARAM"));

				rn_fb_line_t.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				
				rn_fb_line_t.setSp_for_autocomplete(rs.getString("SP_FOR_AUTOCOMPLETE"));
				

				rn_fb_line_t.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));

				rn_fb_line_t.setSp_name_for_dropdown(rs.getString("SP_NAME_FOR_DROPDOWN"));

				return rn_fb_line_t;
			}

		});

		return rn_fb_line;
	}

	@Override
	public List<Rn_Fb_Line> fb_listDatetime(String fc) {
		

		String sql = " SELECT ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE FROM rn_fb_lines_t WHERE DATA_TYPE='DATETIME' AND TYPE1='HEADER' AND FORM_CODE='"
				+ fc + "'";

		List<Rn_Fb_Line> rn_fb_line = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Line>() {

			@Override
			public Rn_Fb_Line mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Line rn_fb_line_t = new Rn_Fb_Line();

				rn_fb_line_t.setId(rs.getInt("ID"));
				rn_fb_line_t.setField_name(rs.getString("FIELD_NAME"));
				rn_fb_line_t.setMapping(rs.getString("MAPPING"));
				rn_fb_line_t.setData_type(rs.getString("DATA_TYPE"));

				rn_fb_line_t.setType_field(rs.getString("TYPE_FIELD"));

				rn_fb_line_t.setForm_code(rs.getString("FORM_CODE"));

				rn_fb_line_t.setKey1(rs.getString("KEY1"));
				rn_fb_line_t.setType1(rs.getString("TYPE1"));

				rn_fb_line_t.setMandatory(rs.getString("MANDATORY"));
				rn_fb_line_t.setHidden(rs.getString("HIDDEN"));
				rn_fb_line_t.setReadonly(rs.getString("READONLY"));

				rn_fb_line_t.setDependent(rs.getString("DEPENDENT"));
				rn_fb_line_t.setDependent_on(rs.getString("DEPENDENT_ON"));
				rn_fb_line_t.setDependent_sp(rs.getString("DEPENDENT_SP"));
				rn_fb_line_t.setDependent_sp_param(rs.getString("DEPENDENT_SP_PARAM"));

				rn_fb_line_t.setSequence(rs.getString("SEQUENCE"));
				rn_fb_line_t.setSeq_name(rs.getString("SEQ_NAME"));
				rn_fb_line_t.setSeq_sp(rs.getString("SEQ_SP"));
				rn_fb_line_t.setSeq_sp_param(rs.getString("SEQ_SP_PARAM"));

				rn_fb_line_t.setValidation_1(rs.getString("VALIDATION_1"));
				rn_fb_line_t.setVal_type(rs.getString("VAL_TYPE"));
				rn_fb_line_t.setVal_sp(rs.getString("VAL_SP"));
				rn_fb_line_t.setVal_sp_param(rs.getString("VAL_SP_PARAM"));

				rn_fb_line_t.setDefault_1(rs.getString("DEFAULT_1"));
				rn_fb_line_t.setDefault_type(rs.getString("DEFAULT_TYPE"));
				rn_fb_line_t.setDefault_value(rs.getString("DEFAULT_VALUE"));
				rn_fb_line_t.setDefault_sp(rs.getString("DEFAULT_SP"));
				rn_fb_line_t.setDefault_sp_param(rs.getString("DEFAULT_SP_PARAM"));

				rn_fb_line_t.setCalculated_field(rs.getString("CALCULATED_FIELD"));
				rn_fb_line_t.setCal_sp(rs.getString("CAL_SP"));
				rn_fb_line_t.setCal_sp_param(rs.getString("CAL_SP_PARAM"));

				rn_fb_line_t.setAdd_to_grid(rs.getString("ADD_TO_GRID"));
				
				rn_fb_line_t.setSp_for_autocomplete(rs.getString("SP_FOR_AUTOCOMPLETE"));
				
				rn_fb_line_t.setSp_name_for_autocomplete(rs.getString("SP_NAME_FOR_AUTOCOMPLETE"));

				rn_fb_line_t.setSp_name_for_dropdown(rs.getString("SP_NAME_FOR_DROPDOWN"));

				return rn_fb_line_t;
			}

		});

		return rn_fb_line;
	}

	@Override
	public List<Rn_Fb_Header> fb_header(String f_code) {
		System.out.println("---f_code----in----fb_header started--------"+f_code);
		String sql = "SELECT TABLE_NAME,JSP_NAME,FORM_CODE, CONTROLLER_NAME,SERVICE_NAME,SERVICE_IMPL_NAME,DAO_NAME,DAO_IMPL_NAME,LINE_TABLE_NAME FROM RN_FB_HEADER_T WHERE FORM_CODE = '"+ f_code + "'";     
		List<Rn_Fb_Header> rn_fb_header = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Header>() {
			@Override
			public Rn_Fb_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Header rn_fb_header_t = new Rn_Fb_Header();

				rn_fb_header_t.setTable_name(rs.getString("TABLE_NAME"));
				rn_fb_header_t.setJsp_name(rs.getString("JSP_NAME"));
				rn_fb_header_t.setForm_code(rs.getString("FORM_CODE"));

				rn_fb_header_t.setController_name(rs.getString("CONTROLLER_NAME"));
				rn_fb_header_t.setService_name(rs.getString("SERVICE_NAME"));
				rn_fb_header_t.setService_impl_name(rs.getString("SERVICE_IMPL_NAME"));
				rn_fb_header_t.setDao_name(rs.getString("DAO_NAME"));
				rn_fb_header_t.setDao_impl_name(rs.getString("DAO_IMPL_NAME"));
				rn_fb_header_t.setLine_table_name(rs.getString("LINE_TABLE_NAME"));

				return rn_fb_header_t;
			}

		});
		for (int i = 0; i < rn_fb_header.size(); i++) {
			System.out.println("asdfh  " + rn_fb_header.get(i).getTable_name());
		}
		return rn_fb_header;
	}

	// -------------------------------for attribute
	// values------------------------------------------------

	@Override
	public List<Rn_Lookup_Values> attribute_values() {
		String sql = "SELECT LOOKUP_CODE FROM RN_LOOKUP_VALUES_T WHERE  LOOKUP_TYPE='FORM_EXT'";

		List<Rn_Lookup_Values> rn_lookup_values = jdbcTemplate.query(sql, new RowMapper<Rn_Lookup_Values>() {

			@Override
			public Rn_Lookup_Values mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Lookup_Values rn_lookup_values_t = new Rn_Lookup_Values();

				rn_lookup_values_t.setLookup_code(rs.getString("LOOKUP_CODE"));

				return rn_lookup_values_t;
			}

		});

		return rn_lookup_values;
	}

	@Override
	public int saveTrsting(Rn_Test12 t12, int id) {
		System.out
				.println("======================DAO++userregistration dao//methode==CreateUser======================");
		int id1 = t12.getId();

		if (id1 == 0) {
			hibernateTemplate.saveOrUpdate(t12);
		} else {
			t12.setId(id);

			hibernateTemplate.saveOrUpdate(t12);

		}
		return t12.getId();
	}

	


	@Override
	public List<Rn_state_t> findAllEmployees() {
		String sql = "SELECT ID,COUNTRY_ID,STATE_NAME FROM RN_STATE_T WHERE COUNTRY_ID=8";

		List<Rn_state_t> rn_state_t = jdbcTemplate.query(sql, new RowMapper<Rn_state_t>() {

			@Override
			public Rn_state_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_state_t rn_state = new Rn_state_t();

				// rn.setId(rs.getInt("ID"));

				rn_state.setId(rs.getInt("ID"));
				rn_state.setCountry_id(rs.getInt("COUNTRY_ID"));
				rn_state.setState_name(rs.getString("STATE_NAME"));

				return rn_state;
			}

		});

		return rn_state_t;
	}

	@Override
	public Rn_state_t findById(int id) {

		System.out.println("valu of id" + id);
		String sql = "SELECT ID,COUNTRY_ID,STATE_NAME FROM RN_STATE_T WHERE ID='" + id + "'";

		Rn_state_t rn_state_t = (Rn_state_t) jdbcTemplate.query(sql, new RowMapper<Rn_state_t>() {

			@Override
			public Rn_state_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_state_t rn_state = new Rn_state_t();

				// rn.setId(rs.getInt("ID"));

				rn_state.setId(rs.getInt("ID"));
				rn_state.setCountry_id(rs.getInt("COUNTRY_ID"));
				rn_state.setState_name(rs.getString("STATE_NAME"));

				return rn_state;
			}

		});

		return rn_state_t;
	}

}
