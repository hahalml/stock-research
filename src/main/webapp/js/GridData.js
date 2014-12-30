(function() {

    GridData = function(data, field) {
        this._data = data;
        this._field = field;
        this.init();
    };
    GridData.prototype = {
        init: function() {
            var data = this._data;
            this.headData = data.head.slice(0, data.head.length);
            this.bodyData = [];
            var field = this._field;
            for (var i = 0; i < data.body.length; i++) {
                var cols = [];
                var rowData = data.body[i];
                for (var j = 0; j < rowData.length; j++) {
                    cols[j] = rowData[j][field];
                }
                this.bodyData.push(cols);
                cols.unshift(rowData[0].name);
                cols.unshift(rowData[0].symbol);
            }
            this.headData.unshift("股票名称");
            this.headData.unshift("股票代码");
        }
    }
})();
