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
					style="color: #CC0000;">Token</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active current"
					style="height: auto;" role="tabpanel">
					<li><a href="tokengenerator"><label
							style="color: #CC0000;">GENERATE/VIEW TOKEN</label></a></li>
				</ul></li>

			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-state-active ui-corner-top current"
				role="tab" aria-expanded="true" aria-selected="true" tabindex="0"
				id="menuitem1" href="0"><span
					class="ui-icon ui-icon-triangle-1-s"></span><label
					style="color: #CC0000;">Transaction</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active current"
					style="height: auto;" role="tabpanel">
					<li><a href="refundrequestcust"><label
							style="color: #CC0000;">REFUND REQUEST</label></a></li>
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
					<li><a href="accountstatement"><label
							style="color: #CC0000;">ACCOUNT STATEMENT</label></a></li>

					<li><a href="rechargehistory"><label
							style="color: #CC0000;">RECHARGE HISTORY</label></a></li>
					<li><a href="refundreportcust"><label
							style="color: #CC0000;">REFUND REPORT</label></a></li>
					<li><a href="searchtransaction"><label
							style="color: #CC0000;">SEARCH TRANSACTION</label></a></li>
				</ul></li>
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="-1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Miscellaneous</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">

					<li><a href="changepasswordcustomer"><label
							style="color: #CC0000;">CHANGE PASSWORD</label></a></li>
					<li><a href="customercommission"><label
							style="color: #CC0000;">CUSTOMER COMMISSION</label></a></li>
					<li><a href="transacionReceipt"><label
							style="color: #CC0000;">PRINT TID RECEIPT</label></a></li>
				</ul></li>

			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">
					Balance: Rs.<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${customerInfo.currentBalance}" />
			</label></li>

		</ul>

	</div>
</div>
