<main role="main" class="m-4">
	<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
	<div class="d-flex flex-wrap flex-md-nowrap align-items-center	 mt-2 pb-2 mb-3 border-bottom">
		<div>
			<h2 class="h2"><i class="bi bi-boxes me-2"></i>Daily Planned Production</h2>		
		</div>
		<div>
			<button type="button" class="btn btn-dark ms-2" data-bs-toggle="modal"
				data-bs-target="#addPmModal" id="btnShowAddPm">+ Add PM</button>	
			<button type="button" class="btn btn-dark ms-2" data-bs-toggle="modal"
				data-bs-target="#updatePmModal" id="btnShowUpdatePm">Update PM</button>	
		</div>
		<div class="ms-auto btn-toolbar mb-2 mb-md-0">
			<button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
				data-bs-target="#addModal" id="btnShowAddDpp">+ Add</button>
			<button type="button" class="btn btn-primary  me-1" data-bs-toggle="modal"
				data-bs-target="#updateModal" id="btnShowUpdateDpp">Update</button>
			<button type="button" class="btn btn-danger  me-1" data-bs-toggle="modal"
				data-bs-target="#deleteModal" id="btnShowDeleteDpp">Delete</button>
		</div>
	</div>
	<div class="d-flex justify-content-center mt-5">
		<div id="divDppTable"></div>
	</div>
</main>

<!-- add DPP modal -->
<div class="modal fade" id="addModal" tabindex="-1"
	aria-labelledby="addDppModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addDppModalLabel">Add New DPP:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<label for="txtDppId" class="col-form-label">DPP ID:</label>
						<input type="text" class="form-control" id="txtDppId" readonly>
					</div>
					<div class="mb-3">
						<label for="txtProductionDate" class="col-form-label">Production Date:</label>
						<input id="txtProductionDate"
							class="form-control" type="date" />
					</div>
					<div class="mb-3">
						<label for="selectSkuCode">SKU Code</label>
						<select class="form-select selectSkuCode" id="selectSkuCode"></select>
					</div>
					<div class="mb-3">
						<label for="txtQuantity">Quantity</label>
                		<input type="number" id="txtQuantity" class="form-control" min="1"/>
					</div>
					<div class="mb-3">
						<label for="selectStatus">Status</label>
				        <select id="selectStatus" class="form-select">
				            <option value="Planned">Planned</option>
				            <option value="In Progress">In Progress</option>
				            <option value="Completed">Completed</option>
				        </select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseAddModal"
					data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
				<button type="button" class="btn btn-primary" id="btnAddDppSubmit">Add
					DPP</button>
			</div>
		</div>
	</div>
</div>

<!-- update DPP modal -->
<div class="modal fade" id="updateModal" tabindex="-1"
	aria-labelledby="updateDppModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="updateDppModalLabel">Update DPP:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<label for="txtUpdateDppId" class="col-form-label">DPP ID:</label>
						<input type="text" class="form-control" id="txtUpdateDppId" value = '' readonly>
					</div>
					<div class="mb-3">
						<label for="txtUpdateProductionDate" class="col-form-label">Production Date:</label>
						<input id="txtUpdateProductionDate"
							class="form-control" type="date" />
					</div>
					<div class="mb-3">
						<label for="selectUpdateSkuCode">SKU Code</label>
						<select class="form-select selectSkuCode" id="selectUpdateSkuCode"></select>
					</div>
					<div class="mb-3">
						<label for="txtUpdateQuantity">Quantity</label>
                		<input type="number" id="txtUpdateQuantity" class="form-control" min="1"/>
					</div>
					<div class="mb-3">
						<label for="selectUpdateStatus">Status</label>
				        <select id="selectUpdateStatus" class="form-select">
				            <option value="Planned">Planned</option>
				            <option value="In Progress">In Progress</option>
				            <option value="Completed">Completed</option>
				        </select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseAddModal"
					data-bs-dismiss="modal" id="btnCloseUpdateModal">Close</button>
				<button type="button" class="btn btn-primary" id="btnUpdateDppSubmit">Update DPP</button>
			</div>
		</div>
	</div>
</div>

<!-- Delete DPP Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1"
    aria-labelledby="deleteDppModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteDppModalLabel">Delete DPP</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this DPP?</p>
                <input type="hidden" id="txtDeleteDppId">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btnCloseAddModal" 
                data-bs-dismiss="modal" id="btnCloseDeleteModal">Close</button>
                <button type="button" class="btn btn-danger" id="btnConfirmDeleteDpp">Delete</button>
            </div>
        </div>
    </div>
</div>

<!-- add PM modal -->
<div class="modal fade" id="addPmModal" tabindex="-1"
	aria-labelledby="addPmModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addPmModalLabel">Add Production Material/s:</h5>
				<button type="button" class="btn-close btnCloseAddPmModal" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div id="divProductionMaterialTable"></div>
				<form>
					<table class="table" id="tblAddPm">
						<tr>
							<th scope="col" id="materialDppIdContainer">
								<label for="materialDppId">DPP ID: </label>
								<input type="text" class="form-control" id="materialDppId" readonly>
							</th>
							<th scope="col" id="dppSkuNameContainer">
								<label for="dppSkuName">SKU Name: </label>
								<input type="text" class="form-control" id="dppSkuName" readonly>
							</th>
						</tr>
						<tr>
							<th scope="col">Select Material/s:</th>
							<th scope="col">Unit of Measurement</th>
							<th scope="col">Initial Stock:</th>
							<th scope="col">Quantity to Use:</th>
							<th scope="col">Remaining Stock:</th>
						</tr>
					</table>
				<button type="button" id="btnAddPmRow" class="btn btn-dark w-100"> + Add Row </button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseAddPmModal"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="btnAddPmSubmit">
					Add PM
				</button>
			</div>
		</div>
	</div>
</div>

<!-- update PM modal -->
<div class="modal fade" id="updatePmModal" tabindex="-1"
	aria-labelledby="updatePmModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="updatePmModalLabel">Update Production Material/s:</h5>
				<button type="button" class="btn-close btnCloseUpdatePmModal" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<table class="table" id="tblUpdatePm">
						<tr>
							<th scope="col">
								<label for="updateMaterialDppId">DPP ID: </label>
								<input type="text" class="form-control" id="updateMaterialDppId" readonly>
							</th>
						</tr>
						<tr>
							<th scope="col">Select Material/s:</th>
							<th scope="col">Quantity to Use:</th>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseUpdatePmModal"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="btnUpdatePmSubmit">
					Update PM
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
    var dpp = JSON.parse('${dpp}');
    var sku = JSON.parse('${sku}');
    var rawMaterial = JSON.parse('${rawMaterial}');
    var rawMaterialList = JSON.parse('${rawMaterialList}');
    var productionMaterial = JSON.parse('${productionMaterial}')
</script>
<script src="js/navbar/dpp/dpp.js"></script>
<script src="js//navbar/dpp/production_material.js"></script>