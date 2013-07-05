define([ 'jquery', 'underscore', 'backbone' ], function($, _, Backbone) {
	var AppRouter = Backbone.Router.extend({

		routes : {
			'applications' : 'showApplications',
			'*actions': 'showApplications' 
		},
	
		showApplications: function() {
			
		},
		
		initialize: function() {
			
		}
	});
	
	return AppRouter;
});