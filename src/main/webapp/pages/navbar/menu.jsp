    <nav class="navbar navbar-expand-lg sticky-top divMenu">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#navbarOffcanvasLg" aria-controls="navbarOffcanvasLg"
                    aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <!--navbar items-->
                <ul class="navbar-nav ms-3 me-auto mb-2 mb-lg-0">
                    <li class="navbar-brand">
                        <img src="images/icon.png" class="me-1 menuIcon"><span>Patisserie Plain</span>
                    </li>
                <div class="offcanvas offcanvas-start" tabindex="-1" id="navbarOffcanvasLg" aria-laballededby="navbarOffcanvasLgLabel">
                    <!--menu header-->
                    <div class="offcanvas-header">     
                        <h5 class="offcanvas-title" id="navbarOffcanvasLgLabel"><i class="bi bi-box-fill me-1"></i>Patisserie Plain</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <!--menu body-->
                    <div class="offcanvas-body">
                        <ul class="navbar-nav flex-wrap pe-3">
                            <li class="nav-item">
                                <a class="nav-link active" href="#" id="btnDashboard"><i class="bi bi-border-all me-1"></i>Dashboard</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="bi bi-dropbox me-1"></i>Inventory</a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#" id="btnRawMaterials">Raw Materials</a></li>
                                    <li><a class="dropdown-item" href="#" id="btnProducts">Finished Products</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" btn="btnReports"><i class="bi bi-clipboard-data-fill me-1"></i>Reports</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" id="btnDispatching"><i class="bi bi-truck me-1"></i>Dispatch</a>

                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="bi bi-database-fill-gear me-1"></i>Maintenance</a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#" id="btnMngDispatchType">Dispatch Types</a></li>
                                    <li><a class="dropdown-item" href="#" id="btnMngBranch">Branches</a></li>
                                    <li><a class="dropdown-item" href="#" id="btnMngSku">SKU</a></li>
                                    <li><a class="dropdown-item" href="#" id="btnMngMaterial">Materials</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                            	<a class="nav-link" href="#" id="btnDpp"><i class="bi bi-card-heading me-1"></i>DPP</a>
                            </li>
                            <li class="nav-item">
                            	<a class="nav-link" href="#" id="btnProductionMaterial">Production Materials</a>
                            </li>
                        </ul>
                    </div>
                </div>
                </ul>
                <ul class="navbar-nav me-3">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="bi bi-person-circle me-1"></i>Hello ${username}</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Settings</a></li>
                        <li><a class="dropdown-item" href="#">Help</a></li>
                        <li><a class="dropdown-item" href="#" id="btnLogout">LogOut</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
<script src="js/navbar/menu.js"></script>