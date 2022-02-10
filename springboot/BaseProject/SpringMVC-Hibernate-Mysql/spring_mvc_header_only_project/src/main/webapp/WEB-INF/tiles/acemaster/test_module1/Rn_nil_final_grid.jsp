 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8"/>
<title>Realnet Oil Engines Ltd</title>
<meta name="description" content="Static &amp; Dynamic Tables" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="resources/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
<!-- page specific plugin styles -->
<!-- text fonts -->
<link rel="stylesheet" href="resources/assets/fonts/fonts.googleapis.com.css"/>
<!-- ace styles -->
<link rel="stylesheet" href="resources/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
</head>
<body class="no-skin">
<div class="main-container" id="main-container">
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
<li class="active"> User Details</li>
</ul> 
 </div>
<div class="page-content">
 <div class="page-header">
<h1>
rn_nil_final
<div style="float: right; padding-right: 3%;">
<a href="rn_nil_final_entryform"> <i class="fa fa-plus fa-1g" aria-hidden="true" title="Add New Report"></i></a><a href="rn_help_menu?form_code=nil_final_view"><i
									class="fa fa-question-circle fa-1g" title="Help Menu"></i></a>
</div>
</h1>
</div><!-- /.page-header -->
<div class="row">
<div class="col-xs-12">
<div class="row">
<div class="col-xs-12">
<div class="clearfix">
<div class="pull-right tableTools-container"></div>
</div>
<div class="table-header">
rn_nil_final
</div>
<div>
<table   class="table table-striped table-bordered table-hover" id="table1"  cellspacing="0" width="1500px" style="width:100%; margin: 0 auto;">
<thead>
<tr>
<th class="center">select</th>
<th class="center">label1</th>
<th class="center">label2</th>
<th class="center">label3</th>
<th class="center">label4</th>
<th class="center">label5</th>
<th class="center">label6</th>
<th class="center">label7</th>
<th class="center">label8</th>
<th class="center">label9</th>
<%@include file="/WEB-INF/tiles/acemaster/test_module1/rn_nil_final_add_grid.jsp"%>
</tr>
</thead>
<tbody>
<c:forEach var="rn_nil_final" items="${rn_nil_final}" varStatus="status">
<tr>	<td class="center"><a href="rn_nil_final_readonly?id=${rn_nil_final.id}"><i class="fa fa-eye green" aria-hidden="true"></i>/</a><a href="rn_nil_final_update?id=${rn_nil_final.id}"><i class="fa fa-edit red" aria-hidden="true"></i>/</a><a href="rolenewviewreports?user_id=${rn_userlist.id}"><i class="fa fa-graduation-cap" aria-hidden="true"></i></a></td>
<td class="center">${rn_nil_final.textfield1}</td>
<td class="center">${rn_nil_final.textfield2}</td>
<td class="center">${rn_nil_final.textfield3}</td>
<td class="center">${rn_nil_final.textfield4}</td>
<td class="center">${rn_nil_final.textfield5}</td>
<td class="center">${rn_nil_final.textfield6}</td>
<td class="center">${rn_nil_final.textfield7}</td>
<td class="center">${rn_nil_final.textfield8}</td>
<td class="center">${rn_nil_final.textfield9}</td><%@include file="/WEB-INF/tiles/acemaster/test_module1/rn_nil_final_add_grid2.jsp"%>
</tr>
</c:forEach>
</tbody> 
</table>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
</div>
<script src="resources/assets/js/jquery.2.1.1.min.js"></script>
<script src="resources/assets/js/bootstrap.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="resources/assets/js/dataTables.tableTools.min.js"></script>
<script src="resources/assets/js/dataTables.colVis.min.js"></script>
<script src="resources/assets/js/ace-elements.min.js"></script>
<script src="resources/assets/js/ace.min.js"></script>
<script type="text/javascript">
$(document).ready(function() 
{
$('#table1').DataTable( {
"scrollX": true
} );
} );
jQuery(function($)
{
var oTable1 = 
$('#dynamic-table')
.dataTable( {
bAutoWidth: false,
"aoColumns": [
{ "bSortable": false },
 null, null,null, null, null,
{ "bSortable": false }
],
"aaSorting": [],
} );
})
</script>
</body>
</html>by ganesh bute