
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

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Instance_Type;

@Repository("Rn_Instance_Type_Dao")
public class Rn_Instance_Type_DaoImpl implements Rn_Instance_Type_Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private HibernateConfiguration hibernateConfiguration;

	@Override
	public List<Rn_Instance_Type> userlist() {
		String sql = "SELECT	ID,INSTANCE_TYPE	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	FROM	RN_INSTANCE_TYPE_T";
		List<Rn_Instance_Type> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Instance_Type>() {
			@Override
			public Rn_Instance_Type mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Instance_Type rn_instance_type_t = new Rn_Instance_Type();

				rn_instance_type_t.setId(rs.getInt("ID"));
				rn_instance_type_t.setInstance_type(rs.getString("INSTANCE_TYPE"));
				rn_instance_type_t.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_instance_type_t.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_instance_type_t.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_instance_type_t.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_instance_type_t.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_instance_type_t.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_instance_type_t.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_instance_type_t.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_instance_type_t.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_instance_type_t.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_instance_type_t.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_instance_type_t.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_instance_type_t.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_instance_type_t.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_instance_type_t.setAttribute15(rs.getString("ATTRIBUTE15"));
				rn_instance_type_t.setFlex1(rs.getString("FLEX1"));
				rn_instance_type_t.setFlex2(rs.getString("FLEX2"));
				rn_instance_type_t.setFlex3(rs.getString("FLEX3"));
				rn_instance_type_t.setFlex4(rs.getString("FLEX4"));
				rn_instance_type_t.setFlex5(rs.getString("FLEX5"));
				return rn_instance_type_t;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_Instance_Type> prefield(int u_id) {
		String sql = "SELECT ID,INSTANCE_TYPE	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	FROM	RN_INSTANCE_TYPE_T	WHERE	ID= "
				+ u_id + "";
		List<Rn_Instance_Type> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Instance_Type>() {
			@Override
			public Rn_Instance_Type mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Instance_Type rn_instance_type_t = new Rn_Instance_Type();
				rn_instance_type_t.setId(rs.getInt("ID"));
				rn_instance_type_t.setInstance_type(rs.getString("INSTANCE_TYPE"));
				rn_instance_type_t.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_instance_type_t.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_instance_type_t.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_instance_type_t.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_instance_type_t.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_instance_type_t.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_instance_type_t.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_instance_type_t.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_instance_type_t.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_instance_type_t.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_instance_type_t.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_instance_type_t.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_instance_type_t.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_instance_type_t.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_instance_type_t.setAttribute15(rs.getString("ATTRIBUTE15"));
				rn_instance_type_t.setFlex1(rs.getString("FLEX1"));
				rn_instance_type_t.setFlex2(rs.getString("FLEX2"));
				rn_instance_type_t.setFlex3(rs.getString("FLEX3"));
				rn_instance_type_t.setFlex4(rs.getString("FLEX4"));
				rn_instance_type_t.setFlex5(rs.getString("FLEX5"));
				return rn_instance_type_t;
			}
		});
		return userlist;
	}

	@Override
	@Transactional
	public int save(int rowcount, String[] id, String[] instance_type) {
		int infonum = 0;
		for (int i = 0; i < rowcount; i++) {
			Rn_Instance_Type rn_instance_type_t = new Rn_Instance_Type();
			if (id != null && id.length > 0) {
				infonum = Integer.parseInt(id[i]);
			} else {
				infonum = rn_instance_type_t.getId();
			}
			rn_instance_type_t.setId(infonum);
			rn_instance_type_t.setInstance_type(instance_type[i]);
			hibernateTemplate.saveOrUpdate(rn_instance_type_t);
		}
		return 1;
	}

	@Transactional
	public int saveheader(Rn_Instance_Type rn_instance_type_t) {
		hibernateTemplate.saveOrUpdate(rn_instance_type_t);
		System.out.println(rn_instance_type_t.getId());
		return rn_instance_type_t.getId();
	}
}