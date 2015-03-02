<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="base_url" />
<script type="text/javascript">
	function rechargeAgain() {
		window.location.href = "electricitybill";
	}

	function print() {
		var dateTime = document.getElementById("datetime").value;
		var connectionId = document.getElementById("connectionid").value;
		var operator = document.getElementById("operator").value;
		var tid = document.getElementById("transId").value;
		var amount = document.getElementById("amount").value;
		/* var prtContent = '<fieldset><legend>Transaction Details</legend><table><tr><td>Type:</td><td>Electricity Bill Payment</td></tr><tr><td>Date-Time:</td><td>'
				+ dateTime
				+ '</td></tr><tr><td>Connection No.:</td><td>'
				+ connectionId
				+ '</td></tr><tr><td>Operator:</td><td>'
				+ operator
				+ '</td></tr><tr><td>TID:</td><td>'
				+ tid
				+ '</td></tr><tr><td>Amount:</td><td>'
				+ amount
				+ '</td></tr></table></fieldset>';

		var WinPrint = window
				.open('', '',
						'letf=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
		WinPrint.document.write(prtContent);
		WinPrint.document.close();
		WinPrint.focus();
		WinPrint.print();
		WinPrint.close(); */
		var printReciept = document.getElementById("reciept");
		var WinPrint = window
				.open('', '',
						'letf=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
		WinPrint.document.write(printReciept.innerHTML);
		WinPrint.document.close();
		WinPrint.focus();
		WinPrint.print();
		WinPrint.close();
	}
</script>
<input type="hidden" value="${datetime}" id="datetime" />
<input type="hidden" value="${connectionid}" id="connectionid" />
<input type="hidden" value="${operator}" id="operator" />
<input type="hidden" value="${transId}" id="transId" />
<input type="hidden" value="${amount}" id="amount" />
<div class="grid_10">
	<div class="box round first" id="reciept">
		<h2>
			<label style="font-size: 20px;">Electricity Bill Payment
				Detail</label>
		</h2>
		<div class="block" style="padding: 0;">

			<img src="${base_url}img/logo1.png" alt=""
				style="float: left; padding-top: 12px;">
			<h3
				style="font-size: 20px; padding-top: 55px; float: left; margin-left: 30%; color: #CC0000;">Acknowledgement</h3>
			<img src="${base_url}img/${logo}" alt=""
				style="float: right; padding-top: 25px;">

			<hr
				style="margin: 0; border-color: #000; border-style: solid; border-width: 2px 0 0;" />

			<table class="form">
				<tbody>
					<tr>
						<td class="col1" style="width: 39%"><label
							style="font-size: 15px; color: #CC0000;">Transaction
								Status:</label></td>
						<td class="col2"><label
							style="font-size: 15px; color: #CC0000;">Transaction
								Successful</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 15px; color: #CC0000;">Date:</label></td>
						<td><label style="font-size: 15px; color: #CC0000;">${datetime}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 15px; color: #CC0000;">Electricity
								Connection Number (CA):</label></td>
						<td><label style="font-size: 15px; color: #CC0000;">${connectionid}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 15px; color: #CC0000;">Operator:</label></td>
						<td><label style="font-size: 15px; color: #CC0000;">${operator}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 15px; color: #CC0000;">TID:</label></td>
						<td><label style="font-size: 15px; color: #CC0000;">${transId}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 15px; color: #CC0000;">Amount(Rs.):</label></td>
						<td><label style="font-size: 15px; color: #CC0000;">${amount}</label></td>
					</tr>


				</tbody>
			</table>
			<hr
				style="margin: 0; border-color: #000; border-style: solid; border-width: 2px 0 0;" />
			<h3 style="font-size: 20px; color: #CC0000;">Terms and
				Conditions</h3>
			<ul>
				<li style="font-size: 14px; color: #CC0000;">You will be
					charged a service fee of Rs.3.00.</li>
				<li style="font-size: 14px; color: #CC0000;">Please check the
					details printed on the receipt</li>
				<li style="font-size: 14px; color: #CC0000;">CyberTel is not
					responsible for handling the complaints regarding act/ mistake/
					omission after you have left the counter.</li>
			</ul>

			<hr
				style="margin: 0; border-color: #000; border-style: solid; border-width: 2px 0 0;" />

			<h4 style="font-size: 15px; color: #CC0000; padding-top: 5px;">CyberTel
				Customer Care: +91-9310858478</h4>
			<img style="float: right;" src="${base_url}img/logo1.png" alt="">
			<p
				style="float: right; font-size: 15px; font-weight: bold; padding-top: 40px; color: #CC0000;">Powered
				By:</p>

			<hr
				style="border-color: #000; border-style: solid; border-width: 2px 0 0;" />
			<div class="dis"
				style="clear: both; display: block; margin-bottom: 12px; overflow: hidden;">
				<a href="#"><img src="${base_url}img/mob-recharge.png"
					style="float: left; margin-right: 30px; margin-left: 72px;"></a>
				<a href="#"><img src="${base_url}img/datacard-recharge.png"
					style="float: left; margin-right: 30px;"></a> <a href="#"><img
					src="${base_url}img/dth-recharge.png"
					style="float: left; margin-right: 30px;"></a> <a href="#"><img
					src="${base_url}img/tr-booking.png"
					style="float: left; margin-right: 30px;"></a> <a href="#"><img
					src="${base_url}img/utility.png"
					style="float: left; margin-right: 30px;"></a> <a href="#"><img
					src="${base_url}img/entertainment.png" style="float: left;"></a>

			</div>
			<hr
				style="border-color: #000; border-style: solid; border-width: 2px 0 0;" />

			<input type="submit" name="submit" class="btn btn-large"
				style="margin-left: 38%; margin-right: 20px;" value="Print"
				onclick="window.print()" style="width: 101px" /> <input
				type="submit" name="submit" class="btn btn-large" value="Pay Again"
				onclick="rechargeAgain();" />
		</div>

	</div>