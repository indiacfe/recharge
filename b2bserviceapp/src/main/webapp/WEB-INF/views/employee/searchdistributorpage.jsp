<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="search_panel1">
        <h1>Search distributorid</h1>
        <form:form method="get" action="searchdistributor">
          <div class="search_panel_box">
            <div class="form_row">
          <label>Enter Distributor ID/Mobile NO.</label>
          <input class="inputfield" name="numberOrDistId" type="text" id="distributorId"/>
        </div> 
          </div>
             
             <input type="submit"  class="button" value="Search"/>
            <input type="submit"  class="button" value="See All"/>
            
         
        </form:form>
      </div>