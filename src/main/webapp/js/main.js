(function(){
	var init = function(){
		window.gridData = {
			field:"current",
			data:null
		};
		window.stockField = 'curr_price';
		window.symbols = "";
		$(document).ready(function(){
			bindEvent();
			loadStock();
		});		 
	};

	var loadStock = function(){
		$.ajax({
			"url": "/stock-field-stat",
			"data": "field="+stockField+"&symbols="+symbols,
			"dataType": "json",
			"success": function(data){
				window.gridData.field = "current";
				window.gridData.data = data;
				createGrid();
			}
		});
	};
	
	var autocomplete = function(q, event){
		$.ajax({
			"url": "/autocomplete",
			"data": "query="+q,
			"dataType": "json",
			"success": function(data){
				console.log(data);
				createDropdownList(data, event);			
			}
		});
	};
	var createDropdownList = function(data, event){
		var d = [];
		for(var i=0; i<data.length; i++){
			d.push(data[i].symbol + "  " +data[i].name);
		}
		var offset = $(event.target || event.srcElement).offset();
		var dropdown = new DropdownList(d, {
			left: offset.left,
			top: offset.top
		});
	};
	var bindEvent = function(){
		$('body').on('keyup', '.search-stock', function(e){
			var q = $(e.target || e.srcElement).val();
			autocomplete(q, e);
			 
		});
		bindFieldEvent();
	};
	var bindFieldEvent = function(){
		$('body').on('click', '.settings span', function(e){
			var field = $(e.target || e.srcElement).attr('v');
			gridData.field = field;
			createGrid();
		});
	};

	var bindStockField = function(){
		$('body').on('click', '.stock-field span', function(e){
			stockField = $(e.target || e.srcElement).attr('field');
			loadStock();
		});
	};
	//stock-field
	var createGrid = function(){
		var gridData = new GridData(window.gridData.data, window.gridData.field);
		$('.stock-grid').empty();
		var grid = new Grid($('.stock-grid'),gridData.headData, gridData.bodyData);
		window.grid = grid;
	};
	init();
})();