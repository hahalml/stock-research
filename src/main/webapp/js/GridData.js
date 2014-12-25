(function() {
    rock = rock || {};
    var GridData = rock.GridData = function(data, field) {
        this._data = data;
        this._field = field;
    };
    GridData.prototype = {
        init: function() {
            var data = this._data;
            this.headData = data.head;
            this.bodyData = [];
            var field = this._field;
            for (var i = 0; i < data.body.length; i++) {
                var cols = [];
                var rowData = data.body[i];
                for (var j = 0; j < rowData.length; j++) {
                    cols[j] = rowData[j][field];
                }
                this.bodyData.push(cols);
            }
        }
    }
})();
