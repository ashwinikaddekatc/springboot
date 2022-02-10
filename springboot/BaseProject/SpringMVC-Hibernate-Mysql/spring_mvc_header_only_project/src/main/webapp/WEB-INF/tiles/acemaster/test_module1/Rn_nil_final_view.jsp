
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
href="<c:url value='/resources/assets/css/chosen.min.css' />" /><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css"><link href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css" rel="stylesheet" type="text/css"><script src="//code.jquery.com/jquery-1.11.2.min.js"></script><script src="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js" type="text/javascript" ></script> <script src="//code.jquery.com/jquery-1.11.2.min.js"></script> <script src="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js" type="text/javascript" ></script><link href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css" rel="stylesheet" type="text/css"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css"><script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js"></script>
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
<script src="resources/assets/js/ace-extra.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
</ul>
</div>
<div class="page-content">
<div class="page-header">
<h1>rn_nil_final<div style="float: right; padding-right: 5%;"><a href="rn_form_builder_extension"><i class="fa fa-paper-plane-o fa-1x" aria-hidden="true" title="help"></i></a></div></h1>
</div>
<div class="row">
<div class="col-xs-12">
<div class="widget-body">
<div class="widget-main">
<div id="fuelux-wizard-container">
<div class="step-content pos-rel">
<div class="step-pane active" data-step="1">
<div class="table-header" style="margin-bottom: 30px; margin-top: 30px;"> section1</div>
<form action="rn_nil_final_submit" class="form-horizontal" id="Regi" method="Post">
<input type="hidden" name="test" id="test" value="" /> 
<table>
<tr><td  style="display:none;"> <input type="text" id="id" class="col-xs-10 col-sm-11"  name="id" value=""/></td>
<tr>
<td>
<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
<label class="control-label col-xs-12 col-sm-3 no-padding-right">
 label1
</label>
<div class="col-xs-12 col-sm-9">
<div class="clearfix">
<input value="${rn_nil_final_updt.textfield1}"  type="text" name=textfield1 id=textfield1   class="form-control"/>
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
<input value="${rn_nil_final_updt.textfield2}"  type="text" name=textfield2 id=textfield2   class="form-control"/>
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
<input value="${rn_nil_final_updt.textfield3}"  type="text" name=textfield3 id=textfield3   class="form-control"/>
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
<input value="${rn_nil_final_updt.textfield4}"  type="text" name=textfield4 id=textfield4   class="form-control"/>
</div>
</div>
</div>
</td>
<td>
<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
<label class="control-label col-xs-12 col-sm-3 no-padding-right">
 label5
</label>
<div class="col-xs-12 col-sm-9">
<div class="clearfix">
<input value="${rn_nil_final_updt.textfield5}"  type="text" name=textfield5 id=textfield5   class="form-control"/>
</div>
</div>
</div>
</td>
</table>
<div class="table-header" style="margin-bottom: 30px; margin-top: 30px;"> section2</div> 
<table>
<tr>
<tr>
<td>
<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
<label class="control-label col-xs-12 col-sm-3 no-padding-right">
 label6
</label>
<div class="col-xs-12 col-sm-9">
<div class="clearfix">
<input value="${rn_nil_final_updt.textfield6}"  type="text" name=textfield6 id=textfield6   class="form-control"/>
</div>
</div>
</div>
</td>
<td>
<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
<label class="control-label col-xs-12 col-sm-3 no-padding-right">
 label7
</label>
<div class="col-xs-12 col-sm-9">
<div class="clearfix">
<input value="${rn_nil_final_updt.textfield7}"  type="text" name=textfield7 id=textfield7   class="form-control"/>
</div>
</div>
</div>
</td>
<td>
<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
<label class="control-label col-xs-12 col-sm-3 no-padding-right">
 label8
</label>
<div class="col-xs-12 col-sm-9">
<div class="clearfix">
<input value="${rn_nil_final_updt.textfield8}"  type="text" name=textfield8 id=textfield8   class="form-control"/>
</div>
</div>
</div>
</td>
</tr>
<tr>
<td>
<div class="form-group" style="margin-left: 10%; margin-right: 10%;">
<label class="control-label col-xs-12 col-sm-3 no-padding-right">
 label9
</label>
<div class="col-xs-12 col-sm-9">
<div class="clearfix">
<input value="${rn_nil_final_updt.textfield9}"  type="text" name=textfield9 id=textfield9   class="form-control"/>
</div>
</div>
</div>
</td>
</table>
<%@include file="/WEB-INF/tiles/acemaster/test_module1/rn_nil_final_extension.jsp"%>
<div class="wizard-actions">
<button type="submit" class="btn btn-success center" onclick="submitForms()">
button1
</button>
</div> 
</form>
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
</div><script src="<c:url value='/resources/assets/js/bootstrap-datepicker.min.js'/>" type="text/javascript">
</script><script src="<c:url value='cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js'/>" type="text/javascript">
</script><script></script>
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
</script><script></script>
</body>
</html>