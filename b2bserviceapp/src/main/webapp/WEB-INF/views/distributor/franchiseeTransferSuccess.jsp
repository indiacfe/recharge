<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="search_panel1">
<h3><b>You have successfully transfered amount.</b></h3>

<table>
  <tr >
    <td><b>Retailer id is:</b> </td>
    <td>${franchId}</td>
  </tr>
  <tr>
    <td><b>Mobile number is:</b></td>
    <td>${mobileNo}</td>
  </tr>
    <tr >
    <td><b>Firm Name is:</b> </td>
    <td>${firmName}</td>
  </tr>
   <tr>
    <td><b>Transfer amount is:</b></td>
    <td>${amount}</td>
  </tr>
   <tr>
    <td><b>Retailer Previous Bal:</b></td>
    <td>${preRetailerBal}</td>
  </tr>
  <tr>
    <td><b>Retailer New Bal:</b></td>
    <td>${newRetailerBal}</td>
  </tr>
</table>
</div>


