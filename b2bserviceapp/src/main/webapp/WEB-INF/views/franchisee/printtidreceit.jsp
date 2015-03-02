<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<div id="search_panel1">
        <h1>Generate Transaction Receipt</h1>
        <form:form method="get" action=""  onSubmit="return contactVali();">
          <div class="search_panel_box">
          
            
           <div class="form_row">
          <label>TID No.</label>
          <form:input class="inputfield" required="required" path="tidNo" type="text"></form:input>
        </div>
            
            
          </div>
    
            <input type="submit" required="required" class="button" value="View"/>
         
        </form:form>
      </div>

 
 


