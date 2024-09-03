<!-- Example using JSP -->
<form action="generateReport" method="post">
    <label for="reportType">Select Report Type:</label>
    <select id="reportType" name="reportType">
        <option value="currentFinishedInventory">Current Finished Inventory</option>
        <option value="plannedRawMaterials">Planned Raw Materials Inventory</option>
        <option value="productionReport">Production Report</option>
        <option value="receivedInventoryReport">Received Inventory Report</option>
    </select>
    
    <label for="reportDate">Select Date:</label>
    <input type="date" id="reportDate" name="reportDate" required>

    <button type="submit">Generate Report</button>
</form>
