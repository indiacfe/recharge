<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you soure want to register a user");
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

	function amountValidation() {
		var amount = document.getElementById("amount").value;
		if (isNaN(amount)) {
			alert("pls enter number in amount.");
			document.getElementById("amount").focus();
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
		<h2>Cash Card Creation</h2>
		<div class="block ">
			<c:if test="${not empty Error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${Error}.</p>
				</div>
			</c:if>
			<p style="font­size: 19px;">If you have middle name, you can write it with your last name.</p>	
			<form:form method="post" action="icashregister"
				onSubmit="return validation();" modelAttribute="registration">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>First Name<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="userName" id="name"></form:input></td>
							<td class="col1"><label>Last Name<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="lastName" id="lastName"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Mobile Number<font
									color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									path="mobileNumber" id="mobileNumber" value="${userName}"
									readonly="true"></form:input></td>
							<td class="col1"><label>Date of Birth<font
									color="red">*</font>:
							</label></td>
							<td class="col2"><input class="inputfield" name="birthDate"
								required="required" id="fromDate"></input></td>

						</tr>
						<tr>
							<td style="vertical-align: middle;; padding-top: 0px;"><label>Address<font
									color="red">*</font>:
							</label></td>
							<td class="col2"><form:textarea class="inputfield"
									required="required" path="address" id="address"></form:textarea></td>
							<td class="col1"><label>State<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield" path="state"
									required="required" id="state"></form:input></td>

						</tr>
						<tr>
							<td class="col1"><label>Email ID<font color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="emailId" id="emailId"></form:input></td>
							<td class="col1"><label>Mother's First Name<font
									color="red">*</font>:
							</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="motherName" id="motherName"></form:input></td>
						</tr>





					</tbody>
				</table>
				<input type="submit" class="button" value="Submit" id="submit" />
			</form:form>
		</div>
	</div>
</div>

