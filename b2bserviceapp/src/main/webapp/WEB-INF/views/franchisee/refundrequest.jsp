<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="search_panel1">
	<h1>Refund Request</h1>
		<h2>${error}</h2>
	<form:form method="POST" action="refundrequestfran">
		<div class="search_panel_box">
			<div class="form_row">
				<label>Transaction ID</label> <input class="inputfield" name="tid"
					type="text" id="transId" />
			</div>
			<input type="submit" class="button" value="Submit" />
		</div>
	</form:form>
</div>
<div style="padding-left: 130px">
	<c:if test="${not empty detailForRefund }">
		<div style="padding-left: 50px">
			<h1>Transaction Details</h1>
		</div>
		<form:form method="post" action="refundamount">
			<table>
				<c:forEach items="${detailForRefund}" var="refund"
					varStatus="status">
					<tr>
						<td align="right"><strong>RETAILER ID : </strong></td>
						<td align="left">${refund.generatedId}</td>
					</tr>
					<tr>
						<td align="right"><strong>RETAILER MOBILE No. : </strong></td>
						<td align="left">${refund.retailerMobNo}</td>
					</tr>
					<tr>
						<td align="right"><strong>RETAILER NAME : </strong></td>
						<td align="left">${refund.retailerName}</td>
					</tr>
					<tr>
						<td align="right"><strong>MOBILE/CONNECTION No. : </strong></td>
						<td align="left">${refund.mobileNo}${refund.connectionid}</td>
					</tr>
					<tr>
						<td align="right"><strong>RECHARGE TYPE : </strong></td>
						<td align="left">${refund.transactionName}</td>
					</tr>
					<tr>
						<td align="right"><strong>OPERATOR : </strong></td>
						<td align="left">${refund.operator}</td>
					</tr>
					<tr>
						<td align="right"><strong>DATE/TIME : </strong></td>
						<td align="left"><strong></strong>${refund.createdAt}</td>
					</tr>
					<tr>
						<td align="right"><strong>CREDIT AMOUNT : </strong></td>
						<td align="left">Rs.${refund.creditAmountFranchisee}</td>
					</tr>
					<tr>
						<td align="right"><strong>AMOUNT : </strong></td>
						<td align="left">Rs.${refund.amount}</td>
					</tr>
					<tr>
						<td align="right"><strong>STATUS : </strong></td>
						<td align="left">${refund.status}</td>
					</tr>
					<input type="hidden" name="id" value="${refund.id}">
					<input type="hidden" name="franId" value="${refund.retailerId}">
					<input type="hidden" name="amount" value="${refund.amount}">
					<input type="hidden" name="creditAmount"
						value="${refund.creditAmountFranchisee}">
					<input type="hidden" name="status" value="${refund.status}">
					<tr>
						<td colspan="2" align="center"><br /> <input type="submit"
							class="button" value="Refund" /></td>
					</tr>
				</c:forEach>

			</table>

		</form:form>
	</c:if>
</div>

