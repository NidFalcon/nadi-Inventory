<main role="main" class="m-4">
    <div class="alert alert-warning d-none mt-1" role="alert" id="divAlert"></div>
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mt-2 pb-2 mb-3 border-bottom">
        <h1 class="h2"><i class="bi bi-boxes me-2"></i>Production Materials</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
                data-bs-target="#addModal">+ Add</button>
            <button type="button" class="btn btn-primary me-1" data-bs-toggle="modal"
                data-bs-target="#updateModal" id="btnShowUpdateMaterial">Update</button>
            <button type="button" class="btn btn-danger me-1" data-bs-toggle="modal"
                data-bs-target="#deleteModal" id="btnShowDeleteMaterial">Delete</button>
        </div>
    </div>
    <div class="d-flex justify-content-center mt-5">
        <div id="divProductionMaterialTable"></div> <!-- Production Material Table -->
    </div>
</main>

<!-- Add Modal -->
<div class="modal fade" id="addModal" tabindex="-1"
    aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add New Production Material</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="txtPmId" class="col-form-label">PM ID:</label>
                        <input type="text" class="form-control" id="txtPmId" value='' readonly>
                    </div>
                    <div class="mb-3">
                        <label for="selectDppId" class="col-form-label">DPP ID:</label>
                        <select id="selectDppId" class="form-select"></select>
                    </div>
                    <div class="mb-3">
                        <label for="selectMaterialCode" class="col-form-label">Material Code:</label>
                        <select id="selectMaterialCode" class="form-select"></select>
                    </div>
                    <div class="mb-3">
                        <label for="txtQuantityToUse" class="col-form-label">Quantity to Use:</label>
                        <input type="number" id="txtQuantityToUse" class="form-control" min="1"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btnCloseAddModal"
                    data-bs-dismiss="modal" id="btnCloseAddModal">Close</button>
                <button type="button" class="btn btn-primary" id="btnAddProductionMaterial">Add Material</button>
            </div>
        </div>
    </div>
</div>

<!-- Update Modal -->
<div class="modal fade" id="updateModal" tabindex="-1"
    aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Update Production Material</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="txtUpdatePmId" class="col-form-label">PM ID:</label>
                        <input type="text" class="form-control" id="txtUpdatePmId" value='' readonly>
                    </div>
                    <div class="mb-3">
                        <label for="selectUpdateDppId" class="col-form-label">DPP ID:</label>
                        <select id="selectUpdateDppId" class="form-select"></select>
                    </div>
                    <div class="mb-3">
                        <label for="selectUpdateMaterialCode" class="col-form-label">Material Code:</label>
                        <select id="selectUpdateMaterialCode" class="form-select"></select>
                    </div>
                    <div class="mb-3">
                        <label for="txtUpdateQuantityToUse" class="col-form-label">Quantity to Use:</label>
                        <input type="number" id="txtUpdateQuantityToUse" class="form-control" min="1"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btnCloseAddModal"
                    data-bs-dismiss="modal" id="btnCloseUpdateModal">Close</button>
                <button type="button" class="btn btn-primary" id="btnUpdateMaterial">Update Material</button>
            </div>
        </div>
    </div>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1"
    aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete Production Material</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this production material?</p>
                <input type="hidden" id="txtDeletePmId">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btnCloseAddModal" 
                data-bs-dismiss="modal" id="btnCloseDeleteModal">Close</button>
                <button type="button" class="btn btn-danger" id="btnConfirmDeleteMaterial">Delete</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var productionMaterial = JSON.parse('${productionMaterial}');
    var dpp = JSON.parse('${dpp}');
    var rawMaterial = JSON.parse('${rawMaterial}');
</script>
<script src="js/production_material.js"></script>
