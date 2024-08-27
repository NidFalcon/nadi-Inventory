<div id="divDispatchingTable" class="container"></div>
<br>
<div id="divDispatchForm" class="container">
  <form>
    <input type="hidden" id="txtActiveTag" value="" />
    
    <div class="row mb-3">
      <label for="dispatchType" class="col-sm-3 col-form-label">Dispatch Type</label>
      <div class="col-sm-9">
        <select id="dispatchType" class="form-select">
          <option value="" class="option">Select Dispatch Type</option>
          <!-- Add options here -->
        </select>
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="finishProduct" class="col-sm-3 col-form-label">Finish Product</label>
      <div class="col-sm-9">
        <select id="finishProduct" class="form-select">
          <option value="">Select Finish Product</option>
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
      <label for="branchId" class="col-sm-3 col-form-label">Branch ID</label>
      <div class="col-sm-9">
        <select id="branchId" class="form-select">
          <option value="">Select Branch ID</option>
          <!-- Add options here -->
        </select>
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="batchNumber" class="col-sm-3 col-form-label">Batch Number</label>
      <div class="col-sm-9">
        <select id="batchNumber" class="form-select">
          <option value="">Select Batch Number</option>
          <!-- Add options here -->
        </select>
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
