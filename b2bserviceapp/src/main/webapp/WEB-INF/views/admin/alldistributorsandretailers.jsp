<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="search_panel1">
	<h1>Please Select User Type</h1>
</div>
<div style="width: 500px;"text-align:center"">
	<form method="get" action="distributorsandretailerslist">
		<select name="sel" id="sel">
			<option value="Distributors">Distributors</option>
			<option value="Retailers">Retailers</option>
			<option value="All" selected="selected">All</option>
		</select> <br> <input type="Submit" class="button" value="Submit">
	</form>
</div>

<div id="table" style="padding-left: 50px">
	<c:if test="${not empty userdetail}">
		<table border="1">
			<tr bgcolor="#3399FF">
				<td><strong>User Id</strong></td>
				<td><strong>Firm Name</strong></td>
				<td><strong>Mobile No.</strong></td>
				<td><strong>Business Bal</strong></td>				
				<td><strong>B2B Curr.Bal</strong></td>
				<td><strong>B2B Curr.Ad Unit Bal</strong></td>
				<td><strong>User Details.</strong></td>
			</tr>
			<c:forEach items="${userdetail}" var="reportItem" varStatus="status">
				<form method="GET" action="showuserdetails">
				<tr>
					<td>${reportItem.userId}.</td>
					<td>${reportItem.firmName}</td>
					<td>${reportItem.mobileNo}</td>
					<td>${reportItem.b2bCurrBal}</td>
					<td>${reportItem.b2bCurrAcBal}</td>
					<td>${reportItem.b2bCurrAdUnitBal}</td>
					<td>
						<input type="hidden" name="userId" value="${reportItem.userId}">
					<input type="button" class="button" type="submit"value="show details"></td>
				</tr>
				</form>
			</c:forEach>
		</table>
	</c:if>
</div>
