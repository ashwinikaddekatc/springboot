package com.realnet.fnd.service;

import java.util.List;

import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Lookup_Autofill;

public interface Rn_Menu_Register_Service {
	
	public List<Rn_Lookup_Autofill> MenuRegister_List();
	
	public int addMenuGroupHeader(Rn_Menu_Group_Header menuHeader);
	
	public int addMenuGroupLine(int rowcount, int group_header_id,  String[] group_line_id, String[] name, String[] active);
	
	public List<Rn_Lookup_Autofill> Menu_List(int SR_num);
	
	public int addMenuGroupHeader1(Rn_Menu_Group_Header groupHeader);
	
	public int addMenuGroupLine1(int rowcount, int group_header_id, String[] menu_group_line_id, String[] name,
			String[] active);
}
