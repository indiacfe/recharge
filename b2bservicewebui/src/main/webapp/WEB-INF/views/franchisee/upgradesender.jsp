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
		<div class="block ">
			<form:form method="POST" action="upgradesender"
				modelAttribute="remittanceuserdto"
				onsubmit="return validatemoblile();">
				<h2>Remittance User Registration</h2>
				<p style="font-size: 19px; color: red">Note: All fields are
					mandatory</p>
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label> Mobile no:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder=" Mobile no" value="${mobileNo}" id="mobileNo"
									readonly="true" required="required" path="mobileNO"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>First Name:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="First Name" required="required" path="firstName"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Middle Name:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Middle Name" required="required" path="middleName"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Last Name:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Last Name" required="required" path="lastName"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Mother Maiden Name:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="" required="required" path="motherMaidenName"></form:input></td>
						</tr>

						<tr>
							<td><label>Date of Birth:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="MM/DD/YY" path="dateOfBirth" type="text"
									id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td><label> ID Proof Type:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="" path="" type="text" id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Address:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Address" required="required" path="address"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>City:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="City" required="required" path="city"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>State:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="State:" required="required" path="state"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Pin Code:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Pincode" required="required" path="pinCode"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><label>EmailId:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Email Id" required="required" path="emailId"></form:input></td>
						</tr>
						<tr>
							<td><label> ID Proof Type:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="idProofType" path="idProofType" type="text"></form:input></td>
						</tr>
						<tr>
							<td><label> ID Proof No:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="Name of idProof" path="idProof" type="text"></form:input></td>
						</tr>
						<tr>
							<td><label> ID Proof URL:</label></td>
							<td><form:input type="file" path="idProofURL" size="40"></form:input></td>
						</tr>
						<tr>
							<td><label> Address Proof Type:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="Address Proof Type" path="addressProofType"
									type="text" id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td><label> Address Proof No:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="Address Proof No" path="addressProof" type="text"></form:input></td>
						</tr>
						<tr>
							<td><label> Address Proof URL:</label></td>
							<td><form:input type="file" path="addressProofURL" size="40" ></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Register" />
			</form:form>
		</div>
	</div>
</div>
