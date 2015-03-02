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
	function addTable() {
		document.getElementById('table').innerHTML;
		return false;
	}
</script>
<div id="search_panel1">
	<h1>Fund Transfer Report</h1>
	<form:form method="get" action="fundTransferReport"
		modelAttribute="fundTransferReport">
		<div class="search_panel_box">

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
		<input type="submit" class="button" value="View" />
	</form:form>
</div>
<div id="table">
	<c:if test="${not empty distBalanceTransferLog }">
		<table border="1" width="100%">
			<tr bgcolor="#3399FF">
				<td><strong>DATE/TIME</strong></td>
				<td><strong>TID</strong></td>
				<td><strong>TRANSACTION NAME</strong></td>
				<td><strong>TRANSACTION TYPE</strong></td>
				<td><strong>AMOUNT</strong></td>
				<td><strong>BALANCE</strong></td>
			</tr>
			<c:forEach items="${distBalanceTransferLog}" var="reportItem"
				varStatus="status">
				<tr>
					<td>${reportItem.createdAt}</td>
					<td>${reportItem.transactionId}</td>
					<td>${reportItem.displayTransactionName}</td>
					<td>${reportItem.transferType}</td>
					<td>Rs. ${reportItem.transferAmount}</td>
					<td>AdUnit Bal: Rs. ${reportItem.newB2bAdUnitBal} - B2b Bal: Rs. ${reportItem.newB2bCurrBal}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
