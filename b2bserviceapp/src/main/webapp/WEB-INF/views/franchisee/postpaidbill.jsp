<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function validateFormSubmit() {

		return (contactVali() && confirmation());
	}
	function confirmation() {
		var connection = document.getElementById("connectionid").value;
		var operator = document.getElementById("operator").value;
		var rAmount = document.getElementById("amount").value;
		var con = confirm("Recharge type: Postpaid or Landline Bill\n\nOperator: "
				+ operator + "\n\nMobile No. or Phone Number: " + connection + "\n\nAmount: "
				+ rAmount
				+ "\n\nAre you sure you want to pay bill to the mobile no. or Phone Number?");
		if (con == true) {
			return true;
		} else {

			return false;
		}
	}
	function contactVali() {
		var connection = document.getElementById("connectionid").value;
		var amt = document.getElementById("amount").value;
		var operator = document.getElementById("operator").value;
		var rechargeType = document.getElementById("rechargeType").value;
		if (rechargeType == -1) {
			alert("pls select the recharge type");
			document.getElementById("rechargeType").focus();
			return false;
		}
		if (connection.length == 10) {
			if (isNaN(connection)) {

				alert("pls enter a Number in Mobile no.");
				document.getElementById("mobileNo").focus();
				return false;
			}
		} 
	
		if (isNaN(amt)) {
			alert("pls enter a Number in  Amount");
			document.getElementById("amount").focus();
			return false;
		}
		if (operator == -1) {
			alert("pls select the operator");
			document.getElementById("operator").focus();
			return false;
		}
		return true;

	}
</script>

<div id="search_panel1">
        <h1>Post Paid Bill Payment</h1>
        	<h3>Your Current Balance is Rs.
		${franchiseeInfo.b2bCurrentBalance}</h3>
        <form:form method="POST" action="postpaidbillsubmit"  onSubmit="return validateFormSubmit()" modelAttribute="postpaidbean">
        <br />
		<div class="form_row">
			<label>Service Type</label>
			<form:select path="rechargeType" id="rechargeType">
				<form:option value="MOBILE_POSTPAID">MOBILE POSTPAID</form:option>
			</form:select>
		</div>
        <div style="color: red; left-padding: 80px;">${Error}</div>
          <div class="search_panel_box">
            <div class="form_row">
              <label>Operator</label>
              <form:select path="operator">
                <form:option value="-1" selected="selected">Select</form:option>
				<option value="AIRTEL">AIRTEL</option>
				<option value="IDEA">IDEA</option>
				<option value="MTNL DELHI">MTNL DELHI</option>
				<option value="RELIANCE CDMA">RELIANCE CDMA</option>
				<option value="RELIANCE GSM">RELIANCE GSM</option>
				<option value="TATA DOCOMO CDMA">TATA DOCOMO CDMA</option>
				<option value="TATA DOCOMO GSM">TATA DOCOMO GSM</option>
				<option value="VODAFONE">VODAFONE</option>
              </form:select>
              </div>
              <div class="form_row">
          <label>Mobile Number or Phone Number</label>
              <form:input class="inputfield" required="required" path="connectionid" type="text" id="connectionid"></form:input>
              </div>
           	<div class="form_row">
	          <label>Amount (Rs.)</label>
	          <form:input class="inputfield" required="required" path="amount" type="text" id="amount"/>
	        </div>
	         <input type="submit" name="submit"  class="button" value="Pay Now"/>          
            
          </div>
         
        </form:form>
      </div>
      
      <script type="text/javascript">
		function clearAmount() {
			document.getElementById("amount").value = "";
		}
		clearAmount();
	</script>