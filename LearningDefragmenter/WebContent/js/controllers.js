/**
 * 
 */
'use strict';

angular.module('LearnigDefragmenterAPP', []).controller('UserDefragmenterSelection',function($scope){
	$scope.defragmentedSelection={};
	$scope.defragmentedSelection.defragment=function(event){
		console.log('defragmenting...');
		if (window.getSelection) {  // all browsers, except IE before version 9
		    var range = window.getSelection ();
		    alert (range.toString ());
		} 
		else {
		    if (document.selection.createRange) { // Internet Explorer
		        var range = document.selection.createRange ();
		        alert (range.text);
		    }
		}
	};
});
angular.module('LearnigDefragmenterAPP').config(function() {
	//angular.element('mainFrame').contents().find("body").mouseup(function(){
	//	alert ('mouseup');
   // });
	var ifrm = document.getElementById('mainFrame');
	var doc = ifrm.contentDocument? ifrm.contentDocument: ifrm.contentWindow.document;
	console.log(doc);
	$("#mainFrame").ready(function (event){
		var mainFrameBody=$('#mainFrame').contents().find("body");
		//$(mainFrameBody).bind("mouseup", function() { alert("Hello"); });
		console.log(mainFrameBody);
		mainFrameBody.click(function(){
			alert('mouseup...');
		});
		console.log(mainFrameBody);
	});
	console.log('config...');
	
	
});