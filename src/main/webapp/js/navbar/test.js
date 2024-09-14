
function generateReport(response) {

	console.log(response);
	console.log("report Data");
	console.log(reportData);
	switch ($('#selReportType').val()) {
		case 'getCurrentFinishedInventory':
			reportName = "CurrentFinishedInventory";
			reportCols = [
				{title: 'FPL ID', field: 'fplId'}, 
				{title: 'Date Finished', field: 'dateFinished'}, 
				{title: 'Quantity', field: 'quantity'},
				{title: 'SKU Code', field: 'skuCode'},
				{title: 'Branch ID', field: 'branchId'},
				{title: 'Material', field: 'materialName'}
		    ];
		    break;
		 case 'getPlannedRawMaterialsInventory':
			reportName = "PlannedRawMaterialsInventory";
			reportCols = [
				{title: 'Material', field: 'materialName'},
				{title: 'Quantity', field: 'quantity'}
		    ];
		    break;
		 case 'getProductionReport':
			reportName = "ProductionReport";
			reportCols = [
				{title: 'Material', field: 'materialName'},
				{title: 'Quantity', field: 'quantity'}
		    ];
		    break;
		 case 'getReceivedInventoryReport':
			reportName = "ReceivedInventoryReport";
			reportCols = [
				{title: 'Material', field: 'materialName'},
				{title: 'Quantity', field: 'quantity'},
				{title: 'Date Received', field: 'dateReceived'}
		    ];
		    break;
	}

	objReportTable = new Tabulator('#divReportTable', {
	    height: '212px',
	    layout: 'fitDataTable',
	    data: reportData,
	    pagination: 'local',
	    paginationSize: 5,
	    columns: reportCols
	});

	$('#btnPrint').prop('disabled', false);
}

generateReport();