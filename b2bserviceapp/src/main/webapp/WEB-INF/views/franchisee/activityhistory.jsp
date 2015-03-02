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
    </script>
<div id="search_panel1">
        <h1>Activity History</h1>
        <form:form method="get" action=""  onSubmit="return contactVali();">
          <div class="search_panel_box">
          
            
           <div class="form_row">
          <label>From Date</label>
          <form:input class="inputfield" required="required" path="fromDate" type="text" id="fromdate"></form:input>
        </div>
             <div class="form_row">
          <label>To Date</label>
          <form:input class="inputfield" required="required" path="toDate" type="text" id="todate"></form:input>
        </div>
            
          </div>
    
            <input type="submit" required="required" class="button" value="View"/>
         
        </form:form>
      </div>

 
 


