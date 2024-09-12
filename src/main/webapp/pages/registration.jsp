<div class="container-fluid">
	<div class="d-flex justify-content-center">
		<div class="mt-5">
			<img src="images/logo.png" alt="Icon" class="img-fluid align-self-center">
		</div>
		<div class="col-md-3 mt-5 loginContainer">
			<div class="m-3">
				<blockquote class="blockqoute">
					<h2 class="mb-0">Patisserie Plain</h2>
					<footer class="blockqoute-footer">Register</footer>
				</blockquote>
			</div>
			<div class="form-floating m-3">
				<input type="text" name="txtRegUsername" id="txtRegUsername" class="form-control mb-3 mt-3" name="txtRegUsername" placeholder="Username" />
				<label for="txtRegUsername">Username</label>
			</div>
			<div class="form-floating me-3 mt-2 ms-3">
				<input type="password" name="password" class="form-control mb-3 mt-3" id="txtRegPassword" name="txtRegPassword" placeholder="Password" />
				<label for="password">Password</label>
			</div>
			<div class="ms-3 me-3	" >
				<select  id="txtBranchId" class="form-select mb-3 mt-3" name="txtBranchId"></select>
			</div>
			<div class="mt-2 ms-3">
				<button type="button" class="btn btn-primary mt-2 mb-3" id="btnConfirmRegister">Register</button>
			</div>
			<div class="ms-3">
				<button type="button" class="btn btn-primary mb-3" id="btnCancelRegister">Cancel</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var branches = JSON.parse('${branches}');
</script>
<script src="js/registration.js"></script>