<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		<h2>Generate Token</h2>
		<div class="block ">
			<form method="post" action="tokengenerator">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Ip Address</label></td>
							<td class="col2"><input class="inputfield"
								required="required" name="ipAddress" type="text" id="ipAddress"></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="btn btn-large" value="Generate Token" />
			</form>
			<br /> <br />

			<div class="box round first grid">

				<div class="block">


					<c:if test="${not empty tokens}">
						<table class="display" id="example">
							<thead>
								<tr>
									<th>SN.</th>
									<th>IP Address</th>
									<th>Token</th>
									<th>Created At</th>
									<th>Status</th>
									<th>Delete</th>


								</tr>
							</thead>

							<tbody>
								<c:forEach items="${tokens}" var="tokens" varStatus="status">

									<tr class="odd gradeX">

										<td>${status.count}</td>
										<td>${tokens.ipAddress}</td>
										<td>${tokens.token}</td>
										<td>${tokens.timestamp}</td>


										<c:choose>
											<c:when test="${tokens.enabled == '1'}">

												<td><form action="changetokenstatus" method="post"
														onsubmit="return confirm('Are you sure to change the status ?');">
														<input type="hidden" name="tokenId" value="${tokens.id}"><input
															type="submit" value="Disable">
													</form></td>
											</c:when>
											<c:otherwise>
												<td><form action="changetokenstatus" method="post"
														onsubmit="return confirm('Are you sure to change the status ?');">
														<input type="hidden" name="tokenId" value="${tokens.id}"><input
															type="submit" value="Enable">
													</form></td>
											</c:otherwise>
										</c:choose>
										<td><a href="deletetoken?tokenId=${tokens.id}" onclick="return confirm('Are you sure want to delete the Token ?');">Delete</a></td>

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
