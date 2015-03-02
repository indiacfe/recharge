<%@page import="java.util.UUID"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function dthForm() {

		return (contactVali() && confirmation());
	}
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
		var operator = document.getElementById("operator").value;
		var dthNo = document.getElementById("connectionid").value;
		var amount = document.getElementById("amount").value;
		var con = confirm("Are you sure you want to recharge DTH card as\n\nRechargeType:"
				+ rechargeType
				+ "\n\nOperator Name:"
				+ operator
				+ "\n\nDTH No.:" + dthNo + "\n\nAmount " + amount);
		if (con == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">DTH-TV Recharge</label>
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
			<form:form method="post" action="rechargedetaildth"
				onsubmit="return dthForm();" modelAttribute="dthtv">
				<table class="form">
					<%
						Long uuid = UUID.randomUUID().getMostSignificantBits();
							session.setAttribute("suuid", uuid);
					%>
					<tbody>
						<tr>
							<td class="col1" style="width: 39%"><label
								style="font-size: 20px; color: #CC0000;">B2B Balance</label></td>
							<td class="col2"><label
								style="font-size: 20px; color: #CC0000;">Rs.<fmt:formatNumber
										type="number" value="${franchiseeInfo.b2bCurrentBalance}"
										maxFractionDigits="2"></fmt:formatNumber></label></td>
						</tr>
						<tr>
							<td class="col1"><label
								style="font-size: 20px; color: #CC0000;">Recharge Type</label></td>
							<td class="col2"><form:select path="rechargeType"
									id="rechargeType" class="recharge-Select"
									style="border-color:#CC0000;">
									<form:option value="DTH">DTH</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Select
									Operator</label></td>
							<td><form:select path="operator" id="operator"
									class="recharge-Select" style="border-color:#CC0000;">
									<form:option value="DISH TV">DISH TV</form:option>
									<form:option value="RELIANCE DIGITAL TV">RELIANCE DIGITAL TV</form:option>
									<form:option value="SUN DIRECT">SUN DIRECT</form:option>
									<form:option value="VIDEOCON D2H">VIDEOCON D2H</form:option>
									<form:option value="AIRTEL DIGITAL TV">AIRTEL DIGITAL TV</form:option>
									<form:option value="TATA SKY">TATA SKY</form:option>

								</form:select></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">DTH-ID
									No.</label></td>
							<td><form:input required="required" path="connectionid"
									id="connectionid" class="recharge-Select"
									style="border-color:#CC0000;"></form:input></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Amount
									(Rs.)</label></td>
							<td><form:input required="required" path="amount"
									id="amount" class="recharge-Select"
									style="border-color:#CC0000;"></form:input></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" name="submit" class="btn btn-large"
								value="Recharge" /></td>
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



