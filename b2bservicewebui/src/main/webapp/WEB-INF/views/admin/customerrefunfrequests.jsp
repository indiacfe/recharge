
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function checkLength() {
		var id = document.getElementById("transId").value;
		if (id.length != 10) {
			alert("pls enter 10 digit Distributor Id/MobileNo");
			document.getElementById("transId").focus();
			return false;
		}
	}
	function seeAll() {
		window.location.href = "allrefundrequestscust";
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Search Transaction Id</h2>
		<div class="block ">
			<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
			</c:if>
			<form:form method="post" action="searchcustomertransactiondetails">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Enter Transaction Number:</label></td>
							<td class="col2"><input class="inputfield" name="tid"
								type="text" id="transId" /></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="Submit" />
				<input class="button" type="button" value="See all"
					onclick="seeAll()">
			</form:form>
		</div>
	</div>
</div>
<c:if test="${not empty detailForRefund }">
	<div class="box round first">
		<h2>Transaction Details</h2>
		<div class="block ">
			<form:form method="post" action="refundamount">
				<table class="form">
					<c:forEach items="${detailForRefund}" var="refund"
						varStatus="status">
						<tbody>

							<tr>
								<td class="col1"><label>RETAILER ID:</label></td>
								<td class="col2">${refund.generatedId}</td>
							</tr>
							<tr>
								<td align="col1">RETAILER MOBILE No.:</td>
								<td align="col2">${refund.retailerMobNo}</td>
							</tr>
							<tr>
								<td align="col1">RETAILER NAME :</td>
								<td align="col2">${refund.retailerName}</td>
							</tr>
							<tr>
								<td align="col1">MOBILE/CONNECTION No. :</td>
								<td align="col2">${refund.mobileNo}${refund.connectionid}</td>
							</tr>
							<tr>
								<td align="col1">RECHARGE TYPE :</td>
								<td align="col2">${refund.transactionName}</td>
							</tr>
							<tr>
								<td align="col1">OPERATOR :</td>
								<td align="col2">${refund.operator}</td>
							</tr>
							<tr>
								<td align="col1">DATE/TIME :</td>
								<td align="col2">${refund.createdAt}</td>
							</tr>
							<tr>
								<td align="col1">CREDIT AMOUNT :</td>
								<td align="col2">Rs.${refund.creditAmountFranchisee}</td>
							</tr>
							<tr>
								<td align="col1">AMOUNT :</td>
								<td align="col2">Rs.${refund.amount}</td>
							</tr>
							<tr>
								<td align="col1">STATUS :</td>
								<td align="col2">${refund.status}</td>
							</tr>


							<tr>
								<td><input type="hidden" name="id" value="${refund.id}">
									<input type="hidden" name="franId" value="${refund.retailerId}">
									<input type="hidden" name="amount" value="${refund.amount}">
									<input type="hidden" name="creditAmount"
									value="${refund.creditAmountFranchisee}"> <input
									type="hidden" name="status" value="${refund.status}"></td>
							</tr>


						</tbody>
					</c:forEach>
				</table>

				<input type="submit" class="button" value="Refund" />
			</form:form>
		</div>
	</div>
</c:if>
