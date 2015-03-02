<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
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

		var getexel = document.getElementById("custrechargehis");
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
			<form:form method="post" action="rechargehistory"
				modelAttribute="customerAccountStatement">
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
				<h6>
					<c:if test="${not empty fromDate}">
						<label>&nbsp;&nbsp;From Date :-&nbsp;&nbsp;</label>
						<c:out value="${fromDate}"></c:out>
					</c:if>
					<c:if test="${not empty toDate}">
						<label>&nbsp;&nbsp;To Date:-&nbsp;&nbsp;</label>
						<c:out value="${toDate}"></c:out>
					</c:if>
				</h6>
				<div class="block">


					<c:if test="${not empty reportList }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>DATE/TIME</th>
									<th>TID</th>
									<th>Client TID</th>
									<th>Operator</th>
									<th>Amount</th>
									<th>Mobile/Connection No.</th>
									<th>Status</th>
									<th>Commission</th>
									<th>Complain</th>


								</tr>
							</thead>

							<tbody>
								<c:forEach items="${reportList}" var="reportItem"
									varStatus="status">
									<tr class="odd gradeX">
										<td class="center">${status.count}</td>
										<td class="center">${reportItem.createdAt}</td>
										<td class="center">${reportItem.transactionId}</td>
										<td class="center">${reportItem.clientTransId}</td>
										<td class="center">${reportItem.operator}</td>
										<td class="center">Rs.<fmt:formatNumber type="number"
												maxFractionDigits="2" value="${reportItem.displayAmount}" /></td>
										<td class="center">${reportItem.mobileNo}<c:if
												test="${empty reportItem.mobileNo}">${reportItem.connectionid}</c:if>
										</td>
										<td class="center">${reportItem.status}</td>
										<td class="center">Rs.<fmt:formatNumber type="number"
												maxFractionDigits="2"
												value="${reportItem.creditAmountCustomer}" /></td>
										<td class="center"><form method="POST"
												action="refundrequestcust">
												<input class="inputfield" name="clientTransId" type="hidden"
													id="transId" value="${reportItem.clientTransId}" />
												<c:choose>
													<c:when
														test="${(fn:containsIgnoreCase(reportItem.status, 'success') or fn:containsIgnoreCase(reportItem.status, 'error')) and !reportItem.refunded}">
														<input type="submit" class="button" value="Book Complaint" />
													</c:when>
													<c:otherwise>
														<button type="button" class="button" disabled>Book
															Complaint</button>
													</c:otherwise>
												</c:choose>
											</form></td>


									</tr>

								</c:forEach>
							</tbody>
						</table>
						<table>
							<tr>
								<td colspan="8"><h2>Total&nbsp;&nbsp;&nbsp;</h2></td>
								<td>DR.: Rs.<fmt:formatNumber type="number"
										maxFractionDigits="2" value="${totalDebit}" />
									&nbsp;&nbsp;&nbsp;
								</td>
								<td>CR.: Rs. <fmt:formatNumber type="number"
										maxFractionDigits="2" value="${totalCredit}" /></td>
							</tr>
						</table>

						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
					<form:form method="post" action="CustomerRechargeRistory"
						modelAttribute="customerAccountStatement" id="custrechargehis">

						<form:hidden id="num" path="number" />
						<form:hidden id="fDate" path="fromDate" />
						<form:hidden id="tDate" path="toDate" />


					</form:form>

				</div>
			</div>
		</div>

	</div>
</div>


