<main role="main" class="m-4">
	<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mt-2 pb-2 mb-3 border-bottom">
		<h1 class="h2"><i class="bi bi-boxes me-2"></i>Daily Planned Production</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
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
						<select type="submit" class="form-select selectSkuCode" id="selectUpdateSkuCode"></select>
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

<script type="text/javascript">
    var dpp = JSON.parse('${dpp}');
    var sku = JSON.parse('${sku}');
</script>
<script src="js/navbar/dpp.js"></script>
