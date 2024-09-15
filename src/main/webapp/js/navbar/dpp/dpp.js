var dppTable = new Tabulator("#divDppTable", {
	layout: 'fitDataFill',
	data: dpp,
	pagination: 'local',
	pagination: true,
	paginationSize: 10,
	paginationSizeSelector: [5, 10, 15, 20],
	paginationCounter: "rows",
	selectableRows: 1,
	movableColumns: true,
	responsiveLayout: true,
	columns: [
		{ title: "DPP ID", field: 'dppId' },
		{ title: "Production Date", field: 'productionDate' },
		{ title: "Branch ID", field: 'branch.branchId' },
		{ title: "SKU Code", field: 'sku.skuCode' },
		{ title: "SKU Name", field: 'sku.skuName' },
		{ title: "Quantity", field: 'quantity' },
		{ title: "Status", field: 'status' }
	],
});

$('#btnShowAddPm').hide();
$('#btnShowUpdatePm').hide();
$('#btnShowUpdateDpp').hide();
$('#btnShowDeleteDpp').hide();

dppTable.on('rowClick', function() {
	let row = dppTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		$('#btnShowAddPm').show();
		$('#btnShowUpdateDpp').show();
		$('#btnShowDeleteDpp').show();
	} else {
		$('#btnShowAddPm').hide();
		$('#btnShowUpdatePm').hide();
		$('#btnShowUpdateDpp').hide();
		$('#btnShowDeleteDpp').hide();
	}
})

$('#txtProductionDate').val(new Date().toISOString().split('T')[0]);

function populateForm(row) {
	if (row !== undefined) {
		populateUpdateDppForm(row);
		$('#txtDeleteDppId').val(row.dppId);
		$('#materialDppId').val(row.dppId);
		$('#dppSkuName').val(row.sku.skuName);
		$('#updateMaterialDppId').val(row.dppId);
		$('#updateDppSkuName').val(row.sku.skuName);
		filterProductionMaterial(row);
	}
}

function populateUpdateDppForm(row) {
	$('#txtUpdateDppId').val(row.dppId)
	$('#selectUpdateSkuCode').val(row.skuCode);
	$('#txtUpdateProductionDate').val(row.productionDate);
	$('#txtUpdateQuantity').val(row.quantity);
	$('#selectUpdateStatus').val(row.status);
}


function createSkuOptions() {
	let html = '';
	$.each(sku, function(index, item) {
		if (item.isActive === "y") {
			html += `<option id="item${item.skuCode}" value="${item.skuCode}">
						${item.skuCode} ${item.skuName}
					</option>`;
		}
	});
	$("#selectSkuCode, #selectUpdateSkuCode").html(html);
}

createSkuOptions();

function createItem(crudOperation) {
	let dppId;
	let item;
	if (crudOperation === "create") {
		item = {
			dppId: null,
			productionDate: $('#txtProductionDate').val(),
			skuCode: $('#selectSkuCode').val(),
			quantity: $('#txtQuantity').val(),
			status: $('#selectStatus').val()
		};
	} else if (crudOperation === "update") {
		dppId = $('#txtUpdateDppId').val().trim();
		item = {
			dppId: dppId === '' ? null : parseInt(dppId, 10),
			productionDate: $('#txtUpdateProductionDate').val(),
			skuCode: $('#selectUpdateSkuCode').val(),
			quantity: $('#txtUpdateQuantity').val(),
			status: $('#selectUpdateStatus').val()
		};
	}
	return item;
}

function addItem(crudOperation) {
	let item = createItem(crudOperation);
	if (validate(item)) {
		$.post('DppController', {
			action: 'saveItem',
			item: JSON.stringify(item),
			operation: crudOperation
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseAddModal').click();
				$('.btnCloseUpdateModal').click();

				$('#divAlert').html(response);
				var $toastLiveExample = $('#successToast');
				var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
				toastBootstrap.show();

				$('#btnDpp').click();
			} else {
				$('#divAlert').html(response);
				var $toastLiveExample = $('#liveToast');
				var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
				toastBootstrap.show();
			}
		});
	}
}

$('#btnAddDppSubmit').click(function() {
	addItem("create");
});

$('#btnUpdateDppSubmit').click(function() {
	addItem("update");
});

function deleteItem() {
	let deleteDppId = $('#txtDeleteDppId').val().trim();
	if (deleteDppId !== '') {
		let item = {
			dppId: deleteDppId
		};
		$.post('DppController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseDeleteModal').click();

				var $toastLiveExample = $('#successToast');
				var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
				toastBootstrap.show();

				$('#btnDpp').click();
			} else {
				$('#divAlert').html(response);
				var $toastLiveExample = $('#liveToast');
				var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
				toastBootstrap.show();
			}
		});
	}
}

