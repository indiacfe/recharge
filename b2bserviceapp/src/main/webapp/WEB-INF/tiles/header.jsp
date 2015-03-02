<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="header_panel">
    <div id="title_section"> CYBERTEL <br/> <h4><font color="white">Recharge Service</font></h4></div>
    <div id="top_right_section">
      <ul>
        <li><a href="home">Home</a></li>
       	<li>User: <sec:authentication property="principal.username" /> <a href='<c:url value="/j_spring_security_logout" />' > Logout</a></li> 
      </ul>
    </div>
  </div>