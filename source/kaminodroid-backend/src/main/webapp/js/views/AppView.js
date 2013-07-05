define([ 'jquery', 'underscore', 'backbone', 'views/ApplicationListView',
		'collections/Applications', 'text!templates/App.html' ], function($, _,
		Backbone, ApplicationListView, Applications, template) {

	'use strict';

	var AppView = Backbone.View.extend({

		el : '#app',
		
		template : _.template(template),

		initialize : function() {
			this.render();
		},

		render : function() {
			$(this.el).html(this.template({

			}));
			var applications = new Applications();
			var self = this;
			applications.fetch({
				success : function() {
					self.$('#main').html(new ApplicationListView({
						model : applications
					}).render());
				}
			});
			
		},
	});

	return AppView;
});