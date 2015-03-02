<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="grid_10">
	<div class="box round first">
		<h2>Outlet Configuration: Firm detail for Outlet update</h2>
		<div class="block ">
			<table class="form">
				<tbody>

					<tr>
						<td class="col1"><label>Mobile Number:</label>
						</td>
						<td class="col2">${userName}</td>
					</tr>
					<tr>
						<td class="col1"><label>Firm Name:</label>
						</td>
						<td class="col2">${userDetail.firmName}</td>
					</tr>
				</tbody>
			</table>

			<form:form method="POST" action="outletconfigfinal"
				modelAttribute="outletDetail">
				<form:hidden path="outletDetailPK.franchiseeId" />
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Third Party Name:</label>
							</td>
							<td class="col2"><form:select path="outletDetailPK.thirdpartyname" id="thirdpartyname">
									<form:option value="-1">select</form:option>
									<form:options items="${thirdpartyname}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="col1"><label>Select Operators:</label>
							</td>
							<td class="col2"><form:select path="outletDetailPK.operator" id="operator">
									<form:option value="-1">select</form:option>
									<form:options items="${operatorname}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="col1"><label>Enter the outlet Id:</label></td>
							<td class="col2"><form:input path="outletid"
									required="required" /></td>
						</tr>

					</tbody>
				</table>
				<input type="submit" class="button" value="Save/Update" />
			</form:form>
		</div>
	</div>
</div>