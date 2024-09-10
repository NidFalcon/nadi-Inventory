var dispatchTypeTable = new Tabulator("#divDispatchTypeTable" , {
	layout: "fitColumns",
	data: dispatchType,
	pagination: 'local',
	pagination: true,
	paginationSize: 5,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	selectableRows:1,
	movableColumns:true,
	responsiveLayout:true,
	columns: [
		{title:"Dispatch Type Code", field: 'dispatchTypeCode'},
		{title:"Dispatch Type Name", field: 'dispatchTypeName'},
		{title:"Active", field: 'isActive'}
	],
});

$('#btnShowTypeUpdate').hide();
$('#btnShowTypeDelete').hide();

dispatchTypeTable.on('rowClick',function() {
	let row = dispatchTypeTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnShowTypeUpdate').show();
		$('#btnShowTypeDelete').show();
	} else {
		$('#btnShowTypeUpdate').hide();
		$('#btnShowTypeDelete').hide();
	}
})

//populateForm
function populateForm(row) {
	if(row !== undefined) {
		$('#updateDispatchTypeCode').val(row.dispatchTypeCode);
		$('#updateDispatchTypeName').val(row.dispatchTypeName);
		row.isActive === 'y' ? $('#updatecheckActive').prop('checked', true) : $('#updatecheckActive').prop('checked', false);
	}
}

function populateDeleteForm(row) {
	if(row !== undefined) {
		$('#deleteDispatchCode').val(row.dispatchTypeCode);	
		$('#deleteDispatchName').val(row.dispatchTypeName);
		$('#deleteCheckActive').val(row.isActive);	
	}
}

function validate(item) {
    let valid = true;
    
	if (item.isActive !== 'y' && item.isActive !== 'n') {
	        alert('Please select a valid Active status');
	        valid = false;
	}
    
    if (item.dispatchTypeCode === '') {
        alert('Please fill out the Dispatch Type Code');
        valid = false;
    }
    
    if (item.dispatchTypeName === '') {
        alert('Please fill out the Dispatch Type Name');
        valid = false;
    }
    
    return valid;
}


function createItem(crudOperation) {
    let item = {};
    
    switch (crudOperation) {
        case 'create':
            item = {
                isActive: $('#addCheckActive').is(':checked') ? 'y' : 'n',
                dispatchTypeCode: $('#addDispatchCode').val() !== '' ? $('#addDispatchCode').val() : '',
                dispatchTypeName: $('#addDispatchTypeName').val()
            };
            break;

        case 'update':
            item = {
                isActive: $('#updateCheckActive').is(':checked') ? 'y' : 'n',
                dispatchTypeCode: $('#updateDispatchTypeCode').val() !== '' ? $('#updateDispatchTypeCode').val() : '',
                dispatchTypeName: $('#updateDispatchTypeName').val()
            };
            break;

        case 'delete':
            item = {
				isActive: $('#deleteCheckActive').is(':checked') ? 'y' : 'n',
                dispatchTypeCode: $('#deleteDispatchCode').val() !== '' ? $('#deleteDispatchCode').val() : '',
                dispatchTypeName: $('#deleteDispatchName').val()
            };
            break;

        default:
            console.error('Invalid CRUD operation');
            break;
    }
    
    return item;
}


function addItem(crudOperation) {
    let item = createItem(crudOperation);
	console.log(item);
    if (validate(item)) {
        $.post('DispatchTypeController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
				$('.btnCloseAddModal').click();
                $('#btnMngDispatchType').click();
            } else {
                alert('Unable to save changes');
            }
        });
    }
}

$('#btnAddDispatchType').click(function() {
	addItem("create");
});

$('#btnUpdateDispatchType').click(function() {
	addItem("update");
});

$('#btnDeleteDispatchType').click(function() {
    if ($('#txtDispatchTypeCode').val() !== '') {
        let item = createItem('delete');
        $.post('DispatchTypeController', {
            action: 'deleteItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
				$('#btnDeleteDispatchTypeCancel').click();
                $('#btnMngDispatchType').click();
            } else {
                alert('Unable to save changes');
            }
        });
    } else {
        alert('Please select a dispatch type to delete');
    }
});
