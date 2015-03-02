<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you soure you want to register a user");
		if (con == true) {
			return true;
		} else {
			document.getElementById("submit").focus();
			return false;
		}
	}

	function typeValidation() {
		var reg = document.getElementById("registrationType").value;
		if (reg == "-1") {
			alert("Pls select the registration type");
			document.getElementById("registrationType").focus();
			return false;
		}
		return true;
	}

	function mobileNumberValidation() {
		var mobileNo = document.getElementById("mobileNo").value;
		if (mobileNo.length == 10) {
			if (isNaN(mobileNo)) {
				alert("pls enter number in Mobile no.");
				document.getElementById("mobileNo").focus();
				return false;
			}
		} else {
			alert("pls enter the length 10 digit Number");
			document.getElementById("mobileNo").focus();
			return false;
		}
		return true;
	}

	function passwordValidation() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		if (password.length >= 6) {
			if (password != confirmPassword) {
				alert("password & confirm password should be same");
				document.getElementById("confirmPassword").focus();
				return false;
			}
		} else {
			alert("password length should be grater than 6 digit");
			document.getElementById("password").focus();
			return false;
		}
		return true;
	}
	function validation() {
		return (typeValidation() && mobileNumberValidation()
				&& passwordValidation() && confirmation());
	}
</script>
<div id="search_panel1">
	<h1>Retailer/Distributor Registration</h1>
	<h2>${error}</h2>
	<form:form method="post" action="registrationproccessemp"
		onSubmit="return validation();" modelAttribute="registration">
		<div class="search_panel_box">
			<div class="form_row">
				<label>Type*:</label>
				<form:select path="registrationType" id="registrationType">
					<form:option value="-1">Select</form:option>
					<form:option value="ROLE_DISTRIBUTOR">Distributor</form:option>
					<form:option value="ROLE_FRANCHISEE">Retailer</form:option>
				</form:select>
			</div>
			<div class="form_row">
				<label>Firm Name:</label>
				<form:input class="inputfield" path="firmName" id="firmName"></form:input>
			</div>
			<div class="form_row">
				<label>Name*:</label>
				<form:input class="inputfield" required="required" path="name"
					id="name"></form:input>
			</div>

			<div class="form_row">
				<label>Permanent Address:</label>
				<form:textarea class="inputfield" path="permanentAddress"
					id="permanentAddress"></form:textarea>
			</div>

			<div class="form_row">
				<label>Office Address:</label>
				<form:textarea class="inputfield" path="officeAddress"
					id="officeAddress"></form:textarea>
			</div>


			<div class="form_row">
				<label>District:</label>
				<form:input class="inputfield" path="district" id="district"></form:input>
			</div>

			<div class="form_row">
				<label>Pincode:</label>
				<form:input class="inputfield" path="pincode" id="pincode"></form:input>
			</div>

			<div class="form_row">
				<label>Land LineNo:</label>
				<form:input class="inputfield" path="landLine" id="landLine"></form:input>
			</div>

			<div class="form_row">
				<label>Mobile No*.:</label>
				<form:input class="inputfield" required="required" path="mobileNo"
					id="mobileNo"></form:input>
			</div>

			<div class="form_row">
				<label>Password*:</label>
				<form:password class="inputfield" required="required"
					path="password" id="password"></form:password>
			</div>

			<div class="form_row">
				<label>Confirm Password*:</label> <input class="inputfield"
					required="required" name="confirmPassword" type="password"
					id="confirmPassword" />
			</div>

			<div class="form_row">
				<label>Email ID:</label>
				<form:input class="inputfield" path="emailId" id="emailId"></form:input>
			</div>

			<div class="form_row">
				<label>Bank Account No:</label>
				<form:input class="inputfield" path="bankAccount" id="bankAccount"></form:input>
			</div>

			<div class="form_row">
				<label>Bank Name:</label>
				<form:input class="inputfield" path="bankName" id="bankName"></form:input>
			</div>

			<div class="form_row">
				<label>Bank Branch:</label>
				<form:input class="inputfield" path="bankBranch" id="bankBranch"></form:input>
			</div>

			<div class="form_row">
				<label>Bank Branch City:</label>
				<form:input class="inputfield" path="bankBranchCity"
					id="bankBranchCity"></form:input>
			</div>

			<div class="form_row">
				<label>IFS Code:</label>
				<form:input class="inputfield" path="ifsCode" id="ifsCode"></form:input>
			</div>

			<div class="form_row">
				<label>Pancard No:</label>
				<form:input class="inputfield" path="pancardNo" id="pancardNo"></form:input>
			</div>

		</div>

		<input type="submit" class="button" value="Submit" id="submit" />

	</form:form>
