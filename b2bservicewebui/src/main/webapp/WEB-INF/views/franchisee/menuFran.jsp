<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="indexValDiv" style="display: none">2</div>
<div class="box sidemenu" style="height: auto;">




	<div id="section-menu"
		class="block ui-accordion ui-widget ui-helper-reset ui-accordion-icons"
		role="tablist">
		<ul class="section menu">
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-state-active ui-corner-top current"
				role="tab" aria-expanded="true" aria-selected="true" tabindex="0"
				id="menuitem1" href="0"><span
					class="ui-icon ui-icon-triangle-1-s"></span><label
					style="color: #CC0000;">Transaction</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active current"
					style="height: auto;" role="tabpanel">
					<li><a href="telecomrecharge"><label
							style="color: #CC0000;">MOBILE RECHARGE</label></a></li>
					<li><a href="tvrecharge"><label style="color: #CC0000;">DTH-TV
								RECHARGE</label></a></li>
					<li><a href="dataCardrecharge"><label
							style="color: #CC0000;">DATACARD RECHARGE</label></a></li>
					<li><a href="postpaidbillpayment"><label
							style="color: #CC0000;">POST PAID BILL</label></a></li>
					<li><a href="refundrequesthome"><label
							style="color: #CC0000;">REFUND REQUEST</label></a></li>
					<li><a href="fundtransferfran"><label
							style="color: #CC0000;">FUND TRANSFER</label></a></li>
					<li><a href="newdthconnections"><label
							style="color: #CC0000;">DTH CONNECTION</label></a></li>
					<li><a href="#"><label style="color: #CC0000;">AIRLINE
								BOOKING</label></a></li>
					<li><a href="#"><label style="color: #CC0000;">RAILWAY
								BOOKING</label></a></li>
					<li><a href="#"><label style="color: #CC0000;">BUS
								BOOKING</label></a></li>
					<li><a href="#"><label style="color: #CC0000;">HOTEL
								BOOKING</label></a></li>
					<li><a href="#"><label style="color: #CC0000;">MOVIES
								TICKET</label></a></li>

				</ul></li>
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="1"
				id="menuitem1" href="1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Utility Bill Payment</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">

					<li><a href="landlinebill"><label style="color: #CC0000;">LAND
								LINE</label></a></li>
					<li><a href="electricitybill"><label
							style="color: #CC0000;">ELECTRICITY</label></a></li>
					<li><a href="insurancepremium"><label
							style="color: #CC0000;">INSURANCE PREMIUM</label></a></li>
					<li><a href="gasbill"><label style="color: #CC0000;">GAS</label></a></li>
					<li><a href="waterbill"><label style="color: #CC0000;">WATER</label></a></li>
				</ul></li>
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="2"
				id="menuitem1" href="1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Money Transfer</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="moneytransferhome"><label
							style="color: #CC0000;">LOGIN/FORGOT PIN/REGISTRATION</label></a></li>
					<%
						String cardno = (String) session.getAttribute("cardno");
					%>
					<c:if test="${not empty cardno && not empty mobileNo}">
						<li><a href="beneficiary"><label style="color: #CC0000;">ADD,VIEW
									AND REMOVE BENEFICIARY</label></a></li>
						<li><a href="topsenderwallet"><label
								style="color: #CC0000;">TOPUP SENDER WALLET</label></a></li>
						<li><a href="fundtransferotp"><label
								style="color: #CC0000;">FUND TRANSFER</label></a></li>
						<li><a href="moneytransferhistory"><label
								style="color: #CC0000;">FUND TRANSFER HISTORY</label></a></li>
						<li><a href="upgradesender"><label
								style="color: #CC0000;">UPGRADE SENDER</label></a></li>
					</c:if>
					<!-- <li><a href="#"><label style="color: #CC0000;">REFUND REPORT</label></a></li> -->
				</ul></li>
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="2"
				id="menuitem1" href="1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Reports</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="accountstatement"><label
							style="color: #CC0000;">ACCOUNT STATEMENT</label></a></li>
					<li><a href="rechargehistory"><label
							style="color: #CC0000;">RECHARGE HISTORY</label></a></li>
					<li><a href="activityhistory"><label
							style="color: #CC0000;">ACTIVITY HISTORY</label></a></li>
					<li><a href="refundalllist"><label style="color: #CC0000;">REFUND
								REPORT</label></a></li>
					<!-- <li><a href="refundalllist"><label style="color: #CC0000;">REFUND
								LIST</label></a></li> -->
				</ul></li>

			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="2"
				id="menuitem1" href="1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Historical Reports</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="historicalrechargehistory"><label
							style="color: #CC0000;">HISTORICAL RECHARGE HISTORY</label></a></li>
					<li><a href="historicalactivityhistory"><label
							style="color: #CC0000;">HISTORICAL ACTIVITY HISTORY</label></a></li>
				</ul></li>

			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="3"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Miscellaneous</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="cashdepositrequestfran"><label
							style="color: #CC0000;">CASH DEPOSIT REQUEST</label></a></li>
					<li><a href="refundrequesthome"><label
							style="color: #CC0000;">REFUND REQUEST</label></a></li>
					<li><a href="tidreceipt"><label style="color: #CC0000;">PRINT
								TID RECEIPT</label></a></li>
					<li><a href="cashcardregister"><label
							style="color: #CC0000;">CASHCARD REGISTRATION</label></a></li>
					<li><a href="icashcardrecharge"><label
							style="color: #CC0000;">CASHCARD RECHARGE</label></a></li>
					<li><a href="#"><label style="color: #CC0000;">KEYWORD
								LIST</label></a></li>
					<li><a href="#"><label style="color: #CC0000;">CUSTOMER
								CARE</label></a></li>
					<li><a href="retailercommision"><label
							style="color: #CC0000;">COMMISSION DETAILS</label></a></li>
					<li><a href="earning"><label style="color: #CC0000;">EARNING
								DETAILS</label></a></li>

					<li><a href="changepasswordfran"><label
							style="color: #CC0000;">CHANGE PASSWORD</label></a></li>

					<li><a href="downloadmobileapp"><label
							style="color: #CC0000;">*DOWNLOAD MOBILE APP*</label></a></li>
				</ul></li>

			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">B2B
					Balance: Rs.<fmt:formatNumber type="number"
						value="${franchiseeInfo.b2bCurrentBalance}" maxFractionDigits="2"></fmt:formatNumber>
			</label></li>
			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">B2B
					Ad Unit Balance: Rs.<fmt:formatNumber type="number"
						value="${franchiseeInfo.b2bCurrentAdUnitBalance}"
						maxFractionDigits="2"></fmt:formatNumber>
			</label></li>
		</ul>
	</div>
</div>
