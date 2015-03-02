<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="grid_10">
	<div class="box round first">
		<h2>Outlet Configuration: Search Mobile Number</h2>
		<h3>${message}</h3>
		<div class="block ">

			<form:form method="POST" action="outletconfigpage2">
				<table class="form">
				<tbody>

					<tr>
						<td class="col1"><label>Enter the Mobile Number:</label></td>
						<td class="col2"><form:input path="userName" required="required"/></td>
					</tr>

				</tbody>
				</table>
				<input type="submit" class="button" value="Search" />
			</form:form>
		</div>
	</div>
</div>