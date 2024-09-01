/**
 * 
 */

$('#btnCancelRegister').click(function() {
	$('#divContent').load('pages/login.jsp');
});

function createOptions() {
    let html = '';
    $.each(branches, function(index, item) {
        if ("y" === item.isActive) {
            html += '<option id="item' + item.branchId + '" value="' + item.branchId + '">' + item.branchId + ' ' + item.branchName + '</option>';
        }
    });

    $("#txtBranchId").html(html);
};

function createUserJSON() {
    let userObject = {
        userId: 0,
        username: $("#txtRegUsername").val(),
        password: $("#txtRegPassword").val(),
        branchId: $("#txtBranchId").val()
    };

    return JSON.stringify(userObject);
}

$('#btnConfirmRegister').click(function() {
    let newUser = createUserJSON();
    console.log(newUser);
    $.post("UserController", {
        action: "registerNewUser",
        user: newUser
    }, function(response) {
        if(response.includes("success")){
			console.log("YAY");
		} else {
			console.log(":(");
		}
    });
});


createOptions();