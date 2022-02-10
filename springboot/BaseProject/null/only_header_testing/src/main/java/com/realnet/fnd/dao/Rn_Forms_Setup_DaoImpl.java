
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
import com.realnet.fnd.model.Rn_Forms_Component_Setup;
import com.realnet.fnd.model.Rn_Forms_Setup;
@Repository("Rn_Forms_Setup_Dao")
public class	Rn_Forms_Setup_DaoImpl	implements	Rn_Forms_Setup_Dao
{
@Autowired
private JdbcTemplate jdbcTemplate;
@Autowired
private HibernateTemplate hibernateTemplate;
@Autowired
private HibernateConfiguration hibernateConfiguration; 

@Override
public List<Rn_Forms_Setup> userlist() 
{
String sql ="SELECT	FORM_ID,FORM_NAME,	FORM_DESC,	RELATED_TO,	PAGE_EVENT,	BUTTON_CAPTION	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	FROM	RN_FORMS_SETUP_T";
List<Rn_Forms_Setup> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Forms_Setup>()
{	
@Override
public	Rn_Forms_Setup	mapRow(ResultSet rs, int rowNum) throws SQLException
{
Rn_Forms_Setup	rn_forms_setup_t = new	Rn_Forms_Setup();

rn_forms_setup_t.setForm_id(rs.getInt("FORM_ID"));
rn_forms_setup_t.setForm_name(rs.getString("FORM_NAME"));
rn_forms_setup_t.setForm_desc(rs.getString("FORM_DESC"));
rn_forms_setup_t.setRelated_to(rs.getString("RELATED_TO"));
rn_forms_setup_t.setPage_event(rs.getString("PAGE_EVENT"));
rn_forms_setup_t.setButton_caption(rs.getString("BUTTON_CAPTION"));
rn_forms_setup_t.setAttribute1(rs.getString("ATTRIBUTE1"));
rn_forms_setup_t.setAttribute2(rs.getString("ATTRIBUTE2"));
rn_forms_setup_t.setAttribute3(rs.getString("ATTRIBUTE3"));
rn_forms_setup_t.setAttribute4(rs.getString("ATTRIBUTE4"));
rn_forms_setup_t.setAttribute5(rs.getString("ATTRIBUTE5"));
rn_forms_setup_t.setAttribute6(rs.getString("ATTRIBUTE6"));
rn_forms_setup_t.setAttribute7(rs.getString("ATTRIBUTE7"));
rn_forms_setup_t.setAttribute8(rs.getString("ATTRIBUTE8"));
rn_forms_setup_t.setAttribute9(rs.getString("ATTRIBUTE9"));
rn_forms_setup_t.setAttribute10(rs.getString("ATTRIBUTE10"));
rn_forms_setup_t.setAttribute11(rs.getString("ATTRIBUTE11"));
rn_forms_setup_t.setAttribute12(rs.getString("ATTRIBUTE12"));
rn_forms_setup_t.setAttribute13(rs.getString("ATTRIBUTE13"));
rn_forms_setup_t.setAttribute14(rs.getString("ATTRIBUTE14"));
rn_forms_setup_t.setAttribute15(rs.getString("ATTRIBUTE15"));
rn_forms_setup_t.setFlex1(rs.getString("FLEX1"));
rn_forms_setup_t.setFlex2(rs.getString("FLEX2"));
rn_forms_setup_t.setFlex3(rs.getString("FLEX3"));
rn_forms_setup_t.setFlex4(rs.getString("FLEX4"));
rn_forms_setup_t.setFlex5(rs.getString("FLEX5"));
return	rn_forms_setup_t;
}
});
return userlist;}

