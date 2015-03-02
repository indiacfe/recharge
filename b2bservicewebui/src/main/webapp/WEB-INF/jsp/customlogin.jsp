<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<spring:url value="/loginstyle" var="css_url" />
<spring:url value="/loginstyle" var="js_url" />
<spring:url value="/" var="base_url" />
<head>
<script type="text/javascript">
	function goBack() {
		window.history.back();
	}
</script>
<title>Cybertel</title>
<link href="${css_url}/css/style1.css" rel="stylesheet" type="text/css" />
<link href="${css_url}/css/font-awesome.min.css" rel="stylesheet">
<body>

	<div class="main">
		<div class="container">
			<div class="main-heading text-center">
				<a href="index.html"><img src="${base_url}img/logo.png"></a>
			</div>
			<div class="main-heading text-left">
				<a style="color:#fff; text-decoration:none;font-family: OpenSans;" href="index.html">Home</a>
			</div>
		</div>
	</div>


	<section class="features-login">
		<div class="container">


			<div class="feature-login-form">
				<div class="feature-login-head">
					<h2>Login</h2>
				</div>
				<form class="form-horizontal" id="form" name="form" method="post"
					action="<c:url value='j_spring_security_check'/>" role="form">

					<div class="name-select">
						<label for="inputSelect" class="text-color-white">Select</label> <select
							class="select" name="category">
							<option value="-1">Select</option>
							<option value="ROLE_DISTRIBUTOR">Distributor</option>
							<!-- <option value="ROLE_EMPLOYEE">Employee</option> -->
							<option value="ROLE_FRANCHISEE">Retailer</option>
						</select>
					</div>
					<div class="name-text">
						<label for="exampleInputEmail1" class="text-color-white">Username</label>
						<input type="text" name='j_username'
							value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'
							class="form-input-text" placeholder="Mobile No/ID" />
					</div>
					<div class="pass-text">
						<label for="exampleInputPassword1" class="text-color-white">Password</label>
						<input type="password" class="form-input-password"
							placeholder="Password" name='j_password' />
					</div>
					<div class="feature-form-control">
						<div class="login-button">
							<!-- <button type="submit">Login</button>
							 -->
							<input type="submit" value="submit" />

						</div>
						<div class="forgat-anchor">
							<a href="forgot"><strong>Forget Password?</strong></a>
						</div>
						<div class="clearfixer"></div>
					</div>
				</form>
				<div class="clearfixer"></div>
			</div>
			<div class="clearfixer"></div>
		</div>
		<div class="clearfixer"></div>
	</section>

	<div class="main-heading text-center bck-btn">
		<input type="submit" value="Back" onclick="goBack();" />
	</div>

	<div class="login-footer-bottom">
		<div class="container">
			<div class="footer-para text-center">
				<p>Copyright &copy; cybertel.com All Right Reserved</p>
			</div>
		</div>
	</div>
</body>
</html>
 --%>