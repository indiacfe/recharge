<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

		var thirdparty1 = document.getElementById("fromDate").value;

		document.getElementById("fDate").value = thirdparty1;

		var thirdparty2 = document.getElementById("toDate").value;

		document.getElementById("tDate").value = thirdparty2;

		var thirdparty3 = document.getElementById("number").value;

		document.getElementById("num").value = thirdparty3;

		var getexel = document.getElementById("RechHistory");
		if (document.getElementById("fDate").value != ''
				&& document.getElementById("tDate").value != '') {

			getexel.submit();

		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Recharge History</h2>
		<div class="block ">
			<form:form method="post" action="rechargehistoryretrieve"
				modelAttribute="accountstatement"
				onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Connection/Mobile No.</label></td>
							<td class="col2"><form:input class="inputfield"
									path="number" type="text" id="number"></form:input></td>
						</tr>

						<tr>
							<td><label>From Date</label></td>
							<td><form:input class="inputfield" required="required"
									path="fromDate" type="text" id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td><label>To Date</label></td>
							<td><form:input class="inputfield" required="required"
									path="toDate" type="text" id="toDate"></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="View" />
			</form:form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty reportList }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>DATE/TIME</th>
									<th>TID</th>
									<th>Operator</th>
									<th>Amount</th>
									<th>Mobile/Connection No.</th>
									<th>Account</th>
									<th>Status</th>
									<th>Commission</th>
									<th>Refund Request</th>


								</tr>
							</thead>

							<tbody>
								<c:forEach items="${reportList}" var="reportItem"
									varStatus="status">
									<tr class="odd gradeX">
										<td class="center">${status.count}</td>
										<td class="center">${reportItem.createdAt}</td>
										<td class="center">${reportItem.transactionId}</td>
										<td class="center">${reportItem.operator}</td>
										<td class="center">Rs.${reportItem.displayAmount}</td>
										<td class="center">${reportItem.mobileNo}${reportItem.connectionid}</td>
										<td class="center">${reportItem.account}</td>
										<td class="center">${reportItem.status}</td>
										<td class="center">Rs.${reportItem.displayCreditAmountFranchisee}</td>
										<td class="center"><form method="POST"
												action="refundrequestfran">
												<input class="inputfield" name="tid" type="hidden"
													id="transId" value="${reportItem.transactionId}" />
												<c:choose>
													<c:when
														test="${(fn:containsIgnoreCase(reportItem.status, 'success') or fn:containsIgnoreCase(reportItem.status, 'error')) and !reportItem.refunded}">
														<input type="submit" class="button" value="Book Complaint" />
													</c:when>
													<c:otherwise>
														<button type="button" class="button" disabled>Book Complaint</button>
													</c:otherwise>
												</c:choose>
											</form></td>

									</tr>

								</c:forEach>
							</tbody>
						</table>
						<table>
							<tr>
								<td><h2 style="margin-right: 7px">Total</h2>&emsp;&emsp;&emsp;</td>

								<td><h6>
										Debit: Rs.
										<fmt:formatNumber type="number" value="${totalDebit}"
											maxFractionDigits="2"></fmt:formatNumber>
										&emsp;&emsp;&emsp;
									</h6></td>
								<td><h6>
										Credit: Rs.
										<fmt:formatNumber type="number" value="${totalCredit}"
											maxFractionDigits="2"></fmt:formatNumber>
									</h6></td>
							</tr>
						</table>
						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
					<form:form method="post" action="RetailerRechargeHistry"
						modelAttribute="accountstatement" id="RechHistory">

						<form:hidden id="num" path="number" />
						<form:hidden id="fDate" path="fromDate" />
						<form:hidden id="tDate" path="toDate" />


					</form:form>

				</div>
			</div>
		</div>

	</div>
</div>


