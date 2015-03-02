<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
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
		var user = document.getElementById("customerName").value;
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
		} else if (user == -1) {
			alert("pls select the Customer");
			document.getElementById("customerName").focus();
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
</script>

<div class="grid_10">


	<div class="box round first">
		<h2>Set the Customer Commission</h2>
		<div class="block ">
			<div class="block ">
				<c:if test="${not empty message}">
					<div class="message info">
						<h4>Information</h4>
						<h5>${message}.</h5>
					</div>
				</c:if>

				<form:form method="post" action="customercommission"
					modelAttribute="customercommisionAdmin"
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
								<td class="col2"><form:select
										path="thirdpartyServiceProvider" id="thirdParty">
										<form:option value="-1">select</form:option>
										<form:options items="${thirdpartyname}" />
									</form:select></td>
							</tr>

							<tr>
								<td class="col1"><label>Select Operators:</label></td>
								<td class="col2"><form:select path="operatorName"
										id="operator">
										<form:option value="-1">select</form:option>
										<form:options items="${operatorname}" />
									</form:select></td>
							</tr>

							<tr>
								<td class="col1"><label>Select Customer:</label></td>
								<td class="col2"><form:select path="userId"
										id="customerName">
										<form:option value="-1">select</form:option>
										<c:forEach var="customer" items="${customers}">
											<option value="${customer.userId}">${customer.firmName}</option>
										</c:forEach>

									</form:select></td>
							</tr>




							<tr>
								<td class="col1"><label>Commission Amount:</label></td>
								<td class="col2"><form:input path="customerCommission"
										required="required" id="customerCommission" /></td>
							</tr>


						</tbody>
					</table>
					<input type="submit" class="button" value="Update" />
				</form:form>
			</div>
		</div>

	</div>