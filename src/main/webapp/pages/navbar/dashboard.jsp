<div class="container mt-4">
    <div class="alert alert-secondary" role="alert">
        <h4 class="alert-heading text-center">Welcome ${username}!</h4>
    </div>
    <hr class="my-4"/>
    <div class="row">
        <div class="col-md-6">
            <p class="font-weight-bold">Username:</p>
            <p>${username}</p>
        </div>
        <div class="col-md-6">
            <p class="font-weight-bold">Branch:</p>
            <p>${branchName}</p>
        </div>
    </div>

	<div class="container mt-4">
    <!-- Introduction Text -->
    <div class="text-center mb-4">
        <h5>What would you like to do today?</h5>
    </div>

    <!-- Buttons Section -->
    <div class="row mt-4">
        <div class="col-12 text-center">
            <div role="group" aria-label="Dashboard Buttons" id="dashboardButtons">
                <button type="button" class="btn btn-primary mx-2 my-2" id="btnDashboardReports">Reports</button>
                <button type="button" class="btn btn-secondary mx-2 my-2" id="btnDashboardDispatch">Dispatch</button>
                <button type="button" class="btn btn-success mx-2 my-2" id="btnDashboardRawMaterials">Raw Materials</button>
                <button type="button" class="btn btn-danger mx-2 my-2" id="btnDashboardDpp">DPP</button>
            </div>
        </div>
    </div>
</div>
</div>
<script src="js/navbar/dashboard.js"></script>
