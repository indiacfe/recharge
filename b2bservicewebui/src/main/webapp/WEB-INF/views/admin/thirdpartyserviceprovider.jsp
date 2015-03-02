
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

	<div class="box round first grid" id="fromDate">
		<h2>Current Third Party Service Provider List</h2>
		<div class="block" id="toDate">


			<c:if test="${not empty thirdPartyServiceList}">
				<table class="display" id="example">
					<thead>
						<tr>
							
							<th>Service Type</th>
							<th>Operator Name</th>
							<th>Current Service Provider</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${thirdPartyServiceList}" var="reportItem"
							varStatus="status">

							<tr class="odd gradeX">


								
								<td>${reportItem.serviceType}</td>
								<td>${reportItem.operatorName}</td>
								<td>${reportItem.serviceProvider}</td>

							</tr>


						</c:forEach>
					</tbody>

				</table>
				<input type="button" class="button" value="Download Excel Document"
					onclick="window.location.href='AdminThirdPartyServiceProviderList'" />  
			</c:if>


		</div>
	</div>
</div>
