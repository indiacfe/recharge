<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="search_panelR">
<script type="text/javascript">
function rechargeAgain() {
	window.location.href = "electricitybill";
}

function print(){
	var prtContent = document.getElementById("printDiv");
	var WinPrint = window.open('', '', 'letf=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
	WinPrint.document.write(prtContent.innerHTML);
	WinPrint.document.close();
	WinPrint.focus();
	WinPrint.print();
	WinPrint.close();
}

</script>
	<h1>Electricity Bill Payment Detail</h1>
	
	<div class="form_action" div="printDiv">
		<form action="" style="text-align: left;">
			<div class="form_row">

				<div class="tdElement">Transaction Status: </div>
				<div class="tdElement">
					<strong>Transaction Successful</strong>
				</div>
			</div>

			<div class="form_row">
				<div class="tdElement">Date:</div>
				<div class="tdElement">${datetime}</div>
			</div>
			<div class="form_row">
				<div class="tdElement">Electricity Connection Number (CA):</div>
				<div class="tdElement">${connectionid}</div>
			</div>
			<div class="form_row">
				<div class="tdElement">OPERATOR:</div>
				<div class="tdElement">${operator}</div>

			</div>
			<div class="form_row">

				<div class="tdElement">TID:</div>
				<div class="tdElement">${transId}</div>

			</div>
			<div class="form_row">
				<div class="tdElement">Amount(Rs.):</div>
				<div class="tdElement">${amount}</div>
			</div>

			<input type="button" name="submit" value="Print" class="button" onclick="print()" /> 
			<input type="button" name="submit" value="Pay Again" class="button" onclick="rechargeAgain();" /> 

			<div></div>
		</form>
	</div>
</div>
