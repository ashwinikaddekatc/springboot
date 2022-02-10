
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
import com.realnet.fnd.model.Rn_Dashboard_T;
import com.realnet.fnd.model.Rn_Section1;
import com.realnet.fnd.model.Rn_Section2;
import com.realnet.fnd.model.Rn_Section3;
import com.realnet.fnd.model.Rn_Section4;
import com.realnet.fnd.model.Rn_Section5;

@Repository("Rn_dashboard_dao")
public class Rn_Dashboard_DaoImpl_T implements Rn_Dashboard_Dao_T {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private HibernateConfiguration hibernateConfiguration;

	@Override
	public List<Rn_Dashboard_T> userlist() {
		String sql = "SELECT	ID,DASHBOARD_NAME,	DESCRIPTION,	CHART_TYPE,	SQL_QUERY	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	FROM	RN_DASHBOARD_T";
		List<Rn_Dashboard_T> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Dashboard_T>() {
			@Override
			public Rn_Dashboard_T mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Dashboard_T rn_dashboard_t = new Rn_Dashboard_T();

				rn_dashboard_t.setId(rs.getInt("ID"));
				rn_dashboard_t.setDashboard_name(rs.getString("DASHBOARD_NAME"));
				rn_dashboard_t.setDescription(rs.getString("DESCRIPTION"));
				rn_dashboard_t.setChart_type(rs.getString("CHART_TYPE"));
				rn_dashboard_t.setSql_query(rs.getString("SQL_QUERY"));
				rn_dashboard_t.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_dashboard_t.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_dashboard_t.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_dashboard_t.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_dashboard_t.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_dashboard_t.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_dashboard_t.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_dashboard_t.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_dashboard_t.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_dashboard_t.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_dashboard_t.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_dashboard_t.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_dashboard_t.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_dashboard_t.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_dashboard_t.setAttribute15(rs.getString("ATTRIBUTE15"));
				rn_dashboard_t.setFlex1(rs.getString("FLEX1"));
				rn_dashboard_t.setFlex2(rs.getString("FLEX2"));
				rn_dashboard_t.setFlex3(rs.getString("FLEX3"));
				rn_dashboard_t.setFlex4(rs.getString("FLEX4"));
				rn_dashboard_t.setFlex5(rs.getString("FLEX5"));
				return rn_dashboard_t;
			}
		});
		return userlist;
	}

	@Override
	public List<Rn_Dashboard_T> prefield(int u_id) {
		String sql = "SELECT ID,DASHBOARD_NAME,	DESCRIPTION,	CHART_TYPE,	SQL_QUERY	,LABEL_NAME,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	FROM	RN_DASHBOARD_T	WHERE	ID= "
				+ u_id + "";
		List<Rn_Dashboard_T> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Dashboard_T>() {
			@Override
			public Rn_Dashboard_T mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Dashboard_T rn_dashboard_t = new Rn_Dashboard_T();
				rn_dashboard_t.setId(rs.getInt("ID"));
				rn_dashboard_t.setDashboard_name(rs.getString("DASHBOARD_NAME"));
				rn_dashboard_t.setDescription(rs.getString("DESCRIPTION"));
				rn_dashboard_t.setChart_type(rs.getString("CHART_TYPE"));
				rn_dashboard_t.setSql_query(rs.getString("SQL_QUERY"));
				rn_dashboard_t.setLabel_name(rs.getString("LABEL_NAME"));
				rn_dashboard_t.setAttribute1(rs.getString("ATTRIBUTE1"));
				rn_dashboard_t.setAttribute2(rs.getString("ATTRIBUTE2"));
				rn_dashboard_t.setAttribute3(rs.getString("ATTRIBUTE3"));
				rn_dashboard_t.setAttribute4(rs.getString("ATTRIBUTE4"));
				rn_dashboard_t.setAttribute5(rs.getString("ATTRIBUTE5"));
				rn_dashboard_t.setAttribute6(rs.getString("ATTRIBUTE6"));
				rn_dashboard_t.setAttribute7(rs.getString("ATTRIBUTE7"));
				rn_dashboard_t.setAttribute8(rs.getString("ATTRIBUTE8"));
				rn_dashboard_t.setAttribute9(rs.getString("ATTRIBUTE9"));
				rn_dashboard_t.setAttribute10(rs.getString("ATTRIBUTE10"));
				rn_dashboard_t.setAttribute11(rs.getString("ATTRIBUTE11"));
				rn_dashboard_t.setAttribute12(rs.getString("ATTRIBUTE12"));
				rn_dashboard_t.setAttribute13(rs.getString("ATTRIBUTE13"));
				rn_dashboard_t.setAttribute14(rs.getString("ATTRIBUTE14"));
				rn_dashboard_t.setAttribute15(rs.getString("ATTRIBUTE15"));
				rn_dashboard_t.setFlex1(rs.getString("FLEX1"));
				rn_dashboard_t.setFlex2(rs.getString("FLEX2"));
				rn_dashboard_t.setFlex3(rs.getString("FLEX3"));
				rn_dashboard_t.setFlex4(rs.getString("FLEX4"));
				rn_dashboard_t.setFlex5(rs.getString("FLEX5"));
				return rn_dashboard_t;
			}
		});
		return userlist;
	}
	
	
	@Override
	public List<Rn_Dashboard_T> dashByProjectId(int u_id) {
		String sql = "SELECT ID,DASHBOARD_NAME,	DESCRIPTION,CHART_TYPE,	SQL_QUERY,LABEL_NAME FROM RN_DASHBOARD_T WHERE is_build!='Y' AND PROJECT_ID= "+ u_id + "";
		List<Rn_Dashboard_T> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Dashboard_T>() {
			@Override
			public Rn_Dashboard_T mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Dashboard_T rn_dashboard_t = new Rn_Dashboard_T();
				rn_dashboard_t.setId(rs.getInt("ID"));
				rn_dashboard_t.setDashboard_name(rs.getString("DASHBOARD_NAME"));
				rn_dashboard_t.setDescription(rs.getString("DESCRIPTION"));
				rn_dashboard_t.setChart_type(rs.getString("CHART_TYPE"));
				rn_dashboard_t.setSql_query(rs.getString("SQL_QUERY"));
				rn_dashboard_t.setLabel_name(rs.getString("LABEL_NAME"));
				
				return rn_dashboard_t;
			}
		});
		return userlist;
	}
	

	@Override
	@Transactional
	public int save(int rowcount, String[] id, String[] dashboard_name, String[] description, String[] chart_type,
			String[] sql_query) {
		int infonum = 0;
		for (int i = 0; i < rowcount; i++) {
			Rn_Dashboard_T rn_dashboard_t = new Rn_Dashboard_T();
			if (id != null && id.length > 0) {
				infonum = Integer.parseInt(id[i]);
			} else {
				infonum = rn_dashboard_t.getId();
			}
			rn_dashboard_t.setId(infonum);
			rn_dashboard_t.setDashboard_name(dashboard_name[i]);
			rn_dashboard_t.setDescription(description[i]);
			rn_dashboard_t.setChart_type(chart_type[i]);
			rn_dashboard_t.setSql_query(sql_query[i]);
			hibernateTemplate.saveOrUpdate(rn_dashboard_t);
		}
		return 1;
	}

	@Transactional
	public int saveheader(Rn_Dashboard_T rn_dashboard_t) {
		hibernateTemplate.saveOrUpdate(rn_dashboard_t);
		System.out.println(rn_dashboard_t.getId());
		return rn_dashboard_t.getId();
	}
	
	
	
	@Override
	@Transactional
	public int addSection1(int dash_id,String chart_size[],String chart1[],String chart2[],String chart3[])
	{
		int i=0; 
		
		Rn_Section1	rn_rb_adhoc_t=new	Rn_Section1();
		
		rn_rb_adhoc_t.setDash_id(dash_id);
		//rn_rb_adhoc_t.setId(infonum);
		rn_rb_adhoc_t.setChart_size1(chart_size[i]);
		rn_rb_adhoc_t.setChart1(chart1[i]);
		rn_rb_adhoc_t.setChart2(chart2[i]);
		if(chart3 !=null)
		{
		  rn_rb_adhoc_t.setChart3(chart3[i]);
		}
		
		hibernateTemplate.saveOrUpdate(rn_rb_adhoc_t);
		 
		
		
		return 1;
	}
	
	
	@Override
	@Transactional
	public int addSection2(int dash_id,String chart_size2[],String chart4[],String chart5[],String chart6[])
	{
		int i=0; 
		
		Rn_Section2	rn_rb_adhoc_t=new	Rn_Section2();
		
		rn_rb_adhoc_t.setDash_id(dash_id);
		//rn_rb_adhoc_t.setId(infonum);
		rn_rb_adhoc_t.setChart_size2(chart_size2[i]);
		rn_rb_adhoc_t.setChart4(chart4[i]);
		
		if(chart5 !=null)
		{
		  rn_rb_adhoc_t.setChart5(chart5[i]);
		}
		if(chart6 !=null)
		{
		  rn_rb_adhoc_t.setChart6(chart6[i]);
		}
		
		hibernateTemplate.saveOrUpdate(rn_rb_adhoc_t);
		 
		
		
		return 1;
	}
	
	@Override
	@Transactional
	public int addSection3(int dash_id,String chart_size3[],String chart7[],String chart8[],String chart9[])
	{
		int i=0; 
		
		Rn_Section3	rn_rb_adhoc_t=new	Rn_Section3();
		
		rn_rb_adhoc_t.setDash_id(dash_id);
		//rn_rb_adhoc_t.setId(infonum);
		rn_rb_adhoc_t.setChart_size3(chart_size3[i]);
		rn_rb_adhoc_t.setChart7(chart7[i]);
		
		if(chart8 !=null)
		{
		  rn_rb_adhoc_t.setChart8(chart8[i]);
		}
		if(chart9 !=null)
		{
		  rn_rb_adhoc_t.setChart9(chart9[i]);
		}
		
		hibernateTemplate.saveOrUpdate(rn_rb_adhoc_t);
		 
		
		
		return 1;
	}
	
	
	
	@Override
	@Transactional
	public int addSection4(int dash_id,String chart_size4[],String chart10[],String chart11[],String chart12[])
	{
		int i=0; 
		
		Rn_Section4	rn_rb_adhoc_t=new	Rn_Section4();
		
		rn_rb_adhoc_t.setDash_id(dash_id);
		//rn_rb_adhoc_t.setId(infonum);
		rn_rb_adhoc_t.setChart_size4(chart_size4[i]);
		rn_rb_adhoc_t.setChart10(chart10[i]);
		
		if(chart11 !=null)
		{
		  rn_rb_adhoc_t.setChart11(chart11[i]);
		}
		if(chart12 !=null)
		{
		  rn_rb_adhoc_t.setChart12(chart12[i]);
		}
		
		hibernateTemplate.saveOrUpdate(rn_rb_adhoc_t);
		 
		
		
		return 1;
	}
	
	@Override
	@Transactional
	public int addSection5(int dash_id,String chart_size5[],String chart13[],String chart14[],String chart15[])
	{
		int i=0; 
		
		Rn_Section5	rn_rb_adhoc_t=new	Rn_Section5();
		
		rn_rb_adhoc_t.setDash_id(dash_id);
		//rn_rb_adhoc_t.setId(infonum);
		rn_rb_adhoc_t.setChart_size5(chart_size5[i]);
		rn_rb_adhoc_t.setChart13(chart13[i]);
		
		if(chart14 !=null)
		{
		  rn_rb_adhoc_t.setChart14(chart14[i]);
		}
		if(chart15 !=null)
		{
		  rn_rb_adhoc_t.setChart15(chart15[i]);
		}
		
		hibernateTemplate.saveOrUpdate(rn_rb_adhoc_t);
		 
		
		
		return 1;
	}
	
	@Override
	public List<Rn_Section1> section1List(int u_id) {
		String sql = "SELECT DASH_ID,CHART_SIZE1,CHART1,CHART2,CHART3 FROM RN_SECTION1_T	WHERE	DASH_ID= "+ u_id + "";
		List<Rn_Section1> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Section1>() {
			@Override
			public Rn_Section1 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Section1 rn_dashboard_t = new Rn_Section1();
				rn_dashboard_t.setDash_id(rs.getInt("DASH_ID"));
				rn_dashboard_t.setChart_size1(rs.getString("CHART_SIZE1"));
				rn_dashboard_t.setChart1(rs.getString("CHART1"));
				rn_dashboard_t.setChart2(rs.getString("CHART2"));
				rn_dashboard_t.setChart3(rs.getString("CHART3"));
				return rn_dashboard_t;
			}
		});
		return userlist;
	}
	
	
	
	@Override
	public List<Rn_Section2> section2List(int u_id) {
		String sql = "SELECT DASH_ID,CHART_SIZE2,CHART4,CHART5,CHART6	FROM RN_SECTION2_T	WHERE	DASH_ID= "+ u_id + "";
		List<Rn_Section2> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Section2>() {
			@Override
			public Rn_Section2 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Section2 rn_dashboard_t = new Rn_Section2();
				rn_dashboard_t.setDash_id(rs.getInt("DASH_ID"));
				rn_dashboard_t.setChart_size2(rs.getString("CHART_SIZE2"));
				rn_dashboard_t.setChart4(rs.getString("CHART4"));
				rn_dashboard_t.setChart5(rs.getString("CHART5"));
				rn_dashboard_t.setChart6(rs.getString("CHART6"));
				return rn_dashboard_t;
			}
		});
		return userlist;
	}
	
	
	
	@Override
	public List<Rn_Section3> section3List(int u_id) {
		String sql = "SELECT DASH_ID,CHART_SIZE3,CHART7,CHART8,CHART9	FROM RN_SECTION3_T	WHERE	DASH_ID= "+ u_id + "";
		List<Rn_Section3> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Section3>() {
			@Override
			public Rn_Section3 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Section3 rn_dashboard_t = new Rn_Section3();
				rn_dashboard_t.setDash_id(rs.getInt("DASH_ID"));
				rn_dashboard_t.setChart_size3(rs.getString("CHART_SIZE3"));
				rn_dashboard_t.setChart7(rs.getString("CHART7"));
				rn_dashboard_t.setChart8(rs.getString("CHART8"));
				rn_dashboard_t.setChart9(rs.getString("CHART9"));
				return rn_dashboard_t;
			}
		});
		return userlist;
	}
	
	
	@Override
	public List<Rn_Section4> section4List(int u_id) {
		String sql = "SELECT DASH_ID,CHART_SIZE4,CHART10,CHART11,CHART12	FROM RN_SECTION4_T	WHERE	DASH_ID= "+ u_id + "";
		List<Rn_Section4> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Section4>() {
			@Override
			public Rn_Section4 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Section4 rn_dashboard_t = new Rn_Section4();
				rn_dashboard_t.setDash_id(rs.getInt("DASH_ID"));
				rn_dashboard_t.setChart_size4(rs.getString("CHART_SIZE4"));
				rn_dashboard_t.setChart10(rs.getString("CHART10"));
				rn_dashboard_t.setChart11(rs.getString("CHART11"));
				rn_dashboard_t.setChart12(rs.getString("CHART12"));
				return rn_dashboard_t;
			}
		});
		return userlist;
	}
	
	
	@Override
	public List<Rn_Section5> section5List(int u_id) {
		String sql = "SELECT DASH_ID,CHART_SIZE5,CHART13,CHART14,CHART15	FROM RN_SECTION5_T	WHERE	DASH_ID= "+ u_id + "";
		List<Rn_Section5> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Section5>() {
			@Override
			public Rn_Section5 mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Section5 rn_dashboard_t = new Rn_Section5();
				rn_dashboard_t.setDash_id(rs.getInt("DASH_ID"));
				rn_dashboard_t.setChart_size5(rs.getString("CHART_SIZE5"));
				rn_dashboard_t.setChart13(rs.getString("CHART13"));
				rn_dashboard_t.setChart14(rs.getString("CHART14"));
				rn_dashboard_t.setChart15(rs.getString("CHART15"));
				return rn_dashboard_t;
			}
		});
		return userlist;
	}
	
	
	
	
	
	

}