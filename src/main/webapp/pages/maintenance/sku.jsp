<main role="main" class="m-4">
	<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mt-2 pb-2 mb-3 border-bottom">
		<h1 class="h2"><i class="bi bi-boxes me-2"></i>Raw Material List</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
				data-bs-target="#addModal">+ add</button>
			<button type="button" class="btn btn-primary  me-1" data-bs-toggle="modal"
				data-bs-target="#updateModal" id="btnShowUpdateSku">Update</button>
			<button type="button" class="btn btn-danger  me-1" data-bs-toggle="modal"
				data-bs-target="#deleteModal" id="btnShowDeleteSku">Delete</button>
		</div>
	</div>
	<div class="d-flex justify-content-center mt-5">
		<div id="divSkuTable"></div> <!--Raw Material Table-->
	</div>
</main>
<br>

<div class="modal fade" id="addModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add New
					SKU:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
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
						<input type="text" class="form-control"
							id="txtUnitOfMeasurement">
					</div>
					<div class="mb-3">
						<label for="chkIsActive">is Active?</label> <input id="chkIsActive"
							class="form-check-input" type="checkbox" />
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseAddModal"
					data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
				<button type="button" class="btn btn-primary" id="btnAddSku">Add
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
				<h5 class="modal-title" id="exampleModalLabel">Update raw
					SKU:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
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
						<label for=updateSkuUnitOfMesaurement class="col-form-label">Quantity:</label>
						<input type="text" class="form-control"
							id="updateSkuUnitOfMesaurement">
					</div>
					<div class="mb-3">
						<label for="updateIsActive">is Active?</label> <input id="updateIsActive"
							class="form-check-input" type="checkbox" />
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btnCloseAddModal"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					id="btnUpdateSku">update material</button>
			</div>
		</div>
	</div>
</div>
<!--
<div id="divSkuForm">
  <form>
    <table>
      <tr>
        <td><label for="txtSkuCode">SKU Code</label></td>
        <td><input type="text" class="input" id="txtSkuCode" /></td>
      </tr>
      <tr>
        <td><label for="txtSkuName">SKU Name</label></td>
        <td><input type="text" class="input" id="txtSkuName" /></td>
      </tr>
      <tr>
        <td><label for="txtUnitOfMeasurement">Unit of Measurement</label></td>
        <td><input type="text" class="input" id="txtUnitOfMeasurement" /></td>
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
<br>
-->
<script type="text/javascript">
    var sku = JSON.parse('${sku}');
</script>
<script src="js/maintenance/sku.js"></script>
