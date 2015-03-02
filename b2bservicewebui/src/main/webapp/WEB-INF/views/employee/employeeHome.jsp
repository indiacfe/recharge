<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:url value="/" var="base_url" />
<div class="grid_10">
	<div class="box round first">
		<h2>Figures</h2>
		<div class="block" style="text-align: center;" align="center">
			<div class="stat-col"
				style="padding-left: padding-left: 10px; padding-right: 149px; margin-left: 100px;"
				align="center">
				<span style="padding-left: 180px;">Account Balance</span>
				<p class="purple"
					style="width: 251px; padding-left: 6px; margin-left: 174px;">
					Rs.
					<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${amount}" />
				</p>
			</div>
			<div class="stat-col"
				style="padding-left: padding-left: 10px; padding-right: 149px; margin-left: 100px;"
				align="center">
				<span style="padding-left: 200px;">Total Distributors</span>
				<p class="yellow"
					style="width: 212px; padding-left: 27px; margin-left: 185px;">${distributors}</p>
			</div>
			<div class="stat-col"
				style="padding-left: padding-left: 10px; padding-right: 149px; margin-left: 100px;"
				align="center">
				<span style="padding-left: 200px;">Total Retailers</span>
				<p class="green"
					style="width: 212px; padding-left: 27px; margin-left: 185px;">${franchisees}</p>
			</div>




			<div class="clear"></div>
		</div>
	</div>

</div>


