var currentQuantity = 0;
var totalQuantityUpdate = 0;
var totalQuantityAdd = 0;
var availableQuantity = 0;

var dispatchTable = new Tabulator("#divDispatchingTable", {
	layout: 'fitDataTable',
	data: dispatch,
	pagination: 'local',
	pagination: true,
	paginationSize: 10,
	paginationSizeSelector: [5, 10, 15, 20],
	paginationCounter: "rows",
	selectableRows: 1,
	movableColumns: true,
	responsiveLayout: true,
	columns: [
		{ title: "Dispatch Track ID", field: 'dispatchTrackId' },
		{ title: "Dispatch Type Name", field: 'dispatchType.dispatchTypeName' },
		{ title: "Finished Product ID", field: 'fplId' },
		{ title: "SKU Name", field: 'fpl.sku.skuName' },
		{ title: "Quantity", field: 'quantity' },
		{ title: "Branch Name", field: 'branch.branchName' },
		{ title: "Destination", field: 'destination' },
		{ title: "Dispatch Date", field: 'dispatchDate' }
	],
});

$('#btnShowUpdateDispatching').hide();
$('#btnDelete').hide();
$('.btnConfirmDate').hide();

function handleDateChange() {
	$('.btnConfirmDate').show();
}

$('button[data-bs-target="#addModal"]').click(function() {
	$('#dateSelected').val(getCurrentDate());
	clearAll(); 
	getFplID();
});

function clearAll() {
	$('#selFinishedProdId').val('');

	$('#txtQuantityFPL').val('');
	$('#txtDateFinished').val('');

	$('#addDispatchQuantity').val(0);

	$('#addDispatchDestination').val('');

	$('.btnConfirmDate').hide();
}

dispatchTable.on('rowClick', function() {
	let row = dispatchTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnShowUpdateDispatching').show();
		$('#btnShowAddDispatching').hide();
		$('#btnDelete').show();
	} else {
		$('#btnShowUpdateDispatching').hide();
		$('#btnShowAddDispatching').show();
		$('#btnDelete').hide();
	}
});

function createDispatchOptions() {
	let html = '';
	$.each(dispatchType, function(index, item) {
		html += '<option id="item' + item.dispatchTypeCode + '" value="' + item.dispatchTypeCode + '">' + item.dispatchTypeName + '</option>';
	});
	$(".selDispatchType").html(html);
}

function getCurrentDate() {
	let date = new Date();
	let day = String(date.getDate()).padStart(2, '0');
	let month = String(date.getMonth() + 1).padStart(2, '0'); // January is 0!
	let year = date.getFullYear();
	return `${year}-${month}-${day}`; // Format as "yyyy-MM-dd"
}

// Helper function to generate HTML for dropdown options
function generateOptionsHtml(finishedProducts, skuToQuantityMap, dateFilter) {
    let html = '<option value="">';

    $.each(finishedProducts, function(index, item) {
        let dateFinished = new Date(item.dateFinished);
		let skuCode = item.sku.skuCode;
		let quantity = skuToQuantityMap[skuCode] || 0;
        if (dateFinished <= dateFilter && quantity > 0) {
            
            html += `<option value="${item.fplId}" 
                            data-sku-code="${skuCode}" 
                            data-sku-name="${item.sku.skuName}" 
                            data-quantity="${item.quantity}" 
                            data-date-finished="${dateFinished.toLocaleDateString()}"
                            data-branch-id="${item.branchId}">
                            ${item.fplId} ${item.sku.skuName}
                     </option>`;
        }
    });

    html += '</option>';
    return html;
}

