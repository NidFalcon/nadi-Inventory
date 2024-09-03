<div id="divDppTable"></div>
<br>
<div id="divDppForm">
    <form>
        <table>
            <tr>
                <td><label for="txtDppId">DPP ID</label></td>
                <td><input type="text" id="txtDppId" readonly /></td>
            </tr>
            <tr>
                <td><label for="txtProductionDate">Production Date</label></td>
                <td><input type="date" id="txtProductionDate" /></td>
            </tr>
            <tr>
                <td><label for="txtBranchId">Branch ID</label></td>
                <td><input type="text" id="txtBranchId" readonly /></td>
            </tr>
            <tr>
                <td><label for="selectBranchName">Branch Name</label></td>
                <td>
                    <select id="selectBranchName">
                        <!-- Options will be added dynamically -->
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="txtSkuCode">SKU Code</label></td>
                <td><input type="text" id="txtSkuCode" readonly /></td>
            </tr>
            <tr>
                <td><label for="selectSkuName">SKU Name</label></td>
                <td>
                    <select id="selectSkuName">
                        <!-- Options will be added dynamically -->
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="txtQuantity">Quantity</label></td>
                <td><input type="number" id="txtQuantity" /></td>
            </tr>
            <tr>
                <td><label for="txtStatus">Status</label></td>
                <td><input type="text" id="txtStatus" /></td>
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
    var dpp = JSON.parse('${dpp}');
</script>
<script src="js/dpp.js"></script>