@Override
public List<Rn_Forms_Setup> prefield(int u_id)
{
String sql ="SELECT FORM_ID,FORM_NAME,	FORM_DESC,	RELATED_TO,	PAGE_EVENT,	BUTTON_CAPTION	,ATTRIBUTE1,	ATTRIBUTE2,	ATTRIBUTE3,	ATTRIBUTE4,	ATTRIBUTE5,	ATTRIBUTE6,	ATTRIBUTE7,	ATTRIBUTE8,	ATTRIBUTE9,	ATTRIBUTE10,	ATTRIBUTE11,	ATTRIBUTE12,	ATTRIBUTE13,	ATTRIBUTE14,	ATTRIBUTE15,	FLEX1,	FLEX2,	FLEX3,	FLEX4,	FLEX5	FROM	RN_FORMS_SETUP_T	WHERE	FORM_ID= "+u_id+"";
List<Rn_Forms_Setup> userlist = jdbcTemplate.query(sql, new RowMapper<Rn_Forms_Setup>() {
@Override
public	Rn_Forms_Setup	mapRow(ResultSet rs, int rowNum) throws SQLException {
Rn_Forms_Setup	rn_forms_setup_t = new 	Rn_Forms_Setup();
rn_forms_setup_t.setForm_id(rs.getInt("FORM_ID"));
rn_forms_setup_t.setForm_name(rs.getString("FORM_NAME"));
rn_forms_setup_t.setForm_desc(rs.getString("FORM_DESC"));
rn_forms_setup_t.setRelated_to(rs.getString("RELATED_TO"));
rn_forms_setup_t.setPage_event(rs.getString("PAGE_EVENT"));
rn_forms_setup_t.setButton_caption(rs.getString("BUTTON_CAPTION"));
rn_forms_setup_t.setAttribute1(rs.getString("ATTRIBUTE1"));
rn_forms_setup_t.setAttribute2(rs.getString("ATTRIBUTE2"));
rn_forms_setup_t.setAttribute3(rs.getString("ATTRIBUTE3"));
rn_forms_setup_t.setAttribute4(rs.getString("ATTRIBUTE4"));
rn_forms_setup_t.setAttribute5(rs.getString("ATTRIBUTE5"));
rn_forms_setup_t.setAttribute6(rs.getString("ATTRIBUTE6"));
rn_forms_setup_t.setAttribute7(rs.getString("ATTRIBUTE7"));
rn_forms_setup_t.setAttribute8(rs.getString("ATTRIBUTE8"));
rn_forms_setup_t.setAttribute9(rs.getString("ATTRIBUTE9"));
rn_forms_setup_t.setAttribute10(rs.getString("ATTRIBUTE10"));
rn_forms_setup_t.setAttribute11(rs.getString("ATTRIBUTE11"));
rn_forms_setup_t.setAttribute12(rs.getString("ATTRIBUTE12"));
rn_forms_setup_t.setAttribute13(rs.getString("ATTRIBUTE13"));
rn_forms_setup_t.setAttribute14(rs.getString("ATTRIBUTE14"));
rn_forms_setup_t.setAttribute15(rs.getString("ATTRIBUTE15"));
rn_forms_setup_t.setFlex1(rs.getString("FLEX1"));
rn_forms_setup_t.setFlex2(rs.getString("FLEX2"));
rn_forms_setup_t.setFlex3(rs.getString("FLEX3"));
rn_forms_setup_t.setFlex4(rs.getString("FLEX4"));
rn_forms_setup_t.setFlex5(rs.getString("FLEX5"));
return	rn_forms_setup_t;
}
});
return userlist;
}
@Override
@Transactional
public int save(int rowcount,String[]	form_id,String[]	form_name,	String[]	form_desc,	String[]	related_to,	String[]	page_event,	String[]	button_caption	)
{
int infonum = 0;
for (int i = 0; i < rowcount; i++) 
{
Rn_Forms_Setup	rn_forms_setup_t= new	Rn_Forms_Setup();
if (form_id != null && form_id.length > 0) 
{
infonum = Integer.parseInt(form_id[i]);
} else 
{
infonum = rn_forms_setup_t.getForm_id();
}
rn_forms_setup_t.setForm_id(infonum);
rn_forms_setup_t.setForm_name(form_name[i]);
rn_forms_setup_t.setForm_desc(form_desc[i]);
rn_forms_setup_t.setRelated_to(related_to[i]);
rn_forms_setup_t.setPage_event(page_event[i]);
rn_forms_setup_t.setButton_caption(button_caption[i]);
hibernateTemplate.saveOrUpdate(rn_forms_setup_t);
}
return 1;
}

@Transactional
public int saveheader(Rn_Forms_Setup	rn_forms_setup_t)
{
hibernateTemplate.saveOrUpdate(rn_forms_setup_t);
System.out.println(rn_forms_setup_t.getForm_id());
return 	rn_forms_setup_t.getForm_id();
} 



