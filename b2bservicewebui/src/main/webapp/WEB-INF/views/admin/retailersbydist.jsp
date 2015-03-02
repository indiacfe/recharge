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


function goBack()
  {
  window.history.back();
  }

</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Please Select User Type</h2>
		<div class="block">
		
				
			
			<form method="get" action="getlists">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><select name="sel" id="sel">
									<option value="ROLE_DISTRIBUTOR">By Distributors</option>
									<option value="ROLE_FRANCHISEE">By Retailers</option>

							</select></td>

						</tr>
					</tbody>
				</table>
				<input type="Submit" class="button" value="Submit">
				<input type="submit" class="button" value="Back" onclick="goBack();">
			</form>
			<div class="box round first grid" id="fromDate">



				<div class="block" id="toDate">


					<c:if test="${not empty userdetail}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>User Id</th>
									<th>Firm Name</th>
									<th>Mobile No.</th>
									<th>Retailers</th>


								</tr>
							</thead>

							<tbody>
								<c:forEach items="${userdetail}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">
										<td class="center">${reportItem.userId}.</td>
										<td class="center">${reportItem.firmName}</td>
										<td class="center">${reportItem.mobileNo}</td>
										<td class="center"><form action="retailerslists"
												method="GET">
												<input type="hidden" name="userId"
													value="${reportItem.userId}"> <input type="submit"
													class="button" type="submit" value="Retailers">
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
