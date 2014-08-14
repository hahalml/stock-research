define(['jquery','text!grid.html',"jsrender"], function($,gridHtml){
    var PlainGrid = function(data, opt){
    	$('body').append(gridHtml);
//     	this.defaultOpt = {
//     		container:$(body)
//     	};
//     	$.extends(this.defaultOpt, opt);
//     	this.data = data;
       // this.init();
    };

    PlainGrid.prototype = {
        init : function(){
           this.el = $('#myTable');
            
           this.render();
        },
        render : function(){
        	var data = {
        		cols : ['col1','col2','col3'],
        		rows : [[1,2,3],[11,22,33]]
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
