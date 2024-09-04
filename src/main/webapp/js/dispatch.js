function toggleAddButton() {
	if ('' === $('#txtDispatchId').val()) {
		$('#btnAdd').html('Add');
	} else {
		$('#btnAdd').html('Update');
	}
}

function bindRowsClick(dispatch) {
	$.each(dispatch, function(index, item) {
		$('#item' + index + 'row').click(function() {
			$('#txtDispatchId').val(item.dispatchTrackId);
			$('#selDispatchType').val(item.dispatchTypeCd);
			$('#txtFinishedProdId').val(item.fplId);
			$('#txtQuantity').val(item.quantity);
			$('#selBranch').val(item.branchId);
			$('#txtDestination').val(item.destination);
			$('#dateDispatchDate').val(item.dispatchDate);
			toggleAddButton();
		});
	});
}

function getDispatchType() {
    let html = '<option value="">';
    $.each(dispatchType, function(index, item) {
        html += '<option value="' + item.dispatchTypeCode + '">' + item.dispatchTypeName + '</option>'
    });
    $('#selDispatchType').html(html);
}

getDispatchType();

function createDispatchingTable(dispatch) {
    let html = '';
    html += '<table class="dispatch">';
    html += '  <tr>';
    html += '    <th>Dispatch Type ID</th>';
    html += '    <th>Dispatch Type Name</th>';
    html += '    <th>Finished Product ID</th>';
    html += '    <th>SKU Name</th>';
    html += '    <th>Quantity</th>';
    html += '    <th>Branch ID</th>';
    html += '    <th>Branch Name</th>';
    html += '    <th>Destination</th>';
    html += '    <th>Dispatch Date</th>';
    html += '  </tr>';
    $.each(dispatch, function(index, item) {
        html += '<tr id="item' + index + 'row">';
        html += '  <td id="item' + index + 'disType" style="align: center;">' + item.dispatchTypeCd + '</td>';
        html += '  <td id="item' + index + 'disName">' + item.dispatchType.dispatchTypeName + '</td>';
        html += '  <td id="item' + index + 'fplId" style="align: center;">' + item.fplId + '</td>';
        html += '  <td id="item' + index + 'skuName">' + item.fpl.sku.skuName + '</td>';
        html += '  <td id="item' + index + 'qty" style="align: center;">' + item.quantity + '</td>';
        html += '  <td id="item' + index + 'brId" style="align: center;">' + item.branchId + '</td>';
        html += '  <td id="item' + index + 'brName">' + item.branch.branchName + '</td>';
        html += '  <td id="item' + index + 'desti">' + item.destination + '</td>';
        html += '  <td id="item' + index + 'disDate">' + item.dispatchDate + '</td>';
        html += '</tr>';
    });
    html += '</table>';
    $('#divDispatchingTable').html(html);
    bindRowsClick(dispatch);
}

function createItem() {
    let item;
    if ($('#txtDispatchId').val() == "") {
        item = {
            dispatchTrackId: '0',
            dispatchTypeCd: $('#selDispatchType').val(),
            fplId: $('#txtFinishedProdId').val(),
            quantity: $('#txtQuantity').val(),
            branchId: $('#hiddenBranchId').val(), // Use hidden field value
            destination: $('#txtDestination').val(),
            dispatchDate: $('#dateDispatchDate').val()
        };
    }
    else {
        item = {
            dispatchTrackId: $('#txtDispatchId').val() !== '' ? $('#txtDispatchId').val() : '0',
            dispatchTypeCd: $('#selDispatchType').val(),
            fplId: $('#txtFinishedProdId').val(),
            quantity: $('#txtQuantity').val(),
            branchId: $('#hiddenBranchId').val(), // Use hidden field value
            destination: $('#txtDestination').val(),
            dispatchDate: $('#dateDispatchDate').val()
        };
    }
    return item;
}

function validate(item) {
    let valid = true;
    if (item.description === '' || item.quantity === '') {
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
    $('#txtDispatchId').val('');
    $('#selDispatchType').val('');
    $('#txtFinishedProdId').val('');
    $('#txtQuantity').val('');
    $('#txtDestination').val('');
    $('#dateDispatchDate').val('');
    toggleAddButton();
}

$('#btnClear').click(resetDispatchingForm);

$('#btnDelete').click(function() {
    if ($('#txtDispatchId').val() !== '') {
        let item = createItem();
        $.post('DispatchingController', {
            action: 'deleteItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnDispatching').click();
            } else {
                alert('Unable to save changes');
            }
        });
    } else {
        alert('Please select an item to delete');
    }
});

createDispatchingTable(dispatch);