// ==UserScript==
// @name       Mirkoonline
// @namespace  http://wykop.pl/
// @version    1.0
// @description  Liczba aktywnych mirków
// @match      http://www.wykop.pl/*
// @copyright  2014, @Grizwold
// @updateURL   http://tomek.ckhost.pl/mirkoonline/mirkoonline.user.js
// @installURL  http://tomek.ckhost.pl/mirkoonline/mirkoonline.user.js
// @downloadURL http://tomek.ckhost.pl/mirkoonline/mirkoonline.user.js
// ==/UserScript==
 
var load,execute,loadAndExecute;load=function(a,b,c){var d;d=document.createElement("script"),d.setAttribute("src",a),b!=null&&d.addEventListener("load",b),c!=null&&d.addEventListener("error",c),document.body.appendChild(d);return d},execute=function(a){var b,c;typeof a=="function"?b="("+a+")();":b=a,c=document.createElement("script"),c.textContent=b,document.body.appendChild(c);return c},loadAndExecute=function(a,b){return load(a,function(){return execute(b)})};
 
var loadAddon = function() {
	$.ajax({
		url: 'http://mirkoonline.herokuapp.com/jsonp',
		dataType: 'jsonp',
		success: function(data){
			$('#nav > div > ul.clearfix.mainnav > li:nth-child(5) > a').append(' <em class="mark-number mirko-counter">'+data.counter+'</em>');
		}
	});
}
execute(loadAddon);