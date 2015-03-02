<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
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
		<h2>Cash Deposit Requests List</h2>
		<div class="block " id="toDate">
			<c:if test="${not empty depositrequestlist }">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>SN.</th>
							<th>Mobile Number</th>
							<th>Firm Name</th>
							<th>Req. Type</th>
							<th>Payment Mode</th>
							<th>Amount</th>
							<th>Bank</th>
							<th>Receipt No.</th>
							<th>Date</th>
							<th>Remark</th>
							<th>Transfer</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${depositrequestlist}" var="requestItem"
							varStatus="status">

							<tr class="odd gradeX">


								<td class="center">${status.count}</td>
								<td class="center">${requestItem.mobileNumber}</td>
								<td class="center">${requestItem.firmName}</td>
								<td class="center">${requestItem.requestType}</td>
								<td class="center">${requestItem.paymentMode}</td>
								<td class="center">RS. ${requestItem.amount}0</td>
								<td class="center">${requestItem.counter}</td>
								<td class="center">${requestItem.recieptChequeDdNo}</td>
								<td class="center">${requestItem.chequeDdDate}</td>
								<td class="center">${requestItem.remark}</td>

								<td class="center"><form
										action="AdminFundTransferToFranchisee" method="POST">
										<input type="hidden" name="amount"
											value="${requestItem.amount}"> <input type="hidden"
											name="id" value="${requestItem.id}"> <input
											type="hidden" name="requesterId"
											value="${requestItem.requesterId}"> <input
											class="button" type="submit" value="transfer">
									</form></td>


							</tr>


						</c:forEach>
					</tbody>

				</table>
				<input type="button" class="button" value="Download Excel Document"
					onclick="window.location.href='AdminCashDepositRequest'" />
			</c:if>


		</div>
	</div>
</div>

