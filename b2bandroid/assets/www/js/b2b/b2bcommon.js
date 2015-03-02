function pageLoading() {
	document.addEventListener("backbutton", onBackKeyDown, false);
}
function setCurrentBalance()
{
	$('#currentBalance').val(sessionStorage.getItem("currentBal"));
}
function setAccountDetails()
{
	$('#name').val(sessionStorage.getItem("name"));
	$('#currentBal').val(sessionStorage.getItem("currentBal"));
	$('#firmName').val(sessionStorage.getItem("firmName"));
	$('#franId').val(sessionStorage.getItem("franId"));
	$('#mobNo').val(sessionStorage.getItem("userName"));
}
function setFirmNameAndCurrBal()
{
	var firmName=sessionStorage.getItem("firmName");
	var currentBalance=sessionStorage.getItem("currentBal");
	currentBalance= Math.round(currentBalance * 1000) / 1000;
	$('#firmName').append(firmName);
	$('#currentBalance').append(currentBalance);
}
function getPageName(url) {
    var index = url.lastIndexOf("/") + 1;
    var filenameWithExtension = url.substr(index);
    var filename = filenameWithExtension.split(".")[0]; // <-- added this line
    return filename;                                    // <-- added this line
}
function onBackKeyDown(e) {
	if (getPageName(window.location.pathname) == "menu")
	{
	}
	else if (getPageName(window.location.pathname) == "index")
	{
		exitAppPopup();
	}
	else {
		history.back();
	}
}
function exitAppPopup() {
	navigator.notification.confirm('Exit Cyber Tel App?', function(button) {
		if (button == 2) {
			navigator.app.exitApp();
		}
	}, 'Exit', 'No,Yes');
	return false;
}
