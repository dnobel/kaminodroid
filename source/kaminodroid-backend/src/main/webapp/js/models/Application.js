define([ 'underscore', 'backbone' ], function(_, Backbone) {
	'use strict';

	var Application = Backbone.Model.extend({
		urlRoot : '/rest/applications'
	});

	return Application;
});