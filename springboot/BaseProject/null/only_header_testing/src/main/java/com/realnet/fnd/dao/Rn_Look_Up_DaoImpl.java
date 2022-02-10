package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Look_Up;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_Lookup_Values;

@Repository("Rn_Look_Up_Dao")

public class Rn_Look_Up_DaoImpl implements Rn_Look_Up_Dao
{
	@Autowired
	private HibernateConfiguration hibernateConfiguration;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String save_lookup(Rn_Look_Up lookup) {
		hibernateTemplate.saveOrUpdate(lookup);
		return lookup.getLookup_type();
	}

	HttpServletRequest request;
	
	@Override
	@Transactional
	public int save_lookupvalues(int user_id,int count,String lookup_type, String[] lookup_code, String[] meaning, String[] description,
			String[] active_start_date, String[] active_end_date)
	{
		
		for (int i = 0; i < count; i++)
		{		
				Rn_Lookup_Values rn_lookup_values=new Rn_Lookup_Values();
				
				if(lookup_code[i]!= null && lookup_code[i].length()>0)
				{					
					
					rn_lookup_values.setLookup_type(lookup_type);
					rn_lookup_values.setLookup_code(lookup_code[i]);
					rn_lookup_values.setMeaning(meaning[i]);	
					rn_lookup_values.setDescription(description[i]);	
			
				if(active_start_date[i]!=null)
				{
				 Date start_date = null;
			           try {
			        	   start_date = new SimpleDateFormat("dd-MM-yyyy").parse(active_start_date[i]);
			           } catch (ParseException e) {

			               e.printStackTrace();
			           } 			         
			           rn_lookup_values.setActive_start_date(start_date);
				}	
				
				if(active_end_date[i]!=null)
				{
				 Date end_date = null;
			           try {
			        	   end_date = new SimpleDateFormat("dd-MM-yyyy").parse(active_end_date[i]);
			           } catch (ParseException e) {

			               e.printStackTrace();
			           } 			         
			           rn_lookup_values.setActive_end_date(end_date);
				}					
				
				rn_lookup_values.setCreated_by(user_id);
				rn_lookup_values.setLast_updated_by(user_id);
				rn_lookup_values.setEnabled_flag("Y"); 
						
						hibernateTemplate.saveOrUpdate(rn_lookup_values);
				}
		}
				
		return 1;
	}

	/////////////////////////////////////////////////////////////autofill//////////////////////////////
	@Override
	public List<Rn_Look_Up> listLookUp(String tagName) 
	{
		System.out.println("strat of implementation"+tagName);
		
		List<Map> rows = (ArrayList) jdbcTemplate.queryForList("SELECT LOOKUP_TYPE,ACTIVE_START_DATE,"
				+ "ACTIVE_END_DATE FROM RN_LOOKUP_TYPE_T WHERE LOOKUP_TYPE= ?",new Object[] {tagName});
		List<Rn_Look_Up> lookup_Autofill_list = new ArrayList<Rn_Look_Up>();
		
		for(Map row : rows){
			Rn_Look_Up rn_look_up  = new Rn_Look_Up();	
			
			rn_look_up.setLookup_type((row.get("LOOKUP_TYPE").toString()));
						
			
			
			lookup_Autofill_list.add(rn_look_up);
		}
		System.out.println("end of implementation"+tagName);

			return lookup_Autofill_list;

	}

	////////////////////////////////////////////////

	@Override
	public List<Rn_Lookup_Autofill> lookuplist() {
		
		String sql="SELECT LOOKUP_TYPE,ACTIVE_START_DATE,ACTIVE_END_DATE FROM RN_LOOKUP_TYPE_T";	

List<Rn_Lookup_Autofill> lookuplist = jdbcTemplate.query(sql, new RowMapper<Rn_Lookup_Autofill>() {

			@Override
			public Rn_Lookup_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				System.out.println("in dao impl");

				Rn_Lookup_Autofill rn_lookup_autofill = new Rn_Lookup_Autofill();
	
				rn_lookup_autofill.setLookup_type(rs.getString("LOOKUP_TYPE"));
				
				DateFormat cre1 = new SimpleDateFormat("dd-MM-yyyy");
				String dateStr = cre1.format(rs.getString("ACTIVE_START_DATE"));
				rn_lookup_autofill.setActive_start_date(dateStr);	
				try {
					
					Date dc = cre1.parse(rs.getString("ACTIVE_START_DATE").toString());
				} catch (ParseException e) {
						e.printStackTrace();
				}
				
				
				DateFormat cre3 = new SimpleDateFormat("dd-MM-yyyy");
				String dateStr3 = cre3.format(rs.getString("ACTIVE_END_DATE"));
				rn_lookup_autofill.setActive_end_date(dateStr3);
				try {
					Date dc1 = cre1.parse(rs.getString("ACTIVE_END_DATE").toString());
				} catch (ParseException e) {			
					e.printStackTrace();
				}
			
				
				System.out.println(rs.getString("LOOKUP_TYPE"));

				return rn_lookup_autofill;
			}
			
		});
		
