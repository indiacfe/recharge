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

<!-- Caption Style -->
<style>
.captionOrange,.captionBlack {
	color: #fff;
	font-size: 20px;
	line-height: 30px;
	text-align: center;
	border-radius: 4px;
}

.captionOrange {
	background: #EB5100;
	background-color: rgba(235, 81, 0, 0.6);
}

.captionBlack {
	font-size: 16px;
	background: #000;
	background-color: rgba(0, 0, 0, 0.4);
}

a.captionOrange,A.captionOrange:active,A.captionOrange:visited {
	color: #ffffff;
	text-decoration: none;
}

a.captionOrange:hover {
	color: #eb5100;
	text-decoration: underline;
	background-color: #eeeeee;
	background-color: rgba(238, 238, 238, 0.7);
}

.bricon {
	background: url(../img/browser-icons.png);
}

<
style> /*
            .jssorb01 div           (normal)
            .jssorb01 div:hover     (normal mouseover)
            .jssorb01 .av           (active)
            .jssorb01 .av:hover     (active mouseover)
            .jssorb01 .dn           (mousedown)
            */ .jssorb01 div,.jssorb01 div:hover,.jssorb01 .av {
	filter: alpha(opacity = 70);
	opacity: .7;
	overflow: hidden;
	cursor: pointer;
	border: #000 1px solid;
}

.jssorb01 div {
	background-color: gray;
}

.jssorb01 div:hover,.jssorb01 .av:hover {
	background-color: #d3d3d3;
}

.jssorb01 .av {
	background-color: #fff;
}

.jssorb01 .dn,.jssorb01 .dn:hover {
	background-color: #555555;
}
</style>
<style>
/* jssor slider arrow navigator skin 05 css */
/*
            .jssora05l              (normal)
            .jssora05r              (normal)
            .jssora05l:hover        (normal mouseover)
            .jssora05r:hover        (normal mouseover)
            .jssora05ldn            (mousedown)
            .jssora05rdn            (mousedown)
            */
.jssora05l,.jssora05r,.jssora05ldn,.jssora05rdn {
	position: absolute;
	cursor: pointer;
	display: block;
	background: url(${base_url}img/a17.png) no-repeat;
	overflow: hidden;
}

.jssora05l {
	background-position: -10px -40px;
}

.jssora05r {
	background-position: -70px -40px;
}

.jssora05l:hover {
	background-position: -130px -40px;
}

.jssora05r:hover {
	background-position: -190px -40px;
}

.jssora05ldn {
	background-position: -250px -40px;
}

