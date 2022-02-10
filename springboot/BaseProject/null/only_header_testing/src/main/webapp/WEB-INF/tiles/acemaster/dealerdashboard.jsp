<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
      "http://www.w3.org/TR/html4/loose.dtd">  
<!-- <!DOCTYPE html> -->
<html lang="en">
	<head>
	    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
	<script type="text/javascript">
		
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawStuff);

      function drawStuff() {
        var data = new google.visualization.arrayToDataTable([
         <c:forEach var="contact" items="${DashbordDealerCount}" varStatus="status" >
          ['Opening Move', 'Percentage'],
          ["Pending SR", '${contact.pending_srdealer}'],
          ["Submitted SR", '${contact.submitted_srdealer}'],
          ["Converted SR", '${contact.converted_srdealer}'],
          ["Total SR", '${contact.total_srdealer}'],
          ["Approved Claims", '${contact.approved_claimsdealer}'],
          ["Rejected Claims", '${contact.rejected_claimsdealer}'],
          ["Inprocess Claims", '${contact.inprocess_claimsdealer}'],
          ["Total Claims", '${contact.total_claimsdealer}']
          </c:forEach>
        ]);

        var options = {
          title: 'Chess opening moves',
          width: 900,
          legend: { position: 'none' },
          chart: { title: 'Chess opening moves',
                   subtitle: 'popularity by percentage' },
          bars: 'horizontal', // Required for Material Bar Charts.
          axes: {
            x: {
              0: { side: 'top', label: 'Percentage'} // Top x-axis.
            }
          },
          bar: { groupWidth: "90%" }
        };

        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
        chart.draw(data, options);
      };
    </script>
		
	</head>
    <body class="no-skin">

<SCRIPT TYPE="text/javascript"> 
    
				function popupform(myform, windowname) 
				{ 
				    if (! window.focus)return true; 
				window.open('', windowname, 'height=850px,width=850px,scrollbars=yes'); 
				    myform.target=windowname; return true; } 
				
				
				 
 		</SCRIPT>
<script>
var daydiff=${daydif1};

	 if(daydiff>=21)
		 {
		 var diff=31-daydiff;
		// alert("Your Password is Expired After "+diff+" days.Please Reset Your Password");
		 
		 
		 var txt;
		    var r = confirm("Your Password is Expired After "+diff+" days.Please Reset Your Password");
		    if (r == true) {
		    	 window.location.href = "resetpassword";
		    }
		 }
</script>
<!-- 	<div class="main-content"> -->
				<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li class="active">Dashboard</li>
						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- /.nav-search -->
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1 style="float: left;">
								Dashboard
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									overview 
									</small>
							</h1><br />
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
							

								<div class="row">
									<div class="space-6"></div>

									<div class="col-sm-6 infobox-container">
								<c:forEach var="contact" items="${DashbordDealerCount}" varStatus="status" >
									
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-list-alt"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.pending_srdealer}</span>
												<div class="infobox-content">Pending SR</div>
											</div>

										</div>

										
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-indent"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.submitted_srdealer}</span>
												<div class="infobox-content">Submitted SR</div>
											</div>
										</div>

										


							
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-refresh"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.converted_srdealer}</span>
												<div class="infobox-content">Claimed SR</div>
											</div>

											
										</div>
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-newspaper-o"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.total_srdealer}</span>
												<div class="infobox-content">Total SR</div>
											</div>

											
										</div>
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa 	fa-check-square-o"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.approved_claimsdealer}</span>
												<div class="infobox-content">Approved Claims</div>
											</div>

										</div>

										
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-times"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.rejected_claimsdealer}</span>
												<div class="infobox-content">Total Rejected Claims</div>
											</div>
										</div>

										


							
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-clock-o"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.inprocess_claimsdealer}</span>
												<div class="infobox-content">In-process Claims</div>
											</div>

											
										</div>
										<div class="infobox infobox-black">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-th"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">${contact.total_claimsdealer}</span>
												<div class="infobox-content">Total Claims</div>
											</div>

											
										</div>
										
												</c:forEach>	

										<div class="space-6"></div>

									

									</div>

							
					
								
								<div class="row">
								
									<div class="col-sm-5">
										<div class="widget-box transparent">
											<div class="widget-header widget-header-flat">
												<h4 class="widget-title lighter">
													<i class="ace-icon fa 	fa-globe "></i>
													Notification
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
														

													
<%-- 												<c:forEach var="contact" items="${DashbordApprover}" varStatus="status" > --%>
<!-- 												<tbody><tr> -->
<%-- 													<td class="center"><a href="approvalaction?claim_num=${contact.claim_number }" onclick="popupform(this, 'join')" >Claim Number ${contact.claim_number} requires your approval.</a></td> --%>
<!-- 												</tr> -->
<!-- 												</tbody> -->
<%-- 												</c:forEach>	 --%>
												
