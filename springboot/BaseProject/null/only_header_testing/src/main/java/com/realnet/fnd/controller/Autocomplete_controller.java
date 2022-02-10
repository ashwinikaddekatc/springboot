package com.realnet.fnd.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_User_Access_Menulist;

import org.springframework.stereotype.Controller;

@Controller
public class Autocomplete_controller 
{

	@Autowired
	private HibernateConfiguration hibernateConfiguration;
	
	
	
	@RequestMapping(value = "/autocomplete_sp_1", method = RequestMethod.GET)
	 public void loadItem_Description1( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Autofill> menulist = new ArrayList<Rn_Lookup_Autofill>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure for autocomplete tables---------");

			
			
			   
			   HttpSession session=request.getSession(false);
	           String sp_auto=(String)session.getAttribute("auto_1");
	           
	           System.out.println("------------sp for auto complete-----------------:"+sp_auto);
	           

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call "+sp_auto+"()}");
			 		
		   System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
		   System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			
		                   System.out.println(" show data2---------"+data2);
			 			
		                   Rn_Lookup_Autofill mapping = new Rn_Lookup_Autofill();
			 			
			 			mapping.setTable_name(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}
	
	
	@RequestMapping(value = "/autocomplete_sp_2", method = RequestMethod.GET)
	 public void loadItem_Description2( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Autofill> menulist = new ArrayList<Rn_Lookup_Autofill>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure for autocomplete tables---------");

			
			
			   
			   HttpSession session=request.getSession(false);
	           String sp_auto=(String)session.getAttribute("auto_2");
	           
	           System.out.println("------------sp for auto complete-----------------:"+sp_auto);
	           

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call "+sp_auto+"()}");
			 		
		   System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
		   System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			
		                   System.out.println(" show data2---------"+data2);
			 			
		                   Rn_Lookup_Autofill mapping = new Rn_Lookup_Autofill();
			 			
			 			mapping.setTable_name(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}
	
	
	
	@RequestMapping(value = "/autocomplete_sp_3", method = RequestMethod.GET)
	 public void loadItem_Description3( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Autofill> menulist = new ArrayList<Rn_Lookup_Autofill>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure for autocomplete tables---------");

			
			
			   
			   HttpSession session=request.getSession(false);
	           String sp_auto=(String)session.getAttribute("auto_3");
	           
	           System.out.println("------------sp for auto complete-----------------:"+sp_auto);
	           

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call "+sp_auto+"()}");
			 		
		   System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
		   System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			
		                   System.out.println(" show data2---------"+data2);
			 			
		                   Rn_Lookup_Autofill mapping = new Rn_Lookup_Autofill();
			 			
			 			mapping.setTable_name(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}
	
	
	@RequestMapping(value = "/autocomplete_sp_4", method = RequestMethod.GET)
	 public void loadItem_Description4( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Autofill> menulist = new ArrayList<Rn_Lookup_Autofill>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure for autocomplete tables---------");

			
			
			   
			   HttpSession session=request.getSession(false);
	           String sp_auto=(String)session.getAttribute("auto_4");
	           
	           System.out.println("------------sp for auto complete-----------------:"+sp_auto);
	           

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call "+sp_auto+"()}");
			 		
		   System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
		   System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			
		                   System.out.println(" show data2---------"+data2);
			 			
		                   Rn_Lookup_Autofill mapping = new Rn_Lookup_Autofill();
			 			
			 			mapping.setTable_name(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}
	
	
	
	
	
	@RequestMapping(value = "/autocomplete_tables", method = RequestMethod.GET)
	 public void loadItem_Description( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Autofill> menulist = new ArrayList<Rn_Lookup_Autofill>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure for autocomplete tables---------");

			
	           CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call sp_autocomplete1()}");
			 		
		   System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
		   System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			
		                   System.out.println(" show data2---------"+data2);
			 			
		                   Rn_Lookup_Autofill mapping = new Rn_Lookup_Autofill();
			 			
			 			mapping.setTable_name(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}
	
	//---------------------sp for linetables--------------------------------
	
	
	@RequestMapping(value = "/autocomplete_tables_lines", method = RequestMethod.GET)
	 public void line_sp( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Autofill> menulist = new ArrayList<Rn_Lookup_Autofill>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure for autocomplete tables lines---------");

			
	           CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call sp_autocomplete1()}");
			 		
		   System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
		   System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			
		                   System.out.println(" show data2---------"+data2);
			 			
		                   Rn_Lookup_Autofill mapping = new Rn_Lookup_Autofill();
			 			
			 			mapping.setTable_name(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}
	
	
	
