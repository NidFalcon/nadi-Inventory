var rawMaterialTable = new Tabulator("#divRawMaterialTable" , {
	data: rawMaterialList,
	maxheight:"100px",
	layout:	"fitDataTable",
	responsiveLayout:"hide",
	textDirection:"auto",
	pagination:"local",
	movableColumns:true,
	paginationCounter:"rows",
	paginazationSize: 5,
	paginazationSizeSelector:[5,10],
	selectableRows:1,
	columns: [
		{title:"Material ID", field: 'materialID'},
		{title:"Material Name", field: 'materialName'},
		{title:"Quantity", field: 'quantity'},
		{title:"Date Received", field: 'dateRecieve'}
	],
});

rawMaterialTable.on('rowClick',function() {
	populateForm(rawMaterialTable.getSelectData()[0]);
	$('#btnUpdateMaterial').show();
})

//fill up the form for updates
function populateForm(row) {
	if(row !== undefined) {
		$('#updateRawMaterialName').val(row.materialID);
		$('#updateRawMaterialQuantity').val(row.quantity);
		$('#updateRawMaterialListDateSelected').val(row.dateRecieve);
	}
}

function createItem() {
	let item = {
		materialName:('#rawMaterialListName').val(),
		quantity: $('#rawMaterialListQuantity').val(),
		date:$('#rawMaterialListDateSelected').val()
	};
	
	return item;
}

function validate(item) {
	let valid = true;
	if(item.description === '' || item.quantity == '') {
		alert('Please correctly fill-out all required fields');
		valid = false;
	} else if (item.quantity < 0) {
		alert('Quantity must be a non-negative number');
		valid = false;
	}
	return valid;
}

function addItem() {
	let item = createItem();
	if (validate(item)) {
		$.post('RawMaterialListController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnAddRawMaterials').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}

$('#btnAddRawMaterial').click(addItem);

$('#btnDelete').click(function() {
	if ($('#rawMaterialListName').val() !== '') {
		let item = createItem();
		$.post('RawMaterialListController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#"btnRawMaterials"').click();
			} else {
				alert('Unable to save changes');
			}
		});
	} else {
		alert('Please select an item to delete');
	}
});