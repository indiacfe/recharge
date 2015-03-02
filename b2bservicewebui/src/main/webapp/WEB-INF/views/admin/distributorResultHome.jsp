<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you sure want to transfer the amount?");
		if (con == true) {
			return true;
		} else {
			return false;
		}
	}
	function transferAmountValidation() {
		var amount = document.getElementById("transferamount").value;
		if (isNaN(amount)) {
			alert("pls enter the amount");
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
		<h2>Transfer To Distributor Ad-Unit Balance</h2>
		<div class="block ">

			<form method="post" action="transfertodistributor"
				onsubmit="return fundTransferFormValidation();">
				<table class="form">
				<tbody>

					<tr>
						<td class="col1"><label>Distributor ID:</label></td>
						<td class="col2"><input class="inputfield" name="distId"
							type="text" id="distributorId" readonly="readonly"
							value="${distId}" /></td>
					</tr>

					<tr>
						<td class="col1"><label>Mobile NO.:</label></td>
						<td class="col2"><input class="inputfield"
							name="mobileNumber" type="text" id="mobileno" readonly="readonly"
							value="${mobileNumber}" /></td>
					</tr>
					
					<tr>
						<td><label>Firm Name:</label></td>
						<td><input type="text" name="firmName" id="firmName"
							readonly="readonly" value="${firmName}" /></td>
					</tr>
					
					
					
					
					
					<tr>
						<td><label>Current Add Unit Balance :</label></td>
						<td><input type="text" name="b2baddunitbalance" id="b2baddunitbalance"
							readonly="readonly" value="${funds.b2bCurrAcAdUnitBal}" /></td>
					</tr>
					



					<tr>
						<td class="col1"><label>Transfer Amount:</label></td>
						<td class="col2"><input class="inputfield" name="amount"
							type="text" required="required" id="transferamount" /></td>
					</tr>


				</tbody>
				</table>
				<input class="button" type="submit" value="submit" />
			</form>
		</div>
	</div>

<div class="box round first">
		<h2>Transfer To Distributor Business Balance</h2>
		<div class="block ">

			<form method="post" action="transfertodistributorB2b" onsubmit="return fundTransferFormValidation();">
				<table class="form">
				<tbody>

					<tr>
						<td class="col1"><label>Distributor ID:</label></td>
						<td class="col2"><input class="inputfield" name="distId"
							type="text" id="distributorId" readonly="readonly"
							value="${distId}" /></td>
					</tr>

					<tr>
						<td class="col1"><label>Mobile NO.:</label></td>
						<td class="col2"><input class="inputfield"
							name="mobileNumber" type="text" id="mobileno" readonly="readonly"
							value="${mobileNumber}" /></td>
					</tr>
					<tr>
						<td><label>Firm Name:</label></td>
						<td><input type="text" name="firmName" id="firmName"
							readonly="readonly" value="${firmName}" /></td>
					</tr>
					
					<tr>
						<td><label>Current Business Balance :</label></td>
						<td><input type="text" name="b2bcurrentamount" id="b2bcurrentamount"
							readonly="readonly" value="${funds.currAcBalance}" /></td>
					</tr>
					
					
					
					<tr>
						<td class="col1"><label>Transfer Amount:</label></td>
						<td class="col2"><input class="inputfield" name="amount"
							type="text" required="required" id="transferamount" /></td>
					</tr>


				</tbody>
				</table>
				<input class="button" type="submit" value="submit" class="btn btn-large"/>
			</form>
		</div>
	</div>
	<div class="box round first">
		<h2>Transfer To Distributor B2B Balance</h2>
		<div class="block ">

			<%-- <form method="post" action="transfertodistributorB2b" onsubmit="return fundTransferFormValidation();"> --%>
				<table class="form">
				<tbody>

					<tr>
						<td class="col1"><label>Distributor ID:</label></td>
						<td class="col2"><input class="inputfield" name="distId"
							type="text" id="distributorId" readonly="readonly"
							value="${distId}" /></td>
					</tr>

					<tr>
						<td class="col1"><label>Mobile NO.:</label></td>
						<td class="col2"><input class="inputfield"
							name="mobileNumber" type="text" id="mobileno" readonly="readonly"
							value="${mobileNumber}" /></td>
					</tr>
					<tr>
						<td><label>Firm Name:</label></td>
						<td><input type="text" name="firmName" id="firmName"
							readonly="readonly" value="${firmName}" /></td>
					</tr>
					
					<tr>
						<td><label>Current B2B Balance :</label></td>
						<td><input type="text" name="b2bcurrentamount" id="b2bcurrentamount"
							readonly="readonly" value="${funds.b2bCurrAcBalance}" /></td>
					</tr>
					
					
					
					<!-- <tr>
						<td class="col1"><label>Transfer Amount:</label></td>
						<td class="col2"><input class="inputfield" name="amount"
							type="text" required="required" id="transferamount" /></td>
					</tr> -->


				</tbody>
				</table>
				<!-- <input class="button" type="submit" value="submit" class="btn btn-large"/> -->
			<%-- </form> --%>
		</div>
	</div>
</div>


