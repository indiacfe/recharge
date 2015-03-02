<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function checkLength() {
		var id = document.getElementById("franchiseeid").value;
		if (id.length != 10) {
			alert("pls enter 10 digit Retailer Id/MobileNo");
			document.getElementById("franchiseeid").focus();
			return false;
		}
	}
</script>
<div id="search_panelD">
	<h1>search franchiseeId/mobileNumber</h1>
	<h2>${error}</h2>
	<form method="post" action="searchfranchisee"
		onsubmit="return checkLength();">
		<div class="search_panel_box">

			<div class="form_row">
				<label>Enter Retailer ID/Mobile NO.</label> <input
					class="inputfield" name="franchIdOrmobileNo" type="text" required="required"
					id="franchiseeid" />
			</div>
		</div>
		<input type="submit" class="button" value="Search" />
		<input	type="submit" class="button" value="See All" />
	</form>
	
</div>





