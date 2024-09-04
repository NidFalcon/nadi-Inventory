<div id="divDispatchingTable" class="container"></div>
<br>
<div id="divDispatchForm" class="container">
  <form>    
    <div class="row mb-3">
      <label for="txtDispatchId" class="col-sm-3 col-form-label">Dispatch Track ID</label>
      <div class="col-sm-9">
        <input type="text" class="input" id="txtDispatchId"  />
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="selDispatchType" class="col-sm-3 col-form-label">Dispatch Type</label>
      <div class="col-sm-9">
        <select id="selDispatchType" class="form-select">
          <option value="" class="option">Select Dispatch Type</option>
          <!-- Add options here -->
        </select>
      </div>
    </div>
    
   <div class="row mb-3">
     <label for="txtFinishedProdId" class="col-sm-3 col-form-label">Finished Product</label>
     <div class="col-sm-9">
        <input type="text" id="txtFinishedProdId" class="form-control" placeholder="Select Finished Product ID">
     </div>  
   </div>
    
    <div class="row mb-3">
      <label for="txtQuantity" class="col-sm-3 col-form-label">Quantity</label>
      <div class="col-sm-9">
        <input type="number" class="form-control" id="txtQuantity" maxlength="12" placeholder="Enter Quantity"/>
      </div>
    </div>
    
    <!-- Hidden field for branch ID -->
    <input type="hidden" id="hiddenBranchId" value="${branchId}" />
    
    <div class="row mb-3">
      <label for="txtDestination" class="col-sm-3 col-form-label">Destination</label>
      <div class="col-sm-9">
        <input type="text" class="form-control" id="txtDestination" placeholder="Enter Destination"/>
      </div>
    </div>
    
    <div class="row mb-3">
      <label for="dateDispatchDate" class="col-sm-3 col-form-label">Dispatch Date</label>
      <div class="col-sm-9">
        <input type="date" class="form-control" id="dateDispatchDate" />
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
    var dispatchType = JSON.parse('${dispatchType}');
    var branchId = '${branchId}'; // Ensure branchId is set here
</script>
<script src="js/dispatch.js"></script>
