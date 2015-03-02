

<%@page import="java.io.PrintWriter"%>
<%@page import="com.cfeindia.b2bserviceapp.util.CyberTelUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	/* <!--
	 $(document).ready(function() {
	 $('#example').dataTable({
	 "bRetrieve" : true,
	 "sPaginationType" : "full_numbers",
	 "aaSorting" : [ [ 0, "desc" ] ]
	 });
	 });
	 -->
	 */
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>LIC Report</h2>
		<div class="block ">
			<div class="box round first grid">

				<div class="block">
					<form method="get" action="licpremiumreport">
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



							<c:if test="${not empty licreportlist }">
								<table class="display" id="example">
									<thead>
										<tr>
											<th>SN.</th>
											<th>Policy Number</th>
											<th>DOB</th>
											<th>Amount</th>
											<th>Created At</th>
											<th>Status</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${licreportlist}" var="licreportitems"
											varStatus="status">

											<tr class="odd gradeX">
												
												<td align="center">${status.count}</td>
												<td align="center">${licreportitems.policyNumber}</td>
												<td align="center">${licreportitems.dob}</td>
												<td align="center">${licreportitems.displayAmount}</td>
												<td align="center">${licreportitems.createdAt}</td>
												<td align="center">${licreportitems.status}</td>
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