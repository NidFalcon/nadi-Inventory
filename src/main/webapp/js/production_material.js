$('#btnAddPmSubmit').click(function() {
	var productionMaterial = createProductionMaterialObjects();
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: productionMaterial
	}, function(response) {
		if (response.includes('success')) {
			$('.btnCloseAddPmModal').click();
			$('#btnDpp').click();
		} else {
			alert('Unable to add production materials');
		}
	});
});

function createProductionMaterialObjects() {
	var pmObjArr = [];
	for (let i = 1; i <= materialCounter; i++) {
		if ($(`#selectRawMaterial${i}`).length) {
			pmObj = {
				pmId: null,
				dppId: $('#materialDppId').val(),
				materialCode: $(`#selectRawMaterial${i}`).val(),
				quantityToUse: $(`#txtMaterialQuantity${i}`).val()
			}
			pmObjArr.push(pmObj);
		}
	};

	return JSON.stringify(pmObjArr);
}

$('#btnUpdatePmSubmit').click(function() {
	var updateProductionMaterial = updateProductionMaterialObjects();
	console.log("func2- production material inside the onclick: " + updateProductionMaterial);
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: updateProductionMaterial
	}, function(response) {
		console.log("func2- "+response);
		if (response.includes('success')) {
			$('.btnCloseUpdatePmModal').click();
			$('#btnDpp').click();
		} else {
			alert('Unable to add production materials');
		}
	});
});

function updateProductionMaterialObjects() {
	var updPmObjArr = [];
	console.log("func1- productionMaterialFiltered.length: " + productionMaterialFiltered.length);
	for (let i = 1; i <= productionMaterialFiltered.length; i++) {
		if ($(`#selectRawMaterial${i}`).length) {
			var updPmObj = {
				pmId: $(`#txtUpdatePmId${i}`).val(),
				dppId: $('#updateMaterialDppId').val(),
				materialCode: $(`#selectRawMaterial${i}`).val(),
				quantityToUse: $(`#txtMaterialQuantity${i}`).val()
			}
			updPmObjArr.push(updPmObj);
		}
	};

	console.log("func1- updatePmObjArr: " + JSON.stringify(updPmObjArr));
	return JSON.stringify(updPmObjArr);
}

function deletePmItem(index) {
	var deletePmId = $(`#txtUpdatePmId${index}`).val();
	if (deletePmId !== '') {
		let item = {
			pmId: deletePmId
		};
		$.post('ProductionMaterialController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnCloseUpdatePmModal').click();
				$('#btnDpp').click();
			} else {
				alert('Unable to delete production material item');
			}
		});
	} else {
		alert('Please select a valid item to delete');
	}
}
