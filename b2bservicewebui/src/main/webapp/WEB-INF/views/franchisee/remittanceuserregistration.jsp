<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

function validatemoblile() {
	var connection = document.getElementById("mobileNo").value;
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

return true;
}  
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Remittance User Registration</h2>
		<div style="width: 80px; padding-left: 950px;">
			<a href="logout"><input type="button" value="Log out" /></a>
		</div>
		<div class="block ">
		<form:form method="post" action="remittancegetotp"
				modelAttribute="remittanceuserdto"
				onsubmit="return validatemoblile();"> 
				<c:if test="${not empty alert}">
				<div class="message info">
					<h5>Information</h5>
					<p>${alert}.</p>
				</div>
				</c:if>
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Mobile No:</label></td>
							<td class="col2"><form:input class="inputfield"
								value="${mobileno}"	placeholder="mobileNo" required="required" readonly="readonly" path="mobileNO"></form:input></td>
						</tr>
						<tr>
							<td class="col1" ><label>Name:</label></td>
							<td class="col2"><form:input class="inputfield"
								placeholder="First Name" required="required" path="firstName"></form:input></td>
							<td class="col2"><form:input class="inputfield"
								placeholder="Middle Name" path="middleName"></form:input></td>
							<td class="col2"><form:input class="inputfield"
								placeholder="Last Name"	required="required" path="lastName"></form:input></td>

						</tr>

						<tr>
							<td><label>Date of Birth:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="MM/DD/YY" path="dateOfBirth" type="text" id="fromDate"></form:input></td>
						</tr>
						<tr> 
							<td class="col1"><label>Pincode:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Pincode" required="required" path="pinCode"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><label>EmailId:</label></td>
							<td class="col2"><form:input class="inputfield"
								placeholder="Email Id"	required="required" path="emailId"></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Get OTP" />
			</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
