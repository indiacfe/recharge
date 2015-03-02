<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<spring:url value="/css" var="css_url" />
<link href="${css_url}/style.css" rel="stylesheet" type="text/css" />
<link href="${css_url}/datepicker.css" rel="stylesheet" type="text/css" />
<link href="${css_url}/contentslider.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${css_url}/contentslider.js"></script>
 <script type="text/javascript" src="${css_url}/slideshow.js"></script>  
<!--[if lt IE 7]><style type="text/css">.thumb_box span { behavior: url(iepngfix.htc); }</style><![endif]-->
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div id="container">
<table border="0" 
cellpadding="2" cellspacing="2" align="center">
<tr>
<td height="30" colspan="2"> <tiles:insertAttribute name="header" />
</td>
</tr>
<tr>
<td style="background:#E4E4E4;height: auto;" valign="top"> <tiles:insertAttribute name="menu" />
</td>

<td style="background:#E4E4E4;height: auto;" valign="top"><tiles:insertAttribute name="body" />
</td>
</tr>
<tr>
<td height="30" colspan="2"> <tiles:insertAttribute name="footer" />
</td>
</tr>
</table>
</div>
</body>
</html>
