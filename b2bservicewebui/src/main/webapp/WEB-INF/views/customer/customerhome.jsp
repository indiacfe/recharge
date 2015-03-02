<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:url value="/" var="base_url" />
<div class="grid_10">
	<div class="box round first">

		<h2>Figures</h2>
		<div class="block" style="text-align: center;" align="center">
			<div class="stat-col"
				style="padding-left: padding-left:  100px; padding-right: 149px; margin-left: 100px;"
				align="center">
				<span style="padding-left: 150px;">Current Balance</span>
				<p class="purple"
					style="width: 212px; padding-left: 27px; margin-left: 185px;">Rs.<fmt:formatNumber type="number" 
            maxFractionDigits="2" value="${customerInfo.currentBalance}" />
            </p>

			</div>
			<div class="clear"></div>
		</div>
		<%-- <div style="padding-left: 12px">
			<c:forEach var="news" items="${newses}">
				<p style="font-size: 18px; color: blue;">${news.description}</p>


			</c:forEach>
		</div> --%>

	</div>

</div>

