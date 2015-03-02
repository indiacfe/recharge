<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h2>COMPLETED CASH DEPOSIT REQUESTS</h2>
<div id="table">
	<c:if test="${not empty depositrequestlist }">
		<table border="1" width="100%">
			<tr bgcolor="#3399FF">
				<td><strong>SN.</strong></td>					
				<td><strong>REQ. TYPE</strong></td>
				<td><strong>PAYMENT MODE</strong></td>
				<td><strong>AMOUNT</strong></td>
				<td><strong>BANK</strong></td>							
				<td><strong>RECIEPT NO.</strong></td>
				<td><strong>DATE</strong></td>
				<td><strong>REMARK</strong></td>
			</tr>
			<c:forEach items="${depositrequestlist}" var="requestItem" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${requestItem.requestType}</td>
					<td>${requestItem.paymentMode}</td>
					<td>RS.${requestItem.amount}0</td>
					<td>${requestItem.counter}</td>
					<td>${requestItem.recieptChequeDdNo}</td>					
					<td>${requestItem.chequeDdDate}</td>
					<td>${requestItem.remark}</td>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
