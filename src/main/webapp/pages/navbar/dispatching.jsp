<div class="m-4 mainWrapper">
		<main role="main">
		<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
		<div class="d-flex justify-content-between align-items-center contentNavbar">
				<h1 class="h2">
					<i class="bi bi-boxes me-2 ms-3"></i>Dispatch
				</h1>
			<div class="btn-toolbar mb-2 mb-md-0 me-3 ms-2">
				<button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#addModal">+ add</button>
				<button type="button" class="btn btn-primary  me-2" data-bs-toggle="modal" data-bs-target="#updateModal" id="btnUpdate">Update</button>
				<button type="button" class="btn btn-danger  me-2" data-bs-toggle="modal" data-bs-target="#deleteModal" id="btnDelete">Delete</button>
			</div>
		</div>
		<div class="container mt-4 pb-3">
			<div class="mb-4" id="divDispatchingTable"></div> <!--Dispatch Table-->
		</div>
	</main>
	
	<!--Add Modal-->
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Add Dispatch:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="addDispatchId" class="col-form-label">Dispatch Type ID:</label>
							<input type="text" class="form-control" id="addDispatchId" value="0" placeholder="0" readonly>
						</div>
						<div class="mb-3">
							<label for="addDispatchType" class="col-form-label">Dispatch Type:</label>
							<select class="form-select" id="addDispatchType">
								<option></option>
							</select> 
						</div>
						<div class="mb-3">
							<label for="addFinishedProductId" class="col-form-label">Finished Product ID:</label>
							<input type="text" class="form-control"
								id="addFinishedProductId">
						</div>
						<div class="mb-3">
							<label for="addDispatchQuantity" class="col-form-label">Quantity:</label>
							<input type="text" class="form-control"
								id="addDispatchQuantity">
						</div>
						<div class="mb-3">
							<label for="addDispatchDestination" class="col-form-label">Destination:</label>
							<input type="text" class="form-control"
								id="addDispatchDestination">
						</div>
						<div class="mb-3">
							<label for="material-date">Dispatch Date:</label> <input id="dateSelected"
								class="form-control" type="date" />
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal"
						data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnAddDispatch">Add
						Material</button>
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
					<h5 class="modal-title" id="exampleModalLabel">Update Dispatch:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="updateDispatchId" class="col-form-label">Dispatch Type ID:</label>
							<input type="text" class="form-control" id="updateDispatchId" readonly>
						</div>
						<div class="mb-3">
							<label for="updateDispatchType" class="col-form-label">Dispatch Type:</label>
							<select class="form-select" id="updateDispatchType">
								<option></option>
							</select> 
						</div>
						<div class="mb-3">
							<label for="updateFinishedProductId" class="col-form-label">Finished Product ID:</label>
							<input type="text" class="form-control"
								id="updateFinishedProductId">
						</div>
						<div class="mb-3">
							<label for="updateDispatchQuantity" class="col-form-label">Quantity:</label>
							<input type="text" class="form-control"
								id="updateDispatchQuantity">
						</div>
						<div class="mb-3">
							<label for="updateDispatchDestination" class="col-form-label">Destination:</label>
							<input type="text" class="form-control"
								id="updateDispatchDestination">
						</div>
						<div class="mb-3">
							<label for="material-date">Dispatch Date:</label> <input id="updateDate" class="form-control" type="date" />
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="btnUpdateDispatch">update material</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- delete modal-->
	<div class="modal fade" id="deleteModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Delete row:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<input type="text" class="form-control d-none" id="deleteDispatchId">
							<input type="text" class="form-control d-none" id="deleteDispatchName">
							<input type="text" class="form-control d-none" id="deleteFinishedProductId">
							<input type="text" class="form-control d-none" id="deleteSkuName">
							<input type="text" class="form-control d-none" id="deleteDispatchQuantity">
							<input type="text" class="form-control d-none" id="deleteBranchId">
							<input type="text" class="form-control d-none" id="deleteBranchName">
							<input type="text" class="form-control d-none" id="deleteDestination">
							<input type="text" class="form-control d-none" id="deleteDispatchDate">
						</div>
						<div class="mb-3">
							<span>Are you sure you want to delete this row?</span>
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
	
					<button type="button" class="btn btn-danger" id="btnDeleteDispatch">Yes</button>
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnDeleteDispatchCancel">No</button>				
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
    var dispatch = JSON.parse('${dispatch}');
    var dispatchType = JSON.parse('${dispatchType}');
    var finishedProduct = JSON.parse('${finishedProduct}');
    var branchId = '${branchId}';
 	var currentInventory = JSON.parse('${currentInventory}');
</script>
<script src="js/navbar/dispatch.js"></script>
