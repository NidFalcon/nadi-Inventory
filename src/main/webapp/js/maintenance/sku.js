function toggleAddButton() {
    if ('' === $('#txtSkuCode').val()) {
        $('#btnAdd').html('Add');
    } else {
        $('#btnAdd').html('Update');
    }
}

function bindRowsClick(sku) {
    $.each(sku, function(index, item) {
        $('#item' + index + 'row').click(function() {
            $('#chkIsActive').prop('checked', item.isActive === 'y');
            $('#txtSkuCode').val(item.skuCode);
            $('#txtSkuName').val(item.skuName);
            $('#txtUnitOfMeasurement').val(item.unitOfMeasurement);
            toggleAddButton();
        });
    });
}

function createSkuTable(sku) {
    let html = '';
    html += '<table class="sku">';
    html += '  <tr>';
    html += '    <th>SKU Code</th>';
    html += '    <th>SKU Name</th>';
    html += '    <th>Unit of Measurement</th>';
    html += '    <th>Active</th>'; 
    html += '  </tr>';
    $.each(sku, function(index, item) {
        html += '<tr id="item' + index + 'row">';
        html += '  <td id="item' + index + 'code">' + item.skuCode + '</td>';
        html += '  <td id="item' + index + 'name" class="center-aligned">' + item.skuName + '</td>';
        html += '  <td>' + item.unitOfMeasurement + '</td>';
        html += '  <td>' + (item.isActive === 'y' ? 'Yes' : 'No') + '</td>';
        html += '</tr>';
    });
    html += '</table>';
    $('#divSkuTable').html(html);
    bindRowsClick(sku);
}

function createItem() {
    let item = {
        isActive: $('#chkIsActive').is(':checked') ? 'y' : 'n',
        skuCode: $('#txtSkuCode').val() !== '' ? $('#txtSkuCode').val() : '',
        skuName: $('#txtSkuName').val(),
        unitOfMeasurement: $('#txtUnitOfMeasurement').val()
    };
    return item;
}

function validate(item) {
    let valid = true;
    if (item.skuName === '') {
        alert('Please fill out the SKU Name');
        valid = false;
    }
    return valid;
}

function addItem() {
    let item = createItem();
    if (validate(item)) {
        $.post('SkuController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnMngSku').click();
            } else {
                alert('Unable to save changes');
            }
        });
    }
}

$('#btnAdd').click(addItem);

function resetSkuForm() {
    $('#chkIsActive').prop('checked', false);
    $('#txtSkuCode').val('');
    $('#txtSkuName').val('');
    $('#txtUnitOfMeasurement').val('');
    toggleAddButton();
}

$('#btnClear').click(resetSkuForm);

$('#btnDelete').click(function() {
    if ($('#txtSkuCode').val() !== '') {
        let item = createItem();
        $.post('SkuController', {
            action: 'deleteItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnMngSku').click();
            } else {
                alert('Unable to save changes');
            }
        });
    } else {
        alert('Please select a SKU to delete');
    }
});

createSkuTable(sku);
