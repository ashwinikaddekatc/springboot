
package com.realnet.fnd.dao;

import java.util.List;

import com.realnet.fnd.model.Rn_Section1;
import com.realnet.fnd.model.Rn_Section2;
import com.realnet.fnd.model.Rn_Section3;
import com.realnet.fnd.model.Rn_Section4;
import com.realnet.fnd.model.Rn_Section5;
import com.realnet.fnd.model.Rn_Dashboard_T;

public interface Rn_Dashboard_Dao_T {
	public List<Rn_Dashboard_T> userlist();

	public List<Rn_Dashboard_T> prefield(int u_id);
	
	public List<Rn_Dashboard_T> dashByProjectId(int u_id);

	public int save(int rowcount, String[] id, String[] dashboard_name, String[] description, String[] chart_type,
			String[] sql_query);

	public int saveheader(Rn_Dashboard_T rn_dashboard_t);

	public int addSection1(int dash_id,String chart_size[],String chart1[],String chart2[],String chart3[]);	
	public int addSection2(int dash_id,String chart_size2[],String chart4[],String chart5[],String chart6[]);
	public int addSection3(int dash_id,String chart_size3[],String chart7[],String chart8[],String chart9[]);
	public int addSection4(int dash_id,String chart_size4[],String chart10[],String chart11[],String chart12[]);
	public int addSection5(int dash_id,String chart_size5[],String chart13[],String chart14[],String chart15[]);
	public List<Rn_Section1> section1List(int u_id);
	public List<Rn_Section2> section2List(int u_id);
	public List<Rn_Section3> section3List(int u_id);
	public List<Rn_Section4> section4List(int u_id);
	public List<Rn_Section5> section5List(int u_id);

}