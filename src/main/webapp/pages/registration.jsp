<h1>Register NOW!</h1>
<form id="registrationFrom">
  <table>
    <tr>
      <td><input type="text" class="input" id="txtRegUsername" name="txtRegUsername" placeholder="Username" /></td>
    </tr>
    <tr>
      <td><input type="password" class="input" id="txtRegPassword" name="txtRegPassword" placeholder="Password" /></td>
    </tr>
    <tr>
      <td><select  id="txtBranchId" name="txtBranchId">
			<c:forEach var="item" items="${branches}">
			    <option value="${item.branchId}">${item.branchName}</option>
			</c:forEach>
      </select></td>
    </tr>
    <tr>
      <td><button type="button" class="button blue right" id="btnCancelRegister">Cancel</button></td>
      <td><button type="button" class="button blue right" id="btnConfirmRegister">Register</button></td>
    </tr>
  </table>
</form>
<script type="text/javascript">
	var branches = JSON.parse('${branches}');
</script>
<script src="js/registration.js"></script>