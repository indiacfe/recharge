
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
		<h2>Historical Transaction Summary</h2>
		<div class="block ">
			<form:form method="get" action="#"
				onSubmit="return selectWallet(),dateRestriction();">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Wallet Type</label></td>
							<td class="col2"><form:select path="walletType"
									id="walletType">
									<form:option value="-1">Select</form:option>
									<form:option value="mobile">Mobile</form:option>
									<form:option value="businessLoad">Business Load</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><form:input class="inputfield"
									required="required" path="fromDate" type="text" id="fromDate"></form:input></td>
						</tr>

						<tr>
							<td><label>To Date</label></td>
							<td><form:input class="inputfield" required="required"
									path="toDate" type="text" id="toDate"></form:input></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" required="required" class="button"
					value="Search" />
			</form:form>

		</div>
	</div>
</div>



