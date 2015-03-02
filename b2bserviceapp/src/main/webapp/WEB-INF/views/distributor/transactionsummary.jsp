<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script type="text/javascript">
$(function() {
	$( "#fromdate" ).datepicker();
	});
	
$(function() {
	$( "#todate" ).datepicker();
	});  
	
	function selectWallet(){
		
		var wallet=document.getElementById("walletType").value;
		if(wallet=="-1"){
			alert("Pls select the wallet Type");
			/* document.getElementById("walletType").focus(); */
			return false;
		}
	}
	
    </script>
<div id="search_panel1">
        <h1>Transaction Summary</h1>
        <form:form method="get" action=""  onSubmit="return selectWallet();">
          <div class="search_panel_box">
            <div class="form_row">
              <label>Wallet Type</label>
              <form:select path="walletType" id="walletType">
               <form:option value="-1">Select</form:option>
                <form:option value="mobile">Mobile</form:option>
                <form:option value="businessLoad">Business Load</form:option>
             </form:select>
              </div>
            <div class="form_row">
          <label>From Date</label>
          <form:input class="inputfield" required="required" path="fromDate" type="text" id="fromdate"></form:input>
        </div>
           <div class="form_row">
          <label>To Date</label>
          <form:input class="inputfield" required="required" path="toDate" type="text" id="todate"></form:input>
        </div>
            
          </div>
    
            <input type="submit" required="required" class="button" value="Search"/>
         
        </form:form>
      </div>

 
 


