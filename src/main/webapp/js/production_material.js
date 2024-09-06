$("#btnAddProductionMaterial").click(function() {
	let productionMaterials = createProductionMaterialObjects();
	
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: productionMaterials
	}, function(response){
		if (response.includes('success')){
			$('#btnCloseAddPmModal').click();
		} else {
			alert('unable to add production materials');
		}
	})
})

function createProductionMaterialObjects(){
	let pmObjArr = [];
		for (let i = 1; i <= materialCounter; i++){
			if ($(`#selectMaterial${i}`).length){
				pmObj = {
					pmId: null,
					dppId: $('#materialDppId').val(),
					materialCode: $(`#selectMaterial${i}`).val(),
					quantityToUse: $(`#materialQuantity${i}`).val()
				}
				pmObjArr.push(pmObj);
			}			
		}
	
	return JSON.stringify(pmObjArr);
}