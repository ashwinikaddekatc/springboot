
package com.realnet.test_module1.MyProjectModule.dao;
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
import com.realnet.test_module1.model.Myprojectui_t;
@Repository("Myprojectui_dao")
public class	Myprojectui_daoimpl	implements	Myprojectui_dao
{
@Autowired
private JdbcTemplate jdbcTemplate;
@Autowired
private HibernateTemplate hibernateTemplate;

@Override
public List<Myprojectui_t> userlist() 
{
String sql ="SELECT ID, TEXTFIELD1, TEXTFIELD2, TEXTFIELD3, TEXTFIELD4,  FROM	MYPROJECTUI_T";
List<Myprojectui_t> userlist = jdbcTemplate.query(sql, new RowMapper<Myprojectui_t>()
{	
@Override
public	Myprojectui_t	mapRow(ResultSet rs, int rowNum) throws SQLException
{
Myprojectui_t	myprojectui_t = new	Myprojectui_t();

myprojectui_t.setId(rs.getInt("ID"));
myprojectui_t.setTextfield1(rs.getString("TEXTFIELD1"));
myprojectui_t.setTextfield2(rs.getString("TEXTFIELD2"));
myprojectui_t.setTextfield3(rs.getString("TEXTFIELD3"));
myprojectui_t.setTextfield4(rs.getString("TEXTFIELD4"));
return	myprojectui_t;
}
});
return userlist;}

@Override
public List<Myprojectui_t> prefield(int u_id)
{
String sql ="SELECT
ID, TEXTFIELD1, TEXTFIELD2, TEXTFIELD3, TEXTFIELD4, created_by, updated_by, created_at, updated_at, account_idFROM	MYPROJECTUI_T	WHERE	ID= "+u_id+"";
List<Myprojectui_t> userlist = jdbcTemplate.query(sql, new RowMapper<Myprojectui_t>() {
@Override
public	Myprojectui_t	mapRow(ResultSet rs, int rowNum) throws SQLException {
Myprojectui_t	myprojectui_t = new 	Myprojectui_t();
myprojectui_t.setId(rs.getInt("ID"));
myprojectui_t.setTextfield1(rs.getString("TEXTFIELD1"));
myprojectui_t.setTextfield2(rs.getString("TEXTFIELD2"));
myprojectui_t.setTextfield3(rs.getString("TEXTFIELD3"));
myprojectui_t.setTextfield4(rs.getString("TEXTFIELD4"));
myprojectui_t.setExtn1(rs.getString("EXTN1"));
myprojectui_t.setExtn2(rs.getString("EXTN2"));
myprojectui_t.setExtn3(rs.getString("EXTN3"));
myprojectui_t.setExtn4(rs.getString("EXTN4"));
myprojectui_t.setExtn5(rs.getString("EXTN5"));
myprojectui_t.setExtn6(rs.getString("EXTN6"));
myprojectui_t.setExtn7(rs.getString("EXTN7"));
myprojectui_t.setExtn8(rs.getString("EXTN8"));
myprojectui_t.setExtn9(rs.getString("EXTN9"));
myprojectui_t.setExtn10(rs.getString("EXTN10"));
myprojectui_t.setExtn11(rs.getString("EXTN11"));
myprojectui_t.setExtn12(rs.getString("EXTN12"));
myprojectui_t.setExtn13(rs.getString("EXTN13"));
myprojectui_t.setExtn14(rs.getString("EXTN14"));
myprojectui_t.setExtn15(rs.getString("EXTN15"));
myprojectui_t.setFlex1(rs.getString("FLEX1"));
myprojectui_t.setFlex2(rs.getString("FLEX2"));
myprojectui_t.setFlex3(rs.getString("FLEX3"));
myprojectui_t.setFlex4(rs.getString("FLEX4"));
myprojectui_t.setFlex5(rs.getString("FLEX5"));
myprojectui_t.setAccount_id(rs.getInt("ACCOUNT_ID"));
myprojectui_t.setCreatedBy(rs.getLong("CREATED_BY"));
myprojectui_t.setCreatedAt(rs.getDate("CREATED_AT"));
myprojectui_t.setUpdatedBy(rs.getLong("UPDATED_BY"));
myprojectui_t.setUpdatedAt(rs.getDate("UPDATED_AT"));
return	myprojectui_t;
}
});
return userlist;
}
@Transactional
public int saveheader(Myprojectui_t	myprojectui_t) {
hibernateTemplate.saveOrUpdate(myprojectui_t);
System.out.println(myprojectui_t.getId());
return 	myprojectui_t.getId();
} }