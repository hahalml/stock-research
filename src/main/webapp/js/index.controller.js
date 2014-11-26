(function(){
	var stockDaysInfo = 1;
	var stockPeriodsInfo = 2;
	var infoType = stockDaysInfo;


	function loadData(){
		if(infoType == stockDaysInfo)
			loadStockDaysInfo();
		else
			loadStockPeriodsInfo();
	}
	function loadStockDaysInfo(){
		$.ajax({
			url:'stocks-infos-days-ago',
			dataType:'json',
			timeout:100000000,
			success: function(data){
				drawGrid(data);
			}
		});
	}
	function loadStockPeriodsSummary(){
		$.ajax({
			url:'stocks-infos-summary-by-week',
			dataType:'json',
			timeout:100000000,
			success: function(data){
				drawGrid(data);
			}
		});
	}



	loadData();

})();