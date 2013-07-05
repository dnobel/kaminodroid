define([ 'underscore', 'backbone', 'models/Application' ], function(_, Backbone, Application) {
	'use strict';

	var Applications = Backbone.Collection.extend({
		model: Application,
		url: '/rest/applications'
	});

	return Applications;
});