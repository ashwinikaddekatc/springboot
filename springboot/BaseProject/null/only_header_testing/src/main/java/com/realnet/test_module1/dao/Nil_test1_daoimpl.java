
package com.realnet.test_module1.dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.realnet.test_module1.model.Rn_nil_test1;

@Repository("Nil_test1_dao")
public class Nil_test1_daoimpl implements Nil_test1_dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Rn_nil_test1> userlist() {
		String sql = "SELECT	ID,TEXTFIELD1,	TEXTFIELD2,	TEXTFIELD3,	TEXTFIELD4	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	 FROM	RN_NIL_TEST1";
		List<Rn_nil_test1> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_nil_test1>() {
			@Override
			public Rn_nil_test1 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_nil_test1 rn_nil_test1 = new Rn_nil_test1();

				rn_nil_test1.setId(rs.getInt("ID"));
				rn_nil_test1.setTextfield1(rs.getString("TEXTFIELD1"));
				rn_nil_test1.setTextfield2(rs.getString("TEXTFIELD2"));
				rn_nil_test1.setTextfield3(rs.getString("TEXTFIELD3"));
				rn_nil_test1.setTextfield4(rs.getString("TEXTFIELD4"));
				rn_nil_test1.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_nil_test1.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_nil_test1.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_nil_test1.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_nil_test1.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_nil_test1.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_nil_test1.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_nil_test1.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_nil_test1.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_nil_test1.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_nil_test1.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_nil_test1.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_nil_test1.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_nil_test1.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_nil_test1.setAttribute15(rs.getString("ATTRIBUTE15"));
				rn_nil_test1.setFlex1(rs.getString("FLEX1"));
				rn_nil_test1.setFlex2(rs.getString("FLEX2"));
				rn_nil_test1.setFlex3(rs.getString("FLEX3"));
				rn_nil_test1.setFlex4(rs.getString("FLEX4"));
				rn_nil_test1.setFlex5(rs.getString("FLEX5"));
				return rn_nil_test1;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_nil_test1> prefield(int u_id) {
		String sql = "SELECT ID,TEXTFIELD1,	TEXTFIELD2,	TEXTFIELD3,	TEXTFIELD4	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	,ACCOUNT_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE FROM	RN_NIL_TEST1	WHERE	ID= "
				+ u_id + "";
		List<Rn_nil_test1> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_nil_test1>() {
			@Override
			public Rn_nil_test1 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_nil_test1 rn_nil_test1 = new Rn_nil_test1();
				rn_nil_test1.setId(rs.getInt("ID"));
				rn_nil_test1.setTextfield1(rs.getString("TEXTFIELD1"));
				rn_nil_test1.setTextfield2(rs.getString("TEXTFIELD2"));
				rn_nil_test1.setTextfield3(rs.getString("TEXTFIELD3"));
				rn_nil_test1.setTextfield4(rs.getString("TEXTFIELD4"));
				rn_nil_test1.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_nil_test1.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_nil_test1.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_nil_test1.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_nil_test1.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_nil_test1.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_nil_test1.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_nil_test1.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_nil_test1.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_nil_test1.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_nil_test1.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_nil_test1.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_nil_test1.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_nil_test1.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_nil_test1.setAttribute15(rs.getString("ATTRIBUTE15"));
				rn_nil_test1.setFlex1(rs.getString("FLEX1"));
				rn_nil_test1.setFlex2(rs.getString("FLEX2"));
				rn_nil_test1.setFlex3(rs.getString("FLEX3"));
				rn_nil_test1.setFlex4(rs.getString("FLEX4"));
				rn_nil_test1.setFlex5(rs.getString("FLEX5"));
				rn_nil_test1.setAccount_id(rs.getInt("ACCOUNT_ID"));
				rn_nil_test1.setCreated_by(rs.getInt("CREATED_BY"));
				rn_nil_test1.setCreation_date(rs.getDate("CREATION_DATE"));
				rn_nil_test1.setLast_updated_by(rs.getInt("LAST_UPDATED_BY"));
				rn_nil_test1.setLast_update_date(rs.getDate("LAST_UPDATE_DATE"));
				return rn_nil_test1;
			}
		});
		return userlist;
	}

	@Override
	@Transactional
	public int save(int rowcount, String id, String textfield1, String textfield2, String textfield3,
			String textfield4) {
		int infonum = 0;
		for (int i = 0; i < rowcount; i++) {
			Rn_nil_test1 rn_nil_test1 = new Rn_nil_test1();
			if (id != null && id.length() > 0) {
				infonum = Integer.parseInt(id);
			} else {
				infonum = rn_nil_test1.getId();
			}
			rn_nil_test1.setId(infonum);
			rn_nil_test1.setTextfield1(textfield1);
			rn_nil_test1.setTextfield2(textfield2);
			rn_nil_test1.setTextfield3(textfield3);
			rn_nil_test1.setTextfield4(textfield4);
			hibernateTemplate.saveOrUpdate(rn_nil_test1);
		}
		return 1;
	}

	@Transactional
	public int saveheader(Rn_nil_test1 rn_nil_test1) {
		hibernateTemplate.saveOrUpdate(rn_nil_test1);
		System.out.println(rn_nil_test1.getId());
		return rn_nil_test1.getId();
	}
}