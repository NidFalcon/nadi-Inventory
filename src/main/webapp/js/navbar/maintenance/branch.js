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

function validate(item) {
    let valid = true;
    if (item.branchName === '') {
        alert('Please fill out the Branch Name');
        valid = false;
    }
    return valid;
}

function addItem(crudOperation) {
    let item = createItem(crudOperation);
    if (validate(item)) {
        $.post('BranchController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
				$('.btnCloseModal').click();
                $('#btnMngBranch').click();
            } else {
                alert('Unable to save changes');
            }
        });
    }
}

$('#btnAddBranch').click(function(){
	addItem("create");
});
$('#btnUpdateBranchId').click(function(){
	addItem("update");
});

$('#btnDeleteBranch').click(function() {
	console.log(JSON.stringify(createItem("delete")));
	if ($('#deleteRawMaterialCode').val() !== '') {
		$.post('BranchController', {
			action: 'deleteItem',
			item: JSON.stringify(createItem("delete"))
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteBranchCancel').click();
				$('#btnMngBranch').click();
			} else {
				$('#divAlert').removeClass('d-none');
				$('#divAlert').html('Unable to save changes');
			}
		});
	} else {
		$('#divAlert').removeClass('d-none');
		$('#divAlert').html('Please select an item to delete');
	}
});
