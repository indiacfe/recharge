		function rechargeAgain() {
			window.location.href = "mobileRecharge.html";
			location.reload();
		}
		function setData()
		{
			$('#transid').val(sessionStorage.getItem("transid"));
			$('#date').val(sessionStorage.getItem("date"));
			$('#operator').val(sessionStorage.getItem("operator"));
			$('#amount').val(sessionStorage.getItem("amount"));
			$('#mobileNo').val(sessionStorage.getItem("mobileNo"));
			$('#preBal').val(sessionStorage.getItem("preBal"));
			$('#newBal').val(sessionStorage.getItem("newBal"));
		}	
		function getCircleName() 
		{
			var urlVal = 'http://178.79.143.57/b2bservicemobileui/getcirclename/'+$('#cname').val()+'.json';
			$.support.cors=true;
			var auth= sessionStorage.getItem("auth");
			$.ajax({
				url : urlVal,
				cache : false,
				headers:{
					'Authorization':auth
				},		
				beforeSend : function(xhr) {
				xhr.setRequestHeader("Authorization",auth);
				},		
				contentType : "application/json",
				type : "GET",	
				dataType : 'application/json',
				success : onCircleFetchSuccess,
				error : onCircleFetchError
			});
		}	
		function onCircleFetchSuccess(data,status) {
				var str = data.split("@");
					
				if (data != "") 
				{
					$("#selcircle").empty();
					sessionStorage.removeItem("circleCode");
					var auxArr = "";
					for ( var i = 0; i < str.length; i++) 
						{
							var circle = str[i].split("#");
							if (circle[0] == 'DELHI') {
								auxArr += '<option value="'+ circle[1] +'" selected="selected">'
										+ circle[0] + '</option>';
							} else {
								auxArr += '<option value="'+ circle[1] +'">'
										+ circle[0] + '</option>';
							}
						}
					$("#selcircle").html(auxArr);
				}
			}
	function onCircleFetchError(data, status, error)
	{
		//alert("error");
	}
function doTelecomRecharge() 
	{
		var userId=sessionStorage.getItem("userId"); 
		var urlVal = 'http://178.79.143.57/b2bservicemobileui/doTelecomRecharge.json';
		$.support.cors=true;
		var auth= sessionStorage.getItem("auth");
			$.ajax({
					url : urlVal,
					cache : false,
					data : '{ "cname" : "' + $("#cname").val()
							+ '", "mobileNo" : "' + $("#mobileNo").val()
							+ '", "circleCode" : "' + $("#selcircle").val()							
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
				sessionStorage.setItem("operator",$("#cname").val());
				sessionStorage.setItem("mobileNo",$("#mobileNo").val());
				sessionStorage.setItem("amount",$("#amount").val());
				sessionStorage.setItem("transid",transid);
				sessionStorage.setItem("date",dateVal);
				sessionStorage.setItem("preBal",preBal);
				sessionStorage.setItem("newBal",newBal);
				sessionStorage.removeItem("currentBal");
				sessionStorage.setItem("currentBal",newBal);
				window.location.href="mobilerechargesuccess.html";						
			}	
					//			document.getElementById('operator').innerHTML=operator;			
	}
	function onMobileRechargeError(data, status, error) {
		alert("Could Not Recharge");
	}
