<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script type="text/javascript">
	$(function() {
		$("#fromdate").datepicker();
	});
	$(function() {
		$("#todate").datepicker();
	});
	function addTable() {
		document.getElementById('table').innerHTML;
		return false;
	}
</script>
<div id="search_panel1">
	<h1>Recharge Report</h1>
	<form method="get" action="rechargereport">
		<div class="search_panel_box">
			<div class="form_row">
				<label>From Date</label>
				<input class="inputfield" required="required" name="fromDate"
					type="text" id="fromdate">
			</div>
			<div class="form_row">
				<label>To Date</label>
				<input class="inputfield" required="required" name="toDate"
					type="text" id="todate"></input>
			</div>
			<div class="form_row">
				<label>Type of Recharge:</label>
				<select name="sel" id="sel">
					<option value="goprocess">GoProcess</option>
					<option value="instantpay">Instantpay</option>
					<option value="payworld">PayWorld</option>
				</select>
			</div>
			<div class="form_row">
				<label>Status</label>
				<select name="status" id="status">
					<option value="SUCCESS">Success</option>
					<option value="FAILED">Failed</option>
				</select>
			</div>
			
		</div>

		<input type="submit" class="button" value="submit" />
	</form>
	</div>
	
	<div id="table">
	<c:if test="${not empty rechargeDetail}">
	<h3>Report for Third Party Service Provider: ${sel} And  Status: ${status}</h3>
		<table border="1">
			<tr bgcolor="#3399FF">
				<td><strong>SN.</strong></td>
				<td><strong>Retailer ID</strong></td>
				<td><strong>Date/Time</strong></td>
				<td><strong>Service Provider</strong></td>
				<td><strong>Mobile Number</strong></td>
				<td><strong>Connection No.</strong></td>				
				<td><strong>Operator</strong></td>
				<td><strong>Transaction Type</strong></td>
				<td><strong>Debit</strong></td>
				<td><strong>Credit</strong></td>				
			</tr>
			<c:forEach items="${rechargeDetail}" var="reportItem" varStatus="status">
				<tr>
					<td>${status.count}.</td>
					<td>${reportItem.retailerId}</td>
					<td>${reportItem.thirdPartyTransDateTime}</td>
					<td>${reportItem.thirdPartyServiceProvider}</td>
					<td>${reportItem.mobileNo}</td>
					<td>${reportItem.connectionid}</td>
					<td>${reportItem.operator}</td>
					<td>${reportItem.transactionName}</td>
					<td>${reportItem.amount}</td>
					<td>${reportItem.creditAmountFranchisee}</td>					
				</tr>			
			</c:forEach>
			<tr bgcolor="#3399FF">
			<td colspan="8"><h2>Total</h2></td>
			<td>${totalDebit}</td>
			<td>${totalCredit}</td>
			</tr>
		</table>
	</c:if>
