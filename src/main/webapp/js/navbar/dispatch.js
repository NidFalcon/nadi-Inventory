var fplQuantity = 0;
var currentQuantity = 0;
var totalQuantityUpdate = 0;

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

// Function to show the confirm button when date changes
function handleDateChange() {
    $('.btnConfirmDate').show();
}

dispatchTable.on('rowClick', function() {
    let row = dispatchTable.getSelectedData()[0];
    if (row !== undefined) {
        populateForm(row);
        populateDeleteForm(row);
        $('#btnShowUpdateDispatching').show();
        $('#btnDelete').show();
    } else {
        $('#btnShowUpdateDispatching').hide();
        $('#btnDelete').hide();
    }
})

function createOptions() {
    let html = '';
    $.each(dispatchType, function(index, item) {
        html += '<option id="item' + item.dispatchTypeCode + '" value="' + item.dispatchTypeCode + '">' + item.dispatchTypeName + '</option>';
    });
    $(".selDispatchType").html(html);
}

// Validation
function getCurrentDate() {
    let date = new Date();
    let day = String(date.getDate()).padStart(2, '0');
    let month = String(date.getMonth() + 1).padStart(2, '0'); // January is 0!
    let year = date.getFullYear();
    return `${year}-${month}-${day}`; // Format as "yyyy-MM-dd"
}

function updateFplIDOptionsByDate() {
    let selectedDate = new Date($('#dateSelected').val());
    let updateDate = new Date($('#updateDate').val());

    console.log('Selected Date:', selectedDate); // Debugging statement

    let html = '<option value="">';
    let html2 = '<option value="">';
    $.each(finishedProduct, function(index, item) {
        let dateFinished = new Date(item.dateFinished);
        console.log('Finished Product Date:', dateFinished); // Debugging statement

        if (dateFinished <= selectedDate) {
            html += '<option value="' + item.fplId + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + " " + item.sku.skuName + '</option>';
        }
        if (dateFinished <= updateDate) {
            html2 += '<option value="' + item.fplId + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + " " + item.sku.skuName + '</option>';
        }
    });

    html += '</option>';
    html2 += '</option>';
    $('#selFinishedProdId').html(html);
    $('#updateFinishedProductId').html(html2);

    console.log('Updated Options HTML:', html);
    checkQuantity();
    checkQuantityUpdate();
}

$(document).ready(function() {
    $('#dateSelected').val(getCurrentDate());
    $('#dateSelected').change(updateFplIDOptionsByDate);
});

$('#dateSelected').change(handleDateChange);
$('#updateDate').change(handleDateChange);

function checkQuantity() {
    let fplId = $('#selFinishedProdId').val();
    if (!fplId) return; // If no fplId is selected, skip validation

    /let fplQuantity = parseFloat($(`option[value="${fplId}"]`).data('quantity'));/
    let dispatchQuantity = parseFloat($('#addDispatchQuantity').val());

    if (isNaN(currentQuantity) || isNaN(dispatchQuantity)) {
        return;
    }

    if (dispatchQuantity > currentQuantity) {
        $('#addDispatchQuantity').val(totalQuantityUpdate);
    }
}

function checkQuantityUpdate() {
    let dispatchQuantityUpdate = parseFloat($('#updateDispatchQuantity').val()); // Updated dispatch quantity

    if (isNaN(currentQuantity) || isNaN(dispatchQuantityUpdate)) {
        return; // Return if either quantity is not a number
    }
    
    // Ensure total quantity doesn't exceed currentQuantity
    if (dispatchQuantityUpdate > totalQuantityUpdate) {
/*        alert('Dispatch quantity cannot be greater than available quantity.'); */
        $('#updateDispatchQuantity').val(totalQuantityUpdate); // Clear invalid input
    }
}

$('#updateDispatchQuantity').on('input', checkQuantityUpdate);
$('#addDispatchQuantity').on('input', checkQuantity);

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
        currentQuantity = skuToQuantityMap[skuCode] || 0;

        $('.txtSkuName').val(selectedOption.data('sku-name'));
        $('.txtQuantityFPL').val(currentQuantity);
        $('.txtDateFinished').val(selectedOption.data('date-finished'));
    });
}

