<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
$(function() {
	$( "#reciptDate" ).datepicker();
	});
       
    </script>
<div id="search_panel1">
        <h1>Cash Deposit Request</h1>
        <form:form method="POST" action="submitrequest">
          <div class="search_panel_box">
            <div class="form_row">
              <label>Request Type</label>
              <form:select path="cashDepositRequestType" >
                <form:option value="-1">Select</form:option>
                <form:option value="Mobile">Mobile</form:option>
                <form:option value="DTH">DTH</form:option>
                <form:option value="Datacard">Datacard</form:option>
              </form:select>
              </div>
              
               <div class="form_row">
              <label>Payment Mode</label>
              <form:select path="paymentMode" >
                <form:option value="-1">Select Mode</form:option>
                <form:option value="Cash In Bank">Cash in Bank</form:option>
                <form:option value="Cheque">Cheque</form:option>
                <form:option value="Demand Draft">Demand Draft</form:option>
                  <form:option value="NEFT/RTGS/Transfer">NEFT/RTGS/Transfer</form:option>
                   <form:option value="Cash In Office">Cash In Office</form:option>
              </form:select>
              </div>
         <div class="form_row">
          <label>Amount (Rs.)</label>
          <form:input class="inputfield" required="required" path="amount" type="text" id="amount"></form:input>
        </div>
        
        <div class="form_row">
              <label>Counter</label>
              <form:select path="counterBank"  required="required">
                <form:option value="-1">Select Bank</form:option>
                <form:option value="Axis Bank">Axis Bank</form:option>
                <form:option value="HDFC">HDFC Bank</form:option>
                <form:option value="ICICI">ICICI Bank</form:option>
                  <form:option value="Punjab National Bank">PNB Bank</form:option>
                   <form:option value="State Bank Of India">SBI Bank</form:option>
              </form:select>
              </div>
        
          <div class="form_row">
          <label>Bank Name</label>
          <form:input class="inputfield" path="bankName" type="text" id=""></form:input>
        </div>  
            
             <div class="form_row">
          <label>Receipt/Cheque/DD No.(1 for Cash deposited in Office)</label>
          <form:input class="inputfield" path="reciptNo" type="text" id="" required="required"></form:input>
        </div> 
            
             <div class="form_row">
          <label>Deposit Branch/Office</label>
          <form:input class="inputfield" path="depositBranch" type="text" id=""></form:input>
        </div>
            
             <div class="form_row">
          <label>Deposit/Cheque/DD Date</label>
          <form:input class="inputfield" path="reciptDate" type="text" id="reciptDate"  required="required"></form:input>
        </div>
            
             <div class="form_row">
          <label>Remark</label>
          <form:textarea class="inputfield" path="remark" type="text" id=""></form:textarea>
        </div>
         
          </div>
          <div class="form_row">
          <label>Upload Receipt</label>
          <input type="file"  name="uploadRecipt" style="height:24px;width:175px;"/>
          
        </div>
            <input type="submit" required="required" class="button" value="Submit" action="submitrequest"/>
         
        </form:form>
      </div>

  --%>
<!--new jap----------------------------------------------------------------->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$(function() {
		$("#reciptDate").datepicker();
	});
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Cash Deposit Request</h2>
		<div class="block ">
			<form:form method="POST" action="submitrequest">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Request Type</label></td>
							<td class="col2"><form:select path="cashDepositRequestType">
									<form:option value="-1">Select</form:option>
									<form:option value="Mobile">Mobile</form:option>
									<form:option value="DTH">DTH</form:option>
									<form:option value="Datacard">Datacard</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td><label>Payment Mode</label></td>
							<td><form:select path="paymentMode">
									<form:option value="-1">Select Mode</form:option>
									<form:option value="Cash In Bank">Cash in Bank</form:option>
									<form:option value="Cheque">Cheque</form:option>
									<form:option value="Demand Draft">Demand Draft</form:option>
									<form:option value="NEFT/RTGS/Transfer">NEFT/RTGS/Transfer</form:option>
									<form:option value="Cash In Office">Cash In Office</form:option>
								</form:select></td>
						</tr>
						<tr>
							<td><label>Amount (Rs.)</label></td>
							<td><form:input class="inputfield" required="required"
									path="amount" type="text" id="amount"></form:input></td>
						</tr>
						<tr>
							<td><label>Counter</label></td>
							<td><form:select path="counterBank"  required="required">
                <form:option value="-1">Select Bank</form:option>
                <form:option value="Axis Bank">Axis Bank</form:option>
                <form:option value="HDFC">HDFC Bank</form:option>
                <form:option value="ICICI">ICICI Bank</form:option>
                  <form:option value="Punjab National Bank">PNB Bank</form:option>
                   <form:option value="State Bank Of India">SBI Bank</form:option>
              </form:select></td>
						</tr>
						<tr>
							<td><label>Bank Name</label></td>
							<td><form:input class="inputfield" path="bankName" type="text" id=""></form:input></td>
						</tr>
						<tr>
							<td><label>Receipt/Cheque/DD No.(1 for Cash deposited in Office)</label></td>
							<td><form:input class="inputfield" path="reciptNo" type="text" id="" required="required"></form:input></td>
						</tr>
						<tr>
							<td><label>Deposit Branch/Office</label></td>
							<td><form:input class="inputfield" path="depositBranch" type="text" id=""></form:input></td>
						</tr>
						<tr>
							<td><label>Deposit/Cheque/DD Date</label></td>
							<td><form:input class="inputfield" path="reciptDate" type="text" id="reciptDate"  required="required"></form:input></td>
						</tr>
						<tr>
							<td><label>Remark</label></td>
							<td><form:textarea class="inputfield" path="remark" type="text" id=""></form:textarea></td>
						</tr>
						<tr>
							<td><label>Upload Receipt</label></td>
							<td><input type="file"  name="uploadRecipt" style="height:24px;width:175px;"/></td>
						</tr>

					</tbody>
				</table>
				 <input type="submit" required="required" class="button" value="Submit" action="submitrequest"/>
			</form:form>
		</div>
	</div>
</div>



