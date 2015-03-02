<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
function goBack()
{
window.history.back();
}
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
		<h2>Retailer/Distributor Update</h2>
		<div class="block ">
			<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
			</c:if>
			<form:form method="post" action="updateuser"
				onSubmit="return validation();" modelAttribute="registration">
				<table class="form">
					<tbody>
						<form:hidden path="userId" />
						<input type="hidden" name="userId" value="${id}">
						<tr>
							<td class="col1"><label>Type*:</label></td>
							<td class="col2"><form:select path="registrationType"
									id="registrationType">
									<form:option value="-1">Select</form:option>
									<form:option value="ROLE_DISTRIBUTOR">Distributor</form:option>
									<form:option value="ROLE_FRANCHISEE">Retailer</form:option>
								</form:select></td>
							<td class="col1"><label>Firm Name*:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="firmName" id="firmName" required="required"></form:input></td>
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

						<tr>
							<td class="col2"></td>
							<td class="col2"><input type="submit" class="button"
								value="Update" id="submit" /></td>

							<td class="col2">
							<input type="button" class="button" value="Go Back" id="submit" onclick="goBack();" /></td>
								<td class="col2"></td>
							
						</tr>
					</tbody>
				</table>

			</form:form>
		</div>
	</div>
	

</div>