function populateForm(row) {
	let dateFinished = new Date(row.fpl.dateFinished).toLocaleDateString();
	    if (row !== undefined) {
	        $('#updateDispatchId').val(row.dispatchTrackId);
	        $('#updateDispatchType').val(row.dispatchType.dispatchTypeCode);
	        $('#updateFinishedProductId').val(row.fplId);
	        $('#updateSkuCode').val(row.fpl.sku.skuCode);
	        $('#updateDispatchQuantity').val(row.quantity);
	        $('#updateDispatchDestination').val(row.destination);
	        $('#updateDate').val(row.dispatchDate);
			
			console.log("Dispatch Quantity = " + row.quantity);
			console.log("Current Quantity = " + currentQuantity);
		
			let skuToQuantityMap = {};
		    $.each(currentInventory, function(index, item) {
		        skuToQuantityMap[item[0]] = item[1];
		    });
			
	        let selectedOption = $(this).find('option:selected');
	        let skuCd = row.fpl.sku.skuCode;
	        currentQuantity = skuToQuantityMap[skuCd] || 0;
			
			totalQuantityUpdate = row.quantity + currentQuantity; // Total quantity after update
			
	        $('.txtSkuName').val(selectedOption.data('sku-name'));
	        $('.txtQuantityFPL').val(totalQuantityUpdate);
	        $('.txtDateFinished').val(dateFinished);

	    }
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
	            dispatchTrackId: $('#addDispatchId').val(),
	            dispatchTypeCd: $('#addDispatchType').val(),
	            fplId: $('#selFinishedProdId').val(),
	            quantity: $('#addDispatchQuantity').val(),
	            destination: $('#addDispatchDestination').val(),
	            dispatchDate: $('#dateSelected').val()
	        };
	    } else if (crudOperation === "update") {
	        item = {
	            dispatchTrackId: $('#updateDispatchId').val(),
	            dispatchTypeCd: $('#updateDispatchType').val(),
	            fplId: $('#updateFinishedProductId').val(),
	            quantity: $('#updateDispatchQuantity').val(),
	            destination: $('#updateDispatchDestination').val(),
	            dispatchDate: $('#updateDate').val()
	        };
	    } else if (crudOperation === "delete") {
	        item = {
	            dispatchTrackId: $('#deleteDispatchId').val(),
	            dispatchTypeCd: $('#updateDispatchType').val(),
	            fplId: $('#deleteFinishedProductId').val(),
	            quantity: $('#deleteDispatchQuantity').val(),
	            destination: $('#deleteDestination').val(),
	            dispatchDate: $('#deleteDispatchDate').val(),
	            branchId: $('#deleteBranchId').val()
	        };
	    }
	    return item;
	}

	function createDeleteItem() {
	    let json = {
	        dispatchId: $('#deleteDispatchId').val(),
	        dispatchName: $('#deleteDispatchName').val(),
	        finishedProductId: $('#deleteFinishedProductId').val(),
	        skuName: $('#deleteSkuName').val(),
	        quantity: $('#deleteDispatchQuantity').val(),
	        branchId: $('#deleteBranchId').val(),
	        branchName: $('#deleteBranchName').val(),
	        destination: $('#deleteDestination').val(),
	        dispatchDate: $('#deleteDispatchDate').val()
	    };

	    return json;
	}

	function validate(item) {
	    let valid = true;
	    if (item.dispatchTrackId === '' || item.quantity === '' || item.dispatchDate === '') {
	        alert('Please correctly fill out all required fields');
	        valid = false;
	    } else if (item.quantity < 0) {
	        alert('Quantity must be a non-negative number');
	        valid = false;
	    }
	    return valid;
	}

	function addItem(isAdd) {
	    let item = createItem(isAdd);
	    console.log(item);

	    // Perform validation checks
	    if (!validate(item)) {
	        return; // Stop execution if validation fails
	    }

	    // Additional client-side validation before sending data
	    if (!validateQuantities(item)) {
	        return; // Stop execution if quantity validation fails
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

	function validateQuantities(item) {
	    let isValid = true;

	    // Check if FPL ID is selected
	    if (!item.fplId) {
	        alert('Please select a finished product.');
	        isValid = false;
	    }

	    // Check if quantity is valid
	    let fplQuantity = parseFloat($(`option[value="${item.fplId}"]`).data('quantity'));
	    if (isNaN(fplQuantity) || item.quantity > fplQuantity) {
	        alert('Dispatch quantity cannot be greater than available quantity.');
	        isValid = false;
	    }

	    return isValid;
	}

	$('.btnConfirmDate').click(function() {
	    console.log("confirming date");
	    updateFplIDOptionsByDate();
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

	createOptions();
	getFplID();