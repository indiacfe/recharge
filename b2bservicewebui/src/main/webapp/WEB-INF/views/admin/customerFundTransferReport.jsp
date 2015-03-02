<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function dateRestriction() {

		var date1 = document.getElementById("fromDate").value;
		var days = 1000 * 3600 * 24;
		var date2 = document.getElementById("toDate").value;
		var firstDate = new Date(date1);
		var secondDate = new Date(date2);
		var timeDiffInDays = Math.floor(Math.abs(secondDate.getTime()
				- firstDate.getTime())
				/ days);
		if (timeDiffInDays > 30 || timeDiffInDays < 0) {
			alert("Please set the time within 30 days");
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
	<div class="box round first">
		<h2>Fund Transfer Statement</h2>
		<div class="block ">
			<form:form method="post" action="apifundtranserreport"
				modelAttribute="adminfundtransfer" onsubmit="dateRestriction();">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Customer Id: (Optional)</label></td>
							<td class="col2"><form:input class="inputfield"
									path="number" type="text" id="number"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="fromDate" type="text" id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>To Date</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="toDate" type="text" id="toDate"></form:input></td>

						</tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="View" />
			</form:form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty reportList }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>Date/Time</th>
									<th>Firm Name</th>
									<th>Mobile Number</th>
									<th>Tid</th>
									<th>Type</th>
									<th>Transfer Amount</th>
									<th>New Amount</th>
									<th>Old Balance</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${reportList}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">

										<td class="center">${status.count}</td>
										<td class="center">${reportItem.createdAt}</td>
										<td class="center">${reportItem.firmName}</td>
										<td class="center">${reportItem.mobileNumber}</td>
										<td class="center">${reportItem.transactionId}</td>
										<td class="center">${reportItem.transferType}</td>
										<td class="center">Rs. ${reportItem.transferAmount}</td>
										<td class="center">Rs.<fmt:formatNumber type="number"
												maxFractionDigits="2" value=" ${reportItem.newBalance}" /></td>
										<td class="center">Rs. <fmt:formatNumber type="number"
												maxFractionDigits="2" value="${reportItem.preBalance}" /></td>



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
