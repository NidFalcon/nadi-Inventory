function toggleAddButton() {
    if ($('#txtBranchId').val() === '') {
        $('#btnAdd').html('Add');
    } else {
        $('#btnAdd').html('Update');
    }
}

function bindRowsClick(branch) {
    $.each(branch, function(index, item) {
        $('#item'+index+'row').click(function() {
            $('#chkIsActive').prop('checked', item.isActive === 'y');
            $('#txtBranchId').val(item.branchId);
            $('#txtBranchName').val(item.branchName);
            toggleAddButton();
        });
    });
}

function createBranchTable(branch) {
    let html = '';
    html += '<table class="branch">';
    html += '  <tr>';
    html += '    <th>Branch ID</th>';
    html += '    <th>Branch Name</th>';
    html += '    <th>Active</th>'; 
    html += '  </tr>';
    $.each(branch, function(index, item) {
        html += '<tr id="item'+index+'row">';
        html += '  <td id="item'+index+'id">' + item.branchId + '</td>';
        html += '  <td id="item'+index+'name" class="center-aligned">' + item.branchName + '</td>';
        html += '  <td>' + (item.isActive === 'y' ? 'Yes' : 'No') + '</td>';
        html += '</tr>';
    });
    html += '</table>';
    $('#divBranchTable').html(html);
    bindRowsClick(branch);
}

function createItem() {
    let branchId = $('#txtBranchId').val().trim();
    let item = {
        isActive: $('#chkIsActive').is(':checked') ? 'y' : 'n',
        branchId: branchId === '' ? null : parseInt(branchId, 10),
        branchName: $('#txtBranchName').val().trim()
    };
    return item;
}


function validate(item) {
    let valid = true;
    if (item.branchName === '') {
        alert('Please fill out the Branch Name');
        valid = false;
    }
    return valid;
}

function addItem() {
    let item = createItem();
    if (validate(item)) {
        $.post('BranchController', {
            action: 'saveItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnMngBranch').click();
            } else {
                alert('Unable to save changes');
            }
        });
    }
}

$('#btnAdd').click(addItem);

function resetBranchForm() {
    $('#chkIsActive').prop('checked', false);
    $('#txtBranchId').val('');
    $('#txtBranchName').val('');
    toggleAddButton();
}

$('#btnClear').click(resetBranchForm);

$('#btnDelete').click(function() {
    if ($('#txtBranchId').val() !== '') {
        let item = createItem();
        $.post('BranchController', {
            action: 'deleteItem',
            item: JSON.stringify(item)
        }, function(response) {
            if (response.includes('success')) {
                $('#btnMngBranch').click();
            } else {
                alert('Unable to delete item');
            }
        });
    } else {
        alert('Please select a branch to delete');
    }
});

createBranchTable(branch);
