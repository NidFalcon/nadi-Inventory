$("#btnAddProductionMaterial").click(function() {
	let productionMaterial = createProductionMaterialObjects();
	
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: productionMaterial
	}, function(response){
		if (response.includes('success')){
			$('#btnCloseAddPmModal').click();
		} else {
			alert('Unable to add production materials');
		}
	})
})

function createProductionMaterialObjects(){
	let pmObjArr = [];
		for (let i = 1; i <= materialCounter; i++){
			if ($(`#selectRawMaterial${i}`).length){
				pmObj = {
					pmId: null,
					dppId: $('#materialDppId').val(),
					materialCode: $(`#selectRawMaterial${i}`).val(),
					quantityToUse: $(`#materialQuantity${i}`).val()
				}
				pmObjArr.push(pmObj);
			}			
		};
	return JSON.stringify(pmObjArr);
}