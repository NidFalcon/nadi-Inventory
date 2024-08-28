<div id="divInventoryTable"></div>
<br>
<div id="divInventoryForm">
  <form>
    <input type="hidden" id="txtActiveTag" value="" />
    <table>
      <tr>
        <td><label for="txtInventoryId">ID</label></td>
        <td><input type="text" class="input" id="txtInventoryId" readonly="readonly" /></td>
      </tr>
      <tr>
        <td><label for="txtDescription">Item</label></td>
        <td><input type="text" class="input" id="txtDescription" maxlength="200" /></td>
      </tr>
      <tr>
        <td><label for="txtQuantity">Quantity</label></td>
        <td><input type="number" class="input" id="txtQuantity" maxlength="12" /></td>
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
	var inventory = JSON.parse('${inventory}');
</script>

<script src="js/inventory.js"></script>