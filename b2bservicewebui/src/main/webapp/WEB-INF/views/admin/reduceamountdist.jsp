<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you sure want to deduct the amount?");
		if (con == true) {
			return true;
		} else {
			return false;
		}
	}
	function transferAmountValidation() {

		var currbal = document.getElementById("currbal").value;
		var adunitbal = document.getElementById("adunitbal").value;
		var b2bbal = document.getElementById("b2bbal").value;
		var currdeduct = document.getElementById("currdeduct").value;
		var adUnitdeduct = document.getElementById("adUnitdeduct").value;
		var b2bdeduct = document.getElementById("b2bdeduct").value;
	
	 
		if (isNaN(currdeduct||b2bdeduct||adUnitdeduct)) {
			alert("pls enter numeric the amount");
			return false;
		}
		var a=currdeduct-currbal;
		var b=b2bdeduct-b2bbal;
		var c=adUnitdeduct-adunitbal;
		if(a>0||b>0||c>0){
			alert("pls enter less amount for reduction ");
			return false;
		}
		return true;
	}
	

	function fundTransferFormValidation() {
		return (transferAmountValidation()  && confirmation());
	}
</script>
<div class="grid_10">
<div class="box round first">
		<h2>Deduct From Distributor current Balance</h2>
		<div class="block ">

			<form method="post" action="deductdistfromcurrbal"
				onsubmit="return fundTransferFormValidation();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Distributor ID:</label></td>
							<td class="col2"><input class="inputfield" name="distId"
								type="text" id="distributorId" readonly="readonly"
								value="${userId}" /></td>
						</tr>

						<tr>
							<td class="col1"><label>Mobile NO.:</label></td>
							<td class="col2"><input class="inputfield"
								name="mobileNumber" type="text" id="mobileno"
								readonly="readonly" value="${mobileNo}" /></td>
						</tr>
						<tr>
							<td><label>Firm Name:</label></td>
							<td><input type="text" name="firmName" id="firmName"
								readonly="readonly" value="${firmName}" /></td>
						</tr>
						<tr>
							<td><label>Current Balance:</label></td>
							<td><input type="text" name="distcurrbal"
								readonly="readonly" value="${distcurrbal}" id="currbal" /></td>
						</tr>
						<tr>
							<td><label>Remark:</label></td>
							<td><input type="text" name="remark" id="firmName" /></td>
						</tr>
						<tr>
							<td class="col1"><label>Deduct Amount:</label></td>
							<td class="col2"><input class="inputfield" name="amount"
								type="text" required="required" id="currdeduct" /></td>
						</tr>


					</tbody>
				</table>
				<input class="button" type="submit" value="Deduct"
					class="btn btn-large" />
			</form>
		</div>
	</div>
<div class="box round first">
		<h2>Deduct From Distributor b2bcurrac Balance</h2>
		<div class="block ">

			<form method="post" action="deductdistfromb2bbal"
				onsubmit="return fundTransferFormValidation();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Distributor ID:</label></td>
							<td class="col2"><input class="inputfield" name="distId"
								type="text" id="distributorId" readonly="readonly"
								value="${userId}" /></td>
						</tr>

						<tr>
							<td class="col1"><label>Mobile NO.:</label></td>
							<td class="col2"><input class="inputfield"
								name="mobileNumber" type="text" id="mobileno"
								readonly="readonly" value="${mobileNo}" /></td>
						</tr>
						<tr>
							<td><label>Firm Name:</label></td>
							<td><input type="text" name="firmName" id="firmName"
								readonly="readonly" value="${firmName}" /></td>
						</tr>
						<tr>
							<td><label>Business Balance:</label></td>
							<td><input type="text" name="distb2bbal"
								readonly="readonly" value="${distb2bcurrbal}" id="b2bbal" /></td>
						</tr>
						<tr>
							<td><label>Remark:</label></td>
							<td><input type="text" name="remark" id="firmName" /></td>
						</tr>
						<tr>
							<td class="col1"><label>Deduct Amount:</label></td>
							<td class="col2"><input class="inputfield" name="amount"
								type="text" required="required" id="b2bdeduct" /></td>
						</tr>


					</tbody>
				</table>
				<input class="button" type="submit" value="Deduct"
					class="btn btn-large" />
			</form>
		</div>
	</div>
	<div class="box round first">
		<h2>Deduct From Distributor Ad-Unit</h2>
		<div class="block ">

			<form method="post" action="deductdistfromadunit"
				onsubmit="return fundTransferFormValidation();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Distributor ID:</label></td>
							<td class="col2"><input class="inputfield" name="distId"
								type="text" id="distributorId" readonly="readonly"
								value="${userId}" /></td>
						</tr>

						<tr>
							<td class="col1"><label>Mobile NO.:</label></td>
							<td class="col2"><input class="inputfield"
								name="mobileNumber" type="text" id="mobileno"
								readonly="readonly" value="${mobileNo}" /></td>
						</tr>

						<tr>
							<td><label>Firm Name:</label></td>
							<td><input type="text" name="firmName" id="firmName"
								readonly="readonly" value="${firmName}" /></td>
						</tr>
						<tr>
							<td><label>Ad-Unit Balance:</label></td>
							<td><input type="text" name="distAdUnitbal"
								readonly="readonly" value="${distAdUnitbal}" id="adunitbal" /></td>
						</tr>
						<tr>
							<td><label>Remark:</label></td>
							<td><input type="text" name="remark" id="firmName" /></td>
						</tr>

						<tr>
							<td class="col1"><label>Deduct Amount:</label></td>
							<td class="col2"><input class="inputfield" name="amount"
								type="text" required="required" id="adUnitdeduct" /></td>
						</tr>


					</tbody>
				</table>
				<input class="button" type="submit" value="Deduct" />
			</form>
		</div>
	</div>

	
</div>

