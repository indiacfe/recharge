<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function telecomRechargeForm() {

		return (contactVali() && confirmation());
	}
	function confirmation() {
		var rechargeType = document.getElementById("rechargeType").value;
		var operator = document.getElementById("cname").value;
		var circle = document.getElementById("circleNames").value;
		var mobNo = document.getElementById("mobileNo").value;
		var rAmount = document.getElementById("amount").value;
		var con = confirm("Recharge type: " + rechargeType + "\n\nOperator: "
				+ operator + "\n\nMobile No.: " + mobNo + "\n\nAmount: "
				+ rAmount
				+ "\n\nAre you sure you want to recharge the mobile no.");
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

		$("#circleNames").empty();
		$.ajax({

			type : "GET",
			url : "getcirclename",
			data : "operator=" + $('#cname').val(),
			success : disp,
		});

		function disp(data) {
			var str = data.split("@");

			if (data != "") {

				var select = '<div class="form_row"><label>Circles</label><select id="selcircle" name="circleCode"></select></div>';
				$("#circleNames").empty();

				$("#circleNames").append(select);

				var auxArr = "";
				for ( var i = 0; i < str.length; i++) {
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

<div id="search_panel1">

	<h1>Mobile Recharge</h1>
	<h3>Your Current Balance is Rs.
		${franchiseeInfo.b2bCurrentBalance}</h3>
	<form:form method="post" action="rechargedetail"
		modelAttribute="telecomrecharge"
		onSubmit="return telecomRechargeForm()">
		<div style="color: red; left-padding: 80px;">${Error}</div>
		<br />
		<div class="form_row">
			<label>Recharge Type</label>
			<form:select path="rechargeType" id="rechargeType">
				<form:option value="MOBILE_PREPAID">Mobile</form:option>
			</form:select>
		</div>
		<div class="form_row" style="display: none">
			<label>Type</label>
			<form:select path="type">
				<form:option value="pinless">PinLess</form:option>
			</form:select>
		</div>
		<%-- <div class="form_row">
			<label>If MNP, Select Manual</label>
			<form:radiobutton path="mnp" style="height:12px;width:15px"
				value="Automatic"></form:radiobutton>
			<label>Automatic</label>
			<form:radiobutton path="mnp" style="height:12px;width:15px"
				value="Manual"></form:radiobutton>
			<label>Manual</label>
		</div> --%>

		<div class="form_row">
			<label>Mobile Operator</label>
			<form:select path="operator"
				onchange="getCircleName(); return false;" id="cname">
				<form:option value="-1" selected="selected">Select</form:option>
				<option value="AIRTEL">AIRTEL</option>
				<option value="AIRCEL">AIRCEL</option>
				<option value="BSNL - SPECIAL TARIFF">BSNL - SPECIAL TARIFF</option>
				<option value="BSNL - TALKTIME">BSNL - TALKTIME</option>
				<option value="IDEA">IDEA</option>
				<option value="LOOP MOBILE">LOOP MOBILE</option>
				<option value="MTNL DELHI SPECIAL">MTNL DELHI SPECIAL</option>
				<option value="MTNL DELHI TALKTIME">MTNL DELHI TALKTIME</option>
				<option value="MTNL MUMBAI SPECIAL">MTNL MUMBAI SPECIAL</option>
				<option value="MTNL MUMBAI TALKTIME">MTNL MUMBAI TALKTIME</option>
				<option value="MTS">MTS</option>
				<option value="T24 MOBILE-SPECIAL TARIFF">T24
					MOBILE-SPECIAL TARIFF</option>
				<option value="T24 MOBILE-TALKTIME">T24 MOBILE-TALKTIME</option>
				<option value="TATA DOCOMO CDMA">TATA DOCOMO CDMA</option>
				<option value="TATA DOCOMO GSM-SPECIAL T">TATA DOCOMO
					GSM-SPECIAL T</option>
				<option value="TATA DOCOMO GSM-TALKTIME">TATA DOCOMO
					GSM-TALKTIME</option>
				<option value="UNINOR-SPECIAL TARIFF">UNINOR-SPECIAL TARIFF</option>
				<option value="UNINOR-TALKTIME">UNINOR-TALKTIME</option>
				<option value="VODAFONE">VODAFONE</option>
				<option value="RELIANCE GSM">RELIANCE GSM</option>
				<option value="TATA_WALKY">TATA_WALKY</option>
				<option value="NORTON MOBILE">NORTON MOBILE</option>
				<option value="RELIANCE CDMA">RELIANCE CDMA</option>
				


			</form:select>
		</div>

		<div id="circleNames"></div>

		<div class="form_row">
			<label>Mobile No.</label>
			<form:input class="inputfield" required="required" path="mobileNo"
				type="text" id="mobileNo"></form:input>
		</div>
		<div class="form_row">
			<label>Amount (Rs.)</label>
			<form:input class="inputfield" required="required" path="amount"
				id="amount"></form:input>
		</div>
		<input type="submit" name="submit" class="button" value="Recharge" />



	</form:form>
	<script type="text/javascript">
		function clearAmount() {
			document.getElementById("amount").value = "";
		}
		clearAmount();
	</script>

</div>