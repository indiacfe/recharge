
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "desc" ] ]
		});
	});
</script>
<div class="grid_10">
	<div class="box round first grid" id="fromDate">
		<h2>Refund Request Details</h2>
		<div class="block" id="toDate">
			<c:if test="${not empty refundlist }">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>SN.</th>
							<th>TID</th>
							
							<th>Creation Date/Time</th>
							<th>Updated Date/Time</th>
							<th>Status</th>
							<th>Remark</th>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${refundlist}" var="requestItem"
							varStatus="status">
							<tr class="odd gradeX">
								<td class="center">${status.count}</td>
								<td class="center">${requestItem.clientTransId}</td>
								
								<td class="center">${requestItem.createdat}</td>
								<td class="center">${requestItem.updatedAt}</td>
								<td class="center">${requestItem.status}</td>
								<td class="center">${requestItem.remark}</td>
								<td><form method="GET" action="refundetailscust">
										<input type="hidden" name="clientTransId"
											value="${requestItem.clientTransId}"> <input
											class="button" type="submit" value="Details" id="submit">
									</form></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>






