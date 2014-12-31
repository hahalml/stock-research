(function(){
	DropdownList = function(data, cfg){
		this.data = data;
		this.cfg = cfg;
		this.init();
	};

	DropdownList.prototype = {
		init: function(){
			var data = thhis.data;
			var html = [];
			html.push('<ul class="list-group">');
			for(var i=0; i<data.length; i++){
				html.push('<li class="list-group-item">');
				html.push(data[i]);
				html.push('</li>');
			}
			html.push('</ul>')
			this.el = $(html.join(''));
			this.el.css('position','absolute');
			this.setPos(cfg.left, cfg.top);
		},
		setPos: function(left, top){
			this.el.css({
				left:left,
				top:top
			});
		}
	}
})();