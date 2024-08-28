function toggleAddButton() {
	if ('' === $('#txtDispatchTypeCode').val()) {
		$('#btnAdd').html('Add');
	} else {
		$('#btnAdd').html('Update');
	}
}

function bindRowsClick(dispatchType) {
	$.each(dispatchType, function(index, item) {
		$('#item'+index+'row').click(function() {
			$('#txtIsActive').val(item.isActive);
			$('#txtDispatchTypeCode').val(item.dispatchTypeCode);
			$('#txtDispatchTypeName').val(item.dispatchTypeName);
			toggleAddButton();
		});
	});
}

function createDispatchTypeTable(dispatchType) {
	let html = '';
	html += '<table class="dispatchType">';
	html += '  <tr>';
	html += '    <th>Dispatch Type Code</th>';
	html += '    <th>Dispatch Type Name</th>';
	html += '  </tr>';
	$.each(dispatchType, function(index, item) {
		if ('y' === item.isActive) {
			html += '<tr id="item'+index+'row">';
			html += '  <td id="item'+index+'code">' + item.dispatchTypeCode + '</td>';
			html += '  <td id="item'+index+'name" class="center-aligned">' + item.dispatchTypeName + '</td>';
			html += '</tr>';
		}
	});
	html += '</table>';
	$('#divDispatchTypeTable').html(html);
	bindRowsClick(dispatchType);
}

function createItem() {
	let item = {
		isActive: $('#txtIsActive').val() !== '' ? $('#txtIsActive').val() : 'y',
		dispatchTypeCode: $('#txtDispatchTypeCode').val() !== '' ? $('#txtDispatchTypeCode').val() : '',
		dispatchTypeName: $('#txtDispatchTypeName').val()
	};
	return item;
}

function validate(item) {
	let valid = true;
	if (item.dispatchTypeName === '') {
		alert('Please fill out the Dispatch Type Name');
		valid = false;
	}
	return valid;
}

function addItem() {
	let item = createItem();
	if (validate(item)) {
		$.post('DispatchTypeController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnMngDispatchType').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}

$('#btnAdd').click(addItem);

function resetDispatchTypeForm() {
	$('#txtIsActive').val('');
	$('#txtDispatchTypeCode').val('');
	$('#txtDispatchTypeName').val('');
	toggleAddButton();
}

$('#btnClear').click(resetDispatchTypeForm);

$('#btnDelete').click(function() {
	if ($('#txtDispatchTypeCode').val() !== '') {
		let item = createItem();
		$.post('DispatchTypeController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnMngDispatchType').click();
			} else {
				alert('Unable to save changes');
			}
		});
	} else {
		alert('Please select a dispatch type to delete');
	}
});

createDispatchTypeTable(dispatchType);
