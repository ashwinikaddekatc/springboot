<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>500 Error Page</title>

		<meta name="description" content="500 Error Page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
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
	
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

	
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

<!-- 							<li> -->
<!-- 								<a href="#">Other Pages</a> -->
<!-- 							</li> -->
							<li class="active">Error 500</li>
						</ul><!-- /.breadcrumb -->

<!-- 						<div class="nav-search" id="nav-search"> -->
<!-- 							<form class="form-search"> -->
<!-- 								<span class="input-icon"> -->
<!-- 									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> -->
<!-- 									<i class="ace-icon fa fa-search nav-search-icon"></i> -->
<!-- 								</span> -->
<!-- 							</form> -->
<!-- 						</div>/.nav-search -->
					</div>

					<div class="page-content">
			
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-random"></i>
												500
											</span>
											Something Went Wrong
										</h1>

										<hr />
										<h3 class="lighter smaller">
											But we are working
											<i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
											on it!
										</h3>

										<div class="space"></div>

										<div>
											<h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="ace-icon fa fa-hand-o-right blue"></i>
													Read the faq
												</li>

												<li>
													<i class="ace-icon fa fa-hand-o-right blue"></i>
													Give us more info on how this specific error occurred!
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>

										<div class="center">
											<a href="javascript:history.back()" class="btn btn-grey">
												<i class="ace-icon fa fa-arrow-left"></i>
												Go Back
											</a>

<!-- 											<a href="/" class="btn btn-primary"> -->
<!-- 												<i class="ace-icon fa fa-tachometer"></i> -->
<!-- 												Dashboard -->
<!-- 											</a> -->
										</div>
									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

<!-- 			<div class="footer"> -->
<!-- 				<div class="footer-inner"> -->
<!-- 					<div class="footer-content"> -->
<!-- 						<span class="bigger-120"> -->
<!-- 							<span class="blue bolder">Ace</span> -->
<!-- 							Application &copy; 2013-2014 -->
<!-- 						</span> -->

<!-- 						&nbsp; &nbsp; -->
<!-- 						<span class="action-buttons"> -->
<!-- 							<a href="#"> -->
<!-- 								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i> -->
<!-- 							</a> -->

<!-- 							<a href="#"> -->
<!-- 								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i> -->
<!-- 							</a> -->

<!-- 							<a href="#"> -->
<!-- 								<i class="ace-icon fa fa-rss-square orange bigger-150"></i> -->
<!-- 							</a> -->
<!-- 						</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> -->
<!-- 				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i> -->
<!-- 			</a> -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="assets/js/jquery.2.1.1.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
	</body>
</html>
