function toggleAddButton() {
    if ('' === $('#txtMaterialCode').val()) {
        $('#btnAdd').html('Add');
    } else {
        $('#btnAdd').html('Update');
    }
}

function bindRowsClick(rawMaterial) {
    $.each(rawMaterial, function(index, item) {
        $('#item' + index + 'row').click(function() {
            $('#chkIsActive').prop('checked', item.isActive === 'y');
            $('#txtMaterialCode').val(item.materialCode);
            $('#txtMaterialName').val(item.materialName);
            $('#txtUnitOfMeasurement').val(item.unitOfMeasurement);
            toggleAddButton();
        });
    });
}

function createRawMaterialTable(rawMaterial) {
    let html = '';
    html += '<table class="rawMaterial">';
    html += '  <tr>';
    html += '    <th>Material Code</th>';
    html += '    <th>Material Name</th>';
    html += '    <th>Unit of Measurement</th>';
    html += '    <th>Active</th>'; 
    html += '  </tr>';
    $.each(rawMaterial, function(index, item) {
        html += '<tr id="item' + index + 'row">';
        html += '  <td id="item' + index + 'code">' + item.materialCode + '</td>';
        html += '  <td id="item' + index + 'name" class="center-aligned">' + item.materialName + '</td>';
        html += '  <td>' + item.unitOfMeasurement + '</td>';
        html += '  <td>' + (item.isActive === 'y' ? 'Yes' : 'No') + '</td>';
        html += '</tr>';
    });
    html += '</table>';
    $('#divRawMaterialTable').html(html);
    bindRowsClick(rawMaterial);
}

function createItem() {
    let item = {
        isActive: $('#chkIsActive').is(':checked') ? 'y' : 'n',
        materialCode: $('#txtMaterialCode').val() !== '' ? $('#txtMaterialCode').val() : '',
        materialName: $('#txtMaterialName').val(),
        unitOfMeasurement: $('#txtUnitOfMeasurement').val()
    };
    return item;
}

function validate(item) {
    let valid = true;
    if (item.materialName === '') {
        alert('Please fill out the Material Name');
        valid = false;
    }
    return valid;
}

function addItem() {
    let item = createItem();
    if (validate(item)) {
        $.post('RawMaterialController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnMngRawMaterial').click();
            } else {
                alert('Unable to save changes');
            }
        });
    }
}

$('#btnAdd').click(addItem);

function resetRawMaterialForm() {
    $('#chkIsActive').prop('checked', false);
    $('#txtMaterialCode').val('');
    $('#txtMaterialName').val('');
    $('#txtUnitOfMeasurement').val('');
    toggleAddButton();
}

$('#btnClear').click(resetRawMaterialForm);

$('#btnDelete').click(function() {
    if ($('#txtMaterialCode').val() !== '') {
        let item = createItem();
        $.post('RawMaterialController', {
            action: 'deleteItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnMngRawMaterial').click();
            } else {
                alert('Unable to delete item');
            }
        });
    } else {
        alert('Please select a Raw Material to delete');
    }
});

createRawMaterialTable(rawMaterial);
