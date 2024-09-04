var dppTable = new Tabulator("#divDppTable" , {
	layout: 'fitColumns',
	data: dpp,
	pagination: 'local',
	pagination: true,
	paginationSize: 5,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	selectableRows:1,
	movableColumns:true,
	responsiveLayout:true,
	columns: [
		{title:"DPP ID", field: 'dppId'},
		{title:"Production Date", field: 'productionDate'},
		{title:"Branch ID", field: 'branch.branchId'},
		{title:"SKU Code", field: 'sku.skuCode'},
		{title:"SKU Name", field: 'sku.skuName'},
		{title:"Quantity", field: 'quantity'},
		{title:"Status", field: 'status'}
	],
});

function createOptions(){
	//"selectSkuCode"
	let html = '';
	$.each(sku, function(index, item){
		if ("y" == item.isActive){
			html += '<option id="item'+item.skuCode+'" value="'+"" +item.skuCode+'">'+item.skuCode+ " " +item.skuName+'</option>';
		}
	})
	$(".selectSkuCode").html(html);
}

$('#btnShowUpdateDpp').hide();
$('#btnShowDeleteDpp').hide();

function createItem() {
	let dppId = $('#txtDppId').val().trim();
	let item = {
		dppId: dppId === '' ? null : parseInt(dppId, 10),
		productionDate: $('#txtProductionDate').val(),
		//branchId: $('#txtBranchId').val(),
		skuCode: $('#selectSkuCode').val(),
		quantity: $('#txtQuantity').val(),
		status: $('#selectStatus').val()
	};
	console.log(item)
	return item;
}

