
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

		<title><tiles:insertAttribute name="title" ignore="true" /></title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- 		<!-- bootstrap & fontawesome --> 
<%-- 		<link rel="stylesheet" href="<c:url value='/resources/assets/css/bootstrap.min.css' />" /> --%>
<%-- 		<link rel="stylesheet" href="<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css' />" /> --%>

<!-- 		<!-- page specific plugin styles --> 

<!-- 		<!-- text fonts --> 
<%-- 		<link rel="stylesheet" href="<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />" /> --%>

<!-- 		<!-- ace styles --> 
<%-- 		<link rel="stylesheet" href="<c:url value='/resources/assets/css/ace.min.css' />" class="ace-main-stylesheet" id="main-ace-style" /> --%>

<!-- 		<!--[if lte IE 9]> -->
<!-- 			<link rel="stylesheet" href="<c:url value='/resources/assets/css/ace-part2.min.css' />" class="ace-main-stylesheet" /> -->
<!-- 		<![endif]--> 

<!-- 		<!--[if lte IE 9]> -->
<!-- 		  <link rel="stylesheet" href="<c:url value='/resources/assets/css/ace-ie.min.css' />" /> -->
<!-- 		<![endif]--> 

<!-- 		<!-- inline styles related to this page --> 

<!-- 		<!-- ace settings handler --> 
<%-- 		<script src="<c:url value='/resources/assets/js/ace-extra.min.js' />"></script> --%>

<!-- 		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries --> 

<!-- 		<!--[if lte IE 8]> -->
<!-- 		<script src="<c:url value='/resources/assets/js/html5shiv.min.js' />"></script> -->
<!-- 		<script src="<c:url value='/resources/assets/js/respond.min.js' />"></script> -->
<!-- 		<![endif]--> 
		
			
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/bootstrap.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css'/>"/>

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/jquery-ui.custom.min.css' />"/>
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/chosen.min.css' />"/>
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/datepicker.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/bootstrap-timepicker.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/daterangepicker.min.css' />"/>
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/bootstrap-datetimepicker.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/colorpicker.min.css' />"/>

		<!-- text fonts -->
		<link rel="stylesheet" href="<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />"/>

		<!-- ace styles -->
		<link rel="stylesheet" href="<c:url value='/resources/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style' />"/>

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="<c:url value='/resources/assets/js/ace-extra.min.js'/>" type="text/javascript"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
				
		<script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
		<script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
		<link href="<c:url value="/resources/core/main.css" />" rel="stylesheet"> 
	</head>
 
	<body class="no-skin">
<!-- 		<div id="navbar" class="navbar navbar-default"> -->
<!-- 			<script type="text/javascript"> -->
<!-- 				try{ace.settings.check('navbar' , 'fixed')}catch(e){} -->
<!-- 			</script> -->

			<div class="navbar-container" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="/WASIB_CRP1/" class="navbar-brand">
						<small>
							
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
<!-- 						<li class="grey"> -->
<!-- 							<a data-toggle="dropdown" class="dropdown-toggle" href="#"> -->
<!-- 								<i class="ace-icon fa fa-tasks"></i> -->
<!-- 								<span class="badge badge-grey">4</span> -->
<!-- 							</a> -->

<!-- 							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close"> -->
<!-- 								<li class="dropdown-header"> -->
<!-- 									<i class="ace-icon fa fa-check"></i> -->
<!-- 									4 Tasks to complete -->
<!-- 								</li> -->

<!-- 								<li class="dropdown-content"> -->
<!-- 									<ul class="dropdown-menu dropdown-navbar"> -->
<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<div class="clearfix"> -->
<!-- 													<span class="pull-left">Software Update</span> -->
<!-- 													<span class="pull-right">65%</span> -->
<!-- 												</div> -->

<!-- 												<div class="progress progress-mini"> -->
<!-- 													<div style="width:65%" class="progress-bar"></div> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</li> -->

<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<div class="clearfix"> -->
<!-- 													<span class="pull-left">Hardware Upgrade</span> -->
<!-- 													<span class="pull-right">35%</span> -->
<!-- 												</div> -->

<!-- 												<div class="progress progress-mini"> -->
<!-- 													<div style="width:35%" class="progress-bar progress-bar-danger"></div> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</li> -->

<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<div class="clearfix"> -->
<!-- 													<span class="pull-left">Unit Testing</span> -->
<!-- 													<span class="pull-right">15%</span> -->
<!-- 												</div> -->

<!-- 												<div class="progress progress-mini"> -->
<!-- 													<div style="width:15%" class="progress-bar progress-bar-warning"></div> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</li> -->

<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<div class="clearfix"> -->
<!-- 													<span class="pull-left">Bug Fixes</span> -->
<!-- 													<span class="pull-right">90%</span> -->
<!-- 												</div> -->

