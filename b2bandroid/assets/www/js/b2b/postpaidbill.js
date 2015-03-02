function rechargeAgain() {
	window.location.href = "postPaidBill.html";
}
function setData() {
	$('#transid').val(sessionStorage.getItem("transid"));
	$('#date').val(sessionStorage.getItem("date"));
	$('#operator').val(sessionStorage.getItem("operator"));
	$('#amount').val(sessionStorage.getItem("amount"));
	$('#mobileNo').val(sessionStorage.getItem("mobileNo"));
	$('#preBal').val(sessionStorage.getItem("preBal"));
	$('#newBal').val(sessionStorage.getItem("newBal"));
}
function doPostPaidBillPay() {
	var userId=sessionStorage.getItem("userId");
	var urlVal = 'http://178.79.143.57/b2bservicemobileui/doPostpaidBillPay.json';
	$.support.cors = true;
	var auth = sessionStorage.getItem("auth");
	$.ajax({
		url : urlVal,
		cache : false,
		data : '{ "cname" : "' + $("#operator").val() + '", "mobileNo" : "'
				+ $("#mobileNo").val() + '", "amount" : "' + $("#amount").val()
				+ '", "userId" : "' + userId
				+ '"}',
		headers : {
			'Authorization' : auth
		},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization", auth);
		},
		contentType : "application/json",
		type : "POST",
		dataType : 'application/json',
		success : onMobileRechargeSuccess,
		error : onMobileRechargeError
	});
}
function onMobileRechargeSuccess(data, status) {
	   var vals = data.split(";");
		var message=vals[1];
		if(message=="error")
		{
			alert("Please try again. Error in Transaction.");
		}
		else
		{						
			
			var transid = vals[1];
			var dateVal = vals[2];
			var newBal=vals[3];
			var preBal=vals[4];
			sessionStorage.setItem("operator",$("#operator").val());
			sessionStorage.setItem("mobileNo",$("#mobileNo").val());
			sessionStorage.setItem("amount",$("#amount").val());
			sessionStorage.setItem("transid",transid);
			sessionStorage.setItem("date",dateVal);
			sessionStorage.setItem("preBal",preBal);
			sessionStorage.setItem("newBal",newBal);
			sessionStorage.removeItem("currentBal");
			sessionStorage.setItem("currentBal",newBal);
			window.location.href="postpaidbillsuccess.html";						
		}
}
function onMobileRechargeError(data, status, error) {
	alert("Could Not Recharge");
}
