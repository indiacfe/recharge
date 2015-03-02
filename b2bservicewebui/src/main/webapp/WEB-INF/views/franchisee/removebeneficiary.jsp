
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
	function getbeneidbyname() {

		var mobileNO = document.getElementById("mobileNO").value;

		$
				.ajax({
					type : "get",
					url : 'getbeneficiary/' + mobileNO,
					success : function(data, status) {
						var st = '<div><tr><td class="col1"><label><b>Select Beneficiary Mobile No </b></label></td><td><select name="beneId" style="margin-left: 50px; margin-top: -26px;"></tr></div>'
								+ '<option value="">'
								+ "Select a Beneficiary"
								+ "</option>";

						$('#vehicleId').empty();
						var j = JSON.parse(data);
						var length = j.length;

						for (var i = 0; i < length; i++) {

							st = st + '<option value=' +j[i].beneId+ '>'
									+ j[i].benemobile + '</option>';

						}
						st = st + "</select></td></tr></table></div>";

						$("#vehicleId").append(st);

					},
					error : function(e) {

						alert('No User Found');
					}
				});
	}
</script>
<div class="grid_10">
	<div class="box round first" id="fromDate">
		<h2>Remove Beneficiary</h2>
		<div class="floatright">
			<ul class="floatleft">
				<li>Sender User :${mobileNo}</li>
				<li><a href="logout">Log out</a></li>
			</ul>
		</div>
		<div class="block " id="toDate">
			<form:form method="post" action="removebeneficiarydetails"
				modelAttribute="remittanceuserdto">
				<table class="form">
					<tbody> 
						<tr>
							<td class="col1"><label> Sender Mobile No</label></td>
							<td class="col2"><form:input class="inputfield" value=""
									id="mobileNO" required="required" path="mobileNO"></form:input></td>
							<td><input type="button" class="button"
								value="View Beneficiary" onclick="getbeneidbyname();"
								style="margin-left: 50px;" /></td>
						</tr>

					</tbody>
				</table>
				<div id="vehicleId"></div>
				<input type="submit" class="button" value="Submit" />

			</form:form>
		</div>
	</div>
</div>
