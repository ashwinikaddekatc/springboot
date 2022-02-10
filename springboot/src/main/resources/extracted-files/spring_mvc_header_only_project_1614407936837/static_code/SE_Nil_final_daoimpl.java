"" + "\r\n" + 
"package com.realnet.test_module1." + module_name + "." + module_name + ".dao;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"import java.sql.ResultSet;" + "\r\n" + 
"import java.sql.SQLException;" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import javax.sql.DataSource;" + "\r\n" + 
"import javax.transaction.Transactional;" + "\r\n" + 
"import org.hibernate.Criteria;" + "\r\n" + 
"import org.hibernate.criterion.Restrictions;" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.dao.DataAccessException;" + "\r\n" + 
"import org.springframework.jdbc.core.JdbcTemplate;" + "\r\n" + 
"import org.springframework.jdbc.core.ResultSetExtractor;" + "\r\n" + 
"import org.springframework.jdbc.core.RowMapper;" + "\r\n" + 
"import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;" + "\r\n" + 
"import org.springframework.jdbc.core.namedparam.SqlParameterSource;" + "\r\n" + 
"import org.springframework.orm.hibernate5.HibernateTemplate;" + "\r\n" + 
"import org.springframework.stereotype.Repository;" + "\r\n" + 
"import com.realnet.test_module1.model.Rn_nil_final;" + "\r\n" + 
"@Repository(\"" + dao_name_first_upper + "\")" + "\r\n" + 
"public class	" + dao_name_first_upper + "impl	implements	" + dao_name_first_upper + "" + "\r\n" + 
"{" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private JdbcTemplate jdbcTemplate;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private HibernateTemplate hibernateTemplate;" + "\r\n" + 
"" + "\r\n" + 
"@Override" + "\r\n" + 
"public List<Rn_nil_final> userlist() " + "\r\n" + 
"{" + "\r\n" + 
"String sql =\"SELECT	ID,TEXTFIELD1,	TEXTFIELD2,	TEXTFIELD3,	TEXTFIELD4,	TEXTFIELD5,	TEXTFIELD6,	TEXTFIELD7,	TEXTFIELD8,	TEXTFIELD9	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	 FROM	RN_NIL_FINAL\";" + "\r\n" + 
"List<Rn_nil_final> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_nil_final>()" + "\r\n" + 
"{	" + "\r\n" + 
"@Override" + "\r\n" + 
"public	Rn_nil_final	mapRow(ResultSet rs, int rowNum) throws SQLException" + "\r\n" + 
"{" + "\r\n" + 
"Rn_nil_final	rn_nil_final = new	Rn_nil_final();" + "\r\n" + 
"" + "\r\n" + 
"rn_nil_final.setId(rs.getInt(\"ID\"));" + "\r\n" + 
"rn_nil_final.setTextfield1(rs.getString(\"TEXTFIELD1\"));" + "\r\n" + 
"rn_nil_final.setTextfield2(rs.getString(\"TEXTFIELD2\"));" + "\r\n" + 
"rn_nil_final.setTextfield3(rs.getString(\"TEXTFIELD3\"));" + "\r\n" + 
"rn_nil_final.setTextfield4(rs.getString(\"TEXTFIELD4\"));" + "\r\n" + 
"rn_nil_final.setTextfield5(rs.getString(\"TEXTFIELD5\"));" + "\r\n" + 
"rn_nil_final.setTextfield6(rs.getString(\"TEXTFIELD6\"));" + "\r\n" + 
"rn_nil_final.setTextfield7(rs.getString(\"TEXTFIELD7\"));" + "\r\n" + 
"rn_nil_final.setTextfield8(rs.getString(\"TEXTFIELD8\"));" + "\r\n" + 
"rn_nil_final.setTextfield9(rs.getString(\"TEXTFIELD9\"));" + "\r\n" + 
"rn_nil_final.setAttribute1(rs.getString(\"ATTRIBUTE1\"));" + "\r\n" + 
"rn_nil_final.setAttribute2(rs.getString(\"ATTRIBUTE2\"));" + "\r\n" + 
"rn_nil_final.setAttribute3(rs.getString(\"ATTRIBUTE3\"));" + "\r\n" + 
"rn_nil_final.setAttribute4(rs.getString(\"ATTRIBUTE4\"));" + "\r\n" + 
"rn_nil_final.setAttribute5(rs.getString(\"ATTRIBUTE5\"));" + "\r\n" + 
"rn_nil_final.setAttribute6(rs.getString(\"ATTRIBUTE6\"));" + "\r\n" + 
"rn_nil_final.setAttribute7(rs.getString(\"ATTRIBUTE7\"));" + "\r\n" + 
"rn_nil_final.setAttribute8(rs.getString(\"ATTRIBUTE8\"));" + "\r\n" + 
"rn_nil_final.setAttribute9(rs.getString(\"ATTRIBUTE9\"));" + "\r\n" + 
"rn_nil_final.setAttribute10(rs.getString(\"ATTRIBUTE10\"));" + "\r\n" + 
"rn_nil_final.setAttribute11(rs.getString(\"ATTRIBUTE11\"));" + "\r\n" + 
"rn_nil_final.setAttribute12(rs.getString(\"ATTRIBUTE12\"));" + "\r\n" + 
"rn_nil_final.setAttribute13(rs.getString(\"ATTRIBUTE13\"));" + "\r\n" + 
"rn_nil_final.setAttribute14(rs.getString(\"ATTRIBUTE14\"));" + "\r\n" + 
"rn_nil_final.setAttribute15(rs.getString(\"ATTRIBUTE15\"));" + "\r\n" + 
"rn_nil_final.setFlex1(rs.getString(\"FLEX1\"));" + "\r\n" + 
"rn_nil_final.setFlex2(rs.getString(\"FLEX2\"));" + "\r\n" + 
"rn_nil_final.setFlex3(rs.getString(\"FLEX3\"));" + "\r\n" + 
"rn_nil_final.setFlex4(rs.getString(\"FLEX4\"));" + "\r\n" + 
"rn_nil_final.setFlex5(rs.getString(\"FLEX5\"));" + "\r\n" + 
"return	rn_nil_final;" + "\r\n" + 
"}" + "\r\n" + 
"});" + "\r\n" + 
"return userlist;}" + "\r\n" + 
"" + "\r\n" + 
"@Override" + "\r\n" + 
"public List<Rn_nil_final> prefield(int u_id)" + "\r\n" + 
"{" + "\r\n" + 
"String sql =\"SELECT ID,TEXTFIELD1,	TEXTFIELD2,	TEXTFIELD3,	TEXTFIELD4,	TEXTFIELD5,	TEXTFIELD6,	TEXTFIELD7,	TEXTFIELD8,	TEXTFIELD9	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	,ACCOUNT_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE FROM	RN_NIL_FINAL	WHERE	ID= \"+u_id+\"\";" + "\r\n" + 
"List<Rn_nil_final> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_nil_final>() {" + "\r\n" + 
"@Override" + "\r\n" + 
"public	Rn_nil_final	mapRow(ResultSet rs, int rowNum) throws SQLException {" + "\r\n" + 
"Rn_nil_final	rn_nil_final = new 	Rn_nil_final();" + "\r\n" + 
"rn_nil_final.setId(rs.getInt(\"ID\"));" + "\r\n" + 
"rn_nil_final.setTextfield1(rs.getString(\"TEXTFIELD1\"));" + "\r\n" + 
"rn_nil_final.setTextfield2(rs.getString(\"TEXTFIELD2\"));" + "\r\n" + 
"rn_nil_final.setTextfield3(rs.getString(\"TEXTFIELD3\"));" + "\r\n" + 
"rn_nil_final.setTextfield4(rs.getString(\"TEXTFIELD4\"));" + "\r\n" + 
"rn_nil_final.setTextfield5(rs.getString(\"TEXTFIELD5\"));" + "\r\n" + 
"rn_nil_final.setTextfield6(rs.getString(\"TEXTFIELD6\"));" + "\r\n" + 
"rn_nil_final.setTextfield7(rs.getString(\"TEXTFIELD7\"));" + "\r\n" + 
"rn_nil_final.setTextfield8(rs.getString(\"TEXTFIELD8\"));" + "\r\n" + 
"rn_nil_final.setTextfield9(rs.getString(\"TEXTFIELD9\"));" + "\r\n" + 
"rn_nil_final.setAttribute1(rs.getString(\"ATTRIBUTE1\"));" + "\r\n" + 
"rn_nil_final.setAttribute2(rs.getString(\"ATTRIBUTE2\"));" + "\r\n" + 
"rn_nil_final.setAttribute3(rs.getString(\"ATTRIBUTE3\"));" + "\r\n" + 
"rn_nil_final.setAttribute4(rs.getString(\"ATTRIBUTE4\"));" + "\r\n" + 
"rn_nil_final.setAttribute5(rs.getString(\"ATTRIBUTE5\"));" + "\r\n" + 
"rn_nil_final.setAttribute6(rs.getString(\"ATTRIBUTE6\"));" + "\r\n" + 
"rn_nil_final.setAttribute7(rs.getString(\"ATTRIBUTE7\"));" + "\r\n" + 
"rn_nil_final.setAttribute8(rs.getString(\"ATTRIBUTE8\"));" + "\r\n" + 
"rn_nil_final.setAttribute9(rs.getString(\"ATTRIBUTE9\"));" + "\r\n" + 
"rn_nil_final.setAttribute10(rs.getString(\"ATTRIBUTE10\"));" + "\r\n" + 
"rn_nil_final.setAttribute11(rs.getString(\"ATTRIBUTE11\"));" + "\r\n" + 
"rn_nil_final.setAttribute12(rs.getString(\"ATTRIBUTE12\"));" + "\r\n" + 
"rn_nil_final.setAttribute13(rs.getString(\"ATTRIBUTE13\"));" + "\r\n" + 
"rn_nil_final.setAttribute14(rs.getString(\"ATTRIBUTE14\"));" + "\r\n" + 
"rn_nil_final.setAttribute15(rs.getString(\"ATTRIBUTE15\"));" + "\r\n" + 
"rn_nil_final.setFlex1(rs.getString(\"FLEX1\"));" + "\r\n" + 
"rn_nil_final.setFlex2(rs.getString(\"FLEX2\"));" + "\r\n" + 
"rn_nil_final.setFlex3(rs.getString(\"FLEX3\"));" + "\r\n" + 
"rn_nil_final.setFlex4(rs.getString(\"FLEX4\"));" + "\r\n" + 
"rn_nil_final.setFlex5(rs.getString(\"FLEX5\"));" + "\r\n" + 
"rn_nil_final.setAccount_id(rs.getInt(\"ACCOUNT_ID\"));" + "\r\n" + 
"rn_nil_final.setCreated_by(rs.getInt(\"CREATED_BY\"));" + "\r\n" + 
"rn_nil_final.setCreation_date(rs.getDate(\"CREATION_DATE\"));" + "\r\n" + 
"rn_nil_final.setLast_updated_by(rs.getInt(\"LAST_UPDATED_BY\"));" + "\r\n" + 
"rn_nil_final.setLast_update_date(rs.getDate(\"LAST_UPDATE_DATE\"));" + "\r\n" + 
"return	rn_nil_final;" + "\r\n" + 
"}" + "\r\n" + 
"});" + "\r\n" + 
"return userlist;" + "\r\n" + 
"}" + "\r\n" + 
"@Override" + "\r\n" + 
"@Transactional" + "\r\n" + 
"public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4,	String	textfield5,	String	textfield6,	String	textfield7,	String	textfield8,	String	textfield9	)" + "\r\n" + 
"{" + "\r\n" + 
"int infonum = 0;" + "\r\n" + 
"for (int i = 0; i < rowcount; i++) " + "\r\n" + 
"{" + "\r\n" + 
"Rn_nil_final	rn_nil_final= new	Rn_nil_final();" + "\r\n" + 
"if (id != null && id.length() > 0) {" + "\r\n" + 
"infonum = Integer.parseInt(id);" + "\r\n" + 
"} else {" + "\r\n" + 
"infonum = rn_nil_final.getId();" + "\r\n" + 
"}" + "\r\n" + 
"rn_nil_final.setId(infonum);" + "\r\n" + 
"rn_nil_final.setTextfield1(textfield1);" + "\r\n" + 
"rn_nil_final.setTextfield2(textfield2);" + "\r\n" + 
"rn_nil_final.setTextfield3(textfield3);" + "\r\n" + 
"rn_nil_final.setTextfield4(textfield4);" + "\r\n" + 
"rn_nil_final.setTextfield5(textfield5);" + "\r\n" + 
"rn_nil_final.setTextfield6(textfield6);" + "\r\n" + 
"rn_nil_final.setTextfield7(textfield7);" + "\r\n" + 
"rn_nil_final.setTextfield8(textfield8);" + "\r\n" + 
"rn_nil_final.setTextfield9(textfield9);" + "\r\n" + 
"hibernateTemplate.saveOrUpdate(rn_nil_final);" + "\r\n" + 
"}" + "\r\n" + 
"return 1;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"@Transactional" + "\r\n" + 
"public int saveheader(Rn_nil_final	rn_nil_final) {" + "\r\n" + 
"hibernateTemplate.saveOrUpdate(rn_nil_final);" + "\r\n" + 
"System.out.println(rn_nil_final.getId());" + "\r\n" + 
"return 	rn_nil_final.getId();" + "\r\n" + 
"} }" 
