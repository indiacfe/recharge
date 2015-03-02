
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
		<h2>View Beneficiary</h2>
		<div class="floatright">
		<ul class="floatleft">
					<li>Sender User :${mobileNo}</li>
					<li><a href="logout">Log out</a></li>
				</ul>
		</div>
		<div class="block " id="toDate">
			<form:form method="post" action="viewbeneficiarydetails"
				modelAttribute="remittanceuserdto">
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
							<td class="col2"><form:input class="inputfield" value=""
									onblur="getbeneidbyname();" id="mobileNO" required="required"
									path="mobileNO"></form:input></td>

						</tr>



					</tbody>
				</table>

				<input type="submit" class="button" value="Submit" />

			</form:form>

			<div class="box round first grid">

				<div class="block">
					<c:if test="${not empty reportList }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>BENE ID</th>
									<th>BENE NAME</th>
									<th>BENE MOBILE</th>
									<th>BANK NAME</th>
									<th>BRANCH NAME</th>
									<th>CITY</th>
									<th>STATE</th>
									<th>IFSC CODE</th>
									<th>ACCOUNT NO</th>
									<th>MMID</th>
									<th>DELETE</th>


								</tr>
							</thead>

							<tbody>
								<c:forEach items="${reportList}" var="reportItem"
									varStatus="status">
									<tr class="odd gradeX">
										<td class="center">${status.count}</td>
										<td class="center">${reportItem.beneId}</td>
										<td class="center">${reportItem.benename}</td>
										<td class="center">${reportItem.benemobile}</td>
										<td class="center">${reportItem.bankname}</td>
										<td class="center">${reportItem.branchname}</td>
										<td class="center">${reportItem.city}</td>
										<td class="center">${reportItem.state}</td>
										<td class="center">${reportItem.IFSCCode}</td>
										<td class="center">${reportItem.accountno}</td>
										<td class="center">${reportItem.mmId}</td>
										<td><a
											href="removebeneficiarydetails?beneId=${reportItem.beneId}&cardno=${cardno}">
												Delete</a></td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>

</div>