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
		
		var productionMaterialFiltered = productionMaterial.filter(material => material.dppId === row.dppId);
		
		if (productionMaterialFiltered.length !== 0){
			$('#divProductionMaterialTable').show();
			var productionMaterialTable = new Tabulator("#divProductionMaterialTable", {
			    layout: 'fitColumns',
			    data: productionMaterialFiltered,
			    pagination: 'local',
			    paginationSize: 3,
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
		} else {
			$('#divProductionMaterialTable').hide();
		}
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

var materialCounter = 0; // Counter to track added material rows

// Function to dynamically add materials
function addSelect() {
    // Increment counter for each added material row
    materialCounter++;

    // Create a new row for material and quantity input with unique IDs
    let html = `
        <tr id="newMaterialSelect${materialCounter}">
			<td>
                <select class="form-select selectMaterial" id="selectMaterial${materialCounter}">
                    ${createRawMaterialOptions()}
                </select>
            </td>
            <td>
                <input type="number" class="form-control" id="materialQuantity${materialCounter}" min="1" placeholder="Enter quantity" />
            </td>
            <td>
                <button class="btn btn-danger" type="button" onclick="removeMaterial(${materialCounter})">X</button>
            </td>
        </tr>
    `;

    // Append the new row to the table
    $('.table').append(html);
}

// Function to dynamically create options for the select dropdown
function createRawMaterialOptions() {
    let optionsHtml = '';
    $.each(rawMaterial, function(index, item) {
        if (item.isActive === "y") {
            optionsHtml += `<option value="${item.materialCode}">${item.materialCode} ${item.materialName}</option>`;
        }
    });
    return optionsHtml;
}

// Function to remove a material row by counter
function removeMaterial(counter) {
    $(`#newMaterialSelect${counter}`).remove(); // Remove the specific row
}

// Event handler for the 'Add Material' button
$('#selectAdd').on('click', function(e) {
    addSelect(); // Call function to add material row
});

// Event handler for the modal close button to remove added rows
$('#btnCloseAddSelectModal').on('click', function() {
    $('.table tr[id^="newMaterialSelect"]').remove(); // Remove all dynamically added rows
});



