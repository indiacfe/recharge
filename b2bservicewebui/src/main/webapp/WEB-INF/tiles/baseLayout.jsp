<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="google-site-verification"
	content="7HnNjEKxyFQfzn7igGzB30pZpCjX6k_pe0RiLchjIKQ" />
<spring:url value="/css" var="css_url" />
<spring:url value="/js" var="js_url" />
<spring:url value="/" var="base_url" />
<link rel="stylesheet" type="text/css" href="${css_url}/datepickr.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="${css_url}/reset.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="${css_url}/text.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="${css_url}/grid.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="${css_url}/layout.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="${css_url}/nav.css"
	media="screen" />
<link href="${css_url}/fancy-button/fancy-button.css" rel="stylesheet"
	type="text/css" />
<script src="${js_url}/datepickr.js" type="text/javascript"></script>
<script src="${js_url}/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="${js_url}/jquery-ui/jquery.ui.core.min.js"
	type="text/javascript"></script>
<script src="${js_url}/jquery-ui/jquery.ui.widget.min.js"
	type="text/javascript"></script>
<script src="${js_url}/jquery-ui/jquery.ui.accordion.min.js"
	type="text/javascript"></script>
<script src="${js_url}/jquery-ui/jquery.effects.core.min.js"
	type="text/javascript"></script>
<script src="${js_url}/jquery-ui/jquery.effects.slide.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${css_url}/jquery.jqplot.min.css" />
<script language="javascript" type="text/javascript"
	src="${js_url}/jqPlot/jquery.jqplot.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${js_url}/jqPlot/plugins/jqplot.barRenderer.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${js_url}/jqPlot/plugins/jqplot.pieRenderer.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${js_url}/jqPlot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${js_url}/jqPlot/plugins/jqplot.highlighter.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${js_url}/jqPlot/plugins/jqplot.pointLabels.min.js"></script>
<script src="${js_url}/jquery-ui/jquery.ui.datepicker.min.js"
	type="text/javascript"></script>
<script src="${js_url}/setup.js" type="text/javascript"></script>
<script src="${js_url}/table/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		setupLeftMenu();
		/* setSelectedMenu(1); */

		setSidebarHeight();

		new datepickr('fromDate', {
			'dateFormat' : 'm/d/Y'
		});
		new datepickr('toDate', {
			'dateFormat' : 'm/d/Y'
		});

	});
</script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div class="container_12">
		<tiles:insertAttribute name="header" />
		<div class="clear"></div>
		<div class="grid_2">
			<tiles:insertAttribute name="menu" />
		</div>


		<tiles:insertAttribute name="body" />

		<div class="clear"></div>
	</div>
	<div id="site_info">
		<tiles:insertAttribute name="footer" />
	</div>
	<div class="clear"></div>

</body>
</html>