function getFplID() {
    let currentDate = new Date(getCurrentDate());
    let skuToQuantityMap = {};
    
    $.each(currentInventory, function(index, item) {
        skuToQuantityMap[item[0]] = item[1];
    });

    let html = generateOptionsHtml(finishedProduct, skuToQuantityMap, currentDate);

    $('.selFinishedProd').html(html);

    $('.selFinishedProd').change(function() {
        let selectedOption = $(this).find('option:selected');
        let skuCode = selectedOption.data('sku-code');
        let branchId = selectedOption.data('branch-id');
        totalQuantityAdd = skuToQuantityMap[skuCode] || 0;

        $('#addBranchId').val(branchId);
        $('.txtSkuName').val(selectedOption.data('sku-name'));
        $('.txtQuantityFPL').val(totalQuantityAdd);
        $('.txtDateFinished').val(selectedOption.data('date-finished'));

    });
}

function updateFplIDOptionsByDate() {
    let updateDate = new Date($('#updateDate').val());
    let skuToQuantityMap = {};

    $.each(currentInventory, function(index, item) {
        skuToQuantityMap[item[0]] = item[1];
    });

    let html = generateOptionsHtml(finishedProduct, skuToQuantityMap, updateDate);
    
    $('#updateFinishedProductId').html(html);
}

function addFplIDOptionsByDate() {
    let selectedDate = new Date($('#dateSelected').val());
    let skuToQuantityMap = {};

    $.each(currentInventory, function(index, item) {
        skuToQuantityMap[item[0]] = item[1];
    });

    let html = generateOptionsHtml(finishedProduct, skuToQuantityMap, selectedDate);
    
    $('#selFinishedProdId').html(html);
    
    clearAll(); 
}

$(document).ready(function() {
	$('#dateSelected').val(getCurrentDate());
	$('#updateDate').change(function() {
		updateFplIDOptionsByDate();
		handleDateChange();
	});
	$('#dateSelected').change(function() {
		addFplIDOptionsByDate();
		handleDateChange();
	});
});

function checkQuantity() {
	let fplId = $('#selFinishedProdId').val();
	if (!fplId) return; 

	let dispatchQuantity = parseFloat($('#addDispatchQuantity').val());

	if (isNaN(totalQuantityAdd) || isNaN(dispatchQuantity)) {
		return;
	}

	if (dispatchQuantity > totalQuantityAdd) {
		$('#addDispatchQuantity').val(totalQuantityAdd);
	}

}

function checkQuantityUpdate() {
	let dispatchQuantityUpdate = parseFloat($('#updateDispatchQuantity').val()); 
	let fplQuantityUpdate = parseFloat($('.txtQuantityFPL').val()); 
	
	if (isNaN(availableQuantity) || isNaN(dispatchQuantityUpdate)) {
		return; 
	}

	// Ensure total quantity doesn't exceed currentQuantity
	if (dispatchQuantityUpdate > fplQuantityUpdate) {
		$('#updateDispatchQuantity').val(fplQuantityUpdate);
	} 
	
	if (availableQuantity == 0) {
		if (dispatchQuantityUpdate > fplQuantityUpdate) {
            $('#updateDispatchQuantity').val(fplQuantityUpdate); 
        }
	}

}

$('#updateDispatchQuantity').on('input', checkQuantityUpdate);
$('#addDispatchQuantity').on('input', checkQuantity);

