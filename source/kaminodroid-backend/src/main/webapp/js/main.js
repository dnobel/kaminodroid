require.config({
	baseUrl : 'js',
	paths : {
		jquery : 'libs/jquery.min',
		underscore : 'libs/underscore.min',
		backbone : 'libs/backbone.min',
		bootstrap : 'libs/bootstrap.min',
		text : 'libs/text'
	}
});

require([ 'backbone', 'router', 'views/AppView' ], function(Backbone,
		AppRouter, AppView) {
	new AppRouter();
	Backbone.history.start();
	new AppView();
});