define(['jquery','text!grid.html', "text!grid.css", "jsrender"], function($,gridHtml){
    var PlainGrid = function(data, container){
    	this.container = container;
    	this.headData = data.head;
    	this.bodyData = data.body;
        this.data = data;
    	container.append(gridHtml);
    	 
    };

    PlainGrid.prototype = {
        init : function(){
           this.el = $('#myTable');
           this.render();
        },

        
        render : function(){
          
            var data = this.data;

            var $head = this.el.find('thead');
            var $body = this.el.find('tbody');

            var $headRow = $('<tr></tr>');
            for(var i=0; i<data.head.length; i++){
                var cell = data.head[i];
                var $col = $('<td></td>').html(cell.label);
                cell.style = cell.style || {};
                $col.css(cell.style);
                $headRow.append($col);
            }
            $head.append($headRow);

            var bodyData = data.body;
            for(var i=0;i<bodyData.length;i++){
                var $row = $('<tr></tr>');
                var rowData = bodyData[i];
                for(var j=0;j<rowData.length;j++){
                    var cellData = rowData[j];
                    var $col = $('<td></td>').html(cellData.label);
                    cellData.style = cellData.style || {};
                    $col.css(cellData.style);
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
