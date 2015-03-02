
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

		var thirdparty1 = document.getElementById("fromDate").value;

		document.getElementById("fDate").value = thirdparty1;

		var thirdparty2 = document.getElementById("toDate").value;

		document.getElementById("tDate").value = thirdparty2;

		var getexel = document.getElementById("acstmt");
		if (document.getElementById("fDate").value != ''
				&& document.getElementById("tDate").value != '') {

			getexel.submit();

		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Money Transfer History</h2>
		<div class="floatright">
			<ul class="floatleft">
				<li>Sender User :${mobileNo}</li>
				<li><a href="logout">Log out</a></li>
			</ul>
		</div>
		<div class="block ">
			<form:form method="post" action="" modelAttribute="remittanceuserdto"
				onsubmit="return dateRestriction();">
				<table class="form">
					<c:if test="${not empty alert}">
						<div class="message info">
							<h5>Information</h5>
							<p>${alert}.</p>
						</div>
					</c:if>
					<tbody>
						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="fromDate" type="text" id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td><label>To Date</label></td>
							<td><form:input class="inputfield" required="required"
									path="toDate" type="text" id="toDate"></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Submit" />
			</form:form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty moneytransfer }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>DATE/TIME</th>
									<th>TransactionId</th>
									<th>Sender Name</th>
									<th>Sender Mobile</th>
									<th>Bene Name</th>
									<th>Bene Mobile</th>
									<th>IFSCCODE</th>
									<th>To Account No</th>
									<th>From Account No</th>
									<th>Transaction Amount</th>
									<th>Transaction Charge</th>
									<th>Remarks</th>
									<th>Account Type</th>
									<th>Transaction Status</th>


								</tr>
							</thead>
							<tbody>
								<c:forEach items="${moneytransfer}" var="reportItem"
									varStatus="status">
									<tr class="odd gradeX">
										<td class="center">${status.count}</td>

										<td class="center">${reportItem.dateTime}</td>
										<td class="center">${reportItem.transactionId}</td>
										<td class="center">${reportItem.senderName}</td>
										<td class="center">${reportItem.senderMobile}</td>
										<td class="center">${reportItem.benename}</td>
										<td class="center">${reportItem.benemobile}</td>
										<td class="center">${reportItem.IFSCCode}</td>
										<td class="center">${reportItem.toaccountno}</td>
										<td class="center">${reportItem.fromaccountno}</td>
										<td class="center">${reportItem.transactionamount}</td>
										<td class="center">${reportItem.serviceCharge}</td>
										<td class="center">${reportItem.remarks}</td>

										<td class="center">${reportItem.accountType}</td>
										<td class="center">${reportItem.transactionstatus}</td>

										<td class="center">${reportItem.impsreferenceNO}</td>
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



