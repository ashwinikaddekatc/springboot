<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.ResourceBundle" %>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
      "http://www.w3.org/TR/html4/loose.dtd">
<!-- <!DOCTYPE html> -->
<html lang="en">
<head>


<SCRIPT TYPE="text/javascript"> 
    
				function popupform(myform, windowname) 
				{ 
				    if (! window.focus)return true; 
				window.open('', windowname, 'height=850px,width=850px,scrollbars=yes'); 
				    myform.target=windowname; return true; } 
 		</SCRIPT>
 		
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
 		
 		

</head>
<body class="no-skin">



	<!-- 	<div class="main-content"> -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>
					<li class="active">Dashboard</li>
				</ul>
				<!-- /.breadcrumb -->

			</div>

			<div class="page-content">


				<div class="page-header">
					<h1>
						Dashboard <small> <i
							class="ace-icon fa fa-angle-double-right"></i> overview
						</small>
					</h1>
				</div>
				<!-- /.page-header -->

				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->


						<div class="row">
							<div class="space-6"></div>

							<div class="col-sm-6 infobox-container">
								<c:forEach var="contact" items="${DashbordSalesCount}"
									varStatus="status">
									<table>
										<tr>
											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa fa-list-alt"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.pending_srsales}</span>
														<div class="infobox-content">Pending SR</div>
													</div>

												</div>
											</td>
											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa fa-indent"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.submitted_srsales}</span>
														<div class="infobox-content">Submitted SR</div>
													</div>
												</div>
											</td>
											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa fa-refresh"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.converted_srsales}</span>
														<div class="infobox-content">Claimed SR</div>
													</div>


												</div>
											</td>
										
											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa fa-newspaper-o"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.total_srsales}</span>
														<div class="infobox-content">Total SR</div>
													</div>


												</div>
											</td>
											
											</tr>
										<tr>
											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa 	fa-check-square-o"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.approved_claimssales}</span>
														<div class="infobox-content">Approved Claims</div>
													</div>

												</div>
											</td>
											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa fa-times"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.rejected_claimssales}</span>
														<div class="infobox-content">Total Rejected Claims</div>
													</div>
												</div>
											</td>
										

											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa fa-clock-o"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.inprocess_claimssales}</span>
														<div class="infobox-content">In-process Claims</div>
													</div>


												</div>
											</td>
											<td>
												<div class="infobox infobox-black">
													<div class="infobox-icon">
														<i class="ace-icon fa fa-th"></i>
													</div>

													<div class="infobox-data">
														<span class="infobox-data-number">${contact.total_claimssales}</span>
														<div class="infobox-content">Total Claims</div>
													</div>


												</div>
											</td>
										</tr>
										
									</table>
								</c:forEach>
								
								<div class="space-6"></div>
								
							</div>
							


<!-- 							<div class="col-sm-5" style="float: right;"> -->
<!-- 								<div class="widget-box transparent"> -->
<!-- 									<div class="widget-header widget-header-flat"> -->
<!-- 										<h4 class="widget-title lighter"> -->
<!-- 											<i class="ace-icon fa 	fa-globe "></i> Notification -->
<!-- 										</h4> -->

<!-- 										<div class="widget-toolbar"> -->
<!-- 											<a href="#" data-action="collapse"> <i -->
<!-- 												class="ace-icon fa fa-chevron-up"></i> -->
<!-- 											</a> -->
<!-- 										</div> -->
<!-- 									</div> -->


<!-- 									<div class="widget-body"> -->
<!-- 										<div class="widget-main no-padding"> -->
<!-- 											<table class="table table-bordered table-striped"> -->



<%-- 												<c:forEach var="contact" items="${DashbordApprover}" --%>
<%-- 													varStatus="status"> --%>
<!-- 													<tbody> -->
<!-- 														<tr> -->
<!-- 															<td class="center"><a -->
<%-- 																href="approvalaction?claim_num=${contact.claim_number }" --%>
<!-- 																onclick="popupform(this, 'join')">Claim Number -->
<%-- 																	${contact.claim_number} requires your approval.</a></td> --%>
<!-- 														</tr> -->
<!-- 													</tbody> -->
<%-- 												</c:forEach> --%>

<!-- 											</table> -->

<!-- 										</div> -->
<!-- 									</div> -->

<!-- 								</div> -->


