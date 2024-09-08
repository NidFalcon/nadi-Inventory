var dppTable = new Tabulator("#divDppTable" , {
	layout: 'fitDataFill',
	data: dpp,
	pagination: 'local',
	pagination: true,
	paginationSize: 10,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	selectableRows:1,
	movableColumns:true,
	responsiveLayout:true,
	columns: [
		{title:"DPP ID", field: 'dppId'},
		{title:"Production Date", field: 'productionDate'},
		{title:"Branch ID", field: 'branch.branchId'},
		{title:"SKU Code", field: 'sku.skuCode'},
		{title:"SKU Name", field: 'sku.skuName'},
		{title:"Quantity", field: 'quantity'},
		{title:"Status", field: 'status'}
	],
});

function createSkuOptions(){
	let html = '';
	$.each(sku, function(index, item){
		if ("y" == item.isActive){
			html += '<option id="item'+item.skuCode+'" value="'+"" +item.skuCode+'">'+item.skuCode+ " " +item.skuName+'</option>';
		}
	})
	$("#selectSkuCode").html(html);
}

function createRawMaterialOptions(){
	let html = '';
	$.each(rawMaterial, function(index, item){
		if ("y" == item.isActive){
			html += '<option id="item'+item.materialCode+'" value="'+"" +item.materialCode+'">'+item.materialCode+ " " +item.materialName+'</option>';
		}
	})
	$(".selectMaterial").html(html);
	console.log(html);
}

$('#btnShowMaterialDpp').hide();
$('#btnShowUpdateDpp').hide();
$('#btnShowDeleteDpp').hide();

dppTable.on('rowClick',function() {
	let row = dppTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		//populateDeleteForm(row);
		$('#btnShowMaterialDpp').show();
		$('#btnShowUpdateDpp').show();
		$('#btnShowDeleteDpp').show();
	} else {
		$('#btnShowMaterialDpp').hide();
		$('#btnShowUpdateDpp').hide();
		$('#btnShowDeleteDpp').hide();
	}
})

function populateForm(row) {
	if(row !== undefined) {
		$('#txtUpdateDppId').val(row.dppId)
		$('#selectUpdateSkuCode').val(row.skuCode);
		$('#txtUpdateProductionDate').val(row.productionDate);
		$('#txtUpdateQuantity').val(row.quantity);
		$('#selectUpdateStatus').val(row.status);
		$('#txtDeleteDppId').val(row.dppId);
		$('#materialDppId').val(row.dppId);
	}
}

function createItem(crudOperation) {
	let dppId;
	let item;
	if (crudOperation === "create"){
		dppId = $('#txtDppId').val().trim();
		item = {
			dppId: dppId === '' ? null : parseInt(dppId, 10),
			productionDate: $('#txtProductionDate').val(),
			skuCode: $('#selectSkuCode').val(),
			quantity: $('#txtQuantity').val(),
			status: $('#selectStatus').val()
		};
	} else if (crudOperation === "update"){
		dppId = $('#txtUpdateDppId').val().trim();
		item = {
			dppId: dppId === '' ? null : parseInt(dppId, 10),
			productionDate: $('#txtUpdateProductionDate').val(),
			skuCode: $('#selectUpdateSkuCode').val(),
			quantity: $('#txtUpdateQuantity').val(),
			status: $('#selectUpdateStatus').val()
		};
	}
	console.log(item)
	return item;
}

function validate(item) {
	let valid = true;
	if (item.productionDate === '' || item.branchId === '' || item.skuCode === '' || item.quantity === '' || item.status === '') {
		alert('Please correctly fill out all required fields');
		valid = false;
	} else if (item.quantity < 0) {
		alert('Quantity must be a non-negative number');
		valid = false;
	}
	return valid;
}

function addItem(crudOperation) {
	let item = createItem(crudOperation);
	if (validate(item)) {
		$.post('DppController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnCloseAddModal').click();
				$('#btnCloseUpdateModal').click();
				$('#btnDpp').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}

$('#btnAddDpp').click(function(){
	addItem("create");
});
$('#btnUpdateDpp').click(function(){
	addItem("update");
});

createSkuOptions();
createRawMaterialOptions();

// for Adding Materials

function addselect() {
	let html = '';
	html += '<tr id="newMaterialSelect">',
	html += '	<td><Select class="form-select selectMaterial" id="selectMaterial">',
	html += '		<option></option>',
	html += '	</Select></td>',
	html += '	<td><input type="number" class="form-control" min="1"></td>',
	html += '</tr>'
	
	$('#selectAdd').click(function () {
		$('.table').append(html)
	})
	
	$('#btnCloseAddSelectModal').click(function () {
		$('.table #newMaterialSelect').remove();
	})
}	

addselect();


