
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
	function addBankDetail() {
		var mobileNo = document.getElementById("mobileNo").value;
		if (mobileNo.length == 10) {
			if (isNaN(mobileNo)) {

				alert("pls enter a Number in Mobile no.");
				document.getElementById("mobileNo").focus();
				return false;
			}
		} else {
			alert("pls enter the length 10 digit Integer Number");
			document.getElementById("mobileNo").focus();
			return false;
		}
		$("#note").hide();
		$("#form").show();
		var mobileNo = document.getElementById("mobileNo").value;
		$.ajax({
			type : "get",
			url : 'checkexistingmobile/' + mobileNo,
			success : function(data, status) {

				var j = JSON.parse(data);
				$("#mob").val(j);

			},
			error : function(e) {
				alert('No User Found');
			}
		});

		return true;
	}
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
		<h2>Remittance Bank Detail of user</h2>
		<div class="block " id="toDate">
			<form:form method="post" action="getbankdetails"
				modelAttribute="remittanceuserdto">
				<table class="form">
					<tbody>
						<c:if test="${not empty msg}">
							<div class="message info">
								<h5>Information</h5>
								<p>${msg}.</p>
							</div>
						</c:if>
						<c:if test="${not empty err}">
							<div class="message info">
								<h5>Information</h5>
								<p>${err}.</p>
							</div>
						</c:if>
						<c:if test="${not empty nodetail}">
							<div class="message info">
								<h5>Information</h5>
								<p>${nodetail}.</p>
							</div>
						</c:if>
						<c:if test="${not empty mobileNo}">
							<tr>
								<td class="col1"><label>Mobile No</label></td>
								<td class="col2"><form:input class="inputfield"
										value="${mobileNo}" id="mobileNo" required="required"
										path="mobileNO"></form:input></td>
							</tr>
						</c:if>
						<c:if test="${ empty mobileNo }">
							<tr>
								<td class="col1"><label>Enter Mobile No</label></td>
								<td class="col2"><form:input class="inputfield"
										id="mobileNo" required="required" path="mobileNO"></form:input></td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<input type="submit" class="button" value="Get Bank Details" />
				<input type="button" class="button" onclick="addBankDetail();"
					value="Add Bank Detail">
			</form:form>
			<div class="box round first grid">

				<div class="block">
					<c:if test="${not empty bankDetail}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>Nick Name</th>
									<th>Account Name</th>
									<th>Account Number</th>
									<th>Account Type</th>
									<th>IFSC Code</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${bankDetail}" var="bankDetail"
									varStatus="status">

									<tr class="odd gradeX">

										<td>${status.count}</td>
										<td>${bankDetail.nickName}</td>
										<td>${bankDetail.accountName}</td>
										<td>${bankDetail.bankAccountNumber}</td>
										<td>${bankDetail.accountType}</td>
										<td>${bankDetail.IFSCCode}</td>

									</tr>
								</c:forEach>

							</tbody>

						</table>
					</c:if>
				</div>
			</div>
			<form:form method="post" action="addbankdetail"
				modelAttribute="remittanceuserdto" onsubmit="return validate();"
				id="form" style="display:none">
				<table class="form">
					<form:hidden value="" id="mob" path="mobileNO" />

					<tr>
						<td class="col1"><label>Nick Name:</label></td>
						<td class="col2"><form:input class="inputfield"
								placeholder="A name to remember payee" required="required"
								path="nickName"></form:input></td>
					</tr>

					<tr>
						<td class="col1"><label>Account Name:</label></td>
						<td class="col2"><form:input class="inputfield"
								placeholder="Name As in Bank Account" required="required"
								path="accountName"></form:input></td>
					</tr>

					<tr>
						<td class="col1"><label>Account Number:</label></td>
						<td class="col2"><form:input class="inputfield"
								placeholder="Bank A/C Number" required="required"
								path="bankAccountNumber"></form:input></td>
					</tr>

					<tr>
						<td class="col1"><label>Account Type:</label></td>
						<td class="col2"><form:select path="accountType"
								style="width: 153px;" class="Select">
								<form:option value="">Select type</form:option>
								<form:option value="saving">Saving</form:option>
								<form:option value="current">Current</form:option>
							</form:select></td>
					</tr>

					<tr>
						<td class="col1"><label>IFSC Code:</label></td>
						<td class="col2"><form:input class="inputfield" id="IFSC"
								placeholder="11 digit Alpha Numeric data" required="required"
								path="IFSCCode"></form:input></td>
					</tr>
				</table>
				<input type="submit" class="button" value="Add" />
			</form:form>

			<%-- <form:form id="form" style= "display:none">
				<table class="form" >
					 <tr>
					
						<td class="col1"><label>Enter Mobile No</label></td>
					</tr>
						 
				</table>
					</form:form> --%>
			<div class="box round first grid"></div>
		</div>
	</div>
</div>
