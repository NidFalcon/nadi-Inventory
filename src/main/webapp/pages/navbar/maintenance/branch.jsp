<main role="main" class="m-4">
	<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mt-2 pb-2 mb-3 border-bottom">
		<h1 class="h2"><i class="bi bi-boxes me-2"></i>Company Branches</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
				data-bs-target="#addModal">+ add</button>
			<button type="button" class="btn btn-primary  me-1" data-bs-toggle="modal"
				data-bs-target="#updateModal" id="btnShowUpdateBranches">Update</button>
			<button type="button" class="btn btn-danger  me-1" data-bs-toggle="modal"
				data-bs-target="#deleteModal" id="btnShowDeleteBranches">Delete</button>
		</div>
	</div>
	<div class="d-flex justify-content-center mt-5">
		<div id="divBranchTableTable"></div> <!--Raw Material Table-->
	</div>
</main>

<br>

<div class="modal fade" id="addModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add Branch:</h5>
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
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseModal"
					data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
				<button type="button" class="btn btn-primary" id="btnAddBranch">Add
					Branch</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="updateModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Branch:</h5>
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
			<div class="modal-footer">
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
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Delete Branch:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
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
			<div class="modal-footer">

				<button type="button" class="btn btn-danger" id="btnDeleteBranch">Yes</button>
				<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnDeleteBranchCancel">No</button>				
			</div>
		</div>
	</div>
</div>
<!--
<div id="divBranchForm">
  <form>
    <table>
      <tr>
        <td><label for="txtBranchId">Branch ID</label></td>
        <td><input type="text" class="input" id="txtBranchId" /></td>
      </tr>
      <tr>
        <td><label for="txtBranchName">Branch Name</label></td>
        <td><input type="text" class="input" id="txtBranchName" /></td>
      </tr>
      <tr>
        <td><label for="chkIsActive">Active</label></td>
        <td><input type="checkbox" id="chkIsActive" /></td>
      </tr>
      <tr>
        <td></td>
        <td>
          <button type="button" class="button" id="btnClear">Clear</button>
          <button type="button" class="button blue" id="btnAdd">Add</button>
          <button type="button" class="button red" id="btnDelete">Delete</button>
        </td>
      </tr>
    </table>
  </form>
</div>

-->
<br>
<script type="text/javascript">
    var branch = JSON.parse('${branch}');
</script>
<script src="js/navbar/maintenance/branch.js"></script>