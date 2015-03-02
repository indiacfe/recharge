<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you sure want to transfer the amount to retailer");
		if (con == true) {
			document.getElementById("refund").focus();
			return true;
		} else {
			return false;
		}
	}
</script>


<h2>ALL REFUND REQUESTS</h2>
<div id="table">
	<c:if test="${not empty detailForRefund }">
		<table border="1" width="100%">
			<tr bgcolor="#3399FF">
				<td><strong>SN.</strong></td>	
				<td><strong>DATE/TIME</strong></td>				
				<td><strong>TRANS ID</strong></td>
				<td><strong>RET. NAME</strong></td>
				<td><strong>CONNECTION_ID/ MOBILE_NO</strong></td>							
				<td><strong>OPERATOR</strong></td>
				<td><strong>AMOUNT</strong></td>
				<td><strong>REFUND AMOUNT</strong></td>
				<td><strong>CREDIT AMOUNT</strong></td>
				<td><strong>REFUND</strong></td>
			</tr>
			<c:forEach items="${detailForRefund}" var="requestItem" varStatus="status">
				<tr>
				<form method="POST" action="refundamount" onsubmit="return confirmation();">
					<td>${status.count}</td>
					<td>${requestItem.createdAt}</td>
					<td>${requestItem.transid}</td>
					<td>${requestItem.retailerName}</td>
					<td>${requestItem.connectionid}</td>
					<td>${requestItem.operator}</td>
					<td>${requestItem.amount}</td>					
					<td>${requestItem.amount-requestItem.creditAmountFranchisee}</td>
					<td>${requestItem.creditAmountFranchisee}</td>
					<td>
						<input type="hidden" name="franId" value="${requestItem.retailerId}">
						<input type="hidden" name="amount" value="${requestItem.amount}">
						<input type="hidden" name="creditAmount" value="${requestItem.creditAmountFranchisee}">	
						<input type="hidden" name="id" value="${requestItem.id}"> 
						<input type="hidden" name="status" value="${requestItem.status}"> 
						<input class="button" type="submit" value="refund" id="submit">
					</td>
				</form>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
