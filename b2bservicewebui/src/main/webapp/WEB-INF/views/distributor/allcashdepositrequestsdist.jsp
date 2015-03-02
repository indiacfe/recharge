<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h2>COMPLETED CASH DEPOSIT REQUESTS</h2>
<div id="table">
	<c:if test="${not empty depositrequestlist }">
		<table border="1" width="100%">
			<tr bgcolor="#3399FF">
				<td><strong>SN.</strong></td>					
				<td><strong>REQ. TYPE</strong></td>
				<td><strong>PAYMENT MODE</strong></td>
				<td><strong>AMOUNT</strong></td>
				<td><strong>BANK</strong></td>							
				<td><strong>RECIEPT NO.</strong></td>
				<td><strong>DATE</strong></td>
				<td><strong>REMARK</strong></td>
			</tr>
			<c:forEach items="${depositrequestlist}" var="requestItem" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${requestItem.requestType}</td>
					<td>${requestItem.paymentMode}</td>
					<td>RS.${requestItem.amount}0</td>
					<td>${requestItem.counter}</td>
					<td>${requestItem.recieptChequeDdNo}</td>					
					<td>${requestItem.chequeDdDate}</td>
					<td>${requestItem.remark}</td>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="grid_10">
	<div class="box round first">
		<h2>COMPLETED CASH DEPOSIT REQUESTS</h2>
		<div class="block ">
			<div class="box round first grid">
				<div class="block">
					<c:if test="${not empty depositrequestlist }">
						<table class="data display datatable" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>REQ. TYPE</th>
									<th>PAYMENT MODE</th>
									<th>AMOUNT</th>
									<th>BANK</th>
									<th>RECIEPT NO.</th>
									<th>DATE</th>
									<th>REMARK</th>
									
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${depositrequestlist}" var="requestItem" varStatus="status">
									<tr class="odd gradeX">
										<td class="center">${status.count}</td>
										<td class="center">${requestItem.requestType}</td>
										<td class="center">${requestItem.paymentMode}</td>
										<td class="center">Rs.${requestItem.amount}0</td>
										<td class="center">${requestItem.counter}</td>
										<td class="center">${requestItem.recieptChequeDdNo}</td>
										<td class="center">${requestItem.chequeDdDate}</td>
										<td class="center">${requestItem.remark}</td>
										
						
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

