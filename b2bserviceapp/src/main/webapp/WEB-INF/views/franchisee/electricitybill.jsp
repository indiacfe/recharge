<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function contactVali() {
		var connection = document.getElementById("connectionid").value;
		var amt = document.getElementById("amount").value;
		if (isNaN(connection)) {
			alert("pls enter a Number in Connection number");
			document.getElementById("connectionid").focus();
			return false;
		}
		if (isNaN(amt)) {
			alert("pls enter a Number in Amount");
			document.getElementById("amount").focus();
			return false;
		}
		return true;
	}
	function confirmation() {
		var rechargeType = document.getElementById("rechargeType").value;
		var operator = document.getElementById("operator").value;
		var connectionid = document.getElementById("connectionid").value;
		var amount = document.getElementById("amount").value;
		var con = confirm("Are you sure you want to pay bill as\n\nBill Type:"
				+ rechargeType + "\n\nOperator Name:"+operator+"\n\nConnection no.:"+connectionid+"\n\nAmount"+amount);
		if (con == true) {
			return true;
		} else {
			return false;
		}
	}
	function dthForm() {
		return (contactVali() && confirmation());
	}
</script>

<div id="search_panel1">
	<h1>Electricity Bill Payment</h1>
	<div style="color: red; left-padding: 80px;">${Error}</div>
	<br />
	<div class="search_panel_box">
	<form:form method="POST" action="electricitybillsubmit" onSubmit="return contactVali();"
		modelAttribute="electricitybill">

			<div class="form_row">
				<label>Electricity Bill Payment Type</label>
				<form:select path="rechargeType" id="rechargeType">
					<form:option value="ELECTRICITY">ELECTRICITY</form:option>
				</form:select>
			</div>
			<div class="form_row">
				<label>Operator</label>
				<form:select path="operator">
					<form:option value="-1">Select</form:option>
					<form:option value="BSES Rajdhani Power Limited - DELHI">BSES Rajdhani Power Limited - DELHI</form:option>
					<form:option value="BSES Yamuna Power Limited - DELHI">BSES Yamuna Power Limited - DELHI</form:option>
					<form:option value="Reliance Energy Limited - MUMBAI">Reliance Energy Limited - MUMBAI</form:option>
					<form:option value="Tata Power Delhi Distribution Limited - DELHI">Tata Power Delhi Distribution Limited - DELHI</form:option>
				</form:select>
			</div>
			<div class="form_row">
				<label>Electricity Connection Number (CA)</label>
				<form:input class="inputfield" required="required"
					path="connectionid" id="connectionid"></form:input>
			</div>
			<div class="form_row">
				<label>Amount (Rs.)</label>
				<form:input class="inputfield" required="required" path="amount"
					id="amount"></form:input>
			</div>
			<input type="submit" name="submit" class="button" value="Pay Bill" />
	</form:form>
</div>
<script type="text/javascript">
		function clearAmount() {
			document.getElementById("amount").value = "";
		}
		clearAmount();
	</script>

</div>