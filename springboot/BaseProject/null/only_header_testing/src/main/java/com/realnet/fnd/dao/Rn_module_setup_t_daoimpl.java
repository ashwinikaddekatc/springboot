
package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.realnet.fnd.model.Rn_module_setup_t;
import com.realnet.wfb.model.Rn_Fb_Header;

@Repository("Rn_module_setup_t_dao")
public class Rn_module_setup_t_daoimpl implements Rn_module_setup_t_dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Rn_module_setup_t> rn_module_values() {
		String sql = "SELECT	ID,MODULE_NAME,COPY_TO,	DESCRIPTION,PROJECT_ID,	MODULE_PREFIX,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	 FROM	RN_MODULE_SETUP_T ";
		List<Rn_module_setup_t> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_module_setup_t>() {
			@Override
			public Rn_module_setup_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();

				rn_module_setup_t.setId(rs.getInt("ID"));
				rn_module_setup_t.setModule_name(rs.getString("MODULE_NAME"));
				rn_module_setup_t.setCopy_to(rs.getString("COPY_TO"));

				rn_module_setup_t.setDescription(rs.getString("DESCRIPTION"));
				rn_module_setup_t.setModule_prefix(rs.getString("MODULE_PREFIX"));
				rn_module_setup_t.setProject_id(rs.getInt("PROJECT_ID"));
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
				return rn_module_setup_t;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_module_setup_t> prefield(int u_id) {
		String sql = "SELECT ID,MODULE_NAME, COPY_TO, DESCRIPTION, PROJECT_ID, MODULE_PREFIX,ATTRIBUTE1, ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	,ACCOUNT_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE FROM	RN_MODULE_SETUP_T	WHERE	ID = "
				+ u_id + "";
		List<Rn_module_setup_t> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_module_setup_t>() {
			@Override
			public Rn_module_setup_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();
				rn_module_setup_t.setId(rs.getInt("ID"));
				rn_module_setup_t.setModule_name(rs.getString("MODULE_NAME"));
				rn_module_setup_t.setCopy_to(rs.getString("COPY_TO"));

				rn_module_setup_t.setDescription(rs.getString("DESCRIPTION"));
				rn_module_setup_t.setModule_prefix(rs.getString("MODULE_PREFIX"));
				rn_module_setup_t.setProject_id(rs.getInt("PROJECT_ID"));
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
				rn_module_setup_t.setAccount_id(rs.getInt("ACCOUNT_ID"));
				rn_module_setup_t.setCreated_by(rs.getInt("CREATED_BY"));
				rn_module_setup_t.setCreation_date(rs.getDate("CREATION_DATE"));
				rn_module_setup_t.setLast_updated_by(rs.getInt("LAST_UPDATED_BY"));
				rn_module_setup_t.setLast_update_date(rs.getDate("LAST_UPDATE_DATE"));
				return rn_module_setup_t;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_module_setup_t> prefield_module(int m_id) {
		String sql = "SELECT ID,MODULE_NAME,	COPY_TO,DESCRIPTION,PROJECT_ID	,MODULE_PREFIX,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	,ACCOUNT_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE FROM	RN_MODULE_SETUP_T	WHERE	ID= "
				+ m_id + "";
		List<Rn_module_setup_t> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_module_setup_t>() {
			@Override
			public Rn_module_setup_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();
				rn_module_setup_t.setId(rs.getInt("ID"));
				rn_module_setup_t.setModule_name(rs.getString("MODULE_NAME"));
				rn_module_setup_t.setCopy_to(rs.getString("COPY_TO"));

				rn_module_setup_t.setDescription(rs.getString("DESCRIPTION"));
				rn_module_setup_t.setModule_prefix(rs.getString("MODULE_PREFIX"));
				rn_module_setup_t.setProject_id(rs.getInt("PROJECT_ID"));
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
				rn_module_setup_t.setAccount_id(rs.getInt("ACCOUNT_ID"));
				rn_module_setup_t.setCreated_by(rs.getInt("CREATED_BY"));
				rn_module_setup_t.setCreation_date(rs.getDate("CREATION_DATE"));
				rn_module_setup_t.setLast_updated_by(rs.getInt("LAST_UPDATED_BY"));
				rn_module_setup_t.setLast_update_date(rs.getDate("LAST_UPDATE_DATE"));
				return rn_module_setup_t;
			}
		});
		return userlist;
	}

	@Override
	@Transactional
	public int save(int rowcount, String id, String module_name, String description, String module_prefix,
			String project_id, String copy_to) {
		int infonum = 0, p_id = 0;
		for (int i = 0; i < rowcount; i++) {
			Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();
			if (id != null && id.length() > 0) {
				infonum = Integer.parseInt(id);
			} else {
				infonum = rn_module_setup_t.getId();
				p_id = rn_module_setup_t.getProject_id();
			}
			rn_module_setup_t.setId(infonum);
			rn_module_setup_t.setModule_name(module_name);
			rn_module_setup_t.setDescription(description);
			rn_module_setup_t.setModule_prefix(module_prefix);
			rn_module_setup_t.setCopy_to(copy_to);
			rn_module_setup_t.setProject_id(p_id);
			hibernateTemplate.saveOrUpdate(rn_module_setup_t);
		}
		return 1;
	}

	@Override
	@Transactional
	public int saveheader(Rn_module_setup_t rn_module_setup_t) {
		hibernateTemplate.saveOrUpdate(rn_module_setup_t);
		System.out.println("Rn_module_setup_t save with id = " + rn_module_setup_t.getId());
		return rn_module_setup_t.getId();
	}

	@Override
	public List<Rn_module_setup_t> rn_module_values(int module_id) {
		String sql = "SELECT	ID,MODULE_NAME,COPY_TO,	DESCRIPTION,PROJECT_ID,	MODULE_PREFIX,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	 FROM	RN_MODULE_SETUP_T WHERE id = "
				+ module_id + " ";
		List<Rn_module_setup_t> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_module_setup_t>() {
			@Override
			public Rn_module_setup_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();

				rn_module_setup_t.setId(rs.getInt("ID"));
				rn_module_setup_t.setModule_name(rs.getString("MODULE_NAME"));
				rn_module_setup_t.setCopy_to(rs.getString("COPY_TO"));
				rn_module_setup_t.setDescription(rs.getString("DESCRIPTION"));
				rn_module_setup_t.setModule_prefix(rs.getString("MODULE_PREFIX"));
				rn_module_setup_t.setProject_id(rs.getInt("PROJECT_ID"));
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
				return rn_module_setup_t;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_module_setup_t> rn_module_values_for_pid(int p_id) {
		String sql = "SELECT ID, MODULE_NAME, TECHNOLOGY_STACK, COPY_TO ,DESCRIPTION, PROJECT_ID,	MODULE_PREFIX,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	 FROM	RN_MODULE_SETUP_T  WHERE project_id = "
				+ p_id + " ";
		List<Rn_module_setup_t> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_module_setup_t>() {
			@Override
			public Rn_module_setup_t mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();

				rn_module_setup_t.setId(rs.getInt("ID"));
				rn_module_setup_t.setModule_name(rs.getString("MODULE_NAME"));
				rn_module_setup_t.setTechnology_stack(rs.getString("TECHNOLOGY_STACK"));
				rn_module_setup_t.setCopy_to(rs.getString("COPY_TO"));
				rn_module_setup_t.setDescription(rs.getString("DESCRIPTION"));
				rn_module_setup_t.setModule_prefix(rs.getString("MODULE_PREFIX"));
				rn_module_setup_t.setProject_id(rs.getInt("PROJECT_ID"));
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
				return rn_module_setup_t;
			}
		});
		return userlist;
	}

	@Override
	public Rn_module_setup_t getOneById(int module_id) {
		String sql = "SELECT * FROM RN_MODULE_SETUP_T WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { module_id },
				(rs, rowNum) -> new Rn_module_setup_t(rs.getInt("ID"), rs.getString("MODULE_NAME"),
						rs.getString("DESCRIPTION"), rs.getString("MODULE_PREFIX"), rs.getInt("PROJECT_ID"),
						rs.getString("COPY_TO"), rs.getString("TECHNOLOGY_STACK"), rs.getString("ATTRIBUTE1"),
						rs.getString("ATTRIBUTE2"), rs.getString("ATTRIBUTE3"), rs.getString("ATTRIBUTE4"),
						rs.getString("ATTRIBUTE5"), rs.getString("ATTRIBUTE6"), rs.getString("ATTRIBUTE7"),
						rs.getString("ATTRIBUTE8"), rs.getString("ATTRIBUTE9"), rs.getString("ATTRIBUTE10"),
						rs.getString("ATTRIBUTE11"), rs.getString("ATTRIBUTE12"), rs.getString("ATTRIBUTE13"),
						rs.getString("ATTRIBUTE14"), rs.getString("ATTRIBUTE15"), rs.getString("FLEX1"),
						rs.getString("FLEX2"), rs.getString("FLEX3"), rs.getString("FLEX4"), rs.getString("FLEX5"),
						rs.getInt("CREATED_BY"), rs.getInt("LAST_UPDATED_BY"), rs.getDate("CREATION_DATE"),
						rs.getDate("LAST_UPDATE_DATE"), rs.getInt("ACCOUNT_ID")));
	}

