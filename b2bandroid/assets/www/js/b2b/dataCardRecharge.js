		function rechargeAgain() {
			window.location.href = "dataCardRecharge.html";
		}
		function setData()
		{
			$('#transid').val(sessionStorage.getItem("transid"));
			$('#date').val(sessionStorage.getItem("date"));
			$('#operator').val(sessionStorage.getItem("operator"));
			$('#amount').val(sessionStorage.getItem("amount"));
			$('#connectionid').val(sessionStorage.getItem("connectionid"));
			$('#preBal').val(sessionStorage.getItem("preBal"));
			$('#newBal').val(sessionStorage.getItem("newBal"));
		}
function doDataCardRecharge() 
	{
		var userId=sessionStorage.getItem("userId");
		var urlVal = 'http://178.79.143.57/b2bservicemobileui/doDataCardRecharge.json';
		$.support.cors=true;
		var auth= sessionStorage.getItem("auth");
			$.ajax({
					url : urlVal,
					cache : false,
					data : '{ "cname" : "' + $("#operator").val()
							+ '", "datacardnumber" : "' + $("#connectionid").val()				
							+ '", "amount" : "' + $("#amount").val()
							+ '", "userId" : "' + userId
							+ '"}',
					headers:{
						'Authorization':auth
					},		
					beforeSend : function(xhr) {
					xhr.setRequestHeader("Authorization",auth);
					},		
					contentType : "application/json",
					type : "POST",
					dataType : 'application/json',
					success : onDataCardRechargeSuccess,
					error : onDataCardRechargeError
				});
	}
	function onDataCardRechargeSuccess(data, status) {
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
				sessionStorage.setItem("connectionid",$("#connectionid").val());
				sessionStorage.setItem("amount",$("#amount").val());
				sessionStorage.setItem("transid",transid);
				sessionStorage.setItem("date",dateVal);
				sessionStorage.setItem("preBal",preBal);
				sessionStorage.setItem("newBal",newBal);
				sessionStorage.removeItem("currentBal");
				sessionStorage.setItem("currentBal",newBal);
				window.location.href="datacardrechargesuccess.html";						
			}
	}
	function onDataCardRechargeError(data, status, error) {
		alert("Could Not Recharge");
	}
