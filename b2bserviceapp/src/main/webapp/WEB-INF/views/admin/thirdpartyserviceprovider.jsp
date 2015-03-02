<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="search_panel1">
	<h1>Current Third Party Service Provider List</h1>
</div>
<div id="table">
	<c:if test="${not empty thirdPartyServiceList}">
		<table border="1">
			<tr bgcolor="#3399FF">
				<td><strong>Service Type</strong></td>
			    <td><strong>Operator Name</strong></td>
			    <td><strong>Current Service Provider</strong></td>
			</tr>
			<c:forEach items="${thirdPartyServiceList}" var="reportItem"
				varStatus="status">
				<tr>
					<td>${reportItem.serviceType}</td>	
				    <td>${reportItem.operatorName}</td>
				    <td>${reportItem.serviceProvider}</td>
									
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>