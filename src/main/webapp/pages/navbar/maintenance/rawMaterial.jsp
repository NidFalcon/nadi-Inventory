<div id="divRawMaterialTable"></div>
<br>
<div id="divRawMaterialForm">
  <form>
    <table>
      <tr>
        <td><label for="txtMaterialCode">Material Code</label></td>
        <td><input type="text" class="input" id="txtMaterialCode" /></td>
      </tr>
      <tr>
        <td><label for="txtMaterialName">Material Name</label></td>
        <td><input type="text" class="input" id="txtMaterialName" /></td>
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
<script type="text/javascript">
    var rawMaterial = JSON.parse('${rawMaterial}');
</script>
<script src="js/navbar/maintenance/raw_material.js"></script>
