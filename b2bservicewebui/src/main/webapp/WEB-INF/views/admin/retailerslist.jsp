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

	function goBack() {
		window.history.back();
	}
</script>
<div class="grid_10">

	<div class="box round first">

		<h2>All Retailers List</h2>
		<div class="block ">
			<c:if test="${not empty distDetails}">


				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Distributor ID:</label></td>
							<td class="col2"><label>${distDetails.userId }</label></td>
						</tr>

						<tr>
							<td class="col1"><label>Mobile NO.:</label></td>
							<td class="col2"><label>${distDetails.mobileNo}</label></td>
						</tr>

						<tr>
							<td><label>Firm Name:</label></td>
							<td><label>${distDetails.firmName}</label></td>
						</tr>
					</tbody>
				</table>
			</c:if>
		</div>
		<div class="block">



			<div class="box round first grid" id="fromDate"
				style="margin-top: -31px;">



				<div class="block" id="toDate">


					<c:if test="${not empty userdetail}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>User Id</th>
									<th>Firm Name</th>
									<th>Mobile No.</th>
									<th>Add/Change</th>
									<th>Remove</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${userdetail}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">
										<td class="center">${reportItem.userId}.</td>
										<td class="center">${reportItem.firmName}</td>
										<td class="center">${reportItem.mobileNo}</td>

										<td class="center"><form method="GET"
												action="addretailer">
												<input type="hidden" name="retailerId"
													value="${reportItem.userId}"> <input type="hidden"
													name="distId" value="${reportItem.creatorId}">

												<c:choose>
													<c:when test="${reportItem.flag==0}">
														<input type="submit" class="button" value="Add">
													</c:when>

													<c:otherwise>
														<input type="submit" class="button" value="Change">
													</c:otherwise>
												</c:choose>

											</form></td>
										<td>
											<form method="GET" action="removeretailer">
												<input type="hidden" name="retailerId"
													value="${reportItem.userId}"> <input type="hidden"
													name="distId" value="${distDetails.userId}"> <input
													type="submit" class="button" value="Remove">
											</form>
										</td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
					</c:if>


				</div>
			</div>

			<div align="right" class="box round first grid">
				<input type="submit" class="button" value="Back" onclick="goBack();">
			</div>


		</div>


	</div>

</div>
