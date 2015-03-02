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
</style>
<!-- it works the same with all jquery version from 1.x to 2.x -->
<script type="text/javascript" src="${js_url}/jquery-1.9.1.min.js"></script>
<!-- use jssor.slider.mini.js (39KB) or jssor.sliderc.mini.js (31KB, with caption, no slideshow) or jssor.sliders.mini.js (26KB, no caption, no slideshow) instead for release -->
<!-- jssor.slider.mini.js = jssor.sliderc.mini.js = jssor.sliders.mini.js = (jssor.core.js + jssor.utils.js + jssor.slider.js) -->


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
						<li><i class="fa fa-phone"></i> 011-123312341</li>
						<li><a href="mailto:info@cybertel.com"><i
								class="fa fa-envelope"></i> Info@cybertel.com</a></li>
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
				</div>
			</div>
		</div>
	</div>
	<div class="main1">

		<div class="container">


			<div class="cont-num" style="color: blue; ">
				<marquee direction="left"
					onmouseover="this.setAttribute('scrollamount', 0, 0);"
					onmouseout="this.setAttribute('scrollamount', 3, 0);"
					scrollamount="3">
					<b>NOTICE:-</b>Require super distributor, distributor &amp;
					retailer for all over india. kindly contact us at
					9310858478,9650720207,9268171171,9811905405,8459505105.
				</marquee>
			</div>


			<!-- Jssor Slider Begin -->
			<!-- You can move inline styles to css file or css block. -->
			<div id="slider2_container"
				style="position: relative; width: 1170px;">About Cybertel
				Cybertel provides the most convenient payment options for: Prepaid
				mobile recharges DTH recharges Utility bills
				(Electricity/Water/Gas/Post paid telephone bills) Travel (
				Air/Train/Bus/Cab).</div>
			<!-- Jssor Slider End -->






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
				<h3>Copyright &copy; cybertel.com All Right Reserved</h3>
			</div>
		</div>
	</div>

</body>
</html>


