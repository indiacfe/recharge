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

			<h2>Enter OTP</h2>
			<form:form method="POST" action="submitotp"
				modelAttribute="remittanceuserdto">
				<c:if test="${not empty alert}">
					<div class="message info">
						<h5>Information</h5>
						<p>${alert}.</p>
					</div>
				</c:if>

				<table class="form">
					<tbody>
						<c:if test="${not empty list}">
							<table class="form">
								<tbody>
									<h5>Your Details:</h5>
									<tr>
										<td class="col1"><label>Mobile No:</label></td>
										<td class="col1"><label>${list[0]}</label></td>
									</tr>
									<tr>
										<td class="col1"><label>Transaction ID:</label></td>
										<td class="col1"><label>${list[1]}</label></td>
									</tr>
									<tr>
										<td class="col1"><label>CARD No:</label></td>
										<td class="col1"><label>${list[2]}</label></td>
									</tr>
								</tbody>
							</table>
						</c:if>
						<tr>
							<td class="col2"><form:input class="inputfield" value=""
									placeholder="ENTER OTP" required="required" path="OTP"></form:input></td>
							<td class="col2">
							<td><input type="submit" value="Submit" /></td>
						</tr>
						<tr>

						</tr>
					</tbody>
				</table>
				<div id="mydiv"></div>
			</form:form>
			<div style="border-top-width: 62px; margin-top: 60px; margin-bottom: 8px; padding-right: 32px;">
				<form:form method="GET" action="resendotprequest"
					modelAttribute="remittanceuserdto">
					<td>
					<td><form:hidden path="transactionId" value="${list[1]}"></form:hidden></td>
					<td><input type="submit" value="Regenerate OTP" /></td>
				</form:form>

			</div>
		</div>
	</div>
</div>
