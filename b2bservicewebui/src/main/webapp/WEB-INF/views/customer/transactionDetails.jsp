<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function print() {
		var tid = document.getElementById("transid").value;
		var createdAt = document.getElementById("createdAt").value;
		var operator = document.getElementById("operator").value;
		var mobileNo = document.getElementById("mobileNo").value;
		var connectionid = document.getElementById("connectionid").value;
		var status = document.getElementById("status").value;
		var amount = document.getElementById("amount").value;
		var prtContent = '<fieldset><legend>Transaction Details</legend><table><tr><td>Transacton ID:</td><td>'
				+ tid + '</td></tr><tr><td>Date:</td><td>' + createdAt;
		if (mobileNo != "") {
			prtContent = prtContent + '</td></tr><tr><td>Mobile No:</td><td>'
					+ mobileNo;
		} else {
			prtContent = prtContent
					+ '</td></tr><tr><td>Connection ID:</td><td>'
					+ connectionid;
		}
		prtContent = prtContent + '</td></tr><tr><td>Operator:</td><td>'
				+ operator + '</td></tr><tr><td>Status:</td><td>' + status
				+ '</td></tr><tr><td>Amount:</td><td>' + amount
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
<input type="hidden" value="${transactionTransportBean.transid}"
	id="transid" />
<input type="hidden" value="${transactionTransportBean.createdAt}"
	id="createdAt" />
<input type="hidden" value="${transactionTransportBean.operator}"
	id="operator" />
<input type="hidden" value="${transactionTransportBean.mobileNo}"
	id="mobileNo" />
<input type="hidden" value="${transactionTransportBean.connectionid}"
	id="connectionid" />
<input type="hidden" value="${transactionTransportBean.status}"
	id="status" />
<input type="hidden" value="${transactionTransportBean.amount}"
	id="amount" />
<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">Transaction Details</label>
		</h2>
		<div class="block ">

			<table class="form">
				<tbody>
					<tr>
						<td class="col1" style="width: 39%"><label
							style="font-size: 20px; color: #CC0000;">Transacton ID:</label></td>
						<td class="col2"><label
							style="font-size: 20px; color: #CC0000;">${transactionTransportBean.transid}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Date:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${transactionTransportBean.createdAt}</label></td>
					</tr>
					<c:if test="${transactionTransportBean.mobileNo!=null}">
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Mobile
									No:</label></td>
							<td><label style="font-size: 20px; color: #CC0000;">${transactionTransportBean.mobileNo}</label></td>
						</tr>
					</c:if>
					<c:if test="${transactionTransportBean.connectionid!=null}">
						<tr>
							<td><label style="font-size: 20px; color: #CC0000;">Connection
									ID:</label></td>
							<td><label style="font-size: 20px; color: #CC0000;">${transactionTransportBean.connectionid}</label></td>
						</tr>
					</c:if>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Operator:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${transactionTransportBean.operator}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Status:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${transactionTransportBean.status}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Amount(Rs.):</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${transactionTransportBean.amount}</label></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" name="submit"
							class="btn btn-large" value="Print" onclick="window.print()"
							style="width: 101px" /></td>
						<td><input type="button" name="submit" class="btn btn-large"
							value="Back" onclick="window.history.back();" /></td>
					</tr>

				</tbody>
			</table>


		</div>
	</div>
</div>