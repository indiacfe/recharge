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

	function exeldoc() {

		
		var no1 = document.getElementById("sel").value;

		document.getElementById("distAndRetailer").value = no1;

		var getexel = document.getElementById("distandret");
		if (document.getElementById("distAndRetailer").value != '') {

			getexel.submit();

		}
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Please Select User Type</h2>
		<div class="block ">
			<form method="get" action="distributorsandretailerslist">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><select name="sel" id="sel">
									<option value="Distributors">Distributors</option>
									<option value="Retailers">Retailers</option>
									<option value="All" selected="selected">All</option>
							</select></td>

						</tr>
					</tbody>
				</table>
				<input type="Submit" class="button" value="Submit">
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
									<th>Curr.Bal</th>
									<th>B2B Curr.Bal</th>
									<th>B2B Curr.Ad Unit Bal</th>
									<th>A/c Created At</th>
									<th>CreatedBy</th>
									<th>User Details.</th>
									<th>A/c Deduction</th>



								</tr>
							</thead>

							<tbody>
								<c:forEach items="${userdetail}" var="reportItem"
									varStatus="status">

									<tr class="odd gradeX">
										<td class="center">${reportItem.userId}.</td>
										<td class="center">${reportItem.firmName}</td>
										<td class="center">${reportItem.mobileNo}</td>
										<td class="center">${reportItem.currBal}</td>
										<td class="center">${reportItem.b2bCurrAcBal}${reportItem.b2bCurrBal}</td>
										<td class="center">${reportItem.b2bCurrAdUnitBal}</td>
										<td class="center">${reportItem.createdAt}</td>
										<td class="center">${reportItem.createdBy}</td>
										<td class="center"><form action="showuserdetails"
												method="GET">
												<input type="hidden" name="userId"
													value="${reportItem.userId}"> <input type="submit"
													class="button" type="submit" value="show details">
											</form></td>
										<td class="center"><form method="GET"
												action="amountdeduction">
												<input type="hidden" name="franBal"
													value="${reportItem.b2bCurrBal}"> <input
													type="hidden" name="distcurrbal"
													value="${reportItem.currBal}"> <input type="hidden"
													name="distb2bcurrbal" value="${reportItem.b2bCurrAcBal}">
												<input type="hidden" name="distAdUnitbal"
													value="${reportItem.b2bCurrAdUnitBal}"> <input
													type="hidden" name="userId" value="${reportItem.userId}">
												<input type="hidden" name="firmName"
													value="${reportItem.firmName}"> <input
													type="hidden" name="mobileNo"
													value="${reportItem.mobileNo}"><input type="submit"
													class="button" value="Deduction">
											</form></td>

									</tr>


								</c:forEach>
							</tbody>

						</table>
						<input type="button" class="button"
							value="Download Excel Document" onclick="exeldoc();" />
					</c:if>
					<form method="get" action="AdminDistAndRetailerList"
						id="distandret">
						<input type="hidden" name="sel" id="distAndRetailer">
					</form>

				</div>
			</div>
		</div>
	</div>
</div>
