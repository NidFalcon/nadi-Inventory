function addselect() {
	let html = '';
	html += "<tr><td><select>",
	html += "	<option>1</option>",
	html += "	<option>2</option>",
	html += "	<option>3</option>",
	html +="</select></td></tr>"
	
	$('#add').click(function () {
		$('table').append(html)
	})
}
	addselect();
