<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h2>CASH DEPOSIT REQUESTS</h2>
<form action="showallcashdepositrequestsdist" method="get">
<input class="button" type="submit" value="processed requests">
</form>
<div id="table">
	<c:if test="${not empty depositrequestlist }">
		<table border="1" width="100%">
			<tr bgcolor="#3399FF">
				<td><strong>SN.</strong></td>					
				<td><strong>REQ. TYPE</strong></td>
				<td><strong>PAYMENT MODE</strong></td>
				<td><strong>AMOUNT</strong></td>
				<td><strong>RET ID</strong></td>				
				<td><strong>BANK</strong></td>							
				<td><strong>RECIEPT NO.</strong></td>
				<td><strong>DATE</strong></td>
				<td><strong>REMARK</strong></td>
				<td><strong>TRANSFER</strong></td>
			</tr>
			<c:forEach items="${depositrequestlist}" var="requestItem" varStatus="status">
				<tr>
				<form action="showTransferPage" method="POST">
					<td>${status.count}</td>
					<td>${requestItem.requestType}</td>
					<td>${requestItem.paymentMode}</td>
					<td>RS.${requestItem.amount}0</td>
					<td>R000000${requestItem.requesterId}</td>
					<td>${requestItem.counter}</td>
					<td>${requestItem.recieptChequeDdNo}</td>					
					<td>${requestItem.chequeDdDate}</td>
					<td>${requestItem.remark}</td>
					<td>
						<input type="hidden" name="amount" value="${requestItem.amount}">
						<input type="hidden" name="id" value="${requestItem.id}">
						<input type="hidden" name="requesterId" value="${requestItem.requesterId}">
						<input class="button" type="submit" value="transfer">
					</td>
				</form>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
