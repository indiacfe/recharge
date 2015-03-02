<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">


</script>
<div class="grid_10">
	<div class="box round first">
		<h2>MONEY TRANSFER HOME</h2>
		<div class="block ">
		<form method="post" action="moneytransferregistration"> 
				<c:if test="${not empty alert}">
				<div class="message info">
					<h5>Information</h5>
					<p>${alert}.</p>
				</div>
				</c:if>
				<div class="box round first">
				<p style="font-size: 19px;">Note: Registered User need to log-in for any transaction</p>
				<table>
				<tr>
				<td><a href="moneytransferregistration"><input type="button" value="REGISTRATION"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
				<td><a href="forgotpin"><input type="button" value="FORGOT PIN"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
				<td><a href="moneytransferlogin"><input type="button" value="LOGIN"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
				
				</tr>
				</table>
				</div>
				
				
			</form>
				</div>
			</div>
		</div>
	
