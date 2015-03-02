<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function confirmation() {
		var con = confirm("Are you sure want to deduct the amount?");
		if (con == true) {
			return true;
		} else {
			return false;
		}
	}
	function transferAmountValidation() {

		var currbal = document.getElementById("currbal").value;
		var adunitbal = document.getElementById("adunitbal").value;
		var b2bbal = document.getElementById("b2bbal").value;
		var currdeduct = document.getElementById("currdeduct").value;
		var adUnitdeduct = document.getElementById("adUnitdeduct").value;
		var b2bdeduct = document.getElementById("b2bdeduct").value;

		if (isNaN(currdeduct || b2bdeduct || adUnitdeduct)) {
			alert("pls enter numeric the amount");
			return false;
		}
		var a = currdeduct - currbal;
		var b = b2bdeduct - b2bbal;
		var c = adUnitdeduct - adunitbal;
		if (a > 0 || b > 0 || c > 0) {
			alert("pls enter less amount for reduction ");
			return false;
		}
		return true;
	}

	function fundTransferFormValidation() {
		return (transferAmountValidation() && confirmation());
	}
</script>
<div class="grid_10">
	<div class="box round first">
		<h2>Deduct From Customer A/C Balance</h2>
		<div class="block ">
			<c:if test="${not empty Error}">
				<div class="message info">
					<h4>Information</h4>
					<h5>${Error}.</h5>
				</div>
			</c:if>
			<form:form method="post" action="deductamountapiaccount"
				onsubmit="return fundTransferFormValidation();"
				modelAttribute="userdetail">
				<table class="form">
					<tbody>

						<tr>
							<td class="col1"><label>Customer ID:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="userId" id="distributorId" readonly="readonly" /></td>
						</tr>

						<tr>
							<td class="col1"><label>Mobile NO.:</label></td>
							<td class="col2"><form:input class="inputfield"
									path="mobileNo" id="mobileno" readonly="readonly" /></td>
						</tr>
						<tr>
							<td><label>Firm Name:</label></td>
							<td><form:input path="firmName" id="firmName"
									readonly="readonly" class="inputfield" /></td>
						</tr>
						<tr>
							<td><label>Current Balance:</label></td>
							<td><form:input path="currBal" readonly="readonly"
									id="currbal" class="inputfield" /></td>
						</tr>
						<tr>
							<td><label>Remark:</label></td>
							<td><input type="text" name="remark" id="firmName" /></td>
						</tr>
						<tr>
							<td class="col1"><label>Deduct Amount:</label></td>
							<td class="col2"><input class="inputfield" name="amount"
								type="text" required="required" id="currdeduct" /></td>
						</tr>


					</tbody>
				</table>
				<input class="button" type="submit" value="Deduct"
					class="btn btn-large" />
			</form:form>
		</div>
	</div>
</div>

