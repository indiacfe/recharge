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


			<div class="cont-num">
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
				style="position: relative; width: 1170px;">

				Frequently Asked Questions: My balance has been deducted, but
				customer did not receive? Please wait for 10 to 20 minutes as there
				can be delay in SMS service from operator end. If still not received
				after that time then please call our Customer Care Executive.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				What is the process of STV? STV is Special Tariff Voucher, i.e. this
				option is used for special recharge schemes like SMS pack, GPRS
				pack, free night call pack, etc.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How I can recharge for Mobile Network Portable number (MNP)? Web
				Interface user can do by using Portability under Recharge Option in
				Menu bar. SMS users can recharge portable numbers by using keyword
				T*Mobile Number*Amount*Operator Name.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How I can define if number is portable or not? Please check with the
				Customer whether his number is ported or not, if yes then ask for
				operator name where it is ported.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				I am using the proper SMS syntax, but still recharge is showing
				failed? Please contact our Customer Care Executive for all the
				details or visit our website www.cybertelindia.com
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				I have forgotten my password, how I can get it back? Please send SMS
				PWD through your register number to ________________.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				Sometime customer received the balance but I am not getting
				confirmation message? Please wait up to 30 minute there can be delay
				in SMS sending gateway by operator end
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How I can change my registered mobile number? Please contact to your
				Distributor.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				When I can make next transaction at same number? same number, same
				denomination after 30 minutes
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How much time it takes to get confirmation from the biller for
				Electricity/Gas/Water/Insurance/Post paid biller? Generally all the
				Billers take 24-36 Hrs to send the confirmation to the customers.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				What are the SMS key words for mobile transactions? Please check all
				SMS keyword process through this link www.cybertelindia
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How I can recharge prepaid walky? Please check all SMS keyword
				process through this link www.cybertelindia.com
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How I can recharge prepaid internet connection? Please check all SMS
				keyword process through this link www.cybertelindia.com
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How I can check if failed recharge amount has been refunded or not?
				Please check all SMS keyword process through this link
				www.cybertelindia.com
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How I can get the ticket status by SMS? Please check all SMS keyword
				process through this link www.cybertelindia.com
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				How can I become your Channel partner (Distributor/Retailer)? It's
				very simple! Just fill up the KYC form on our website, pay the
				joining fee and you will become our Channel partner instantly. You
				will receive your login id and password on your Registered Mobile
				Number (RMN) to start transactions for Cybertel.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				My balance is deducted, success message is received but bill is not
				updated ? It would take 24-36 hours to get confirmation from the
				service provider/Biller. Pl. check after 1/2/3 days for the update
				if not receive any confirmation from the biller.
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			</div>
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