</div>


 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you soure you want to register a user");
		if (con == true) {
			return true;
		} else {
			document.getElementById("submit").focus();
			return false;
		}
	}

	function typeValidation() {
		var reg = document.getElementById("registrationType").value;
		if (reg == "-1") {
			alert("Pls select the registration type");
			document.getElementById("registrationType").focus();
			return false;
		}
		return true;
	}

	function mobileNumberValidation() {
		var mobileNo = document.getElementById("mobileNo").value;
		if (mobileNo.length == 10) {
			if (isNaN(mobileNo)) {
				alert("pls enter number in Mobile no.");
				document.getElementById("mobileNo").focus();
				return false;
			}
		} else {
			alert("pls enter the length 10 digit Number");
			document.getElementById("mobileNo").focus();
			return false;
		}
		return true;
	}

	function passwordValidation() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		if (password.length >= 6) {
			if (password != confirmPassword) {
				alert("password & confirm password should be same");
				document.getElementById("confirmPassword").focus();
				return false;
			}
		} else {
			alert("password length should be grater than 6 digit");
			document.getElementById("password").focus();
			return false;
		}
		return true;
	}
	function validation() {
		return (typeValidation() && mobileNumberValidation()
				&& passwordValidation() && confirmation());
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Retailer/Distributor Registration</h2>
		<div class="block ">
			<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
			</c:if>
			<form:form method="post" action="registrationproccessemp"
				onSubmit="return validation();" modelAttribute="registration">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Type*:</label></td>
							<td class="col2"><form:select path="registrationType"
									id="registrationType">
									<form:option value="-1">Select</form:option>
									<form:option value="ROLE_DISTRIBUTOR">Distributor</form:option>
									<form:option value="ROLE_FRANCHISEE">Retailer</form:option>
								</form:select></td>
							<td class="col1"><label>Firm Name:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="firmName" id="firmName"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Name*:</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="name" id="name"></form:input></td>
							<td class="col1"><label>Mobile Number*:</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="mobileNo" id="mobileNo"></form:input></td>


						</tr>

						<tr>
							<td style="vertical-align: middle;; padding-top: 0px;"><label>Office
									Address:</label></td>
							<td class="col2"><form:textarea class="inputfield"
									path="officeAddress" id="officeAddress"></form:textarea></td>
							<td style="vertical-align: middle; padding-top: 0px;"><label>Permanent
									Address:</label></td>
							<td class="col2"><form:textarea class="inputfield"
									path="permanentAddress" id="permanentAddress"></form:textarea></td>
						</tr>

						<tr>
							<td class="col1"><label>Pincode:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="pincode" id="pincode"></form:input></td>
							<td class="col1"><label>Land LineNo:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="landLine" id="landLine"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>District:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="district" id="district"></form:input></td>
							<td class="col1"><label>Email ID:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="emailId" id="emailId"></form:input></td>
						</tr>


						<tr>
							<td class="col1"><label>Password*:</label></td>
							<td class="col2"><form:password class="inputfield"
									required="required" path="password" id="password"></form:password></td>
							<td class="col1"><label>Confirm Password*:</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="confirmPassword" type="password"
								id="confirmPassword" /></td>
						</tr>

						<tr>
							<td class="col1"><label>Bank Account No:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="bankAccount" id="bankAccount"></form:input></td>
							<td class="col1"><label>Bank Name:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="bankName" id="bankName"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><label>Bank Branch:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="bankBranch" id="bankBranch"></form:input></td>
							<td class="col1"><label>Bank Branch City:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="bankBranchCity" id="bankBranchCity"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><label>IFS Code:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="ifsCode" id="ifsCode"></form:input></td>
							<td class="col1"><label>Pancard No:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="pancardNo" id="pancardNo"></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Submit" id="submit" />
			</form:form>
		</div>
	</div>
</div>