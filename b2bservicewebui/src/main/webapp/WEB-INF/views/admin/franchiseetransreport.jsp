<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">

function dateRestriction(){
	
	var date1 = document.getElementById("fromDate").value;
	var days=1000 * 3600 * 24;
	var date2 = document.getElementById("toDate").value;
	var firstDate = new Date(date1);
	var secondDate = new Date(date2);
	var timeDiffInDays = Math.floor(Math.abs(secondDate.getTime() - firstDate.getTime())/days);
	if(timeDiffInDays>31 || timeDiffInDays<0){
		alert("Please Set the Time Within One Month");
	return false;
	}
}

	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "desc" ] ]
		});
	});
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Transaction Statement</h2>
		<div class="block ">
			<form:form method="post" action="franchiseedayreport"
				modelAttribute="datewisereport" onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Retailer ID </label></td>
							<td class="col2"><form:input class="inputfield"
									path="number" type="text" id="number"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><div id="datemonthlabel">
									<label>From Date</label>
								</div></td>
							<td class="col2"><div id="datemonthinput">
									<form:input class="inputfield" required="required"
										path="fromDate" type="text" id="fromDate"></form:input>
								</div></td>
						</tr>
						<tr>
							<td class="col1"><div id="datemonthlabel">
									<label>To Date</label>
								</div></td>
							<td class="col2"><div id="datemonthinput">
									<form:input class="inputfield" required="required"
										path="toDate" type="text" id="toDate"></form:input>
								</div></td>
						</tr>

						<tr>
							<td class="col1"><label> Service Provider</label></td>
							<td class="col2"><form:select class="inputfield"
									required="required" path="serviceProvider" id="serviceProvider">
									<form:option value="-1">Select</form:option>
									<form:option value="goprocess">goprocess</form:option>
									<form:option value="payworld">payworld</form:option>
									<form:option value="instantpay">instantpay</form:option>
									<form:option value="CYBERTEL">Cybertel</form:option>
								</form:select></td>
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
									<th>Mobile Number</th>
									<th>Tid</th>
									<th>Amount</th>
									<th>New Amount</th>
									<th>Pre Balance</th>
									<th>Status</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${reportList}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">
										<td class="center">${status.count}</td>
										<td class="center"><fmt:formatDate
												value="${reportItem.createdAt}" pattern="dd-MM-yyyy HH:mm" />
										</td>
										<td class="center">${reportItem.mobileNo}</td>
										<td class="center">${reportItem.transid}</td>
										<td class="center">Rs.<fmt:formatNumber type="number"
												value=" ${reportItem.amount}" maxFractionDigits="2"></fmt:formatNumber></td>

										<td class="center"><c:if
												test="${not empty reportItem.retailerPreBal}">Rs.<fmt:formatNumber
													type="number" value="${reportItem.retailerPreBal}"
													maxFractionDigits="2"></fmt:formatNumber>
											</c:if></td>


										<td class="center"><c:if
												test="${not empty reportItem.retailerNewBal}">Rs.<fmt:formatNumber
													type="number" value=" ${reportItem.retailerNewBal}"
													maxFractionDigits="2"></fmt:formatNumber>
											</c:if></td>
										<td class="center">${reportItem.status}</td>

									</tr>

								</c:forEach>
							</tbody>
						</table>
						<table>
							<tr>
								<td><h2 style="margin-right: 7px">Total</h2>&emsp;&emsp;&emsp;</td>

								<td><h6>
										Amount: Rs.
										<fmt:formatNumber type="number" value="${totalamount}"
											maxFractionDigits="2"></fmt:formatNumber>
										&emsp;&emsp;&emsp;
									</h6></td>

							</tr>
						</table>
					</c:if>


				</div>
			</div>
		</div>
	</div>
</div>
