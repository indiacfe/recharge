<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	
</script>
<div class="grid_10">
	<div class="box round first">
		<div class="floatright">
			<ul class="floatleft">
				<li>Sender User :${mobileNo}</li>
				<li><a href="logout">Log out</a></li>
			</ul>
		</div>
		<div class="block ">

			<h2>Beneficiary</h2>
			<c:if test="${not empty alert}">
				<div class="message success">
					<h5>Success!</h5>
				</div>
				<div class="message info">
					<h5>Information</h5>
					<p>${alert}.</p>
				</div>
			</c:if>
			<form:form method="get" action="">

				<table class="form">
					<tbody>
						<tr>

							<td><a href="addbeneficiary"><input type="button"
									value="ADD BENEFICIARY" /></a></td>
							<td><a href="viewbeneficiarydetails"><input
									type="button" value="VIEW BENEFICIARY" /></a></td>
							<!-- 							<td><a href="removebeneficiary"><input type="button" value="REMOVE BENEFICIARY" /></a></td>
 -->
						</tr>

					</tbody>
				</table>

			</form:form>

			<div class="box round first grid">




				<div class="block">
					<c:if test="${not empty reportsingleitem ||not empty reportList}">
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
								<c:if test="${not empty reportList}">
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
								</c:if>
								<c:if test="${not empty reportsingleitem}">
									<tr class="odd gradeX">
										<td class="center">1</td>
										<td class="center">${reportsingleitem.beneId}</td>
										<td class="center">${reportsingleitem.benename}</td>
										<td class="center">${reportsingleitem.benemobile}</td>
										<td class="center">${reportsingleitem.bankname}</td>
										<td class="center">${reportsingleitem.branchname}</td>
										<td class="center">${reportsingleitem.city}</td>
										<td class="center">${reportsingleitem.state}</td>
										<td class="center">${reportsingleitem.IFSCCode}</td>
										<td class="center">${reportsingleitem.accountno}</td>
										<td class="center">${reportsingleitem.mmId}</td>
										<td><a
											href="removebeneficiarydetails?beneId=${reportItem.beneId}&cardno=${cardno}">
												Delete</a></td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>


		</div>
	</div>
</div>
