<div id="divSkuTable"></div>
<br>
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
<script type="text/javascript">
    var sku = JSON.parse('${sku}');
</script>
<script src="js/maintenance/sku.js"></script>
