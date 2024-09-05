<main role="main" class="m-4">
	<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
	<div class="d-flex flex-wrap flex-md-nowrap align-items-center	 mt-2 pb-2 mb-3 border-bottom">
		<div>
			<h2 class="h2"><i class="bi bi-boxes me-2"></i>Daily Planned Production</h2>		
		</div>
		<div>
			<button type="button" class="btn btn-primary ms-2" data-bs-toggle="modal"
				data-bs-target="#selectModal" id="btnShowMaterialDpp">+ add Material</button>	
		</div>
		<div class="ms-auto btn-toolbar mb-2 mb-md-0">
			<button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
				data-bs-target="#addModal">+ add</button>
			<button type="button" class="btn btn-primary  me-1" data-bs-toggle="modal"
				data-bs-target="#updateModal" id="btnShowUpdateDpp">Update</button>
			<button type="button" class="btn btn-danger  me-1" data-bs-toggle="modal"
				data-bs-target="#deleteModal" id="btnShowDeleteDpp">Delete</button>
		</div>
	</div>
	<div class="d-flex justify-content-center mt-5">
		<div id="divDppTable"></div> <!--Raw Material Table-->
	</div>
</main>

<!-- add modal -->
<div class="modal fade" id="addModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add New
					DPP:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<!-- 
						<td><label for="txtDppId">c</label></td>
                		<td><input type="text" id="txtDppId" readonly /></td>
						 -->
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
						<select type="submit" class="form-select selectSkuCode" id="selectSkuCode"></select>
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
				<button type="button" class="btn btn-primary" id="btnAddDpp">Add
					DPP</button>
			</div>
		</div>
	</div>
</div>

<!-- update modal -->
<div class="modal fade" id="updateModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update DPP:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<!-- 
						<td><label for="txtDppId">c</label></td>
                		<td><input type="text" id="txtDppId" readonly /></td>
						 -->
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
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete DPP</h5>
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

<!-- select modal -->
<div class="modal fade" id="selectModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add Material:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close" id="btnCloseAddSelectModal"></button>
			</div>
			<div class="modal-body">
				<form>
					<table class="table">
						<tr>
							<th scope="col">
								<label for="materialDppId">DPP ID: </label>
								<input type="text" class="form-control" id="materialDppId" readonly>
							</th>
						</tr>
						<tr>
							<th scope="col"><label for="selectMaterial">Select Material/s:</label></th>
							<th scope="col"><label for="materialQuantity"></label>Quantity:</th>
							<!-- <th scope="col"><label for="materialQuantity"></label>Unit of Measurement:</th> -->
						</tr>
					</table>
				<button id="selectAdd" class="btn btn-primary"> + </button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseAddModal"
					data-bs-dismiss="modal" id="btnCloseAddSelectModal">Close</button>
				<button type="button" class="btn btn-primary" id="btnAddMaterial">Add
					Material</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    var dpp = JSON.parse('${dpp}');
    var sku = JSON.parse('${sku}');
    var rawMaterial = JSON.parse('${rawMaterial}');
</script>
<script src="js/navbar/dpp.js"></script>
<script src="js/production_material.js"></script>
