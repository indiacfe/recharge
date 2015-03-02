<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function validatemoblile() {
		var connection = document.getElementById("mobileNo").value;
		if (connection.length == 10) {
			if (isNaN(connection)) {

				alert("pls enter a Number in Mobile no.");
				document.getElementById("mobileNo").focus();
				return false;
			}
		} else {
			alert("pls enter the length 10 digit Integer Number");
			document.getElementById("mobileNo").focus();
			return false;
		}

		return true;
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<div class="floatright">
			<ul class="floatleft">
				<li>Sender User :${mobileNo}</li>
				<li><a href="logout">Log out</a></li>
			</ul>
		</div>
		<div class="block ">
			<table>
				<tr>
					<td><a href="beneficiary"><input type="button"
							value="ADD,VIEW AND REMOVE BENEFICIARY" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
					<td><a href="topsenderwallet"><input type="button"
							value="TOPUP SENDER WALLET" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
					<td><a href="fundtransferotp"><input type="button"
							value="FUND TRANSFER" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
					<td><a href="moneytransferhistory"><input type="button"
							value="FUND TRANSFER HISTORY" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>

				</tr>
			</table>
			<div class="message success">
				<h5>Success!</h5>
				<p>You have successfully ${success}.</p>
			</div>
			<c:if test="${not empty alert}">
				<div class="message info">
					<h5>Information</h5>
					<p>${alert}.</p>
				</div>
			</c:if>

			<c:if test="${not empty list}">
				<div class="message info">
					<table class="form">
						<tbody>
							<h5>Your Details:</h5>
							<tr>
								<td class="col1"><label>Mobile No:</label></td>
								<td class="col1"><label>${list[0]}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Transaction ID:</label></td>
								<td class="col1"><label>${list[1]}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>CARD No:</label></td>
								<td class="col1"><label>${list[2]}</label></td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${not empty bean}">
				<div class="message info">
					<table class="form">
						<tbody>
							<h5>Your Details:</h5>
							<tr>
								<td class="col1"><label>Name:</label></td>
								<td class="col1"><label>${bean.firstName}
										${bean.middleName} ${bean.lastName}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Mobile No:</label></td>
								<td class="col1"><label>${bean.mobileNO}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Date of Birth:</label></td>
								<td class="col1"><label>${bean.dateOfBirth}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Email ID:</label></td>
								<td class="col1"><label>${bean.emailId}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Pincode:</label></td>
								<td class="col1"><label>${bean.pinCode}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Address:</label></td>
								<td class="col1"><label>${bean.address}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>City:</label></td>
								<td class="col1"><label>${bean.city}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>State:</label></td>
								<td class="col1"><label>${bean.state}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Mother Maiden Name:</label></td>
								<td class="col1"><label>${bean.motherMaidenName}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Card No:</label></td>
								<td class="col1"><label>${bean.cardno}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Wallet Balance:</label></td>
								<td class="col1"><label>${bean.amount}</label></td>
							</tr>
							<tr>
								<td class="col1"><label>Transaction Limit:</label></td>
								<td class="col1"><label>${bean.transactionLimit}</label></td>
							</tr>

						</tbody>
					</table>
				</div>
			</c:if>
			<div id="mydiv"></div>
		</div>
	</div>
</div>
