<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="search_panel1">
<script type="text/javascript">
function rechargeAgain() {
	window.location.href = "telecomrecharge";
}
</script>
	<h1>Recharge Detail</h1>

	<div class="form_action">
		<form action="" style="text-align: left;">
			<div class="form_row">

				<div class="tdElement">Transaction Status: </div>
				<div class="tdElement">
					<strong>Transaction Successful</strong>
				</div>
			</div>

			<div class="form_row">

				<div class="tdElement">Type:</div>
				<div class="tdElement">${rechargeType}</div>
			</div>

			<div class="form_row">
				<div class="tdElement">Date:</div>
				<div class="tdElement">${datetime}</div>
			</div>
			<div class="form_row">
				<div class="tdElement">Mobile/DTH:</div>
				<div class="tdElement">${mobileNo}</div>
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

			<input type="button" name="submit" value="Print" class="button" onclick="window.print()" /> 
			<input type="button" name="submit" value="Save" class="button"/>
			<input type="button" name="submit" value="Recharge Again" class="button" onclick="rechargeAgain();" /> 
			
			
			<div></div>
		</form>
	</div>
</div>
