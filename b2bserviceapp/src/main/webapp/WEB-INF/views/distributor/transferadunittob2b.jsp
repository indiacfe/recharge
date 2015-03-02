<script type="text/javascript">
	function checkLength() {
		var amount = document.getElementById("transferAmount").value;
		if (isNaN(amount)) {
			alert("pls enter number in amount");
			document.getElementById("transferAmount").focus();
			return false;
		}
	}
</script>
<div id="search_panelD">
	<h1>Transfer Amount from Ad-Unit to B2B </h1>
	<form method="post" action="transferadunittob2b">
		<div class="search_panel_box">

			<div class="form_row">
				<label>Enter Transfer Amount</label> <input class="inputfield" name="amount"
					required="required" id="transferAmount" />
			</div>
		</div>

		<input type="submit" class="button" value="Submit" />
	</form>
</div>