<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="login_banner_panel">

	<div id="paginate-slider2" class="pagination">
		<a href="" class="toc"></a> <a href="" class="toc anotherclass"></a> <a
			href="" class="toc"></a>
	</div>
	<div id="slider2" class="sliderwrapper">
		<div class="contentdiv">
			<img src="images/image_12.jpg" alt="" />
		</div>
		<div class="contentdiv">
			<img src="images/image_13.jpg" alt="" />
		</div>
		<div class="contentdiv">
			<img src="images/image_14.jpg" alt="" />
		</div>
	</div>
	<div id="login_panel">
		<h1>Login</h1>
		<form id="form" name="form" method="post"
			action="<c:url value='j_spring_security_check'/>">
			<div class="form_row">
				<label>Login As</label> <select name="category">
					<option value="-1">Select</option>
					<option value="ROLE_DISTRIBUTOR">Distributor</option>
					<option value="ROLE_EMPLOYEE">Employee</option>
					<option value="Retailer">Master Distributor</option>
					<option value="ROLE_FRANCHISEE">Retailer</option>
				</select>
			</div>
			<div class="form_row">
				<label>User Name</label> <input type="text" name='j_username'
					id="textfield" class="inputfield"
					value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
			</div>
			<div class="form_row">
				<label>Password </label> <input type="password" name='j_password'
					id="textfield2" class="input" />
			</div>
			<table width="100%">
				<tr>
					<td><a href="#">Forgot Password?</a></td>
					<td><input class="button" type="submit" name="Submit"
						value="Login" /></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		featuredcontentslider.init({
			id : "slider2",
			contentsource : [ "inline", "" ],
			toc : "markup",
			nextprev : [ "Previous", "Next" ],
			revealtype : "click",
			enablefade : [ true, 0.2 ],
			autorotate : [ true, 3000 ],
			onChange : function(previndex, curindex) {
			}
		});
	</script>
</div>	

<div id="banner_panel">
	<h1>News</h1>
		<marquee height="50px" style="padding: 5px;" behavior="scroll"
			loop="true" direction="right" scrollamount="1">
			<div id="divnews">Dear Retailers, Please change your password now due to security reasons.</div>
		</marquee>
	</div>
