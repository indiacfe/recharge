
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="grid_10">
	<div class="box round first">
		<h2>Notification</h2>
		<div class="block ">

			<div class="message info">
				<h5>Information</h5>
				<p>You have successfully deduct amount of ${user}.</p>
			</div>


			<table class="form">
				<tbody>
					<tr>
						<td class="col1"><label>Mobile number is:</label></td>
						<td class="col2">${mobileNumber}</td>
					</tr>
					<tr>
						<td><label>${user} is:</label></td>
						<td>${distId}</td>
					</tr>

					<tr>
						<td><label>Deduct amount is:</label></td>
						<td>${transferamount}</td>
					</tr>

				</tbody>
			</table>

		</div>
	</div>
</div>
