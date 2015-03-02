
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					style="color: #CC0000;">Transfer</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active current"
					style="height: auto;" role="tabpanel">
					<li><a href="searchdistributorpage"><label
							style="color: #CC0000;">FUND TRANSFER</label></a></li>
					<li><a href="searchtransactionpage"><label
							style="color: #CC0000;">REFUND AMOUNT</label></a></li>
							
							<li><a href="rejectedRefundRequest"><label
							style="color: #CC0000;">REJECTED REFUND REQUEST LIST</label></a></li>
							
					<li><a href="updateAdmin"><label style="color: #CC0000;">UPDATE
								UTILITY</label></a></li>

					<li><a href="outletconfigpage1"><label
							style="color: #CC0000;">UPDATE OUTLET</label></a></li>
							
							<li><a href="searchretailer"><label
							style="color: #CC0000;">SEARCH USER</label></a></li>

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
					<li><a href="rechargehistoryreport"><label
							style="color: #CC0000;">RECHARGE HISTORY REPORT</label></a></li>
					<li><a href="mergerechargehistoryreport"><label
							style="color: #CC0000;">MERGE RECHARGE HISTORY REPORT</label></a></li>
					<li><a href="showcashdepositrequestsadmin"><label
							style="color: #CC0000;">SHOW REQUESTS</label></a></li>
					<li><a href="userdetail"><label style="color: #CC0000;">USER
								DETAIL</label></a></li>
					<li><a href="adminfundtransfer"><label
							style="color: #CC0000;">FUND TRANSFER REPORT</label></a></li>
							<li><a href="operatorwiserechargereport"><label
							style="color: #CC0000;">OPERATOR WISE RECHARGE REPORT</label></a></li>
							<li><a href="adminfundtransfer"><label
							style="color: #CC0000;">FUND TRANSFER REPORT</label></a></li>
							<li><a href="electricityrechargereport"><label
							style="color: #CC0000;">ELECTRICITY RECHARGE REPORT</label></a></li>
					<!-- <li><a href="transactionsummary"><label
							style="color: #CC0000;">TRANSACTION SUMMARY</label></a></li> -->
					<li><a href="thirdpartyserviceprovider"><label
							style="color: #CC0000;">THIRD PARTY SERVICE PROVIDER</label></a></li>
					<li><a href="companyoperatorcommission"><label
							style="color: #CC0000;">COMPANY OPERATOR COMMISSION</label></a></li>

					<li><a href="daybasereport"><label style="color: #CC0000;">Generate
								Report</label></a></li>
					
					<li><a href="totalfundtransferreport"><label
							style="color: #CC0000;">Total Fund Transfer Day/Month</label></a></li>
				</ul></li>
				<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="-1"
				id="menuitem1" href="0"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Historical Report</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="historicaldata"><label
							style="color: #CC0000;">Historical Data Report</label></a></li>
					<li><a href="historicalmergedata"><label
							style="color: #CC0000;">Historical Merge Date Report</label></a></li>
					<li><a href="historicaladminfundtransfer"><label
							style="color: #CC0000;">Historical Fund Transfer Report</label></a></li>
							
					</ul>
					</li>
			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="-1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">Miscellaneous</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">

					<li><a href="alldistributorsandretailers"><label
							style="color: #CC0000;">DIST/RET LIST</label></a></li>
					<li><a href="registrationadmin"><label
							style="color: #CC0000;">REGISTRATION</label></a></li>
					<li><a href="addnewairteluser"><label
							style="color: #CC0000;">ADD AIRTEL USER</label></a></li>
					<li><a href="changepasswordadmin"><label
							style="color: #CC0000;">CHANGE PASSWORD</label></a></li>
					<li><a href="retaileraddremove"><label
							style="color: #CC0000;">ADD/REMOVE</label></a></li>
					<li><a href="newnotice"><label style="color: #CC0000;">CREATE
								NOTICE</label></a></li>
					<li><a href="licpremiums"><label style="color: #CC0000;">LIC
								REQUESTS </label></a></li>



					<li><a href="smsrechargedetails"><label
							style="color: #CC0000;">SMS RECHARGE DETAILS</label></a></li>
				</ul></li>

			<!-- Api Menu start-->

			<li><a
				class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"
				role="tab" aria-expanded="false" aria-selected="false" tabindex="-1"><span
					class="ui-icon ui-icon-triangle-1-e"></span><label
					style="color: #CC0000;">API Management</label></a>
				<ul
					class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom"
					style="height: auto; display: none;" role="tabpanel">
					<li><a href="customerdetail"><label
							style="color: #CC0000;">CUSTOMERS LIST</label></a></li>
					<li><a href="apiregister"><label style="color: #CC0000;">API
								REGISTRATION</label></a></li>
					<li><a href="searchcustomerpage"><label
							style="color: #CC0000;">CUSTOMER FUND TRANSFER</label></a></li>
					<li><a href="searchcustomertransaction"><label
							style="color: #CC0000;">REFUND REQUEST</label></a></li>
					<li><a href="commission"><label style="color: #CC0000;">
								SET CUSTOMER COMMISSION</label></a></li>
					<li><a href="commisionpercustomer"><label
							style="color: #CC0000;">CUSTOMER'S COMMISSION</label></a></li>
					<li><a href="apifundtranserreport"><label
							style="color: #CC0000;">CUSTOMER FUND TRANSFER REPORT</label></a></li>
					<li><a href="customerrechargereport"><label
							style="color: #CC0000;">CUSTOMER RECHARGE REPORT</label></a></li>

				</ul></li>





			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">Account
					Balance:<fmt:formatNumber type="number" maxFractionDigits="2"
						value="${amount}" />
					</p>
			</label></li>
			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">Total
					Distributor: ${distributors}</label></li>
			<li><label
				style="line-height: 26px; border-bottom: 1px solid #CCCCCC; display: block; padding: 4px 10px; color: #CC0000; font-weight: bold; text-decoration: none; font-size: 100%; margin: 0; outline: 0 none; vertical-align: baseline;">Total
					Retailers: ${franchisees}</label></li>
		</ul>

	</div>
</div>
