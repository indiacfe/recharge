function b2bUserLogin()
{
	var urlVal = 'http://178.79.143.57/b2bservicemobileui/login.json';
	var login = $("#loginUserNameId").val();
	var pwd = $("#loginPasswordId").val();
		var auth = make_base_auth(login, pwd);
		sessionStorage.setItem("auth", auth);
		$.support.cors = true;
		$.ajax({
			url : urlVal,
			cache : false,
			data : '{"userName":"' + $("#loginUserNameId").val() + '","password":"'
					+ $("#loginPasswordId").val() + '"}',
			headers : {
				'Authorization' : auth
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Authorization", auth);
			},
			type : "POST",
			contentType : 'application/json',
			success : OnLoginSuccess,
			error : OnLoginError
		});
}
function OnLoginSuccess(data, status, jqXHR) 
{
	if(data)
	{	
	var userName = data.userDetails.userName; 
	var baseId = data.userDetails.baseId;
	var currentBal=data.userDetails.currentBalance;
	var firmName=data.userDetails.firmName;
	var franId=data.userDetails.franId;
	var name=data.userDetails.name;
	
	sessionStorage.setItem("firmName", firmName);
	sessionStorage.setItem("franId", franId);
	sessionStorage.setItem("name", name);
	sessionStorage.setItem("currentBal", currentBal);
	sessionStorage.setItem("userName", userName);
	sessionStorage.setItem("userId",baseId);
	document.location.href = 'menu.html';
	}
	else
	{
		alert("Could not login. Please try again.");
	}	
}

function OnLoginError(request, status, error) {
	sessionStorage.removeItem("userName");
	sessionStorage.removeItem("auth");
	$("#errorMessageBlock").empty();
	var content = '<h5>Entered UserName or Password is wrong</h5>';
	$(content).appendTo("#errorMessageBlock");
}
