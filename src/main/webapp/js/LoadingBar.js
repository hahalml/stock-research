(function(){
	var LoadingBar = window.LoadingBar = function($container){
		var strArr = [];
		strArr.push('<div class="spinner">');

		strArr.push('<div class="spinner-container container1">');
		strArr.push('<div class="circle1"></div>');
		strArr.push('<div class="circle2"></div>');
		strArr.push('<div class="circle3"></div>');
		strArr.push('<div class="circle4"></div>');
		strArr.push('</div>');
		 
		strArr.push('<div class="spinner-container container2">');
		strArr.push('<div class="circle1"></div>');
		strArr.push('<div class="circle2"></div>');
		strArr.push('<div class="circle3"></div>');
		strArr.push('<div class="circle4"></div>');
		strArr.push('</div>');

		strArr.push('<div class="spinner-container container3">');
		strArr.push('<div class="circle1"></div>');
		strArr.push('<div class="circle2"></div>');
		strArr.push('<div class="circle3"></div>');
		strArr.push('<div class="circle4"></div>');
		strArr.push('</div>');

		strArr.push('</div>');

		$container.append($(strArr.join('')));
		this.el = $container.find('.spinner');
		 
	};
	LoadingBar.prototype = function(){
		destroy: function(){
			this.el.remove();
		}
	};

})();