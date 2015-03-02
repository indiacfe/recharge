<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation(){
		var con=confirm("Are you sure want to transfer the amount?");
		if(con==true){
			return true;
		}
		else{
			return false;
		}
	}
	function transferAmountValidation(){
		var amount=document.getElementById("transferamount").value;
		if(isNaN(amount)){
			alert("pls enter the amount");
			document.getElementById("transferamount").focus();
			return false;
		}
		return true ;
	} 
	function fundTransferFormValidation(){
		return (transferAmountValidation() && confirmation());
	}
</script>
<div id="search_panel1">
	<h1>Transfer To Distributor Ad-Unit</h1>
	<form method="post" action="transfertodistributor" onsubmit="return fundTransferFormValidation();">
		<table>

			<tr>
				<td>Distributor ID:</td>
				<td><input class="inputfield" name="distId" type="text"
					id="distributorId" readonly="readonly" value="${distId}" /></td>
			</tr>
			<tr>
				<td>Mobile NO.:</td>
				<td><input class="inputfield" name="mobileNumber" type="text"
					id="mobileno" readonly="readonly" value="${mobileNumber}" /></td>
			</tr>
			<tr>
				<td>Firm Name:</td>
				<td><input type="text" name="firmName" id="firmName"
					readonly="readonly" value="${firmName}" /></td>
			</tr>
			<tr>
				<td>Transfer Amount:</td>
				<td><input class="inputfield" name="amount" type="text" required="required"
					id="transferamount" /></td>
			</tr>
			<tr>
				<td colspan="2"><input class="button" type="submit" value="submit" />
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="search_panel1">
	<h1>Transfer To Distributor Business Balance</h1>
	<form method="post" action="transfertodistributorB2b" onsubmit="return fundTransferFormValidation();">
		<table>

			<tr>
				<td>Distributor ID:</td>
				<td><input class="inputfield" name="distId" type="text"
					id="distributorId" readonly="readonly" value="${distId}" /></td>
			</tr>
			<tr>
				<td>Mobile NO.:</td>
				<td><input class="inputfield" name="mobileNumber" type="text"
					id="mobileno" readonly="readonly" value="${mobileNumber}" /></td>
			</tr>
			<tr>
				<td>Transfer Amount:</td>
				<td><input class="inputfield" name="amount" type="text" required="required"
					id="transferamount" /></td>
			</tr>
			<tr>
				<td colspan="2"><input class="button" type="submit" value="submit" />
				</td>
			</tr>
		</table>
	</form>
</div>





