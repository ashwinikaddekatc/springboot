
package com.realnet.fnd.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.realnet.fnd.model.Rn_module_setup_t;
public interface	Rn_module_setup_t_service
{
public List<Rn_module_setup_t> prefield(int u_id);
public List<Rn_module_setup_t> prefield_module(int m_id);

public List<Rn_module_setup_t> rn_module_values();
public int saveheader(Rn_module_setup_t	rn_module_setup_t);
public int save(int rowcount,String	id,String	module_name,	String	description,	String	module_prefix,String porject_id	,String copy_to); 
}