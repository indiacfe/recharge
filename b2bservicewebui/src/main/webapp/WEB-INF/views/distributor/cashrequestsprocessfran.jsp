
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "desc" ] ]
		});
	});
//-->
</script>
<div class="grid_10">
	<div class="box round first grid" id="fromDate">
		<h2>Cash Deposit Request</h2>
		<div class="block" id="toDate">
			<c:if test="${not empty depositrequestlist }">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>SN.</th>
							<th>REQ. TYPE</th>
							<th>PAYMENT MODE</th>
							<th>AMOUNT</th>
							<th>RET ID</th>
							<th>BANK</th>
							<th>RECIEPT NO.</th>
							<th>DATE</th>
							<th>REMARK</th>
							<th>TRANSFER</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${depositrequestlist}" var="requestItem"
							varStatus="status">
							<tr class="odd gradeX">
								<td class="center">${status.count}</td>
								<td class="center">${requestItem.requestType}</td>
								<td class="center">${requestItem.paymentMode}</td>
								<td class="center">RS.${requestItem.amount}0</td>
								<td class="center">R000000${requestItem.requesterId}</td>
								<td class="center">${requestItem.counter}</td>
								<td class="center">${requestItem.recieptChequeDdNo}</td>
								<td class="center">${requestItem.chequeDdDate}</td>
								<td class="center">${requestItem.remark}</td>
								<td class="center"><form action="showTransferPage"
										method="POST">
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
					onclick="window.location.href='DistCashDepositRequest'" />

			</c:if>


		</div>
	</div>
</div>

