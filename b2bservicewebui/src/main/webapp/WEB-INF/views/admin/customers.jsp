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
</script>
<div class="grid_10">
	<div class="box round first grid" id="fromDate">
		<h2>User Detail List</h2>
		<div class="block" id="toDate">
			<c:if test="${not empty userdetail}">
				<table class="display" id="example">
					<thead>
						<tr>
							<th>SN.</th>
							<th>User Id</th>
							<th>User Name</th>
							<th>Password</th>
							<th>Email-id</th>
							<th>Firm Name</th>
							<th>A/C Balance</th>
							<th>Enabled</th>
							<th>Deactivate</th>
							<th>Deduct</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${userdetail}" var="reportItem"
							varStatus="status">
							<tr class="odd gradeX">
								<td class="col2">${status.count}</td>
								<td class="col2">${reportItem.userId}</td>
								<td class="col2">${reportItem.userName}</td>
								<td class="col2">${reportItem.password}</td>
								<td class="col2">${reportItem.emailId}</td>
								<td class="col2">${reportItem.firmName}</td>
								<td class="col2">${reportItem.currBal}</td>
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
										<input type="hidden" name="userId"
											value="${reportItem.userId}"> <input type="hidden"
											name="enabledisable" value="${reportItem.enabled}">
										<c:choose>
											<c:when test="${reportItem.enabled==1}">

												<input type="submit" value="Deactivate" class="button">



											</c:when>
											<c:otherwise>
												<input type="submit" value="Activate" class="button">
											</c:otherwise>


										</c:choose>


									</form></td>
								<td><form:form action="deductamountapi" method="post"
										modelAttribute="userDetailDto">
										<form:hidden path="userId" value="${reportItem.userId}" />
										<form:hidden path="mobileNo" value="${reportItem.userName}" />
										<form:hidden path="firmName" value="${reportItem.firmName}" />
										<form:hidden path="currBal" value="${reportItem.currBal}" />
										<input type="submit" value="Deduct" class="button">
									</form:form></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</c:if>
		</div>
	</div>
</div>

