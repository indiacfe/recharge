<%@page import="java.util.UUID"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	var globaldata;
	function loadDetails() {

		$.ajax({
			url : 'connectiondetailsfromthirdparty',
			type : 'GET',

			success : function(data) {
				globaldata = JSON.parse(data);
				$('#amount').val(0);
				getOperatorsName();
			},
		});

	}

	function getOperatorsName() {
		var operator = globaldata;
		operator = jQuery.parseJSON(operator);
		$('#seloperatorname').empty();
		$('#seloperatorname').append("<option value='-1'>select </option>");
		for ( var counter in operator) {
			$('#seloperatorname').append(
					"<option value='"+operator[counter].service_provider+"'>"
							+ operator[counter].service_provider + "</option>");

		}

	}

	function getServices() {
		var service = globaldata;
		service = jQuery.parseJSON(service);
		var selectedoperator = $('#seloperatorname').val();

		var label = "<label style='font-size: 20px; color: #CC0000;''>Sub Services</label>";
		var contents = "<select name='subservice' class='recharge-Select'style='border-color:#CC0000;' id='subserviceselect' onchange='getPackages();'><option value='-1'>Select Subservice</option></select>";
		for ( var counter in service) {
			if (selectedoperator == service[counter].service_provider) {

				var subServices = service[counter].sub_services;
				$('#subservice').empty();
				$('#subservicelist').empty();
				$('#subservice').append(label);
				$('#subservicelist').append(contents);
				for ( var subcounter in subServices) {
					$('#subserviceselect').append(
							"<option value='"+subServices[subcounter].sub_service_name+"'>"
									+ subServices[subcounter].sub_service_name
									+ "</option>");
				}
			}

		}

	}

	function getPackages() {
		var packagesdata = globaldata;
		packagesdata = jQuery.parseJSON(packagesdata);
		var subServSelected = $('#subserviceselect').val();

		var label = "<label style='font-size: 20px; color: #CC0000;'> Packages</label>";
		var contents = "<select name='packageKey' class='recharge-Select'style='border-color:#CC0000;' id='packageselect' onchange='getAmount();'><option value='-1' selected='selected'>Select Package</option></select>";
		$('#Packages').empty();
		$('#Packageslist').empty();
		$('#Packages').append(label);
		$('#Packageslist').append(contents);

		for ( var counter in packagesdata) {
			var subServices = packagesdata[counter].sub_services;
			for ( var subcounter in subServices) {

				if (subServSelected == subServices[subcounter].sub_service_name) {
					var packs = subServices[subcounter].packages;
					for ( var packcounter in packs) {
						$('#packageselect').append(
								"<option value='"+packs[packcounter].package_key+"'>"
										+ packs[packcounter].package_name
										+ "</option>");
					}
				}
			}
		}

	}

	function getAmount() {
		var packagesdata = globaldata;
		packagesdata = jQuery.parseJSON(packagesdata);
		var subServSelected = $('#subserviceselect').val();
		var packkey = $('#packageselect').val();
		for ( var counter in packagesdata) {
			var subServices = packagesdata[counter].sub_services;
			for ( var subcounter in subServices) {

				if (subServSelected == subServices[subcounter].sub_service_name) {
					var packs = subServices[subcounter].packages;
					for ( var packcounter in packs) {
						if (packkey == packs[packcounter].package_key) {
							$('#amount').val(packs[packcounter].package_mrp);
							$('#desclabel').empty();
							$('#description').empty();
							$('#desclabel')
									.append(
											"<label style='font-size: 20px; color: #CC0000;'> Description</label>");

							$('#description').append(
									"<label  style='font-size: 20px; color: #CC0000;'> "
											+ packs[packcounter].package_desc
											+ "</Label>");
						}
					}
				}
			}
		}

	}

	function contactVali() {
		var connection = document.getElementById("connectionid").value;
		var amt = document.getElementById("amount").value;
		var pin = document.getElementById("pinnumber").value;
		var phoneNumber = document.getElementById("connectionid").value;
		var service = document.getElementById("seloperator").value;
		if (service == -1) {
			alert("pls Select Operator");
			document.getElementById("seloperator").focus();
			return false;
		}
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

		if (isNaN(pin)) {
			alert("pls enter a valid Name");
			document.getElementById("pinnumber").focus();
			return false;
		}
		if ((pin.length < 6) && (pin.length > 6)) {
			alert("pls enter a valid Pin Number");
			document.getElementById("pinnumber").focus();
			return false;
		}
		if ((phoneNumber.length < 10) || (phoneNumber.length > 13)) {
			alert("pls enter a valid Phone Number ");
			document.getElementById("connectionid").focus();
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
	function dthForm() {
		return (contactVali() && confirmation());
	}
</script>
<body onload="loadDetails();">
	<div class="grid_10">
		<div class="box round first">
			<h2>
				<label style="font-size: 20px;">DTH CONNECTIONS</label>
			</h2>
			<div class="block ">
				<c:if test="${not empty Error}">
					<div class="message info">
						<h4>Information</h4>
						<h5>${Error}.</h5>
					</div>
				</c:if>
				<p style="font-size: 19px;">Note: Please verify the number
					before recharging as mobile operators does not entertain for
					reversal of wrong recharges</p>
				<form:form method="POST" action="newdthconnectionsdetail"
					commandName="dthConnetion" onSubmit="return contactVali();">
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
									style="font-size: 20px; color: #CC0000;">Rs.${franchiseeInfo.b2bCurrentBalance}</label></td>
							</tr>
							<tr>
								<td class="col1"><label
									style="font-size: 20px; color: #CC0000;">Bill Payment
										Type</label></td>
								<td class="col2"><select name="rechargeType"
									id="rechargeType" class="recharge-Select"
									style="border-color: #CC0000;" onclick="getOperatorsName();">
										<option value="CONNECTION">DTH CONNECTION</option>
								</select></td>
							</tr>
							<tr>
								<td class="col1"><label
									style="font-size: 20px; color: #CC0000;">Operator</label></td>
								<td><form:select path="operator" class="recharge-Select"
										style="border-color:#CC0000;" id="seloperatorname"
										onchange="getServices();">
										<%-- <form:option value="-1">Select</form:option> --%>
										<%-- <form:option value="AIRTEL DIGITAL TV">AIRTEL</form:option>
										<form:option value="DISH TV">Dish TV</form:option>
										<form:option value="SUN DIRECT">Sun Direct</form:option>
										<form:option value="TATA SKY">Tata Sky</form:option>
										<form:option value="VIDEOCON D2H">Videocon D2h</form:option>
								 --%>
									</form:select></td>
							</tr>
							<tr>

								<td class="col1">
									<div id="subservice"></div>
								</td>



								<td class="col2">
									<div id="subservicelist"></div>



								</td>


							</tr>

							<tr>

								<td class="col1">
									<div id="Packages"></div>
								</td>



								<td class="col2">
									<div id="Packageslist"></div>



								</td>


							</tr>

							<tr>
								<td><div id="desclabel"></div></td>
								<td><div id="description"></div></td>
							</tr>



							<tr>
								<td><label style="font-size: 20px; color: #CC0000;">Amount
										(Rs.)</label></td>
								<td><form:input required="required" path="amount"
										id="amount" class="recharge-Select"
										style="border-color:#CC0000;" placeholder="Amount"
										readonly="true"></form:input></td>
							</tr>

							<tr>
								<td><label style="font-size: 20px; color: #CC0000;">Phone
										Number </label></td>
								<td><form:input required="required" path="connectionid"
										id="connectionid" class="recharge-Select"
										style="border-color:#CC0000;" placeholder="Mobile Number"></form:input></td>
							</tr>
							<tr>
								<td><label style="font-size: 20px; color: #CC0000;">Full
										Name </label></td>
								<td><form:input required="required" path="customerName"
										id="customerName" class="recharge-Select"
										style="border-color:#CC0000;" placeholder="Customer Name"></form:input></td>
							</tr>


							<tr>
								<td><label style="font-size: 20px; color: #CC0000;">Customer
										Address </label></td>
								<td><form:input required="required" path="customerAddress"
										id="customerAddress" class="recharge-Select"
										style="border-color:#CC0000;" placeholder="Customer Address"></form:input></td>
							</tr>
							<tr>
								<td><label style="font-size: 20px; color: #CC0000;">Pin
										Number </label></td>
								<td><form:input required="required" path="pinNumber"
										id="pinnumber" class="recharge-Select"
										style="border-color:#CC0000;" placeholder="Pin Number"></form:input></td>
							</tr>





							<tr>
								<td></td>
								<td><input type="submit" name="submit"
									class="btn btn-large" value="Pay" /></td>
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
</body>