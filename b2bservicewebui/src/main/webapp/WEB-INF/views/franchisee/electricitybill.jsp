<%@page import="java.util.UUID"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function getCommission() {
		var ammount = document.getElementById("amount").value;
		var operator = document.getElementById("operator").value;
		var paymentType = document.getElementById("paymentType").value;

		if (paymentType != -1 && operator != -1) {
			$
					.ajax({

						type : "GET",
						url : "franchisee/getretailercommission",
						data : "amount=" + ammount + "&operatorName="
								+ operator + "&paymentType=" + paymentType
								+ "&serviceType=ELECTRICITY",
						success : function(response) {
							$('#addcomm').empty();
							$('#addcomm').append(response);
							$("#commissionId").show();
						},
						error : function(e) {
							$("#commissionId").hide();
							alert("Service charge not define. Please contact to Admin.");

						},

					});
		}

	}
	function currentBalanceForUse(value) {
		if ("CURRENT" == value) {
			$("#b2bBalanceId").show();
			$("#adUnitBalanceId").hide();
		} else if ("ADUNIT" == value) {
			$("#b2bBalanceId").hide();
			$("#adUnitBalanceId").show();
		}

	}
	function getcycleNumber() {
		var opval = $('#operator').val();
		$('#cyclenumberl').empty();
		$('#cyclenumberf').empty();

		if (opval == 'Reliance Energy Limited - MUMBAI') {
			$('#cyclenumberl')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">Cycle Number </label>');
			$('#cyclenumberf')
					.append(
							'<input required="required" name="cycleNumber" style="border-color:#CC0000;" size="20" placeholder="Cycle Number" class="recharge-Select"></input>');
		}
	}

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
				+ rechargeType + "\n\nOperator Name:" + operator
				+ "\n\nConnection no.:" + connectionid + "\n\nAmount" + amount);
		if (con == true) {
			return true;
		} else {
			return false;
		}
	}

	function confirmPaymentType() {
		var payType = document.getElementById("paymentType").value;
		var commType = document.getElementById("commissionType").value;
		if (payType == -1 || commType == -1) {
			return false;
		} else {
			return true;
		}

	}
	function dthForm() {
		return (contactVali() && confirmation() && confirmPaymentType());
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">Electricity Bill Payment</label>
		</h2>
		<div class="block ">
			<c:if test="${not empty Error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${Error}.</p>
				</div>
			</c:if>
			<p style="font-size: 19px;">Note: Please verify the number before
				recharging as mobile operators does not entertain for reversal of
				wrong recharges</p>
			<form:form method="POST" action="electricitybillsubmit"
				onSubmit="return contactVali();"
				modelAttribute="electricitybillbean">
				<table class="form">
					<%
						Long uuid = UUID.randomUUID().getMostSignificantBits();
							session.setAttribute("suuid", uuid);
					%>
					<tbody>
						<tr id="b2bBalanceId" style="display: show;">
							<td class="col1" style="width: 39%"><label
								style="font-size: 20px; color: #CC0000;">B2B Balance</label></td>
							<td class="col2"><label
								style="font-size: 20px; color: #CC0000;">Rs.<fmt:formatNumber
										type="number" value="${franchiseeInfo.b2bCurrentBalance}"
										maxFractionDigits="2"></fmt:formatNumber></label></td>
						</tr>
						<tr id="adUnitBalanceId" style="display: none;">
							<td class="col1" style="width: 39%"><label
								style="font-size: 20px; color: #CC0000;">Ad-Unit Balance</label></td>
							<td class="col2"><label
								style="font-size: 20px; color: #CC0000;">Rs.<fmt:formatNumber
										type="number"
										value="${franchiseeInfo.b2bCurrentAdUnitBalance}"
										maxFractionDigits="2"></fmt:formatNumber></label></td>
						</tr>
						<tr>
							<td class="col1"><label
								style="font-size: 20px; color: #CC0000;">Payment Type</label></td>
							<td class="col2"><form:select path="rechargeType"
									id="rechargeType" class="recharge-Select"
									style="border-color:#CC0000;">
									<form:option value="ELECTRICITY">ELECTRICITY</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Operator</label></td>
							<td><form:select path="operator" class="recharge-Select"
									style="border-color:#CC0000;"
									onchange="getcycleNumber(),getCommission();" id="operator">
									<form:option value="-1">Select</form:option>
									<form:option value="BSES Rajdhani Power Limited - DELHI">BSES Rajdhani Power Limited - DELHI</form:option>
									<form:option value="BSES Yamuna Power Limited - DELHI">BSES Yamuna Power Limited - DELHI</form:option>
									<form:option value="Reliance Energy Limited - MUMBAI">Reliance Energy Limited - MUMBAI</form:option>
									<form:option
										value="Tata Power Delhi Distribution Limited - DELHI">Tata Power Delhi Distribution Limited - DELHI</form:option>
									<form:option value="North Delhi Power Limited">North Delhi Power Limited</form:option>
									<form:option value="DHBVNBill">Haryana Power Limited</form:option>

								</form:select></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Connection
									Number (CA)/KNumber</label></td>
							<td><form:input required="required" path="connectionid"
									id="connectionid" class="recharge-Select"
									style="border-color:#CC0000;"></form:input></td>
						</tr>



						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Mobile
									Number</label></td>
							<td><form:input required="required" path="mobileNo"
									id="mobileNo" class="recharge-Select"
									style="border-color:#CC0000;"></form:input></td>
						</tr>

						<tr>
							<td>

								<div id="cyclenumberl"></div>
							</td>
							<td><div id="cyclenumberf"></div></td>
						</tr>


						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Amount
									(Rs.)</label></td>
							<td><form:input required="required" path="amount"
									id="amount" class="recharge-Select"
									style="border-color:#CC0000;"></form:input></td>
						</tr>

						<!-- It is for payment type and commission type   start here -->
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Payment
									Type </label></td>
							<td><form:select path="paymentType" class="recharge-Select"
									style="border-color:#CC0000;" id="paymentType"
									onchange="currentBalanceForUse(this.value),getCommission()">
									<form:option value="-1">Select</form:option>
									<form:option value="CURRENT">Current Balance </form:option>
									<form:option value="ADUNIT">Ad Unit Balance</form:option>
								</form:select></td>
						</tr>

						<tr id="commissionId" style="display: none;">
							<td><label style="font-size: 20px; color: #CC0000;">Service
									Charge </label></td>
							<td style="font-size: 20px; color: #CC0000;" id="addcomm"></td>
						</tr>

						<!-- It is for payment type and commission type   end here -->



						<tr>
							<td></td>
							<td><input type="submit" name="submit" class="btn btn-large"
								value="Pay Bill" /></td>
						</tr>

					</tbody>
				</table>

			</form:form>
			<p style="font-size: 19px;">Note :- The tariff plans are subject
				to change by the service provider.</p>
			<br>
			<p style="font-size: 19px;">Plan may have been for information
				purpose only. Site does not take any responsibility in case these
				have been changed by the service provider.</p>
		</div>
	</div>
</div>