/**
 * 
 */

let isTest = false;

function createOptions(){
	let html = '';
	$.each(branchList, function(index, item){
		if ("y" == item.isActive){
			html += '<option id="item'+item.branchId+'" value="'+item.branchId+'">'+item.branchName+'</option>';
		}
	})
	$("#selectTest").html(html);
}

function createInventoryTable(inventory) {
	let html = '';
	html += '<table class="inventory">';
	html += '  <tr>';
	html += '    <th>Item</th>';
	html += '    <th>Quantity</th>';
	html += '  </tr>';
	$.each(inventory, function(index, item) {
		if ('Y' === item.activeTag) {
			html += '<tr id="item'+index+'row">';
			html += '  <td id="item'+index+'desc">' + item.description + '</td>';
			html += '  <td id="item'+index+'qty" class="center-aligned">' + item.quantity + '</td>';
			html += '</tr>';
		}
	});
	html += '</table>';
	$('#divInventoryTable').html(html);
	bindRowsClick(inventory);
}

function testfunc(){
	$("#test").html("HELLO");
}

$("#testP").hide();
$("#btnTest").click(function(){
	if(isTest){
		$("#testP").hide();
	} else {
		$("#testP").show();
	}
	isTest = isTest ? false : true;
	console.log(isTest);
});

console.log("Hello World");
console.log(rawMaterialList);
createOptions();


