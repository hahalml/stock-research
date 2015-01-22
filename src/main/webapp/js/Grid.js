(function(){
	Grid = function(container, headData, bodyData){
		this.container = container;
		this.headData = headData;
		this.bodyData = bodyData;
		this.init();
	};
	Grid.prototype = {
		init: function(){
			this.el = $("<table class='table'></table>").appendTo(this.container);
			this.renderHeader();
			this.renderBody();
		},
		renderHeader: function(){
			var head = $('<thead></thead>');
			var headRow = this.createRow(this.headData, true);
			head.append(headRow);
			this.el.append(head);
		},
		renderBody: function(){
			var body = $('<tbody></tbody>');
			for(var i=0; i<this.bodyData.length; i++){
				body.append(this.createRow(this.bodyData[i], false));
			}
			this.el.append(body);
		},
		createRow: function(rowData, isHead){
			var cellName = isHead?'th':'td';
			var row = $('<tr></tr>');
			for(var i=0; i<rowData.length; i++){
				var cell = this.createCell(rowData[i], cellName);
				if(i == 0 || i == 1){
					cell.addClass('nowrap');
				}
				row.append(cell);
			}
			return row;
		},
		createCell: function(cellData, cellName){
			cellName = cellName || 'td';
			var cell = $('<'+cellName+'></'+cellName+">");
			cell.html(cellData);
			if(cellData.indexOf('%') > -1){
				if(cellData != '-' && cellData.indexOf('-') > -1){
					cell.css('color', 'green');
				}else{
					cell.css('color', 'red');
				}
			}
			return cell;
		},
		addRows: function(rowDatas){
			var gridBody = this.el.find('tbody');
			for(var j=0; j<rowDatas.length; j++){
				var row = $('<tr></tr>');
				var rowData = rowDatas[j];
				for(var i=0; i<rowData.length; i++){
					var cell = this.createCell(rowData[i], "td");
					if(i == 0 || i == 1){
						cell.addClass('nowrap');
					}
					row.append(cell);
				}
				gridBody.append(row);
			}
			
		}
	};
})();