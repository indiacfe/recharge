<%@page import="java.util.UUID"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function getCommission() {
		var ammount = document.getElementById("amount").value;
		var operator = document.getElementById("seloperator").value;
		var paymentType = document.getElementById("paymentType").value;
		if (paymentType != -1 && operator != -1) {
			$
					.ajax({

						type : "GET",
						url : "franchisee/getretailercommission",
						data : "amount=" + ammount + "&operatorName="
								+ operator + "&paymentType=" + paymentType
								+ "&serviceType=LANDLINE",
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

	function loadFieldsDetails() {
		var opval = $('#seloperator').val();
		$('#connectionlabel').empty();
		$('#connectionfield').empty();
		$('#canumber').empty();
		$('#accountlable').empty();
		$('#accountfield').empty();
		$('#circlelabel').empty();
		$('#circlefield').empty();
		if (opval == 'AIRTEL All India') {
			$('#connectionlabel')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">Connection Number</label>');
			$('#connectionfield')
					.append(
							'<input required="required" name="stdCode" style="border-color:#CC0000;" size="6" placeholder="STD CODE" class="recharge-Select-std"></input>-<input required="required" name="connectionid" id="connectionid" class="recharge-Select-Min" style="border-color:#CC0000;" placeholder="PHONE NUMBER"></input>');
		} else if (opval == 'BSNL All India') {
			$('#connectionlabel')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">Connection Number</label>');

			$('#connectionfield')
					.append(
							'<input required="required" name="stdCode" style="border-color:#CC0000;" placeholder="STD CODE" class="recharge-Select-std"></input>-<input required="required" name="connectionid" id="connectionid"  style="border-color:#CC0000;" placeholder="PHONE NUMBER" class="recharge-Select-Min"></input>');
			$('#utcircle').append(
					'<option value="AS">Assam Telecom Circle</option>');
			$('#accountlable')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">Account Number</label>');

			$('#accountfield')
					.append(
							'<input required="required" name="accountNumber" id="acountnumber" class="recharge-Select" style="border-color:#CC0000;" placeholder="Account Number"></input>');

			$('#circlelabel')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">Circle Name</label>');
			$('#circlefield')
					.append(
							'<select  name="circleName" class="recharge-Select"	style="border-color:#CC0000;" id="utcircle"><option value="0">Select Circle</option></select>');

			$('#utcircle')
					.append(
							'<option value="BH">Bihar and Jharkhand Telecom Circle</option><option value="KL">Kolkata Metro Telecom Circle (includes parts of Howrah, Hooghly, North and South 24 Parganas and Nadia Districts)</option><option value="OR">Odisha Telecom Circle</option><option value="NE">North East Telecom Circle (includes Arunachal Pradesh, Meghalaya, Mizoram, Nagaland, Manipur and Tripura) - NESA</option><option value="WB">West Bengal Telecom Circle (includes Andaman and Nicobar Islands, Sikkim excludes Calcutta Telecom District)</option><option value="MH">Maharashtra Telecom Circle (includes Goa but excludes Mumbai, Navi Mumbai and Kalyan)</option><option value="MP">Madhya Pradesh and Chhattisgarh Telecom Circle - MPCG</option><option value="MU">Mumbai Metro Telecom Circle (includes Navi Mumbai and Kalyan)</option><option value="GJ">Gujarat Telecom Circle (includes Daman and Diu, Dadra and Nagar Haveli)</option><option value="P">Punjab Telecom Circle (includes Chandigarh and Panchkula)</option><option value="RJ">Rajasthan Telecom Circle</option><option value="UPE">Uttar Pradesh (East) Telecom Circle</option><option value="UPW">Uttar Pradesh (West) and Uttarakhand Telecom Circle (excludes Ghaziabad and Noida)</option><option value="HR">Haryana Telecom Circle (excludes Faridabad, Gurgaon and Panchkula)</option><option value="HP">Himachal Pradesh Telecom Circle</option><option value="JK">Jammu and Kashmir Telecom Circle</option><option value="DL">Delhi Metro Telecom Circle (includes NCR, Faridabad, Ghaziabad, Gurgaon and Noida)</option><option value="AP">Andhra Pradesh Telecom Circle</option><option value="CN">Chennai Metro Telecom Circle (includes Chennai, MEPZ and Mahabalipuram)</option><option value="KR">Kerala Telecom Circle (includes Lakshadweep)</option><option value="KA">Karnataka Telecom Circle</option><option value="TN">Tamil Nadu Telecom Circle (excludes CH Chennai, MEPZ, Mahabalipuram and Minjur and includes Puducherry except Yanam and Mahé)</option>');

		} else if (opval == 'MTNL All India') {
			$('#connectionlabel')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">Connection Number</label>');
			$('#connectionfield')
					.append(
							'<input required="required" name="connectionid" id="connectionid" class="recharge-Select" style="border-color:#CC0000;" placeholder="PHONE NUMBER"></input>');
			$('#accountlable')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">CA Number</label>');
			$('#accountfield')
					.append(
							'<input required="required" name ="canumber" id="canumber" class="recharge-Select" style="border-color:#CC0000;" placeholder="CA NUMBER"></input>');

		} else if (opval == 'Reliance All India') {
			$('#connectionlabel')
					.append(
							'<label style="font-size: 20px; color: #CC0000;">Connection Number</label>');
			$('#connectionfield')
					.append(
							'<input required="required"  name="connectionid" id="connectionid" class="recharge-Select" style="border-color:#CC0000;"></input>');

		}

	}

	function contactVali() {
		var connection = document.getElementById("connectionid").value;
		var amt = document.getElementById("amount").value;
		if (isNaN(connection)) {
			alert("pls enter a Number in Landline number");
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
		var type = document.getElementById("type").value;
		var operator = document.getElementById("operator").value;
		var connectionid = document.getElementById("connectionid").value;
		var amount = document.getElementById("amount").value;
		var con = confirm("Are you sure you want to pay bill as\n\nBill Type:"
				+ rechargeType + "\n\nType:" + type + "\n\nOperator Name:"
				+ operator + "\n\nLandline No.:" + connectionid + "\n\nAmount"
				+ amount);
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
	function currentBalanceForUse(value) {
		if ("CURRENT" == value) {
			$("#b2bBalanceId").show();
			$("#adUnitBalanceId").hide();
		} else if ("ADUNIT" == value) {
			$("#b2bBalanceId").hide();
			$("#adUnitBalanceId").show();
		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">Land Line Bill Payment</label>
		</h2>
		<div class="block ">
			<c:if test="${not empty Error}">
				<div class="message info">
					<h4>Information</h4>
					<h5>${Error}.</h5>
				</div>
			</c:if>
			<p style="font-size: 19px;">Note: Please verify the number before
				recharging as mobile operators does not entertain for reversal of
				wrong recharges</p>
			<form:form method="POST" action="landlinebilldetail"
				commandName="landline" onSubmit="return contactVali();">
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
								style="font-size: 20px; color: #CC0000;">Bill Payment
									Type</label></td>
							<td class="col2"><form:select path="rechargeType"
									id="rechargeType" class="recharge-Select"
									style="border-color:#CC0000;">
									<form:option value="LANDLINE">LANDLINE</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td class="col1"><label
								style="font-size: 20px; color: #CC0000;">Operator</label></td>
							<td><form:select path="operator" class="recharge-Select"
									style="border-color:#CC0000;" id="seloperator"
									onchange="loadFieldsDetails(),getCommission();">
									<form:option value="-1">Select</form:option>
									<form:option value="AIRTEL All India">AIRTEL All India</form:option>
									<form:option value="BSNL All India">BSNL All India</form:option>
									<form:option value="MTNL All India">MTNL All India</form:option>
									<form:option value="Reliance All India">Reliance All India</form:option>
								</form:select></td>
						</tr>
						<tr>

							<td>
								<div id="connectionlabel"></div>
							</td>



							<td>
								<div id="connectionfield"></div>



							</td>


						</tr>

						<tr>

							<td>
								<div id="accountlable"></div>
							</td>



							<td>
								<div id="accountfield"></div>



							</td>


						</tr>
						<tr>

							<td>
								<div id="circlelabel"></div>
							</td>



							<td>
								<div id="circlefield"></div>



							</td>


						</tr>






						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Amount
									(Rs.)</label></td>
							<td><form:input required="required" path="amount"
									id="amount" class="recharge-Select"
									style="border-color:#CC0000;" placeholder="Amount"></form:input></td>
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