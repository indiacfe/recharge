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
		if (timeDiffInDays > 31 || timeDiffInDays < 0) {
			alert("Please Set the Time Within One Month");
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

	function exeldoc() {
		var thirdparty1 = document.getElementById("number").value;
		document.getElementById("num").value = thirdparty1;
		var thirdparty2 = document.getElementById("fromDate").value;
		document.getElementById("fDate").value = thirdparty2;
		var thirdparty3 = document.getElementById("toDate").value;
		document.getElementById("tDate").value = thirdparty2;

		var getexel = document.getElementById("transferFund");
		if (document.getElementById("fDate").value != ''
				&& document.getElementById("tDate").value != '') {
			getexel.submit();

		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Fund Transfer Statement</h2>
		<div class="block ">
			<form:form method="post" action="adminfundtransferreport"
				modelAttribute="adminfundtransfer"
				onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Distributor or Retailer ID
									(Optional)</label></td>
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
									<th>LoadType</th>
									<th>Transfer Amount</th>
									<th>New Amount</th>
									<th>Old Balance</th>
									<th>Third Party Name</th>

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
										<td class="center">${reportItem.transferTo}</td>
										<td class="center">Rs.<fmt:formatNumber type="number"
												maxFractionDigits="2" value=" ${reportItem.transferAmount}" /></td>
										<td class="center">Rs.<fmt:formatNumber type="number"
												maxFractionDigits="2" value=" ${reportItem.newBalance}" /></td>
										<td class="center">Rs.<fmt:formatNumber type="number"
												maxFractionDigits="2" value="  ${reportItem.preBalance}" /></td>
										<td class="center">${reportItem.thirdPartyServiceProviderName}</td>
									</tr>

								</c:forEach>
							</tbody>

						</table>

						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
					<form:form method="post" action="AdminFundTransfer"
						modelAttribute="adminfundtransfer" id="transferFund">
						<form:hidden path="number" id="num" />
						<form:hidden path="fromDate" id="fDate" />
						<form:hidden path="toDate" id="tDate" />
					</form:form>

				</div>
			</div>
		</div>
	</div>
</div>
