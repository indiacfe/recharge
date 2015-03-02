<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you sure want to transfer the amount to customer");
		if (con == true) {
			document.getElementById("refund").focus();
			return true;
		} else {
			return false;
		}
	}
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "asc" ] ]
		});
	});
</script>

<div class="grid_10">
	<div class="box round first grid" id="fromDate">
		<h2>ALL REFUND REQUESTS</h2>
		<div class="block" id="toDate">
			<c:if test="${not empty detailForRefund }">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>SN.</th>
							<th>Created Date</th>
							<th>Refund Requested Date</th>
							<th>TRANS ID</th>
							<th>RET. NAME</th>
							<th>CONNECTION_ID/ MOBILE_NO</th>
							<th>OPERATOR</th>
							<th>THIRD PARTY SERVICE PROVIDER</th>
							<th>AMOUNT</th>
							<th>REFUND AMOUNT</th>
							<th>CREDIT AMOUNT</th>
							<th>REFUND</th>
							<th>REJECT</th>
							<th>Remark</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${detailForRefund}" var="requestItem"
							varStatus="status">
							<tr class="odd gradeX">

								<td>${status.count}</td>
								<td>${requestItem.createdAt}</td>
								<td>${requestItem.updatedAt}</td>
								<td>${requestItem.transid}</td>
								<td>${requestItem.retailerName}</td>
								<td>${requestItem.connectionid}${requestItem.mobileNo}</td>
								<td>${requestItem.operator}</td>
								<td>${requestItem.thirdPartyServiceProviderName}</td>
								<td>${requestItem.amount}</td>
								<td>${requestItem.amount-requestItem.creditAmountFranchisee}</td>
								<td>${requestItem.creditAmountFranchisee}</td>
								<td><form method="POST" action="customerrefundamount"
										onsubmit="return confirmation(this);">
										<input type="hidden" name="custId"
											value="${requestItem.retailerId}"> <input
											type="hidden" name="amount" value="${requestItem.amount}">
										<input type="hidden" name="creditAmount"
											value="${requestItem.creditAmountFranchisee}"> <input
											type="hidden" name="id" value="${requestItem.id}"> <input
											type="hidden" name="status" value="${requestItem.status}">
										<input class="button" type="submit" value="Refund" id="refund">
									</form></td>



								<form action="rejectrefund" method="post">
									<input type="hidden" name="id" value="${requestItem.id}">
									<td><input class="button" type="submit" value="Reject"
										id="reject"></td>
								<td><input type="text" name="remark" id="remarktext">
								</td>
								</form>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>


