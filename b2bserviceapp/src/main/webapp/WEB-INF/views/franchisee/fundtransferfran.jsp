<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
		
       
    </script>
<div id="search_panel1">
        <h1>Fund Transfer</h1>
        <form:form method="get" action=""  onSubmit="return contactVali();">
          <div class="search_panel_box">
            
            <div class="form_row">
          <label>Enter Retailer ID/Mobile NO.</label>
         <form:input path="retailerId" class="inputfield"></form:input>
        </div>
        
        <div class="form_row">
          
         <form:input path="retailerName" class="inputfield"></form:input>
        </div>
        
         <div class="form_row">
          <label>Amount(Rs.)</label>
         <form:input path="amount" class="inputfield"></form:input>
        </div>  
          </div>
    
            <input type="submit"  class="button" value="Submit"/>
            
         
        </form:form>
      </div>