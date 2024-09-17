var branches = new Tabulator("#divBranchTableTable" , {
	layout: 'fitColumns',
	data: branch,
	pagination: 'local',
	pagination: true,
	paginationSize: 5,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	selectableRows:1,
	movableColumns:true,
	responsiveLayout:true,
	columns: [
		{title:"Branch ID", field: 'branchId'},
		{title:"Branch Name", field: 'branchName'},
		{title:"Active", field: 'isActive'}
	],
});

$('#btnShowUpdateBranches').hide();
$('#btnShowDeleteBranches').hide();

branches.on('rowClick',function() {
	let row = branches.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnShowUpdateBranches').show();
		$('#btnShowDeleteBranches').show();
	} else {
		resetForm();
		$('#btnShowUpdateBranches').hide();
		$('#btnShowDeleteBranches').hide();
	}
})

function populateForm(row) {
	if(row !== undefined) {
		$('#txtUpdateBranchId').val(row.branchId)
		$('#txtUpdateBranchName').val(row.branchName);
		row.isActive === 'y' ? $('#chkUpdateBranchIsActive').prop('checked', true) : $('#chkUpdateBranchIsActive').prop('checked', false);
	}
}

function populateDeleteForm(row) {
	if(row !== undefined) {
		$('#deleteBranchId').val(row.branchId)
		$('#deleteBranchName').val(row.branchName);
		$('#deleteStatus').val(row.isActive);	
	}
}

function createItem(crudOperation) {
	let item;
	if (crudOperation === "create"){
		item = {
			branchId: $('#txtBranchId').val() !== '' ? $('#txtBranchId').val() : null,
			branchName: $('#txtBranchName').val(),
			isActive: $('#chkBranchIsActive').is(':checked') ? 'y' : 'n',
		};
	} else if (crudOperation === "update"){
		item = {
			branchId: $('#txtUpdateBranchId').val() !== '' ? $('#txtUpdateBranchId').val() : '',
			branchName: $('#txtUpdateBranchName').val(),
			isActive: $('#chkUpdateBranchIsActive').is(':checked') ? 'y' : 'n',
		};
	} else if (crudOperation === "delete"){
		item = {
			branchId: $('#deleteBranchId').val() !== '' ? $('#deleteBranchId').val() : '',
			branchName: $('#deleteBranchName').val(),
			isActive: $('#deleteStatus').val()
		}
	}
	return item;
}

function initializeBranchIdMap() {
    let branchIdMap = {};
    $.each(branch, function(index, item) {
        branchIdMap[item.branchId] = true; 
    });
    return branchIdMap;
}

function isBranchNameExists(branchName) {
    return branchNameMap.hasOwnProperty(branchName.toLowerCase());
}


function initializeBranchNameMap() {
    let branchNameMap = {};
    $.each(branch, function(index, item) {
        branchNameMap[item.branchName.toLowerCase()] = true;
    });
    return branchNameMap;
}

function isBranchIdExists(branchId) {
    return branchIdMap.hasOwnProperty(branchId); 
}

function validate(item) {
    var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
    let valid = true;
    if (item.branchName === '') {
        $('#errorMessage').html('Please fill out the Branch Name');
        toastMessage.show();
        valid = false;
    }
    if (isBranchIdExists(item.branchId)) {
        $('#errorMessage').html('Branch ID already exists');
        toastMessage.show();
        valid = false;
    }
    if (isBranchNameExists(item.branchName)) {
        $('#errorMessage').html('Branch Name already exists');
        toastMessage.show();
        valid = false;
    }
    return valid;
}


function recalculateBranches() {
    branchIdMap = initializeBranchIdMap();
	branchNameMap = initializeBranchNameMap();

}



function addItem(crudOperation) {
    var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
    let item = createItem(crudOperation);
    if (validate(item)) {
        $.post('BranchController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('.btnCloseModal').click();
                $('#divAlert').html(response);    
                toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
                toastMessage.show();
                $('#btnMngBranch').click();
            } else {
                $('#errorMessage').html('Unable to save changes');
                toastMessage.show();
            }
        });
    }
}

$('#btnAddBranch').click(function(){
    recalculateBranches();
    addItem("create");
});

$('#btnUpdateBranchId').click(function(){
    recalculateBranches();
    addItem("update");
});

$('#btnDeleteBranch').click(function() {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	if ($('#deleteRawMaterialCode').val() !== '') {
		$.post('BranchController', {
			action: 'deleteItem',
			item: JSON.stringify(createItem("delete"))
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteBranchCancel').click();
				$('#divAlert').html(response);	
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnMngBranch').click();
			} else {
				$('#errorMessage').html.html('Unable to save changes');
				toastMessage.show();
			}
		});
	} else {
		$('#errorMessage').html.html('Please select an item to delete');
		toastMessage.show();
	}
});