<!-- 							</div> -->

							<%-- 	<div class="row">
								
									<div class="col-sm-5">
										<div class="widget-box transparent">
											<div class="widget-header widget-header-flat">
												<h4 class="widget-title lighter">
													<i class="ace-icon fa 	fa-globe "></i>
													Todays Claim Approval
												</h4>

												<div class="widget-toolbar">
													<a href="#" data-action="collapse">
														<i class="ace-icon fa fa-chevron-up"></i>
													</a>
												</div>
											</div>
											

											<div class="widget-body">
												<div class="widget-main no-padding">
													<table class="table table-bordered table-striped">

												<c:forEach var="contact" items="${DashbordApprover}" varStatus="status" >
												<tbody><tr>
													<td class="center"><a href="approvalaction?claim_number=${contact.claim_number}&country_code=${conta-ct.country_code}&history=${contact.wf_notification_msg}" onclick="popupform(this, 'join')" > Claim Number ${contact.claim_number} ${contact.wf_notification_msg}</a></td>
												</tr>
												Claim Number ${contact.claim_number}
												</tbody>
												</c:forEach>	

															
														
													</table>
												</div><!-- /.widget-main -->
											</div><!-- /.widget-body -->
										</div><!-- /.widget-box -->
									</div><!-- /.col -->

									
								</div><!-- /.row -->

 --%>

							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->

			</div>
			
	<!-- for bar charts dataa -->
	
	
	       <div class="page-content">
                <div class="row">
					<div class="col-xs-12">
						<hr/>
						<div class="row">
						
						    <div class="col-xs-9" >
								<div>
							        
							             <td>
										   <canvas id="bar-chart" width="800" height="450"></canvas>
										 </td>
										 
										 </hr>
										 <td>
										   <canvas id="line-chart" width="800" height="450"></canvas>
										 </td>
								    
                               </div>
                             </div>
						
						</div>
                     </div>
				</div>
			</div>
	
	
			
			
			
			
			
			
			

			<div class="page-content">



				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<hr/>
						<div class="row">
						
							<div class="col-xs-12" >
								
								 <div class="clearfix">
									<div class="pull-right tableTools-container"></div>
								</div> 
								
								
								<div class="table-header">Distributor Details</div>

								<!-- div.table-responsive -->

								<!-- div.dataTables_borderWrap -->
								<div>
									<table class="table table-striped table-bordered table-hover"
										id="table1" cellspacing="0">
										<thead>
											<tr style="height: 40px">
												<!--<th class="center"style="width:2px">No</th> -->
												<th class="center" style="width: 25%;">Distributor Name</th>
												<th class="center">Open Claim</th>
                                                <th class="center">Open SR</th>
												<th class="center">Closed Claim</th>
												<th class="center">Rejected Claim</th>
												<th class="center">PRF</th>

											</tr>

										</thead>


										<tbody>
											<c:forEach var="contact1" items="${AdminCount}" varStatus="status">

												<tr>
													<!-- 					<td class="center"> -->

													<!-- 					</td> -->
													<td>${contact1.distributor_name}</td>
													<td class="center">${contact1.open_claim}</td>
													<td class="center">${contact1.open_sr}</td>
													<td class="center">${contact1.closed_claim}</td>

													<td class="center">${contact1.rejected_claim}</td>
													<td class="center">${contact1.installed_prf}</td>




												</tr>
											</c:forEach>


										</tbody>
									</table>
								</div>
							</div>
						</div>


					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->


		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

		<SCRIPT>		   
				
		    	$('.selectdrop').mousedown(function(event) {								  		 		
		    		 var count = $('.selectdrop').index(this)+1;	
		    		 
			  		$.ajax({
				        url: '${pageContext.request.contextPath}/loadpricelist',				       
				        success: function(data) {    	
				        	
				        	var select = $('#price_list_id'+count); 	  
				        	select.find('option').remove();	        	
				        	  
				        	$.each(data, function(index, value) {
					        	  $('<option>').val(value.list_header_id).text(value.list_name).appendTo(select);
				        	});   	        	  
				        }
				      });

			});
		      </SCRIPT>
		<!-- <script> -->
		<!-- $(document).ready(function() { -->

		<%-- <%for(int i11=1;i11 <=ii;i11++){%>    --%>


		<!-- 	var str1 = "#price_list_id"; -->
		<!-- 	//var str2 = count; -->
		<!-- 	//var res = str1.concat(str2); -->

		<!-- 	$.ajax({ -->
		<%-- 	        url: '${pageContext.request.contextPath}/loadpricelist', --%>

		<!-- 	        success: function(data) { -->
		<%-- 	            var select = $('#price_list_id<%=i11%>');  --%>
		<!-- 		            select.find('option').remove(); -->
		<!-- 		              $.each(data, function(index, value) { -->
		<!-- 		            	  $('<option>').val(value.list_header_id).text(value.list_name).appendTo(select); -->
		<!-- 		            }); -->

		<!-- 	        } -->
		<!-- 		      }); -->

		<%-- 	<%}%> --%>

		<!-- }); -->

		<!--  </script>  -->




		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="resources/assets/js/jquery.2.1.1.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
			window.jQuery || document.write("<script src='resources/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	    <script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='resources/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		
	<script src="resources/assets/js/bootstrap.min.js"></script>
    <!-- page specific plugin scripts -->
	<script src="resources/assets/js/jquery.dataTables.min.js"></script>
	<script src="resources/assets/js/jquery.dataTables.bootstrap.min.js"></script>
	<script src="resources/assets/js/dataTables.tableTools.min.js"></script>
	<script src="resources/assets/js/dataTables.colVis.min.js"></script>

	<!-- ace scripts -->
	<script src="resources/assets/js/ace-elements.min.js"></script>
	<script src="resources/assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->


	<script type="text/javascript">
		
	
		$(document).ready(function() {
			$('#table1').DataTable( {
			"scrollX": true
			} );
		
			} );
		
		
			jQuery(function($) {
				
			
				//initiate dataTables plugin
				var oTable1 = 
				$('#table1')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				
				//oTable1.fnAdjustColumnSizing();
			
			
				//TableTools settings
				TableTools.classes.container = "btn-group btn-overlap";
				TableTools.classes.print = {
					"body": "DTTT_Print",
					"info": "tableTools-alert gritter-item-wrapper gritter-info gritter-center white",
				}
			
				//initiate TableTools extension
				var tableTools_obj = new $.fn.dataTable.TableTools( oTable1, {
					"sSwfPath": "resources/assets/swf/copy_csv_xls_pdf.swf",
					
					"sRowSelector": "td:not(:last-child)",
					"sRowSelect": "multi",
					"fnRowSelected": function(row) {
						//check checkbox when row is selected
						try { $(row).find('input[type=checkbox]').get(0).checked = true }
						catch(e) {}
					},
					"fnRowDeselected": function(row) {
						//uncheck checkbox
						try { $(row).find('input[type=checkbox]').get(0).checked = false }
						catch(e) {}
					},
			
					"sSelectedClass": "success",
			        "aButtons": [
						{
							"sExtends": "copy",
							"sToolTip": "Copy to clipboard",
							"sButtonClass": "btn btn-white btn-primary btn-bold",
							"sButtonText": "<i class='fa fa-copy bigger-110 pink'></i>",
							"fnComplete": function() {
								this.fnInfo( '<h3 class="no-margin-top smaller">Table copied</h3>\
									<p>Copied '+(oTable1.fnSettings().fnRecordsTotal())+' row(s) to the clipboard.</p>',
									1500
								);
							}
						},
						
						{
							"sExtends": "csv",
							"sToolTip": "Export to CSV",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-file-excel-o bigger-110 green'></i>"
						},
						
						{
							"sExtends": "pdf",
							"sToolTip": "Export to PDF",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-file-pdf-o bigger-110 red'></i>"
						},
						
						{
							"sExtends": "print",
							"sToolTip": "Print view",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-print bigger-110 grey'></i>",
							
							
							"sInfo": "<h3 class='no-margin-top'>Print view</h3>\
									  <p>Please use your browser's print function to\
									  print this table.\
									  <br />Press <b>escape</b> when finished.</p>",
						}
			        ]
			    } );
				//we put a container before our table and append TableTools element to it

			    $(tableTools_obj.fnContainer()).appendTo($('.tableTools-container'));
				
				//also add tooltips to table tools buttons
				//addding tooltips directly to "A" buttons results in buttons disappearing (weired! don't know why!)
				//so we add tooltips to the "DIV" child after it becomes inserted
				//flash objects inside table tools buttons are inserted with some delay (100ms) (for some reason)
				setTimeout(function() {
					$(tableTools_obj.fnContainer()).find('a.DTTT_button').each(function() {
						var div = $(this).find('> div');
						if(div.length > 0) div.tooltip({container: 'body'});
						else $(this).tooltip({container: 'body'});
					});
				}, 200);
				
				
				
				//ColVis extension
				var colvis = new $.fn.dataTable.ColVis( oTable1, {
					"buttonText": "<i class='fa fa-search bigger-110'></i>",
					"aiExclude": [0, 7],
					"bShowAll": true,
					//"bRestore": true,
					"sAlign": "right",
					"fnLabel": function(i, title, th) {
						return $(th).text();//remove icons, etc
					}
					
				}); 
				
				//style it
				$(colvis.button()).addClass('btn-group').find('button').addClass('btn btn-white btn-info btn-bold')
				
				//and append it to our table tools btn-group, also add tooltip
				$(colvis.button())
				.prependTo('.tableTools-container .btn-group')
				.attr('title', 'Show/hide columns').tooltip({container: 'body'});
				
				//and make the list, buttons and checkboxed Ace-like
				$(colvis.dom.collection)
				.addClass('dropdown-menu dropdown-light dropdown-caret dropdown-caret-right')
				.find('li').wrapInner('<a href="javascript:void(0)" />') //'A' tag is required for better styling
				.find('input[type=checkbox]').addClass('ace').next().addClass('lbl padding-8');
			
			
				
				/////////////////////////////////
				//table checkboxes
				$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
				
				//select/deselect all rows according to table header checkbox
				$('#table1> thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) tableTools_obj.fnSelect(row);
						else tableTools_obj.fnDeselect(row);
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#table1').on('click', 'td input[type=checkbox]' , function(){
					var row = $(this).closest('tr').get(0);
					if(!this.checked) tableTools_obj.fnSelect(row);
					else tableTools_obj.fnDeselect($(this).closest('tr').get(0));
				});
				
			
				
				
					$(document).on('click', '#table1 .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});
				
				
				//And for the first simple table, which doesn't have TableTools or dataTables
				//select/deselect all rows according to table header checkbox
				var active_class = 'active';
				$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
						else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#simple-table').on('click', 'td input[type=checkbox]' , function(){
					var $row = $(this).closest('tr');
					if(this.checked) $row.addClass(active_class);
					else $row.removeClass(active_class);
				});
			
				
			
				/********************************/
				//add tooltip for small view action buttons in dropdown menu
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				
				//tooltip placement on right or left
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			});
	</script>
	 
	 
	<%
	ResourceBundle resource = ResourceBundle.getBundle("application");
	String url = resource.getString("spring.datasource.url");
	String username = resource.getString("spring.datasource.username");
	String password = resource.getString("spring.datasource.password");
	
	 String sql="SELECT PENDING_SR, SUBMITTED_SR, CONVERTED_SR, TOTAL_SR, APPROVED_CLAIMS, REJECTED_CLAIMS, INPROCESS_CLAIMS, TOTAL_CLAIMS"
				+ " FROM (SELECT COUNT(SERVICE_REQUEST_ID) PENDING_SR FROM RN_SERVICE_REQUEST_HEADER_T WHERE SERVICE_REQUEST_STATUS = 'Claimed')as PENDING_SR, "
				+ "(SELECT COUNT(SERVICE_REQUEST_ID) SUBMITTED_SR FROM RN_SERVICE_REQUEST_HEADER_T WHERE SERVICE_REQUEST_STATUS = 'Submitted')as SUBMITTED_SR, "
				+ "(SELECT COUNT(SERVICE_REQUEST_ID) CONVERTED_SR FROM RN_SERVICE_REQUEST_HEADER_T WHERE SERVICE_REQUEST_STATUS = 'Claimed')as CONVERTED_SR, "
				+ "(SELECT COUNT(SERVICE_REQUEST_ID) TOTAL_SR FROM RN_SERVICE_REQUEST_HEADER_T)as TOTAL_SR, (SELECT COUNT(CLAIM_HEADER_ID) APPROVED_CLAIMS "
				+ "FROM RN_CLAIM_HEADER_T WHERE CLAIM_STATUS = 'Approved')as APPROVED_CLAIMS, (SELECT COUNT(CLAIM_HEADER_ID) REJECTED_CLAIMS FROM RN_CLAIM_HEADER_T "
				+ "WHERE CLAIM_STATUS = 'Rejected')as REJECTED_CLAIMS, (SELECT COUNT(CLAIM_HEADER_ID) INPROCESS_CLAIMS FROM RN_CLAIM_HEADER_T WHERE CLAIM_STATUS ="
				+ "'In Process')as INPROCESS_CLAIMS, (SELECT COUNT(CLAIM_HEADER_ID) TOTAL_CLAIMS FROM RN_CLAIM_HEADER_T)as TOTAL_CLAIMS";
		
	
	
	  Connection con = DriverManager.getConnection(url, username, password);	  
	  Statement stmt = con.createStatement();
	  ResultSet rs = stmt.executeQuery(sql);
	  
	
	      rs.next();
			
		  int psr=rs.getInt("PENDING_SR");
		  int ss=rs.getInt("SUBMITTED_SR");
		  int cr=rs.getInt("CONVERTED_SR");
		  int ts=rs.getInt("TOTAL_SR");
		  int ac=rs.getInt("APPROVED_CLAIMS");
		  int rc=rs.getInt("REJECTED_CLAIMS");
		  int ic=rs.getInt("INPROCESS_CLAIMS");
		  int tc=rs.getInt("TOTAL_CLAIMS");
		  
		  int total=psr+ss+cr+ts+ac+rc+ic+tc;
		  
		  System.out.println(" psr ::::::::"+psr);
		 
	
	
	
	
	%>
	
	
	
	
	
	
	
	
	<script>
	$(function () 
	{
		
		var datapoint=[<%=psr%>,<%=ss%>,<%=cr%>,<%=ts%>,<%=ac%>,<%=rc%>,<%=ic%>,<%=tc%>];
		new Chart(document.getElementById("bar-chart"), {
		    type: 'bar',
		    data: {
		      labels: ["PENDING_SR", "SUBMITTED_SR", "CONVERTED_SR", "TOTAL_SR", "APPROVED_CLAIMS","REJECTED_CLAIMS","INPROCESS_CLAIMS","TOTAL_CLAIMS"],
		      datasets: [
		        {
		          label: "Claims",
		          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850","#cc0066","#ff9900","#000066"],
		          data: datapoint
		        }
		      ]
		    },
		    options: {
		      legend: { display: false },
		      title: {
		        display: true,
		        text: 'Dashboard Overview'
		      }
		    }
		});
});
	
	
	
	new Chart(document.getElementById("line-chart"), {
		  type: 'line',
		  data: {
		    labels: [1500,1600,1700,1750,1800,1850,1900,1950,1999,2050],
		    datasets: [{ 
		        data: [86,114,106,106,107,111,133,221,783,2478],
		        label: "Africa",
		        borderColor: "#3e95cd",
		        fill: false
		      }, { 
		        data: [282,350,411,502,635,809,947,1402,3700,5267],
		        label: "Asia",
		        borderColor: "#8e5ea2",
		        fill: false
		      }, { 
		        data: [168,170,178,190,203,276,408,547,675,734],
		        label: "Europe",
		        borderColor: "#3cba9f",
		        fill: false
		      }, { 
		        data: [40,20,10,16,24,38,74,167,508,784],
		        label: "Latin America",
		        borderColor: "#e8c3b9",
		        fill: false
		      }, { 
		        data: [6,3,2,2,7,26,82,172,312,433],
		        label: "North America",
		        borderColor: "#c45850",
		        fill: false
		      }
		    ]
		  },
		  options: {
		    title: {
		      display: true,
		      text: 'World population per region (in millions)'
		    }
		  }
		});
	
	
	
	
	
	
	
	</script>
		
		


	<!-- 		<script> -->
	<!-- // 			$( document ).ready(function() { -->

	<!-- // 			    $.ajax({ -->
	<%-- // 			        url: '${pageContext.request.contextPath}/loadMenus', --%>

	<!-- // 			        success: function(data) { -->
	<!-- // 			            var select = $('#menu_id'); -->
	<!-- // 			            alert(select); -->
	<!-- // 			            select.find('option').remove(); -->
	<!-- // 			              $.each(data, function(index, value) { -->
	<!-- // 						  $('<option>').val(value.menu_id).text(value.menu_name).appendTo(select); -->
	<!-- // 			            }); -->

	<!-- // 			        } -->
	<!-- // 			      }); -->

	<!-- // 			}); -->
	<!-- 		</script> -->
</body>
</html>