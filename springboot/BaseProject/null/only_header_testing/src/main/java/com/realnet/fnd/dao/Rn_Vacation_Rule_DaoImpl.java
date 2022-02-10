package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Menu_Group_Autofill;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Group_Line;

@Repository("Rn_Vacation_Rule_Dao")
public class Rn_Vacation_Rule_DaoImpl implements Rn_Vacation_Rule_Dao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private HibernateConfiguration hibernateConfiguration;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	//////////////////////////////////////// menu group   -pramila ////////////////////////
	
	public List<Rn_Menu_Group_Autofill> get_group_menu()
	{
		String sql = "SELECT MENU_GROUP_HEADER_ID, MENU_NAME, DESCRIPTION, HEADER_ACTIVE, HEADER_START_DATE, HEADER_END_DATE FROM RN_MENU_GROUP_HEADER_T";
		List<Rn_Menu_Group_Autofill> rn_menu_group_autofill_list = jdbcTemplate.query(sql, new RowMapper<Rn_Menu_Group_Autofill>() {
					@Override
					public Rn_Menu_Group_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Rn_Menu_Group_Autofill rn_menu_group_autofill = new Rn_Menu_Group_Autofill(rowNum, sql);						
						
						rn_menu_group_autofill.setMenu_group_header_id(rs.getInt("MENU_GROUP_HEADER_ID"));
						rn_menu_group_autofill.setMenu_name(rs.getString("MENU_NAME"));
						rn_menu_group_autofill.setDescription(rs.getString("DESCRIPTION"));
						rn_menu_group_autofill.setHeader_active(rs.getString("HEADER_ACTIVE"));
						if(rs.getDate("HEADER_START_DATE")!=null)
						{
							DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
							String dateStr1 = ser1.format(rs.getDate("HEADER_START_DATE"));
							rn_menu_group_autofill.setHeader_start_date(dateStr1);	
							try {
								System.out.println(ser1.parse(rs.getString("HEADER_START_DATE")));
							} catch (ParseException e) {
									e.printStackTrace();
							}
						}
						
						if(rs.getDate("HEADER_END_DATE")!=null)
						{
							DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
							String dateStr1 = ser1.format(rs.getDate("HEADER_END_DATE"));
							rn_menu_group_autofill.setHeader_end_date(dateStr1);	
							try {
								System.out.println(ser1.parse(rs.getString("HEADER_END_DATE")));
							} catch (ParseException e) {
									e.printStackTrace();
							}
						}
						
						
						System.out.println("kdsjfksdjf   "+rn_menu_group_autofill.getMenu_group_header_id());
						
						
						return rn_menu_group_autofill;
					}
					
				});
				
				return rn_menu_group_autofill_list;
	}

	
	@Transactional
	public int savemenu(Rn_Menu_Group_Header rn_menu_group_header)
	{
		hibernateTemplate.saveOrUpdate(rn_menu_group_header);
		System.out.println(rn_menu_group_header.getMenu_group_header_id());
		
		return rn_menu_group_header.getMenu_group_header_id();
	
	}
	
	
	@Override
	@Transactional
	public int addmenuGroupLine(int rowcount,int menu_group_id, String report_group_line_id1[], String name1[],String active1[])
	{
		 int infonum=0,menuid=0; 			
				
	
		for(int i=0; i<rowcount; i++)
		{
			if(menu_group_id!=0)

					{			
						menuid= Integer.parseInt(name1[i]);
						
						Rn_Menu_Group_Line rn_menu_group_line = new Rn_Menu_Group_Line();
				 
							 if(report_group_line_id1[i]!=null && report_group_line_id1[i].length()>0)
							 { 
									infonum= Integer.parseInt(report_group_line_id1[i]); 
							 }
							 else 
							{ 
								 infonum= rn_menu_group_line.getMenu_group_line_id();
							 }
								 	
							 rn_menu_group_line.setMenu_group_header_id(menu_group_id);
							 rn_menu_group_line.setMenu_group_line_id(infonum);
							 rn_menu_group_line.setMenu_id(menuid);
							 rn_menu_group_line.setActive(active1[i]);
														 
							 hibernateTemplate.saveOrUpdate(rn_menu_group_line);			 		
													
				}
		}
		
		return 1;
				
	}
	
	//update data laod
	public List<Rn_Menu_Group_Autofill> update_group_menu(int menu_grp_id)
	{
		String sql = "SELECT MENU_GROUP_HEADER_ID, MENU_NAME, DESCRIPTION, HEADER_ACTIVE, HEADER_START_DATE, HEADER_END_DATE FROM RN_MENU_GROUP_HEADER_T WHERE MENU_GROUP_HEADER_ID=";
		List<Rn_Menu_Group_Autofill> rn_menu_group_autofill_list = jdbcTemplate.query(sql+menu_grp_id, new RowMapper<Rn_Menu_Group_Autofill>() {
					@Override
					public Rn_Menu_Group_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Rn_Menu_Group_Autofill  rn_menu_group_autofill = new Rn_Menu_Group_Autofill(rowNum, sql);						
						
						 rn_menu_group_autofill.setMenu_group_header_id(rs.getInt("MENU_GROUP_HEADER_ID"));
						 rn_menu_group_autofill.setMenu_name(rs.getString("MENU_NAME"));
						 rn_menu_group_autofill.setDescription(rs.getString("DESCRIPTION"));
						 rn_menu_group_autofill.setHeader_active(rs.getString("HEADER_ACTIVE"));
						
						if(rs.getDate("HEADER_START_DATE")!=null)
						{
							DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
							String dateStr1 = ser1.format(rs.getDate("HEADER_START_DATE"));
							 rn_menu_group_autofill.setHeader_start_date(dateStr1);	
							try {
								System.out.println(ser1.parse(rs.getString("HEADER_START_DATE")));
							} catch (ParseException e) {
									e.printStackTrace();
							}
						}
						
						if(rs.getDate("HEADER_END_DATE")!=null)
						{
							DateFormat ser1 = new SimpleDateFormat("dd-MM-yyyy");
							String dateStr1 = ser1.format(rs.getDate("HEADER_END_DATE"));
							 rn_menu_group_autofill.setHeader_end_date(dateStr1);	
							try {
								System.out.println(ser1.parse(rs.getString("HEADER_END_DATE")));
							} catch (ParseException e) {
									e.printStackTrace();
							}
						}
						
						
						System.out.println("kdsjfksdjf   "+rn_menu_group_autofill.getMenu_group_header_id());
						
						
						return  rn_menu_group_autofill;
					}
					
				});
				
				return rn_menu_group_autofill_list;
	}
	
	public List<Rn_Menu_Group_Autofill> update_group_menu_line(int menu_grp_id)
	{
		String sql = "SELECT MGL.MENU_GROUP_LINE_ID,MGL.ACTIVE, MGL.MENU_ID, KMG.MAIN_MENU_NAME FROM RN_MENU_GROUP_LINES_T MGL, RN_MENU_REGISTER_T KMG WHERE MGL.MENU_ID=KMG.MAIN_MENU_ID AND MGL.MENU_GROUP_HEADER_ID=";
		List<Rn_Menu_Group_Autofill> rn_menu_group_autofill_list = jdbcTemplate.query(sql+menu_grp_id, new RowMapper<Rn_Menu_Group_Autofill>() {
					@Override
					public Rn_Menu_Group_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Rn_Menu_Group_Autofill  rn_menu_group_autofill = new Rn_Menu_Group_Autofill(rowNum, sql);						
						
						 rn_menu_group_autofill.setMenu_group_line_id(rs.getInt("MENU_GROUP_LINE_ID"));
						 rn_menu_group_autofill.setMain_menu_name(rs.getString("MAIN_MENU_NAME"));
						 rn_menu_group_autofill.setActive(rs.getString("ACTIVE"));
						 rn_menu_group_autofill.setMain_menu_id(rs.getInt("MENU_ID"));						
												
						System.out.println("kdsjfksdjf   "+ rn_menu_group_autofill.getMenu_group_header_id());
						
						
						return  rn_menu_group_autofill;
					}
					
				});
				
				return rn_menu_group_autofill_list;
		
	}
}
