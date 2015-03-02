<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="search_panel1">
        <h1>Update Company Account</h1>
        <form:form method="post" action="companyaccountupdate" modelAttribute="companyaccount">
          <div class="search_panel_box">
            <div class="form_row">
              <label>Enter the Amount</label>
             <form:input path="companyAmount" required="required"/>
             </div>
            </div>
    		<input type="submit" class="button" value="Update"/>
             </form:form>
      </div>

<div id="search_panel1">
        <h1>Set the commission for distributor</h1>
        <form:form method="post" action="distributoCommissionupdate"  modelAttribute="distributoCommission">
          <div class="search_panel_box">
            <div class="form_row">
              <label>Distributor Id:</label>
              <form:input path="distributorId" required="required"/>
              </div>
              <div class="form_row">
              <label>Amount:</label>
              <form:input path="amount" required="required"/>
              </div>
            </div>
    		<input type="submit" class="button" value="Update"/>
             </form:form>
      </div>
      <div id="search_panel1">
        <h1>Set the Operator commission</h1>
        <form:form method="get" action="operatorCommisionupdate"  modelAttribute="operatorCommision">
          <div class="search_panel_box">
             <div class="form_row">
              <label>Select Recharge Type:</label>
              <form:select path="rechargeType">
              <form:option value="-1">select</form:option>
              <form:option value="TELECOM">Telecom</form:option>
              <form:option value="DATACARD">Datacard</form:option>
              <form:option value="DTH">DTH</form:option>
              </form:select>
               </div>
               <div class="form_row">
              <label>Third Party Name:</label>
              <form:select path="operator">
              <form:option value="-1">select</form:option>
               <form:option value="payworld">Pay World</form:option>
               <form:option value="instantpay">Instant Pay</form:option>
              </form:select>
               </div>
            <div class="form_row">
              <label>Select Operators:</label>
              <form:select path="operator">
              <form:option value="-1">select</form:option>
           </form:select>
               </div>
            	 <div class="form_row">
              <label>Commission Amount:</label>
              <form:input path="amount"/>
              </div>
            </div>
    		<input type="submit" class="button" value="Update"/>
             </form:form>
      </div>