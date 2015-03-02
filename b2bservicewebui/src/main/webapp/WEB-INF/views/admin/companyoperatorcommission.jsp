
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "asc" ] ]
		});
	});
//-->
</script>
<div class="grid_10">
	<div class="box round first grid" id="fromDate">
		<h2>Company Operator Commission Report</h2>
		<div class="block" id="toDate">



			<c:if test="${not empty companyOperatorCommissionList }">
				<table class="data display datatable" id="example">
					<thead>
						<tr>
							<th>Operator Name</th>
							<th>Recharge Type</th>
							<th>Third Party Service Provider</th>
							<th>Retailer Commission</th>
							<th>Commission Type</th>
							<th>Deduction Type</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${companyOperatorCommissionList}"
							var="reportItem">

							<tr class="odd gradeX">



								<td class="center">${reportItem.operatorName}</td>
								<td class="center">${reportItem.rechargeType}</td>
								<td class="center">${reportItem.thirdpartyServiceProvider}</td>
								<td class="center">${reportItem.retailercommision}</td>
								<td class="center">${reportItem.comissionType}</td>
								<td class="center">${reportItem.deductionType}</td>

							</tr>


						</c:forEach>
					</tbody>

				</table>
				<input type="button" class="button" value="Download Excel Document"
					onclick="window.location.href='AdminCompanyOperatorCommissionList'" /> 
				
			</c:if>


		</div>
	</div>
</div>
