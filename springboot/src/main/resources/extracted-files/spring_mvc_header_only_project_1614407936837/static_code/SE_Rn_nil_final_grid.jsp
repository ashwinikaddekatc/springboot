" " + "\r\n" + 
"<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>" + "\r\n" + 
"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + "\r\n" + 
"<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>" + "\r\n" + 
"<%@ taglib prefix=\"form\" uri=\"http://www.springframework.org/tags/form\"%>" + "\r\n" + 
"<html>" + "\r\n" + 
"<head>" + "\r\n" + 
"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />" + "\r\n" + 
"<meta charset=\"utf-8\"/>" + "\r\n" + 
"<title>Realnet Oil Engines Ltd</title>" + "\r\n" + 
"<meta name=\"description\" content=\"Static &amp; Dynamic Tables\" />" + "\r\n" + 
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\"/>" + "\r\n" + 
"<!-- bootstrap & fontawesome -->" + "\r\n" + 
"<link rel=\"stylesheet\" href=\"resources/assets/css/bootstrap.min.css\" />" + "\r\n" + 
"<link rel=\"stylesheet\" href=\"resources/assets/font-awesome/4.2.0/css/font-awesome.min.css\" />" + "\r\n" + 
"<!-- page specific plugin styles -->" + "\r\n" + 
"<!-- text fonts -->" + "\r\n" + 
"<link rel=\"stylesheet\" href=\"resources/assets/fonts/fonts.googleapis.com.css\"/>" + "\r\n" + 
"<!-- ace styles -->" + "\r\n" + 
"<link rel=\"stylesheet\" href=\"resources/assets/css/ace.min.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style\" />" + "\r\n" + 
"</head>" + "\r\n" + 
"<body class=\"no-skin\">" + "\r\n" + 
"<div class=\"main-container\" id=\"main-container\">" + "\r\n" + 
"<div class=\"main-content\">" + "\r\n" + 
"<div class=\"main-content-inner\">" + "\r\n" + 
"<div class=\"breadcrumbs\" id=\"breadcrumbs\">" + "\r\n" + 
"<script type=\"text/javascript\">" + "\r\n" + 
"try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}" + "\r\n" + 
"</script>" + "\r\n" + 
"<ul class=\"breadcrumb\">" + "\r\n" + 
"<li>" + "\r\n" + 
"<i class=\"ace-icon fa fa-home home-icon\"></i>" + "\r\n" + 
"<a href=\"#\">Home</a>" + "\r\n" + 
"</li>" + "\r\n" + 
"<li class=\"active\"> User Details</li>" + "\r\n" + 
"</ul> " + "\r\n" + 
" </div>" + "\r\n" + 
"<div class=\"page-content\">" + "\r\n" + 
" <div class=\"page-header\">" + "\r\n" + 
"<h1>" + "\r\n" + 
"rn_nil_final" + "\r\n" + 
"<div style=\"float: right; padding-right: 3%;\">" + "\r\n" + 
"<a href=\"rn_nil_final_entryform\"> <i class=\"fa fa-plus fa-1g\" aria-hidden=\"true\" title=\"Add New Report\"></i></a><a href=\"rn_help_menu?form_code=nil_final_view\"><i" + "\r\n" + 
"									class=\"fa fa-question-circle fa-1g\" title=\"Help Menu\"></i></a>" + "\r\n" + 
"</div>" + "\r\n" + 
"</h1>" + "\r\n" + 
"</div><!-- /.page-header -->" + "\r\n" + 
"<div class=\"row\">" + "\r\n" + 
"<div class=\"col-xs-12\">" + "\r\n" + 
"<div class=\"row\">" + "\r\n" + 
"<div class=\"col-xs-12\">" + "\r\n" + 
"<div class=\"clearfix\">" + "\r\n" + 
"<div class=\"pull-right tableTools-container\"></div>" + "\r\n" + 
"</div>" + "\r\n" + 
"<div class=\"table-header\">" + "\r\n" + 
"rn_nil_final" + "\r\n" + 
"</div>" + "\r\n" + 
"<div>" + "\r\n" + 
"<table   class=\"table table-striped table-bordered table-hover\" id=\"table1\"  cellspacing=\"0\" width=\"1500px\" style=\"width:100%; margin: 0 auto;\">" + "\r\n" + 
"<thead>" + "\r\n" + 
"<tr>" + "\r\n" + 
"<th class=\"center\">select</th>" + "\r\n" + 
"<th class=\"center\">label1</th>" + "\r\n" + 
"<th class=\"center\">label2</th>" + "\r\n" + 
"<th class=\"center\">label3</th>" + "\r\n" + 
"<th class=\"center\">label4</th>" + "\r\n" + 
"<th class=\"center\">label5</th>" + "\r\n" + 
"<th class=\"center\">label6</th>" + "\r\n" + 
"<th class=\"center\">label7</th>" + "\r\n" + 
"<th class=\"center\">label8</th>" + "\r\n" + 
"<th class=\"center\">label9</th>" + "\r\n" + 
"<%@include file=\"/WEB-INF/tiles/acemaster/test_module1/rn_nil_final_add_grid.jsp\"%>" + "\r\n" + 
"</tr>" + "\r\n" + 
"</thead>" + "\r\n" + 
"<tbody>" + "\r\n" + 
"<c:forEach var=\"rn_nil_final\" items=\"${rn_nil_final}\" varStatus=\"status\">" + "\r\n" + 
"<tr>	" + "\r\n" + 
"<td class=\"center\">" + "\r\n" + 
"<a href=\"rn_nil_final_readonly?id=${rn_nil_final.id}\">" + "\r\n" + 
"<i class=\"fa fa-eye green\" aria-hidden=\"true\"></i>/" + "\r\n" + 
"</a><a href=\"rn_nil_final_update?id=${rn_nil_final.id}\">" + "\r\n" + 
"<i class=\"fa fa-edit red\" aria-hidden=\"true\"></i>/</a>" + "\r\n" + 
"<a href=\"rolenewviewreports?user_id=${rn_userlist.id}\">" + "\r\n" + 
"<i class=\"fa fa-graduation-cap\" aria-hidden=\"true\"></i>" + "\r\n" + 
"</a></td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield1}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield2}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield3}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield4}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield5}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield6}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield7}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield8}</td>" + "\r\n" + 
"<td class=\"center\">${rn_nil_final.textfield9}</td><%@include file=\"/WEB-INF/tiles/acemaster/test_module1/rn_nil_final_add_grid2.jsp\"%>" + "\r\n" + 
"</tr>" + "\r\n" + 
"</c:forEach>" + "\r\n" + 
"</tbody> " + "\r\n" + 
"</table>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"</div>" + "\r\n" + 
"<a href=\"#\" id=\"btn-scroll-up\" class=\"btn-scroll-up btn btn-sm btn-inverse\">" + "\r\n" + 
"<i class=\"ace-icon fa fa-angle-double-up icon-only bigger-110\"></i>" + "\r\n" + 
"</a>" + "\r\n" + 
"</div>" + "\r\n" + 
"<script src=\"resources/assets/js/jquery.2.1.1.min.js\"></script>" + "\r\n" + 
"<script src=\"resources/assets/js/bootstrap.min.js\"></script>" + "\r\n" + 
"<script src=\"resources/assets/js/jquery.dataTables.min.js\"></script>" + "\r\n" + 
"<script src=\"resources/assets/js/jquery.dataTables.bootstrap.min.js\"></script>" + "\r\n" + 
"<script src=\"resources/assets/js/dataTables.tableTools.min.js\"></script>" + "\r\n" + 
"<script src=\"resources/assets/js/dataTables.colVis.min.js\"></script>" + "\r\n" + 
"<script src=\"resources/assets/js/ace-elements.min.js\"></script>" + "\r\n" + 
"<script src=\"resources/assets/js/ace.min.js\"></script>" + "\r\n" + 
"<script type=\"text/javascript\">" + "\r\n" + 
"$(document).ready(function() " + "\r\n" + 
"{" + "\r\n" + 
"$('#table1').DataTable( {" + "\r\n" + 
"\"scrollX\": true" + "\r\n" + 
"} );" + "\r\n" + 
"} );" + "\r\n" + 
"jQuery(function($)" + "\r\n" + 
"{" + "\r\n" + 
"var oTable1 = " + "\r\n" + 
"$('#dynamic-table')" + "\r\n" + 
".dataTable( {" + "\r\n" + 
"bAutoWidth: false," + "\r\n" + 
"\"aoColumns\": [" + "\r\n" + 
"{ \"bSortable\": false }," + "\r\n" + 
" null, null,null, null, null," + "\r\n" + 
"{ \"bSortable\": false }" + "\r\n" + 
"]," + "\r\n" + 
"\"aaSorting\": []," + "\r\n" + 
"} );" + "\r\n" + 
"})" + "\r\n" + 
"</script>" + "\r\n" + 
"</body>" + "\r\n" + 
"</html>by ganesh bute" 
