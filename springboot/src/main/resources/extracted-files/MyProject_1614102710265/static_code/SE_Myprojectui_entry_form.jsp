"" + "\r\n" + 
"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>" + "\r\n" + 
"<%@ page import=\"java.util.ArrayList\"%>" + "\r\n" + 
"<%@ page import=\"java.util.Date\"%>" + "\r\n" + 
"<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>" + "\r\n" + 
"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\"%>" + "\r\n" + 
"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%> " + "\r\n" + 
"" + "\r\n" + 
"<html lang=\"en\">" + "\r\n" + 
"<head>" + "\r\n" + 
"<meta http-equiv=\"X-UA-Compatible\"  content=\"IE=edge,chrome=1\">" + "\r\n" + 
"<meta charset=\"utf-8\" />" + "\r\n" + 
"<title>Realnet Oil Engines Ltd</title>" + "\r\n" + 
"<meta name=\"description\" content=\"Common form elements and layouts\" />" + "\r\n" + 
"<meta name=\"viewport\"" + "\r\n" + 
"content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />" + "\r\n" + 
"<!-- bootstrap & fontawesome -->" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
" href=\"<c:url value='/resources/assets/css/bootstrap.min.css'/>\" />" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css'/>\" />" + "\r\n" + 
"<!-- page specific plugin styles -->" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/jquery-ui.custom.min.css' />\" />" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/chosen.min.css' />\" /><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css\"><link href=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css\" rel=\"stylesheet\" type=\"text/css\"><script src=\"//code.jquery.com/jquery-1.11.2.min.js\"></script><script src=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js\" type=\"text/javascript\" ></script> <script src=\"//code.jquery.com/jquery-1.11.2.min.js\"></script> <script src=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js\" type=\"text/javascript\" ></script><link href=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css\" rel=\"stylesheet\" type=\"text/css\"><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css\"><script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js\"></script>" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/datepicker.min.css'/>\" />" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/bootstrap-timepicker.min.css'/>\" />" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/daterangepicker.min.css' />\" />" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/bootstrap-datetimepicker.min.css'/>\" />" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/colorpicker.min.css' />\" />" + "\r\n" + 
"<!-- text fonts -->" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />\" />" + "\r\n" + 
"<!-- ace styles -->" + "\r\n" + 
"<link rel=\"stylesheet\"" + "\r\n" + 
"href=\"<c:url value='/resources/assets/css/ace.min.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style' />\" />" + "\r\n" + 
"<!-- inline styles related to this page -->" + "\r\n" + 
"<!-- ace settings handler -->" + "\r\n" + 
"<script src=\"<c:url value='/resources/assets/js/ace-extra.min.js'/>\"" + "\r\n" + 
"type=\"text/javascript\"></script>" + "\r\n" + 
"<script>" + "\r\n" + 
"submitForms = function()" + "\r\n" + 
"{" + "\r\n" + 
"document.forms[\"userdetails1\"].submit();" + "\r\n" + 
"document.forms[\"userdetails2\"].submit();}" + "\r\n" + 
"</script> " + "\r\n" + 
"<script src=\"resources/assets/js/ace-extra.min.js\"></script>" + "\r\n" + 
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>" + "\r\n" + 
"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>" + "\r\n" + 
"<!-- MultiSelect DropDown Scripts  -->" + "\r\n" + 
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>" + "\r\n" + 
"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script> " + "\r\n" + 
"<script src=\"multiselect.min.js\"></script>   " + "\r\n" + 
" <script src=\"http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js\"></script>    " + "\r\n" + 
"<script src=\"http://code.jquery.com/jquery-1.11.3.min.js\"></script>    " + "\r\n" + 
"<script src=\"http://code.jquery.com/ui/1.11.1/jquery-ui.min.js\"></script>    " + "\r\n" + 
"<script src=\"http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.core.js\"></script>   " + "\r\n" + 
" <script src=\"http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.lob.js\"></script>  <!-- Ignite UI Required Combined CSS Files --> " + "\r\n" + 
"  <link href=\"http://cdn-na.infragistics.com/igniteui/2019.2/latest/css/themes/infragistics/infragistics.theme.css\" rel=\"stylesheet\" />   " + "\r\n" + 
"<link href=\"http://cdn-na.infragistics.com/igniteui/2019.2/latest/css/structure/infragistics.css\" rel=\"stylesheet\" />  " + "\r\n" + 
"</head>" + "\r\n" + 
"<body>" + "\r\n" + 
"<input type=\"hidden\" name=\"form_code\" id=\"form_code\" value=\"nil_final_view\" />" + "\r\n" + 
"<div class=\"main-container\" id=\"main-container\">" + "\r\n" + 
"<div class=\"main-content\">" + "\r\n" + 
"<div class=\"main-content-inner\">" + "\r\n" + 
"<div class=\"breadcrumbs\" id=\"breadcrumbs\">" + "\r\n" + 
"<ul class=\"breadcrumb\">" + "\r\n" + 
"<li><i class=\"ace-icon fa fa-home home-icon\"></i> <a href=\"#\">Home</a>" + "\r\n" + 
"</li>" + "\r\n" + 
"<li><a href=\"#\">ManageUsers</a></li>" + "\r\n" + 
"</ul>" + "\r\n" + 
"</div>" + "\r\n" + 
"<div class=\"page-content\">" + "\r\n" + 
"<div class=\"page-header\">" + "\r\n" + 
"<h1>myprojectui_t<div style=\"float: right; padding-right: 5%;\"><a href=\"rn_form_builder_extension\"><i class=\"fa fa-paper-plane-o fa-1x\" aria-hidden=\"true\" title=\"help\"></i></a></div></h1>" + "\r\n" + 
"</div>" + "\r\n" + 
"<div class=\"row\">" + "\r\n" + 
"<div class=\"col-xs-12\">" + "\r\n" + 
"<div class=\"widget-body\">" + "\r\n" + 
"<div class=\"widget-main\">" + "\r\n" + 
"<div id=\"fuelux-wizard-container\">" + "\r\n" + 
"<div class=\"step-content pos-rel\">" + "\r\n" + 
"<div class=\"step-pane active\" data-step=\"1\">" + "\r\n" + 
"<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\"> section1</div>" + "\r\n" + 
"<form action=\"myprojectui_t_submit\" class=\"form-horizontal\" id=\"Regi\" method=\"Post\">" + "\r\n" + 
"<input type=\"hidden\" name=\"test\" id=\"test\" value=\"\" /> " + "\r\n" + 
"<table>" + "\r\n" + 
"<tr><td  style=\"display:none;\"> <input type=\"text\" id=\"id\" class=\"col-xs-10 col-sm-11\"  name=\"id\" value=\"\"/></td>" + "\r\n" + 
"" + "\r\n" + 
"<tr>" + "\r\n" + 
"" + "\r\n" + 
"<td>" + "\r\n" + 
"	<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"		<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
"			label1</label>" + "\r\n" + 
"		<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"			<div class=\"clearfix\">" + "\r\n" + 
"				<input type=\"text\" name=\"textfield1\" id=\"textfield1\" class=\"form-control\" />" + "\r\n" + 
"			</div>" + "\r\n" + 
"		</div>" + "\r\n" + 
"	</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"" + "\r\n" + 
"<td>" + "\r\n" + 
"	<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"		<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
"			label2</label>" + "\r\n" + 
"		<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"			<div class=\"clearfix\">" + "\r\n" + 
"				<input type=\"text\" name=\"textfield2\" id=\"textfield2\" class=\"form-control\" />" + "\r\n" + 
"			</div>" + "\r\n" + 
"		</div>" + "\r\n" + 
"	</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"" + "\r\n" + 
"<td>" + "\r\n" + 
"	<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"		<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
"			label3</label>" + "\r\n" + 
"		<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"			<div class=\"clearfix\">" + "\r\n" + 
"				<input type=\"text\" name=\"textfield3\" id=\"textfield3\" class=\"form-control\" />" + "\r\n" + 
"			</div>" + "\r\n" + 
"		</div>" + "\r\n" + 
"	</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"</tr>" + "\r\n" + 
"<tr>" + "\r\n" + 
"" + "\r\n" + 
"<td>" + "\r\n" + 
"	<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"		<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
"			label4</label>" + "\r\n" + 
"		<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"			<div class=\"clearfix\">" + "\r\n" + 
"				<input type=\"text\" name=\"textfield4\" id=\"textfield4\" class=\"form-control\" />" + "\r\n" + 
"			</div>" + "\r\n" + 
"		</div>" + "\r\n" + 
"	</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"</tr></table>" + "\r\n" + 
"<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\"> section2</div> " + "\r\n" + 
"<table>" + "\r\n" + 
"<tr>" + "\r\n" + 
"<tr>" + "\r\n" + 
"<td>" + "\r\n" + 
"<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
" label6" + "\r\n" + 
"</label>" + "\r\n" + 
"<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"<div class=\"clearfix\">" + "\r\n" + 
"<input value=\"${myprojectui_t_updt.textfield6}\"  type=\"text\" name=textfield6 id=textfield6   class=\"form-control\"/>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"<td>" + "\r\n" + 
"<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
" label7" + "\r\n" + 
"</label>" + "\r\n" + 
"<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"<div class=\"clearfix\">" + "\r\n" + 
"<input value=\"${myprojectui_t_updt.textfield7}\"  type=\"text\" name=textfield7 id=textfield7   class=\"form-control\"/>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"<td>" + "\r\n" + 
"<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
" label8" + "\r\n" + 
"</label>" + "\r\n" + 
"<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"<div class=\"clearfix\">" + "\r\n" + 
"<input value=\"${myprojectui_t_updt.textfield8}\"  type=\"text\" name=textfield8 id=textfield8   class=\"form-control\"/>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"</tr>" + "\r\n" + 
"<tr>" + "\r\n" + 
"<td>" + "\r\n" + 
"<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
" label9" + "\r\n" + 
"</label>" + "\r\n" + 
"<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"<div class=\"clearfix\">" + "\r\n" + 
"<input value=\"${myprojectui_t_updt.textfield9}\"  type=\"text\" name=textfield9 id=textfield9   class=\"form-control\"/>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"</table>" + "\r\n" + 
"<%@include file=\"/WEB-INF/tiles/acemaster/test_module1/myprojectui_t_extension.jsp\"%>" + "\r\n" + 
"<div class=\"wizard-actions\">" + "\r\n" + 
"<button type=\"submit\" class=\"btn btn-success center\" onclick=\"submitForms()\">" + "\r\n" + 
"button1" + "\r\n" + 
"</button>" + "\r\n" + 
"</div> " + "\r\n" + 
"</form>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div><script src=\"<c:url value='/resources/assets/js/bootstrap-datepicker.min.js'/>\" type=\"text/javascript\">" + "\r\n" + 
"</script><script src=\"<c:url value='cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js'/>\" type=\"text/javascript\">" + "\r\n" + 
"</script><script></script>" + "\r\n" + 
"<!-- ace scripts -->	" + "\r\n" + 
"<script src=\"<c:url value='/resources/assets/js/ace-elements.min.js'/>\"		" + "\r\n" + 
"type=\"text/javascript\"></script>	" + "\r\n" + 
"<script src=\"<c:url value='/resources/assets/js/ace.min.js'/>\"	" + "\r\n" + 
"	type=\"text/javascript\"></script>	" + "\r\n" + 
"<!-- inline scripts related to this page -->" + "\r\n" + 
"<script language=\"javascript\">" + "\r\n" + 
"function AddRow()  " + "\r\n" + 
"{$('#dynamic-table1 tr').last().after('<tr></tr>');" + "\r\n" + 
"$('#rowcount1').val($('#dynamic-table1 tr').length-1);" + "\r\n" + 
"var count = $('#rowcount1').val();" + "\r\n" + 
"$('.Deleterow').click(function() { " + "\r\n" + 
"var index = $('.Deleterow').index(this)+1;" + "\r\n" + 
"$('#delrow1').val(index);" + "\r\n" + 
"});" + "\r\n" + 
"}" + "\r\n" + 
"</script>" + "\r\n" + 
"<script>" + "\r\n" + 
"function del(){" + "\r\n" + 
"var index=$('#delrow1').val();" + "\r\n" + 
"if(index!= \"\")" + "\r\n" + 
"{" + "\r\n" + 
"document.getElementById(\"dynamic-table1\").deleteRow(index);" + "\r\n" + 
"$('#delrow1').val(\"\");" + "\r\n" + 
"}" + "\r\n" + 
"}" + "\r\n" + 
"</script><script></script>" + "\r\n" + 
"</body>" + "\r\n" + 
"</html>" 
