<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<h2>Search Transaction</h2>
		<div class="block ">
			<form method="post" action="searchtransaction">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Transaction Id:</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="transactionId" type="text"
								id="transactionId"></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="Search" />
			</form>
			<br /> <br />

			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty beanValue }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>Date/Time</th>
									<th>Mobile Number</th>
									<th>Connection No.</th>
									<th>Operator</th>
									<th>Transaction Type</th>
									<th>Debit</th>
									<th>Credit</th>
									<th>Status</th>

								</tr>
							</thead>

							<tbody>
								<tr class="odd gradeX">
									<td align="center"><fmt:formatDate
											value="${beanValue.createdAt}" pattern="dd-MM-yyyy HH:mm" /></td>
									<td align="center">${beanValue.mobileNo}</td>
									<td align="center">${beanValue.connectionid}</td>
									<td align="center">${beanValue.operator}</td>
									<td align="center">${beanValue.transactionName}</td>
									<td align="center"><fmt:formatNumber type="number"
											value="${beanValue.amount}" maxFractionDigits="2"></fmt:formatNumber></td>
									<td align="center"><fmt:formatNumber type="number"
											value="${beanValue.creditAmountFranchisee}"
											maxFractionDigits="2"></fmt:formatNumber></td>
									<td align="center">${beanValue.status}</td>
								</tr>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
