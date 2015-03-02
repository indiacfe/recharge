<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you soure want to add a airtel user");
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
				alert("please enter number in Mobile no.");
				document.getElementById("mobileNumber").focus();
				return false;
			}
		} else {
			alert("please enter the length 10 digit Number");
			document.getElementById("mobileNumber").focus();
			return false;
		}
		return true;
	}

	function userNameValidation() {
		var userName = document.getElementById("userName").value;
		if (userName == null || userName.trim() == "") {
			alert("please enter the username");
			document.getElementById("userName").focus();
			return false;
		} else {
			return true;
		}

	}
	function validation() {
		return (userNameValidation() && mobileNumberValidation() && confirmation());
	}
	function showAirtelUser() {
		var formObj = document.getElementById("formId");
		formObj.action = "updateairteluser";
		formObj.method = "get";
		formObj.submit();
	}
	function updateAirtelUser() {
		var formObj = document.getElementById("formId");
		formObj.action = "updateairteluser";
		formObj.method = "post";
		if (mobileNumberValidation() || userNameValidation()) {
			formObj.submit();
			return true;
		} else {
			return false;
		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Add or Update Airtel User</h2>
		<div class="block ">
			<c:if test="${not empty info}">
				<div class="message info">
					<h5>Information</h5>
					<p>${info}.</p>
				</div>
			</c:if>
			<p style="font-size: 19px;"></p>
			<form:form method="post" action="addnewairteluser"
				onSubmit="return validation();" modelAttribute="addNewAirtelUser"
				id="formId">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>User Name<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="userName" id="userName"
									value="${detail.userName}"></form:input></td>
							<td class="col1"><label>Store Code: </label></td>
							<td class="col2"><form:input class="inputfield"
									path="documentDetail" value="${detail.documentDetail}"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Mobile Number<font
									color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									path="mobileNo" id="mobileNumber" required="required"
									value="${detail.mobileNo}"></form:input></td>
							<td class="col1"><label>Address: </label></td>
							<td class="col2"><form:input class="inputfield"
									path="permanentAddress" value="${detail.permanentAddress}"></form:input></td>

						</tr>
						<tr>
							<td style="vertical-align: middle;; padding-top: 0px;"><label>Circle
									Name: </label></td>
							<td class="col2"><form:input class="inputfield"
									path="documentType" value="${detail.documentType}"></form:input></td>
							<td class="col1"><label>State: </label></td>
							<td class="col2"><form:input class="inputfield" path="state"
									value="${detail.state}"></form:input></td>

						</tr>
						<tr>
							<td class="col1"><label>City Name: </label></td>
							<td class="col2"><form:input class="inputfield"
									path="district" value="${detail.state}"></form:input></td>
							<td class="col1"><label>Pincode: </label></td>
							<td class="col2"><form:input class="inputfield"
									path="pincode" value="${detail.pincode}"></form:input></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="button" value="Add New" />
				<c:if test="${empty updatebutton}">
					<input type="button" class="button" value="Show User"
						onclick="showAirtelUser();" />
				</c:if>
				<c:if test="${not empty updatebutton}">
					<input type="button" class="button" value="Update User"
						onclick="updateAirtelUser();" />
				</c:if>
			</form:form>
		</div>
	</div>
</div>

