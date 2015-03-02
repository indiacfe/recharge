
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you sure want to transfer the amount to retailer");
		if (con == true) {
			return true;
		} else {
			document.getElementById("submit").focus();
			return false;
		}
	}
	function transferAmountValidation() {
		var amount = document.getElementById("transferamount").value;
		if (isNaN(amount)) {
			alert("Pls enter the number in amount");
			document.getElementById("transferamount").focus();
			return false;
		}
		return true;
	}
	function fundTransferFormValidation() {
		return (transferAmountValidation() && confirmation());

	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Notification</h2>
		<div class="block ">

			<div class="message info">
				<h5>Information</h5>
				<p>You have successfully transfered amount.</p>
			</div>


			<table class="form">
				<tbody>
					<tr>
						<td class="col1"><label>Retailer ID is:</label></td>
						<td class="col2">${franchId}</td>
					</tr>
					<tr>
						<td><label>Mobile NO. is:</label></td>
						<td>${mobileNo}</td>
					</tr>

					<tr>
						<td><label>Transfer Amount(Rs.) is:</label></td>
						<td>${amount}</td>
					</tr>

				</tbody>
			</table>
			
		</div>
	</div>
</div>
