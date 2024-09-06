

$("#btnAddProductionMaterial").click(function() {
	let productionMaterials = createProductionMaterialObjects();
	
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: productionMaterials
	}, function(response){
		if (response.includes('success')){
			$('#btnCloseAddSelectModal').click();
		} else {
			alert('unable to add production materials');
		}
	})
})

function createProductionMaterialObjects(){
	let testObjArr = [];
		for (let i = 1; i <= materialCounter; i++){
			testObj = {
				pmId: null,
				dppId: $('#materialDppId').val(),
				materialCode: $(`#selectMaterial${i}`).val(),
				quantityToUse: $(`#materialQuantity${i}`).val()
			}
			
			testObjArr.push(testObj);
		}
	
	return JSON.stringify(testObjArr);
}
/*
var productionMaterialTable = new Tabulator("#divProductionMaterialTable", {
    layout: 'fitColumns',
    data: productionMaterial,
    pagination: 'local',
    paginationSize: 5,
    paginationSizeSelector: [5, 10, 15, 20],
    paginationCounter: "rows",
    selectableRows: 1,
    movableColumns: true,
    responsiveLayout: true,
    columns: [
        {title: "PM ID", field: 'pmId'},
        {title: "DPP ID", field: 'dppId'},
        {title: "Material Code", field: 'materialCode'},
        {title: "Quantity to Use", field: 'quantityToUse'}
    ],
});

function createMaterialOptions() {
    let html = '';
    $.each(rawMaterial, function(index, item) {
        if ("y" === item.isActive) {
            html += '<option id="item' + item.materialCode + '" value="' + item.materialCode + '">' + item.materialCode + '</option>';
        }
    });
    $("#selectMaterialCode").html(html);
    $("#selectUpdateMaterialCode").html(html);
}

function createDppOptions() {
    let html = '';
    $.each(dpp, function(index, item) {
        html += '<option value="' + item.dppId + '">' + item.dppId + '</option>';
    });

    $('#selectDppId').html(html);
    $('#selectUpdateDppId').html(html); 
}

$('#btnShowUpdateMaterial').hide();
$('#btnShowDeleteMaterial').hide();

productionMaterialTable.on('rowClick', function() {
    let row = productionMaterialTable.getSelectedData()[0];
    if (row !== undefined) {
        populateForm(row);
        $('#btnShowUpdateMaterial').show();
        $('#btnShowDeleteMaterial').show();
    } else {
        resetForm();
        $('#btnShowUpdateMaterial').hide();
        $('#btnShowDeleteMaterial').hide();
    }
});

function populateForm(row) {
    if (row !== undefined) {
        $('#txtUpdatePmId').val(row.pmId);
        $('#selectUpdateMaterialCode').val(row.materialCode);
        $('#selectUpdateDppId').val(row.dppId);
        $('#txtUpdateQuantityToUse').val(row.quantityToUse);
		$('#txtDeletePmId').val(row.pmId);
    }
	console.log("#txtDeletePmId: "+$('#txtDeletePmId').val());
}

function createItem(crudOperation) {
    let pmId;
    let item;
    if (crudOperation === "create") {
        item = {
            pmId: null, 
            dppId: $('#selectDppId').val(),
            materialCode: $('#selectMaterialCode').val(),
            quantityToUse: $('#txtQuantityToUse').val()
        };
    } else if (crudOperation === "update") {
        pmId = $('#txtUpdatePmId').val().trim();
        item = {
            pmId: parseInt(pmId, 10),
            dppId: $('#selectUpdateDppId').val(),
            materialCode: $('#selectUpdateMaterialCode').val(),
            quantityToUse: $('#txtUpdateQuantityToUse').val()
        };
    }
    return item;
}

function validate(item) {
    let valid = true;
    if (item.dppId === '' || item.materialCode === '' || item.quantityToUse === '') {
        alert('Please correctly fill out all required fields');
        valid = false;
    } else if (item.quantityToUse < 0) {
        alert('Quantity must be a non-negative number');
        valid = false;
    }
    return valid;
}

function addItem(crudOperation) {
    let item = createItem(crudOperation);
    if (validate(item)) {
        $.post('ProductionMaterialController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnCloseAddModal').click();
				$('#btnCloseUpdateModal').click();
                $('#btnProductionMaterial').click();
            } else {
                alert('Unable to save changes');
            }
        });
    }
}

$('#btnAddMaterial').click(function() {
    addItem("create");
});
$('#btnUpdateMaterial').click(function() {
    addItem("update");
});

function resetForm() {
    $('#txtPmId').val('');
    $('#selectDppId').val('');
    $('#selectMaterialCode').val('');
    $('#txtQuantityToUse').val('');
    $('#btnAddMaterial').show();
    $('#btnUpdateMaterial').hide();
}

$('#btnClearMaterial').click(resetForm);

function deleteItem() {
    let deletePmId = $('#txtDeletePmId').val().trim();
    if (deletePmId !== '') {
        let item = {
            pmId: deletePmId
        };
        $.post('ProductionMaterialController', {
            action: 'deleteItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnCloseDeleteModal').click();
                $('#btnProductionMaterial').click();
            } else {
                alert('Unable to delete item');
            }
        });
    } else {
        alert('Please select an item to delete');
    }
}


$('#btnConfirmDeleteMaterial').click(function() {
    deleteItem()
});

$(document).ready(function() {
	createDppOptions();
    createMaterialOptions();
});

*/