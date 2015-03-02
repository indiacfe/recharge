<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function contactVali() {
		var connection = document.getElementById("connectionid").value;
		var amt = document.getElementById("amount").value;
		if (isNaN(connection)) {
			alert("pls enter a Number in Connection id.");
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
		var rechargeType = document.getElementById("rechargeType").value;
		var type = document.getElementById("type").value;
		var operator = document.getElementById("operator").value;
		var dthNo = document.getElementById("connectionid").value;
		var amount = document.getElementById("amount").value;
		var con = confirm("Are you sure you want to recharge DTH card as\n\nRechargeType:"
				+ rechargeType + "\n\nType:" + type+"\n\nOperator Name:"+operator+"\n\nDTH No.:"+dthNo+"\n\nAmount"+amount);
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

	<h1>DTH-TV Recharge</h1>
	<h3>Your Current Balance is Rs.
		${franchiseeInfo.b2bCurrentBalance}</h3>
	<div style="color: red;">${Error}</div>
	<form:form method="post" action="rechargedetaildth"
		onsubmit="return dthForm()" modelAttribute="dthtv">
		<div class="search_panel_box">
			<div class="form_row">
				<label>Recharge Type</label>
				<form:select path="rechargeType" id="rechargeType">
					<form:option value="DTH">DTH</form:option>

				</form:select>
			</div>
			<!-- div class="form_row">
				<label>Type</label>
				<form:select path="type" id="type">
					<form:option value="pinless">PinLess</form:option>
				</form:select>
			</div -->

			<div class="form_row">
				<label>Select Operator</label>
				<form:select path="operator" id="operator">
					<form:option value="DISH TV">DISH TV</form:option>
					<form:option value="RELIANCE DIGITAL TV">RELIANCE DIGITAL TV</form:option>
					<form:option value="SUN DIRECT">SUN DIRECT</form:option>
					<form:option value="VIDEOCON D2H">VIDEOCON D2H</form:option>
					<form:option value="AIRTEL DIGITAL TV">AIRTEL DIGITAL TV</form:option>
					<form:option value="TATA SKY">TATA SKY</form:option>

				</form:select>
			</div>
			<div class="form_row">
				<label>DTH-ID No.</label>
				<form:input class="inputfield" required="required"
					path="connectionid" id="connectionid"></form:input>
			</div>
			<div class="form_row">
				<label>Amount (Rs.)</label>
				<form:input class="inputfield" required="required" path="amount"
					id="amount" />
			</div>
			<input type="submit" name="submit" class="button" value="Recharge" />
		</div>


	</form:form>

	<script type="text/javascript">
		function clearAmount() {
			document.getElementById("amount").value = "";
		}
		clearAmount();
	</script>
</div>