@Override
@Transactional
public int addmenuGroupLine(int rowcount,int	form_id,String	component_id[],String	label[],	String	type[],	String	mandatory[],	String	readonly[],	String	values[],	String	sp[]	){
int infonum=0; 

System.out.println("\nrowcount="+rowcount+"\nform id="+form_id+"\ncomponent id="+component_id+"\n label="+label+"\ntype="+type+"\nmandatory="+mandatory+"\nreadonly="+readonly+"\nvalues="+values+"\nsp="+sp);

for(int i=0; i<rowcount; i++)
{
if(form_id!=0)
{
Rn_Forms_Component_Setup	rn_forms_component_setup_t=new	Rn_Forms_Component_Setup();
if (component_id[i] != null && 	component_id[i].length() > 0)
{ 
infonum = Integer.parseInt(component_id[i]);
}
else
{
infonum =rn_forms_component_setup_t.getComponent_id(); 
}
rn_forms_component_setup_t.setForm_id(form_id);
rn_forms_component_setup_t.setComponent_id(infonum);
rn_forms_component_setup_t.setLabel(label[i]);
rn_forms_component_setup_t.setType(type[i]);

if(mandatory!=null){
	rn_forms_component_setup_t.setMandatory("Y");
}else{
	rn_forms_component_setup_t.setMandatory("N");
}

if(readonly!=null){
	rn_forms_component_setup_t.setReadonly("Y");
}else{
	rn_forms_component_setup_t.setReadonly("N");
}


rn_forms_component_setup_t.setDrop_values(values[i]);
rn_forms_component_setup_t.setSp(sp[i]);
hibernateTemplate.saveOrUpdate(rn_forms_component_setup_t);
 }
}
return 1; 
}





public List<Rn_Forms_Component_Setup> update_group_menu_line(int 	form_id)
{
String sql = "select	component_id,label,	type,	mandatory,	readonly,	drop_values,	sp	,attribute1,	attribute2,	attribute3,	attribute4,	attribute5,	attribute6,	attribute7,	attribute8,	attribute9,	attribute10,	attribute11,	attribute12,	attribute13,	attribute14,	attribute15,	flex1,	flex2,	flex3,	flex4,	flex5	from	rn_forms_component_setup_t	where	form_id='"+form_id+"'";
List<Rn_Forms_Component_Setup> group_list = jdbcTemplate.query(sql, new RowMapper<Rn_Forms_Component_Setup>() {
@Override
public 	Rn_Forms_Component_Setup	mapRow(ResultSet rs, int rowNum) throws SQLException {
Rn_Forms_Component_Setup	rn_forms_component_setup_t = new 	Rn_Forms_Component_Setup();
rn_forms_component_setup_t.setComponent_id(rs.getInt("COMPONENT_ID"));
rn_forms_component_setup_t.setLabel(rs.getString("LABEL"));
rn_forms_component_setup_t.setType(rs.getString("TYPE"));
rn_forms_component_setup_t.setMandatory(rs.getString("MANDATORY"));
rn_forms_component_setup_t.setReadonly(rs.getString("READONLY"));
rn_forms_component_setup_t.setDrop_values(rs.getString("DROP_VALUES"));
rn_forms_component_setup_t.setSp(rs.getString("SP"));
rn_forms_component_setup_t.setAttribute1(rs.getString("ATTRIBUTE1"));
rn_forms_component_setup_t.setAttribute2(rs.getString("ATTRIBUTE2"));
rn_forms_component_setup_t.setAttribute3(rs.getString("ATTRIBUTE3"));
rn_forms_component_setup_t.setAttribute4(rs.getString("ATTRIBUTE4"));
rn_forms_component_setup_t.setAttribute5(rs.getString("ATTRIBUTE5"));
rn_forms_component_setup_t.setAttribute6(rs.getString("ATTRIBUTE6"));
rn_forms_component_setup_t.setAttribute7(rs.getString("ATTRIBUTE7"));
rn_forms_component_setup_t.setAttribute8(rs.getString("ATTRIBUTE8"));
rn_forms_component_setup_t.setAttribute9(rs.getString("ATTRIBUTE9"));
rn_forms_component_setup_t.setAttribute10(rs.getString("ATTRIBUTE10"));
rn_forms_component_setup_t.setAttribute11(rs.getString("ATTRIBUTE11"));
rn_forms_component_setup_t.setAttribute12(rs.getString("ATTRIBUTE12"));
rn_forms_component_setup_t.setAttribute13(rs.getString("ATTRIBUTE13"));
rn_forms_component_setup_t.setAttribute14(rs.getString("ATTRIBUTE14"));
rn_forms_component_setup_t.setAttribute15(rs.getString("ATTRIBUTE15"));
rn_forms_component_setup_t.setFlex1(rs.getString("FLEX1"));
rn_forms_component_setup_t.setFlex2(rs.getString("FLEX2"));
rn_forms_component_setup_t.setFlex3(rs.getString("FLEX3"));
rn_forms_component_setup_t.setFlex4(rs.getString("FLEX4"));
rn_forms_component_setup_t.setFlex5(rs.getString("FLEX5"));
return 	rn_forms_component_setup_t;
}
});
return group_list;
}}