.jssora05rdn {
	background-position: -310px -40px;
}
</style>
</style>
<!-- it works the same with all jquery version from 1.x to 2.x -->
<script type="text/javascript" src="${js_url}/jquery-1.9.1.min.js"></script>
<!-- use jssor.slider.mini.js (39KB) or jssor.sliderc.mini.js (31KB, with caption, no slideshow) or jssor.sliders.mini.js (26KB, no caption, no slideshow) instead for release -->
<!-- jssor.slider.mini.js = jssor.sliderc.mini.js = jssor.sliders.mini.js = (jssor.core.js + jssor.utils.js + jssor.slider.js) -->
<script type="text/javascript" src="${js_url}/jssor.core.js"></script>
<script type="text/javascript" src="${js_url}/jssor.utils.js"></script>
<script type="text/javascript" src="${js_url}/jssor.slider.js"></script>
<script>
	jQuery(document)
			.ready(
					function($) {
						//Reference http://www.jssor.com/development/slider-with-slideshow-jquery.html
						//Reference http://www.jssor.com/development/tool-slideshow-transition-viewer.html

						var _SlideshowTransitions = [
								//Swing Outside in Stairs
								{
									$Duration : 1200,
									$Delay : 20,
									$Cols : 8,
									$Rows : 4,
									$Clip : 15,
									$During : {
										$Left : [ 0.3, 0.7 ],
										$Top : [ 0.3, 0.7 ]
									},
									$FlyDirection : 9,
									$Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
									$Assembly : 260,
									$Easing : {
										$Left : $JssorEasing$.$EaseInWave,
										$Top : $JssorEasing$.$EaseInWave,
										$Clip : $JssorEasing$.$EaseOutQuad
									},
									$ScaleHorizontal : 0.2,
									$ScaleVertical : 0.1,
									$Outside : true,
									$Round : {
										$Left : 1.3,
										$Top : 2.5
									}
								}

								//Dodge Dance Outside out Stairs
								,
								{
									$Duration : 1500,
									$Delay : 20,
									$Cols : 8,
									$Rows : 4,
									$Clip : 15,
									$During : {
										$Left : [ 0.1, 0.9 ],
										$Top : [ 0.1, 0.9 ]
									},
									$SlideOut : true,
									$FlyDirection : 9,
									$Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
									$Assembly : 260,
									$Easing : {
										$Left : $JssorEasing$.$EaseInJump,
										$Top : $JssorEasing$.$EaseInJump,
										$Clip : $JssorEasing$.$EaseOutQuad
									},
									$ScaleHorizontal : 0.3,
									$ScaleVertical : 0.3,
									$Outside : true,
									$Round : {
										$Left : 0.8,
										$Top : 2.5
									}
								}

								//Dodge Pet Outside in Stairs
								,
								{
									$Duration : 1500,
									$Delay : 20,
									$Cols : 8,
									$Rows : 4,
									$Clip : 15,
									$During : {
										$Left : [ 0.3, 0.7 ],
										$Top : [ 0.3, 0.7 ]
									},
									$FlyDirection : 9,
									$Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
									$Assembly : 260,
									$Easing : {
										$Left : $JssorEasing$.$EaseInWave,
										$Top : $JssorEasing$.$EaseInWave,
										$Clip : $JssorEasing$.$EaseOutQuad
									},
									$ScaleHorizontal : 0.2,
									$ScaleVertical : 0.1,
									$Outside : true,
									$Round : {
										$Left : 0.8,
										$Top : 2.5
									}
								}

								//Dodge Dance Outside in Random
								,
								{
									$Duration : 1500,
									$Delay : 20,
									$Cols : 8,
									$Rows : 4,
									$Clip : 15,
									$During : {
										$Left : [ 0.3, 0.7 ],
										$Top : [ 0.3, 0.7 ]
									},
									$FlyDirection : 9,
									$Easing : {
										$Left : $JssorEasing$.$EaseInJump,
										$Top : $JssorEasing$.$EaseInJump,
										$Clip : $JssorEasing$.$EaseOutQuad
									},
									$ScaleHorizontal : 0.3,
									$ScaleVertical : 0.3,
									$Outside : true,
									$Round : {
										$Left : 0.8,
										$Top : 2.5
									}
								}

								//Flutter out Wind
								,
								{
									$Duration : 1800,
									$Delay : 30,
									$Cols : 10,
									$Rows : 5,
									$Clip : 15,
									$During : {
										$Left : [ 0.3, 0.7 ],
										$Top : [ 0.3, 0.7 ]
									},
									$SlideOut : true,
									$FlyDirection : 5,
									$Reverse : true,
									$Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
									$Assembly : 2050,
									$Easing : {
										$Left : $JssorEasing$.$EaseInOutSine,
										$Top : $JssorEasing$.$EaseOutWave,
										$Clip : $JssorEasing$.$EaseInOutQuad
									},
									$ScaleHorizontal : 1,
									$ScaleVertical : 0.2,
									$Outside : true,
									$Round : {
										$Top : 1.3
									}
								}

								//Collapse Stairs
								,
								{
									$Duration : 1200,
									$Delay : 30,
									$Cols : 8,
									$Rows : 4,
									$Clip : 15,
									$SlideOut : true,
									$Formation : $JssorSlideshowFormations$.$FormationStraightStairs,
									$Assembly : 2049,
									$Easing : $JssorEasing$.$EaseOutQuad
								}

								//Collapse Random
								,
								{
									$Duration : 1000,
									$Delay : 80,
									$Cols : 8,
									$Rows : 4,
									$Clip : 15,
									$SlideOut : true,
									$Easing : $JssorEasing$.$EaseOutQuad
								}

								//Vertical Chess Stripe
								,
								{
									$Duration : 1000,
									$Cols : 12,
									$FlyDirection : 8,
									$Formation : $JssorSlideshowFormations$.$FormationStraight,
									$ChessMode : {
										$Column : 12
									}
								}

								//Extrude out Stripe
								,
								{
									$Duration : 1000,
									$Delay : 40,
									$Cols : 12,
									$SlideOut : true,
									$FlyDirection : 2,
									$Formation : $JssorSlideshowFormations$.$FormationStraight,
									$Assembly : 260,
									$Easing : {
										$Left : $JssorEasing$.$EaseInOutExpo,
										$Opacity : $JssorEasing$.$EaseInOutQuad
									},
									$ScaleHorizontal : 0.2,
									$Opacity : 2,
									$Outside : true,
									$Round : {
										$Top : 0.5
									}
								}

								//Dominoes Stripe
								,
								{
									$Duration : 2000,
									$Delay : 60,
									$Cols : 15,
									$SlideOut : true,
									$FlyDirection : 8,
									$Formation : $JssorSlideshowFormations$.$FormationStraight,
									$Easing : $JssorEasing$.$EaseOutJump,
									$Round : {
										$Top : 1.5
									}
								} ];

						var options = {
							$AutoPlay : true, //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
							$AutoPlaySteps : 1, //[Optional] Steps to go for each navigation request (this options applys only when slideshow disabled), the default value is 1
							$AutoPlayInterval : 4000, //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
							$PauseOnHover : 1, //[Optional] Whether to pause when mouse over if a slider is auto playing, 0 no pause, 1 pause for desktop, 2 pause for touch device, 3 pause for desktop and touch device, default value is 1

							$ArrowKeyNavigation : true, //[Optional] Allows keyboard (arrow key) navigation or not, default value is false
							$SlideDuration : 500, //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
							$MinDragOffsetToSlide : 20, //[Optional] Minimum drag offset to trigger slide , default value is 20
							//$SlideWidth: 600,                                 //[Optional] Width of every slide in pixels, default value is width of 'slides' container
							//$SlideHeight: 300,                                //[Optional] Height of every slide in pixels, default value is height of 'slides' container
							$SlideSpacing : 0, //[Optional] Space between each slide in pixels, default value is 0
							$DisplayPieces : 1, //[Optional] Number of pieces to display (the slideshow would be disabled if the value is set to greater than 1), the default value is 1
							$ParkingPosition : 0, //[Optional] The offset position to park slide (this options applys only when slideshow disabled), default value is 0.
							$UISearchMode : 1, //[Optional] The way (0 parellel, 1 recursive, default value is 1) to search UI components (slides container, loading screen, navigator container, arrow navigator container, thumbnail navigator container etc).
							$PlayOrientation : 1, //[Optional] Orientation to play slide (for auto play, navigation), 1 horizental, 2 vertical, 5 horizental reverse, 6 vertical reverse, default value is 1
							$DragOrientation : 3, //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)

							$SlideshowOptions : { //[Optional] Options to specify and enable slideshow or not
								$Class : $JssorSlideshowRunner$, //[Required] Class to create instance of slideshow
								$Transitions : _SlideshowTransitions, //[Required] An array of slideshow transitions to play slideshow
								$TransitionsOrder : 1, //[Optional] The way to choose transition to play slide, 1 Sequence, 0 Random
								$ShowLink : true
							//[Optional] Whether to bring slide link on top of the slider when slideshow is running, default value is false
							},

							$BulletNavigatorOptions : { //[Optional] Options to specify and enable navigator or not
								$Class : $JssorBulletNavigator$, //[Required] Class to create navigator instance
								$ChanceToShow : 2, //[Required] 0 Never, 1 Mouse Over, 2 Always
								$AutoCenter : 0, //[Optional] Auto center navigator in parent container, 0 None, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
								$Steps : 1, //[Optional] Steps to go for each navigation request, default value is 1
								$Lanes : 1, //[Optional] Specify lanes to arrange items, default value is 1
								$SpacingX : 10, //[Optional] Horizontal space between each item in pixel, default value is 0
								$SpacingY : 10, //[Optional] Vertical space between each item in pixel, default value is 0
								$Orientation : 1
							//[Optional] The orientation of the navigator, 1 horizontal, 2 vertical, default value is 1
							},

							$ArrowNavigatorOptions : {
								$Class : $JssorArrowNavigator$, //[Requried] Class to create arrow navigator instance
								$ChanceToShow : 2
							//[Required] 0 Never, 1 Mouse Over, 2 Always
							}
						};

						var jssor_slider2 = new $JssorSlider$(
								"slider2_container", options);

						//responsive code begin
						//you can remove responsive code if you don't want the slider scales while window resizes
						function ScaleSlider() {
							var parentWidth = jssor_slider2.$Elmt.parentNode.clientWidth;
							if (parentWidth)
								jssor_slider2.$SetScaleWidth(Math.min(
										parentWidth, 1170));
							else
								window.setTimeout(ScaleSlider, 30);
						}

						ScaleSlider();

						if (!navigator.userAgent
								.match(/(iPhone|iPod|iPad|BlackBerry|IEMobile)/)) {
							$(window).bind('resize', ScaleSlider);
						}

						//if (navigator.userAgent.match(/(iPhone|iPod|iPad)/)) {
						//    $(window).bind("orientationchange", ScaleSlider);
						//}
						//responsive code end
					});
