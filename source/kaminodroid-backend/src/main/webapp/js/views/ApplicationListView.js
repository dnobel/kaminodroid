define([ 'jquery', 'underscore', 'backbone', 'views/ApplicationListItemView',
		'text!templates/ApplicationList.html' ], function($, _, Backbone,
		ApplicationListItemView, template) {

	'use strict';

	var ApplicationListView = Backbone.View.extend({

		template : _.template(template),

		initialize : function() {

		},

		render : function() {

			this.$el.html(this.template({}));

			_.each(this.model.models, function(application) {
				this.$('.applications').append(new ApplicationListItemView({
					model : application
				}).render());
			}, this);

			return this.el;
		},
	});

	return ApplicationListView;
});