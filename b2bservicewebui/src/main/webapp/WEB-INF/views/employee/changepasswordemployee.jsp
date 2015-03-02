<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
function changePasswordValidation(){
	return (checkPassword() && CompanyConfirmation());
}
</script>

 
<div id="search_panel1">
        <h1>Change Password</h1>
        <h4>${error}</h4>
        <form:form method="post" action="changepasswordprocessemp"  onSubmit="return changePasswordValidation();" modelAttribute="changepass">
          <div class="search_panel_box">
            
            <div class="form_row">
          <label>Old Password</label>
          <form:password class="inputfield" required="required" path="oldPassword"  id="oldPassword"></form:password>
        </div>
            
            <div class="form_row">
          <label>New Password</label>
          <form:password class="inputfield" required="required" path="newPassword" id="newPassword"></form:password>
        </div>
        
           <div class="form_row">
          <label>Confirm Password</label>
          <form:password class="inputfield" required="required" path="confirmPassword"  id="confirmPassword"></form:password>
        </div>
            
          </div>
    
            <input type="submit" required="required" class="button" value="Change"/>
         
        </form:form>
      </div>
 --%>
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
		<h2>Change Password</h2>
		<div class="block ">
			<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>Please try again.</p>
				</div>
			</c:if>
			<form:form method="post" action="changepasswordprocessemp"
				onSubmit="return changePasswordValidation();"
				modelAttribute="changepass">
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
