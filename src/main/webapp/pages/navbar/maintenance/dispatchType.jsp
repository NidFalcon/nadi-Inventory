<div class="m-4 mainWrapper">
	<main role="main">
		<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
		<div class="d-flex justify-content-between align-items-center contentNavbar">
				<h1 class="h2">
					<i class="bi bi-boxes me-2 ms-3"></i>Dispatch Type
				</h1>
			<div class="btn-toolbar mb-2 mb-md-0 me-3 ms-2">
				<button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#addModal">+ add</button>
				<button type="button" class="btn btn-primary  me-2" data-bs-toggle="modal" data-bs-target="#updateModal" id="btnUpdate">Update</button>
				<button type="button" class="btn btn-danger  me-12" data-bs-toggle="modal" data-bs-target="#deleteModal" id="btnDelete">Delete</button>
			</div>
		</div>
		<div class="container mt-4 pb-3">
			<div class="mb-4" id="divDispatchTypeTable"></div> <!--Dispatch Type Table-->
		</div>
	</main>

<!--Add Modal-->
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Add Dispatch Type:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="addDispatchId" class="col-form-label">Dispatch Type Code:</label>
							<input type="text" class="form-control" id="addDispatchCode">
						</div>
						<div class="mb-3">
							<label for="addDispatchType" class="col-form-label">Dispatch Type Name:</label>
							<select class="form-select" id="addDispatchTypeName">
								<option></option>
							</select> 	
						</div>
						<div class="mb-3">
							<label for="checkActive" class="form-check-label">Active:</label>
							<input class="form-check-input" type="checkbox" id="addCheckActive">
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal"
						data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnAddDispatchType">Add
						Dispatch Type</button>
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
							<label for="updateDispatchId" class="col-form-label">Dispatch Type Code:</label>
							<input type="text" class="form-control" id="updateDispatchCode">
						</div>
						<div class="mb-3">
							<label for="updateDispatchType" class="col-form-label">Dispatch Type Name:</label>
							<select class="form-select" id="updateDispatchTypeName">
								<option></option>
							</select> 	
						</div>
						<div class="mb-3">
							<label for="checkActive" class="form-check-label">Active:</label>
							<input class="form-check-input" type="checkbox" id="updateCheckActive">
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseAddModal"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						id="btnUpdateDispatchType">update Dispatch Type</button>
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
							<input type="text" class="form-control d-none" id="deleteDispatchCode">
							<input type="text" class="form-control d-none" id="deleteDispatchName">
							<input class="form-check-input d-none" type="checkbox" id="deleteCheckActive">
							
						</div>
						<div class="mb-3">
							<span>Are you sure you want to delete this row?</span>
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
	
					<button type="button" class="btn btn-danger" id="btnDeleteDispatchType">Yes</button>
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnDeleteDispatchTypeCancel">No</button>				
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
    var dispatchType = JSON.parse('${dispatchType}');
</script>
<script src="js/navbar/maintenance/dispatch_type.js"></script>
