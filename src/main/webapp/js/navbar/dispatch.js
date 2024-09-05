var dispatchTable = new Tabulator("#divDispatchingTable" , {
	layout: 'fitDataFill',
	data: dispatch,
	pagination: 'local',
	pagination: true,
	paginationSize: 10,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	selectableRows:1,
	movableColumns:true,
	responsiveLayout:true,
	initialSort: [
		{column:"materialListId", dir:"asc"}
	],
	columns: [
		{title:"Dispatch Type ID", field: 'dispatchType.dispatchTypeCode'},
		{title:"Dispatch Type Name", field: 'dispatchType.dispatchTypeName'},
		{title:"Finished Product ID", field: 'fplId'},
		{title:"SKU Name", field: 'fpl.sku.skuName'},
		{title:"Quantity", field: 'quantity'},
		{title:"Branch ID", field: 'branch.branchId'},
		{title:"Branch Name", field: 'branch.branchName'},
		{title:"Destination", field:'destination'},
		{title:"Dispatch Date", field:'dispatchDate'}
	],
});


$('#btnUpdate').hide();
$('#btnDelete').hide();

dispatchTable.on('rowClick',function() {
	let row = dispatchTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnUpdate').show();
		$('#btnDelete').show();
	} else {
		$('#btnUpdate').hide();
		$('#btnDelete').hide();
	}
})

function createOptions(){
	let html = '';
	$.each(dispatchType, function(index, item){
		if ("y" == item.isActive){
						html += '<option id="item'+item.dispatchTypeCode+'" value="'+"" +item.dispatchTypeCode+'">'+item.dispatchTypeCode+ " " +item.dispatchTypeName+'</option>';
		}
	})
	$("selFinishedProdId").html(html);
}

//fill up the form for updates
function populateForm(row) {
	if(row !== undefined) {
		$('#updateDispatchId').val(row.dispatchType.dispatchTypeCode);
		$('#updateDispatchType').val(row.dispatchType.dispatchTypeCode);
		$('#updateFinishedProductId').val(row.fplId);
		$('#updateDispatchQuantity').val(row.quantity);
		$('#updateDispatchDestination').val(row.destination);
		$('#updateDate').val(row.dispatchDate);
	}
}

//fill up the form to delete
function populateDeleteForm(row) {
	if(row !== undefined) {
		$('#deleteDispatchId').val(row.dispatchType.dispatchTypeCode);
		$('#deleteDispatchName').val(row.dispatchType.dispatchTypeName);
		$('#deleteFinishedProductId').val(row.fplId);
		$('#deleteSkuName').val(row.fpl.sku.skuName);
		$('#deleteDispatchQuantity').val(row.quantity);
		$('#deleteBranchId').val(row.branch.branchId);	
		$('#deleteBranchName').val(row.branch.branchName);	
		$('#deleteDestination').val(row.destination);	
		$('#deleteDispatchDate').val(row.dispatchDate);	
	}
}

function createItem(isAdd) {
	let item = {
		dispatchTrackId: $('#addDispatchId').val(),
		dispatchTypeCd: $('#addDispatchType').val(),
		fplId: $('#addFinishedProductId').val(),
		quantity: $('#addDispatchQuantity').val(),
		destination: $('#addDispatchDestination').val(),	
		dispatchDate: $('#dateSelected').val()
	};
	return item;
}

function createDeleteItem(){
	let json = {
		dispatchId: $('#deleteDispatchId').val(),
		dispatchName: $('#deleteDispatchName').val(),
		finishedProductId: $('#deleteFinishedProductId').val(),
		skuName: $('#deleteSkuName').val(),
		quantity: $('#deleteDispatchQuantity').val(),
		branchId:$('#deleteBranchId').val(),
		branchName: $('#deleteBranchName').val(),	
		destination: $('#deleteDestination').val(),	
		dispatchDate: $('#deleteDispatchDate').val()	
	}
	
	return json;
}

function validate(item) {
	let valid = true;
	if(item.dispatchId === '' || item.quantity === '' || item.dispatchDate === '') {
		alert('Please correctly fill-out all required fields');
		valid = false;
	} else if (item.quantity < 0) {
		alert('Quantity must be a non-negative number');
		valid = false;
	}
	return valid;
}

function addItem(isAdd) {
	console.log("clicked");
	let item = createItem(isAdd);
	console.log(item);
	if (validate(item)) {
		$.post('DispatchingController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseAddModal').click();
				$('#btnDispatching').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}


$('#btnAddDispatch').click(function(){
	addItem(true);
});
$('#btnUpdateDispatch').click(function(){
	addItem(false);
});

$('#btnDeleteDispatch').click(function() {
	if ($('#deleteRawMaterialId').val() !== '') {

		$.post('DispatchingController', {
			action: 'deleteItem',
			item: JSON.stringify(createDeleteItem())
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteDispatchCancel').click();
				$('#btnDispatching').click();
			} else {
				$('#divAlert').removeClass('d-none');
				$('#divAlert').html('Unable to save changes');
			}
		});
	} else {
		$('#divAlert').removeClass('d-none');
		$('#divAlert').html('Please select an item to delete');
	}
});

createOptions();