<!-- 												<div class="progress progress-mini progress-striped active"> -->
<!-- 													<div style="width:90%" class="progress-bar progress-bar-success"></div> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</li> -->

<!-- 								<li class="dropdown-footer"> -->
<!-- 									<a href="#"> -->
<!-- 										See tasks with details -->
<!-- 										<i class="ace-icon fa fa-arrow-right"></i> -->
<!-- 									</a> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</li> -->

<!-- 						<li class="purple"> -->
<!-- 							<a data-toggle="dropdown" class="dropdown-toggle" href="#"> -->
<!-- 								<i class="ace-icon fa fa-bell icon-animated-bell"></i> -->
<!-- 								<span class="badge badge-important">8</span> -->
<!-- 							</a> -->

<!-- 							<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close"> -->
<!-- 								<li class="dropdown-header"> -->
<!-- 									<i class="ace-icon fa fa-exclamation-triangle"></i> -->
<!-- 									8 Notifications -->
<!-- 								</li> -->

<!-- 								<li class="dropdown-content"> -->
<!-- 									<ul class="dropdown-menu dropdown-navbar navbar-pink"> -->
<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<div class="clearfix"> -->
<!-- 													<span class="pull-left"> -->
<!-- 														<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i> -->
<!-- 														New Comments -->
<!-- 													</span> -->
<!-- 													<span class="pull-right badge badge-info">+12</span> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</li> -->

<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<i class="btn btn-xs btn-primary fa fa-user"></i> -->
<!-- 												Bob just signed up as an editor ... -->
<!-- 											</a> -->
<!-- 										</li> -->

<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<div class="clearfix"> -->
<!-- 													<span class="pull-left"> -->
<!-- 														<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i> -->
<!-- 														New Orders -->
<!-- 													</span> -->
<!-- 													<span class="pull-right badge badge-success">+8</span> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</li> -->

<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<div class="clearfix"> -->
<!-- 													<span class="pull-left"> -->
<!-- 														<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i> -->
<!-- 														Followers -->
<!-- 													</span> -->
<!-- 													<span class="pull-right badge badge-info">+11</span> -->
<!-- 												</div> -->
<!-- 											</a> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</li> -->

<!-- 								<li class="dropdown-footer"> -->
<!-- 									<a href="#"> -->
<!-- 										See all notifications -->
<!-- 										<i class="ace-icon fa fa-arrow-right"></i> -->
<!-- 									</a> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</li> -->

<!-- 						<li class="green"> -->
<!-- 							<a data-toggle="dropdown" class="dropdown-toggle" href="#"> -->
<!-- 								<i class="ace-icon fa fa-envelope icon-animated-vertical"></i> -->
<!-- 								<span class="badge badge-success">5</span> -->
<!-- 							</a> -->

<!-- 							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close"> -->
<!-- 								<li class="dropdown-header"> -->
<!-- 									<i class="ace-icon fa fa-envelope-o"></i> -->
<!-- 									5 Messages -->
<!-- 								</li> -->

<!-- 								<li class="dropdown-content"> -->
<!-- 									<ul class="dropdown-menu dropdown-navbar"> -->
<%-- 									<c:forEach var="contact" items="${DashbordApprover}" varStatus="status" > --%>
									
<!-- 										<li> -->
<%-- 											<a href="approvalaction?claim_num=${contact.claim_number }" class="clearfix"> --%>
<!-- 												<i class="msg-photo btn btn-xs btn-primary fa fa-user"></i> -->
<!-- 												<span class="msg-body"> -->
<!-- 													<span class="msg-title"> -->
<!-- 														<span class="blue"></span> -->
<%-- 														Claim Number ${contact.claim_number} needs your approval. --%>
<!-- 													</span> -->

													
<!-- 												</span> -->
<!-- 											</a> -->
<!-- 										</li> -->
<%-- 									</c:forEach> --%>
												
<!-- 									</ul> -->
<!-- 								</li> -->

<!-- 								<li class="dropdown-footer"> -->
								
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</li> -->

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="<c:url value='/resources/assets/avatars/avatar2.png' />" alt="Jason's Photo" />
								<span class="user-info">
									<small>Welcome,</small>
									${username}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								

								<li>
									<a href="Profile">
										<i class="ace-icon fa fa-user"></i>
										Profile
									</a>
								</li>
								<li>
									<a href="resetpassword">
										<i class="ace-icon fa fa-user"></i>
										Reset Password
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="vacationrule">
										<i class="ace-icon fa fa-user"></i>
										Vacation Rule
									</a>
								</li>
								<li>
									<a href="logout">
										<i class="ace-icon fa fa-power-off"></i>
										Logout
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<!-- /.navbar-container -->
<!-- 		</div> -->

      </body>
      </html>