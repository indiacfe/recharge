
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
		<h2>Operator Commission Details</h2>
		<div class="block" id="toDate">
			<c:if test="${not empty commission }">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>SN.</th>
							<th>Operator Name</th>
							<th>Recharge Type</th>
							<th>Commission</th>
 						</tr>
					</thead>
					<tbody>
						<c:forEach items="${commission}" var="requestItem"
							varStatus="status">
							<tr class="odd gradeX">
								<td align="center">${status.count}</td>
								<td align="center">${requestItem.operatorName}</td>
								<td align="center">${requestItem.rechargeType}</td>
								<td align="center">Rs.${requestItem.customerCommission}</td> 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>
