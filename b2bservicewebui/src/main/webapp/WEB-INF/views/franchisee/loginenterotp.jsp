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
<div class="floatright">
		<ul class="floatleft">
					<li>Sender User :${mobileNo}</li>
					<li><a href="logout">Log out</a></li>
				</ul>
		</div>
		<div class="block ">

			<h2>Enter OTP</h2>
			<form:form method="POST" action="loginsubmitotp" modelAttribute="remittanceuserdto"
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
							<td class="col1"><label>Enter OTP:</label></td>
							<td class="col2"><form:input class="inputfield" value=""
									placeholder="Enter OTP" required="required" path="OTP"></form:input></td>
							<td><input type="submit" class="button" value="Submit" /></td>
							<td class="col2"><td><a href="moneytransferlogin">Regenerate OTP</a></td>
						</tr>

					</tbody>
				</table>

			</form:form>
			
			
			


		</div>
	</div>
</div>
