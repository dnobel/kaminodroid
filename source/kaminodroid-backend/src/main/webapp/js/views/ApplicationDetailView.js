define([ 'jquery', 'underscore', 'backbone', 'utils/Utils', 'text!templates/ApplicationDetail.html' ], function($, _,
		Backbone, Utils, template) {

	'use strict';

	var ApplicationDetailView = Backbone.View.extend({

		template : _.template(template),

		initialize : function() {

		},

		render : function() {
			var vars = this.model.toJSON();
			vars.latestArtifact.formattedDate = Utils.formatDate(vars.latestArtifact.date);
			_.each(vars.artifacts, function(artifact) {
				artifact.formattedDate = Utils.formatDate(artifact.date);
			});
			$(this.el).html($(this.template(vars)).children());
			return this.$el;
		},
	});

	return ApplicationDetailView;
});