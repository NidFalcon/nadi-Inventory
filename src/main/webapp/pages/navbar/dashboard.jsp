<div class="container mt-4">
    <div class="alert" role="alert">
        <h4 class="alert-heading text-center">Welcome ${username}!</h4>
        <hr class="my-4"/>
    	<div class="row d-flex justify-content-center">
        	<div class="col-lg-auto align-self-center icon">
            	<i class="bi bi-person-square"></i>
        	</div>
        	<div class="col-lg-auto align-self-center">
        		<table class="table table-borderless" id="dashboardTable">
        		<tr>
        			<th><p class="font-weight-bold">Username:</p></th>
        			<th><p>${username}</p></th>
        		</tr>
            	<tr>
            		<th class="font-weight-bold">Branch:</th>
            		<th>${branchName}</th>
            	</tr>
            </table>
        	</div>	
    	</div>
    </div>
    <div class="text-center mb-4">
        <h5>What would you like to do today?</h5>
 	</div>
    <div class="row mt-4">
        <div class="col-12 text-center">
            <div role="group" aria-label="Dashboard Buttons" id="dashboardButtons">
            	<button type="button" class="btn  mx-2 my-2" id="btnDashboardRawMaterials"><i class="bi bi-dropbox me-1"></i>Raw Materials</button>
                <button type="button" class="btn  mx-2 my-2" id="btnDashboardReports"><i class="bi bi-clipboard-data-fill me-1"></i>Reports</button>
                <button type="button" class="btn  mx-2 my-2" id="btnDashboardDispatch"><i class="bi bi-truck me-1"></i>Dispatch</button>	
                <button type="button" class="btn  mx-2 my-2" id="btnDashboardDpp"><i class="bi bi-card-heading me-1"></i>DPP</button>
            </div>
        </div>
    </div>
</div>
<script src="js/navbar/dashboard.js"></script>
