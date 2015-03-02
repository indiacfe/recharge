<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div class="grid_10">
	<div class="box round first">
		<h2>
			<label style="font-size: 20px;">Transaction Details</label>
		</h2>
		<div class="block ">

			<table class="form">
				<tbody>


					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">TID:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${refundetails.transid}</label></td>
					</tr>
					<%-- <tr>
						<td><label style="font-size: 20px; color: #CC0000;">Connection/Mobile No:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${requestItem.mobileNo}${requestItem.connectionid}</label></td>
					</tr> --%>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Operator:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${refundetails.operator}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Amount(Rs.):</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">Rs.${refundetails.amount}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Refund
								Amount(Rs.):</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">Rs.${refundetails.refundAmount}</label></td>
					</tr>

					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Credit
								Amount(Rs.):</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">Rs.${refundetails.creditAmountFranchisee}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Mobile/Connection
								No.:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${refundetails.mobileNo}${refundetails.connectionid}</label></td>
					</tr>
					<tr>
						<td><label style="font-size: 20px; color: #CC0000;">Status:</label></td>
						<td><label style="font-size: 20px; color: #CC0000;">${refundetails.status}</label></td>
					</tr>
					<tr>
						<td></td>
						<td align="center"><input type="submit" name="submit"
							class="btn btn-large" value="Go Back"
							onclick="javascript: history.go(-1);" style="width: 101px" /></td>

					</tr>

				</tbody>
			</table>


		</div>
	</div>
</div>




