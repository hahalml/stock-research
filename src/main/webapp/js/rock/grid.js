(function(export) {
    
    var Grid = function(selector) {
        this.dom = $(selector);
    };

    Grid.prototype = function() {

        draw: function() {
        	this.drawHead();
        	this.drawBody();
        },
        drawHead: function() {

        },
        drawBody: function() {

        },
        refresh: function(){

        }

    };


    window.Grid = Grid;
})(window);
