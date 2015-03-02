<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "asc" ] ]
		});
	});
//-->
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>ALL Notice List</h2>
		<div class="block ">
			<div class="box round first grid" id="fromDate">

				<div class="block" id="toDate">


					<c:if test="${not empty noticelist}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th align="left">Notice ID</th>
									<th align="left">Notice Name</th>
									<th align="left">Description</th>
									<th align="left">Activate/Deactivate</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${noticelist}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">
										<td >${reportItem.noticeId}.</td>
										<td >${reportItem.name}</td>
										<td >${reportItem.description}</td>
										<td ><form action="activeNotice"
												method="GET">
												<input type="hidden" name="noticeId"
													value="${reportItem.noticeId}">
												<c:choose>
													<c:when test="${reportItem.activeNews=='Y'}">
														<input type="submit" class="button" type="submit"
															value="Deactivate">
													</c:when>


													<c:otherwise>
														<input type="submit" class="button" type="submit"
															value="Activate">

													</c:otherwise>
												</c:choose>

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
