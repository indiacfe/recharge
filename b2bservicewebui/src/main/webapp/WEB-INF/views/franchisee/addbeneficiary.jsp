
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
	function ifsc() {
		$("#divmmid").hide();
		$("#divifsc").show();
		document.getElementById("formmmid").reset();
	}
	function mmid() {
		$("#divifsc").hide();
		$("#divmmid").show();
		document.getElementById("formfisc").reset();
	}
</script>
<div class="grid_10">
	<div class="box round first" id="fromDate">
		<h2>Add Beneficiary</h2>
		<div class="floatright">
		<ul class="floatleft">
					<li>Sender User :${mobileNo}</li>
					<li><a href="logout">Log out</a></li>
				</ul>
		</div>
		<div class="block " id="toDate">
		<c:if test="${not empty alert}">
					<div class="message info">
						<h5>Information</h5>
						<p>${alert}.</p>
					</div>
				</c:if>
			<table class="form">
				<tbody>


					<tr>
						<td class="col1"><input type="button" class="button"
							id="ifsc" onclick="ifsc();" value="Add Beneficiary By IFSC" /></td>
						<td class="col2"><input type="button" class="button"
							id="mmid" onclick="mmid();" value="Add Beneficiary By MMID" /></td>
					</tr>
				</tbody>
			</table>
			<div id="divifsc" style="display: none">
				<form:form method="post" id="formfisc" action="addbeneficiaryforifsc"
					modelAttribute="remittanceuserdto">
					<table class="form">
						<tbody>


							<tr>
								<td class="col1"><label>Sender Mobile No</label></td>
								<td class="col2"><form:input class="inputfield" value="${mobileNo}"
									readonly="true"	id="mobileNO" required="required" path="mobileNO"></form:input></td>
							</tr> 

							<tr>
								<td class="col1"><label> Beneficiary Name</label></td>
								<td class="col2"><form:input class="inputfield"
										id="benename" required="required" path="benename"></form:input></td>
							</tr>
							<%-- <tr>
								<td class="col1"><label> Beneficiary MMID</label></td>
								<td class="col2"><form:input class="inputfield"
										id="mmid" required="required" path="mmid"></form:input></td>
							</tr> --%>
							<tr>
								<td class="col1"><label> Beneficiary Mobile No</label></td>
								<td class="col2"><form:input class="inputfield"
										id="benemobile" required="required" path="benemobile"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Bank Name</label></td>
								<td class="col2"><form:input class="inputfield"
										id="bankname" required="required" path="bankname"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Bank Branch
										Name</label></td>
								<td class="col2"><form:input class="inputfield"
										id="branchname" required="required" path="branchname"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Bank City Name</label></td>
								<td class="col2"><form:input class="inputfield" id="city"
										required="required" path="city"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Bank State
										Name</label></td>
								<td class="col2"><form:input class="inputfield" id="state"
										required="required" path="state"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Bank IFSC Code</label></td>
								<td class="col2"><form:input class="inputfield"
										id="IFSCCode" required="required" path="IFSCCode"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Bank A/c No</label></td>
								<td class="col2"><form:input class="inputfield"
										id="accountno" required="required" path="accountno"></form:input></td>
							</tr>

						</tbody>
					</table>
					<input type="submit" class="button" value="Submit" />

				</form:form>
			</div>
			<div id="divmmid" style="display: none">
				<form:form method="post" id="formmmid" action="addbeneficiaryformmid"
					modelAttribute="remittanceuserdto">
					<table class="form">
						<tbody>


							<tr>
								<td class="col1"><label>Sender Mobile No</label></td>
								<td class="col2"><form:input class="inputfield" value="${mobileNo}"
									readonly="true"	id="mobileNO" required="required" path="mobileNO"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Name</label></td>
								<td class="col2"><form:input class="inputfield"
										id="benename" required="required" path="benename"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Beneficiary Mobile No</label></td>
								<td class="col2"><form:input class="inputfield"
										id="benemobile" required="required" path="benemobile"></form:input></td>
							</tr>
 
							<tr>
								<td class="col1"><label> Beneficiary MMID</label></td>
								<td class="col2"><form:input class="inputfield"
										id="mmid" required="required" path="mmId"></form:input></td>
							</tr>
							
						</tbody>
					</table>
					<input type="submit" class="button" value="Submit" />

				</form:form>
			</div>
		</div>
	</div>
</div>
