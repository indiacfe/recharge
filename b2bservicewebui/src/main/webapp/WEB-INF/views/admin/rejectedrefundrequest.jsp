<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "asc" ] ]
		});
	});
	
	function dateRestriction() {

		var date1 = document.getElementById("fromDate").value;
		var days = 1000 * 3600 * 24;
		var date2 = document.getElementById("toDate").value;
		var firstDate = new Date(date1);
		var secondDate = new Date(date2);
		var timeDiffInDays = Math.floor(Math.abs(secondDate.getTime()
				- firstDate.getTime())
				/ days);
		if (timeDiffInDays > 31 || timeDiffInDays < 0) {
			alert("Please Set the Time Within One Month");
			return false;
		}
	}
	
</script>

<div class="grid_10">
	<div class="box round first grid" >
		<h2>ALL REJECTED REFUND REQUESTS</h2>
		<div class="block" >
		<form method="get" action="rejectedRefundRequestList"
				onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="fromDate" type="text" id="fromDate"></td>
						</tr>
						<tr>
							<td class="col1"><label>To Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="toDate" type="text" id="toDate"></input></td>

						</tr>

					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="submit" />
			</form>
			<c:if test="${not empty detailForRejectedRefundList }">
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
							<th>SERVICE PROVIDER</th>
							<th>AMOUNT</th>
							<th>REFUND AMOUNT</th>
							<th>CREDIT AMOUNT</th>
							<th>REMARK</th>
							<th>REFUND</th>
							

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${detailForRejectedRefundList}" var="requestItem"
							varStatus="status">
							<tr class="odd gradeX">

								<td>${status.count}</td>
								<td>${requestItem.createdAt}</td>
								<td>${requestItem.updatedAt}</td>
								<td>${requestItem.transid}</td>
								<td>${requestItem.retailerName}</td>
								<td>${requestItem.connectionid} ${requestItem.mobileNo}</td>
								<td>${requestItem.operator}</td>
								<td>${requestItem.thirdPartyServiceProviderName}</td>
								<td>${requestItem.amount}</td>
								<td>${requestItem.amount-requestItem.creditAmountFranchisee}</td>
								<td>${requestItem.creditAmountFranchisee}</td>
								<td>${requestItem.remark}</td>
								<td><form method="POST" action="refundamount"
										onsubmit="return confirmation(this);">
										<input type="hidden" name="franId"
											value="${requestItem.retailerId}"> <input
											type="hidden" name="amount" value="${requestItem.amount}">
										<input type="hidden" name="creditAmount"
											value="${requestItem.creditAmountFranchisee}"> <input
											type="hidden" name="id" value="${requestItem.id}"> <input
											type="hidden" name="status" value="${requestItem.status}">
										<input type="hidden" name="thirdPartyServiceProviderName"
											value="${requestItem.thirdPartyServiceProviderName}"> <input class="button"
											type="submit" value="Refund" id="refund">
									</form></td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>


