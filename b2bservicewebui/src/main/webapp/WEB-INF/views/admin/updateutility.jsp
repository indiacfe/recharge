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
        
		var commission=document.getElementById("commissionType").value;
        var deduction=document.getElementById("deductionType").value;
		
		var con = confirm("Are you sure want to update the operator commission as\n\nRecharge Type: "
				+ rechargeType
				+ "\n\nThird Party Name: "
				+ thirdPartyName
				+ "\n\nCommission Type: "
				+ commission
				+ "\n\nDeduction Type: "
				+ deduction
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
        var commission=document.getElementById("commissionType").value;
        var deduction=document.getElementById("deductionType").value;
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
		}else if (commission == -1) {
			alert("Pls select the commission");
			document.getElementById("commissionType").focus();
			return false;
		}else if (deduction == -1) {
			alert("Pls select the type of deduction");
			document.getElementById("deductionType").focus();
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

<div class="grid_10">
	<div class="box round first">
		<h2>Update Company Account</h2>
		<div class="block ">

			<form:form method="post" action="companyaccountupdateAdmin"
				onsubmit="return companyFormValidation();"
				modelAttribute="companyaccountAdmin">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Enter the Amount:</label></td>
							<td class="col2"><form:input path="companyAmount"
									required="required" id="companyAmount" /></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Update" />
			</form:form>
		</div>
	</div>
	<div class="box round first">
		<h2>Set the Commission for Distributor</h2>
		<div class="block ">

			<form:form method="post" action="distributoCommissionupdateAdmin"
				modelAttribute="distributoCommissionAdmin"
				onsubmit="return distributorFormValidation();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Distributor ID:</label></td>
							<td class="col2"><form:input path="distributorId"
									required="required" id="distributorId" /></td>
						</tr>

						<tr>
							<td class="col1"><label>Amount:</label></td>
							<td class="col2"><form:input path="amount"
									required="required" id="distributorCommission" /></td>
						</tr>




					</tbody>
				</table>
				<input type="submit" class="button" value="Update" />
			</form:form>
		</div>
	</div>
	<div class="box round first">
		<h2>Set the Operator Commission</h2>
		<div class="block ">

			<form:form method="post" action="operatorCommisionupdateAdmin"
				modelAttribute="operatorCommisionAdmin"
				onsubmit="return operatorFormValidation();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Select Recharge Type:</label></td>
							<td class="col2"><form:select path="rechargeType"
									id="rechargeType">
									<form:option value="-1">select</form:option>
									<form:options items="${rechargetype}" />
								</form:select></td>
						</tr>

						<tr>
							<td class="col1"><label>Third Party Name:</label></td>
							<td class="col2"><form:select path="thirdPartyOperator"
									id="thirdParty">
									<form:option value="-1">select</form:option>
									<form:options items="${thirdpartyname}" />
								</form:select></td>
						</tr>

						<tr>
							<td class="col1"><label>Select Operators:</label></td>
							<td class="col2"><form:select path="operator" id="operator">
									<form:option value="-1">select</form:option>
									<form:options items="${operatorname}" />
								</form:select></td>
						</tr>
						<tr>
							<td class="col1"><label>Type of Commission:</label></td>
							<td class="col2"><form:select path="commissionType" id="commissionType">
							<form:option value="-1">select</form:option>
									<form:option value="percentage">Percentage</form:option>
									<form:option value="flat">Flat</form:option>
								</form:select></td>
						</tr>
						
						<tr>
							<td class="col1"><label>Type of Deduction:</label></td>
							<td class="col2"><form:select path="deductionType" id="deductionType">
							<form:option value="-1">select</form:option>
									<form:option value="credit">Credit</form:option>
									<form:option value="debit">Debit</form:option>
								</form:select></td>
						</tr>
						
						
						<tr>
							<td class="col1"><label>Commission Amount:</label></td>
							<td class="col2"><form:input path="amount"
									required="required" id="commissionAmount" /></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="Update" />
			</form:form>
		</div>
	</div>
	<div class="box round first">
		<h2>Set the Third party Service Provider</h2>
		<div class="block ">

			<form:form method="post" action="updatethirdpartyserviceprovider"
				modelAttribute="thirdpartyServiceProviderUpdateDTO"
				onsubmit="return setThirdPartyFarmValidation();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Select Recharge Type:</label></td>
							<td class="col2"><form:select path="rechargeType"
									id="rechargeType1">
									<form:option value="-1">select</form:option>
									<form:options items="${rechargetype}" />
								</form:select></td>
						</tr>

						<tr>
							<td class="col1"><label>Third Party Name:</label></td>
							<td class="col2"><form:select path="thirdPartyName"
									id="thirdPartyName1">
									<form:option value="-1">select</form:option>
									<form:options items="${thirdpartyname}" />
								</form:select></td>
						</tr>

						<tr>
							<td class="col1"><label>Select Operators:</label></td>
							<td class="col2"><form:select path="operatorName"
									id="operatorName1">
									<form:option value="-1">select</form:option>
									<form:options items="${operatorname}" />
								</form:select></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="Update" />
			</form:form>
		</div>
	</div>
</div>