$('#btnConfirmDeleteDpp').click(function() {
	deleteItem()
});

// Production Materials
var productionMaterialTable;
var productionMaterialFiltered;

function filterProductionMaterial(row) {
	productionMaterialFiltered = productionMaterial.filter(function(material) {
		return material.dppId === row.dppId;
	});

	if (productionMaterialFiltered.length !== 0) {
		$('#btnShowUpdatePm').show();
		$('#materialDppIdContainer').hide();
		$('#dppSkuNameContainer').hide();
		$('#updateDppSkuNameContainer').hide();
		$('#divProductionMaterialTable').show();
		productionMaterialTable = new Tabulator("#divProductionMaterialTable", {
			layout: 'fitColumns',
			data: productionMaterialFiltered,
			pagination: 'local',
			paginationSize: 3,
			paginationCounter: "rows",
			selectableRows: 1,
			movableColumns: true,
			responsiveLayout: true,
			columns: [
				{ title: "PM ID", field: 'pmId' },
				{ title: "DPP ID", field: 'dppId' },
				{ title: "Material Code", field: 'materialCode' },
				{ title: "Quantity to Use", field: 'quantityToUse' }
			],
		});
	} else {
		$('#materialDppIdContainer').show();
		$('#dppSkuNameContainer').show();
		$('#updateDppSkuNameContainer').show();
		$('#divProductionMaterialTable').hide();
		$('#btnShowUpdatePm').hide();
	}
}

function createRawMaterialListOptions() {
	const materialMap = {};

	rawMaterialList.forEach(item => {
		if (item.material.isActive === "y") {
			if (!materialMap[item.materialCode]) {
				materialMap[item.materialCode] = [];
			}
			materialMap[item.materialCode].push(item);
		}
	});

	let html = '';
	for (const [materialCode, items] of Object.entries(materialMap)) {
		items.sort((a, b) => new Date(a.dateReceive) - new Date(b.dateReceive));

		html += `<optgroup label="${materialCode}">`;
		items.forEach(item => {
			html += `<option materialListId="${item.materialListId}" value="${item.material.materialCode}">
                        ${item.material.materialName} &nbsp;&nbsp; [${item.dateReceive}]
                    </option>`;
		});
		html += `</optgroup>`;
	}

	return html;
}


var materialCounter = 0;

function addPmRow() {
	materialCounter++;
	let html = `
        <tr id="pmRow${materialCounter}">
            <td>
                <select class="form-select selectRawMaterial" id="selectRawMaterial${materialCounter}" onchange="fetchRmQty(${materialCounter})">
                    ${createRawMaterialListOptions()}
                </select>
            </td>
			<td>
                <input type="text" class="form-control" id="txtUnitOfMeasurement${materialCounter}" readonly/>
            </td>
            <td>
                <input type="text" class="form-control" id="txtRmQty${materialCounter}" readonly/>
            </td>
            <td>
                <input type="number" class="form-control" id="txtPmQtyToUse${materialCounter}" min="0" placeholder="Enter quantity" oninput="fetchQtyRemaining(${materialCounter})"/>
            </td>
            <td>
                <input type="text" class="form-control" id="txtRmQtyRemaining${materialCounter}" readonly/>
            </td>
            <td>
                <button class="btn btn-danger" type="button" onclick="deleteAddPmRow(${materialCounter})">X</button>
            </td>
        </tr>
    `;

	$('#tblAddPm').append(html);
	fetchRmQty(materialCounter);
	fetchQtyRemaining(materialCounter);
	ensureNumericInputs();
	toggleAddPmColHeaders();
}

function toggleAddPmColHeaders() {
    var rowCount = $('#tblAddPm').find('tr').length;
    $('#trAddPmColHeaders').toggleClass('d-none', rowCount <= 2);
}



function fetchRmQty(counter) {
	const selectElement = $(`#selectRawMaterial${counter}`);
	const selectedOption = selectElement.find('option:selected');
	const selectedMaterialListId = selectedOption.attr('materialListId');

	const matchingMaterial = rawMaterialList.find(function(material) {
		return material.materialListId == selectedMaterialListId;
	});

	if (matchingMaterial) {
		$(`#txtRmQty${counter}`).val(matchingMaterial.quantity);
		$(`#txtUnitOfMeasurement${counter}`).val(matchingMaterial.material.unitOfMeasurement);
	} else {
		$(`#txtRmQty${counter}`).val("");
		$(`#txtUnitOfMeasurement${counter}`).val("");
	}
}

function fetchQtyRemaining(counter) {
	const rmQty = parseFloat($(`#txtRmQty${counter}`).val()) || 0;
	let qtyToUse = parseFloat($(`#txtPmQtyToUse${counter}`).val()) || 0;

	if (qtyToUse > rmQty) {
		qtyToUse = rmQty;
		$(`#txtPmQtyToUse${counter}`).val(rmQty);
	}

	const remainingQty = rmQty - qtyToUse;
	$(`#txtRmQtyRemaining${counter}`).val(remainingQty);
}

