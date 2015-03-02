
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
function exeldoc() {
	
	var thirdparty1 = document.getElementById("fromDate").value;
	
	document.getElementById("fDate").value = thirdparty1;
	
	var thirdparty2 = document.getElementById("toDate").value;
	
	document.getElementById("tDate").value = thirdparty2;

	var getexel = document.getElementById("distfundtrans");
	if (document.getElementById("fDate").value != ''
			&& document.getElementById("tDate").value != '') {
		
		getexel.submit();

	}

}
<!--
$(document).ready(function() {
	$('#example').dataTable( {
		"bRetrieve":true,
		"sPaginationType": "full_numbers",
		"aaSorting": [[ 0, "desc" ]]
	} );
} );
//-->
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Historical Fund Transfer Report</h2>
		<div class="block ">
			<form:form method="get" action="historicalfundTransferReport"
				modelAttribute="fundTransferReport" onsubmit="return dateRestriction();">
				<table class="form">
				<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
					</c:if>h
					<tbody>
						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="fromDate" id="fromDate"></form:input></td>
						</tr>

						<tr>
							<td><label>To Date</label></td>
							<td><form:input class="inputfield" required="required"
									path="toDate"  id="toDate"></form:input></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="View" />
			</form:form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty distBalanceTransferLog }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>Date/Time</th>
									<th>Tid</th>
									<th>Transaction Name</th>
									<th>Transaction Type</th>
									<th>Amount</th>
									<th>Balance</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${distBalanceTransferLog}" var="reportItem"
									varStatus="status">
									<tr class="odd gradeX">

										<td class="center">${reportItem.createdAt}</td>
										<td class="center">${reportItem.transactionId}</td>
										<td class="center">${reportItem.transferTo}</td>
										<td class="center">${reportItem.transferType}</td>
										<td class="center">Rs. ${reportItem.transferAmount}</td>
										<td class="center">AdUnit Bal: Rs.
											${reportItem.newB2bAdUnitBal} - B2b Bal: Rs.
											${reportItem.newB2bCurrBal}</td>

									</tr>

								</c:forEach>
							</tbody>

						</table>
						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
                    <form:form method="get" action="DistFundTransferReport"
				modelAttribute="fundTransferReport" id="distfundtrans">
				<form:hidden id="fDate" path="fromDate" />
				<form:hidden id="tDate" path="toDate" />
				</form:form>

				</div>
			</div>
		</div>
	</div>
</div>
