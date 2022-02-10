
package com.realnet.fnd.service;
import java.util.List;

import com.realnet.fnd.model.Rn_Dashboard_T;
public interface	Rn_Dashboard_Service_T
{
public List<Rn_Dashboard_T> prefield(int u_id);
public List<Rn_Dashboard_T> userlist();
public int save(int rowcount,String[]	id,String[]	dashboard_name,	String[]	description,	String[]	chart_type,	String[]	sql_query	) ;
public int saveheader(Rn_Dashboard_T	rn_dashboard_t);
}