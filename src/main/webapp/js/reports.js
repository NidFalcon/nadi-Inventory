$(document).ready(function() {
    // Event listener for report type selection
    $('#reportType').change(function() {
        var selectedReport = $(this).val();
        updateFormForReportType(selectedReport);
    });

    function updateFormForReportType(reportType) {
        // Reset and show the date input field, required for all reports
        $('#reportDate').show().attr('required', true);
        
        // Custom logic for different report types if needed
        switch(reportType) {
            case 'currentFinishedInventory':
                // Additional logic for Current Finished Inventory
                break;
            case 'plannedRawMaterials':
                // Additional logic for Planned Raw Materials Inventory
                break;
            case 'productionReport':
                // Additional logic for Production Report
                break;
            case 'receivedInventoryReport':
                // Additional logic for Received Inventory Report
                break;
            default:
                break;
        }
    }

    // Trigger change event on page load to set initial form state
    $('#reportType').trigger('change');
});
