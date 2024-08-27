function initializeApp() {
	$.get('MainController', {
		action: 'initializeApp'
	}, null);
}

initializeApp();