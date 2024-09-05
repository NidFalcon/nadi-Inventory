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
<script src="js/maintenance/branch.js"></script>