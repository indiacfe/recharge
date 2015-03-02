<%@page import="javax.swing.text.StyledEditorKit.BoldAction"%>
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

			<h2>Login</h2>
			<form:form method="post" action="getloginotp"
				modelAttribute="remittanceuserdto"
				onsubmit="return validatemoblile();">
				<c:if test="${not empty alert}">
					<div class="message info">
						<h5>Information</h5>
						<p>${alert}.</p>
					</div>
				</c:if>
				<p style="font-size: 19px;">Note: Please Enter the number and
					PIN which was sent to Sender' Mobile during Registration.</p>
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Mobile No:</label></td>
							<td class="col2"><form:input class="inputfield" value=""
									placeholder="mobileNo" required="required" path="mobileNO"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label>Enter PIN:</label></td>
							<td class="col2"><form:input type="password"
									class="inputfield" value="" placeholder="Enter pin"
									required="required" path="pin"></form:input></td>
						<tr>
							<td></td>
							<td><input type="submit" value="submit" /></td>

						</tr>

					</tbody>
				</table>
				<div id="mydiv"></div>
			</form:form>


		</div>
	</div>
</div>
