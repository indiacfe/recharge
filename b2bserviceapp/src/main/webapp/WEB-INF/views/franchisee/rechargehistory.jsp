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
	<h1>Recharge History</h1>
	<form:form method="post" action="rechargehistoryretrieve"
		modelAttribute="accountstatement">
		<div class="search_panel_box">

			<div class="form_row">
				<label>Connection/Mobile No.</label>
				<form:input class="inputfield" path="number" type="text"
					id=""></form:input>
			</div>
			<table></table>
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

		<input type="submit"  class="button" value="View"/>
	</form:form>
</div>
<div id="table">
<c:if test="${not empty reportList }">
<table border="1" width="100%"><tr bgcolor="#3399FF"><td><strong>DATE/TIME</strong></td><td><strong>TID</strong></td><td><strong>AMOUNT</strong></td><td><strong>OPERATOR</strong></td><td><strong>MOBILE NO</strong></td><td><strong>CONNCTION NO</strong></td><td><strong>STATUS</strong></td><td><strong>COMMISSION</strong></td></tr>
 <c:forEach items="${reportList}" var="reportItem" varStatus="status"><tr><td>${reportItem.createdAt}</td><td>${reportItem.transactionId}</td><td>Rs. ${reportItem.displayAmount}</td><td><strong>${reportItem.operator}</strong></td><td>${reportItem.mobileNo}</td><td>${reportItem.connectionid}</td><td>${reportItem.status}</td><td>Rs. ${reportItem.displayCreditAmountFranchisee}</td></tr>
 </c:forEach>
</table>
 </c:if>
 </div>
  