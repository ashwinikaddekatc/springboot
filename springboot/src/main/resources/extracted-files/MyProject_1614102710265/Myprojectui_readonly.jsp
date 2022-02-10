
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible"  content="IE=edge,chrome=1">
<meta charset="utf-8" />
<title>Realnet Oil Engines Ltd</title>
<meta name="description" content="Common form elements and layouts" />
<meta name="viewport"
content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
 href="<c:url value='/resources/assets/css/bootstrap.min.css'/>" />
<link rel="stylesheet"
href="<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css'/>" />
<!-- page specific plugin styles -->
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/jquery-ui.custom.min.css' />" />
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/chosen.min.css' />" />
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/datepicker.min.css'/>" />
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/bootstrap-timepicker.min.css'/>" />
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/daterangepicker.min.css' />" />
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/bootstrap-datetimepicker.min.css'/>" />
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/colorpicker.min.css' />" />
<!-- text fonts -->
<link rel="stylesheet"

href="<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />" />
<!-- ace styles -->
<link rel="stylesheet"
href="<c:url value='/resources/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style' />" />
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="<c:url value='/resources/assets/js/ace-extra.min.js'/>"
type="text/javascript"></script>
<script>
submitForms = function()
{
document.forms["userdetails1"].submit();
document.forms["userdetails2"].submit();}
</script> 

<!-- MultiSelect DropDown Scripts  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> 
<script src="multiselect.min.js"></script>   
 <script src="http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js"></script>    
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>    
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>    
<script src="http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.core.js"></script>   
 <script src="http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.lob.js"></script>  <!-- Ignite UI Required Combined CSS Files -->   
<link href="http://cdn-na.infragistics.com/igniteui/2019.2/latest/css/themes/infragistics/infragistics.theme.css" rel="stylesheet" />   
<link href="http://cdn-na.infragistics.com/igniteui/2019.2/latest/css/structure/infragistics.css" rel="stylesheet" />  
</head>
<body>
<input type="hidden" name="form_code" id="form_code" value="nil_final_view" />
<div class="main-container" id="main-container">
<div class="main-content">
<div class="main-content-inner">
<div class="breadcrumbs" id="breadcrumbs">
<ul class="breadcrumb">
<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
</li>
<li><a href="#">ManageUsers</a></li>
<li class="active">myprojectui_t</li>
</ul>
</div>
<div class="page-content">
<div class="page-header">
<h1>myprojectui_t</h1>
</div>
<div class="row">
<div class="col-xs-12">
<div class="widget-body">
<div class="widget-main">
<div id="fuelux-wizard-container">
<div class="step-content pos-rel">
<div class="step-pane active" data-step="1">
<form action="myprojectui_t_update_submit" class="form-horizontal" id="Regi" method="Post">
<div class="table-header" style="margin-bottom: 30px; margin-top: 30px;"> section1</div> 
<table>
<c:forEach var="myprojectui_t_update" items="${myprojectui_t_update}" varStatus="status">

<input type="hidden" name="id" id="id" value="${myprojectui_t_update.id}" />
<tr>

<td>
	<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">
			label1
		</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<input value="${myprojectui_t_update.textfield1}"
					type="text" name="textfield1" id="textfield1" class="form-control" readonly />
			</div>
		</div>
	</div>
</td>

<td>
	<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">
			label2
		</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<input value="${myprojectui_t_update.textfield2}"
					type="text" name="textfield2" id="textfield2" class="form-control" readonly />
			</div>
		</div>
	</div>
</td>

<td>
	<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">
			label3
		</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<input value="${myprojectui_t_update.textfield3}"
					type="text" name="textfield3" id="textfield3" class="form-control" readonly />
			</div>
		</div>
	</div>
</td>
</tr>
<tr>

<td>
	<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">
			label4
		</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<input value="${myprojectui_t_update.textfield4}"
					type="text" name="textfield4" id="textfield4" class="form-control" readonly />
			</div>
		</div>
	</div>
</td>
</tr></tr>
</c:forEach>
</table>
<div class="hr hr-dotted"></div>
<div class="wizard-actions">
<button type="submit" class="btn btn-success center" onclick="submitForms()">
Update
</button>
</div> 

</form>

<%@include file="/WEB-INF/tiles/acemaster/test_module1/myprojectui_t_ext_Readonly.jsp"%>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</body>
<script>
</script>
<script>
document.getElementById('textfield1').onclick = function() {  
  document.getElementById('textfield1').removeAttribute('readonly');
};
</script>
<script>
document.getElementById('textfield2').onclick = function() {  
  document.getElementById('textfield2').removeAttribute('readonly');
};
</script>
<script>
document.getElementById('textfield3').onclick = function() {  
  document.getElementById('textfield3').removeAttribute('readonly');
};
</script>
<script>
document.getElementById('textfield4').onclick = function() {  
  document.getElementById('textfield4').removeAttribute('readonly');
};
</script>
<script>
var readonly = true;
$('a').on('click', (e) => {  
readonly = !readonly  
$('input').attr('readonly', readonly);  // Extra  
if (readonly) {    
$('input').attr('placeholder', "I'm readonly");  
} else {   
 $('input').attr('placeholder', "I'm not readonly");  
}
});
</script>
<!-- ace scripts -->	
<script src="<c:url value='/resources/assets/js/ace-elements.min.js'/>"		
type="text/javascript"></script>	
<script src="<c:url value='/resources/assets/js/ace.min.js'/>"	
	type="text/javascript"></script>	
<!-- inline scripts related to this page -->
<script language="javascript">
function AddRow()  
{$('#dynamic-table1 tr').last().after('<tr></tr>');
$('#rowcount1').val($('#dynamic-table1 tr').length-1);
var count = $('#rowcount1').val();
$('.Deleterow').click(function() { 
var index = $('.Deleterow').index(this)+1;
$('#delrow1').val(index);
});
}
</script>
<script>
function del(){
var index=$('#delrow1').val();
if(index!= "")
{
document.getElementById("dynamic-table1").deleteRow(index);
$('#delrow1').val("");
}
}
</script><script></script></html>