function validate(item) {
	let valid = true;
	if (item.productionDate === '' || item.branchId === '' || item.skuCode === '' || item.quantity === '' || item.status === '') {
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
		$.post('DppController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnCloseAddModal').click();
				$('#btnDpp').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}

$('#btnAddDpp').click(addItem);

createOptions();



/*
function toggleAddButton() {
	if ($('#txtDppId').val() === '') {
		$('#btnAdd').text('Add');
	} else {
		$('#btnAdd').text('Update');
	}
}

function bindRowsClick(dpp) {
	$.each(dpp, function(index, item) {
		let rowId = '#item' + index + 'row';
		$(rowId).click(function() {
			$('#txtDppId').val(item.dppId);
			$('#txtProductionDate').val(new Date(item.productionDate).toISOString().split('T')[0]);
			$('#txtBranchId').val(item.branch.branchId);
			$('#txtSkuCode').val(item.sku.skuCode);
			$('#txtQuantity').val(item.quantity);
			$('#selectStatus').val(item.status);
			createOptions(dpp, item.branch.branchId, item.sku.skuCode);
			toggleAddButton();
		});
	});
}

function createDppTable(dpp) {
	let html = '';
	html += '<table class="dpp">';
	html += '  <tr>';
	html += '    <th>DPP ID</th>';
	html += '    <th>Production Date</th>';
	html += '    <th>Branch ID</th>';
	html += '    <th>Branch Name</th>';
	html += '    <th>SKU Code</th>';
	html += '    <th>SKU Name</th>';
	html += '    <th>Quantity</th>';
	html += '    <th>Status</th>';
	html += '  </tr>';
	$.each(dpp, function(index, item) {
		html += '<tr id="item' + index + 'row">';
		html += '  <td>' + item.dppId + '</td>';
		html += '  <td>' + new Date(item.productionDate).toLocaleDateString('en-US', {
			month: '2-digit',
			day: '2-digit',
			year: 'numeric'
		}) + '</td>';
		html += '  <td>' + item.branch.branchId + '</td>';
		html += '  <td>' + item.branch.branchName + '</td>';
		html += '  <td>' + item.sku.skuCode + '</td>';
		html += '  <td>' + item.sku.skuName + '</td>';
		html += '  <td>' + item.quantity + '</td>';
		html += '  <td>' + item.status + '</td>';
		html += '</tr>';
	});
	html += '</table>';
	$('#divDppTable').html(html);
	bindRowsClick(dpp);
}

function createItem() {
	let dppId = $('#txtDppId').val().trim();
	let item = {
		dppId: dppId === '' ? null : parseInt(dppId, 10),
		productionDate: $('#txtProductionDate').val(),
		branchId: $('#txtBranchId').val(),
		skuCode: $('#txtSkuCode').val(),
		quantity: $('#txtQuantity').val(),
		status: $('#selectStatus').val()
	};
	console.log(item)
	return item;
}

function validate(item) {
	let valid = true;
	if (item.productionDate === '' || item.branchId === '' || item.skuCode === '' || item.quantity === '' || item.status === '') {
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
		$.post('DppController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDpp').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}

$('#btnAdd').click(addItem);

function resetDppForm() {
	$('#txtDppId').val('');
	$('#txtProductionDate').val('');
	$('#txtBranchId').val('');
	$('#selectBranchName').val('');
	$('#txtSkuCode').val('');
	$('#selectSkuName').val('');
	$('#txtQuantity').val('');
	$('#selectStatus').val('');
	toggleAddButton();
}

$('#btnClear').click(resetDppForm);

$('#btnDelete').click(function() {
	if ($('#txtDppId').val() !== '') {
		let item = createItem();
		$.post('DppController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDpp').click();
			} else {
				alert('Unable to delete item');
			}
		});
	} else {
		alert('Please select an item to delete');
	}
});

function createOptions(dpp, selectedBranchId, selectedSkuCode, selectedStatus) {
    let branchNameHtml = '';
    let skuNameHtml = '';
    let statusHtml = ''; // New variable for status options

    let branchMap = new Map();
    let skuMap = new Map();
    let statusSet = new Set(); // To store unique statuses

    $.each(dpp, function(index, item) {
        if (!branchMap.has(item.branch.branchId)) {
            branchMap.set(item.branch.branchId, item.branch.branchName);
        }
        if (!skuMap.has(item.sku.skuCode)) {
            skuMap.set(item.sku.skuCode, item.sku.skuName);
        }
        statusSet.add(item.status); // Collect unique statuses
    });

    branchMap.forEach((branchName, branchId) => {
        branchNameHtml += '<option value="' + branchId + '"' + (branchId === selectedBranchId ? ' selected' : '') + '>' + branchName + '</option>';
    });
    $('#selectBranchName').html(branchNameHtml);

    skuMap.forEach((skuName, skuCode, isActive) => {
        skuNameHtml += '<option value="' + skuCode + '"' + (skuCode === selectedSkuCode ? ' selected' : '') + '>' + skuName + '</option>';
    });
    $('#selectSkuName').html(skuNameHtml);

    statusSet.forEach(status => {
        statusHtml += '<option value="' + status + '"' + (status === selectedStatus ? ' selected' : '') + '>' + status + '</option>';
    });
    $('#selectStatus').html(statusHtml);
}

$(document).ready(function() {
    createDppTable(dpp);
    createOptions(dpp, '', '', '');

    $('#selectBranchName').change(function() {
        var selectedBranchId = $(this).val();
        var selectedBranch = dpp.find(function(item) {
            return item.branch.branchId == selectedBranchId;
        });
        $('#txtBranchId').val(selectedBranch ? selectedBranch.branch.branchId : '');
    });

    $('#selectSkuName').change(function() {
        var selectedSkuCode = $(this).val();
        var selectedSku = dpp.find(function(item) {
            return item.sku.skuCode == selectedSkuCode;
        });
        $('#txtSkuCode').val(selectedSku ? selectedSku.sku.skuCode : '');
    });

    $('#selectStatus').change(function() {
        var selectedStatus = $(this).val();
        $('#selectStatus').val(selectedStatus);
    });
});
*/