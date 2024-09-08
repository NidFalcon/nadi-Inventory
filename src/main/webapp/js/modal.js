$(document).ready(function() {
    // Function to create and display the Finished Product table
    function createFplTable(finishedProducts) {
        // Clear any existing content in the table container
        $('#fplTableContainer').empty();

        // Create the table header
        let table = $('<table>').addClass('inventory');
        let header = $('<tr>').append(
            $('<th>').text('FPL ID'),
            $('<th>').text('SKU CD'),
            $('<th>').text('Quantity'),
            $('<th>').text('Branch ID'),
            $('<th>').text('Date Finished')
        );
        table.append(header);

        // Populate the table rows with data
        $.each(finishedProducts, function(index, item) {
            let row = $('<tr>').attr('data-fpl-id', item.fplId).append(
                $('<td>').text(item.fplId),
                $('<td>').text(item.skuCD),
                $('<td>').text(item.quantity),
                $('<td>').text(item.branchId),
                $('<td>').text(new Date(item.dateFinished).toLocaleDateString())
            );
            table.append(row);
        });

        // Append the table to the container
        $('#fplTableContainer').append(table);
    }

    // Open modal and fetch data on input click
    $('#fplInput').click(function() {
        $('#fplModal').show(); // Show the modal

        // AJAX call to fetch the finished products data
        $.ajax({
            url: '/api/finished-products', // Adjust the URL to your endpoint
            method: 'GET',
            success: function(data) {
                createFplTable(data); // Create the table with fetched data
            },
            error: function(xhr, status, error) {
                console.error('Failed to fetch data', status, error); // Log any errors
            }
        });
    });

    // Close modal on close button click
    $('.close').click(function() {
        $('#fplModal').hide(); // Hide the modal
    });

    // Close modal when clicking outside the modal content
    $(window).click(function(event) {
        if ($(event.target).is('#fplModal')) {
            $('#fplModal').hide(); // Hide the modal if clicked outside
        }
    });

    // Handle row click in the modal table
    $(document).on('click', '#fplTableContainer table tr', function() {
        var fplId = $(this).data('fpl-id');
        $('#fplInput').val(fplId); // Set the selected FPL ID to the input
        $('#fplModal').hide(); // Hide the modal
    });
});
