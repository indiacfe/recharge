<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
							<th>Email-id</th>
							<th>Firm Name</th>
							<th>Report</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${userdetail}" var="reportItem"
							varStatus="status">
							<c:if test="${fn:startsWith(reportItem.userId, 'R')}">
								<tr class="odd gradeX">
									<td class="col2">${status.count}</td>
									<td class="col2">${reportItem.userId}</td>
									<td class="col2">${reportItem.userName}</td>
									<td class="col2">${reportItem.emailId}</td>
									<td class="col2">${reportItem.firmName}</td>

									<td class="col2"><a
										href="franchiseedayreport?userId=${reportItem.userId}">See
											Report </a></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>

				</table>
				<input type="button" class="button" value="Download Excel Document"
					onclick="window.location.href='AdminGenrateReport'" />
				
			</c:if>
		</div>
	</div>
</div>

