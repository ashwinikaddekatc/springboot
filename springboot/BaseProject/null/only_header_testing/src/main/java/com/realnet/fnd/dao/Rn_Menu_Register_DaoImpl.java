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

import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Group_Line;




@Repository("Rn_Menu_Register_Dao")

public class Rn_Menu_Register_DaoImpl implements Rn_Menu_Register_Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Rn_Lookup_Autofill> MenuRegister_List() {
		String sql = " SELECT MAIN_MENU_ID,MAIN_MENU_NAME,MAIN_MENU_ACTION_NAME,MAIN_MENU_ICON,ENABLE_FLAG,END_DATE FROM RN_MENU_REGISTER_T";

		List<Rn_Lookup_Autofill> rn_lookup_autofill_list = jdbcTemplate.query(sql, new RowMapper<Rn_Lookup_Autofill>() {

			@Override
			public Rn_Lookup_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Lookup_Autofill rn_lookup_autofill = new Rn_Lookup_Autofill();
				
				rn_lookup_autofill.setMain_menu_id(rs.getInt("MAIN_MENU_ID"));
				rn_lookup_autofill.setMain_menu_name(rs.getString("MAIN_MENU_NAME"));
				rn_lookup_autofill.setMain_menu_action_name(rs.getString("MAIN_MENU_ACTION_NAME"));
				rn_lookup_autofill.setMain_menu_icon(rs.getString("MAIN_MENU_ICON"));
				rn_lookup_autofill.setEnable_flag(rs.getString("ENABLE_FLAG"));
				
				if(rs.getDate("END_DATE")!=null)
				{
					DateFormat ser2 = new SimpleDateFormat("YYYY-MM-DD");
					String dateStr2 = ser2.format(rs.getDate("END_DATE"));
					rn_lookup_autofill.setEnd_date(dateStr2);	
					try {
						System.out.println(ser2.parse(rs.getString("END_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}
				System.out.println("menu register run");
				
				
				return rn_lookup_autofill;
			}
			
		});
		
		return rn_lookup_autofill_list;
	}


	@Override
	public int addMenuGroupHeader(Rn_Menu_Group_Header menuHeader) {
		return 0;
	}


	@Override
	public int addMenuGroupLine(int rowcount, int group_header_id, String[] group_line_id, String[] name,
			String[] active) {
		return 0;
	}
	
	@Override
	public List<Rn_Lookup_Autofill> Menu_List(int SR_num) {
		String sql = " SELECT MAIN_MENU_ID,MAIN_MENU_NAME,MAIN_MENU_ACTION_NAME,MAIN_MENU_ICON,ENABLE_FLAG,END_DATE FROM RN_MENU_REGISTER_T WHERE MAIN_MENU_ID=";
List<Rn_Lookup_Autofill> rn_lookup_autofill_list = jdbcTemplate.query(sql+SR_num , new RowMapper<Rn_Lookup_Autofill>() {

			@Override
			public Rn_Lookup_Autofill mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_Lookup_Autofill rn_lookup_autofill = new Rn_Lookup_Autofill();
				
				rn_lookup_autofill.setMain_menu_id(rs.getInt("MAIN_MENU_ID"));
				rn_lookup_autofill.setMain_menu_name(rs.getString("MAIN_MENU_NAME"));
				rn_lookup_autofill.setMain_menu_action_name(rs.getString("MAIN_MENU_ACTION_NAME"));
				rn_lookup_autofill.setMain_menu_icon(rs.getString("MAIN_MENU_ICON"));
				rn_lookup_autofill.setEnable_flag(rs.getString("ENABLE_FLAG"));
				
				if(rs.getDate("END_DATE")!=null)
				{
					DateFormat ser1 = new SimpleDateFormat("YYYY-MM-DD");
					String dateStr1 = ser1.format(rs.getDate("END_DATE"));
					rn_lookup_autofill.setEnd_date(dateStr1);	
					try {
						System.out.println(ser1.parse(rs.getString("END_DATE")));
					} catch (ParseException e) {
							e.printStackTrace();
					}
				}
				
				System.out.println("menu register run");
				System.out.println("main menu id  sujitsssss"+rs.getInt("MAIN_MENU_ID")+"menu name :-"+rs.getString("MAIN_MENU_NAME")+"Action namr"+rs.getString("MAIN_MENU_ICON"));
				
				return rn_lookup_autofill;
			}
			
		});
		
		return rn_lookup_autofill_list;
	}
	
	
	
	
	
	@Override
	@Transactional
	public int addMenuGroupHeader1(Rn_Menu_Group_Header rn_menu_group_header) {
		hibernateTemplate.saveOrUpdate(rn_menu_group_header);
		System.out.println(rn_menu_group_header.getMenu_group_header_id());		
		return rn_menu_group_header.getMenu_group_header_id();
	}


	@Override
	@Transactional
	public int addMenuGroupLine1(int rowcount, int group_header_id, String[] menu_group_line_id, String[] name,
			String[] active) {
			int infonum = 0;
		
			for (int i = 0; i < rowcount; i++) {
				if (group_header_id != 0){
				Rn_Menu_Group_Line rn_menu_group_line= new Rn_Menu_Group_Line();
				if (menu_group_line_id[i] != null && menu_group_line_id[i].length() > 0) {
					infonum = Integer.parseInt(menu_group_line_id[i]);
				
				} else {
					infonum = rn_menu_group_line.getMenu_group_line_id();
				}

				rn_menu_group_line.setMenu_group_header_id(group_header_id);
				rn_menu_group_line.setMenu_group_line_id(infonum);
				rn_menu_group_line.setName(name[i]);
				rn_menu_group_line.setActive(active[i]);
				
				hibernateTemplate.saveOrUpdate(rn_menu_group_line);
			}
		}
		return 1;
	}


	
	
}