function populateForm(row) {
	if (!row) {
		console.error('Invalid row data');
		return;
	}

	let currentDate = new Date(getCurrentDate());
	let selectedFplId = row.fplId; 

	let html = '<option value="">';

	// Populate the form with the dispatch details from the selected row
	$('#updateDispatchId').val(row.dispatchTrackId);
	$('#updateDispatchType').val(row.dispatchType.dispatchTypeCode);
	$('#updateDispatchQuantity').val(row.quantity); 
	$('#updateDispatchDestination').val(row.destination);
	$('#updateDate').val(row.dispatchDate);
	$('#updateBranchId').val(row.branch.branchId);
	
	// Logic for populating FPL options
	let skuToQuantityMapUpdate = {};
	$.each(currentInventory, function(index, item) {
		skuToQuantityMapUpdate[item[0]] = item[1];
	});

	let $updateFinishedProductId = $('#updateFinishedProductId');

	$.each(finishedProduct, function(index, item) {
		let dateFinished = new Date(item.dateFinished);
		if (dateFinished <= currentDate) {
			html += `<option value="${item.fplId}" data-sku-code="${item.sku.skuCode}" data-sku-name="${item.sku.skuName}" data-quantity="${item.quantity}" data-date-finished="${new Date(item.dateFinished).toLocaleDateString()}">${item.fplId} ${item.sku.skuName}</option>`;
		}
	});

	$updateFinishedProductId.html(html);

	// Set the initial FPL ID based on the selected row
	$updateFinishedProductId.val(selectedFplId);
	
	function initialFpl(){
		let selectedOption = $updateFinishedProductId.find('option:selected');
			let skuCode = selectedOption.data('sku-code');
			let updateDispatchQuantity = parseFloat($('#updateDispatchQuantity').val()) || 0;
			availableQuantity = skuToQuantityMapUpdate[skuCode] || 0;
			$('.txtDateFinished').val(selectedOption.data('date-finished'));
				
			if (availableQuantity != 0) {
				totalQuantityUpdate = availableQuantity + updateDispatchQuantity; // Total quantity after update
				$('.txtQuantityFPL').val(totalQuantityUpdate);
			}
			else if (availableQuantity == 0) {
				$('.txtQuantityFPL').val(updateDispatchQuantity);
			}
	}
	
	function updateFplChange() {
		initialFpl();
		$('.txtQuantityFPL').val(availableQuantity);
		$('#updateDispatchQuantity').val(0);
	}
	
	$updateFinishedProductId.off('change').on('change', updateFplChange);

	initialFpl();
}

function populateDeleteForm(row) {
	if (row !== undefined) {
		$('#deleteDispatchId').val(row.dispatchTrackId);
		$('#deleteDispatchName').val(row.dispatchType.dispatchTypeName);
		$('#deleteFinishedProductId').val(row.fplId);
		$('#deleteSkuName').val(row.fpl.sku.skuName);
		$('#deleteDispatchQuantity').val(row.quantity);
		$('#deleteBranchId').val(row.branch.branchId);
		$('#deleteBranchName').val(row.branch.branchName);
		$('#deleteDestination').val(row.destination);
		$('#deleteDispatchDate').val(row.dispatchDate);
	}
}

function createItem(crudOperation) {
	let item;

	if (crudOperation === "create") {
		item = {
			dispatchTrackId: $('#addDispatchId').val().toString(),
			dispatchTypeCd: $('#addDispatchType').val().toString(),
			fplId: $('#selFinishedProdId').val().toString(),
			quantity: $('#addDispatchQuantity').val().toString(),
			destination: $('#addDispatchDestination').val().toString(),
			dispatchDate: $('#dateSelected').val().toString(),
			branchId: $('#addBranchId').val().toString()
		};
	} else if (crudOperation === "update") {
		item = {
			dispatchTrackId: $('#updateDispatchId').val().toString(),
			dispatchTypeCd: $('#updateDispatchType').val().toString(),
			fplId: $('#updateFinishedProductId').val().toString(),
			quantity: $('#updateDispatchQuantity').val().toString(),
			destination: $('#updateDispatchDestination').val().toString(),
			dispatchDate: $('#updateDate').val().toString(),
			branchId: $('#updateBranchId').val().toString()
		};
	} else if (crudOperation === "delete") {
		item = {
			dispatchTrackId: $('#deleteDispatchId').val().toString(),
			dispatchTypeCd: $('#updateDispatchType').val().toString(),
			fplId: $('#deleteFinishedProductId').val().toString(),
			quantity: $('#deleteDispatchQuantity').val().toString(),
			destination: $('#deleteDestination').val().toString(),
			dispatchDate: $('#deleteDispatchDate').val().toString(),
			branchId: $('#deleteBranchId').val().toString()
		};
	}
	return item;
}

