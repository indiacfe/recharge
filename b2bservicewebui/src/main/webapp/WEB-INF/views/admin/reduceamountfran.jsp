<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function currentBalanceForUse(value) {
	
	if ("CURRENT" == value) {
		$("#b2bBalanceId").show();
		$("#adUnitBalanceId").hide();
	} else if ("ADUNIT" == value) {
		$("#b2bBalanceId").hide();
		$("#adUnitBalanceId").show();
	  }
	
}
	function confirmation() {
		var con = confirm("Are you sure want to deduct the amount?");
		if (con == true) {
			return true;
		} else {
			return false;
		}
	}
	function transferAmountValidation() {
		var paymentType = document.getElementById("paymentType").value;
		if(paymentType==-1){
			alert("pls select Payment type for reduction ");
			document.getElementById("paymentType").focus();
			return false;
		}
		var amount = document.getElementById("transferamount").value;
		if (isNaN(amount)) {
			alert("pls enter the amount ");
			document.getElementById("transferamount").focus();
			return false;
		}

		var b2bbalance=${franBal};
		var adUnitbalance=${franAdunitBal};
		if(b2bbalance<=amount &&paymentType=="CURRENT"||adUnitbalance<=amount && paymentType=="ADUNIT"){
			alert("pls enter less amount for reduction ");
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
		<h2>Deduct To Retailer Balance</h2>
		<div class="block ">

			<form method="post" action="deductamountfran"
				onsubmit="return fundTransferFormValidation();">
				<table class="form">
				<tbody>

					<tr>
						<td class="col1"><label>Retailer ID:</label></td>
						<td class="col2"><input class="inputfield" name="retailerid"
							type="text" id="distributorId" readonly="readonly"
							value="${userId}" /></td>
					</tr>

					<tr>
						<td class="col1"><label>Mobile NO.:</label></td>
						<td class="col2"><input class="inputfield"
							name="mobileNumber" type="text" id="mobileno" readonly="readonly"
							value="${mobileNo}" /></td>
					</tr>
					
					<tr>
						<td><label>Firm Name:</label></td>
						<td><input type="text" name="firmName" id="firmName"
							readonly="readonly" value="${firmName}" /></td>
					</tr>
					<tr>
							<td><label>Payment Type: </label></td>
							<td><select id="paymentType" name="paymentType" onchange="currentBalanceForUse(this.value);">
									<option value="-1">Select
									</option>
									<option value="CURRENT">Current Balance</option>
									<option value="ADUNIT">Ad Unit Balance</option>
							</select></td>
						</tr>
						<tr id="b2bBalanceId" style="display: show;">
							<td><label>Retailer B2B Balance:</label></td>
							<td><input type="text" name="franBal" readonly="readonly"
								value="${franBal}" /></td>
						</tr>
						<tr id="adUnitBalanceId" style="display: none;">
							<td><label>Retailer Ad-Unit Balance:</label></td>
							<td><input type="text" name="franAdunitBal" readonly="readonly"
								value="${franAdunitBal}" /></td>
						</tr>
					<tr>
						<td><label>Remark:</label></td>
						<td><input type="text" name="remark" id="firmName"/></td>
					</tr>

					<tr>
						<td class="col1"><label>Deduct Amount:</label></td>
						<td class="col2"><input class="inputfield" name="amount"
							type="text" required="required" id="transferamount" /></td>
					</tr>


				</tbody>
				</table>
				<input class="button" type="submit" value="Deduct" />
			</form>
		</div>
	</div>
</div>
