<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
      "http://www.w3.org/TR/html4/loose.dtd">  
<!-- <!DOCTYPE html> -->

<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>REAL_NET</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<style>
		
 			#sujit { 
		           position:fixed;  
 			        height:100%;  
 			        width:100%;  
  			        overflow: scroll;   
 			} 
 			@media only screen and (min-width: 991px) { 
 			    #sujit { 
 			       position:fixed; 
 			       height:100%;
 			       width:86%; 
 			       overflow: scroll;
 			    } 
 			} 
 			
 		
			</style>
			<style type="text/css">
body {
    overflow:hidden;
}
</style>
	
	</head>
<!-- 	style="overflow-y:hidden;" -->
	<body class="no-skin"  >

 		<div id="navbar" class="navbar navbar-default" >
 			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
 		
			<tiles:insertAttribute name="header" />
		</div>
	
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div id="sidebar" class="sidebar responsive">
			<div id="testDiv2">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
		
				<tiles:insertAttribute name="menu" />
				</div>
			</div>
			<div id="tabbody" class="main-content" >
				<div id="sujit"  >
				<tiles:insertAttribute name="body" />
				</div>
				
<!-- 				<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> -->
<!-- 				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i> -->
		
	
			</div>

			
		
<script type="text/javascript">
    $(function(){

    	  $('#testDiv2').slimScroll({
              allowPageScroll: true
          });

        });
</script>


<script type="text/javascript">

  //enable syntax highlighter
  prettyPrint();

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-3112455-22']);
  _gaq.push(['_setDomainName', 'none']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
	
			
			
		</div><!-- /.main-container -->
	
	
	</body>
</html>
