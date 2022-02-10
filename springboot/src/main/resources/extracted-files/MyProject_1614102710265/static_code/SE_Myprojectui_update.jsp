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
"href=\"<c:url value='/resources/assets/css/chosen.min.css' />\" />" + "\r\n" + 
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
"" + "\r\n" + 
"<!-- MultiSelect DropDown Scripts  -->" + "\r\n" + 
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>" + "\r\n" + 
"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script> " + "\r\n" + 
"<script src=\"multiselect.min.js\"></script>   " + "\r\n" + 
" <script src=\"http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js\"></script>    " + "\r\n" + 
"<script src=\"http://code.jquery.com/jquery-1.11.3.min.js\"></script>    " + "\r\n" + 
"<script src=\"http://code.jquery.com/ui/1.11.1/jquery-ui.min.js\"></script>    " + "\r\n" + 
"<script src=\"http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.core.js\"></script>   " + "\r\n" + 
" <script src=\"http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.lob.js\"></script>  <!-- Ignite UI Required Combined CSS Files -->  " + "\r\n" + 
" <link href=\"http://cdn-na.infragistics.com/igniteui/2019.2/latest/css/themes/infragistics/infragistics.theme.css\" rel=\"stylesheet\" />   " + "\r\n" + 
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
"<li class=\"active\">myprojectui_t</li>" + "\r\n" + 
"</ul>" + "\r\n" + 
"</div>" + "\r\n" + 
"<div class=\"page-content\">" + "\r\n" + 
"<div class=\"page-header\">" + "\r\n" + 
"<h1>myprojectui_t " + "\r\n" + 
" <div style=\"float: right; padding-right: 5%;\"> " + "\r\n" + 
" <a href=\"#myModel\" id=\"${rn_rb_reports_t_updt.id}\">  " + "\r\n" + 
"  <i class=\"fa fa-ticket\" aria-hidden=\"true\"	 " + "\r\n" + 
"  data-toggle=\"modal\" data-target=\"#myModal\">	 " + "\r\n" + 
"  </i> " + "\r\n" + 
"</a>" + "\r\n" + 
"</div>" + "\r\n" + 
"</h1>" + "\r\n" + 
"" + "\r\n" + 
"</div>" + "\r\n" + 
"<div class=\"row\">" + "\r\n" + 
"<div class=\"col-xs-12\">" + "\r\n" + 
"<div class=\"widget-body\">" + "\r\n" + 
"<div class=\"widget-main\">" + "\r\n" + 
"<div id=\"fuelux-wizard-container\">" + "\r\n" + 
"<div class=\"step-content pos-rel\">" + "\r\n" + 
"<div class=\"step-pane active\" data-step=\"1\">" + "\r\n" + 
"<form action=\"myprojectui_t_update_submit\" class=\"form-horizontal\" id=\"Regi\" method=\"Post\">" + "\r\n" + 
"<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\"> section1</div> " + "\r\n" + 
"<table>" + "\r\n" + 
"<c:forEach var=\"myprojectui_t_update\" items=\"${myprojectui_t_update}\" varStatus=\"status\">" + "\r\n" + 
"" + "\r\n" + 
"<input type=\"hidden\" name=\"id\" id=\"id\" value=\"${myprojectui_t_update.id}\" />" + "\r\n" + 
"<tr>" + "\r\n" + 
"" + "\r\n" + 
"<td>" + "\r\n" + 
"	<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">" + "\r\n" + 
"		<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\r\n" + 
"			label1</label>" + "\r\n" + 
"		<div class=\"col-xs-12 col-sm-9\">" + "\r\n" + 
"			<div class=\"clearfix\">" + "\r\n" + 
"				<input value=\"${myprojectui_t_update.textfield1}\"" + "\r\n" + 
"					type=\"text\" name=\"textfield1\" id=\"textfield1\" class=\"form-control\" />" + "\r\n" + 
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
"				<input value=\"${myprojectui_t_update.textfield2}\"" + "\r\n" + 
"					type=\"text\" name=\"textfield2\" id=\"textfield2\" class=\"form-control\" />" + "\r\n" + 
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
"				<input value=\"${myprojectui_t_update.textfield3}\"" + "\r\n" + 
"					type=\"text\" name=\"textfield3\" id=\"textfield3\" class=\"form-control\" />" + "\r\n" + 
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
"				<input value=\"${myprojectui_t_update.textfield4}\"" + "\r\n" + 
"					type=\"text\" name=\"textfield4\" id=\"textfield4\" class=\"form-control\" />" + "\r\n" + 
"			</div>" + "\r\n" + 
"		</div>" + "\r\n" + 
"	</div>" + "\r\n" + 
"</td>" + "\r\n" + 
"</tr><c:forEach var=\"myprojectui_t_update\" items=\"${myprojectui_t_update}\" varStatus=\"status\">" + "\r\n" + 
"" + "\r\n" + 
"<%@include file=\"/WEB-INF/tiles/acemaster/test_module1/myprojectui_t_ext_Update.jsp\"%>" + "\r\n" + 
"</c:forEach>" + "\r\n" + 
"<div class=\"hr hr-dotted\"></div>" + "\r\n" + 
"<div class=\"wizard-actions\">" + "\r\n" + 
"<button type=\"submit\" class=\"btn btn-success center\" onclick=\"submitForms()\">" + "\r\n" + 
"Update" + "\r\n" + 
"</button>" + "\r\n" + 
"</div> " + "\r\n" + 
"</form>" + "\r\n" + 
"<div class=\"modal fade\"  id=\"myModal\" role=\"dialog\">" + "\r\n" + 
"<div class=\"modal-dialog\">" + "\r\n" + 
"	<!-- Modal content-->" + "\r\n" + 
"	<div class=\"modal-content\">" + "\r\n" + 
"		<div class=\"modal-header\">" + "\r\n" + 
"			<button type=\"button\" class=\"close\"" + "\r\n" + 
"				data-dismiss=\"modal\">&times;</button>	" + "\r\n" + 
"	</div>	" + "\r\n" + 
"	<input type=\"hidden\" name=\"test\" id=\"test\" >" + "\r\n" + 
"<c:forEach var=\"myprojectui_t_update\" items=\"${myprojectui_t_update}\" varStatus=\"status\">" + "\r\n" + 
"			<input value=\"${rn_rb_reports_t_updt.id}\"" + "\r\n" + 
"				type=\"hidden\" name=\"id\" id=\"id\"" + "\r\n" + 
"				class=\"col-xs-12 col-sm-4\" />	" + "\r\n" + 
"		<div class=\"modal-body mx-3\">		" + "\r\n" + 
"		<div class=\"md-form mb-5\">	" + "\r\n" + 
"				<label data-error=\"wrong\" data-success=\"right\">Account</label>	" + "\r\n" + 
"				<input type=\"text\" id=\"acc_id\" name=\"acc_id\"	" + "\r\n" + 
"					class=\"form-control validate\"	" + "\r\n" + 
"					value=\"${myprojectui_t_update.account_id}\" readonly>	" + "\r\n" + 
"			</div>	" + "\r\n" + 
"		</div>	" + "\r\n" + 
"		<div class=\"modal-body mx-3\">	" + "\r\n" + 
"			<div class=\"md-form mb-5\">	" + "\r\n" + 
"				<label data-error=\"wrong\" data-success=\"right\">Form_Name</label>	" + "\r\n" + 
"				<input type=\"text\" id=\"form_name\" name=\"form_name\"	" + "\r\n" + 
"					class=\"form-control validate\"	" + "\r\n" + 
"					 readonly>	" + "\r\n" + 
"			</div>	" + "\r\n" + 
"		</div>	" + "\r\n" + 
"		<div class=\"modal-body mx-3\">	" + "\r\n" + 
"			<div class=\"md-form mb-5\">	" + "\r\n" + 
"				<label data-error=\"wrong\" data-success=\"right\">Form_Code</label>	" + "\r\n" + 
"				<input type=\"text\" id=\"form_code\" name=\"form_code\"	" + "\r\n" + 
"					class=\"form-control validate\"	" + "\r\n" + 
"					 readonly>	" + "\r\n" + 
"			</div>	" + "\r\n" + 
"		</div>	" + "\r\n" + 
"		<div class=\"modal-body mx-3\">	" + "\r\n" + 
"			<div class=\"md-form mb-5\">	" + "\r\n" + 
"				<label data-error=\"wrong\" data-success=\"right\">Created_By</label>	" + "\r\n" + 
"				<input type=\"text\" id=\"created_by\"	" + "\r\n" + 
"					name=\"created_by\" class=\"form-control validate\"	" + "\r\n" + 
"					value=\"${myprojectui_t_update.created_by}\"	" + "\r\n" + 
"					readonly>	" + "\r\n" + 
"			</div>	" + "\r\n" + 
"		</div>	" + "\r\n" + 
"		<div class=\"modal-body mx-3\">	" + "\r\n" + 
"			<div class=\"md-form mb-5\">	" + "\r\n" + 
"				<label data-error=\"wrong\" data-success=\"right\">Creation_Date</label>	" + "\r\n" + 
"				<input type=\"text\" id=\"creation_date\"	" + "\r\n" + 
"					name=\"creation_date\" class=\"form-control validate\"	" + "\r\n" + 
"					value=\"${myprojectui_t_update.creation_date}\"	" + "\r\n" + 
"					readonly>	" + "\r\n" + 
"			</div>	" + "\r\n" + 
"		</div>	" + "\r\n" + 
"		<div class=\"modal-body mx-3\">	" + "\r\n" + 
"			<div class=\"md-form mb-5\">	" + "\r\n" + 
"				<label data-error=\"wrong\" data-success=\"right\">Last_Updated_by</label>	" + "\r\n" + 
"				<input type=\"text\" id=\"last_updated_by\"	" + "\r\n" + 
"					name=\"last_updated_by\"	" + "\r\n" + 
"					class=\"form-control validate\"	" + "\r\n" + 
"					value=\"${myprojectui_t_update.last_updated_by}\"	" + "\r\n" + 
"					readonly>	" + "\r\n" + 
"			</div>	" + "\r\n" + 
"		</div>	" + "\r\n" + 
"		<div class=\"modal-body mx-3\">	" + "\r\n" + 
"			<div class=\"md-form mb-5\">	" + "\r\n" + 
"				<label data-error=\"wrong\" data-success=\"right\">Last_Update_Date</label>	" + "\r\n" + 
"				<input type=\"text\" id=\"last_update_date\"	" + "\r\n" + 
"					name=\"last_update_date\"	" + "\r\n" + 
"					class=\"form-control validate\"	" + "\r\n" + 
"					value=\"${myprojectui_t_update.last_update_date}\"	" + "\r\n" + 
"					readonly>	" + "\r\n" + 
"			</div>	" + "\r\n" + 
"		</div>" + "\r\n" + 
"</c:forEach>	" + "\r\n" + 
"	<div class=\"modal-body\"></div>	" + "\r\n" + 
"	<div class=\"modal-footer\">	" + "\r\n" + 
"		<button type=\"button\" class=\"btn btn-default\"	" + "\r\n" + 
"			data-dismiss=\"modal\">Close</button>	" + "\r\n" + 
"	</div>	" + "\r\n" + 
"	</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"<div class=\"modal-body mx-3\">" + "\r\n" + 
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
"</div>" + "\r\n" + 
"" + "\r\n" + 
"<script src=\"<c:url value='/resources/assets/js/bootstrap-datepicker.min.js'/>\" type=\"text/javascript\">" + "\r\n" + 
"</script><script src=\"<c:url value='cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js'/>\" type=\"text/javascript\">" + "\r\n" + 
"</script>" + "\r\n" + 
"<script>" + "\r\n" + 
"</script>" + "\r\n" + 
"<script></script>" + "\r\n" + 
"<!-- ace scripts -->	" + "\r\n" + 
"<script src=\"<c:url value='/resources/assets/js/ace-elements.min.js'/>\"		" + "\r\n" + 
"type=\"text/javascript\"></script>	" + "\r\n" + 
"<script src=\"<c:url value='/resources/assets/js/ace.min.js'/>\"	" + "\r\n" + 
"	type=\"text/javascript\"></script>	" + "\r\n" + 
"<!-- inline scripts related to this page -->" + "\r\n" + 
"</body>" + "\r\n" + 
"</html>" 
