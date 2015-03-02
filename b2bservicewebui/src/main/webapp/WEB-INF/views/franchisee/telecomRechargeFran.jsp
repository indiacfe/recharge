<%@page import="java.util.UUID"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function telecomRechargeForm() {
		return (contactVali() && confirmation());
	}
	function confirmation() {
		var rechargeType = document.getElementById("rechargeType").value;
		var operator = document.getElementById("cname").value;
		var mobNo = document.getElementById("mobileNo").value;
		var rAmount = document.getElementById("amount").value;
		var con = confirm("Recharge type: " + rechargeType + "\n\nOperator: "
				+ operator + "\n\nMobile No.: " + mobNo + "\n\nAmount: "
				+ rAmount
				+ "\n\nAre you sure you want to recharge the above mobile no.?");
		if (con == true) {
			return true;
		} else {

			return false;
		}
	}
	function contactVali() {
		var connection = document.getElementById("mobileNo").value;
		var amt = document.getElementById("amount").value;
		var operator = document.getElementById("cname").value;
		if (connection.length == 10) {
			if (isNaN(connection)) {

				alert("pls enter a Number in Mobile no.");
				document.getElementById("mobileNo").focus();
				return false;
			}
		} else {
			alert("pls enter the length 10 digit Integer Number");
			document.getElementById("mobileNo").focus();
			return false;
		}

		if (isNaN(amt)) {
			alert("pls enter a Number in  Amount");
			document.getElementById("amount").focus();
			return false;
		}
		if (operator == -1) {
			alert("pls select the operator");
			document.getElementById("cname").focus();
			return false;
		}
		return true;

	}

	function getCircleName() {

		$.ajax({

			type : "GET",
			url : "getcirclename",
			data : "operator=" + $('#cname').val(),
			success : disp,
		});

		function disp(data) {
			var str = data.split("@");

			if (data != "") {

				$("#selcircle").empty();

				var auxArr = "";
				for (var i = 0; i < str.length; i++) {
					var circle = str[i].split("#");
					if (circle[0] == 'DELHI') {
						auxArr += '<option value="'+ circle[1] +'" selected="selected">'
								+ circle[0] + '</option>';
					} else {
						auxArr += '<option value="'+ circle[1] +'">'
								+ circle[0] + '</option>';
					}

				}
				$("#selcircle").html(auxArr);
			}
		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">Mobile Recharge</label>
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
			<form:form method="post" action="rechargedetail"
				modelAttribute="telecomrecharge"
				onSubmit="return telecomRechargeForm()">
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
									<form:option value="MOBILE_PREPAID">Mobile</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Mobile
									Operator</label></td>
							<td><form:select path="operator"
									onchange="getCircleName(); return false;" id="cname"
									class="recharge-Select" style="border-color: #CC0000;">
									<form:option value="-1" selected="selected">Select</form:option>
									<option value="AIRTEL">AIRTEL</option>
									<option value="AIRCEL">AIRCEL</option>
									<option value="BSNL - SPECIAL TARIFF">BSNL - SPECIAL
										TARIFF</option>
									<option value="BSNL - TALKTIME">BSNL - TALKTIME</option>
									<option value="IDEA">IDEA</option>
									<option value="LOOP MOBILE">LOOP MOBILE</option>
									<option value="MTNL DELHI SPECIAL">MTNL DELHI SPECIAL</option>
									<option value="MTNL DELHI TALKTIME">MTNL DELHI
										TALKTIME</option>
									<option value="MTNL MUMBAI SPECIAL">MTNL MUMBAI
										SPECIAL</option>
									<option value="MTNL MUMBAI TALKTIME">MTNL MUMBAI
										TALKTIME</option>
									<option value="MTS">MTS</option>
									<option value="T24 MOBILE-SPECIAL TARIFF">T24
										MOBILE-SPECIAL TARIFF</option>
									<option value="T24 MOBILE-TALKTIME">T24
										MOBILE-TALKTIME</option>
									<option value="TATA DOCOMO CDMA">TATA DOCOMO CDMA</option>
									<option value="TATA DOCOMO GSM-SPECIAL T">TATA DOCOMO
										GSM-SPECIAL T</option>
									<option value="TATA DOCOMO GSM-TALKTIME">TATA DOCOMO
										GSM-TALKTIME</option>
									<option value="TATA_WALKY">TATA_WALKY</option>
									<option value="UNINOR-SPECIAL TARIFF">UNINOR-SPECIAL
										TARIFF</option>
									<option value="UNINOR-TALKTIME">UNINOR-TALKTIME</option>
									<option value="VIDEOCON SPECIAL TARIFF">VIDEOCON
										SPECIAL TARIFF</option>
									<option value="VIDEOCON-TALKTIME">VIDEOCON-TALKTIME</option>
									<option value="VODAFONE">VODAFONE</option>
									<option value="RELIANCE GSM">RELIANCE GSM</option>
									<option value="TATA_WALKY">TATA_WALKY</option>
									<!-- <option value="NORTON MOBILE">NORTON MOBILE</option> -->
									<option value="RELIANCE CDMA">RELIANCE CDMA</option>
								</form:select></td>
						</tr>

						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Mobile
									No.</label></td>
							<td><form:input required="required" path="mobileNo"
									type="text" id="mobileNo" class="recharge-Select"
									style="border-color: #CC0000;"></form:input></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Amount
									(Rs.)</label></td>
							<td><form:input required="required" path="amount"
									id="amount" class="recharge-Select"
									style="border-color: #CC0000;"></form:input></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" name="submit" class="btn btn-large"
								value="Recharge" /></td>
						</tr>
						<tr style="visibility: hidden">
							<td><label style="font-size: 20px; color: #CC0000;">Circles</label></td>
							<td><select id="selcircle" name="circleCode"
								class="recharge-Select" style="border-color: #CC0000;"></select></td>
						</tr>
						<%--  <input type ="hidden" name ="uuid" value="${uuidvalue}">   --%>
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