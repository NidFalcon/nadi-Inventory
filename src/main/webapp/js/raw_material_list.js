var rawMaterialTable = new Tabulator("#divRawMaterialTable" , {
	layout: 'fitColumns',
	data: rawMaterialList,
	pagination: 'local',
	pagination: true,
	paginationSize: 5,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	selectableRows:1,
	movableColumns:true,
	responsiveLayout:true,
	columns: [
		{title:"Material ID", field: 'materialListId'},
		{title:"Material Code", field: 'material.materialCode'},
		{title:"Material Name", field: 'material.materialName'},
		{title:"Quantity", field: 'quantity'},
		{title:"User ID", field: 'userId'},
		{title:"Date", field: 'dateRecieve'},
		{title:"Batch Id", field:'branchId'}
	],
});


$('#btnUpdateMaterial').hide();
$('#btnDeleteMaterial').hide();

rawMaterialTable.on('rowClick',function() {
	let row = rawMaterialTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnUpdateMaterial').show();
		$('#btnDeleteMaterial').show();
	} else {
		resetForm();
		$('#btnUpdateMaterial').hide();
		$('#btnDeleteMaterial').hide();
	}
})

function createOptions(){
	let html = '';
	$.each(materialOptions, function(index, item){
		if ("y" == item.isActive){
						html += '<option id="item'+item.materialCode+'" value="'+"" +item.materialCode+'">'+item.materialCode+ " " +item.materialName+'</option>';
		}
	})
	$("select").html(html);
}

//fill up the form for updates
function populateForm(row) {
	if(row !== undefined) {
		$('#updateRawMaterialId').val(row.materialListId);
		$('#updateRawMaterialName').val(row.material.materialCode);
		$('#updateRawMaterialQuantity').val(row.quantity);
		$('#updateDate').val(row.dateRecieve);
	}
}

//fill up the form to delete
function populateDeleteForm(row) {
	if(row !== undefined) {
		$('#deleteRawMaterialId').val(row.materialListId);
		$('#deleteRawMaterialCode').val(row.material.materialCode);
		$('#deleteRawMaterialQuantity').val(row.quantity);
		$('#deleteRawMaterialUserId').val(row.userId);
		$('#deleteRawMaterialDate').val(row.dateRecieve);
		$('#deleteRawMaterialBranchId').val(row.branchId);	
	}
}

function resetForm() {
	rawMaterialTable.deselectRow();	
	$('#updateRawMaterialName').val(''),
	$('#updateRawMaterialQuantity').val(''),
	$('#updateRawMaterialListDateSelected').val('')
};

function createItem(isAdd) {
	let item = {
		materialListId: isAdd ? $("#rawMaterialId").val() : $("#updateRawMaterialId").val(),
		materialCode: isAdd ? $('#rawMaterialListName').val() : $("#updateRawMaterialName").val(),
		quantity: parseInt( isAdd ? $('#rawMaterialListQuantity').val() : $("#updateRawMaterialQuantity").val()),
		dateRecieve: isAdd ? $('#dateSelected').val() : $("#updateDate").val()
	};
	return item;
}

function createDeleteItem(){
	let json = {
		    materialListId: $('#deleteRawMaterialId').val(),
		    materialCode: $('#deleteRawMaterialCode').val(),
		    quantity: $('#deleteRawMaterialQuantity').val(),
		    userId: $('#deleteRawMaterialUserId').val(),
		    dateRecieve: $('#deleteRawMaterialDate').val(),
		    branchId: $('#deleteRawMaterialBranchId').val()
	}
	
	return json;
}

function validate(item) {
	let valid = true;
	if(item.materialCode === '' || item.quantity === '' || item.date === '') {
		alert('Please correctly fill-out all required fields');
		valid = false;
	} else if (item.quantity < 0) {
		alert('Quantity must be a non-negative number');
		valid = false;
	}
	return valid;
}

function addItem(isAdd) {
	console.log("clicked");
	let item = createItem(isAdd);
	console.log(item);
	if (validate(item)) {
		$.post('RawMaterialListController', {
			action: 'saveRawMaterial',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseAddModal').click();
				$('#btnRawMaterials').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}


$('#btnAddRawMaterial').click(function(){
	addItem(true);
});
$('#btnUpdateRawMaterial').click(function(){
	addItem(false);
});

$('#btnDeleteRawMaterial').click(function() {
	if ($('#deleteRawMaterialId').val() !== '') {

		$.post('RawMaterialListController', {
			action: 'deleteRawMaterial',
			item: JSON.stringify(createDeleteItem())
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteRawMaterialCancel').click();
				$('#btnRawMaterials').click();
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

createOptions();
console.log(rawMaterialList);