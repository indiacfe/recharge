
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


</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Activity History</h2>
		<div class="block ">
			<form:form method="get" action="#"  onSubmit="return contactVali(),dateRestriction();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><form:input class="inputfield" required="required"
									path="fromDate" id="fromDate"></form:input></td>
						</tr>
						<tr>
							<td><label>To Date</label></td>
							<td><form:input class="inputfield" required="required"
									path="toDate" id="toDate"></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="View" />
			</form:form>
		</div>
	</div>
</div>


