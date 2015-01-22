var page = require('webpage').create();
page.onResourceRequested = function(request) {
  // console.log('Request ' + JSON.stringify(request, undefined, 4));
  console.log("req url:  "+request.url);
};
page.onResourceReceived = function(response) {
  // console.log('Receive ' + JSON.stringify(response, undefined, 4));
  console.log("res url:  "+response.url);
};
page.open('http://www.baidu.com');