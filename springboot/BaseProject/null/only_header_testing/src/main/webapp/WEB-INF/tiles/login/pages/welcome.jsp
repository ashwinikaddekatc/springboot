<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<!-- <iframe name="frame1" src="NewUserRegistration.jsp"></iframe> -->
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <title>REAL_NET LogIn</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/resources/login_assets/css/bootstrap.min.css" rel="stylesheet'/>"/>

    <!-- Custom CSS -->
    <link href="<c:url value='/resources/login_assets/css/simple-sidebar.css" rel="stylesheet'/>"/>

            <link rel="stylesheet" href="<c:url value='/resources/login_assets/css1/w3.css'/>"/>
            <link rel="stylesheet" href="<c:url value='/resources/login_assets/presentational-only/presentational-only.css'/>"/>
            <script src="<c:url value='/resources/login_assets/presentational-only/presentational-only.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/resources/login_assets/assets1/css/bootstrap.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/login_assets/assets1/font-awesome/4.2.0/css/font-awesome.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/login_assets/assets1/fonts/fonts.googleapis.com.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/login_assets/assets1/css/ace-rtl.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/login_assets/assets1/css/ace.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/login_assets/assets1/css/ace-rtl.min.css'/>"/>

	<link rel="stylesheet" href="<c:url value='resources/slider/bootstrap.min.css'/>"/>
  <script src="<c:url value='/resources/slider/jquery.min.js'/>"></script>
  <script src="<c:url value='/resources/slider/bootstrap.min.js'/>"></script>
  <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 35%;
/*        margin: auto;  */
        margin-left: 40%; */ 
  }
  </style>
    <style>
    
        body {font: normal 0.9em Arial;color: #222;}
        header {display:block; font-size:1.2em; margin-bottom:100px;}
        header a, header span {
            display: inline-block;
            padding: 4px 8px;
            margin-right: 10px;
            border: 2px solid #000;
            background: #DDD;
            color: #000;
            text-decoration: none;
            text-align: center;
            height: 20px;
        }
        header span {background:white;}
        a {color: #1155CC;}
    </style>
<script type="text/javascript">
	function submitOnEnter(inputElement, event) {
		if (event.keyCode == 13) {
			ValidateLogOn();
		}
	}

	function ValidateLogOn() {

	}
	
	
	
	setInterval(function(){ 
		
		
// 	        $.ajax({  
// 	            type : 'POST',  
// 	            url : "koellogin",  

// 	        });
		window.location.href = 'login';
		//window.location.href = 'login';

	}, 5000);
       
    
</script>



<script>
var ab=${ab};
if (ab==5){
alert("Password reset successfully please check your mail...");

}</script>
</head>

<body style="background-color: #fff;">

    <div  style="font-size:15px; color:#808080;">

	<br />
	<br />
       <center>Loading, Please Wait.....</center>
       <div>
         <center>  <img src="resources/login_assets/Kirloskar.png" alt="Kirloskar" style="width:35%;height:48%;margin-left: 32%;margin-top: 6%;" class="w3-container w3-orange1"/>
        </center>
        
       
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper" style="background-color: #fff;" >
           
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<c:url value='/resources/login_assets/js/jquery.js'/>"/></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/resources/login_assets/js/bootstrap.min.js'/>"/></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
    
<script src="<c:url value='/resources/login_assets/assets1/js/jquery.2.1.1.min.js'/>"/></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->
	
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
			
			
			//you don't need this, just used for changing background
			jQuery(function($) {
			
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
				e.preventDefault();
			 });
			 
			});
		</script>

</body>

</html>
