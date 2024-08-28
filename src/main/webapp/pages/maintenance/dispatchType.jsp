<div id="divDispatchTypeTable"></div>
<br>
<div id="divDispatchTypeForm">
  <form>
    <input type="hidden" id="txtIsActive" value="" />
    <table>
      <tr>
        <td><label for="txtDispatchTypeCode">Dispatch Type CD</label></td>
        <td><input type="text" class="input" id="txtDispatchTypeCode" /></td>
      </tr>
      <tr>
        <td><label for="txtDispatchTypeName">Dispatch Type Name</label></td>
        <td><input type="text" class="input" id="txtDispatchTypeName" /></td>
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
	var dispatchType = JSON.parse('${dispatchType}');
</script>
<script src="js/maintenance/dispatchType.js"></script>