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
			<form:form method="post" action="enterotp"
				modelAttribute="remittanceuserdto"
				onsubmit="return validatemoblile();">
				<h2>Money Transfer User Registration</h2>
				<c:if test="${not empty Error}">
					<label>&nbsp;&nbsp;&nbsp;</label>
					<font color="red"><h5><c:out value="${Error}"></c:out></h5></font>
				</c:if>
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Mobile No:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield" value=""
									placeholder="mobileNo" required="required" path="mobileNO"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Name:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="First Name" required="required" path="firstName"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Middle Name:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Middle Name" required="required" path="middleName"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Last Name:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Last Name" required="required" path="lastName"></form:input></td>

						</tr>

						<tr>
							<td><label>Date of Birth:</label></td>
							<td><form:input class="inputfield" required="required"
									placeholder="MM/DD/YY" path="dateOfBirth" type="text"
									id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Address:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Address" required="required" path="address"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>City:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="city" required="required" path="city"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>State:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="state" required="required" path="state"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Pincode:<font color="red">*</font></label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Pincode" required="required" path="pinCode"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><label>EmailId:</label></td>
							<td class="col2"><form:input class="inputfield"
									placeholder="Email Id" required="required" path="emailId"></form:input></td>

						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Register" />
			</form:form>
		</div>
	</div>
</div>
