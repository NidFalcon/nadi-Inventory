		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Dashboard</h1>
				<div class="btn-toolbar mb-2 mb-md-0">
					<div class="btn-group mr-2">
						<button type="button" class="btn btn-primary" data-bs-toggle="modal"
							data-bs-target="#addModal">+</button>
					</div>
				</div>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Material Number</th>
							<th scope="col">Material Name</th>
							<th scope="col">Quantity</th>
							<th scope="col">Date</th>
							<th scope="col">Edit</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Mark</td>
							<td>Otto</td>
							<td>@mdo</td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>@fat</td>
						</tr>
						<tr>
							<th scope="row">3</th>
							<td colspan="2">Larry the Bird</td>
							<td>@twitter</td>
						</tr>
					</tbody>
				</table>
			</div>
		</main>
	</div>
</div>

<!--code for Add Modal-->
<div class="modal fade" id="addModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add raw
					materials:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<label for="material-name" class="col-form-label">Name:</label> <input
							type="text" class="form-control" id="material-name">
					</div>
					<div class="mb-3">
						<label for="material-quantity" class="col-form-label">Quantity:</label>
						<input type="text" class="form-control" id="material-quantity">
					</div>
					<div class="mb-3">
						<label for="material-date">Date</label> <input id="material-date"
							class="form-control" type="date" /> <span
							id="materialDateSelected"></span>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Add Material</button>
			</div>
		</div>
	</div>
</div>

<script src="js/inventory.js"></script>