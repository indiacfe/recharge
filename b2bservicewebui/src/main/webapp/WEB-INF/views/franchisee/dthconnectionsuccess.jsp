<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function rechargeAgain() {
	window.location.href = "newdthconnections";
}

function print() {
	var dateTime = document.getElementById("datetime").value;
	var connectionId = document.getElementById("connectionid").value;
	var operator = document.getElementById("operator").value;
	var tid = document.getElementById("transId").value;
	var amount = document.getElementById("amount").value;
	var prtContent = '<fieldset><legend>Transaction Details</legend><table><tr><td>Type:</td><td>DTH Connection Payment</td></tr><tr><td>Date-Time:</td><td>'
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
<input type="hidden" value="${connectionid}" id="connectionid" />
<input type="hidden" value="${operator}" id="operator" />
<input type="hidden" value="${transId}" id="transId" />
<input type="hidden" value="${amount}" id="amount" />
<div class="grid_10">
	<div class="box round first">
		<h2><label style="font-size: 20px;">DTH Connection Payment Detail</label></h2>
		<div class="block ">

			<table class="form">
				<tbody>
					<tr>
						<td class="col1" style="width: 39%"><label
							style="font-size: 20px; color: #CC0000;">Transaction Status:</label></td>
						<td class="col2"><label
							style="font-size: 20px; color: #CC0000;">Transaction Successful</label></td>
					</tr>
					<tr>
						<td><label
							style="font-size: 20px; color: #CC0000;">Date:</label></td>
						<td><label
							style="font-size: 20px; color: #CC0000;">${datetime}</label></td>
					</tr>
					<tr>
						<td><label
							style="font-size: 20px; color: #CC0000;">DTH Connection Number:</label></td>
						<td><label
							style="font-size: 20px; color: #CC0000;">${connectionid}</label></td>
					</tr>
					<tr>
						<td><label
							style="font-size: 20px; color: #CC0000;">Operator:</label></td>
						<td><label
							style="font-size: 20px; color: #CC0000;">${operator}</label></td>
					</tr>
					<tr>
						<td><label
							style="font-size: 20px; color: #CC0000;">TID:</label></td>
						<td><label
							style="font-size: 20px; color: #CC0000;">${transId}</label></td>
					</tr>
					<tr>
						<td><label
							style="font-size: 20px; color: #CC0000;">Amount(Rs.):</label></td>
						<td><label
							style="font-size: 20px; color: #CC0000;">${amount}</label></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" name="submit" class="btn btn-large"
							value="Print" onclick="print()" style="width: 101px"/></td>
						<td><input type="submit"
							name="submit" class="btn btn-large" value="Pay Again"
							onclick="rechargeAgain();" /></td>
					</tr>

				</tbody>
			</table>
			 

		</div>
	</div>
</div>