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
				materialListId: $(`#selectRawMaterial${i} option:selected`).attr('materialListId'), // Fetch from selected option
				materialCode: $(`#selectRawMaterial${i}`).val(),
				quantityToUse: $(`#txtPmQtyToUse${i}`).val()
			}
			pmObjArr.push(pmObj);
		}
	};

	return JSON.stringify(pmObjArr);
}


$('#btnUpdatePmSubmit').click(function() {
	var updateProductionMaterial = updateProductionMaterialObjects();
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: updateProductionMaterial
	}, function(response) {
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
	console.log("Before: " + JSON.stringify(updPmObjArr));
	for (let i = 1; i <= productionMaterialFiltered.length; i++) {
		var updPmObj = {
			pmId: $(`#hdnPmId${i}`).val(),
			dppId: $('#updateMaterialDppId').val(),
			materialListId: $(`#txtSelectedMaterial${i}`).attr('materialListId'),
			materialCode: $(`#hdnMaterialCode${i}`).val(),
			quantityToUse: $(`#txtPmQtyToUse${i}`).val()
		}
		updPmObjArr.push(updPmObj);
	};

	console.log("After: " + JSON.stringify(updPmObjArr));
	return JSON.stringify(updPmObjArr);
}

function deletePmItem(index) {
	let deletePmId = $(`#hdnPmId${index}`).val().trim();
	if (deletePmId !== '') {
		var item = {
			pmId: deletePmId
		};
		$.post('ProductionMaterialController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseUpdatePmModal').click();
				$('#btnDpp').click();
			} else {
				alert('Unable to delete production material item');
			}
		});
	}
}
