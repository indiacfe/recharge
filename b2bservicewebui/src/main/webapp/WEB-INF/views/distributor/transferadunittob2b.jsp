<!-- <script type="text/javascript">
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
</div> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function safeSubmit() {
	var isSubmitted = false;
    if (!isSubmitted) {
    	
        isSubmitted = true;
        document.forms[0].submit;
    }
}
	function checkLength() {
		var amount = document.getElementById("transferAmount").value;
		if (isNaN(amount)) {
			alert("pls enter number in amount");
			document.getElementById("transferAmount").focus();
			return false;
		}
	}
</script>

<div class="grid_10">
	<div class="box round first">
		<h2>Transfer Amount from Ad-Unit to B2B</h2>
		<div class="block ">
		<c:if test="${not empty error}">
				<div class="message info">
					<h5>Information</h5>
					<p>${error}.</p>
				</div>
			</c:if>
			<form method="post" action="transferadunittob2b"
				onsubmit="return checkLength();">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Enter Transfer Amount</label></td>
							<td class="col2"><input class="inputfield" name="amount"
					required="required" id="transferAmount" /></td>
						</tr>


					</tbody>
				</table>
				<input type="submit" class="button" value="Submit" onclick="safeSubmit();"/>
			</form>
		</div>
	</div>
</div>