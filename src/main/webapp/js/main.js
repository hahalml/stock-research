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
	function loadGridData(){
		$.ajax({
			"url":"/get-stock-statistics-info?days=10&stockNum=100",
			"async":true,
			"dataType":"json",
			"error" : function(e){
				alert(e);
			},
			"success" : function(data){
				drawGrid(data);
			}
		});
	}
	function drawGrid(data){
		 var grid = new Grid(data,$('.stock-grid'));
	     grid.init();
	}
	loadGridData();
   
});
