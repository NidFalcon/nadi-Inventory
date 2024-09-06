<div class="m-4 mainWrapper">
	<main role="main">
		<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
		<div class="d-flex justify-content-between align-items-center contentNavbar">
				<h1 class="h2">
					<i class="bi bi-boxes me-2 ms-3"></i>Company Branches
				</h1>
			<div class="btn-toolbar mb-2 mb-md-0 me-3 ms-2">
				<button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#addModal">+ add</button>
				<button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#updateModal" id="btnShowUpdateBranches">Update</button>
				<button type="button" class="btn btn-danger me-2" data-bs-toggle="modal" data-bs-target="#deleteModal" id="btnShowDeleteBranches">Delete</button>
			</div>
		</div>
		<div class="container mt-4 pb-3">
			<div id="divBranchTableTable"></div> <!--Branch Table-->
		</div>
	</main>
	
	<!-- add modal -->	
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header border-0 modalHeader">
					<h5 class="modal-title" id="exampleModalLabel">Add Branch:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="txtBranchId" class="col-form-label">Branch ID:</label>
							<input type="text" class="form-control" id="txtBranchId" value = '' readonly>
						</div>
						<div class="mb-3">
							<label for="txtBranchName">Branch Name</label>
							<input type="text" id="txtBranchName" class="form-control"/>
						</div>
						<div class="mb-3">
							<label for="chkBranchIsActive">Active</label>
							<input id="chkBranchIsActive" class="form-check-input" type="checkbox" />
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseModal"
						data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnAddBranch">Add
						Branch</button>
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
					<h5 class="modal-title" id="exampleModalLabel">Update Branch:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<label for="txtUpdateBranchId" class="col-form-label">Branch ID:</label>
							<input type="text" class="form-control" id="txtUpdateBranchId" value = '' readonly>
						</div>
						<div class="mb-3">
							<label for="txtUpdateBranchName">Branch Name</label>
							<input type="text" id="txtUpdateBranchName" class="form-control"/>
						</div>
						<div class="mb-3">
							<label for="chkUpdateBranchIsActive">Active</label>
							<input id="chkUpdateBranchIsActive" class="form-check-input" type="checkbox" />
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
					<button type="button" class="btn btn-secondary btnCloseModal"
						data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
					<button type="button" class="btn btn-primary" id="btnUpdateBranchId">Update
						Branch</button>
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
					<h5 class="modal-title" id="exampleModalLabel">Delete Branch:</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body border-0 modalBody">
					<form>
						<div class="mb-3">
							<input type="text" class="form-control d-none" id="deleteBranchId">
							<input type="text" class="form-control d-none" id="deleteBranchName">
							<input type="text" class="form-control d-none" id="deleteStatus">
						</div>
						<div class="mb-3">
							<span>Are you sure you want to delete this row?</span>
						</div>
					</form>
				</div>
				<div class="modal-footer border-0 modalFooter">
	
					<button type="button" class="btn btn-danger" id="btnDeleteBranch">Yes</button>
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnDeleteBranchCancel">No</button>				
				</div>
			</div>
		</div>
	</div>	
</div>


<script type="text/javascript">
    var branch = JSON.parse('${branch}');
</script>
<script src="js/navbar/maintenance/branch.js"></script>