/**
 * 
 */
$('#btnCancelRegister').click(function() {
	$('#divContent').load('pages/login.jsp');
});

$('#btnConfirmRegister').click(function() {
	$.post("UserController", {
		action: "register",
		username: $("#txtRegUsername"),
		password: $("#txtRegPassword"),
		branchId: $("#textBranchId")
	}, function() {
		console.log("YAY");
	})
});