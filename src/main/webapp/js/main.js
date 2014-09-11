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

	var initEvent = function(){
		$('ul.nav li.days-ago-stat').bind('click', function(){
			
			http_get({
				url:"/get-stock-statistics-info?days=220&stockNum=100",
				success:function(data){
					$('.stock-grid').empty();
					drawGrid(createGridData(data));
				}
			});
		});

		$('ul.nav li.day-by-day-stat').bind('click', function(){
			 http_get({
			 	url:"/stocks-infos-by-day",
			 	success:function(data){
			 		$('.stock-grid').empty();
			 		data = createDayByDayData(data);
			 		drawGrid(data);
			 	}
			 });
		});
	};

	function http_get(cfg){
		cfg = cfg || {};
		var defaultCfg = {
			async:true,
			dataType:'json',
			error:function(err){
				alert(err);
			}
		};
		cfg = $.extend(defaultCfg, cfg);
		$.ajax(cfg);
	}

	function loadGridData(){
		$.ajax({
			"url":"/get-stock-statistics-info?days=10&stockNum=100",
			"async":true,
			"dataType":"json",
			"error" : function(e){
				alert(e);
			},
			"success" : function(data){
				drawGrid(createGridData(data));
			}
		});
	}
	function drawGrid(data){
		 var grid = new Grid(data,$('.stock-grid'));
	     grid.init();
	}


	function createGridData(data){
		var gridData = {
			head:[],
			body:[]
		};
		//gridData.head.push({label:"index"});
		for(var i=0; i<data.head.length; i++){
			gridData.head.push({label:data.head[i]});
		}
		for(var i=0; i<data.body.length; i++){
				var rowData = data.body[i];
				var cols = [];
 
        		cols[0] = {label:rowData.stCode};
        		cols[1] = {label:rowData.name};
        		cols[2] = {label:rowData.currPrice};
        		
        		cols[3] = {label:rowData.incPercent};
        		cols[4] = {label:rowData.max};
        		cols[5] = {label:rowData.min};
        		
        		cols[6] = {label:rowData.avg};
        		cols[7] = {label:rowData.totalDealNum};
        		cols[8] = {label:rowData.totalDealPrice};
        		
        		cols[9] = {label:rowData.startDate};
        		cols[10] = {label:rowData.endDate};
        		cols[11] = {label:rowData.preIncPercent};

        		if(rowData.incPercent.indexOf('-')>-1){
        			cols[3].style={color:'green'};
        		}
        		else{
        			cols[3].style={color:'red'};
        		}

        		if(rowData.preIncPercent.indexOf('-')>-1){
        			cols[11].style={color:'green'};
        		}
        		else{
        			cols[11].style={color:'red'};
        		}
        		//cols.unshift({label:i+1});
        		gridData.body.push(cols)
		}
		return gridData;
	}

	function createDayByDayData(data){
		var gridData = {
			head: null,
			body:[]
		};
		var maxNum = data.maxDataNum;
		var stocks = data.data;
		for(var i=0; i<stocks.length; i++){
			var cols = [];
			var stock = stocks[i];
			var stockData = stock.data;
			if(stockData.length == maxNum && gridData.head == null){
				gridData.head = [];
				gridData.head.push({label:'stock name'});
				for(var j=0; j<maxNum; j++){
					var label = stockData[j].date;
					gridData.head.push({label: label});
				}
			}
			var actualNum = stockData.length;
			cols.push({label:stock.name});
			for(var j=0; j<maxNum; j++){
				var label = getDataFromIndex(stockData, j+(actualNum - maxNum), "-");
				cols.push({label:label});
			}
			gridData.body.push(cols);
		}
		return gridData;
	}
	function createCellItem(itemName, itemVal){
		var $li = $('<li></li>').text(itemName+": "+itemVal);
		return $li;
	}
	function createGridCell(stock){
		var $ul = $('<ul></ul>');
		$ul.append(createCellItem('date',stock.date));
		$ul.append(createCellItem('price',stock.price));
		$ul.append(createCellItem('priceIncPercent',stock.priceIncPercent));

		$ul.append(createCellItem('dealNum',stock.dealNum));
		$ul.append(createCellItem('dealNumIncPercent',stock.dealNumIncPercent));

		$ul.append(createCellItem('dealPrice',stock.dealPrice));
		$ul.append(createCellItem('dealPriceIncPercent',stock.dealPriceIncPercent));
		return $ul;
	}
	function getDataFromIndex(arr, index, defaultVal){
		if(index>=0 && index < arr.length){
			return createGridCell(arr[index]);
		}
		return defaultVal;
	}


	initEvent();
	loadGridData();
   





});