	@Override
	public Rn_module_setup_t copyModule(int project_id, int module_id) {
		String sql = "SELECT MODULE_NAME, TECHNOLOGY_STACK, COPY_TO , DESCRIPTION, PROJECT_ID, MODULE_PREFIX, "
				+ "ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3, ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,"
				+ "	ATTRIBUTE9,	ATTRIBUTE10, ATTRIBUTE11, ATTRIBUTE12, ATTRIBUTE13, ATTRIBUTE14, ATTRIBUTE15,"
				+ " FLEX1, FLEX2, FLEX3, FLEX4, FLEX5,"
				+ " CREATED_BY, LAST_UPDATED_BY, CREATION_DATE, LAST_UPDATE_DATE, ACCOUNT_ID FROM RN_MODULE_SETUP_T"
				+ " WHERE PROJECT_ID = ? AND ID = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { project_id, module_id },
				(rs, rowNum) -> new Rn_module_setup_t(
						// rs.getInt("ID"),
						rs.getString("MODULE_NAME"), rs.getString("DESCRIPTION"), rs.getString("MODULE_PREFIX"),
						rs.getInt("PROJECT_ID"), rs.getString("COPY_TO"), rs.getString("TECHNOLOGY_STACK"),
						rs.getString("ATTRIBUTE1"), rs.getString("ATTRIBUTE2"), rs.getString("ATTRIBUTE3"),
						rs.getString("ATTRIBUTE4"), rs.getString("ATTRIBUTE5"), rs.getString("ATTRIBUTE6"),
						rs.getString("ATTRIBUTE7"), rs.getString("ATTRIBUTE8"), rs.getString("ATTRIBUTE9"),
						rs.getString("ATTRIBUTE10"), rs.getString("ATTRIBUTE11"), rs.getString("ATTRIBUTE12"),
						rs.getString("ATTRIBUTE13"), rs.getString("ATTRIBUTE14"), rs.getString("ATTRIBUTE15"),
						rs.getString("FLEX1"), rs.getString("FLEX2"), rs.getString("FLEX3"), rs.getString("FLEX4"),
						rs.getString("FLEX5"), rs.getInt("CREATED_BY"), rs.getInt("LAST_UPDATED_BY"),
						rs.getDate("CREATION_DATE"), rs.getDate("LAST_UPDATE_DATE"), rs.getInt("ACCOUNT_ID")));
	}

	@Override
	public Rn_Fb_Header copyHeader(int project_id, int new_module_id, int module_id) {
		String sql = "SELECT PROJECT_ID, " + new_module_id + ", UI_NAME,"
				+ " TECH_STACK, OBJECT_TYPE, SUB_OBJECT_TYPE, MENU_NAME,"
				+ " TABLE_NAME, CONVERTED_TABLE_NAME , HEADER_NAME, JSP_NAME,"
				+ " FORM_CODE, CONTROLLER_NAME, DAO_NAME, DAO_IMPL_NAME, SERVICE_NAME,"
				+ " SERVICE_IMPL_NAME, FORM_TYPE , LINE_TABLE_NAME, MULTILINE_TABLE_NAME,"
				+ " ACCOUNT_ID, IS_BUILD, IS_UPDATED FROM RN_FB_HEADER_T" + " WHERE PROJECT_ID = ? AND MODULE_ID = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { project_id, module_id },
				(rs, rowNum) -> new Rn_Fb_Header(rs.getString("TECH_STACK"), rs.getString("OBJECT_TYPE"),
						rs.getString("SUB_OBJECT_TYPE"), rs.getInt("ACCOUNT_ID"), rs.getInt("PROJECT_ID"),
						rs.getInt(new_module_id), rs.getString("FORM_TYPE"), rs.getString("TABLE_NAME"),
						rs.getString("LINE_TABLE_NAME"), rs.getString("MULTILINE_TABLE_NAME"), rs.getString("JSP_NAME"),
						rs.getString("FORM_CODE"), rs.getString("CONTROLLER_NAME"), rs.getString("SERVICE_NAME"),
						rs.getString("SERVICE_IMPL_NAME"), rs.getString("DAO_NAME"), rs.getString("DAO_IMPL_NAME"),
						rs.getString("IS_BUILD"), rs.getString("IS_UPDATED"), rs.getString("UI_NAME"),
						rs.getString("MENU_NAME"), rs.getString("HEADER_NAME"), rs.getString("CONVERTED_TABLE_NAME")));
	}

	@Override
	public List<Rn_Fb_Header> copyHeaders(int project_id, int module_id) {
		String sql = "SELECT ID, ACCOUNT_ID, PROJECT_ID, UI_NAME, TECH_STACK, OBJECT_TYPE, SUB_OBJECT_TYPE,"
				+ " TABLE_NAME, LINE_TABLE_NAME, MULTILINE_TABLE_NAME, FORM_TYPE, JSP_NAME, FORM_CODE, CONTROLLER_NAME,"
				+ " SERVICE_NAME, SERVICE_IMPL_NAME, DAO_NAME, DAO_IMPL_NAME, IS_BUILD, IS_UPDATED, MENU_NAME,"
				+ " CONVERTED_TABLE_NAME, HEADER_NAME, MODULE_ID FROM RN_FB_HEADER_T" + " WHERE PROJECT_ID = "
				+ project_id + " AND MODULE_ID = " + module_id;

		// System.out.println(sql);
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
}