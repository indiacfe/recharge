<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="search_panel1">
	<h1>User Detail List</h1>
</div>
<div id="table" style="padding-left : 50px">
	<c:if test="${not empty userdetail}">
		<table border="1">
			<tr bgcolor="#3399FF">
			    <td><strong>User Id</strong></td>
			    <td><strong>User Name</strong></td>
				<td><strong>Password</strong></td>
				<td><strong>Email-id</strong></td>
				<td><strong>Firm Name</strong></td>
			</tr>
			<c:forEach items="${userdetail}" var="reportItem"
				varStatus="status">
				<tr>
				    <td>${reportItem.userId}</td>
				    <td>${reportItem.userName}</td>
				    <td>${reportItem.password}</td>
				    <td>${reportItem.emailId}</td>
				    <td>${reportItem.firmName}</td>				
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>