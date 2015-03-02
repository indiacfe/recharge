
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
		<h2>Top up Sender Wallet</h2>
		<div class="floatright">
			<ul class="floatleft">
				<li>Sender User :${mobileNo}</li>
				<li><a href="logout">Log out</a></li>
			</ul>
		</div>
		<div class="block " id="toDate">
			<form:form method="POST" action="topupsenderwallet" modelAttribute="remittanceuserdto">
				<c:if test="${not empty alert}">
				<div class="message info">
					<h5>Information</h5>
					<p>${alert}.</p>
				</div>
				</c:if>
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label> Sender Mobile No</label></td>
							<td class="col2"><form:input class="inputfield" value="${mobileNo}"
									readonly="true" id="cardno" required="required" path="mobileNO"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label> Topup Amount </label></td>
							<td class="col2"><form:input class="inputfield" id="amount"
									required="required" path="amount"></form:input></td>
						</tr>
						<%-- <tr>
							<td class="col1"><label> Mobile No </label></td>
							<td class="col2"><form:input class="inputfield"
									id="mobileNO" required="required" path="mobileNO"></form:input></td>
						</tr> --%>


					</tbody>
				</table>
				<input type="submit" class="button" value="Submit" />

			</form:form>

		</div>
	</div>
</div>
