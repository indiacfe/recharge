
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

function dateRestriction() {

	var date1 = document.getElementById("fromDate").value;
	var days = 1000 * 3600 * 24;
	var date2 = document.getElementById("toDate").value;
	var firstDate = new Date(date1);
	var secondDate = new Date(date2);
	var timeDiffInDays = Math.floor(Math.abs(secondDate.getTime()
			- firstDate.getTime())
			/ days);
	if (timeDiffInDays > 31 || timeDiffInDays < 0) {
		alert("Please Set the Time Within One Month");
		return false;
	}
}

	$(document).ready(function() {
		$('#example').dataTable({
			"bRetrieve" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 0, "asc" ] ]
		});
	});
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Total Earning Per Day/Month</h2>
		<div class="block ">
			<form:form method="post" action="franearning" onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="fromDate" id="fromDate" type="text" /></td>
						</tr>
						<tr>
							<td><label>To Date</label></td>
							<td><input class="inputfield" required="required"
								name="toDate" id="toDate" type="text" /></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="View" />
			</form:form>
			<div class="box round first grid">

				<div class="block">

					<c:if test="${not empty earning}">

						<h1>Your Total Earning is Rs. ${earning}</h1>
					</c:if>



				</div>
			</div>
		</div>
	</div>
</div>



