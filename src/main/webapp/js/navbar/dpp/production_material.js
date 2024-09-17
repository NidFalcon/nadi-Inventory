$('#btnAddPmSubmit').click(function() {
	var productionMaterial = createProductionMaterialObjects();
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: productionMaterial,
		operation: "add"
	}, function(response) {
		if (response.includes('success')) {
			$('.btnCloseAddPmModal').click();

			$('#divAlert').html(response);
			var $toastLiveExample = $('#successToast');
			var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
			toastBootstrap.show();

			$('#btnDpp').click();
		} else if (response.includes("login")) {
			$('.btnCloseAddPmModal').click();
			$('#divMenu').html('');
			$('#divContent').html(response);
			alert("login expired. Please Login again");
		} else {
			$('#divAlert').html(response);
			var $toastLiveExample = $('#errorToast');
			var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
			toastBootstrap.show();
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
				materialListId: $(`#selectRawMaterial${i} option:selected`).attr('materialListId'),
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
		item: updateProductionMaterial,
		operation: "update"
	}, function(response) {
		if (response.includes('success')) {
			$('.btnCloseUpdatePmModal').click();

			$('#divAlert').html(response);
			var $toastLiveExample = $('#successToast');
			var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
			toastBootstrap.show();

			$('#btnDpp').click();
		} else if (response.includes("login")) {
			$('.btnCloseUpdatePmModal').click();
			$('#divMenu').html('');
			$('#divContent').html(response);
			alert("login expired. Please Login again");
		} else {
			$('#divAlert').html(response);
			var $toastLiveExample = $('#errorToast');
			var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
			toastBootstrap.show();
		}
	});
});

function updateProductionMaterialObjects() {
	var updPmObjArr = [];
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

				$('#divAlert').html(response);
				var $toastLiveExample = $('#successToast');
				var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
				toastBootstrap.show();

				$('#btnDpp').click();
			} else if (response.includes("login")) {
				$('.btnCloseUpdatePmModal').click();
				$('#divMenu').html('');
				$('#divContent').html(response);
				alert("login expired. Please Login again");
			} else {
				$('#divAlert').html(response);
				var $toastLiveExample = $('#errorToast');
				var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);
				toastBootstrap.show();
			}
		});
	}
}