	@RequestMapping(value = "/load_tables", method = RequestMethod.GET)
	 public void load_tables( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Autofill> menulist = new ArrayList<Rn_Lookup_Autofill>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure for autocomplete tables---------");

			

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call sp_autocomplete1()}");
			 		
		   System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
		   System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			
		                   System.out.println(" show data2---------"+data2);
			 			
		                   Rn_Lookup_Autofill mapping = new Rn_Lookup_Autofill();
			 			
			 			mapping.setTable_name(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}	
	
	
	@RequestMapping(value = "/load_columns", method = RequestMethod.GET)
	 public void load_columns( HttpServletRequest request, HttpServletResponse response)  {
  	 List<Rn_User_Access_Menulist> menulist = new ArrayList<Rn_User_Access_Menulist>();
  	 try {
			 
  		
			    System.out.println("-----------------load columns---------------------------------");
			    String query=request.getParameter("p_query");
  		 
		        //String query="rn_cd_t";

			 
			   System.out.println("--------table  name------------"+query);
			 
			 
			 if(query==null)
			 {
				 
				 //query="select COLUMN_NAME COL_NAME, TABLE_NAME TAB_NAME FROM COLS where ( TABLE_NAME ='Please Select Table')";
				 
				  query="SELECT COLUMN_NAME,TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'db_19_10' AND TABLE_NAME ='Please Select Table'";
			 }
			 
			 
			 System.out.println("------sql query for colummns--------------"+query);
			 

//					List<String> list = new ArrayList<String>();
			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call RN_SP_GET_COLUMNS_T(?)}");
			 		
			 		cStmt.setString(1,query);
			 		
			 		
			 		//cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			 		
			 		ResultSet rs1 = cStmt.executeQuery();
			 		
			 		//ResultSet rs1 = (ResultSet) cStmt.getObject(2);
			 		while (rs1.next()) 
			 		{
			 			//int data = rs1.getInt(1);
			 			String data1 = rs1.getString(1);
			 			String data2 = rs1.getString(2);
			 			
			 			//System.out.println("New Data   :::   "+data);
//			 			list.add(data);
			 			Rn_User_Access_Menulist menu = new Rn_User_Access_Menulist();
			 				
			 			//menu.setMenu_name(data2);
			 			menu.setCol_name(data1);
			 			
			 			String temp=data2.concat(".");
			 		    temp=temp.concat(data1); 
			 			
			 			menu.setCol_table(temp);
			 			
			 			menulist.add(menu);
			 		}
			 		} catch (SQLException e) {
			 		e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

   }
	
	
	@RequestMapping(value = "/load_columns2", method = RequestMethod.GET)
	 public void load_columns2( HttpServletRequest request, HttpServletResponse response)  {
 	 List<Rn_User_Access_Menulist> menulist = new ArrayList<Rn_User_Access_Menulist>();
 	 try {
			 
 		
			    System.out.println("-----------------load columns---------------------------------");
			    String query=request.getParameter("p_query");
 		 
		        //String query="rn_cd_t";

			 
			   System.out.println("--------table  name------------"+query);
			 
			 
			 if(query==null)
			 {
				 
				 //query="select COLUMN_NAME COL_NAME, TABLE_NAME TAB_NAME FROM COLS where ( TABLE_NAME ='Please Select Table')";
				 
				  query="SELECT COLUMN_NAME,TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'db_19_10' AND TABLE_NAME ='Please Select Table'";
			 }
			 
			 
			 System.out.println("------sql query for colummns--------------"+query);
			 

//					List<String> list = new ArrayList<String>();
			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call RN_SP_GET_COLUMNS_T(?)}");
			 		
			 		cStmt.setString(1,query);
			 		
			 		
			 		//cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			 		
			 		ResultSet rs1 = cStmt.executeQuery();
			 		
			 		//ResultSet rs1 = (ResultSet) cStmt.getObject(2);
			 		while (rs1.next()) 
			 		{
			 			//int data = rs1.getInt(1);
			 			String data1 = rs1.getString(1);
			 			String data2 = rs1.getString(2);
			 			
			 			//System.out.println("New Data   :::   "+data);
//			 			list.add(data);
			 			Rn_User_Access_Menulist menu = new Rn_User_Access_Menulist();
			 				
			 			//menu.setMenu_name(data2);
			 			menu.setCol_name(data1);
			 			
			 			String temp=data1.concat(".");
			 		    temp=temp.concat(data2); 
			 			
			 			menu.setCol_table(temp);
			 			
			 			menulist.add(menu);
			 		}
			 		} catch (SQLException e) {
			 		e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

  }	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}