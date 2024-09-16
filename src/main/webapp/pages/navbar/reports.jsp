<div class="m-4 mainWrapper">
	<div class="d-flex justify-content-center m-1 p-3">
		<form>
			<div class="row mt-2">
				<label for="selReportType" class="col-sm-4 col-form-label">Report Type</label>
				<div class="col-sm-8">
					<select class="form-control" id="selReportType">
						<option value="getCurrentFinishedInventory">Current Finished Inventory</option>
						<option value="getPlannedRawMaterialsInventory">Planned Raw Materials Inventory</option>
						<option value="getProductionReport">Production Report</option>
						<option value="getReceivedInventoryReport">Received Inventory Report</option>
					</select>
				</div>
			</div>
			<div class="row mt-2">
				<label for="txtReportDate" class="col-sm-4 col-form-label">Report Date</label>
				<div class="col-sm-8">
					<input type="date" class="form-control" id="txtReportDate" value="${defaultDate}" />
				</div>
			</div>
			<div class="row mt-2">
				<label class="col-sm-4 col-form-label"></label>
				<div class="col-sm-8">
					<button type="button" class="btn btn-primary" id="btnGenerateReport">Generate</button>
					<button type="button" class="btn btn-secondary" id="btnPrint" disabled="disabled">
						<i class="bi bi-printer"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
	<div class="container mt-4 pb-3">
		<div class="mb-4" id="divReportTable"></div>
	</div>
</div>

<script type="text/javascript">
	var objReportTable = {};
	var reportName = "";
	var reportData = {};
	var reportCols = {};
</script>
<script src="js/navbar/reports.js"></script>