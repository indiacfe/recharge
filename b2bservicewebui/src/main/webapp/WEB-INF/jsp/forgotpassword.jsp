<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<spring:url value="/loginstyle" var="css_url" />
<spring:url value="/loginstyle" var="js_url" />
<spring:url value="/" var="base_url" />
<head>
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
					<h2>Reset Password</h2>
				</div>
				<div class="name-text">
					<c:if test="${not empty message}">
						<label for="exampleInputEmail1">${message} &nbsp; <br />
							<br /> Please login <a href="${base_url}"><font size="+2"
								color="black" style="text-decoration: underline">Here</font></a>
							again with new password.
						</label>
						<br />
						<br />

					</c:if>
				</div>

				<form class="form-horizontal" id="form" name="form" method="post"
					action=resetpassword " role="form">
					<c:if test="${empty message}">
					<div class="name-text">
						

							<label for="exampleInputEmail1">User Id/Mobile No. </label>
							<input type="text" name='userId' class="form-input-text"
								placeholder="Mobile No/User ID" />

						
					</div>
					<div class="feature-form-control">
						<div class="login-button">
							<input type="submit" value="Reset Password" />
						</div>
						<div class="clearfixer"></div>
					</div>
					</c:if>
				</form>
				<div class="clearfixer"></div>
			</div>
			<div class="clearfixer"></div>
		</div>
		<div class="clearfixer"></div>
	</section>

	<div class="login-footer-bottom">
		<div class="container">
			<div class="footer-para text-center">
				<p>Copyright &copy; cybertel.com All Right Reserved</p>
			</div>
		</div>
	</div>
</body>
</html>


