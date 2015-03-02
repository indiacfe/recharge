
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
		<h2>Refund Details</h2>
		<div class="block" id="toDate">
			<c:if test="${not empty detailForRefund }">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>SN.</th>
							<th>TID</th>
							<th>Connection/Mobile No.</th>
							<th>Operator</th>
							<th>Amount</th>
							<th>Refund Amount</th>
							<th>Credit Amount</th>
							<th>Status</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${detailForRefund}" var="requestItem"
							varStatus="status">
							<tr class="odd gradeX">
								<td class="center">${status.count}</td>
								<td class="center">${requestItem.transid}</td>
								<td class="center">${requestItem.connectionid}${requestItem.mobileNo}</td>
								<td class="center">${requestItem.operator}</td>
								<td class="center">Rs.${requestItem.amount}</td>
								<td class="center">Rs.${requestItem.refundAmount}</td>
								<td class="center">Rs.${requestItem.creditAmountFranchisee}</td>
								<td class="center">${requestItem.status}</td>

							</tr>

						</c:forEach>
					</tbody>

				</table>
			</c:if>


		</div>
	</div>
</div>





