require.config({
  baseUrl : "/",
	paths : {
		"jquery" : "js/lib/jquery.1.7.2.min",
        "text" : "js/lib/text",
        "jsrender" : "js/lib/jsrender",
        "tmpl" : "js/lib/jquery-tmpl",
        "grid" : "grid/grid"
	}
});
require.config({
    baseUrl : "/",
    paths: {
        "test": "js/lib/test"
    },
    shim: {
        "test": {
            exports: "num_utils"
        }
    }
});
require(['jquery' , "grid"], function($,Grid) {
    var grid = new Grid();
    grid.init();
});