</script>
</head>
<body>
	<div class="main">
		<div class="container">
			<div class="header-left">
				<a href="index.html"><img src="${base_url}img/logo.png"></a>
			</div>
			<div class="header-right">
				<div>
					<ul class="header-li">
						<li><i class="fa fa-phone"></i>
							+91 9310858478,9650720207,9811905405</li>
						<li><a href="mailto:info@cybertelindia.com"><i
								class="fa fa-envelope"></i> help@cybertelindia.com</a></li>
					</ul>
				</div>
				<div class="login">
					<div class="menu">
						<ul class="menu-li">
							<li><a href="${base_url}">Home</a></li>
							<li><a href="aboutcybertel">About Us</a></li> 
							<li><a href="faqcybertel">Faq</a></li>
							<li><a href="contactus">Contact Us</a></li>
							 
						</ul>
					</div>
					<!-- <span class="logindata"><a href="admincustomlogin"><i
							class="fa fa-lock"></i>Admin Login</a></span> -->
				</div>
			</div>
		</div>
	</div>
	<div class="main1">

		<div class="container">


			<div class="cont-num" style="color: blue;">
				<marquee direction="left"
					onmouseover="this.setAttribute('scrollamount', 0, 0);"
					onmouseout="this.setAttribute('scrollamount', 3, 0);"
					scrollamount="3">
					<b>NOTICE:-</b>Require super distributor, distributor &amp;
					retailer for all over india. kindly contact us at
					9310858478,9650720207,9268171171,9811905405,8459505105.
				</marquee>
			</div>
			<div class="row">
				<!-- Jssor Slider Begin -->
				<!-- You can move inline styles to css file or css block. -->
				<div class="col-md-8">
					<div id="slider2_container"
						style="position: relative; width: 1170px; height: 485px;">

						<!-- Loading Screen -->
						<div u="loading" style="position: absolute; top: 0px; left: 0px;">
							<div
								style="filter: alpha(opacity = 70); opacity: 0.7; position: absolute; display: block; background-color: #000; top: 0px; left: 0px; width: 100%; height: 100%;">
							</div>
							<div
								style="position: absolute; display: block; background: url(${base_url}img/loading.gif) no-repeat center center; top: 0px; left: 0px; width: 100%; height: 100%;">
							</div>
						</div>

						<!-- Slides Container -->
						<div u="slides"
							style="cursor: move; position: absolute; left: 0px; top: 0px; width: 1170px; height: 485px; overflow: hidden;">
							<div>
								<a u=image href="#"><img
									src="${base_url}img/landscape/01.jpg" /></a>
							</div>
							<div>
								<a u=image href="#"><img
									src="${base_url}img/landscape/02.jpg" /></a>
							</div>
							<div>
								<a u=image href="#"><img
									src="${base_url}img/landscape/03.jpg" /></a>
							</div>
						</div>

						<!-- Bullet Navigator Skin Begin -->
						<!-- jssor slider bullet navigator skin 01 -->

						<!-- bullet navigator container -->
						<div u="navigator" class="jssorb01"
							style="position: absolute; bottom: 16px; right: 10px;">
							<!-- bullet navigator item prototype -->
							<div u="prototype"
								style="POSITION: absolute; WIDTH: 12px; HEIGHT: 12px;"></div>
						</div>
						<!-- Bullet Navigator Skin End -->

						<!-- Arrow Navigator Skin Begin -->

						<!-- Arrow Left -->
						<span u="arrowleft" class="jssora05l"
							style="width: 40px; height: 40px; top: 123px; left: 8px;">
						</span>
						<!-- Arrow Right -->
						<span u="arrowright" class="jssora05r"
							style="width: 40px; height: 40px; top: 123px; right: 8px">
						</span>
						<!-- Arrow Navigator Skin End -->
						<a style="display: none" href="http://www.jssor.com">slideshow</a>
					</div>
					<!-- Jssor Slider End -->
				</div>

				<div class="col-md-4">
					<div class="feature-login-form">
						<div class="feature-login-head">
							<h2>Login</h2>
						</div>
						<form style="padding-bottom:35px;" class="form-horizontal" id="form" name="form" method="post"
							action="<c:url value='j_spring_security_check'/>" role="form">

							<div class="name-select">
								<input type="hidden" name="category" value="ROLE_ADMIN" />
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
				</div>
			</div>
		</div>
		<div class="container">

			<div class="dis_box">
				<div class="dis-in">
					<div class="dis">
						<ul class="ul-img">
							<li><a href="#"><img
									src="${base_url}img/mob-recharge.png"></a></li>
							<li><a href="#"><img
									src="${base_url}img/datacard-recharge.png"></a></li>
							<li><a href="#"><img
									src="${base_url}img/dth-recharge.png"></a></li>
							<li><a href="#"><img src="${base_url}img/tr-booking.png"></a></li>
							<li><a href="#"><img src="${base_url}img/utility.png"></a></li>
							<li><a href="#"><img
									src="${base_url}img/entertainment.png"></a></li>
						</ul>
					</div>
					<div class="telecom">
						<ul class="telecom-li">
							<marquee onMouseOver="this.setAttribute('scrollamount', 0, 0);"
								onMouseOut="this.setAttribute('scrollamount', 3, 0);"
								scrollamount="4" direction="left" behavior="scroll">

								<li><a href="#"><img src="${base_url}img/aircel.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/airtel.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/cell.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/dis.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/mtnl.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/reliance.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/tata.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/vergin.jpg"></a></li>
								<li><a href="#"><img src="${base_url}img/voda.jpg"></a></li>
							</marquee>
						</ul>
					</div>
				</div>
				<div class="news_box">
					<h1>Latest News</h1>
					<ul>
						<marquee direction="up"
							onMouseOver="this.setAttribute('scrollamount', 0, 0);"
							onMouseOut="this.setAttribute('scrollamount', 3, 0);"
							scrollamount="3">
							<c:forEach var="news" items="${newses}">
								<li>${news.description}</li>
							</c:forEach>
						</marquee>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-down">
		<div class="container">
			<div class="footer-menu">
				<ul class="footer-ul">
					<li><a href="${base_url}">Home</a></li>
					<li><a href="aboutcybertel">About Us</a></li> 
					<li><a href="faqcybertel">Faq</a></li>
					<li><a href="contactus">Contact Us</a></li>
				</ul>
			</div>
			<div class="footer-right">
				<h3>Copyright &copy; cybertel.com All Right Reserved | Developed by <a href="http://www.cfeindia.com">CFE INDIA
		| </a> <strong>Call Us: +91 9310858478, +91 9811905405</strong><a
		href="https://www.facebook.com/sharer/sharer.php?u=http://www.cybertelindia.com/login"
		target="_blank">Facebook</a> <a
		href="https://twitter.com/intent/tweet?url=http://www.cybertelindia.com/login"
		target="_blank">Twitter</a> <a
		href="https://plus.google.com/share?url=http://www.cybertelindia.com/login"
		target="_blank">Google Plus</a> <a
		href="https://www.linkedin.com/cws/share?url=http://www.cybertelindia.com/login"
		target="_blank">LinkedIn</a></h3>
				
			</div>
		</div>
	</div>

