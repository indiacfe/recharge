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
<div id="search_panel1">
	<h1>Fund Transfer To Retailer</h1>
	<div style="color: red; left-padding: 80px;">${error}</div>
	<br />
	<form method="post" action="transfertofranchisee"
		onsubmit="return fundTransferFormValidation();">
		<table>
			<tr>
				<td>Retailer ID:</td>
				<td><input type="text" name="franchId" id="franchiseeId"
					readonly="readonly" value="${franchiseeId}" /></td>
			</tr>
			<tr>
				<td>Mobile NO.:</td>
				<td><input type="text" name="mobileNo" id="mobileno"
					readonly="readonly" value="${mobileNumber}" /></td>
			</tr>
			<tr>
				<td>Firm Name:</td>
				<td><input type="text" name="firmName" id="firmName"
					readonly="readonly" value="${firmName}" /></td>
			</tr>
			<tr>
				<td>Retailer Balance:</td>
				<td><input type="text" name="retailerBal"
					placeholder="retailerBal" id="retailerBal" required="required"
					value="${retailerBal}" /></td>
			</tr>
			<tr>
				<td>Transfer Amount:</td>
				<td><input type="text" name="amount" placeholder="amount"
					id="transferamount" required="required" value="${amount}" /></td>
			</tr>

			<tr>
				<td colspan="2"><input class="button" type="submit"
					value="submit" id="submit" /></td>
			</tr>
		</table>
	</form>
</div>



<%-- <div id="search_panel1">
        <h1>Fund Transfer To Franchisee</h1>
        <form method="post" action="transfertofranchisee">
          <div class="search_panel_box">
            <div class="form_row">
          <label>Franchisee ID:</label></br>
          <input class="inputfield" name="franchId" type="text" id="franchiseeId" readonly value="${franchiseeId}"/>
        </div> 
         <div class="form_row">
          <label>Mobile NO.:</label></br>
          <input class="inputfield" name="mobileNo" type="text" id="mobileno" readonly value="${mobileNumber}"/>
        </div> 
          </div>
          <label>Transfer Amount:</label></br>
          <input class="inputfield" name="amount" type="text" id="transferamount"/></br>
         
             <input type="submit" value="ok"/>
           
            
         
        </form>
      </div>  --%>