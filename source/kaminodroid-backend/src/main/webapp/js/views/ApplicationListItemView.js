define([ 'jquery', 'underscore', 'backbone', 'text!templates/ApplicationListItem.html', 'utils/Utils' ],
		function($, _, Backbone, template, Utils) {

			'use strict';

			var ApplicationListItemView = Backbone.View.extend({
	
				tagName: 'li',
				
				className: 'span3',
				
				template : _.template(template),

				initialize : function() {
					
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