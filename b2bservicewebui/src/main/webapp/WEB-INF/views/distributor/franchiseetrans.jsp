<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function safeSubmit() {
		var isSubmitted = false;
		if (!isSubmitted) {

			isSubmitted = true;
			document.forms[0].submit;
		}
	}
	function checkLength() {
		var id = document.getElementById("franchiseeid").value;
		if (id == null) {
			return true;
		} else if (id.length != 10) {
			alert("pls enter 10 digit Retailer Id/MobileNo");
			document.getElementById("franchiseeid").focus();
			return false;
		}
	}

	function seeAll() {
		window.location.href = "searchallfranchisee";
	}
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "desc" ] ]
		});
	});
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Search Franchisee-Id/Mobile Number</h2>
		<div class="block ">
			<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
			</c:if>
			<form method="post" action="searchfranchisee"
				onsubmit="return checkLength();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Enter Retailer ID/Mobile NO.</label></td>
							<td class="col2"><input class="inputfield"
								name="franchIdOrmobileNo" type="text" id="franchiseeid" /></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="Search" /> <input
					type="submit" class="button" value="See All" onclick="seeAll();" />
			</form>
			<div class="box round first grid" id="fromDate">

				<div class="block" id="toDate">


					<c:if test="${not empty franlistdetail }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>Retailer Id</th>
									<th>Mobile No.</th>
									<th>Firm Name</th>
									<th>B2B Balance</th> 
									<th>Transfer In B2b Account</th>
									<th>Adunit Balance</th>
									<th>Transfer In Adunit Account</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${franlistdetail}" var="reportItem"
									varStatus="status">
									<tr class="odd gradeX">

										<td class="center">${reportItem.franId}</td>
										<td class="center">${reportItem.mobileNo}</td>
										<td class="center">${reportItem.firmName}</td>
										<td class="center"><fmt:formatNumber type="number"
												maxFractionDigits="2" value="${reportItem.balance}" /></td> 
										<td class="center"><form method="post"
												action="transfertofranchisee">
												<input type="hidden" name="franchId"
													value="${reportItem.franId}" /> <input type="hidden"
													name="mobileNo" value="${reportItem.mobileNo}" /> <input
													type="hidden" name="firmName"
													value="${reportItem.firmName}" /> <input type="hidden"
													name="retailerBal" value="${reportItem.balance}" /> <input
													type="text" name="amount" placeholder="transfer in b2b"
													id="transferamount" required="required" value="${amount}" style="width: 106px;"/>
												<input class="button" type="submit" value="Transfer"
													id="submit" onsubmit="safeSubmit();" />
											</form></td>
											<td class="center"><fmt:formatNumber type="number"
												maxFractionDigits="2" value="${reportItem.adUnitBalance}" /></td>
											<td class="center"><form method="post"
												action="transfertofranchiseeadunit">
												<input type="hidden" name="franchId"
													value="${reportItem.franId}" /> <input type="hidden"
													name="mobileNo" value="${reportItem.mobileNo}" /> <input
													type="hidden" name="firmName"
													value="${reportItem.firmName}" /> <input type="hidden"
													name="retailerBal" value="${reportItem.balance}" /> <input
													type="text" name="amount" placeholder="transfer in adunit"
													id="transferamount" required="required" value="${amount}" style="width: 120px;"/>
												<input class="button" type="submit" value="Transfer"
													id="submit" onsubmit="safeSubmit();" />
											</form></td>


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

