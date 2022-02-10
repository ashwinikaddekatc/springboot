<%@page import="com.realnet.fnd.model.Rn_Sub_Menu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page import=" java.util.HashMap" import="java.util.Map"
	import="java.util.List" import="java.util.ArrayList" 
	import="com.realnet.fnd.model.Rn_Main_Menu"
	import="com.realnet.fnd.model.Rn_Sub_Menu" %>




<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		


	</head>
	<body class="no-skin">


<!-- <div id="sidebar" class="sidebar responsive"> -->
<!-- 				<script type="text/javascript"> -->
<!-- 					try{ace.settings.check('sidebar' , 'fixed')}catch(e){} -->
<!-- 				</script> -->



    
    <style>
      h1 { text-align: center; }
      #test123 { position:relative; margin:0px auto; padding:0px; width: 195px; height: 520px; overflow: auto; background-color:transparent}
      .container .content { background-image: url('./azusa.jpg'); width: 1280px; height: 720px; }
    </style>
    <script type="text/javascript">
    $(function(){

    	  $('#test123').slimScroll({
              allowPageScroll: true
          });

        });
</script>
   



			<div id="testDiv">
				
			 <div id="logo">
				<small>
						<center> 
							<img src="resources/login_assets/Kirloskar.png"  alt="Kirloskar" style="width:50%;height:15%;margin-right:0%;">
						</center>
						</small>
                    
				</div> 
				<div id="test123">
				
				<ul class="nav nav-list" >
				
				
						
					<c:forEach var="menus" items="${menus}">
						<c:set var="header_menu_id">${menus.main_menu_id}</c:set>
						
						<% 
						
						String id = (String) pageContext.getAttribute("header_menu_id"); 
							System.out.println(id);
							if (session.getAttribute("submenu"+id) != null) {
						
						%>
							
						<li class="">
							<a href="#" class="dropdown-toggle" >
								<i class="${menus.main_menu_icon}" ></i>
								<span class="menu-text"> ${menus.main_menu_name} </span>
					
						
<!-- 								<a href="sujit">Sujit Shelar</a>	 -->
								<b class="arrow fa fa-angle-down"></b>
							</a>
								
							<b class="arrow"></b>
						
						
						<ul class="submenu" id="sub">
						
							<%  
    					
     							ArrayList<Rn_Sub_Menu> mgls = new ArrayList<Rn_Sub_Menu>();
    								System.out.println(id);
     								mgls = (ArrayList<Rn_Sub_Menu>) session.getAttribute("submenu"+id); 
 									for(Rn_Sub_Menu mgl : mgls){ 
 							%> 
								
 								<li class=""> 
 								
 									<a href="<%=mgl.getSub_menu_action_name()%>" > 
 										<i class="menu-icon fa fa-caret-right"></i> 
      
          								     <%=mgl.getSub_menu_name()%>                                 
 
 									
 									
 									</a>
 									
 								
 								</li> 
 							<% 		
 									} 
     					
     						%>
							
						
						</ul>
						</li>
						<%
							} 
	     					else {
	     						
	     						%>
	     						
	     						<li class="">
							<a href="${menus.main_menu_action_name}" >
								<i class="menu-icon fa ${menus.main_menu_icon}"></i>
								<span class="menu-text">${menus.main_menu_name} </span>
					
	     						</a>
								
						</li>	
	     						
	     				<%		
	     					}
						
						%>
						
						
 					</c:forEach> 
															
							</ul>
							</div>
					</div>


			

				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
<!-- 			</div> -->
</body>
</html>