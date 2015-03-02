<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

function checkPassword(){
	
	var newpass=document.getElementById("newPassword").value;
	var confirmpass=document.getElementById("confirmPassword").value;
	if(newpass==confirmpass){
		
		return true;
	}
	else{
		alert("New & confirm password must be same");
		document.getElementById("confirmPassword").focus();
		return false;
	}
	
	
}
    </script>
<div id="search_panel1">
        <h1>Change Password</h1>
        <h4>${error}</h4>
        <form:form method="post" action="changepasswordprocessfran"  onSubmit="return checkPassword();" modelAttribute="changepass">
          <div class="search_panel_box">
            
            <div class="form_row">
          <label>Old Password</label>
          <form:password class="inputfield" required="required" path="oldPassword" id="oldPassword"></form:password>
        </div>
            
            <div class="form_row">
          <label>New Password</label>
          <form:password class="inputfield" required="required" path="newPassword"  id="newPassword"></form:password>
        </div>
        
           <div class="form_row">
          <label>Confirm Password</label>
          <form:password class="inputfield" required="required" path="confirmPassword" id="confirmPassword"></form:password>
        </div>
            
          </div>
    
            <input type="submit" required="required" class="button" value="Change"/>
         
        </form:form>
      </div>

 
 
