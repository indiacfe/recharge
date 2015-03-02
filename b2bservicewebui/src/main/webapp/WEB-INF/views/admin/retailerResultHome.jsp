<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "asc" ] ]
		});
	});
</script>
<div class="grid_10">
	<div class="box round first grid" id="fromDate">
		<h2>User Detail List</h2>
		<div class="block" id="toDate">
			<%-- <c:if test="${not empty userdetail}"> --%>
			<table class="display" id="example">
				<thead>
					<tr>
						<th>User Id</th>
						<th>User Name</th>
						<th>Password</th>
						<th>Email-id</th>
						<th>Firm Name</th>
						<th>CurrBal</th>
						<th>B2bBal</th>
						<th>AdUnitBal</th>
						<th>Enabled/Disable</th>
						<th>Activate/Deactivate</th>
						<th>Show Details</th>
						<th>Deduction</th>
					</tr>
				</thead>

				<tbody style="text-align: center;">
					<tr class="odd gradeX">
						<td class="col2">${retailerId}</td>
						<td class="col2">${reportItem.username}</td>
						<td class="col2">${reportItem.displayPassword}</td>
						<td class="col2">${reportItem.userDetail.emailId}</td>
						<td class="col2">${reportItem.userDetail.firmName}</td>
						<td class="col2"><fmt:formatNumber type="number"
								value="${getCurrAcBalance}" maxFractionDigits="2"></fmt:formatNumber></td>
						<td class="col2"><fmt:formatNumber type="number"
								value="${getB2bCurrAcBalance}" maxFractionDigits="2"></fmt:formatNumber></td>
						<td class="col2"><fmt:formatNumber type="number"
								value="${getB2bCurrAcAdUnitBal}" maxFractionDigits="2"></fmt:formatNumber></td>
						<c:choose>
							<c:when test="${reportItem.enabled==1}">


								<td class="col2">Enabled</td>
								<c:set var="user" value="Enabled" scope="page" />
							</c:when>
							<c:otherwise>
								<td class="col2">Disabled</td>
							</c:otherwise>
						</c:choose>
						<td class="col2"><form action="deactivate" method="post">
								<input type="hidden" name="userId" value="${retailerId}">
								<input type="hidden" name="enabledisable"
									value="${reportItem.enabled}"> <input type="hidden"
									name="flag" value="true">
								<c:choose>
									<c:when test="${reportItem.enabled==1}">
										<input type="submit" value="Deactivate" class="button">
									</c:when>
									<c:otherwise>
										<input type="submit" value="Activate" class="button">
									</c:otherwise>


								</c:choose>


							</form></td>


						<td class="center"><form action="showuserdetails"
								method="GET">

								<input type="hidden" name="userId" value="${retailerId}">
								<input type="submit" class="button" type="submit"
									value="show details">
							</form></td>
						<td class="center"><form method="GET"
								action="amountdeduction">
								<input type="hidden" name="franBal" value="${getCurrAcBalance}">
								<input type="hidden" name="distcurrbal"
									value="${getCurrAcBalance}"> <input type="hidden"
									name="distb2bcurrbal" value="${getB2bCurrAcBalance}"> <input
									type="hidden" name="distAdUnitbal"
									value="${getB2bCurrAcAdUnitBal}"> <input type="hidden"
									name="userId" value="${retailerId}"> <input
									type="hidden" name="firmName"
									value="${reportItem.userDetail.firmName}"> <input
									type="hidden" name="mobileNo" value="${reportItem.username}"><input
									type="submit" class="button" value="Deduction">
							</form></td>
					</tr>

				</tbody>


			</table>
			<%-- </c:if> --%>
		</div>


	</div>
</div>

