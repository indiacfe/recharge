<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function rechargeAgain() {
		window.location.href = "cashcardregister";
	}

	function print() {
		var dateTime = document.getElementById("datetime").value;
		var connectionId = document.getElementById("connectionid").value;
		var operator = document.getElementById("operator").value;
		var tid = document.getElementById("transId").value;
		var amount = document.getElementById("amount").value;
		var prtContent = '<fieldset><legend>Transaction Details</legend><table><tr><td>Type:</td><td>Mobile Recharge</td></tr><tr><td>Date-Time:</td><td>'
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
		WinPrint.close();
	}
</script>
<input type="hidden" value="${datetime}" id="datetime" />
<input type="hidden" value="${mobileNo}" id="connectionid" />
<input type="hidden" value="${operator}" id="operator" />
<input type="hidden" value="${transId}" id="transId" />
<input type="hidden" value="${amount}" id="amount" />
<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">Icash Recharge Detail</label>
		</h2>
		<div class="block ">

			<table class="form">
				<tbody>
					<tr>
						<td class="col1" style="width: 39%"><label
							style="font-size: 20px; color: #CC0000;">Transaction
								Status:</label></td>
						<td class="col2"><label
							style="font-size: 20px; color: #CC0000;">Transaction
								Successful</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Date:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${datetime}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Mobile
								Number:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${mobileNo}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Registration TID:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${transId}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Amount(Rs.):</label></td>
						<td><label style="font-size: 20px; color: #CC0000;"><fmt:formatNumber
									type="number" value="${amount}" maxFractionDigits="2"></fmt:formatNumber></label></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" name="submit"
							class="btn btn-large" value="Print" onclick="window.print()"
							style="width: 101px" /></td>
						<td><input type="submit" name="submit" class="btn btn-large"
							value="Recharge Again" onclick="rechargeAgain();" /></td>
					</tr>

				</tbody>
			</table>


		</div>
	</div>
</div>