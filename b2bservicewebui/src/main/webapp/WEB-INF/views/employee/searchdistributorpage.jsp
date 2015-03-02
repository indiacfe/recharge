<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      </div> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="grid_10">
	<div class="box round first">
		<h2>Search Distributor Id</h2>
		<div class="block ">
			<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
			</c:if>
			<form:form method="get" action="searchdistributor">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Enter Distributor ID/Mobile
									NO.</label></td>
							<td class="col2"><input class="inputfield"
								name="numberOrDistId" type="text" id="distributorId" /></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="Search" />
				<input type="submit" class="button" value="See All" />
			</form:form>
		</div>
	</div>
</div>