</body>
</html>




<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- saved from url=(0042)https://suvidhaa.in/Login.aspx?ReturnUrl=/ -->
<html xmlns="http://www.w3.org/1999/xhtml">
<spring:url value="/loginstyle" var="css_url" />
<spring:url value="/loginstyle" var="js_url" />
<spring:url value="/" var="base_url" />
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Suvidhaa | Login</title>
<link type="text/css" rel="Stylesheet"
	href="${css_url}/HttpCombiner.css" />
<script src="${js_url}/HttpCombiner(1).js" type="text/javascript"></script>
<noscript>&lt;meta http-equiv="REFRESH" content="0;
	url=JavaScriptDisabled.aspx" /&gt;</noscript>

</head>
<body>
<div class="content"></div>
		<div align="center">
			<table width="1003" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tbody>
					<tr>
						<td valign="top">
							<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tbody>
									<tr>
										<td height="90" align="left"><img
											src="${base_url}img/cyber-2.png" alt="Suvidhaa.in Logo"
											width="203" height="85" /></td>
									</tr>
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tbody>
													<tr>
														<td width="14"><img src="${base_url}img/nav_left.gif"
															alt="" width="14" height="37" /></td>
														<td valign="middle"
															style="background-image: url('${base_url}img/nav_midd.gif'); background-repeat: repeat-x;">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tbody>
																	<tr>
																		<td width="3%">&nbsp;</td>
																		<td width="15%" align="center"><a href="#"
																			target="_blank" class="top_nav">Home</a></td>
																		<td width="1%" align="center" class="nav_line">|
																		</td>
																		<td width="15%" align="center"><a href="#"
																			target="_blank" class="top_nav">About Us</a></td>
																		<td width="1%" align="center" class="nav_line">|
																		</td>
																		<td width="14%" align="center"><a href="#"
																			target="_blank" class="top_nav"> Contact Us</a></td>
																		<td width="1%" align="center" class="nav_line">|
																		</td>
																		<td width="15%" align="center"><a href="#"
																			target="_blank" class="top_nav">FAQs</a></td>




																		<td width="3%">&nbsp;</td>
																	</tr>
																</tbody>
															</table>
														</td>
														<td width="14"><img
															src="${base_url}img/nav_right.gif" alt="" width="14"
															height="37" style="margin-left: 0;" /></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td valign="top" align="center">
											<table width="960" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tbody>
													<tr>
														<td width="575px" valign="top" style="padding-top: 7px;">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tbody>
																	<tr>
																		<td><img src="${base_url}img/merged.gif"
																			alt="Banner" width="575" height="317" /></td>
																	</tr>
																</tbody>
															</table>
														</td>
														<td width="23px"></td>
														<td width="362px" valign="top" align="center"
															style="background-image: url('${base_url}img/login_bg.gif'); background-repeat: no-repeat; background-position: top; height: 359px;">
															<form class="form-horizontal" id="form"
																							name="form" method="post"
																							action="<c:url value='j_spring_security_check'/>">
															<table width="320" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tbody>
																	<tr>
																		<td>&nbsp;</td>
																	</tr>
																	<tr>
																		<td align="center">
																			<table width="100%" border="0" align="center"
																				cellpadding="2" cellspacing="2"
																				style="margin-top: 31px;">
																				<tbody>
																					<tr>
																						<td colspan="3" align="left">
																							<div id="ContentPlaceHolder3_divError">

																								<div style="color: Red; font-size: 10px;">

																								</div>
																							</div>
																						</td>
																					</tr>
																					<tr>
																					<input type="hidden" name="category" value="ROLE_ADMIN"/> 
																						<td width="35%" align="left" class="login_text">
																							Mobile/ID No<label style="color: Red;">
																								*</label>
																						</td>
																						<td width="10%" align="center" class="login_text">
																							:</td>
																						<td align="left" width="45%"><input
																							type="text" name='j_username'
																							value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'
																							class="form_field" placeholder="Mobile No/ID" /></td>
																					</tr>
																					<tr>
																						<td align="left" class="login_text" colspan="3"
																							height="10"></td>
																					</tr>
																					<tr>
																						<td width="35%" align="left" class="login_text">
																							Password<label style="color: Red;"> *</label>
																						</td>
																						<td width="10%" align="center" class="login_text">
																							:</td>
																						<td align="left" width="45%"><input
																							type="password" class="form_field"
																							placeholder="Password" name='j_password' /></td>
																					</tr>
																					<tr>
																						<td colspan="3" align="left" height="2px;"></td>
																					</tr>
																					<tr>
																						<td align="right" class="login_text">&nbsp;</td>
																						<td align="center" class="login_text">&nbsp;
																						</td>
																						<td align="right"></td>
																					</tr>
																					<tr>
																						<td colspan="3" align="right"><a href="forgot"
																							id="" class="forgetpassword"> Forgot Password</a></td>
																					</tr>
																				</tbody>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td align="center" style="height: 2px;"><img
																			src="${base_url}img/login_sep_line.gif" alt=""
																			width="297" height="2" /></td>
																	</tr>
																	<tr>
																		<td align="center" height="3px;"></td>
																	</tr>
																	<tr>
																		<td align="center"><img
																			src="${base_url}img/become_partner.gif" alt=""
																			width="297" height="27" /></td>
																	</tr>
																	<tr>
																		<td height="3px;"></td>
																	</tr>
																	<tr>
																		<td align="center">
																			<table width="85%" border="0" align="center"
																				cellpadding="2" cellspacing="2">
																				<tbody>
																					<tr>
																						<td width="35%" align="right" class="login_text">
																							&nbsp;</td>
																						<td width="10%" align="center" class="login_text">
																							&nbsp;</td>
																						<td width="45%" align="right"><input
																							type="image" value="submit"  src="${base_url}img/signup.gif" /></td>
																					</tr>
																				</tbody>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="3"
																			style="color: #0152a3; font-family: Arial, Helvetica, sans-serif; font-size: 11px;">
																		</td>
																	</tr>
																</tbody>
															</table>
														</form>
														</td>
													</tr>


												</tbody>
											</table>
										</td>
									</tr>
									<tr>
										<td align="center">
											<table width="965" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tbody>
													<tr>
														<td>
															<table width="960" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tbody>
																	<tr>
																		<td width="155" class="servicepartnerleft">
																			&nbsp;</td>
																		<td
																			style="background-image: url('img/our_services_midd.gif'); background-repeat: repeat-x;">
																			<div id="slide1">
																				<div class="buttons">
																					<a href="#" id="prev">prev</a> <a href="#"
																						id="next">next</a>
																					<div class="clear"></div>
																				</div>
																				<div id="slides">
																					<ul style="left: 0px;">
																						<li><img src="${base_url}img/icicipru.jpg"
																							alt="icicipru" height="63" width="122" /></li>
																						<li><img src="${base_url}img/tataaia1.jpg"
																							alt="tata aia" height="63" width="122" /></li>
																						<li><img
																							src="${base_url}img/L-and-T-Insurance1.jpg"
																							alt="L-and-T-Insurance" height="63" width="122" /></li>
																						<li><img src="${base_url}img/bses.jpg"
																							alt="bses" height="63" width="122" /></li>
																						<li><img
																							src="${base_url}img/bses_rajdhani.jpg"
																							alt="bses_rajdhani" height="63" width="122" /></li>
																						<li><img src="${base_url}img/bses_yamuna.jpg"
																							alt="bses_yamuna" height="63" width="122" /></li>
																						<li><img src="${base_url}img/mseb.jpg"
																							alt="mseb" height="63" width="122" /></li>
																						<li><img
																							src="${base_url}img/reliance_eng.jpg"
																							alt="reliance_eng" height="63" width="122" /></li>
																						<li><img src="${base_url}img/npcl.jpg"
																							alt="npcl" height="63" width="122" /></li>

																						<li><img src="${base_url}img/dhbvn.jpg"
																							alt="dhbvn" height="63" width="122" /></li>
																						<li><img src="${base_url}img/best-logo1.jpg"
																							alt="" width="122" height="63" /></li>
																						<li><img src="${base_url}img/pspcl1.jpg"
																							alt="" width="122" height="63" /></li>
																						<li><img src="${base_url}img/cspdcl.gif"
																							alt="" width="122" height="63" /></li>
																						<li><img
																							src="${base_url}img/tata-power-logo1.jpg" alt=""
																							width="122" height="63" /></li>
																						<li><img src="${base_url}img/nesco.jpg"
																							alt="" width="122" height="63" /></li>
																						<li><img src="${base_url}img/wesco.jpg"
																							alt="" width="122" height="63" /></li>
																						<li><img src="${base_url}img/southco.jpg"
																							alt="" width="122" height="63" /></li>
																						<li><img src="${base_url}img/CESC1.jpg"
																							alt="" width="122" height="63" /></li>
																						<li><img src="${base_url}img/adanigas.jpg"
																							alt="adanigas" height="63" width="122" /></li>
																						<li><img
																							src="${base_url}img/mahanagar_gas.jpg"
																							alt="mahanagar_gas" height="63" width="122" /></li>
																						<li><img src="${base_url}img/gujratgas.jpg"
																							alt="gujratgas" height="63" width="122" /></li>
																						<li><img src="${base_url}img/redbus.jpg"
																							alt="redbus" height="63" width="122" /></li>
																						<li><img src="${base_url}img/ticketwala.jpg"
																							alt="ticketwala" height="63" width="122" /></li>
																						<li><img src="${base_url}img/makemytrip.jpg"
																							alt="makemytrip" height="63" width="122" /></li>
																						<li><img src="${base_url}img/kingfisher.jpg"
																							alt="kingfisher" height="63" width="122" /></li>
																						<li><img src="${base_url}img/airindia.jpg"
																							alt="airindia" height="63" width="122" /></li>
																						<li><img src="${base_url}img/indigo.jpg"
																							alt="indigo" height="63" width="122" /></li>
																						<li><img src="${base_url}img/go.jpg"
																							alt="spicejet" height="63" width="122" /></li>
																						<li><img src="${base_url}img/jetlete.jpg"
																							alt="jetlete" height="63" width="122" /></li>
																						<li><img src="${base_url}img/spicejet.jpg"
																							alt="spicejet" height="63" width="122" /></li>
																						<li><img src="${base_url}img/irctc.gif"
																							alt="IRCTC" height="63" width="103" /></li>
																						<li><img src="${base_url}img/bsnl.gif"
																							alt="BSNL" height="63" width="103" /></li>
																						<li><img src="${base_url}img/mtnl.gif"
																							alt="MTNL" height="63" width="92" /></li>
																						<li><img src="${base_url}img/vodafone.gif"
																							alt="VODAFONE" height="63" width="103" /></li>
																						<li><img src="${base_url}img/airtel.gif"
																							alt="AIRTEL" height="63" width="119" /></li>
																						<li><img src="${base_url}img/tataindicom.gif"
																							alt="tataindicom" height="63" width="119" /></li>
																						<li><img src="${base_url}img/cellone.gif"
																							alt="cellone" height="63" width="122" /></li>
																						<li><img
																							src="${base_url}img/reliance_comm.gif"
																							alt="reliance_comm" height="63" width="122" /></li>
																						<li><img src="${base_url}img/idea.gif"
																							alt="idea" height="63" width="122" /></li>
																						<li><img src="${base_url}img/aircel.gif"
																							alt="aircel" height="63" width="122" /></li>
																						<li><img src="${base_url}img/loop.jpg"
																							alt="LOOP" height="63" width="122" /></li>
																						<li><img src="${base_url}img/trump.jpg"
																							alt="trump" height="63" width="122" /></li>
																						<li><img src="${base_url}img/mts.jpg"
																							alt="mts" height="63" width="122" /></li>
																						<li><img src="${base_url}img/uninor1.jpg"
																							alt="uninor1" height="63" width="122" /></li>
																						<li><img src="${base_url}img/virgin1.jpg"
																							alt="virgin1" height="63" width="122" /></li>
																						<li><img src="${base_url}img/dish.jpg"
																							alt="dish" height="63" width="122" /></li>
																						<li><img src="${base_url}img/airtel1.jpg"
																							alt="airtel1" height="63" width="122" /></li>
																						<li><img src="${base_url}img/sun.jpg"
																							alt="sun" height="63" width="122" /></li>
																						<li><img src="${base_url}img/big.jpg"
																							alt="big" height="63" width="122" /></li>
																						<li><img src="${base_url}img/videocon1.jpg"
																							alt="videocon1" height="63" width="122" /></li>
																						<li><img src="${base_url}img/tata_sky.jpg"
																							alt="tata_sky" height="63" width="122" /></li>
																						<li><img src="${base_url}img/lic.jpg"
																							alt="lic" height="63" width="122" /></li>
																						<li><img src="${base_url}img/ing.jpg"
																							alt="ing" height="63" width="122" /></li>
																						<li><img src="${base_url}img/dlf.jpg"
																							alt="dlf" height="63" width="122" /></li>


																					</ul>
																					<div class="clear"></div>
																				</div>
																			</div>
																		</td>
																		<td width="11"><img
																			src="${base_url}img/our_services_right.gif" alt=""
																			width="11" height="83" style="margin-left: 0;" /></td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td
											style="background-image: url('/Store/Admin/Images/LoginPage/footer_bg.gif'); background-position: top; background-repeat: no-repeat; height: 50px;">
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2">
												<tbody>
													<tr>
														<td align="center" height="5px;"></td>
													</tr>
													<tr>
														<td align="center" class="text">© 2013 <span
															style="color: #a20b3f;">CyberTel India.</span>&nbsp;All
															Rights Reserved. Developed By <a href="www.cfeindia.com"><span
															style="color: #a20b3f;">CFE India.</span></a>
														</td>
													</tr>
													<tr>
														<td align="center" class="text"><a href="#"
															style="text-decoration: none; color: #3a82f4;">&nbsp;</a>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>

		</div>

</body>
</html> --%>