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
		{ title: "Branch ID", field: 'branch.branchId' },
		{ title: "Branch Name", field: 'branch.branchName' },
		{ title: "Destination", field: 'destination' },
		{ title: "Dispatch Date", field: 'dispatchDate' }
	],
});

$('#btnShowUpdateDispatching').hide();
$('#btnDelete').hide();
$('.btnConfirmDate').hide();

// Function to show the confirm button when date changes
function handleDateChange() {
	$('.btnConfirmDate').show();
}

$('button[data-bs-target="#addModal"]').click(function() {
	clearAll(); // Clear all fields before showing the modal
});

function clearAll() {
	// Clear Finished Product selection
	$('#selFinishedProdId').val('');

	// Clear Quantity and Date Finished fields (read-only fields)
	$('#txtQuantityFPL').val('');
	$('#txtDateFinished').val('');

	// Clear Dispatch Quantity
	$('#addDispatchQuantity').val(0);

	// Clear Destination
	$('#addDispatchDestination').val('');

	// Reset the Dispatch Date to the current date
	$('#dateSelected').val(getCurrentDate());

	// Optionally hide any confirmation buttons
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

function getFplID() {
	console.log("Tinatawag ako");
	let currentDate = new Date(getCurrentDate());
	let html = '<option value="">';

	let skuToQuantityMap = {};
	$.each(currentInventory, function(index, item) {
		skuToQuantityMap[item[0]] = item[1];
	});

	$.each(finishedProduct, function(index, item) {
		let dateFinished = new Date(item.dateFinished);
		if (dateFinished <= currentDate) {
			html += '<option value="' + item.fplId + '" data-sku-code="' + item.sku.skuCode + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + " " + item.sku.skuName + '</option>';
		}
	});

	$('.selFinishedProd').html(html);

	$('.selFinishedProd').change(function() {
		console.log("Tinatawag ako to change currentQuantity");
		let selectedOption = $(this).find('option:selected');
		let skuCode = selectedOption.data('sku-code');
		totalQuantityAdd = skuToQuantityMap[skuCode] || 0;

		$('.txtSkuName').val(selectedOption.data('sku-name'));
		$('.txtQuantityFPL').val(totalQuantityAdd);
		$('.txtDateFinished').val(selectedOption.data('date-finished'));
		console.log("Current totalQuantityAdd = " + totalQuantityAdd);

	});

	console.log("Current Inventory = " + currentQuantity);

}

getFplID();

function updateFplIDOptionsByDate() {
    let updateDate = new Date($('#updateDate').val());
    //let currentDate = new Date(getCurrentDate()); // Assuming you have a function to get current date
    let html = '<option value="">';

    let skuToQuantityMap = {};
    $.each(currentInventory, function(index, item) {
        skuToQuantityMap[item[0]] = item[1];
    });

    $.each(finishedProduct, function(index, item) {
        let dateFinished = new Date(item.dateFinished);
        if(dateFinished <= updateDate) { 
            dateFinished = skuToQuantityMap[item.sku.skuCode] || 0;
			html += '<option value="' + item.fplId + '" data-sku-code="' + item.sku.skuCode + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + " " + item.sku.skuName + '</option>';
			}
    });
    
    html += '</option>';
    $('#updateFinishedProductId').html(html);

    console.log('Updated Options HTML for Update: ', html);
    /*checkQuantity();
    checkQuantityUpdate();*/
}

function addFplIDOptionsByDate() {
    let selectedDate = new Date($('#dateSelected').val());
    //let currentDate = new Date(getCurrentDate()); // Assuming you have a function to get current date

    let html = '<option value="">';

    let skuToQuantityMap = {};
    $.each(currentInventory, function(index, item) {
        skuToQuantityMap[item[0]] = item[1];
    });

    $.each(finishedProduct, function(index, item) {
        let dateFinished = new Date(item.dateFinished);
        if (dateFinished <= selectedDate) {
            dateFinished = skuToQuantityMap[item.sku.skuCode] || 0;
			html += '<option value="' + item.fplId + '" data-sku-code="' + item.sku.skuCode + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + " " + item.sku.skuName + '</option>';  
        }
    });
    
    html += '</option>';
    $('#selFinishedProdId').html(html);
    console.log('Updated Options HTML for Add:', html);
    /*checkQuantity();
    checkQuantityUpdate();*/
}

$(document).ready(function() {
	$('#dateSelected').val(getCurrentDate());
	$('#updateDate').change(function(){
		updateFplIDOptionsByDate();
    	handleDateChange();
	});
	$('#dateSelected').change(function() {
    	addFplIDOptionsByDate();
    	handleDateChange();
	});
});

/*$('#dateSelected').change(handleDateChange);*/

function checkQuantity() {
	let fplId = $('#selFinishedProdId').val();
	if (!fplId) return; // If no fplId is selected, skip validation

	let dispatchQuantity = parseFloat($('#addDispatchQuantity').val());

	if (isNaN(totalQuantityAdd) || isNaN(dispatchQuantity)) {
		return;
	}

	if (dispatchQuantity > totalQuantityAdd) {
		$('#addDispatchQuantity').val(totalQuantityAdd);
	}

	console.log("totalQuantityAdd = " + totalQuantityAdd);
}

function checkQuantityUpdate() {
	let dispatchQuantityUpdate = parseFloat($('#updateDispatchQuantity').val()); // Updated dispatch quantity
	let fplQuantityUpdate = parseFloat($('#updateFinishedProductId').val()); // Updated dispatch quantity
	if (isNaN(fplQuantityUpdate) || isNaN(dispatchQuantityUpdate)) {
		return; // Return if either quantity is not a number
	}

	// Ensure total quantity doesn't exceed currentQuantity
	if (dispatchQuantityUpdate > fplQuantityUpdate) {
		$('#updateDispatchQuantity').val(fplQuantityUpdate); // Clear invalid input
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
	let selectedFplId = row.fplId; // Save the initial FPL ID from the selected row
	let html = '<option value="">';

	// Populate the form with the dispatch details from the selected row
	$('#updateDispatchId').val(row.dispatchTrackId);
	$('#updateDispatchType').val(row.dispatchType.dispatchTypeCode);
	$('#updateDispatchQuantity').val(row.quantity); // Set initial value to row.quantity
	$('#updateDispatchDestination').val(row.destination);
	$('#updateDate').val(row.dispatchDate);

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

	// Function to handle changes in the FPL ID
	function handleFplChange() {
		let selectedOption = $updateFinishedProductId.find('option:selected');
		let skuCode = selectedOption.data('sku-code');
		let updateDispatchQuantity = parseFloat($('#updateDispatchQuantity').val()) || 0;
		availableQuantity = skuToQuantityMapUpdate[skuCode] || 0;

		// Update the dispatch quantity
		if ($updateFinishedProductId.val() == selectedFplId) {
			$('#updateDispatchQuantity').val(row.quantity);
		} else {
			$('#updateDispatchQuantity').val(0);
		}
		
		if (availableQuantity != 0){
			totalQuantityUpdate = availableQuantity + updateDispatchQuantity; // Total quantity after update
			$('.txtQuantityFPL').val(totalQuantityUpdate);
		} else if (availableQuantity == 0) {
			availableQuantity = updateDispatchQuantity;
			$('.txtQuantityFPL').val(availableQuantity);
		}
		// Update totalQuantityUpdate based on available quantity plus initial dispatch quantity
		//totalQuantityUpdate = availableQuantity + (parseFloat($('#updateDispatchQuantity').val()) || 0) - updateDispatchQuantity;

		// Log values for debugging
		console.log('Selected FPL ID:', $updateFinishedProductId.val());
		console.log('Available Quantity for SKU Code:', availableQuantity);
		console.log('Total Quantity Update:', totalQuantityUpdate);

		// Update the total available quantity and date finished
		$('.txtDateFinished').val(selectedOption.data('date-finished'));
	}

	// Bind the change event handler
	$updateFinishedProductId.off('change').on('change', handleFplChange);

	// Call the function to initialize the form with the default FPL ID values
	handleFplChange();
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
			dispatchDate: $('#dateSelected').val().toString()
		};
	} else if (crudOperation === "update") {
		item = {
			dispatchTrackId: $('#updateDispatchId').val().toString(),
			dispatchTypeCd: $('#updateDispatchType').val().toString(),
			fplId: $('#updateFinishedProductId').val().toString(),
			quantity: $('#updateDispatchQuantity').val().toString(),
			destination: $('#updateDispatchDestination').val().toString(),
			dispatchDate: $('#updateDate').val().toString()
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
	console.log(item);

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
	console.log("Confirming date")
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
	//let fplQuantity = parseFloat($(`option[value="${item.fplId}"]`).data('quantity'));
	console.log("availableQuantity = " + availableQuantity);
	console.log(item.dispatchTrackId)
	if (item.dispatchTrackId === '' || item.dispatchTypeCd === '' || item.fplId === '' ||
		item.quantity === '' || item.branchId === '' || item.destination === '' || item.dispatchDate === '') {
		//$('.errorMessage').text("Please correctly fill-out all required fields");
		alert("Please correctly fill-out all required fields");
		valid = false;
	} else if (!(/^[0-9]\d*$/.test(item.dispatchTrackId))) {
		//$('.errorMessage').text("Dispatch Track ID should only contain positive numbers");
		alert("Dispatch Track ID should only contain positive numbers");
		valid = false;
	} else if (!(/^[1-9][0-9]$/.test(item.fplId))) {
		//$('.errorMessage').text("FPL ID should only contain positive numbers");
		alert("FPL ID should only contain positive numbers");
		valid = false;
	} else if (!(/^[0-9]\d$/.test(item.quantity))) {
		//$('.errorMessage').text("Quantity should only contain positive numbers and zero");
		console.log("Item quantity" + item.quantity)
		alert("Quantity should only contain positive numbers and zero" + item.quantity);
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
	} else if (isNaN(totalQuantityAdd) || item.quantity > parseFloat($('#updateFinishedProductId').val())) {
		alert('Dispatch quantity cannot be greater than available quantity.');
		valid = false;
	}
	else if (item.quantity > 99999999999999) {
		//$('.errorMessage').text("Quantity value is too large");
		alert("Quantity value is too large");
		valid = false;
	}  else if (item.destination.length > 50) {
		//$('.errorMessage').text("Destination characters should be less than 51");
		alert("Destination characters should be less than 51");
		valid = false;
	}
	return valid;
}
createDispatchOptions();