$('#btnAddPmRow').on('click', function() {
	addPmRow();
});

function deleteAddPmRow(counter) {
	$(`#pmRow${counter}`).remove();
	toggleAddPmColHeaders();
}

function populateUpdatePmForm() {
	let html = '';

	$.each(productionMaterialFiltered, function(index, item) {
		let rmListMatch = rawMaterialList.find(function(rmList) {
			return rmList.materialListId === item.materialListId;
		});

		if (rmListMatch) {
			item.quantity = rmListMatch.quantity + item.quantityToUse;
			item.dateReceive = rmListMatch.dateReceive;
		}

		let rmMatch = rawMaterial.find(function(rm) {
			return rm.materialCode === item.materialCode;
		});

		if (rmMatch) {
			item.unitOfMeasurement = rmMatch.unitOfMeasurement;
			item.materialName = rmMatch.materialName;
		}

		html += `
		<tr id="updatePmRow${index + 1}">
			<td>
				<input type="text" class="form-control" id="txtSelectedMaterial${index + 1}" 
				value="${item.materialName} [${item.dateReceive}]" materialListId="${item.materialListId}" readonly/>
            </td>
			<td>
                <input type="text" class="form-control" id="txtUnitOfMeasurement${index + 1}" 
				value="${item.unitOfMeasurement}" readonly/>
            </td>
            <td>
                <input type="text" class="form-control" id="txtRmQty${index + 1}" 
				value="${item.quantity}" readonly/>
            </td>
			<td>
                <input type="number" class="form-control" id="txtPmQtyToUse${index + 1}" 
				value="${item.quantityToUse}" min="0" oninput="fetchQtyRemaining(${index + 1})" />
            </td>
            <td>
                <input type="text" class="form-control" id="txtRmQtyRemaining${index + 1}" readonly/>
            </td>
            <td>
                <button class="btn btn-danger" type="button" onclick="deletePmItem(${index + 1})">X</button>
            </td>
			<td>
                <input type="hidden" id="hdnPmId${index + 1}" value="${item.pmId}" />
				<input type="hidden" id="hdnMaterialCode${index + 1}" value="${item.materialCode}" />
            </td>
        </tr>
		`;
	});

	$('#tblUpdatePm').find('tr:gt(1)').remove();
	$('#tblUpdatePm').append(html);

	$.each(productionMaterialFiltered, function(index, item) {
		fetchQtyRemaining(index + 1);
	});

	ensureNumericInputs();
}

$('#btnShowUpdatePm').on('click', function() {
	populateUpdatePmForm();
});

function clearPmRows() {
	$('.table tr[id^="pmRow"]').remove();
	$('.table tr[id^="updatePmRow"]').remove();
	toggleAddPmColHeaders();
	materialCounter = 0;
}

$('.btnCloseAddPmModal, .btnCloseUpdatePmModal').on('click', clearPmRows);

$(document).keydown(function(event) {
	if (event.key === 'Escape' || event.key === 'Esc') {
		clearPmRows();
	}
});


// VALIDATE: DPP
function validate(item) {
	var toastError = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let valid = true;

	function showToast(message) {
		$('#errorMessage').html(message);
		toastError.show();
	}

	function isValidDate(dateString) {
		const datePattern = /^\d{4}-\d{2}-\d{2}$/;
		if (!datePattern.test(dateString)) {
			return false;
		}

		const parsedDate = new Date(dateString);
		return parsedDate instanceof Date && !isNaN(parsedDate.getTime());
	}

	if (!item.productionDate || !isValidDate(item.productionDate)) {
		showToast('Please enter a valid Production Date in the format yyyy-MM-dd');
		valid = false;
		return valid;
	}

	if (!item.skuCode || !/^SKU\d{3}$/.test(item.skuCode)) {
		showToast('Please enter a valid SKU Code (ex: SKU001)');
		valid = false;
		return valid;
	}

	const maxIntValue = 2147483647;

	const quantity = parseInt(item.quantity, 10);
	if (isNaN(quantity) || quantity < 0 || quantity > maxIntValue) {
		showToast('Please enter a valid Quantity');
		valid = false;
		return valid;
	}

	const validStatuses = new Set(["Planned", "In Progress", "Finished"]);

	if (!item.status || !validStatuses.has(item.status)) {
		showToast('Please select a valid Status option');
		valid = false;
		return valid;
	}

	return valid;
}

ensureNumericInputs();

function ensureNumericInputs() {
	$('input[type="number"]').on('input', function() {
		this.value = this.value.replace(/[^0-9]/g, '');
	});
}
