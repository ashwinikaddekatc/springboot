
package com.realnet.fnd.dao;
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
// import com.mysql.cj.xdevapi.SessionFactory;
import com.realnet.fnd.model.Logger_level;
@Repository("Logger_level_dao")
public class	Logger_level_daoimpl	implements	Logger_level_dao
{
@Autowired
private JdbcTemplate jdbcTemplate;
@Autowired
private HibernateTemplate hibernateTemplate;
/*
@Override
public List<Logger_level> userlist() 
{
String sql ="SELECT	ID,LOGGER_LEVEL,FILE_NAME,	TEXTFIELD3,	TEXTFIELD4	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	 FROM	LOGGER_LEVEL";
List<Logger_level> userlist = jdbcTemplate.query(sql, new RowMapper<Logger_level>()
{	
@Override
public	Logger_level	mapRow(ResultSet rs, int rowNum) throws SQLException
{
Logger_level	logger_level = new	Logger_level();

logger_level.setId(rs.getInt("ID"));
logger_level.setLogger_level(rs.getString("LOGGER_LEVEL"));
logger_level.setFile_name(rs.getString("FILE_NAME"));
logger_level.setTextfield3(rs.getString("TEXTFIELD3"));
logger_level.setTextfield4(rs.getString("TEXTFIELD4"));
logger_level.setAttribute1(rs.getString("ATTRIBUTE1"));
logger_level.setAttribute2(rs.getString("ATTRIBUTE2"));
logger_level.setAttribute3(rs.getString("ATTRIBUTE3"));
logger_level.setAttribute4(rs.getString("ATTRIBUTE4"));
logger_level.setAttribute5(rs.getString("ATTRIBUTE5"));
logger_level.setAttribute6(rs.getString("ATTRIBUTE6"));
logger_level.setAttribute7(rs.getString("ATTRIBUTE7"));
logger_level.setAttribute8(rs.getString("ATTRIBUTE8"));
logger_level.setAttribute9(rs.getString("ATTRIBUTE9"));
logger_level.setAttribute10(rs.getString("ATTRIBUTE10"));
logger_level.setAttribute11(rs.getString("ATTRIBUTE11"));
logger_level.setAttribute12(rs.getString("ATTRIBUTE12"));
logger_level.setAttribute13(rs.getString("ATTRIBUTE13"));
logger_level.setAttribute14(rs.getString("ATTRIBUTE14"));
logger_level.setAttribute15(rs.getString("ATTRIBUTE15"));
logger_level.setFlex1(rs.getString("FLEX1"));
logger_level.setFlex2(rs.getString("FLEX2"));
logger_level.setFlex3(rs.getString("FLEX3"));
logger_level.setFlex4(rs.getString("FLEX4"));
logger_level.setFlex5(rs.getString("FLEX5"));
return	logger_level;
}
});
return userlist;}

@Override
public List<Logger_level> prefield(int u_id)
{
String sql ="SELECT ID,LOGGER_LEVEL,FILE_NAME,TEXTFIELD3,	TEXTFIELD4	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	,ACCOUNT_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE FROM	LOGGER_LEVEL	WHERE	ID= "+u_id+"";
List<Logger_level> userlist = jdbcTemplate.query(sql, new RowMapper<Logger_level>() {
@Override
public	Logger_level	mapRow(ResultSet rs, int rowNum) throws SQLException {
Logger_level	logger_level = new 	Logger_level();
logger_level.setId(rs.getInt("ID"));
logger_level.setLogger_level(rs.getString("LOGGER_LEVEL"));
logger_level.setFile_name(rs.getString("FILE_NAME"));
logger_level.setTextfield3(rs.getString("TEXTFIELD3"));
logger_level.setTextfield4(rs.getString("TEXTFIELD4"));
logger_level.setAttribute1(rs.getString("ATTRIBUTE1"));
logger_level.setAttribute2(rs.getString("ATTRIBUTE2"));
logger_level.setAttribute3(rs.getString("ATTRIBUTE3"));
logger_level.setAttribute4(rs.getString("ATTRIBUTE4"));
logger_level.setAttribute5(rs.getString("ATTRIBUTE5"));
logger_level.setAttribute6(rs.getString("ATTRIBUTE6"));
logger_level.setAttribute7(rs.getString("ATTRIBUTE7"));
logger_level.setAttribute8(rs.getString("ATTRIBUTE8"));
logger_level.setAttribute9(rs.getString("ATTRIBUTE9"));
logger_level.setAttribute10(rs.getString("ATTRIBUTE10"));
logger_level.setAttribute11(rs.getString("ATTRIBUTE11"));
logger_level.setAttribute12(rs.getString("ATTRIBUTE12"));
logger_level.setAttribute13(rs.getString("ATTRIBUTE13"));
logger_level.setAttribute14(rs.getString("ATTRIBUTE14"));
logger_level.setAttribute15(rs.getString("ATTRIBUTE15"));
logger_level.setFlex1(rs.getString("FLEX1"));
logger_level.setFlex2(rs.getString("FLEX2"));
logger_level.setFlex3(rs.getString("FLEX3"));
logger_level.setFlex4(rs.getString("FLEX4"));
logger_level.setFlex5(rs.getString("FLEX5"));
logger_level.setAccount_id(rs.getInt("ACCOUNT_ID"));
logger_level.setCreated_by(rs.getInt("CREATED_BY"));
logger_level.setCreation_date(rs.getDate("CREATION_DATE"));
logger_level.setLast_updated_by(rs.getInt("LAST_UPDATED_BY"));
logger_level.setLast_update_date(rs.getDate("LAST_UPDATE_DATE"));
return	logger_level;
}
});
return userlist;
}
@Override
@Transactional
public int save(int rowcount,String	id,String	logger_level,	String	file_name,	String	textfield3,	String	textfield4	)
{
int infonum = 0;
for (int i = 0; i < rowcount; i++) 
{
Logger_level	logger_level1= new	Logger_level();
if (id != null && id.length() > 0) 
{
infonum = Integer.parseInt(id);
} else 
{
infonum = logger_level1.getId();
}
logger_level1.setId(infonum);
logger_level1.setLogger_level(logger_level);
logger_level1.setFile_name(file_name);
logger_level1.setTextfield3(textfield3);
logger_level1.setTextfield4(textfield4);
hibernateTemplate.saveOrUpdate(logger_level1);
}
return 1;
}*/

@Transactional
public int saveheader(Logger_level	logger_level)
{
hibernateTemplate.saveOrUpdate(logger_level);
System.out.println(logger_level.getId());
return 	logger_level.getId();
}

@Override
public List<Logger_level> rn_logger_level(String logger_level_var) {
	String sql ="SELECT	LOGGER_LEVEL FROM	LOGGER_LEVEL WHERE CREATED_BY= "+logger_level_var+"";
	List<Logger_level> userlist = jdbcTemplate.query(sql, new RowMapper<Logger_level>() {
		@Override
		public	Logger_level	mapRow(ResultSet rs, int rowNum) throws SQLException {
		Logger_level	logger_level = new 	Logger_level();
		logger_level.setLogger_level(rs.getString("LOGGER_LEVEL"));
		
		return	logger_level;
		}
		});
		return userlist;
		}

@Override
public List<Logger_level> loggerDetails() {
	String sql = "SELECT ID,LOGGER_LEVEL,FILENAME,OBJECT_TYPE,COMP_TYPE,LOCATION,STATUS FROM LOGGER_LEVEL WHERE STATUS='N'";
	List<Logger_level> userlist = jdbcTemplate.query(sql, new RowMapper<Logger_level>() {
		@Override
		public Logger_level mapRow(ResultSet rs, int rowNum) throws SQLException {
			Logger_level rn_project_setup_t = new Logger_level();
			
			rn_project_setup_t.setId(rs.getInt("ID"));
			rn_project_setup_t.setFilename(rs.getString("FILENAME"));
			
			rn_project_setup_t.setObject_type(rs.getString("OBJECT_TYPE"));
			rn_project_setup_t.setComp_type(rs.getString("COMP_TYPE"));
			
			rn_project_setup_t.setLocation(rs.getString("LOCATION"));
			rn_project_setup_t.setStatus(rs.getString("STATUS"));
			
			rn_project_setup_t.setLogger_level("LOGGER_LEVEL");
			
			return rn_project_setup_t;
		}
	});
	return userlist;
}

@Override
public List<Logger_level> userlist() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Logger_level> prefield(int u_id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int save(int rowcount, String id, String textfield1, String textfield2, String textfield3, String textfield4) {
	// TODO Auto-generated method stub
	return 0;
}
}