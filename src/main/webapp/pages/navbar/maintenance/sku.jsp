<!-- delete toast -->
<div class="toast-container position-fixed top-0 end-0 p-3">
  	<div id="errorToast" class="toast text-bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
    	<div class="toast-header text-bg-danger">
      		<i class="bi bi-exclamation-triangle-fill me-2"></i>
      		<strong class="me-auto">Error</strong>
      		<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    	</div>
    	<div class="toast-body">
      		<div id="errorMessage"></div> 
    	</div>
  	</div>
</div>

<div class="m-4 mainWrapper">
	<main role="main">
		<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
		<div class="d-flex justify-content-between align-items-center contentNavbar">
			<h1 class="h1"><i class="bi bi-boxes me-2 ms-3"></i>SKU</h1>
			<div class="btn-toolbar mb-2 mb-md-0 me-3 ms-2">
				<button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#addModal">+ add</button>
				<button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#updateModal" id="btnShowUpdateSku">Update</button>
				<button type="button" class="btn btn-danger me-2" data-bs-toggle="modal" data-bs-target="#deleteModal" id="btnShowDeleteSku">Delete</button>
			</div>
		</div>
		<div class="container mt-4 pb-3">
			<div class="mb-4" id="divSkuTable"></div>
		</div>
	</main>
	
	<!-- Add Modal -->
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Add New SKU:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="txtSkuCode" class="col-form-label">SKU CD:</label>
							<input type="text" class="form-control" id="txtSkuCode" placeholder="SKUXXX">
						</div>
						<div class="mb-3">
							<label for="txtSkuName" class="col-form-label">SKU NAME:</label>
							<input type="text" class="form-control" id="txtSkuName" placeholder="SKU NAME">
						</div>
						<div class="mb-3">
							<label for="txtUnitOfMesaurement" class="col-form-label">Unit Of Measurement:</label>
							<input type="text" class="form-control" id="txtUnitOfMeasurement">
						</div>
						<div class="mb-3">
							<label for="chkIsActive">is Active?</label> 
							<input id="chkIsActive" class="form-check-input" type="checkbox" />
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal" data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnAddSku">Add SKU</button>
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
					<h5 class="modal-title" id="exampleModalLabel">Update raw SKU:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="rawMaterialId" class="col-form-label">Sku Code:</label>
							<input type="text" class="form-control" id="updateSkuCode" readonly>
						</div>
						<div class="mb-3">
							<label for="updateSkuName" class="col-form-label">Material:</label>
							<input type="text" class="form-control" id="updateSkuName" placeholder="SKU NAME">
						</div>
						<div class="mb-3">
							<label for=updateSkuUnitOfMesaurement class="col-form-label">Unit of measurement:</label>
							<input type="text" class="form-control" id="updateSkuUnitOfMesaurement">
						</div>
						<div class="mb-3">
							<label for="updateIsActive">is Active?</label> 
							<input id="updateIsActive" class="form-check-input" type="checkbox" />
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="btnUpdateSku">update material</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- delete modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Update raw SKU:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<input type="text" class="form-control d-none" id="deleteSkuCode"></input>
							<input type="text" class="form-control d-none" id="deleteSkuName"></input>
							<input type="text" class="form-control d-none" id="deleteSkuMeasurement"></input>
							<input type="text" class="form-control d-none" id="deleteSkuisActive"></input>
						</div>
						<div class="mb-3">
							<span>Are you sure you want to delete this row?</span>
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-danger" id="btnDeleteSku">Yes</button>
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnDeleteSkuCancel">No</button>
				</div>
			</div>
		</div>
	</div>	
</div>


<script type="text/javascript">
    var sku = JSON.parse('${sku}');
</script>
<script src="js/navbar/maintenance/sku.js"></script>
