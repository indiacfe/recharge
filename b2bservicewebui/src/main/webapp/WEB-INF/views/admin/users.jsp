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
							<th>Authority</th>
							<th>Enabled</th>
							<td>Deactivate</td>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${userdetail}" var="reportItem"
							varStatus="status">
							<tr class="odd gradeX">
								<td class="col2">${srNo+status.count}</td>
								<td class="col2">${reportItem.userId}</td>
								<td class="col2">${reportItem.userName}</td>
								<td class="col2">${reportItem.password}</td>
								<td class="col2">${reportItem.emailId}</td>
								<td class="col2">${reportItem.firmName}</td>
								<td class="col2">${reportItem.authority}</td>
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

							</tr>
						</c:forEach>

					</tbody>


				</table>
			</c:if>
			<div class="dataTables_paginate paging_full_numbers"
				id="example_paginate">
				<a href="userdetail?page=1"><span
					class="first paginate_button paginate_button_disabled"
					id="example_first">First</span></a>
				<c:if test="${currentPage !=1}">
					<a href="userdetail?page=${currentPage-1}"><span
						class="previous paginate_button" id="example_previous">Previous</span></a>
				</c:if>
				<c:if test="${currentPage ==1}">
					<a href="userdetail?page=1"><span
						class="previous paginate_button" id="example_previous">Previous</span></a>
				</c:if>
				<c:if test="${currentPage <= 2}">

					<c:forEach begin="1" end="5" var="i">

						<td><a href="userdetail?page=${i}"><span
								class="paginate_button">${i}</span></a></td>

					</c:forEach>
				</c:if>
				<c:if test="${currentPage >= 3&& currentPage+1 < totalPage}">

					<c:forEach begin="${currentPage-2}" end="${currentPage+2}" var="i">
						<c:if test="${currentPage <= totalPage}">
							<td><a href="userdetail?page=${i}"><span
									class="paginate_button" id="example_previous">${i}</span></a></td>
						</c:if>
					</c:forEach>
				</c:if>

				<c:if test="${currentPage+1 >= totalPage}">

					<c:forEach begin="${currentPage-2}" end="${totalPage}" var="i">
						<c:if test="${currentPage <= totalPage}">
							<td><a href="userdetail?page=${i}"><span
									class="paginate_button" id="example_previous">${i}</span></a></td>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${currentPage < totalPage}">
					<a href="userdetail?page=${currentPage+1}"><span
						class="next paginate_button" id="example_next">Next</span></a>
				</c:if>
				<c:if test="${currentPage == totalPage}">
					<a href="userdetail?page=${totalPage}"><span
						class="next paginate_button" id="example_next">Next</span></a>
				</c:if>

				<a href="userdetail?page=${totalPage}"><span
					class="last paginate_button" id="example_last">Last</span></a>
			</div>
		</div>
		<input type="button" class="button" value="Download Excel Document"
			onclick="window.location.href='AdminUserDetailsReportList'" />


	</div>
</div>

