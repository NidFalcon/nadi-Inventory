var finishedProductTable = new Tabulator("#divFinishedProductTable" , {
	layout:"fitColumns",
	data: finishedProductList,
	pagination: 'local',
	pagination: true,
	paginationSize: 10,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	movableColumns:true,
	responsiveLayout:true,
	selectableRows:1,
	columns: [
		{title:"Product ID", field: 'fplId', sorter:"number"},
		{title:"Product Code", field: 'skuCD'},
		{title:"Product Name", field: 'sku.skuName'},
		{title:"Quantity", field: 'quantity'},
		{title:"Date", field: 'dateFinished'},
		{title:"Batch Id", field:'branchId'}
	],
});
