(function(){
	var init = function(){
		bindEvent();
		loadStock();		 
	};

	var loadStock = function(){
		$.ajax({
			"url": "/stock-field-stat",
			"data": "field=curr_price&symbols=",
			"dataType": "json",
			"success": function(data){
				var gridData = new GridData(data, "current");
				var grid = new Grid($('.stock-grid'),gridData.headData, gridData.bodyData);
			}
		});
	};
	
	var autocomplete = function(q){
		$.ajax({
			"url": "/autocomplete",
			"data": "query="+q,
			"dataType": "json",
			"success": function(data){
				 var dropDown = new Dropdown(data);
			}
		});
	};
	
	var bindEvent = function(){
		$('body').on('.search-stock', 'keyup', function(e){
			//var q = $(e.target || e.srcElement).val();
			//autocomplete(q);
		});
		 
	};

	init();
})();