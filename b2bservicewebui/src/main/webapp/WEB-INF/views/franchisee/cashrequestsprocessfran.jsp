<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="grid_10">
	<div class="box round first">
		<h2>Cash Deposit Request</h2>
		<div class="block ">
			<form action="showallcashdepositrequestsdist" method="get">
				<input class="button" type="submit" value="show all">
			</form>
			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty depositrequestlist}">
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
									<th>TRANSFER</th>


								</tr>
							</thead>

							<tbody>
								<c:forEach items="${depositrequestlist}" var="requestItem"
									varStatus="status">
									<tr class="odd gradeX">
										<form action="showTransferPage" method="POST">
										<td class="center">${status.count}</td>
										<td class="center">${requestItem.requestType}</td>
										<td class="center">${requestItem.paymentMode}</td>
										<td class="center">Rs.${requestItem.amount}0</td>
										<td class="center">${requestItem.counter}</td>
										<td class="center">${requestItem.recieptChequeDdNo}</td>
										<td class="center">${requestItem.chequeDdDate}</td>
										<td class="center">${requestItem.remark}</td>
										<td class="center"><input type="hidden" name="amount"
											value="${requestItem.amount}"> <input type="hidden"
											name="id" value="${requestItem.id}"> <input
											type="hidden" name="requesterId"
											value="${requestItem.requesterId}"> <input
											class="button" type="submit" value="transfer"></td>
										</form>

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

