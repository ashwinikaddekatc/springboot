
package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Patch;
import com.realnet.fnd.model.Rn_Project_Setup;
import com.realnet.fnd.model.Rn_module_setup_t;
import com.realnet.wfb.model.Rn_Fb_Header;

@Repository("Rn_Project_Setup_Dao")
public class Rn_Project_Setup_DaoImpl implements Rn_Project_Setup_Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private HibernateConfiguration hibernateConfiguration;

	@Autowired
	private Environment environment;

	// get projectv path
	@Override
	public String getProjectPath() {
		return environment.getRequiredProperty("projectPath");
	}

	// get project path depending on technology stack
	@Override
	public String getProjectPath(String technology_stack) {
		if (technology_stack.equals("Angular_Boot_Maven_Mysql"))
			return environment.getRequiredProperty("angularProjectPath");
		return environment.getRequiredProperty("projectPath");
	}

	@Override
	public List<Rn_Project_Setup> userlist() {
		String sql = "SELECT	ID,PROJECT_NAME,TECHNOLOGY_STACK,	DESCRIPTION		FROM	RN_PROJECT_SETUP_T";
		List<Rn_Project_Setup> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Project_Setup>() {
			@Override
			public Rn_Project_Setup mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Project_Setup rn_project_setup_t = new Rn_Project_Setup();

				rn_project_setup_t.setId(rs.getInt("ID"));
				rn_project_setup_t.setProject_name(rs.getString("PROJECT_NAME"));
				rn_project_setup_t.setTechnology_stack(rs.getString("TECHNOLOGY_STACK"));
				rn_project_setup_t.setDescription(rs.getString("DESCRIPTION"));

				return rn_project_setup_t;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_Project_Setup> prefield(int u_id) {
		String sql = "SELECT ID,PROJECT_NAME,TECHNOLOGY_STACK,	DESCRIPTION,PROJECT_PREFIX,DB_NAME,DB_USER,DB_PASSWORD,PORT_NO FROM	RN_PROJECT_SETUP_T	WHERE	ID= "
				+ u_id + "";
		List<Rn_Project_Setup> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Project_Setup>() {
			@Override
			public Rn_Project_Setup mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Project_Setup rn_project_setup_t = new Rn_Project_Setup();

				rn_project_setup_t.setId(rs.getInt("ID"));
				rn_project_setup_t.setProject_name(rs.getString("PROJECT_NAME"));
				rn_project_setup_t.setDescription(rs.getString("DESCRIPTION"));
				rn_project_setup_t.setTechnology_stack(rs.getString("TECHNOLOGY_STACK"));
				rn_project_setup_t.setProject_prefix(rs.getString("PROJECT_PREFIX"));
				rn_project_setup_t.setDb_name(rs.getString("DB_NAME"));
				rn_project_setup_t.setDb_user(rs.getString("DB_USER"));
				rn_project_setup_t.setDb_password(rs.getString("DB_PASSWORD"));
				rn_project_setup_t.setPort_no(rs.getString("PORT_NO"));

				return rn_project_setup_t;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_Patch> patchDetails() {
		String sql = "SELECT ID,FILENAME,PROJECT_NAME,OBJECT_TYPE,COMP_TYPE,LOCATION,STATUS FROM RN_PATCH_TABLE_T WHERE STATUS='N'";
		List<Rn_Patch> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Patch>() {
			@Override
			public Rn_Patch mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Patch rn_project_setup_t = new Rn_Patch();

				rn_project_setup_t.setId(rs.getInt("ID"));
				rn_project_setup_t.setFilename(rs.getString("FILENAME"));

				rn_project_setup_t.setProject_name(rs.getString("PROJECT_NAME"));
				rn_project_setup_t.setObject_type(rs.getString("OBJECT_TYPE"));
				rn_project_setup_t.setComp_type(rs.getString("COMP_TYPE"));

				rn_project_setup_t.setLocation(rs.getString("LOCATION"));
				rn_project_setup_t.setStatus(rs.getString("STATUS"));

				return rn_project_setup_t;
			}
		});
		return userlist;
	}

	@Override
	@Transactional
	public int save(int rowcount, String[] id, String[] project_name, String[] description, String[] technology_stack,
			String[] project_prefix, String[] db_name, String[] db_user, String[] db_password, String[] port_no,
			String[] copy_to) {
		int infonum = 0;
		for (int i = 0; i < rowcount; i++) {
			Rn_Project_Setup rn_project_setup_t = new Rn_Project_Setup();
			if (id != null && id.length > 0) {
				infonum = Integer.parseInt(id[i]);
			} else {
				infonum = rn_project_setup_t.getId();
			}
			rn_project_setup_t.setId(infonum);
			rn_project_setup_t.setProject_name(project_name[i]);
			rn_project_setup_t.setDescription(description[i]);
			rn_project_setup_t.setTechnology_stack(technology_stack[i]);

			rn_project_setup_t.setProject_prefix(project_prefix[i]);
			rn_project_setup_t.setDb_name(db_name[i]);
			rn_project_setup_t.setDb_user(db_user[i]);
			rn_project_setup_t.setDb_password(db_password[i]);
			rn_project_setup_t.setPort_no(port_no[i]);
			rn_project_setup_t.setCopy_to(copy_to[i]);

			hibernateTemplate.saveOrUpdate(rn_project_setup_t);
		}
		return 1;
	}

	@Transactional
	public int saveheader(Rn_Project_Setup rn_project_setup_t) {
		hibernateTemplate.saveOrUpdate(rn_project_setup_t);
		System.out.println(rn_project_setup_t.getId());
		return rn_project_setup_t.getId();
	}

	@Override
	public Rn_Project_Setup copyProject(int project_id) {
		String sql = "SELECT PROJECT_NAME,	DESCRIPTION, MODULE_ID, COPY_TO, TECHNOLOGY_STACK, PROJECT_PREFIX, DB_NAME,DB_USER,DB_PASSWORD, PORT_NO FROM RN_PROJECT_SETUP_T"
				+ " WHERE ID = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { project_id }, (rs, rowNum) -> new Rn_Project_Setup(
				// rs.getInt("ID"),
				rs.getString("PROJECT_NAME"), rs.getString("DESCRIPTION"), rs.getInt("MODULE_ID"),
				rs.getString("COPY_TO"), rs.getString("TECHNOLOGY_STACK"), rs.getString("PROJECT_PREFIX"),
				rs.getString("DB_NAME"), rs.getString("DB_USER"), rs.getString("DB_PASSWORD"),
				rs.getString("PORT_NO")));
	}

	@Override
	public List<Rn_module_setup_t> copyModules(int project_id) {
		String sql = "SELECT ID, MODULE_NAME, TECHNOLOGY_STACK, COPY_TO , DESCRIPTION, PROJECT_ID, MODULE_PREFIX, "
				+ "ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3, ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,"
				+ "	ATTRIBUTE9,	ATTRIBUTE10, ATTRIBUTE11, ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15,"
				+ " FLEX1, FLEX2, FLEX3, FLEX4, FLEX5,"
				+ " CREATED_BY, LAST_UPDATED_BY, CREATION_DATE, LAST_UPDATE_DATE, ACCOUNT_ID FROM RN_MODULE_SETUP_T"
				+ " WHERE PROJECT_ID = " + project_id + "";

		List<Rn_module_setup_t> rn_fb_header_object = jdbcTemplate.query(sql, new RowMapper<Rn_module_setup_t>() {
			@Override
			public Rn_module_setup_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();
				rn_module_setup_t.setId(rs.getInt("ID"));
				rn_module_setup_t.setModule_name(rs.getString("MODULE_NAME"));
				rn_module_setup_t.setTechnology_stack(rs.getString("TECHNOLOGY_STACK"));
				rn_module_setup_t.setCopy_to(rs.getString("COPY_TO"));
				rn_module_setup_t.setDescription(rs.getString("DESCRIPTION"));
				rn_module_setup_t.setProject_id(rs.getInt("PROJECT_ID"));
				rn_module_setup_t.setModule_prefix(rs.getString("MODULE_PREFIX"));
				
				rn_module_setup_t.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_module_setup_t.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_module_setup_t.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_module_setup_t.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_module_setup_t.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_module_setup_t.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_module_setup_t.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_module_setup_t.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_module_setup_t.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_module_setup_t.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_module_setup_t.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_module_setup_t.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_module_setup_t.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_module_setup_t.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_module_setup_t.setAttribute15(rs.getString("ATTRIBUTE15"));
				rn_module_setup_t.setFlex1(rs.getString("FLEX1"));
				rn_module_setup_t.setFlex2(rs.getString("FLEX2"));
				rn_module_setup_t.setFlex3(rs.getString("FLEX3"));
				rn_module_setup_t.setFlex4(rs.getString("FLEX4"));
				rn_module_setup_t.setFlex5(rs.getString("FLEX5"));
				rn_module_setup_t.setCreated_by(rs.getInt("CREATED_BY"));
				rn_module_setup_t.setLast_updated_by(rs.getInt("LAST_UPDATED_BY"));
				rn_module_setup_t.setCreation_date(rs.getDate("CREATION_DATE"));
				rn_module_setup_t.setLast_update_date(rs.getDate("LAST_UPDATE_DATE"));
				rn_module_setup_t.setAccount_id(rs.getInt("ACCOUNT_ID"));
				return rn_module_setup_t;
			}
		});

		return rn_fb_header_object;
	}

	@Override
	public List<Rn_Fb_Header> copyHeaders(int project_id, int module_id) {
		String sql = "SELECT ID, ACCOUNT_ID, PROJECT_ID, UI_NAME, TECH_STACK, OBJECT_TYPE, SUB_OBJECT_TYPE,"
				+ " TABLE_NAME, LINE_TABLE_NAME, MULTILINE_TABLE_NAME, FORM_TYPE, JSP_NAME, FORM_CODE, CONTROLLER_NAME,"
				+ " SERVICE_NAME, SERVICE_IMPL_NAME, DAO_NAME, DAO_IMPL_NAME, IS_BUILD, IS_UPDATED, MENU_NAME,"
				+ " CONVERTED_TABLE_NAME, HEADER_NAME, MODULE_ID FROM RN_FB_HEADER_T" + " WHERE PROJECT_ID = "
				+ project_id + " AND MODULE_ID = " + module_id;

		List<Rn_Fb_Header> rn_fb_header_object = jdbcTemplate.query(sql, new RowMapper<Rn_Fb_Header>() {
			@Override
			public Rn_Fb_Header mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Fb_Header rn = new Rn_Fb_Header();
				rn.setId(rs.getInt("ID"));
				rn.setAccount_id(rs.getInt("ACCOUNT_ID"));
				rn.setProject_id(rs.getInt("PROJECT_ID"));
				rn.setUi_name(rs.getString("UI_NAME"));
				rn.setTech_stack(rs.getString("TECH_STACK"));
				rn.setObject_type(rs.getString("OBJECT_TYPE"));
				rn.setSub_object_type(rs.getString("SUB_OBJECT_TYPE"));
				rn.setTable_name(rs.getString("TABLE_NAME"));
				rn.setLine_table_name(rs.getString("LINE_TABLE_NAME"));
				rn.setMultiline_table_name(rs.getString("MULTILINE_TABLE_NAME"));
				rn.setForm_type(rs.getString("FORM_TYPE"));
				rn.setJsp_name(rs.getString("JSP_NAME"));
				rn.setForm_code(rs.getString("FORM_CODE"));
				rn.setController_name(rs.getString("CONTROLLER_NAME"));
				rn.setService_name(rs.getString("SERVICE_NAME"));
				rn.setService_impl_name(rs.getString("SERVICE_IMPL_NAME"));
				rn.setDao_name(rs.getString("DAO_NAME"));
				rn.setDao_impl_name(rs.getString("DAO_IMPL_NAME"));
				rn.setIs_build(rs.getString("IS_BUILD"));
				rn.setIs_updated(rs.getString("IS_UPDATED"));
				rn.setMenu_name(rs.getString("MENU_NAME"));
				rn.setConverted_table_name(rs.getString("CONVERTED_TABLE_NAME"));
				rn.setHeader_name(rs.getString("HEADER_NAME"));
				rn.setModule_id(rs.getInt("MODULE_ID"));
				return rn;
			}
		});

		return rn_fb_header_object;
	}

	/*
	 * @Override public List<Rn_Project_Setup> prefield(int u_id, int module_id) {
	 * String sql =
	 * "SELECT ID,MODULE_ID,PROJECT_NAME,	DESCRIPTION,PROJECT_PREFIX,DB_NAME,DB_USER,DB_PASSWORD,PORT_NO FROM	RN_PROJECT_SETUP_T	WHERE	ID="
	 * +u_id+" and module_id = "+module_id+"";
	 * 
	 * List<Rn_Project_Setup> userlist = jdbcTemplate.query(sql, new
	 * RowMapper<Rn_Project_Setup>() {
	 * 
	 * @Override public Rn_Project_Setup mapRow(ResultSet rs, int rowNum) throws
	 * SQLException { Rn_Project_Setup rn_project_setup_t = new Rn_Project_Setup();
	 * 
	 * rn_project_setup_t.setId(rs.getInt("ID"));
	 * rn_project_setup_t.setModule_id(rs.getInt("MODULE_ID"));
	 * rn_project_setup_t.setProject_name(rs.getString("PROJECT_NAME"));
	 * rn_project_setup_t.setDescription(rs.getString("DESCRIPTION"));
	 * rn_project_setup_t.setProject_prefix(rs.getString("PROJECT_PREFIX"));
	 * rn_project_setup_t.setDb_name(rs.getString("DB_NAME"));
	 * rn_project_setup_t.setDb_user(rs.getString("DB_USER"));
	 * rn_project_setup_t.setDb_password(rs.getString("DB_PASSWORD"));
	 * rn_project_setup_t.setPort_no(rs.getString("PORT_NO"));
	 * 
	 * 
	 * return rn_project_setup_t; } }); return userlist; }
	 */

	/*
	 * @Override
	 * 
	 * @Transactional public int updateFbHeader(int rowcount4,int id,String
	 * date_id[],String table_allias_name3[],String column_name3[],String
	 * column_allias_name3[]) { int infonum=0; for(int i=0; i<rowcount4; i++) {
	 * if(id!=0) { Form_builder rn_rb_date_t=new Form_builder(); if (date_id[i] !=
	 * null && date_id[i].length() > 0) { infonum = Integer.parseInt(date_id[i]); }
	 * else { infonum =rn_rb_date_t.getDate_id(); } rn_rb_date_t.setReport_id(id);
	 * rn_rb_date_t.setDate_id(infonum);
	 * rn_rb_date_t.setCol_table_alies_name_date(table_allias_name3[i]);
	 * rn_rb_date_t.setCol_date_query(column_name3[i]);
	 * rn_rb_date_t.setColumn_alias_date_query(column_allias_name3[i]);
	 * 
	 * hibernateTemplate.saveOrUpdate(rn_rb_date_t); } }
	 * 
	 * return 1; }
	 */

}