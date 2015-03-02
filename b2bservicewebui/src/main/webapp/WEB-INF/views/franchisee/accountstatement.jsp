
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

		var thirdparty1 = document.getElementById("fromDate").value;

		document.getElementById("fDate").value = thirdparty1;

		var thirdparty2 = document.getElementById("toDate").value;

		document.getElementById("tDate").value = thirdparty2;

		var getexel = document.getElementById("acstmt");
		if (document.getElementById("fDate").value != ''
				&& document.getElementById("tDate").value != '') {

			getexel.submit();

		}

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Account Statement</h2>
		<div class="block ">
			<form:form method="post" action="accountstatementretrieve"
				modelAttribute="accountstatement"
				onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>

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
				<input type="submit" class="button" value="View" />
			</form:form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty reportList }">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>DATE/TIME</th>
									<th>PARTICULARS</th>
									<th>TID</th>
									<th>TYPE</th>
									<th>AMOUNT</th>
									<th>BALANCE</th>
									<th>ACCOUNT</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${reportList}" var="reportItem"
									varStatus="status">
									<tr class="odd gradeX">
										<td class="center">${status.count}</td>
										<td class="center">${reportItem.createdAt}</td>
										<td class="center">${reportItem.particulars}</td>
										<td class="center">${reportItem.transactionId}</td>
										<td class="center">${reportItem.transactionType}</td>
										<td class="center">Rs.${reportItem.displayAmount}</td>
										<td class="center">Rs.${reportItem.newB2bCurrBal}</td>
										<td class="center">${reportItem.account}</td>

									</tr>

								</c:forEach>
							</tbody>
						</table>
						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
					<form:form method="post" action="RetailerAcStatement"
						modelAttribute="accountstatement" id="acstmt">

						<form:hidden id="fDate" path="fromDate" />
						<form:hidden id="tDate" path="toDate" />

					</form:form>

				</div>
			</div>
		</div>
	</div>
</div>



