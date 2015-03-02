<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url value="/" var="base_url" />
<div class="grid_10">
	<div class="box round first">
		<h2>Figures</h2>
		<div class="block" style="text-align: center;" align="center">
			<div class="stat-col"
				style="padding-left: padding-left:  10px; padding-right: 149px; margin-left: 100px;"
				align="center">
				<span style="padding-left: 150px;">Business Balance</span>
				<p class="purple"
					style="width: 212px; padding-left: 27px; margin-left: 185px;">
					Rs.
					<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${distributorInfo.currAcBalance}" />
				</p>


				<span style="padding-left: 150px;">B2B Balance</span>
				<p class="yellow"
					style="width: 212px; padding-left: 27px; margin-left: 185px;">
					Rs.
					<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${distributorInfo.b2bCurrAcBalance}" />
				</p>


				<span style="padding-left: 180px;">B2B Ad Unit Balance</span>
				<p class="green"
					style="width: 212px; padding-left: 27px; margin-left: 185px;">
					Rs.
					<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${distributorInfo.b2bCurrAcAdUnitBal}" />
				</p>


				<span style="padding-left: 180px;">Total Retailer</span>
				<p class="blue"
					style="width: 212px; padding-left: 27px; margin-left: 185px;">${distributorInfo.numberofFranchisee}</p>



			</div>
			<div></div>

			<div class="clear"></div>


		</div>
		<div style="padding-left: 12px">
			<c:forEach var="news" items="${newses}">
				<p style="font-size: 18px; color: blue;">${news.description}</p>


			</c:forEach>
		</div>
	</div>
</div>

<div class="grid_10">

	  <%-- <div class="block">
		<p class="start">
			<img class="left" alt="Ginger" src="${base_url}img/dc1.jpg">
		</p>

	</div>  --%> 


</div>
