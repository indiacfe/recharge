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
	
function exeldoc() {
		
		
		alert("hello");
		
		var getexel = document.getElementById("rechargebysms");
		if (document.getElementById("fDate").value != ''
				&& document.getElementById("tDate").value != '') {
          alert("hi..");
			getexel.submit();

		}
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Sms Recharge Report</h2>
		<div class="block ">
			<form method="get" action="smsdetailsreport"
				onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="fromdate" type="text" id="fromDate"></td>
						</tr>
						<tr>
							<td class="col1"><label>To Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="toDate" type="text" id="toDate"></input></td>

						</tr>



					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="submit" />
			</form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty smsdetails}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>sender</th>
									<th>Sender Name</th>
									<th>Message</th>
									<th>Destination</th>
									<th>Sending Time</th>
									<th>Message</th>
									<th>Mid</th>
									<th>Created</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${smsdetails}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">

										<td>${status.count}</td>
										<td>${reportItem.sender}</td>
										<td>${reportItem.senderName}</td>
										<td>${reportItem.message}</td>
										<td>${reportItem.destination}</td>
										<td>${reportItem.stime}</td>
										<td>${reportItem.rawMessage}</td>
										<td>${reportItem.mid}</td>
										<td>${reportItem.createdAt}</td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
					<form method="get" action="SMSRechargeDetails" id="rechargebysms">
						<input type="hidden" id="fDate" name="fromdate"
							value="${fromdate}" /> <input type="hidden" id="tDate"
							name="toDate" value="${toDate}" />

					</form>

				</div>
			</div>
		</div>
	</div>
</div>





