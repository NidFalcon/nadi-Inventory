

$("#btnAddProductionMaterial").click(function() {
	let productionMaterials = createProductionMaterialObjects();
	
	$.post("ProductionMaterialController", {
		action: "saveBulkItems",
		item: productionMaterials
	}, function(response){
		if (response.includes('success')){
			$('#btnCloseAddSelectModal').click();
		} else {
			alert('unable to add production materials');
		}
	})
})

function createProductionMaterialObjects(){
	let testObjArr = [];
		for (let i = 1; i <= materialCounter; i++){
			if ($(`#selectMaterial${i}`).length){
				testObj = {
					pmId: null,
					dppId: $('#materialDppId').val(),
					materialCode: $(`#selectMaterial${i}`).val(),
					quantityToUse: $(`#materialQuantity${i}`).val()
				}
				testObjArr.push(testObj);
			}			
		}
	
	return JSON.stringify(testObjArr);
}
