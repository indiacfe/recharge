<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="grid_10">
	<div class="box round first">
		<h2>Search Distributor</h2>
		<div class="block ">
			<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
			</c:if>
			<form:form method="POST" action="searchdistributor">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Enter Distributor ID/Mobile
									NO.</label></td>
							<td class="col2"><input class="inputfield"
								name="numberOrDistId" type="text" id="distributorId"
								required="required" /></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="Search" class="btn btn-large"/>
				
			</form:form>
			<form:form method="POST" action="searchdistributor">
				<input type="submit" class="button" value="See All" class="btn btn-large"/>
			</form:form>
		</div>
	</div>
</div>