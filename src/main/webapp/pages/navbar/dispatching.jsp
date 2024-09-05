<main role="main" class="m-4">
	<div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mt-2 pb-2 mb-3 border-bottom">
		<h1 class="h2"><i class="bi bi-boxes me-2"></i>Dispatch</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
				data-bs-target="#addModal">+ add</button>
			<button type="button" class="btn btn-primary  me-1" data-bs-toggle="modal"
				data-bs-target="#updateModal" id="btnShowUpdateDispatching">Update</button>
			<button type="button" class="btn btn-danger  me-1" data-bs-toggle="modal"
				data-bs-target="#deleteModal" id="btnDelete">Delete</button>
		</div>
	</div>
	<div class="d-flex justify-content-center mt-5">
		<div id="divDispatchingTable"></div> <!--Dispatch Table-->
	</div>
</main>

<!--Add Modal-->
<div class="modal fade" id="addModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add Dispatch:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3 d-none">
						<label for="addDispatchId" class="col-form-label">Dispatch Type ID:</label>
						<input type="text" class="form-control" id="addDispatchId" value="0" placeholder="0" readonly>
					</div>
					<div class="mb-3">
						<label for="addDispatchType" class="col-form-label">Dispatch Type:</label>
						<select class="form-select selDispatchType" id="addDispatchType">
							<option></option>
							<option value="DIS004">DIS001M</option>
						</select> 
					</div>
					<div class="mb-3">
						<label for="selFinishedProdId">Finished Product List</label>
						<select id="selFinishedProdId" class="form-select selFinishedProd">
						            <option value="" class="option">Select Finished Product</option>
						            <option value="23" class="option">test value 23</option>
						</select>
					</div>
					<div class="row mb-3">
					   <div class="col-sm-4">
					        <label for="txtSkuName" class="col-form-label">SKU Name</label>
					        <input type="text" id="txtSkuName" class="form-control txtSkuName" readonly="readonly"/>
					    </div>
					    <div class="col-sm-4">
					        <label for="txtQuantityFPL" class="col-form-label">Quantity</label>
					        <input type="text" id="txtQuantityFPL" class="form-control txtQuantityFPL" readonly="readonly"/>
					    </div>
					    <div class="col-sm-4">
					        <label for="txtDateFinished" class="col-form-label">Date Finished</label>
					        <input type="text" id="txtDateFinished" class="form-control txtDateFinished" readonly="readonly"/>
					    </div>
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
			<div class="modal-footer">
				<button type="button" class="btn btn-success btnConfirmDate"
				 >Confirm Date</button>
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
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Dispatch:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3" >
						<label for="updateDispatchId" class="col-form-label">Dispatch Type ID:</label>
						<input type="text" class="form-control" id="updateDispatchId" readonly>
					</div>
					<div class="mb-3">
						<label for="updateDispatchType" class="col-form-label">Dispatch Type:</label>
						<select class="form-select selDispatchType" id="updateDispatchType">
							<option></option>
							<option value="DIS004">DIS001M</option>
						</select> 
					</div>
					<div class="mb-3">
						<label for="updateFinishedProductId">Finished Product List</label>
						<select id="updateFinishedProductId" class="form-select selFinishedProd">
						            <option value="" class="option">Select Finished Product</option>
						            <option value="23" class="option">test value 23</option>
						</select>
					</div>
					<div class="row mb-3">
					   <div class="col-sm-4">
					        <label for="txtSkuName" class="col-form-label">SKU Name</label>
					        <input type="text" class="form-control txtSkuName" readonly="readonly"/>
					    </div>
					    <div class="col-sm-4">
					        <label for="txtQuantityFPL" class="col-form-label">Quantity</label>
					        <input type="text" class="form-control txtQuantityFPL" readonly="readonly"/>
					    </div>
					    <div class="col-sm-4">
					        <label for="txtDateFinished" class="col-form-label">Date Finished</label>
					        <input type="text" class="form-control txtDateFinished" readonly="readonly"/>
					    </div>
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
						<label for="material-date">Dispatch Date:</label> 
						<input id="updateDate" class="form-control" type="date" />
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success btnConfirmDate"
				 >Confirm Date</button>
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
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Delete row:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
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
			<div class="modal-footer">

				<button type="button" class="btn btn-danger" id="btnDeleteDispatch">Yes</button>
				<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnDeleteDispatchCancel">No</button>				
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
    var dispatch = JSON.parse('${dispatch}');
    var dispatchType = JSON.parse('${dispatchType}');
    var finishedProduct = JSON.parse('${finishedProduct}');
    var branchId = '${branchId}';
</script>
<script src="js/navbar/dispatch.js"></script>
