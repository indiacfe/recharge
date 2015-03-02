<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function contactVali() {

		var connection = document.getElementById("connectionid").value;
		var amt = document.getElementById("amount").value;
		if (isNaN(connection)) {
			alert("pls enter a id in Connection id");
			document.getElementById("connectionid").focus();
			return false;
		}

		if (isNaN(amt)) {
			alert("pls enter a Number in  Amount");
			document.getElementById("amount").focus();
			return false;
		}
		return true;

	}
	function confirmation() {

		var operator = document.getElementById("operator").value;
		var datacardNo = document.getElementById("connectionid").value;
		var amount = document.getElementById("amount").value;
		var con = confirm("Are you sure you want to recharge Datacard card as\n\nOperator Name: "
				+ operator
				+ "\n\nData Card No: "
				+ datacardNo
				+ "\n\nAmount: "
				+ amount);
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
	
	<h1>Datacard Recharge</h1>
	<h3>Your Current Balance is Rs.
		${franchiseeInfo.b2bCurrentBalance}</h3>
	<div style="color: red;">${Error}</div>
	<form:form method="post" action="datacardrechargedetail"
		onSubmit="return dthForm();" modelAttribute="datacardrecharge">
		<div class="search_panel_box">
		    <form:select path="rechargeType" id="rechargeType">
				<form:option value="DATACARD">DATACARD</form:option>
			</form:select>
			<div class="form_row">
				<label>Operator</label>
				<form:select path="operator" id="operator">
					<form:option value="Reliance NetConnect 3G">Reliance NetConnect 3G</form:option>
					<form:option value="Reliance NetConnect+">Reliance NetConnect+</form:option>
					<form:option value="Reliance NetConnect">Reliance NetConnect</form:option>
					<form:option value="MTS MBlaze">MTS MBlaze</form:option>
					<form:option value="MTS MBrowse">MTS MBrowse</form:option>
					<form:option value="Tata Photon+">Tata Photon+</form:option>
					<form:option value="Tata Photon Whiz">Tata Photon Whiz</form:option>
					<form:option value="Vodafone 3G">Vodafone 3G</form:option>
					<form:option value="Aircel Pocket Internet">Aircel Pocket Internet</form:option>
					<form:option value="BSNL">BSNL</form:option>
				</form:select>
			</div>
			<div class="form_row">
				<label>Data Card Number</label>
				<form:input class="inputfield" required="required"
					path="connectionid" id="connectionid"></form:input>
			</div>
			<div class="form_row">
				<label>Amount (Rs.)</label>
				<form:input class="inputfield" required="required" path="amount"
					id="amount"></form:input>
			</div>

		</div>

		<input type="submit" required="required" class="button"
			value="Recharge" />

	</form:form>

	<script type="text/javascript">
		function clearAmount() {
			document.getElementById("amount").value = "";
		}
		clearAmount();
	</script>
</div>