function createDeleteItem() {
	let json = {
		dispatchId: $('#deleteDispatchId').val().toString(),
		dispatchName: $('#deleteDispatchName').val().toString(),
		finishedProductId: $('#deleteFinishedProductId').val().toString(),
		skuName: $('#deleteSkuName').val().toString(),
		quantity: $('#deleteDispatchQuantity').val().toString(),
		branchId: $('#deleteBranchId').val().toString(),
		branchName: $('#deleteBranchName').val().toString(),
		destination: $('#deleteDestination').val().toString(),
		dispatchDate: $('#deleteDispatchDate').val().toString()
	};

	return json;
}

function addItem(isAdd) {
	let item = createItem(isAdd);

	// Perform validation checks
	if (!validate(item)) {
		return; // Stop execution if validation fails
	}

	$.post('DispatchingController', {
		action: 'saveItem',
		item: JSON.stringify(item)
	}, function(response) {
		if (response.includes('success')) {
			$('.btnCloseAddModal').click();
			$('#btnDispatching').click();
		} else {
			alert('Unable to save changes');
		}
	});
}

$('.btnConfirmDate').click(function() {
	$('.btnConfirmDate').hide(); // Hide the confirm button after updating
});

$('#btnAddDispatch').click(function() {
	addItem("create");
});

$('#btnUpdateDispatch').click(function() {
	addItem("update");
});

$('#btnDeleteDispatch').click(function() {
	if ($('#deleteDispatchId').val() !== '') {
		$.post('DispatchingController', {
			action: 'deleteItem',
			item: JSON.stringify(createItem("delete"))
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteDispatchCancel').click();
				$('#btnDispatching').click();
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

function validate(item) {
	let valid = true;
	if (item.dispatchTrackId === '' || item.dispatchTypeCd === '' || item.fplId === '' ||
		item.quantity === '' || item.branchId === '' || item.destination === '' || item.dispatchDate === '') {
		//$('.errorMessage').text("Please correctly fill-out all required fields");
		alert("Please correctly fill-out all required fields");
		valid = false;
	} else if (!(/^[0-9]\d*$/.test(item.dispatchTrackId))) {
		//$('.errorMessage').text("Dispatch Track ID should only contain positive numbers");
		alert("Dispatch Track ID should only contain positive numbers");
		valid = false;
	} else if (!(/^[1-9]\d*$/.test(item.fplId))) {
    alert("FPL ID should only contain positive numbers");
    valid = false;
	} else if (!(/^[0-9]\d*$/.test(item.quantity))) {
		//$('.errorMessage').text("Quantity should only contain positive numbers and zero");
		alert("Quantity should only contain positive numbers and zero");
		valid = false;
	} else if (!(!isNaN(Date.parse(item.dispatchDate)) && (new Date(item.dispatchDate).toISOString().startsWith(item.dispatchDate)))) {
		//$('.errorMessage').text("Please enter valid date");
		alert("Please enter valid date");
		valid = false;
	} else if (item.dispatchTrackId > 99999999999999) {
		//$('.errorMessage').text("Dispatch Track ID value is too large");
		alert("Dispatch Track ID value is too large");
		valid = false;
	} else if (item.dispatchTypeCd.length > 10) {
		//$('.errorMessage').text("Dispatch Type Code characters should be less than 11");
		alert("Dispatch Type Code characters should be less than 11");
		valid = false;
	} else if (item.fplId > 99999999999999) {
		//$('.errorMessage').text("FPL ID value is too large");
		alert("FPL ID value is too large");
		valid = false;
	}
	else if (item.quantity > 99999999999999) {
		//$('.errorMessage').text("Quantity value is too large");
		alert("Quantity value is too large");
		valid = false;
	} else if (item.destination.length > 50) {
		//$('.errorMessage').text("Destination characters should be less than 51");
		alert("Destination characters should be less than 51");
		valid = false;
	}
	return valid;
}


createDispatchOptions();
