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
		{column:"dispatchTrackId", dir:"asc"}
	],
	columns: [
		{title:"Dispatch Track ID", field: 'dispatchTrackId'},
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


$('#btnShowUpdateDispatching').hide();
$('#btnDelete').hide();
$('.btnConfirmDate').hide();

// Function to show the confirm button when #dateDispatchDate changes
function handleDateChange() {
    $('.btnConfirmDate').show();
}

dispatchTable.on('rowClick',function() {
	let row = dispatchTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnShowUpdateDispatching').show();
		$('#btnDelete').show();
	} else {
		$('#btnShowUpdateDispatching').hide();
		$('#btnDelete').hide();
	}
})

function createOptions(){
	let html = '';
	$.each(dispatchType, function(index, item){
		html += '<option id="item'+item.dispatchTypeCode+'" value="'+"" +item.dispatchTypeCode+'">'+" "+item.dispatchTypeName+'</option>'
	})
	$(".selDispatchType").html(html);
	
	/*
	html = '';
	$.each(finishedProduct, function(index, item){
		html += '<option id="item'+item.fplId+'" value="'+"" +item.fplId+'">FPL ID ' +item.fplId+" "+item.sku.skuName+'</option>'
	})
	$(".selFinishedProd").html(html);
	*/
}

function getCurrentDate() {
    let date = new Date();
    let day = String(date.getDate()).padStart(2, '0');
    let month = String(date.getMonth() + 1).padStart(2, '0'); // January is 0!
    let year = date.getFullYear();
    return `${year}-${month}-${day}`; // Format as "yyyy-MM-dd"
}

function updateFplIDOptionsByDate() {
    let selectedDate = new Date($('#dateSelected').val());
	let updateDate = new Date($('#updateDate').val());

    console.log('Selected Date:', selectedDate); // Debugging statement

    let html = '<option value="">';
    let html2 = '<option value="">';
    $.each(finishedProduct, function(index, item) {
        let dateFinished = new Date(item.dateFinished);
        console.log('Finished Product Date:', dateFinished); // Debugging statement

        // Filter items to show only those with dateFinished on or before the selected date
        if (dateFinished <= selectedDate) {
            html += '<option value="' + item.fplId + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + '</option>';
        }
        if (dateFinished <= updateDate) {
            html2 += '<option value="' + item.fplId + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + '</option>';
        }
    });

    html += '</option>';
    html2 += '</option>';
    $('#selFinishedProdId').html(html);
    $('#updateFinishedProductId').html(html2);

    // Log the updated options for verification
    console.log('Updated Options HTML:', html);
}

// Call the function to set default dates when the form is first loaded
$(document).ready(function() {
    $('#dateSelected').val(getCurrentDate());
    // Bind change event to update FPL options based on the selected date
    $('#dateSelected').change(updateFplIDOptionsByDate);
});

// Add event listener to #dateDispatchDate to handle change
$('#dateSelected').change(handleDateChange);
$('#updateDate').change(handleDateChange);

function getFplID() {
    let currentDate = new Date(getCurrentDate());
    let html = '<option value="">';
    $.each(finishedProduct, function(index, item) {
        let dateFinished = new Date(item.dateFinished);
        // Filter items to show only those with dateFinished on or before the current date
        if (dateFinished <= currentDate) {
            html += '<option value="' + item.fplId + '" data-sku-name="' + item.sku.skuName + '" data-quantity="' + item.quantity + '" data-date-finished="' + new Date(item.dateFinished).toLocaleDateString() + '">' + item.fplId + '</option>';
        }
    });
    $('.selFinishedProd').html(html);
    
    $('.selFinishedProd').change(function() {
        let selectedOption = $(this).find('option:selected');
        $('.txtSkuName').val(selectedOption.data('sku-name'));
        $('.txtQuantityFPL').val(selectedOption.data('quantity'));
        $('.txtDateFinished').val(selectedOption.data('date-finished'));
    });
}

//fill up the form for updates
function populateForm(row) {
	if(row !== undefined) {
		$('#updateDispatchId').val(row.dispatchTrackId);
		$('#updateDispatchType').val(row.dispatchType.dispatchTypeCode);
		$('#updateFinishedProductId').val(row.fplId);
		$('#updateDispatchQuantity').val(row.quantity);
		$('#updateDispatchDestination').val(row.destination);
		$('#updateDate').val(row.dispatchDate);
		
		let selectedOption = $('#updateFinishedProductId').find('option:selected');
		$('.txtSkuName').val(selectedOption.data('sku-name'));
		$('.txtQuantityFPL').val(selectedOption.data('quantity'));
		$('.txtDateFinished').val(selectedOption.data('date-finished'));
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

function createItem(crudOperation) {
	let item 
	
	if (crudOperation === "create") {
		item = {
			dispatchTrackId: $('#addDispatchId').val(),
			dispatchTypeCd: $('#addDispatchType').val(),
			fplId: $('#selFinishedProdId').val(),
			quantity: $('#addDispatchQuantity').val(),
			destination: $('#addDispatchDestination').val(),	
			dispatchDate: $('#dateSelected').val()
		};
	} else if (crudOperation === "update") {
		item = {
			dispatchTrackId: $('#updateDispatchId').val(),
			dispatchTypeCd: $('#updateDispatchType').val(),
			fplId: $('#updateFinishedProductId').val(),
			quantity: $('#updateDispatchQuantity').val(),
			destination: $('#updateDispatchDestination').val(),	
			dispatchDate: $('#updateDate').val()
		};
	}
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

// Function to handle "Confirm Changes" button click
$('.btnConfirmDate').click(function() {
	console.log("confirming date");
    updateFplIDOptionsByDate();
    $('.btnConfirmDate').hide(); // Hide the confirm button after updating
});

$('#btnAddDispatch').click(function(){
	addItem("create");
});
$('#btnUpdateDispatch').click(function(){
	addItem("update");
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
getFplID();
