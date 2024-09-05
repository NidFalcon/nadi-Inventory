<div id="divBranchTable"></div>
<br>
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
<br>
<script type="text/javascript">
    var branch = JSON.parse('${branch}');
</script>
<script src="js/navbar/maintenance/branch.js"></script>