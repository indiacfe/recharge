<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	/*...................validation and confirmation of update company account................................  */
	function CompanyConfirmation() {
		var cAmount = document.getElementById("companyAmount").value;
		var con = confirm("Are you sure want to update the company account By Rs."
				+ cAmount);
		if (con == true) {
			return true;
		} else {
			document.getElementById("companyAmount").focus();
			return false;
		}
	}
	function companyAmountValidation() {
		var companyAmount = document.getElementById("companyAmount").value;
		if (isNaN(companyAmount)) {
			alert("Please enter the number in amount");
			document.getElementById("companyAmount").focus();
			return false;
		} else {
			return true;
		}
	}
	function companyFormValidation() {
		return (companyAmountValidation() && CompanyConfirmation());
	}
	/* .........end the company update ........... */
	/* ..................distributor commission update................................ */

	function distributorConfirmation() {
		var amount = document.getElementById("distributorCommission").value;
		var distributorId = document.getElementById("distributorId").value;
		var con = confirm("Are you sure want to update the distributor commission as\n\nDistributor Id: "
				+ distributorId + "\n\nAmount: " + amount);
		if (con == true) {
			return true;
		} else {
			document.getElementById("distributorCommission").focus();
			return false;
		}
	}

	function distributorCommissionValidation() {
		var amount = document.getElementById("distributorCommission").value;
		if (isNaN(amount)) {
			alert("Please enter the number in amount");
			document.getElementById("distributorCommission").focus();
			return false;
		} else {
			return true;
		}
	}
	function distributorFormValidation() {
		return (distributorCommissionValidation() && distributorConfirmation());

	}
	/*.................end the distributor commission ..........................  */
	/* ...........start the operation commission................ */
	function operatorConfirmation() {
		var rechargeType = document.getElementById("rechargeType").value;
		var thirdPartyName = document.getElementById("thirdParty").value;
		var operator = document.getElementById("operator").value;
		var amount = document.getElementById("commissionAmount").value;

		var con = confirm("Are you sure want to update the operator commission as\n\nRecharge Type: "
				+ rechargeType
				+ "\n\nThird Party Name: "
				+ thirdPartyName
				+ "\n\nOperator: " + operator + "\n\nAmount:" + amount);
		if (con == true) {
			return true;
		} else {
			document.getElementById("commissionAmount").focus();
			return false;
		}
	}
	function operatorSelectTypeValidation() {
		var rechargeType = document.getElementById("rechargeType").value;
		var thirdPartyName = document.getElementById("thirdParty").value;
		var operator = document.getElementById("operator").value;

		if (rechargeType == -1) {
			alert("Pls select the recharge type");
			document.getElementById("rechargeType").focus();
			return false;
		} else if (thirdPartyName == -1) {
			alert("Pls select the third party name");
			document.getElementById("thirdParty").focus();
			return false;
		} else if (operator == -1) {
			alert("Pls select the operator");
			document.getElementById("operator").focus();
			return false;
		}
		return true;
	}
	function operatorAmountValidation() {
		var amount = document.getElementById("commissionAmount").value;
		if (isNaN(amount)) {
			alert("Pls enter the number in amount");
			document.getElementById("commissionAmount").focus();
			return false;
		} else {
			return true;
		}
	}
	function operatorFormValidation() {
		return (operatorSelectTypeValidation() && operatorAmountValidation() && operatorConfirmation());
	}
	/* ...............end the operator commission .............................. */
	/* ...............start the set third party service provider.......................... */
	function setThirdPartyconfirmation() {
		var rechargeType = document.getElementById("rechargeType1").value;
		var thirdPartyName = document.getElementById("thirdPartyName1").value;
		var operator = document.getElementById("operatorName1").value;
		var con = confirm("Are you sure want to set the third party service provider as\n\nRecharge Type: "
				+ rechargeType
				+ "\n\nThird Party Name: "
				+ thirdPartyName
				+ "\n\nOperator Name: " + operator);
		if (con == true) {
			return true;
		} else {
			document.getElementById("operatorName1").focus();
			return false;
		}
	}
	function setThirdPartyValidation() {
		var rechargeType = document.getElementById("rechargeType1").value;
		var thirdPartyName = document.getElementById("thirdPartyName1").value;
		var operator = document.getElementById("operatorName1").value;
		if (rechargeType == -1) {
			alert("pls select the recharge type");
			document.getElementById("rechargeType1").focus();
			return false;
		} else if (thirdPartyName == -1) {
			alert("pls select the third party name");
			document.getElementById("thirdPartyName1").focus();
			return false;
		} else if (operator == -1) {
			alert("pls select the operator name");
			document.getElementById("operatorName1").focus();
			return false;
		}
		return true;
	}
	function setThirdPartyFarmValidation() {
		return (setThirdPartyValidation() && setThirdPartyconfirmation());
	}
	/*.............end the third party service provider..........................  */
