<div class="m-4 mainWrapper">
	<main role="main">
		<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
		<div class="d-flex align-items-center contentNavbar">
			<div>
					<h1 class="h2">
						<i class="bi bi-boxes me-2 ms-3"></i>Daily Planned Production
					</h1>		
			</div>
			<div>
				<button type="button" class="btn btn-primary ms-2" data-bs-toggle="modal" data-bs-target="#selectModal" id="btnShowMaterialDpp">+ add Material</button>	
			</div>
			<div class="ms-auto btn-toolbar mb-2 mb-md-0 me-3 ms-2">
				<button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#addModal">+ add</button>
				<button type="button" class="btn btn-primary  me-2" data-bs-toggle="modal" data-bs-target="#updateModal" id="btnShowUpdateDpp">Update</button>
				<button type="button" class="btn btn-danger  me-2" data-bs-toggle="modal" data-bs-target="#deleteModal" id="btnShowDeleteDpp">Delete</button>
			</div>
		</div>
		<div class="container mt-4 pb-3">
			<div class="mb-4" id="divDppTable"></div> <!--Raw Material Table-->
		</div>
	</main>
	
	<!-- add modal -->
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Add New
						DPP:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="txtDppId" class="col-form-label">DPP ID:</label>
							<input type="text" class="form-control" id="txtDppId" value = '' readonly>
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
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal"
						data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnAddDpp">Add
						SKU</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- update modal -->
	<div class="modal fade" id="updateModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Update DPP:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
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
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal"
						data-bs-dismiss="modal" id="btnCloseUpdateModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnUpdateDpp">Update DPP</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Delete Modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1"
	    aria-labelledby="exampleModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header border-0 modalHeader">
	                <h5 class="modal-title" id="exampleModalLabel">Delete DPP</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal"
	                    aria-label="Close"></button>
	            </div>
	            <div class="modal-body border-0 modalBody">
	                <p>Are you sure you want to delete this DPP?</p>
	                <input type="hidden" id="txtDeleteDppId">
	            </div>
	            <div class="modal-footer border-0 modalFooter">
	                <button type="button" class="btn btn-secondary btnCloseAddModal" 
	                data-bs-dismiss="modal" id="btnCloseDeleteModal">Close</button>
	                <button type="button" class="btn btn-danger" id="btnConfirmDeleteDpp">Delete</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- select modal -->
	<div class="modal fade" id="selectModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Add Material:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close" id="btnCloseAddSelectModal"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<div id="divProductionMaterialTable"></div>
					<form>
						<table class="table">
							<tr>
								<th scope="col"><label for="selectMaterial">Select Material/s:</label></th>
								<th scope="col"><label for="materialQuantity"></label>quantity:</th>
							</tr>
							<tr>
								<td><Select class="form-select selectMaterial" id="selectMaterial">
									<option></option>
								</Select></td>
								<td><input type="number" class="form-control" min="1"></td>
							</tr>
						</table>
					<button id="selectAdd" class="btn btn-primary"> + </button>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal"
						data-bs-dismiss="modal" id="btnCloseAddSelectModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnAddMaterial">Add
						Material</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
    var dpp = JSON.parse('${dpp}');
    var sku = JSON.parse('${sku}');
    var rawMaterial = JSON.parse('${rawMaterial}');
    var productionMaterial = JSON.parse('${productionMaterials}')
</script>
<script src="js/navbar/dpp/dpp.js"></script>
<script src="js/navbar/dpp/production_material.js"></script>
