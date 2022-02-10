
package com.realnet.fnd.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Forms_Setup_Dao;
import com.realnet.fnd.model.Rn_Forms_Component_Setup;
import com.realnet.fnd.model.Rn_Forms_Setup;
@Service
public class	Rn_Forms_Setup_ServiceImpl	implements	Rn_Forms_Setup_Service
 {

@Autowired
private 	Rn_Forms_Setup_Dao	rn_forms_setup_dao;

@Override 
public List<Rn_Forms_Setup> prefield(int u_id)
{
return	rn_forms_setup_dao.prefield(u_id);
 }
@Override
public List<Rn_Forms_Setup> userlist() 
{
return	rn_forms_setup_dao.userlist();
}
@Override
public int save(int rowcount,String[]	form_id,String[]	form_name,	String[]	form_desc,	String[]	related_to,	String[]	page_event,	String[]	button_caption	) 
{
return	rn_forms_setup_dao.save(rowcount,form_id,form_name,	form_desc,	related_to,	page_event,	button_caption	);
}public int saveheader(Rn_Forms_Setup	rn_forms_setup_t){return 	rn_forms_setup_dao.saveheader(rn_forms_setup_t);}
public int addmenuGroupLine(int rowcount,int	form_id,String	component_id[],String	label[],	String	type[],	String	mandatory[],	String	readonly[],	String	values[],	String	sp[]	)
{return 	rn_forms_setup_dao.addmenuGroupLine(rowcount,form_id,component_id,label,	type,	mandatory,	readonly,	values,	sp	);
}
public List<Rn_Forms_Component_Setup> update_group_menu_line(int 	form_id)
{
return 	rn_forms_setup_dao.update_group_menu_line(form_id);
}
}