//function toggleAddButton() {
//	if ('' === $('#txtDispatchId').val()) {
//		$('#btnAdd').html('Add');
//	} else {
//		$('#btnAdd').html('Update');
//	}
//}
//
//function bindRowsClick(dispatch) {
//	$.each(dispatch, function(index, item) {
//		$('#item' + index + 'row').click(function() {
//			$('#txtDispatchId').val(item.dispatchTrackId);
//			$('#selDispatchType').val(item.dispatchTypeCd);
//			$('#txtFinishedProdId').val(item.fplId);
//			$('#txtQuantity').val(item.quantity);
//			$('#selBranch').val(item.branchId);
//			$('#txtDestination').val(item.destination);
//			$('#dateDispatchDate').val(item.dispatchDate);
//			toggleAddButton();
//		});
//	});
//}
//
//function getDispatchType() {
//    let html = '<option value="">';
//    $.each(dispatchType, function(index, item) {
//        html += '<option value="' + item.dispatchTypeCode + '">' + item.dispatchTypeName + '</option>'
//    });
//    $('#selDispatchType').html(html);
//}
//
//getDispatchType();
//
//function createDispatchingTable(dispatch) {
//    let html = '';
//    html += '<table class="dispatch">';
//    html += '  <tr>';
//    html += '    <th>Dispatch Type ID</th>';
//    html += '    <th>Dispatch Type Name</th>';
//    html += '    <th>Finished Product ID</th>';
//    html += '    <th>SKU Name</th>';
//    html += '    <th>Quantity</th>';
//    html += '    <th>Branch ID</th>';
//    html += '    <th>Branch Name</th>';
//    html += '    <th>Destination</th>';
//    html += '    <th>Dispatch Date</th>';
//    html += '  </tr>';
//    $.each(dispatch, function(index, item) {
//        html += '<tr id="item' + index + 'row">';
//        html += '  <td id="item' + index + 'disType" style="align: center;">' + item.dispatchTypeCd + '</td>';
//        html += '  <td id="item' + index + 'disName">' + item.dispatchType.dispatchTypeName + '</td>';
//        html += '  <td id="item' + index + 'fplId" style="align: center;">' + item.fplId + '</td>';
//        html += '  <td id="item' + index + 'skuName">' + item.fpl.sku.skuName + '</td>';
//        html += '  <td id="item' + index + 'qty" style="align: center;">' + item.quantity + '</td>';
//        html += '  <td id="item' + index + 'brId" style="align: center;">' + item.branchId + '</td>';
//        html += '  <td id="item' + index + 'brName">' + item.branch.branchName + '</td>';
//        html += '  <td id="item' + index + 'desti">' + item.destination + '</td>';
//        html += '  <td id="item' + index + 'disDate">' + item.dispatchDate + '</td>';
//        html += '</tr>';
//    });
//    html += '</table>';
//    $('#divDispatchingTable').html(html);
//    bindRowsClick(dispatch);
//}
//
//function createItem() {
//    let item;
//    if ($('#txtDispatchId').val() == "") {
//        item = {
//            dispatchTrackId: '0',
//            dispatchTypeCd: $('#selDispatchType').val(),
//            fplId: $('#txtFinishedProdId').val(),
//            quantity: $('#txtQuantity').val(),
//            branchId: $('#hiddenBranchId').val(), // Use hidden field value
//            destination: $('#txtDestination').val(),
//            dispatchDate: $('#dateDispatchDate').val()
//        };
//    }
//    else {
//        item = {
//            dispatchTrackId: $('#txtDispatchId').val() !== '' ? $('#txtDispatchId').val() : '0',
//            dispatchTypeCd: $('#selDispatchType').val(),
//            fplId: $('#txtFinishedProdId').val(),
//            quantity: $('#txtQuantity').val(),
//            branchId: $('#hiddenBranchId').val(), // Use hidden field value
//            destination: $('#txtDestination').val(),
//            dispatchDate: $('#dateDispatchDate').val()
//        };
//    }
//    return item;
//}
//
//function validate(item) {
//    let valid = true;
//    if (item.description === '' || item.quantity === '') {
//        alert('Please correctly fill-out all required fields');
//        valid = false;
//    } else if (item.quantity < 0) {
//        alert('Quantity must be a non-negative number');
//        valid = false;
//    }
//    return valid;
//}
//
//function addItem() {
//    let item = createItem();
//    if (validate(item)) {
//        $.post('DispatchingController', {
//            action: 'saveItem',
//            item: JSON.stringify(item)
//        }, function(response) {
//            if (response.includes('success')) {
//                $('#btnDispatching').click();
//            } else {
//                alert('Unable to save changes');
//            }
//        });
//    }
//}
//
//$('#btnAdd').click(addItem);
//
//function resetDispatchingForm() {
//    $('#txtDispatchId').val('');
//    $('#selDispatchType').val('');
//    $('#txtFinishedProdId').val('');
//    $('#txtQuantity').val('');
//    $('#txtDestination').val('');
//    $('#dateDispatchDate').val('');
//    toggleAddButton();
//}
//
//$('#btnClear').click(resetDispatchingForm);
//
//$('#btnDelete').click(function() {
//    if ($('#txtDispatchId').val() !== '') {
//        let item = createItem();
//        $.post('DispatchingController', {
//            action: 'deleteItem',
//            item: JSON.stringify(item)
//        }, function(response) {
//            if (response.includes('success')) {
//                $('#btnDispatching').click();
//            } else {
//                alert('Unable to save changes');
//            }
//        });
//    } else {
//        alert('Please select an item to delete');
//    }
//});
