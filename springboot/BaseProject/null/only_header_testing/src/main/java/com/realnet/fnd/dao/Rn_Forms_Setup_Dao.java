
package com.realnet.fnd.dao;
import java.util.Date;
import java.util.List;

import com.realnet.fnd.model.Rn_Forms_Component_Setup;
import com.realnet.fnd.model.Rn_Forms_Setup;
public interface	Rn_Forms_Setup_Dao {	
public List<Rn_Forms_Setup> userlist();
public List<Rn_Forms_Setup> prefield(int u_id);
public int save(int rowcount,String[]	form_id,String[]	form_name,	String[]	form_desc,	String[]	related_to,	String[]	page_event,	String[]	button_caption	);
public int saveheader(Rn_Forms_Setup	rn_forms_setup_t);public int addmenuGroupLine(int rowcount,int	form_id,String	component_id[],String	label[],	String	type[],	String	mandatory[],	String	readonly[],	String	values[],	String	sp[]	);
public List<Rn_Forms_Component_Setup> update_group_menu_line(int 	form_id);
}