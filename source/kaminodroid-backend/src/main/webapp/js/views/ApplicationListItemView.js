define([ 'jquery', 'underscore', 'backbone', 'text!templates/ApplicationListItem.html' ],
		function($, _, Backbone, template) {

			'use strict';

			var ApplicationListItemView = Backbone.View.extend({
	
				tagName: 'li',
				
				className: 'span3',
				
				template : _.template(template),

				initialize : function() {
					
				},

				render : function() {
					$(this.el).html($(this.template(this.model.toJSON())).children());
					return this.$el;
				},
			});

			return ApplicationListItemView;
		});