
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

	function validate() {
		var connection = document.getElementById("IFSC").value;
		if (connection.length == 10) {
			if (isNaN(connection)) {

				alert("pls enter a Number in Mobile no.");
				document.getElementById("IFSC").focus();
				return false;
			}
		} else {
			alert("pls enter the length 11 digit IFSC Number");
			document.getElementById("IFSC").focus();
			return false;
		}
	}
</script>
<div class="grid_10">
	<div class="box round first" id="fromDate">
		<h2>Fund Transfer OTP</h2>
		<div style="width: 80px; padding-left: 950px;">
			<a href="logout"><input type="button" value="Log out" /></a>
		</div>
		<c:if test="${not empty alert}">
			<div class="message info">
				<h5>Information</h5>
				<p>${alert}.</p>
			</div>
		</c:if>
		<div class="block " id="toDate">
			<form:form method="POST" action="fundtransferotp"
				modelAttribute="remittanceuserdto">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label> Sender Mobile No</label></td>
							<td class="col2"><form:input class="inputfield" value=""
									id="mobileNO" required="required" path="mobileNO"></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Submit" />

			</form:form>
		</div>
	</div>

</div>
