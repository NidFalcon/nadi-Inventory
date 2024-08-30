<main role="main">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mt-2 pb-2 mb-3 border-bottom">
		<h1 class="h2">Raw Material List</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
				data-bs-target="#addModal">+ add</button>
			<button type="button" class="btn btn-primary  me-1" data-bs-toggle="modal"
				data-bs-target="#updateModal" id="btnUpdateMaterial">Update</button>
			<button type="button" class="btn btn-danger  me-1" data-bs-toggle="modal"
				data-bs-target="#deleteModal" id="btnDeleteMaterial">Delete</button>
		</div>
	</div>
	<div class="d-flex justify-content-center mt-4">
		<div id="divRawMaterialTable"></div>
		<!--Raw Material Table-->
	</div>
</main>

<!--Add Modal-->
<div class="modal fade" id="addModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add raw
					materials:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<label for="rawMaterialListName" class="col-form-label">Material:</label>
						<select id="rawMaterialListName">
							<option value="1">Branch A</option>
						</select> 
					</div>
					<div class="mb-3">
						<label for="rawMaterialListQuantity" class="col-form-label">Quantity:</label>
						<input type="text" class="form-control"
							id="rawMaterialListQuantity">
					</div>
					<div class="mb-3">
						<label for="material-date">Date</label> <input id="material-date"
							class="form-control" type="date" /> <span
							id="rawMaterialListDateSelected"></span>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="btnAddRawMaterial">Add
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
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update raw
					materials:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<label for="updateRawMaterialName" class="col-form-label">Name:</label>
						<input type="text" class="form-control" id="updateRawMaterialName">
					</div>
					<div class="mb-3">
						<label for="updateRawMaterialQuantity" class="col-form-label">Quantity:</label>
						<input type="text" class="form-control"
							id="updateRawMaterialQuantity">
					</div>
					<div class="mb-3">
						<label for="updateDate">Date</label> <input id="material-date"
							class="form-control" type="date" /> <span id="updateDate"></span>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					id="btnUpdateRawMaterial">update material</button>
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
				<h5 class="modal-title" id="exampleModalLabel">Delete Modal:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<input type="text" class="form-control d-none" id="deleteRawMaterialName">
						<input type="text" class="form-control d-none" id="deleteRawMaterialQuantity">
					</div>
					<div class="mb-3">
					</div>
					<div class="mb-3">
						<label for="updateDate">Date</label> <input id="material-date"
							class="form-control" type="date" /> <span id="deleteDate"></span>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					id="btnDeleteRawMaterial">delete material</button>
			</div>
		</div>
	</div>
</div>

<script type=text/javascript>
	var rawMaterialList = JSON.parse('${rawMaterialList}');
</script>
<script src="js/rawMaterialList.js">
	
</script>