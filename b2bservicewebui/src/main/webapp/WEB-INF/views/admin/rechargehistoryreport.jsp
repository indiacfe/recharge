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
	      
		var thirdparty1 = document.getElementById("sel").value;
	
		document.getElementById("tParty").value = thirdparty1;
	
		var getexel = document.getElementById("rechargehist");
		if (document.getElementById("fDate").value != ''
				&& document.getElementById("tDate").value != '') {
			
			getexel.submit();

		}
	
	}
</script>
<div class="grid_10">
<%-- <c:if test="${not empty Error}">
				<div class="message info">
					<h4>Information</h4>
					<h5>${Error}.</h5>
				</div>
			</c:if> --%>

	<div class="box round first">
		<h2>Recharge Report</h2>
		<div class="block ">
		
			<form method="get" action="rechargereport"
				onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="fromDate" type="text" id="fromDate"></td>
						</tr>
						<tr>
							<td class="col1"><label>To Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="toDate" type="text" id="toDate"></input></td>

						</tr>
						<tr>
							<td class="col1"><label>Third Party API:</label></td>
							<td class="col2"><select name="sel" id="sel">
									<option value="ALL">ALL</option>
									<option value="goprocess">GoProcess</option>
									<option value="instantpay">Instantpay</option>
									<option value="payworld">PayWorld</option>
									<option value="cyberplat">Cyberplat</option>
									<option value="mom">MoneyOnMobile</option>
									<option value="qiwi">Qiwi</option>
									<option value="hkrecharge">hkrecharge</option>
							</select></td>
						</tr>
						<tr>
							<td class="col1"><label>Status:</label></td>
							<td class="col2"><select name="status" id="status">
									<option value="ALL">ALL</option>
									<option value="SUCCESS">SUCCESS</option>
									<option value="FAILED">FAILED</option>
									<option value="REJECTED">REJECTED</option>
									<option value="REFUND">REFUND</option>
									<option value="ERROR">ERROR</option>
							</select></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="submit" />
			</form>
			<div class="box round first grid">
				<h6>
					<c:if test="${not empty fromdate}">
						<label>&nbsp;&nbsp;From Date :-&nbsp;&nbsp;</label>
						<c:out value="${fromdate}"></c:out>
					</c:if>


					<c:if test="${not empty todate}">
						<label>&nbsp;&nbsp;To Date:-&nbsp;&nbsp;</label>
						<c:out value="${todate}"></c:out>
					</c:if>



					<c:if test="${not empty status}">
						<label>&nbsp;&nbsp;Status:-&nbsp;&nbsp;</label>
						<c:out value="${status}"></c:out>
					</c:if>

				</h6>

				<div class="block">


					<c:if test="${not empty rechargeDetail}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>Date/Time</th>
									<th>Retailer ID</th>
									<th>Service Provider</th>
									<th>Mobile Number</th>
									<th>Connection No.</th>
									<th>Operator</th>
									<th>Transaction Type</th>
									<th>Debit</th>
									<th>Credit</th>
									<th>Account</th>
									<th>Status</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${rechargeDetail}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">

										<td>${status.count}</td>
										<td><fmt:formatDate value="${reportItem.createdAt}"
												pattern="dd-MM-yyyy HH:mm" /></td>
										<td>${reportItem.retailerId}</td>
										<td>${reportItem.thirdPartyServiceProvider}</td>
										<td>${reportItem.mobileNo}</td>
										<td>${reportItem.connectionid}</td>
										<td>${reportItem.operator}</td>
										<td>${reportItem.transactionName}</td>
										<td>${reportItem.amount}</td>
										<td>${reportItem.creditAmountFranchisee}</td>
										<td>${reportItem.commissionType}</td>
										<td>${reportItem.status}</td>

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
                     <form method="get" action="AdminRechargeHistoryReportList" id="rechargehist">
				<input type="hidden" id="fDate" name="fromDate" value="${fromdate}"/>
				 <input type="hidden" id="tDate" name="toDate" value="${todate}"/>
                 <input type="hidden" id="tParty" name="sel"/>
				 <input type="hidden" id="stata" name="status" value="${status}"/>
				</form>

				</div>
			</div>
		</div>
	</div>
</div>
