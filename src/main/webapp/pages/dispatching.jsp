<div id="divDispatchingTable" class="container"></div>
<br>
<div id="divDispatchForm" class="container">
  <form>    
    <div class="row mb-3">
      <label for="txtDispatchingId" class="col-sm-3 col-form-label">Dispatch Track ID</label>
      <div class="col-sm-9">
        <input type="text" class="input" id="txtDispatchingId" readonly="readonly" />
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="txtdispatchType" class="col-sm-3 col-form-label">Dispatch Type</label>
      <div class="col-sm-9">
        <select id="txtdispatchType" class="form-select">
          <option value="" class="option">Select Dispatch Type</option>
          <!-- Add options here -->
        </select>
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="txtFinProdId" class="col-sm-3 col-form-label">Finished Product ID</label>
      <div class="col-sm-9">
        <input type="text" class="input" id="txtFinProdId" readonly="readonly" />
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="txtFinProdName" class="col-sm-3 col-form-label">Finished Product</label>
      <div class="col-sm-9">
        <select id="txtFinProdName" class="form-select">
          <option value="">Select Finished Product</option>
          <!-- Add options here -->
        </select>
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="txtQuantity" class="col-sm-3 col-form-label">Quantity</label>
      <div class="col-sm-9">
        <input type="number" class="form-control" id="txtQuantity" maxlength="12" />
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="txtBranchId" class="col-sm-3 col-form-label">Branch ID</label>
      <div class="col-sm-9">
        <input type="text" class="input" id="txtBranchId" readonly="readonly" />
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="txtBranchName" class="col-sm-3 col-form-label">Branch Name</label>
      <div class="col-sm-9">
        <select id="txtBranchName" class="form-select">
          <option value="">Select Branch Name</option>
          <!-- Add options here -->
        </select>
      </div>
    </div>
    
    <div class="row mb-3">
	    <label for="txtBatchNumber" class="col-sm-3 col-form-label">Batch Number</label>
	    <div class="col-sm-9">
	        <input type="text" class="form-control" id="txtBatchNumber" placeholder="Enter Batch Number" />
	    </div>
	</div>

    
    <div class="row mb-3">
      <label for="txtDestination" class="col-sm-3 col-form-label">Destination</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="txtDestination" />
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="txtDispatchDate" class="col-sm-3 col-form-label">Dispatch Date</label>
      <div class="col-sm-9">
        <input type="date" class="form-control" id="txtDispatchDate" />
      </div>
    </div>
    
    <div class="row">
      <div class="col-sm-9 offset-sm-3">
        <button type="button" class="btn btn-secondary" id="btnClear">Clear</button>
        <button type="button" class="btn btn-primary" id="btnAdd">Add</button>
        <button type="button" class="btn btn-danger" id="btnDelete">Delete</button>
      </div>
    </div>
  </form>
</div>
<br>
<script type="text/javascript">
    var dispatch = JSON.parse('${dispatch}');
</script>
<script src="js/dispatch.js"></script>
