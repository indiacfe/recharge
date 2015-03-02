<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      </div> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="grid_10">
	<div class="box round first">
		<h2>Fund Transfer</h2>
		<div class="block ">
			
			<form:form method="get" action=""  onSubmit="return contactVali();">
				<table class="form">
					<tbody>
						<tr>
							<td class="col1"><label>Enter Retailer ID/Mobile NO.</label></td>
							<td class="col2"><form:input path="retailerId" class="inputfield"></form:input></td>
						</tr>
						<tr>
							<td class="col1"><label></label></td>
							<td><form:input path="retailerName" class="inputfield"></form:input></td>
						</tr>
						<tr>
							<td><label>Amount (Rs.)</label></td>
							<td><form:input path="amount" class="inputfield"></form:input></td>
						</tr>

					</tbody>
				</table>
				<input type="submit"  class="button" value="Submit"/>
			</form:form>
		</div>
	</div>
</div>