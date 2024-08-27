function toggleAddButton() {
	if ('' === $('#txtInventoryId').val()) {
		$('#btnAdd').html('Add');
	} else {
		$('#btnAdd').html('Update');
	}
}

function bindRowsClick(inventory) {
	$.each(inventory, function(index, item) {
		$('#item'+index+'row').click(function() {
			$('#txtActiveTag').val(item.activeTag);
			$('#txtInventoryId').val(item.inventoryId);
			$('#txtDescription').val(item.description);
			$('#txtQuantity').val(item.quantity);
			toggleAddButton();
		});
	});
}

function createInventoryTable(inventory) {
	let html = '';
	html += '<table class="inventory">';
	html += '  <tr>';
	html += '    <th>Item</th>';
	html += '    <th>Quantity</th>';
	html += '  </tr>';
	$.each(inventory, function(index, item) {
		if ('Y' === item.activeTag) {
			html += '<tr id="item'+index+'row">';
			html += '  <td id="item'+index+'desc">' + item.description + '</td>';
			html += '  <td id="item'+index+'qty" class="center-aligned">' + item.quantity + '</td>';
			html += '</tr>';
		}
	});
	html += '</table>';
	$('#divInventoryTable').html(html);
	bindRowsClick(inventory);
}

function createItem() {
	let item = {
		activeTag: $('#txtActiveTag').val() !== '' ? $('#txtActiveTag').val() : 'Y',
		inventoryId: $('#txtInventoryId').val() !== '' ? $('#txtInventoryId').val() : '0',
		description: $('#txtDescription').val(),
		quantity: $('#txtQuantity').val()
	};
	
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
		$.post('InventoryController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnInventory').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}

$('#btnAdd').click(addItem);

function resetInventoryForm() {
	$('#txtActiveTag').val('');
	$('#txtInventoryId').val('');
	$('#txtDescription').val('');
	$('#txtQuantity').val('');
	toggleAddButton();
}

$('#btnClear').click(resetInventoryForm);

$('#btnDelete').click(function() {
	if ($('#txtInventoryId').val() !== '') {
		let item = createItem();
		$.post('InventoryController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnInventory').click();
			} else {
				alert('Unable to save changes');
			}
		});
	} else {
		alert('Please select an item to delete');
	}
});

createInventoryTable(inventory);