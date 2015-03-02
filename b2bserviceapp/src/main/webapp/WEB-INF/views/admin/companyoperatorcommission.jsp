<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="search_panel1">
	<h1>Company Operator Commission Report</h1>
</div>
<div id="table">
 <c:if test="${not empty companyOperatorCommissionList }"> 
		<table border="1">
			<tr bgcolor="#3399FF">
				<td><strong>Operator Name</strong></td>
				<td><strong>Recharge Type</strong></td>
				<td><strong>Third Party Service Provider</strong></td>
                <td><strong>Retailer Commission</strong></td>
			</tr>
			<c:forEach items="${companyOperatorCommissionList}" var="reportItem">
				<tr>
					<td>${reportItem.operatorName}</td>
					<td>${reportItem.rechargeType}</td>
					<td>${reportItem.thirdpartyServiceProvider}</td>
					<td>${reportItem.retailercommision}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if> 
</div>