<%-- 												<c:forEach var="contact" items="${DashbordApprover}" varStatus="status" > --%>
<!-- 												<tbody><tr> -->
<%-- 													<td class="center"><a href="approvalaction?claim_number=${contact.claim_number}&country_code=${contact.country_code}&history=${contact.wf_notification_msg}" onclick="popupform(this, 'join')" > ${contact.wf_notification_msg}</a></td> --%>
<!-- 												</tr> -->
<%-- <%-- 												Claim Number ${contact.claim_number} --%>
<!-- 												</tbody> -->
<%-- 												</c:forEach>	 --%>
												
												<c:forEach var="contact" items="${DashbordApprover}" varStatus="status" >
												<tbody><tr>
													<td class="center"><a href="approvalaction?claim_number=${contact.claim_number}&country_code=${contact.country_code}&history=${contact.wf_notification_msg}" onclick="popupform(this, 'join')" >Claim Number ${contact.claim_number} ${contact.wf_notification_msg}</a></td>
												</tr>
												</tbody>
												</c:forEach>	

															
														
													</table>
												</div>
											</div>
										</div>
									</div>

									
								</div>

											    <div id="top_x_div" style="width: 900px; height: 500px;"></div>

								

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
<!-- 							<span class="blue bolder">Kirloskar</span> -->
<!-- 							 Oil Engines &copy; 2016-2017 -->
<!-- 						</span> -->

<!-- 						&nbsp; &nbsp; -->
						
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
<!-- 			</div> -->
			<!-- /.main-content -->
			
			<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="<c:url value='/resources/assets/js/jquery.2.1.1.min.js' />"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="<c:url value='/resources/assets/js/jquery.1.11.1.min.js' />"></script>
