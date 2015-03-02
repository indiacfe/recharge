<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function checkPassword() {

		var newpass = document.getElementById("newPassword").value;
		var confirmpass = document.getElementById("confirmPassword").value;
		if (newpass == confirmpass) {

			return true;
		} else {
			alert("New & confirm password must be same");
			document.getElementById("confirmPassword").focus();
			return false;
		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Change Password</h2>
		<div class="block ">
		<c:if test="${not empty error}">
			<div class="message info">
				<h5>Information</h5>
				<p>Please try again.</p>
			</div></c:if>
			<form:form method="post" action="changepasswordprocessfran"
				onSubmit="return checkPassword();" modelAttribute="changepass">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Old Password</label></td>
							<td class="col2"><form:password class="inputfield"
									required="required" path="oldPassword" id="oldPassword"></form:password></td>
						</tr>

						<tr>
							<td class="col1"><label>New Password</label></td>
							<td class="col2"><form:password class="inputfield"
									required="required" path="newPassword" id="newPassword"></form:password></td>
						</tr>

						<tr>
							<td class="col1"><label>Confirm Password</label></td>
							<td class="col2"><form:password class="inputfield"
									required="required" path="confirmPassword" id="confirmPassword"></form:password></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" required="required" class="button"
					value="Change" />
			</form:form>
		</div>
	</div>
</div>


