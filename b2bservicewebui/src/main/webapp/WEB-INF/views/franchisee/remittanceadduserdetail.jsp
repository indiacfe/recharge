
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	 $(document).ready(function() {
			$('#example').dataTable({
				"bRetrieve" : true,
				"sPaginationType" : "full_numbers",
				"aaSorting" : [ [ 0, "asc" ] ]
			});
		});
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Remittance Add User Detail</h2>
		<div class="block ">
			<form:form method="post" action="remittancesavedetail"
				modelAttribute="remittanceuserdto"
				onsubmit="return dateRestriction();">
				<c:if test="${not empty remittanceuserdto}">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Mobile No:</label></td>
							<td class="col2"><form:input class="inputfield"
								value="${remittanceuserdto.mobileNO}" readonly="true" path="mobileNO"></form:input></td>
						
						<td class="col1"><label>Enter OTP:</label></td>
							<td class="col2"><form:input class="inputfield"
								 placeholder="Enter OTP" required="required" path="OTP"></form:input></td>
						</tr>
						<tr>
							<td class="col1" ><label>Name:</label></td>
							<td class="col2"><form:input class="inputfield"
						value="${remittanceuserdto.firstName}" placeholder="First Name" required="required" path="firstName"></form:input></td>
							<td class="col2"><form:input class="inputfield"
							value="${remittanceuserdto.lastName}" placeholder="Middle Name" path="middleName"></form:input></td>
							<td class="col2"><form:input class="inputfield"
							value="${remittanceuserdto.lastName}" placeholder="Last Name"	required="required" path="lastName"></form:input></td>

						</tr>

						<tr>
							<td><label>Date of Birth:</label></td>
							<td><form:input class="inputfield" required="required"
									value="${remittanceuserdto.dateOfBirth}" placeholder="MM/DD/YY" path="dateOfBirth" type="text" id="fromDate"></form:input></td>
						</tr>
						<tr> 
							<td class="col1"><label>Pincode:</label></td>
							<td class="col2"><form:input class="inputfield"
								value="${remittanceuserdto.pinCode}" placeholder="Pincode" required="required" path="pinCode"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><label>EmailId:</label></td>
							<td class="col2"><form:input class="inputfield"
							value="${remittanceuserdto.emailId}" placeholder="Email Id"	required="required" path="emailId"></form:input></td>
						</tr>

					</tbody>
				</table></c:if>
				<input type="submit" class="button" value="REGISTER" />
			</form:form>
			<div class="box round first grid"></div>
		</div>
	</div>
</div>
