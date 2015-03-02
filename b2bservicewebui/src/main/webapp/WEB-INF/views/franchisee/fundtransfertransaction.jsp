
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

	function ifsc() {
		$("#divmmid").hide();
		$("#divifsc").show();
		/* $('#divmmid input[type="text"]').val(''); */
		document.getElementById("formmmid").reset();
	}
	function mmid() {
		$("#divifsc").hide();
		$("#divmmid").show();
		/* $('#divifsc input[type="text"]').val(''); */
		document.getElementById("formifsc").reset();
	}
</script>
<div class="grid_10">
	<div class="box round first" id="fromDate">
		<h2>Fund Transfer Transaction</h2>
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
							id="ifsc" onclick="ifsc();" value="Fund Transfer By IFSC" /></td>
						<td class="col2"><input type="button" class="button"
							id="mmid" onclick="mmid();" value="Fund Transfer By MMID" /></td>
					</tr>
				</tbody>
			</table>
			<div id="divifsc" style="display: none">
				<form:form method="post" id="formifsc"
					action="fundtransfertransactionforifsc"
					modelAttribute="remittanceuserdto">
					<table class="form">
						<tbody>




							<tr>
								<td class="col1"><label> Sender Mobile No</label></td>
								<td class="col2"><form:input class="inputfield"
										value="${mobileNo}" readonly="true" id="mobileNO"
										required="required" path="mobileNO"></form:input></td>

							</tr>


							<tr>
								<td class="col1"><label>Enter Beneficiary Mobile No
								</label></td>
								<td class="col2"><form:input class="inputfield"
										id="benemobile" required="required" path="benemobile"></form:input><label>message
										will send on this Mobile No </label></td>
							</tr>
							<tr>
								<td class="col1"><label> Amount </label></td>
								<td class="col2"><form:input class="inputfield" id="amount"
										required="required" path="amount"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Remarks </label></td>
								<td class="col2"><form:input class="inputfield"
										id="remarks" path="remarks"></form:input></td>
							</tr>
							<tr>
								<td><label> Beneficiary IFSC Code</label></td>
								<td><form:input class="inputfield" id="IFSCCode"
										required="required" path="IFSCCode"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Enter Account No</label></td>
								<td class="col1"><form:input class="inputfield"
										id="accountno" required="required" path="accountno"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label>Select Beneficiary </label></td>
								<td class="col2"><form:select path="beneId"
										onchange="getTransType();" id="ttype">
										<form:option value="-1" selected="selected">Select</form:option>
										<c:forEach items="${reportList}" var="reportItem"
											varStatus="status">
											<option value="${reportItem.beneId}">${reportItem.benename}</option>
										</c:forEach>

									</form:select>
							</tr>

						</tbody>
					</table>




					<input type="submit" class="button" value="Submit" />

				</form:form>
			</div>
			<div id="divmmid" style="display: none">
				<form:form method="post" id="formmmid"
					action="fundtransfertransactionformmid"
					modelAttribute="remittanceuserdto">
					<table class="form">
						<tbody>




							<tr>
								<td class="col1"><label> Sender Mobile No</label></td>
								<td class="col2"><form:input class="inputfield"
										value="${mobileNo}" readonly="true" id="mobileNO"
										required="required" path="mobileNO"></form:input></td>
								<!-- <td><input type="button" class="button"
								value="View Beneficiary" onclick="getbeneidbyname();"
								style="margin-left: 50px;" /></td> -->
							</tr>


							<tr>
								<td class="col1"><label>Enter Beneficiary Mobile No
								</label></td>
								<td class="col2"><form:input class="inputfield"
										id="benemobile" required="required" path="benemobile"></form:input></td>
							</tr>


							<tr>
								<td class="col1"><label> OTP </label></td>
								<td class="col2"><form:input class="inputfield" id="OTP"
										required="required" path="OTP"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Amount </label></td>
								<td class="col2"><form:input class="inputfield" id="amount"
										required="required" path="amount"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label> Remarks </label></td>
								<td class="col2"><form:input class="inputfield"
										id="remarks" path="remarks"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label>Enter Beneficiary MMID </label></td>
								<td class="col1"><form:input class="inputfield" id="mmId"
										required="required" path="mmId"></form:input></td>
							</tr>
							<tr>
								<td class="col1"><label>Select Beneficiary </label></td>
								<td class="col2"><form:select path="beneId"
										onchange="getTransType();" id="ttype">
										<form:option value="-1" selected="selected">Select</form:option>
										<c:forEach items="${reportList}" var="reportItem"
											varStatus="status">
											<option value="${reportItem.beneId}">${reportItem.benename}</option>
										</c:forEach>

									</form:select>
							</tr>

						</tbody>
					</table>









					<input type="submit" class="button" value="Submit" />

				</form:form>
			</div>
		</div>
	</div>

</div>