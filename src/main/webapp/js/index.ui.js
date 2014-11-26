(function(){
	 window.drawGrid = function(data){
		var table = $('.stock-info-days-summary');
		var tableBody = table.find("tbody");
		for(var i=0; i<data.length; i++){
			var stock = data[i];
			var tr = $('<tr></tr>');
			$('<td></td>').text(stock.stCode).appendTo(tr);
			$('<td></td>').text(stock.name).appendTo(tr);
			$('<td></td>').text(stock.curr).appendTo(tr);
			$('<td></td>').text(stock.max).appendTo(tr);
			$('<td></td>').text(stock.min).appendTo(tr);

			$('<td></td>').text(stock.avg).appendTo(tr);
			$('<td></td>').text(stock.incPercent).css('color',getColor(stock.incPercent)).appendTo(tr);
			$('<td></td>').text(stock.lastIncPercent).css('color',getColor(stock.lastIncPercent)).appendTo(tr);
			$('<td></td>').text(stock.startDate).appendTo(tr);
			$('<td></td>').text(stock.endDate).appendTo(tr);

			$('<td></td>').text(stock.totalDealNum).appendTo(tr);
			$('<td></td>').text(stock.totalDealPrice).appendTo(tr);
			tableBody.append(tr);
		}
	};
	window.drawStockPeriodsSummaryGrid = function(data){
		var field = 'totalDealNum';
		if(data.length == 0){
			return;
		}
		var table = $('.stocks-periods-summary');
		var headRow = table.find('thead tr');
		var tableBody = table.find("tbody");

		var stock0 = data[0];
		$('<th></th>').text(stock0.stCode).appendTo(headRow);
		$('<th></th>').text(stock0.name).appendTo(headRow);
		for(var i=0; i<stock0.statisticsDatas.length; i++){
			var d = stock0.statisticsDatas[i];
			$('<th></th>').text(d.label).appendTo(headRow);
		}

		for(var i=0; i<data.length; i++){
			var stock = data[i];
			var tr = $('<tr></tr>');
			$('<td></td>').text(stock.stCode).appendTo(tr);
			$('<td></td>').text(stock.name).appendTo(tr);

			var periodsData = stock.statisticsDatas;
			for(var j=0; j<periodsData.length; j++){
				var periodsData = periodsData[j];
				$('<td></td>').text(periodsData[field]).appendTo(tr);
			}
			tableBody.append(tr);
		}

	};
	function isPositiveNum(str){
		if(str == '-')
			return true;
		if(str.replace('%','') >= 0.0)
			return true;
		return false;
	}
	function getColor(str){
		if(isPositiveNum(str))
			return 'red';
		return 'green';
	}
})();