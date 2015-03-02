<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="search_panelD">
        <h1>Fund Transfer Report</h1>
        <form:form method="get" action="curr"  >
          //<div class="search_panel_box">
           <div class="form_row">
          <label>francisee ID:</label>
          <form:input class="inputfield" required="required"  type="text" ></form:input>
       
          </div>//
          
            
            <input type="submit" required="required" class="button" value="View"/>
         
        </form:form>
      </div>
 --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="grid_10">
	<div class="box round first">
		<h2>Fund Transfer Report</h2>
		<div class="block ">

			<form:form method="get" action="curr"  >
			<input type="submit" required="required" class="button" value="View"/>
			</form:form>
		</div>
	</div>
</div>
 