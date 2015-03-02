<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="base_url"></c:url>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="grid_12 header-repeat">
	<div id="branding">
		<div class="floatleft">
			<img alt="Logo" src="${base_url}img/cyber-2.png">
		</div>
		<div class="floatright">
			<div class="floatleft">
				<%--  <div id="scrollwrapper" style="overflow:hidden; border:1px solid white;
							      position:relative; width:800px; height:20px;">
							  <div style="width:5000px;">
							     <span id="scrollcontent" style="position:absolute; left:100px;">
							       <span id="firstcontent" style="float:left; text-align:left;">
							             <sec:authorize access="hasRole('ROLE_DISTRIBUTOR')">
							             <font style="color:red; font-size:15px">							             
							             Diwali Dhamakka offer: Update deposit 2 lacs &amp; get  1% extra margin on 1st Nov, 2013
							             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
							             </sec:authorize>
							             <sec:authorize access="hasRole('ROLE_FRANCHISEE')">
							            <!--  <font style="color:red; font-size:15px">							             
							             Take Rs. 25000 load from Distributor &amp; get 0.50% extra margin on 1st Nov 2013.
							             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> -->
							             </sec:authorize>
							       </span>
							    </span>
							  </div>
							</div> --%>
			</div>
			<div class="floatleft marginleft10">
				<ul class="inline-ul floatleft">
					<li>Mobile No.:<sec:authentication
							property="principal.username" /></li>
					<li><a href="home">Home</a></li>
					<li><a href='<c:url value="/j_spring_security_logout" />'>Logout</a></li>
				</ul>
				<br>
				<span class="small grey">Your
					Id:${franchiseeInfo.franId}${distributorInfo.distId}${adminId}${empid}${customerInfo.customerId}</span>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<div class="clear"></div>
<div class="grid_12">
	<ul class="nav main">
		<li><a>Welcome</a></li>
		<li><a>${franchiseeInfo.firmName}${distributorInfo.firmName}${companyName}</a></li>

	</ul>
</div>
<div class="clear"></div>