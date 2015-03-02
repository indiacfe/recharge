
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="grid_10">
	<div class="box round first">
		<h2>Notification</h2>
		<div class="block ">

			<div class="message info">
				<h5>Information</h5>
				<p>You have successfully transfered amount to Distributor.</p>
			</div>


			<table class="form">
				<tbody>
					<tr>
						<td class="col1"><label>Mobile number is:</label></td>
						<td class="col2">${mobileNumber}</td>
					</tr>
					<tr>
						<td><label>DistributorId is:</label></td>
						<td>${distId}</td>
					</tr>

					<tr>
						<td><label>Transfer amount is:</label></td>
						<td>${transferamount}</td>
					</tr>

				</tbody>
			</table>

		</div>
	</div>
</div>


