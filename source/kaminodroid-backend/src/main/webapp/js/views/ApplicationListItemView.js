define([ 'jquery', 'underscore', 'backbone', 'utils/Utils', 'router', 'text!templates/ApplicationListItem.html' ], function($, _,
		Backbone, Utils, AppRouter, template) {

	'use strict';

	var ApplicationListItemView = Backbone.View.extend({

		tagName : 'li',

		className : 'span3 application',

		template : _.template(template),

		initialize : function() {
			_.bindAll(this);
		},

		events : {
			'click .detail-button' : 'showDetailView'
		},

		showDetailView : function(event) {
			event.preventDefault();
			var appId = $(event.target).data('id');
			Backbone.history.navigate('applications/' + appId, {
				trigger : true
			});
			return false;
		},

		render : function() {
			var vars = this.model.toJSON();
			vars.latestArtifact.formattedDate = Utils.formatDate(vars.latestArtifact.date);
			$(this.el).html($(this.template(vars)).children());
			return this.$el;
		},
	});

	return ApplicationListItemView;
});