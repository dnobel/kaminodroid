define([ 'jquery', 'underscore', 'backbone', 'views/ApplicationListView', 'views/ApplicationDetailView',
		'collections/Applications', 'models/Application', 'text!templates/App.html' ], function($, _, Backbone, ApplicationListView,
		ApplicationDetailView, Applications, Application, template) {

	'use strict';

	var AppView = Backbone.View.extend({

		el : '#app',

		template : _.template(template),

		initialize : function() {
			_.bindAll(this);
			this.render();
		},
		
		events : {
			'click a.home' : 'navigateToApplications',
			'click .nav a.applications' : 'navigateToApplications'
		},

		render : function() {
			$(this.el).html(this.template({

			}));
		},
		
		navigateToApplications : function(event) {
			event.preventDefault();
			Backbone.history.navigate('applications', {
				trigger : true
			});
			return false;
		},
		
		showApplications : function() {
			$('#main').html('');
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

		showApplicationDetail : function(id) {
			var application = new Application({id: id});
			var self = this;
			application.fetch({
				success : function() {
					self.$('#main').html(new ApplicationDetailView({
						model : application
					}).render());
				}
			});
		}

	});

	return AppView;
});