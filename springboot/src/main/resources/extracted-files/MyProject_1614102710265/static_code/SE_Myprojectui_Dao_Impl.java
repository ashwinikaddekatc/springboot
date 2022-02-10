"" + "\r\n" + 
"package com.realnet.test_module1.MyProjectModule." + module_name + ".dao;" + "\r\n" + 
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
"import com.realnet.test_module1.model.Myprojectui_t;" + "\r\n" + 
"@Repository(\"Myprojectui_dao\")" + "\r\n" + 
"public class	Myprojectui_daoimpl	implements	Myprojectui_dao" + "\r\n" + 
"{" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private JdbcTemplate jdbcTemplate;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private HibernateTemplate hibernateTemplate;" + "\r\n" + 
"" + "\r\n" + 
"@Override" + "\r\n" + 
"public List<Myprojectui_t> userlist() " + "\r\n" + 
"{" + "\r\n" + 
"String sql =\"SELECT ID, TEXTFIELD1, TEXTFIELD2, TEXTFIELD3, TEXTFIELD4,  FROM	MYPROJECTUI_T\";" + "\r\n" + 
"List<Myprojectui_t> userlist = jdbcTemplate.query(sql, new RowMapper<Myprojectui_t>()" + "\r\n" + 
"{	" + "\r\n" + 
"@Override" + "\r\n" + 
"public	Myprojectui_t	mapRow(ResultSet rs, int rowNum) throws SQLException" + "\r\n" + 
"{" + "\r\n" + 
"Myprojectui_t	myprojectui_t = new	Myprojectui_t();" + "\r\n" + 
"" + "\r\n" + 
"myprojectui_t.setId(rs.getInt(\"ID\"));" + "\r\n" + 
"myprojectui_t.setTextfield1(rs.getString(\"TEXTFIELD1\"));" + "\r\n" + 
"myprojectui_t.setTextfield2(rs.getString(\"TEXTFIELD2\"));" + "\r\n" + 
"myprojectui_t.setTextfield3(rs.getString(\"TEXTFIELD3\"));" + "\r\n" + 
"myprojectui_t.setTextfield4(rs.getString(\"TEXTFIELD4\"));" + "\r\n" + 
"return	myprojectui_t;" + "\r\n" + 
"}" + "\r\n" + 
"});" + "\r\n" + 
"return userlist;}" + "\r\n" + 
"" + "\r\n" + 
"@Override" + "\r\n" + 
"public List<Myprojectui_t> prefield(int u_id)" + "\r\n" + 
"{" + "\r\n" + 
"String sql =\"SELECT" + "\r\n" + 
"ID, TEXTFIELD1, TEXTFIELD2, TEXTFIELD3, TEXTFIELD4, created_by, updated_by, created_at, updated_at, account_idFROM	MYPROJECTUI_T	WHERE	ID= \"+u_id+\"\";" + "\r\n" + 
"List<Myprojectui_t> userlist = jdbcTemplate.query(sql, new RowMapper<Myprojectui_t>() {" + "\r\n" + 
"@Override" + "\r\n" + 
"public	Myprojectui_t	mapRow(ResultSet rs, int rowNum) throws SQLException {" + "\r\n" + 
"Myprojectui_t	myprojectui_t = new 	Myprojectui_t();" + "\r\n" + 
"myprojectui_t.setId(rs.getInt(\"ID\"));" + "\r\n" + 
"myprojectui_t.setTextfield1(rs.getString(\"TEXTFIELD1\"));" + "\r\n" + 
"myprojectui_t.setTextfield2(rs.getString(\"TEXTFIELD2\"));" + "\r\n" + 
"myprojectui_t.setTextfield3(rs.getString(\"TEXTFIELD3\"));" + "\r\n" + 
"myprojectui_t.setTextfield4(rs.getString(\"TEXTFIELD4\"));" + "\r\n" + 
"myprojectui_t.setExtn1(rs.getString(\"EXTN1\"));" + "\r\n" + 
"myprojectui_t.setExtn2(rs.getString(\"EXTN2\"));" + "\r\n" + 
"myprojectui_t.setExtn3(rs.getString(\"EXTN3\"));" + "\r\n" + 
"myprojectui_t.setExtn4(rs.getString(\"EXTN4\"));" + "\r\n" + 
"myprojectui_t.setExtn5(rs.getString(\"EXTN5\"));" + "\r\n" + 
"myprojectui_t.setExtn6(rs.getString(\"EXTN6\"));" + "\r\n" + 
"myprojectui_t.setExtn7(rs.getString(\"EXTN7\"));" + "\r\n" + 
"myprojectui_t.setExtn8(rs.getString(\"EXTN8\"));" + "\r\n" + 
"myprojectui_t.setExtn9(rs.getString(\"EXTN9\"));" + "\r\n" + 
"myprojectui_t.setExtn10(rs.getString(\"EXTN10\"));" + "\r\n" + 
"myprojectui_t.setExtn11(rs.getString(\"EXTN11\"));" + "\r\n" + 
"myprojectui_t.setExtn12(rs.getString(\"EXTN12\"));" + "\r\n" + 
"myprojectui_t.setExtn13(rs.getString(\"EXTN13\"));" + "\r\n" + 
"myprojectui_t.setExtn14(rs.getString(\"EXTN14\"));" + "\r\n" + 
"myprojectui_t.setExtn15(rs.getString(\"EXTN15\"));" + "\r\n" + 
"myprojectui_t.setFlex1(rs.getString(\"FLEX1\"));" + "\r\n" + 
"myprojectui_t.setFlex2(rs.getString(\"FLEX2\"));" + "\r\n" + 
"myprojectui_t.setFlex3(rs.getString(\"FLEX3\"));" + "\r\n" + 
"myprojectui_t.setFlex4(rs.getString(\"FLEX4\"));" + "\r\n" + 
"myprojectui_t.setFlex5(rs.getString(\"FLEX5\"));" + "\r\n" + 
"myprojectui_t.setAccount_id(rs.getInt(\"ACCOUNT_ID\"));" + "\r\n" + 
"myprojectui_t.setCreatedBy(rs.getLong(\"CREATED_BY\"));" + "\r\n" + 
"myprojectui_t.setCreatedAt(rs.getDate(\"CREATED_AT\"));" + "\r\n" + 
"myprojectui_t.setUpdatedBy(rs.getLong(\"UPDATED_BY\"));" + "\r\n" + 
"myprojectui_t.setUpdatedAt(rs.getDate(\"UPDATED_AT\"));" + "\r\n" + 
"return	myprojectui_t;" + "\r\n" + 
"}" + "\r\n" + 
"});" + "\r\n" + 
"return userlist;" + "\r\n" + 
"}" + "\r\n" + 
"@Transactional" + "\r\n" + 
"public int saveheader(Myprojectui_t	myprojectui_t) {" + "\r\n" + 
"hibernateTemplate.saveOrUpdate(myprojectui_t);" + "\r\n" + 
"System.out.println(myprojectui_t.getId());" + "\r\n" + 
"return 	myprojectui_t.getId();" + "\r\n" + 
"} }" 
