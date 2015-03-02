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
	<div class="box round first">
		<h2>Customer's Commision Report</h2>
		<div class="block ">
			<form method="get" action="commisionpercustomerdetails">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Select Customer</label></td>
							<td class="col2"><select name="custId" id="custId">
									<c:forEach var="cust" items="${custdetails}">
										<option value="${cust.userId}">${cust.firmName}</option>
									</c:forEach>

							</select></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="submit" />
			</form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty commission }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>Operator Name</th>
									<th>Recharge Type</th>
									<th>Commission</th>
									<th>ThirdParty Provider</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${commission}" var="requestItem"
									varStatus="status">
									<tr class="odd gradeX">
										<td class="center">${status.count}</td>
										<td class="center">${requestItem.operatorName}</td>
										<td class="center">${requestItem.rechargeType}</td>
										<td class="center">Rs.${requestItem.customerCommission}</td>
										<td class="center">${requestItem.thirdpartyServiceProvider}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>


				</div>
			</div>
		</div>
	</div>
</div>





