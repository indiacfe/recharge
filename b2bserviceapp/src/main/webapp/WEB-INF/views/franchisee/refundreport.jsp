<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<script type="text/javascript">
	$(function() {
		$("#fromdate").datepicker();
	});
	$(function() {
		$("#todate").datepicker();
	});
</script>
<div id="search_panel1">
	<h1>Refund Report</h1>
	<form:form method="get" action="refundedlist" onSubmit="return contactVali();"
		modelAttribute="refund">
		<div class="search_panel_box">

			<div class="form_row">
				<label>Service Type</label>
				<form:select path="serviceType">
					<form:option value="-1">Select</form:option>
					<form:option value="MOBILE_RECHARGE">Mobile Recharge</form:option>
					<form:option value="DTH_RECHARGE">DTH Recharge</form:option>
					<form:option value="DATACARD_RECHARGE">Datacard Recharge</form:option>
					<form:option value="POSTPAID_BILL">Postpaid Mobile BillPay</form:option>
					<form:option value="LANDLINE">LandLine BillPay</form:option>
					<%--  <form:option value="#">Electricity BillPay</form:option>
               	<form:option value="induranceBillpay">Insurance BillPay</form:option>
                <form:option value="gasBillPay">Gas BillPay</form:option> --%>
				</form:select>
			</div>

			<div class="form_row">
				<label>From Date</label>
				<form:input class="inputfield" required="required" path="fromDate"
					type="text" id="fromdate"></form:input>
			</div>
			<div class="form_row">
				<label>To Date</label>
				<form:input class="inputfield" required="required" path="toDate"
					type="text" id="todate"></form:input>
			</div>

		</div>

		<input type="submit" required="required" class="button" value="View" />

	</form:form>
</div>
<div id="table">
	<c:if test="${not empty detailForRefund }">
		<table border="1" width="100%">
			<tr bgcolor="#3399FF">
				<td><strong>SN.</strong></td>
				<td><strong>TID</strong></td>
				<td><strong>CONNECTION/MOBILE No.</strong></td>
				<td><strong>OPERATOR</strong></td>
				<td><strong>AMOUNT</strong></td>
				<td><strong>REFUND AMOUNT</strong></td>
				<td><strong>CREDIT AMOUNT</strong></td>
				<td><strong>STATUS</strong></td>
			</tr>
			<c:forEach items="${detailForRefund}" var="requestItem"
				varStatus="status">
				<tr>

					<td>${status.count}</td>
					<td>${requestItem.transid}</td>
					<td>${requestItem.connectionid}${requestItem.mobileNo}</td>
					<td>${requestItem.operator}</td>
					<td>${requestItem.amount}</td>
					<td>${requestItem.refundAmount}</td>
					<td>${requestItem.creditAmountFranchisee}</td>
					<td><strong>${requestItem.status}</strong></td>	
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>