</script>

<div id="search_panel1">
	<h1>Update Company Account</h1>
	<form:form method="post" action="companyaccountupdateAdmin"
		onsubmit="return companyFormValidation();"
		modelAttribute="companyaccountAdmin">
		<div class="search_panel_box">
			<div class="form_row">
				<label>Enter the Amount</label>
				<form:input path="companyAmount" required="required"
					id="companyAmount" />
			</div>
		</div>
		<input type="submit" class="button" value="Update" />
	</form:form>
</div>

<div id="search_panel1">

	<h1>Set the commission for distributor</h1>
	<form:form method="post" action="distributoCommissionupdateAdmin"
		modelAttribute="distributoCommissionAdmin"
		onsubmit="return distributorFormValidation();">
		<div class="search_panel_box">
			<div class="form_row">
				<label>Distributor Id:</label>
				<form:input path="distributorId" required="required"
					id="distributorId" />
			</div>
			<div class="form_row">
				<label>Amount:</label>
				<form:input path="amount" required="required"
					id="distributorCommission" />
			</div>
		</div>
		<input type="submit" class="button" value="Update" />
	</form:form>
</div>
<div id="search_panel1">
	<h1>Set the Operator commission</h1>
	<form:form method="post" action="operatorCommisionupdateAdmin"
		modelAttribute="operatorCommisionAdmin"
		onsubmit="return operatorFormValidation();">
		<div class="search_panel_box">
			<div class="form_row">
				<label>Select Recharge Type:</label>
				<form:select path="rechargeType" id="rechargeType">
					<form:option value="-1">select</form:option>
					<form:options items="${rechargetype}" />
				</form:select>
			</div>
			<div class="form_row">
				<label>Third Party Name:</label>
				<form:select path="thirdPartyOperator" id="thirdParty">
					<form:option value="-1">select</form:option>
					<form:options items="${thirdpartyname}" />
				</form:select>
			</div>
			<div class="form_row">
				<label>Select Operators:</label>
				<form:select path="operator" id="operator">
					<form:option value="-1">select</form:option>
					<form:options items="${operatorname}" />
				</form:select>
			</div>
			<div class="form_row">
				<label>Commission Amount:</label>
				<form:input path="amount" required="required" id="commissionAmount" />
			</div>
		</div>
		<input type="submit" class="button" value="Update" />
	</form:form>
</div>
<div id="search_panel1">
	<h1>Set the Third party Service Provider</h1>
	<form:form method="post" action="updatethirdpartyserviceprovider"
		modelAttribute="thirdpartyServiceProviderUpdateDTO"
		onsubmit="return setThirdPartyFarmValidation();">
		<div class="search_panel_box">
			<div class="form_row">
				<label>Select Recharge Type:</label>
				<form:select path="rechargeType" id="rechargeType1">
					<form:option value="-1">select</form:option>
					<form:options items="${rechargetype}" />
				</form:select>
			</div>
			<div class="form_row">
				<label>Third Party Name:</label>
				<form:select path="thirdPartyName" id="thirdPartyName1">
					<form:option value="-1">select</form:option>
					<form:options items="${thirdpartyname}" />
				</form:select>
			</div>
			<div class="form_row">
				<label>Select Operators:</label>
				<form:select path="operatorName" id="operatorName1">
					<form:option value="-1">select</form:option>
					<form:options items="${operatorname}" />
				</form:select>
			</div>
		</div>
		<input type="submit" class="button" value="Update" />
	</form:form>
</div>