<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function checkLength() {
	var id = document.getElementById("distributorId").value;
	if (id.length != 10) {
		alert("pls enter 10 digit Distributor Id/MobileNo");
		document.getElementById("distributorId").focus();
		return false;
	}
}
</script>
<div id="search_panel1">
        <h1>Search Distributor</h1>
        <h2>${error}</h2>
        <form:form method="POST" action="searchdistributor">
          <div class="search_panel_box">
            <div class="form_row">
          <label>Enter Distributor ID/Mobile NO.</label>
          <input class="inputfield" name="numberOrDistId" type="text" id="distributorId" required="required"/>
        </div> 
          </div>
             
             <input type="submit"  class="button" value="Search"/>
            <input type="submit"  class="button" value="See All"/>
            
         
        </form:form>
      </div>