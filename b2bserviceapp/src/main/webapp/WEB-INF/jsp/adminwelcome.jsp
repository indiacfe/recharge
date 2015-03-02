<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/contentslider.css" />
<script type="text/javascript" src="css/contentslider.js"></script>
<!--[if lt IE 7]><style type="text/css">.thumb_box span { behavior: url(iepngfix.htc); }</style><![endif]-->
<title></title>
</head>
<body>



      <script type="text/javascript">
            featuredcontentslider.init({
            id: "slider2",
            contentsource: ["inline", ""],
            toc: "markup",
            nextprev: ["Previous", "Next"],
            revealtype: "click",
            enablefade: [true, 0.2],
            autorotate: [true, 3000],
            onChange: function(previndex, curindex){
            }
            })
    </script>
   
    
    <div id="login_panel" align="center">
      <h1>Login</h1>
     <form id="form1" name="form1" method="post" action="<c:url value='j_spring_security_check'/>" style="margin:0px;">
      <div class="form_row">
             <input type="hidden" name="category" value="ROLE_ADMIN"/> 
            </div>
        <div class="form_row">
          <label>User Name</label>
           <input type="text" name='j_username' id="textfield" class="inputfield" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
        </div>
        <div class="form_row">
          <label>Password </label>
          <input type="password" name='j_password' id="textfield2" class="input" />
        </div>
        <input class="button" type="submit" name="Submit" value="Login" />
      </form>
 </div>
    
 


</body>
</html>