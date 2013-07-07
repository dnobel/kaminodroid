define([], function() {
	'use strict';

	var Utils = function() {
		this.formatDate = function(timestamp) {
			var date = new Date(timestamp);
			
			var day = date.getDate();
			if(day < 10) {
				day = '0' + day;
			}
		    var month = date.getMonth() + 1;
		    if(month < 10) {
		    	month = '0' + month;
		    }
		    var year = date.getFullYear();
		    
		    var hours = date.getHours();
		    var minutes = date.getMinutes();
		    var seconds = date.getSeconds();
		    
		    return year + '-' + day + '-' + month + ' ' + hours + ':' + minutes + ':' + seconds;
		};
	};
	
	return new Utils();
});