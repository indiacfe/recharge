<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you soure want to recharge card");
		if (con == true) {
			return true;
		} else {
			document.getElementById("submit").focus();
			return false;
		}
	}
	function mobileNumberValidation() {
		var mobileNo = document.getElementById("mobileNumber").value;
		if (mobileNo.length == 10) {
			if (isNaN(mobileNo)) {
				alert("pls enter number in Mobile no.");
				document.getElementById("mobileNumber").focus();
				return false;
			}
		} else {
			alert("pls enter the length 10 digit Number");
			document.getElementById("mobileNumber").focus();
			return false;
		}
		return true;
	}

	function amountAndCardValidation() {
		var amount = document.getElementById("amount").value;
		var cardNumber = document.getElementById("cardNumber").value;
		if (isNaN(amount)) {
			alert("pls enter number in amount.");
			document.getElementById("amount").focus();
			return false;
		} else if (isNaN(cardNumber)) {
			alert("pls enter number in card number.");
			document.getElementById("cardNumber").focus();
			return false;
		} else {
			return true;
		}

	}
	function validation() {
		return (mobileNumberValidation() && amountValidation() && confirmation());
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">Cash Card Recharge</label>
		</h2>
		<div class="block ">
			<c:if test="${not empty Error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${Error}.</p>
				</div>
			</c:if>
			<p style="font-size: 19px;">Note: Recharge amount should not be
				grater than Rs.10000.</p>
			<form:form method="post" action="icashcardrecharge"
				modelAttribute="iCash">
				<table class="form">
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
							<td class="col2"><input id="rechargeType"
								class="recharge-Select" value="ICASH_CARD"
								style="border-color: #CC0000;" readonly="readonly"></input></td>
						</tr>
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Registered
									Mobile Number</label></td>
							<td><form:input path="mobileNumber" id="rechargeType"
									class="recharge-Select" value="${userName}"
									style="border-color: #CC0000;" readonly="true"></form:input></td>
						</tr>

						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Cash
									Card No.</label></td>
							<td><form:input required="required" path="documentDetail"
									type="text" id="cashCardNumber" class="recharge-Select"
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
							<td><label style="font-size: 20px; color: #CC0000;">Comment
							</label></td>
							<td><form:input required="required" path="comment"
									id="comment" class="recharge-Select"
									style="border-color: #CC0000;"></form:input></td>
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
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you soure want to recharge card");
		if (con == true) {
			return true;
		} else {
			document.getElementById("submit").focus();
			return false;
		}
	}
	function mobileNumberValidation() {
		var mobileNo = document.getElementById("mobileNumber").value;
		if (mobileNo.length == 10) {
			if (isNaN(mobileNo)) {
				alert("pls enter number in Mobile no.");
				document.getElementById("mobileNumber").focus();
				return false;
			}
		} else {
			alert("pls enter the length 10 digit Number");
			document.getElementById("mobileNumber").focus();
			return false;
		}
		return true;
	}

	function amountAndCardValidation() {
		var amount = document.getElementById("amount").value;
		var cardNumber = document.getElementById("cardNumber").value;
		if (isNaN(amount)) {
			alert("pls enter number in amount.");
			document.getElementById("amount").focus();
			return false;
		} else if (isNaN(cardNumber)) {
			alert("pls enter number in card number.");
			document.getElementById("cardNumber").focus();
			return false;
		} else {
			return true;
		}

	}
	function validation() {
		return (mobileNumberValidation() && amountValidation() && confirmation());
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Cash Card Recharge</h2>
		<div class="block ">
			<c:if test="${not empty Error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${Error}.</p>
				</div>
			</c:if>
			<form:form method="post" action="icashcardrecharge"
				onSubmit="return validation();" modelAttribute="iCash">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Registered Mobile Number<font
									color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="mobileNumber" id="mobileNumber"
									value="${userName}" readonly="true"></form:input></td>
							<td class="col1"><label>Card Number<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="documentDetail" id="cardNumber"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Amount<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="amount" id="amount"></form:input></td>
							<td class="col1"><label>Comment<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									path="comment" id="comment" required="required"></form:input></td>
						</tr>
						<tr>
							<td class="col1"></td>
							<td class="col2"><input type="submit" class="button"
								value="Submit" id="submit" /></td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
	</div>
</div>

 --%>