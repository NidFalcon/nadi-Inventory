var fplQuantity = 0;
var currentQuantity = 0;
var totalQuantityUpdate = 0;
var totalQuantityAdd = 0;

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
    fplQuantity = 0;
    currentQuantity = 0;
    totalQuantityUpdate = 0;
    
    console.log('Current Quantity: ', currentQuantity);
    console.log('Total Quantity Update: ', totalQuantityUpdate);
    console.log('FPL Quantity: ', fplQuantity);

    // Clear Finished Product selection
    $('#selFinishedProdId').val(''); 

    // Clear Quantity and Date Finished fields (read-only fields)
    $('#txtQuantityFPL').val(0);
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

    let dispatchQuantity = parseFloat($('#addDispatchQuantity').val());

    if (isNaN(totalQuantityAdd) || isNaN(dispatchQuantity)) {
        return;
    }

    if (dispatchQuantity > totalQuantityAdd) {
        $('#addDispatchQuantity').val(totalQuantityAdd);
    }
}

function checkQuantityUpdate() {
    let dispatchQuantityUpdate = parseFloat($('#updateDispatchQuantity').val()); // Updated dispatch quantity

    if (isNaN(totalQuantityUpdate) || isNaN(dispatchQuantityUpdate)) {
        return; // Return if either quantity is not a number
    }
    
    // Ensure total quantity doesn't exceed currentQuantity
    if (dispatchQuantityUpdate > totalQuantityUpdate) {
        $('#updateDispatchQuantity').val(totalQuantityUpdate); // Clear invalid input
    }
}

$('#updateDispatchQuantity').on('input', checkQuantityUpdate);
$('#addDispatchQuantity').on('input', checkQuantity);

function getFplID() {
    console.log("Fetching FPL ID");
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
        console.log("Updating currentQuantity on Add");
        let selectedOption = $(this).find('option:selected');
        let skuCode = selectedOption.data('sku-code');
        totalQuantityAdd = skuToQuantityMap[skuCode] || 0;

        $('.txtQuantityFPL').val(totalQuantityAdd);
        $('.txtDateF	inished').val(selectedOption.data('date-finished'));
		        
		    });
		}
		getFplID();


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

		    $.each(finishedProduct, function(index, item) {
		        let dateFinished = new Date(item.dateFinished);
		        if (dateFinished <= currentDate) {
		            html += `<option value="${item.fplId}" data-sku-code="${item.sku.skuCode}" data-sku-name="${item.sku.skuName}" data-quantity="${item.quantity}" data-date-finished="${new Date(item.dateFinished).toLocaleDateString()}">${item.fplId} ${item.sku.skuName}</option>`;
		        }
		    });

		    let $updateFinishedProductId = $('#updateFinishedProductId');
		    $updateFinishedProductId.html(html);

		    // Set the initial FPL ID based on the selected row
		    $updateFinishedProductId.val(selectedFplId);

		    // Function to handle changes in the FPL ID
		    function handleFplChange() {
		        let selectedOption = $updateFinishedProductId.find('option:selected');
		        let skuCode = selectedOption.data('sku-code');
		        let availableQuantity = skuToQuantityMapUpdate[skuCode] || 0;

		        // Update the dispatch quantity
		        if ($updateFinishedProductId.val() == selectedFplId) {
		            $('#updateDispatchQuantity').val(row.quantity);
		        } else {
		            $('#updateDispatchQuantity').val(0);
		        }
		        
		        // Update totalQuantityUpdate based on available quantity plus initial dispatch quantity
		    	totalQuantityUpdate = availableQuantity + (parseFloat($('#updateDispatchQuantity').val()) || 0);
		        
		        // Log values for debugging
		        console.log('Selected FPL ID:', $updateFinishedProductId.val());
		        console.log('Available Quantity for SKU Code:', availableQuantity);
		        console.log('Total Quantity Update:', totalQuantityUpdate);
		        
		        // Update the total available quantity and date finished
		        $('.txtQuantityFPL').val(totalQuantityUpdate);
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
				    console.log("Confirming date");
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

				createDispatchOptions();