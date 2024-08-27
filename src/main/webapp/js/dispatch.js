function toggleAddButton() {
    if ($('#txtDispatchingId').val() === '') {
        $('#btnAdd').html('Add');
    } else {
        $('#btnAdd').html('Update');
    }
}

function bindRowsClick(dispatch) {
    $.each(dispatch, function(index, item) {
        $('#item' + index + 'row').click(function() {
            $('#txtDispatchingId').val(item.dispatchTrackId);
            $('#txtdispatchType').val(item.dispatchTypeCd);
            $('#txtFinProdId').val(item.fplId);
            $('#txtFinProdName').val(item.fplId);  // Assuming fplId maps to product name
            $('#txtQuantity').val(item.quantity);
            $('#txtBranchId').val(item.branchId);
            $('#txtBranchName').val(item.branchId);  // Assuming branchId maps to branch name
            $('#txtBatchNumber').val(item.batchNumber);
            $('#txtDestination').val(item.destination);
            $('#txtDispatchDate').val(new Date(item.dispatchDate).toISOString().split('T')[0]);  // Formatting date
            toggleAddButton();
        });
    });
}

function createDispatchingTable(dispatch) {
    let html = '';
    html += '<table class="inventory">';
    html += '  <tr>';
    html += '    <th>Dispatch Track ID</th>';
    html += '    <th>Dispatch Type Code</th>';
    html += '    <th>Finished Product ID</th>';
    html += '    <th>Quantity</th>';
    html += '    <th>Branch ID</th>';
    html += '    <th>Destination</th>';
    html += '    <th>Dispatch Date</th>';
    html += '  </tr>';
    $.each(dispatch, function(index, item) {
        html += '<tr id="item' + index + 'row">';
        html += '  <td id="item' + index + 'dispatchTrackId">' + item.dispatchTrackId + '</td>';
        html += '  <td id="item' + index + 'dispatchTypeCd">' + item.dispatchTypeCd + '</td>';
        html += '  <td id="item' + index + 'fplId">' + item.fplId + '</td>';
        html += '  <td id="item' + index + 'qty" class="center-aligned">' + item.quantity + '</td>';
        html += '  <td id="item' + index + 'branchId">' + item.branchId + '</td>';
        html += '  <td id="item' + index + 'destination">' + item.destination + '</td>';
        html += '  <td id="item' + index + 'dispatchDate">' + new Date(item.dispatchDate).toISOString().split('T')[0] + '</td>';  // Formatting date
        html += '</tr>';
    });
    html += '</table>';
    $('#divDispatchingTable').html(html);
    bindRowsClick(dispatch);
}

function createItem() {
    let item = {
        dispatchTrackId: $('#txtDispatchingId').val() !== '' ? $('#txtDispatchingId').val() : '0',
        dispatchTypeCd: $('#txtdispatchType').val(),
        fplId: $('#txtFinProdId').val(),
        quantity: $('#txtQuantity').val(),
        branchId: $('#txtBranchId').val(),
        batchNumber: $('#txtBatchNumber').val(),
        destination: $('#txtDestination').val(),
        dispatchDate: $('#txtDispatchDate').val()
    };
    
    return item;
}

function validate(item) {
    let valid = true;
    if (item.dispatchTypeCd === '' || item.fplId === '' || item.quantity === '' || item.branchId === '' || item.batchNumber === '' || item.destination === '' || item.dispatchDate === '') {
        alert('Please correctly fill out all required fields');
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
        $.post('DispatchingController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnDispatching').click();
            } else {
                alert('Unable to save changes');
            }
        });
    }
}

$('#btnAdd').click(addItem);

function resetDispatchingForm() {
    $('#txtDispatchingId').val('');
    $('#txtdispatchType').val('');
    $('#txtFinProdId').val('');
    $('#txtFinProdName').val('');
    $('#txtQuantity').val('');
    $('#txtBranchId').val('');
    $('#txtBranchName').val('');
    $('#txtBatchNumber').val('');
    $('#txtDestination').val('');
    $('#txtDispatchDate').val('');
    toggleAddButton();
}

$('#btnClear').click(resetDispatchingForm);

$('#btnDelete').click(function() {
    if ($('#txtDispatchingId').val() !== '') {
        let item = createItem();
        $.post('DispatchingController', {
            action: 'deleteItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnDispatching').click();
            } else {
                alert('Unable to delete item');
            }
        });
    } else {
        alert('Please select an item to delete');
    }
});

createDispatchingTable(dispatch);
