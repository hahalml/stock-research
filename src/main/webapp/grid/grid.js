define(['jquery','text!grid.html', "text!grid.css", "jsrender"], function($,gridHtml){
    var PlainGrid = function(data, container){
    	this.container = container;
    	this.headData = data.head;
    	this.bodyData = data.body;
    	container.append(gridHtml);
    	 
    };

    PlainGrid.prototype = {
        init : function(){
           this.el = $('#myTable');
           this.render();
        },
        createRowsData : function(){
        	// [[1,2,3],[11,22,33]]
        	var rows = [];
        	var data = this.bodyData;
        	for(var i=0; i<data.length; i++){
        		var rowData = data[i];
        		var cols = [];
        		rows.push(cols);
        		cols[0] = rowData.stCode;
        		cols[1] = rowData.name;
        		cols[2] = rowData.currPrice;
        		
        		cols[3] = rowData.incPercent;
        		cols[4] = rowData.max;
        		cols[5] = rowData.min;
        		
        		cols[6] = rowData.avg;
        		cols[7] = rowData.totalDealNum;
        		cols[8] = rowData.totalDealPrice;
        		
        		cols[9] = rowData.startDate;
        		cols[10] = rowData.endDate;
        		cols[11] = rowData.preIncPercent;
        		
        	}
        	return rows;
        },
        render : function(){
        	var self = this;
        	var data = {
        		cols : self.headData,
        		rows : self.createRowsData()
        	};
        	var $head = this.el.find('thead');
        	var $body = this.el.find('tbody');

        	var $headRow = $('<tr></tr>');
        	for(var i=0; i<data.cols.length; i++){
        		var $col = $('<td></td>').text(data.cols[i]);
        		$headRow.append($col);
        	}
        	$head.append($headRow);

        	for(var i=0;i<data.rows.length;i++){
        		var $row = $('<tr></tr>');
        		for(var j=0;j<data.rows[i].length;j++){
        			var $col = $('<td></td>').text(data.rows[i][j]);
        			$row.append($col);
        		}
        		$body.append($row);
        	}

        },
    	setOptItem : function(){

    	},
    	setOpt : function(opt){
    		this.opt = opt;
    	},
        refresh : function(){


        },
        setData : function(data){
        	this.data = data;
        },
        _renderData : function(){

        }


    }


    return PlainGrid;
});
