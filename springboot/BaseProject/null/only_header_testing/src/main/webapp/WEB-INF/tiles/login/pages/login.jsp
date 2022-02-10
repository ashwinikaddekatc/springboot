<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
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
      width: 48%;
/*        margin: auto;  */
        margin-left: 40%; 
  }
  
  
   
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
    			<style type="text/css">

</style>
<script type="text/javascript">
	function submitOnEnter(inputElement, event) {
		if (event.keyCode == 13) {
			ValidateLogOn();
		}
	}

	function ValidateLogOn() {

		var userName = document.getElementById('username').value;
		var password = document.getElementById('password').value;

		if (userName == "") {
			alert("Enter Username.");
		} else if (password == "") {
			alert("Enter Password.");
		} else {
			var forms = document.getElementById('login-form');
			forms.submit();
		}
	}
</script>

<script>
var ab=${ab};
if (ab==5){
alert("Password reset successfully please check your mail...");

}
if (ab==3){
	alert("Sending mail is failed");

	}
</script>
</head>

<body style="background-color: #fff;" onLoad="noBack();" onpageshow="if (event.persisted) noBack();" onUnload="">



    <div id="wrapper" >

        <!-- Sidebar -->
       <div id="sidebar-wrapper" style ="overflow:hidden;border-right: 2px solid   #d6d6c2;" >
         <center> 
          <img src="resources/login_assets/map.png" alt="Kirloskar" style="width:90%;height:87%;margin-left: 8%;margin-top: 6%;" class="w3-container w3-orange1"/>
        </center>
        
        <div style="max-width:900px;margin-left: 9%;position:absolute;bottom:10px;width:90%;top:76%;">

            
            <div id="myCarousel" class="carousel slide" data-ride="carousel" style="position: absolute;">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
      <li data-target="#myCarousel" data-slide-to="4"></li>
      <li data-target="#myCarousel" data-slide-to="5"></li>
      <li data-target="#myCarousel" data-slide-to="6"></li>
      <li data-target="#myCarousel" data-slide-to="7"></li>
      
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="resources/genset/1.jpg" alt="" width="460" height="345">
      </div>

      <div class="item">
        <img src="resources/genset/2.jpg" alt="" width="460" height="345">
      </div>
    
      <div class="item">
        <img src="resources/genset/3.jpg" alt="" width="460" height="345">
      </div>

      <div class="item">
        <img src="resources/genset/4.jpg" alt="" width="460" height="345">
      </div>
        <div class="item">
        <img src="resources/genset/5.jpg" alt="" width="460" height="345">
      </div>

      <div class="item">
        <img src="resources/genset/1.jpg" alt="" width="460" height="345">
      </div>
    
      <div class="item">
        <img src="resources/genset/2.jpg" alt="" width="460" height="345">
      </div>

      <div class="item">
        <img src="resources/genset/3.jpg" alt="" width="460" height="345">
      </div>
         <div class="item">
        <img src="resources/genset/4.jpg" alt="" width="460" height="345">
      </div>
         <div class="item">
        <img src="resources/genset/5.jpg" alt="" width="460" height="345">
      </div>
    </div>
<br /><br /><br /><br /><br /><br /><br />
    <!-- Left and right controls -->
    <a class="left " href="#myCarousel" role="button" data-slide="250">
     
      <span class="sr-only">Previous</span>
    </a>
    <a class="right " href="#myCarousel" role="button" data-slide="250">
      <span class="sr-only">Next</span>
    </a>
  </div>
    </div>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper" style="background-color: #fff;" >
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                      <marquee  scrolldelay="150" style="margin-left:3%;margin-right:3%;margin-top: 2%;font-size: 18px;"> 
        <c:forEach var="contact" items="${NewsLogin}" varStatus="status" >
		 <a href="${contact.news_url}" target="_blank">${contact.news_title} </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</c:forEach>
            
        
    </marquee>
    
    <center>
        
        <img src="resources/login_assets/Kirloskar.png" alt="Kirloskar" style="width:30%;height:15%;margin-top: 5%;margin-bottom: 7%;">
    </center>
    
    
                        <h1 ><center><font size="5" >
<br />                            </font></center>
								<small><center>
									<font size="3%" >
									<br /><br />
<br />                                    </font>
                                    </center>
								</small>
							</h1>
                                <div class="widget-body" style="margin-left: 25%;margin-right: 15%;margin-top: 5%;">
                                        <h4 class="header  lighter bigger" style="color:#000;">
												
												 Enter Your Credentials
											</h4>

											<div class="space-6"></div>

<form id="login-form" name="login-form" class="login-form" action="rn_login" method="post">

									<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
														
														 <spring:bind path="kwm_users.user_name">
																<!--USERNAME--><input name="${status.expression}" id="username" type="text" class="form-control" placeholder="Username" onfocus="this.value=''" pattern="[A-Z,a-z,0-9]{1,25}"/><!--END USERNAME-->
															</spring:bind>															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
														<spring:bind path="kwm_users.password">
   														 <!--PASSWORD--><input name="${status.expression}" id="password" type="password" class="form-control" placeholder="Password" onfocus="this.value=''" onKeyPress="submitOnEnter(this, event)" required/><!--END PASSWORD-->
    														</spring:bind>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space">
												</div>

													<div class="clearfix">
														<label class="">
															<!--<input type="checkbox" class="ace" />-->
                                                            <span class=""> <a href="forgotpassword" style="font-size:10px;">Forgot Password</a></span>
														</label>
														<input type="submit" name="submit" value="Login" class="width-35 pull-right btn btn-sm btn-primary" onKeyPress="submitOnEnter(this, event)" /><!--END LOGIN BUTTON-->
															<div style="color: red;">${message}</div>
   													 <!--<input type="submit" name="submit" value="Register" class="register" />-->
													</div>
													
													
													<div class="clearfix">
														<label class="">
															<!--<input type="checkbox" class="ace" />-->
                                                            <span class=""> <a href="forgotpassword2" style="font-size:10px;">Forgot Password2</a></span>
														</label>
														
													</div>
													
													
												

													<div class="space-4"></div>
												</fieldset>

   
											</form>
    </div>
    <!--<label  style="margin-top: 15%;margin-left: 5%;color: #2872dd;">© 2016 Kirloskar Oil Engines</label>
    <img src="Kirloskar.png" alt="Kirloskar" style="width:15%;height:7%;margin-top: 0%;margin-left: 30%;"> -->
</div>
                    </div>
                </div>
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
<script type="text/javascript">
	        window.history.forward();
	        function noBack()
	        {
	            window.history.forward();
	        }
		</script>

</body>

</html>
