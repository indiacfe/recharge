<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

function dateRestriction(){
	
	var date1 = document.getElementById("fromDate").value;
	var days=1000 * 3600 * 24;
	var date2 = document.getElementById("toDate").value;
	var firstDate = new Date(date1);
	var secondDate = new Date(date2);
	var timeDiffInDays = Math.floor(Math.abs(secondDate.getTime() - firstDate.getTime())/days);
	if(timeDiffInDays>31 || timeDiffInDays<0){
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
	
	/* function confirmSuccess() {
		
		//alert();}
	
		 jConfirm('Can you confirm this?', 'Confirmation Dialog', function(r) {
		    jAlert('Confirmed: ' + r, 'Confirmation Results');
		}); 
		 }
	 *//* alert("This is success button");
											navigator.notification.confirm("rrr", function(button) {
												if (button == 2) {
													return false;
												}
											}, "EXIT", "NO,YES");
											return false;
	 */
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Lic Premium Request Report</h2>
		<div class="block ">
			<form method="get" action="licpremiumsdetails" onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="fromDate" type="text" id="fromDate"></td>
						</tr>
						<tr>
							<td class="col1"><label>To Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="toDate" type="text" id="toDate"></input></td>

						</tr>
						<tr>
							<td class="col1"><label>Status:</label></td>
							<td class="col2"><select name="status" id="sel">
									<option value="IN_PROCESS">IN PROCESS</option>
									<option value="PENDING">PENDING</option>
									<option value="SUCCESS">SUCCESS</option>
									<option value="REJECTED">REJECTED</option>
							</select></td>
						</tr>



					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="submit" />
			</form>
			<br /> <br /> <label>&nbsp;&nbsp;From Date-&nbsp;&nbsp;</label>
			<c:if test="${not empty fromdate}">

				<c:out value="${fromdate}"></c:out>
			</c:if>

			<label>&nbsp;&nbsp;To Date-&nbsp;&nbsp;</label>
			<c:if test="${not empty todate}">

				<c:out value="${todate}"></c:out>
			</c:if>


			<label>&nbsp;&nbsp;Status-&nbsp;&nbsp;</label>
			<c:if test="${not empty status}">

				<c:out value="${status}"></c:out>
			</c:if>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty licpremiumdetails}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>Retailer Id</th>
									<th>Firm Name</th>
									<th>Customer Name</th>
									<th>Customer Mobile</th>
									<th>Transaction No</th>
									<th>Policy No</th>
									<th>DOB</th>
									<th>Amount</th>
									<th>Created At</th>
									<th>Status</th>
									<th>Success</th>
									<th>Rejects</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${licpremiumdetails}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">

										<td>${status.count}</td>
										<td>R${reportItem.userId}</td>
										<td>${reportItem.userName}</td>
										<td>${reportItem.customerName}</td>
										<td>${reportItem.mobileNo}</td>
										<td>${reportItem.transactionId}</td>
										<td>${reportItem.policyNumber}</td>
										<td>${reportItem.dob}</td>
										<td>${reportItem.amount}</td>
										<td>${reportItem.createdAt}</td>
										<td>${reportItem.status}</td>

										<c:choose>
											<c:when test="${reportItem.status == 'IN_PROCESS'}">

												<td><form action="changestatussuccess" method="post"
														onsubmit="return confirm('Are you sure to change the status ?');">
														<input type="hidden" name="Id" value="${reportItem.id}"><input
															type="submit" value="Success">
													</form></td>
												<td><form action="changestatusrejected" method="post"
														onsubmit="return confirm('Are you sure to change the staus ?');">
														<input type="hidden" name="Id" value="${reportItem.id}">
														<input type="submit" value="Reject">

													</form></td>

											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${reportItem.status == 'SUCCESS'}">
												<td><input type="submit" value="Success"
													disabled="disabled"></td>

												<td><input type="submit" value="Reject"
													disabled="disabled"></td>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${reportItem.status == 'REJECTED'}">
												<td><input type="submit" value="Success"
													disabled="disabled"></td>
												<td><input type="submit" value="Reject"
													disabled="disabled"></td>
											</c:when>
										</c:choose>







										<%-- 	<c:choose>
											<c:when test="${reportItem.status !='REJECTED'}">

												<td><form action="changestatusrejected" method="post"
														onsubmit="return confirm('Are you sure to change the staus ?');">
														<input type="hidden" name="Id" value="${reportItem.id}">
														<input type="submit" value="Reject">

													</form></td>

											</c:when>
											<c:otherwise>

												<td><input type="submit" value="Reject"
													disabled="disabled"></td>
											</c:otherwise>
										</c:choose>
 --%>



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
