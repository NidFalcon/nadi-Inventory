<nav class="navbar navbar-expand-lg sticky-top divMenu">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"><img src="images/icon.png" class="me-1 menuIcon">Patisserie Plain</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		 <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
			<div class="offcanvas-header">
			  	<h5 class="offcanvas-title" id="offcanvasNavbarLabel">Patisserie Plain</h5>
			  	<button type="button" class="btn-close" id="btnCloseOffNavbar" data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<ul class="navbar-nav me-auto mb-lg-0 flex-wrap">
					<li class="nav-item"><a class="nav-link" href="#" id="btnDashboard">
					<i class="bi bi-border-all me-1"></i>Dashboard</a></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<i class="bi bi-dropbox me-1"></i>Inventory</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#" id="btnRawMaterials">Raw Materials</a></li>
							<li><a class="dropdown-item" href="#" id="btnProducts">Finished Products</a></li>
						</ul>
					</li>
					<li class="nav-item"><a class="nav-link" href="#" id="btnReports"><i class="bi bi-clipboard-data-fill me-1"></i>Reports</a></li>
					<li class="nav-item"><a class="nav-link" href="#" id="btnDispatching"><i class="bi bi-truck me-1"></i>Dispatch</a></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<i class="bi bi-database-fill-gear me-1"></i>Maintenance</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#" id="btnMngDispatchType">Dispatch Types</a></li>
							<li><a class="dropdown-item" href="#" id="btnMngBranch">Branches</a></li>
							<li><a class="dropdown-item" href="#" id="btnMngSku">SKU</a></li>
							<li><a class="dropdown-item" href="#" id="btnMngMaterial">Materials</a></li>
						</ul>
					</li>
					<li class="nav-item"><a class="nav-link" href="#" id="btnDpp"><i class="bi bi-card-heading me-1"></i>DPP</a></li>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item dropdown me-2"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<i class="bi bi-person-circle me-1"></i>Hello ${username}</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#" id="btnLogout">LogOut</a></li>
						</ul>
					</li>
			  	</ul>
			</div>
		</div>
	</div>
</nav>
<script src="js/navbar/menu.js"></script>