		return lookuplist;
	}
	
	
	
	
	@Override
	public List<Rn_Lookup_Autofill> lookuplist1() {
		
		String sql="SELECT LOOKUP_TYPE,ACTIVE_START_DATE,ACTIVE_END_DATE FROM RN_LOOKUP_TYPE_T";	

		List<Rn_Lookup_Autofill> lookuplist = jdbcTemplate.query(sql, new RowMapper<Rn_Lookup_Autofill>() {

			@Override
		public Rn_Lookup_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
				
			
				Rn_Lookup_Autofill rn_lookup_autofill = new Rn_Lookup_Autofill();
	
				rn_lookup_autofill.setLookup_type(rs.getString("LOOKUP_TYPE"));
				
				if(rs.getDate("ACTIVE_START_DATE")!=null)
				{
					DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
					String dateStr1 = ser1.format(rs.getDate("ACTIVE_START_DATE"));
					rn_lookup_autofill.setActive_start_date(dateStr1);	
					try {
						System.out.println(ser1.parse(rs.getString("ACTIVE_START_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}
				
				if(rs.getDate("ACTIVE_END_DATE")!=null)
				{
					DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
					String dateStr1 = ser1.format(rs.getDate("ACTIVE_END_DATE"));
					rn_lookup_autofill.setActive_end_date(dateStr1);	
					try {
						System.out.println(ser1.parse(rs.getString("ACTIVE_END_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}
				

				return rn_lookup_autofill;
			}
			
		});
		
		return lookuplist;
	}
	
	@Override
	public List<Rn_Lookup_Autofill> lookup1(String s1) 
	{
		String sql="SELECT LOOKUP_TYPE,ACTIVE_START_DATE,ACTIVE_END_DATE FROM RN_LOOKUP_TYPE_T WHERE LOOKUP_TYPE = '"+s1+"' ";
		
		List<Rn_Lookup_Autofill> rn_lookup_autofill_list = jdbcTemplate.query(sql, new RowMapper<Rn_Lookup_Autofill>() {

			@Override
			public Rn_Lookup_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
								
				Rn_Lookup_Autofill rn_lookup_autofill = new Rn_Lookup_Autofill();
				
				rn_lookup_autofill.setLookup_type(rs.getString("LOOKUP_TYPE"));	
				
				
				if(rs.getDate("ACTIVE_START_DATE")!=null)
				{
					DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
					String dateStr1 = ser1.format(rs.getDate("ACTIVE_START_DATE"));
					rn_lookup_autofill.setActive_start_date(dateStr1);	
					try {
						System.out.println(ser1.parse(rs.getString("ACTIVE_START_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}
				
				if(rs.getDate("ACTIVE_END_DATE")!=null)
				{
					DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
					String dateStr1 = ser1.format(rs.getDate("ACTIVE_END_DATE"));
					rn_lookup_autofill.setActive_end_date(dateStr1);	
					try {
						System.out.println(ser1.parse(rs.getString("ACTIVE_END_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}					
				return rn_lookup_autofill;
			}
			
		});
		
		return rn_lookup_autofill_list;
		
	}	
	


	@Override
	public List<Rn_Lookup_Autofill> lookup2(String s1)
	{
		
		String sql="SELECT KVL.LOOKUP_CODE,KVL.MEANING,KVL.DESCRIPTION,KVL.ACTIVE_START_DATE,KVL.ACTIVE_END_DATE FROM RN_LOOKUP_TYPE_T KLT,RN_LOOKUP_VALUES_T KVL WHERE KVL.LOOKUP_TYPE=KLT.LOOKUP_TYPE AND KVL.LOOKUP_TYPE='"+s1+"' ";
		
		List<Rn_Lookup_Autofill> rn_lookup_autofill_list = jdbcTemplate.query(sql, new RowMapper<Rn_Lookup_Autofill>() {

			@Override
			public Rn_Lookup_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
								
				Rn_Lookup_Autofill rn_lookup_autofill = new Rn_Lookup_Autofill();
				
				rn_lookup_autofill.setLookup_code(rs.getString("LOOKUP_CODE"));			
				rn_lookup_autofill.setMeaning(rs.getString("MEANING"));	
				rn_lookup_autofill.setDescription(rs.getString("DESCRIPTION"));	
				
				if(rs.getDate("ACTIVE_START_DATE")!=null)
				{
					DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
					String dateStr1 = ser1.format(rs.getDate("ACTIVE_START_DATE"));
					rn_lookup_autofill.setActive_start_date(dateStr1);	
					try {
						System.out.println(ser1.parse(rs.getString("ACTIVE_START_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}
				
				if(rs.getDate("ACTIVE_END_DATE")!=null)
				{
					DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
					String dateStr1 = ser1.format(rs.getDate("ACTIVE_END_DATE"));
					rn_lookup_autofill.setActive_end_date(dateStr1);	
					try {
						System.out.println(ser1.parse(rs.getString("ACTIVE_END_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}
				
				return rn_lookup_autofill;
			}
			
		});
		
		return rn_lookup_autofill_list;
		
	}	
	
		
	
}
