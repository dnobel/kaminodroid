define([ 'jquery', 'underscore', 'backbone', 'views/AppView' ], function($, _, Backbone, AppView) {
	var AppRouter = Backbone.Router.extend({
		
		appView: null,

		routes : {
			'applications' : 'showApplications',
			'applications/:id': 'showApplicationDetail',
			'*actions': 'showApplications' 
		},
	
		showApplications: function() {
			this.appView.showApplications();
		},
		
		showApplicationDetail: function(id) {
			this.appView.showApplicationDetail(id);
		},
		
		initialize: function() {
			this.appView = new AppView();
			_.bindAll(this);
		}
	});
	
	return new AppRouter();
});