<![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<c:url value='/resources/assets/js/jquery.min.js' />'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<c:url value='/resources/assets/js/jquery1x.min.js' />'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<c:url value='/resources/assets/js/jquery.mobile.custom.min.js' />'>"+"<"+"/script>");
		</script>
		<script src="<c:url value='/resources/assets/js/bootstrap.min.js' />"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="<c:url value='/resources/assets/js/excanvas.min.js' />"></script>
		<![endif]-->
		<script src="<c:url value='/resources/assets/js/jquery-ui.custom.min.js' />"></script>
		<script src="<c:url value='/resources/assets/js/jquery.ui.touch-punch.min.js' />"></script>
		<script src="<c:url value='/resources/assets/js/jquery.easypiechart.min.js' />"></script>
		<script src="<c:url value='/resources/assets/js/jquery.sparkline.min.js' />"></script>
		<script src="<c:url value='/resources/assets/js/jquery.flot.min.js' />"></script>
		<script src="<c:url value='/resources/assets/js/jquery.flot.pie.min.js' />"></script>
		<script src="<c:url value='/resources/assets/js/jquery.flot.resize.min.js' />"></script>

		<!-- ace scripts -->
		<script src="<c:url value='/resources/assets/js/ace-elements.min.js' />"></script>
		<script src="<c:url value='/resources/assets/js/ace.min.js' />"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				$('.easy-pie-chart.percentage').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
					var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
					var size = parseInt($(this).data('size')) || 50;
					$(this).easyPieChart({
						barColor: barColor,
						trackColor: trackColor,
						scaleColor: false,
						lineCap: 'butt',
						lineWidth: parseInt(size/10),
						animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
						size: size
					});
				})
			
				$('.sparkline').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
					$(this).sparkline('html',
									 {
										tagValuesAttribute:'data-values',
										type: 'bar',
										barColor: barColor ,
										chartRangeMin:$(this).data('min') || 0
									 });
				});
			
			
			  //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
			  //but sometimes it brings up errors with normal resize event handlers
			  $.resize.throttleWindow = false;
			
			  var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
			  var data = [
				{ label: "social networks",  data: 38.7, color: "#68BC31"},
				{ label: "search engines",  data: 24.5, color: "#2091CF"},
				{ label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
				{ label: "direct traffic",  data: 18.6, color: "#DA5430"},
				{ label: "other",  data: 10, color: "#FEE074"}
			  ]
			  function drawPieChart(placeholder, data, position) {
			 	  $.plot(placeholder, data, {
					series: {
						pie: {
							show: true,
							tilt:0.8,
							highlight: {
								opacity: 0.25
							},
							stroke: {
								color: '#fff',
								width: 2
							},
							startAngle: 2
						}
					},
					legend: {
						show: true,
						position: position || "ne", 
						labelBoxBorderColor: null,
						margin:[-30,15]
					}
					,
					grid: {
						hoverable: true,
						clickable: true
					}
				 })
			 }
			 drawPieChart(placeholder, data);
			
			 /**
			 we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			 so that's not needed actually.
			 */
			 placeholder.data('chart', data);
			 placeholder.data('draw', drawPieChart);
			
			
			  //pie chart tooltip example
			  var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
			  var previousPoint = null;
			
			  placeholder.on('plothover', function (event, pos, item) {
				if(item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : " + item.series['percent']+'%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}
				
			 });
			
				/////////////////////////////////////
				$(document).one('ajaxloadstart.page', function(e) {
					$tooltip.remove();
				});
			
			
			
			
				var d1 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d1.push([i, Math.sin(i)]);
				}
			
				var d2 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d2.push([i, Math.cos(i)]);
				}
			
				var d3 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.2) {
					d3.push([i, Math.tan(i)]);
				}
				
			
				var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
				$.plot("#sales-charts", [
					{ label: "Domains", data: d1 },
					{ label: "Hosting", data: d2 },
					{ label: "Services", data: d3 }
				], {
					hoverable: true,
					shadowSize: 0,
					series: {
						lines: { show: true },
						points: { show: true }
					},
					xaxis: {
						tickLength: 0
					},
					yaxis: {
						ticks: 10,
						min: -2,
						max: 2,
						tickDecimals: 3
					},
					grid: {
						backgroundColor: { colors: [ "#fff", "#fff" ] },
						borderWidth: 1,
						borderColor:'#555'
					}
				});
			
			
				$('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('.tab-content')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
			
				$('.dialogs,.comments').ace_scroll({
					size: 300
			    });
				
				
				//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
				//so disable dragging when clicking on label
				var agent = navigator.userAgent.toLowerCase();
				if("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
				  $('#tasks').on('touchstart', function(e){
					var li = $(e.target).closest('#tasks li');
					if(li.length == 0)return;
					var label = li.find('label.inline').get(0);
					if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
				});
			
				$('#tasks').sortable({
					opacity:0.8,
					revert:true,
					forceHelperSize:true,
					placeholder: 'draggable-placeholder',
					forcePlaceholderSize:true,
					tolerance:'pointer',
					stop: function( event, ui ) {
						//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
						$(ui.item).css('z-index', 'auto');
					}
					}
				);
				$('#tasks').disableSelection();
				$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
					if(this.checked) $(this).closest('li').addClass('selected');
					else $(this).closest('li').removeClass('selected');
				});
			
			
				//show the dropdowns on top or bottom depending on window height and menu position
				$('#task-tab .dropdown-hover').on('mouseenter', function(e) {
					var offset = $(this).offset();
			
					var $w = $(window)
					if (offset.top > $w.scrollTop() + $w.innerHeight() - 100) 
						$(this).addClass('dropup');
					else $(this).removeClass('dropup');
				});
			
			})
		</script>
	</body>
	</html>