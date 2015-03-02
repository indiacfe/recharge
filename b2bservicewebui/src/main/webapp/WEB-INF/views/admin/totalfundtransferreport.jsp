<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

	function changeType(data) {
		if (data == 'month') {
			$("#fromDate").prop("disabled", true);
			$("#toDate").prop("disabled", true);
			$("#monthselect").prop("disabled", false);
			$("#year").prop("disabled", false);
		} else {
			$("#monthselect").prop("disabled", true);
			$("#year").prop("disabled", true);
			$("#fromDate").prop("disabled", false);
			$("#toDate").prop("disabled", false);
		}

	}
	
function exeldoc() {
		
		
		var no1 = document.getElementById("day").value;
		
		
		document.getElementById("no").value = no1;
		
		var getexel = document.getElementById("totalfund");
		if (document.getElementById("fDate").value != ''
				&& document.getElementById("tDate").value != '') {

			getexel.submit();

		}else if(document.getElementById("selMonth").value > 0
			&& document.getElementById("selYear").value > 0){
			var month1 = document.getElementById("monthselect").value;
			var year1 = document.getElementById("year").value;
			if(month1>0 && year1>0){
				
				var newfdate=1+"/"+month1+"/"+year1;
				var newtdate=31+"/"+month1+"/"+year1;
				document.getElementById("fDate").value = newfdate;
				document.getElementById("tDate").value = newtdate;
				getexel.submit();
			}
			
			
		}
	}
</script>
<script type="text/javascript">
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
		<h2>Fund Transfer Report</h2>

		<div class="block ">
			<form:form action="totalfundtransferreport"
				modelAttribute="daymonthreport" method="POST"
				onsubmit="return dateRestriction();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label> Select Report Type</label></td>
							<td class="col2"><form:radiobutton path="reportType"
									label="Day" id="day" value="day"
									onclick="changeType(this.value);" checked="checked"></form:radiobutton>
								<form:radiobutton path="reportType" label="Month" id="month"
									value="month" onclick="changeType(this.value);" /></td>
						</tr>

						<tr>
							<td class="col1"><label>From Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="fromDate" type="text" id="fromDate"></td>
						</tr>
						<tr>
							<td class="col1"><label>To Date</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="toDate" type="text" id="toDate"></td>
						</tr>



						<tr>
							<td class="col1"><div id="datemonthlabel1">
									<label> Month And Year</label>
								</div></td>
							<td class="col2"><div id="datemonthinput1">

									<form:select path="month" id="monthselect" disabled="true">
										<form:option value="-1">Select</form:option>
										<form:option value="1">January</form:option>
										<form:option value="2">February</form:option>
										<form:option value="3">March</form:option>
										<form:option value="4">April</form:option>
										<form:option value="5">May</form:option>
										<form:option value="6">June</form:option>
										<form:option value="7">July</form:option>
										<form:option value="8">August</form:option>
										<form:option value="9">September</form:option>
										<form:option value="10">October</form:option>
										<form:option value="11">November</form:option>
										<form:option value="12">December</form:option>


									</form:select>


									<form:select path="year" id="year" disabled="true">
										<form:option value="2013">2013</form:option>
										<form:option value="2014" selected="selected">2014</form:option>
										<form:option value="2015">2015</form:option>
									</form:select>

								</div></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="submit" />
			</form:form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty transferedreport}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>Date/Time</th>
									<th>Distributor Name</th>
									<th>Transaction Id</th>
									<th>Amount</th>
									<th>Previous Balance</th>
									<th>New Balance</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${transferedreport}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">

										<td>${status.count}</td>
										<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
												value="${reportItem.createdAt}" /></td>
										<td>${reportItem.userName}</td>
										<td>${reportItem.transId}</td>
										<td><fmt:formatNumber type="number"
												value="${reportItem.transferAmount}" maxFractionDigits="2"></fmt:formatNumber></td>
										<td><fmt:formatNumber type="number"
												value="${reportItem.preBalance}" maxFractionDigits="2"></fmt:formatNumber></td>
										<td><fmt:formatNumber type="number"
												value="${reportItem.newBalance}" maxFractionDigits="2"></fmt:formatNumber></td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
						<table>
							<tr>
								<td><h2 style="margin-right: 7px">Total</h2>&emsp;&emsp;&emsp;</td>

								<td><h6>
										Transfer Amount: Rs.
										<fmt:formatNumber type="number" value="${totalAmount}"
											maxFractionDigits="2"></fmt:formatNumber>
									</h6></td>
							</tr>
						</table>
						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
					<form:form action="AdminTotalFundTransfer"
						modelAttribute="daymonthreport" method="POST" id="totalfund">
                      <input type="hidden" id="fDate" name="fromDate" value="${fromDate}" />
						<input type="hidden" id="tDate" name="toDate" value="${toDate}" />
						<form:hidden id="no" path="reportType" />
						<form:hidden id="selMonth" path="month" />
						<form:hidden id="selYear" path="year" />
					</form:form>

				</div>
			</div>
		</div>
	</div>
</div>
