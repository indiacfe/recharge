
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function CompanyConfirmation() {
		var con = confirm("Are you sure want to change your password");
		if (con == true) {
			return true;
		} else {
			document.getElementById("confirmPassword").focus();
			return false;
		}
	}

	function checkPassword() {

		var password = document.getElementById("newPassword").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		if (password.length >= 6) {
			if (password != confirmPassword) {
				alert("password & confirm password should be same");
				document.getElementById("confirmPassword").focus();
				return false;
			}
		} else {
			alert("password length should be grater than 6 digit");
			document.getElementById("newPassword").focus();
			return false;
		}
		return true;
	}
	function changePasswordValidation() {
		return (checkPassword() && CompanyConfirmation());
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Notice Creation</h2>
		<div class="block ">
			<c:if test="${not empty message}">
				<div class="message info">
					<h5>Information</h5>
					<p>${message}</p>
				</div>
			</c:if>
			<form:form method="post" action="newnotice"
				modelAttribute="noticebean">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Notice Name</label></td>
							<td class="col2"><form:input path="name" class="inputfield"
									required="required" id="noticename"></form:input></td>
						</tr>

						<tr>
							<td class="col1"><label>Notice Description</label></td>
							<td class="col2"><form:input path="description"
									class="inputfield" required="required" id="description"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Retailer And Distributor</label></td>
							<td class="col2"><form:checkbox path="retailer"
									class="inputfield" id="retailer"></form:checkbox>Retailer &nbsp; &nbsp;&nbsp;&nbsp;
									<form:checkbox path="distributor"
									class="inputfield" id="distributor"></form:checkbox> Distributor &nbsp; &nbsp;&nbsp;&nbsp;
									<form:checkbox path="homePage"
									class="inputfield" id="homePage"></form:checkbox> Home Page
									
									
									</td>
							<td class="col2"></td>

						</tr>







					</tbody>
				</table>
				<input type="submit" class="button" value="Create" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="noticelist" style="text-decoration: underline; font-size: 13px;"> Notice List</a>

			</form:form>

		</div>
	</div>
</div>
