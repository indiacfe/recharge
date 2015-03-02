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
		<h2>Please Select Distributor</h2>
		<div class="block ">
			<form action="updateRetailer" method="get">
			<input type="hidden" value="${retailerId}" name="retailerId"> 
				<select name="distId" id="distId">
					<c:forEach var="disid" items="${userdetail}">
						<option value="${disid.userId}">${disid.firmName}</option>
					</c:forEach>

				</select>
				<input type="submit" value="update">
				<input type="button" value="goBack" onclick="goBack();">
			</form>
		</div>
	</div>
</div>
