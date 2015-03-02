<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

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
					style="color: #CC0000;">Fund Transfer</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active current"
					style="height: auto;" role="tabpanel">
					<li><a href="franchiseetransfer"><label
							style="color: #CC0000;">RETAILER</label></a></li>
					<li><a href="transferadunit"><label
							style="color: #CC0000;">TRANSFER AD-UNIT TO B2B</label></a></li>
					<li><a href="registration"><label style="color: #CC0000;">REGISTRATION</label></a></li>



				</ul></li>
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="-1"
				id="menuitem1" href="1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Reports</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="fundtransfer"><label style="color: #CC0000;">FUND
								TRANSFER REPORT</label></a></li>
					<li><a href="transactionsummary"><label
							style="color: #CC0000;">TRANSACTION SUMMARY</label></a></li>
					<li><a href="distshowcashdepositrequests"><label
							style="color: #CC0000;">CASH DEPOSIT REQUEST REPORT</label></a></li>
				</ul></li>
			
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="-1"
				id="menuitem1" href="1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Historical Reports</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="historicalfundtransfer"><label style="color: #CC0000;">HISTORICAL FUND
								TRANSFER REPORT</label></a></li>
					<li><a href="historicaltransactionsummary"><label
							style="color: #CC0000;">HISTORICAL TRANSACTION SUMMARY</label></a></li>
				</ul></li>
			
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="-1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Miscellaneous</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">

					<li><a href="cashdepositrequestdist"><label
							style="color: #CC0000;">CASH DEPOSIT REQUEST</label></a></li>
					<!-- li><a href="#">CASH DEPOSIT REQUEST</a></li -->
					<li><a href="#"><label style="color: #CC0000;">EARNING
								OPPERTUNITY</label></a></li>
					<li><a href="changepassworddist"><label
							style="color: #CC0000;">CHANGE PASSWORD</label></a></li>
				</ul></li>
			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">Business
					Balance: Rs.<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${distributorInfo.currAcBalance}" />
			</label></li>
			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">B2B
					Balance: Rs.<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${distributorInfo.b2bCurrAcBalance}" />
			</label></li>
			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">B2B
					Ad Unit Balance: Rs.<fmt:formatNumber type="number"
						maxFractionDigits="2"
						value="${distributorInfo.b2bCurrAcAdUnitBal}" />
			</label></li>
			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">Total
					Retailer: ${distributorInfo.numberofFranchisee}</label></li>

		</ul>
	</div>
</div>

