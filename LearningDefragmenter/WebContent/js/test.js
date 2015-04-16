/**
 * 
 */
$(document).ready(function() {
        var isOverIFrame = false;

        function processMouseOut() {
            log("IFrame mouse >> OUT << detected.");
            isOverIFrame = false;
            top.focus();
        }
        
        function processMouseUp() {
            log("IFrame mouse >> UP << detected.");
            isOverIFrame = false;
            top.focus();
        }

        function processMouseOver() {
            log("IFrame mouse >> OVER << detected.");
            isOverIFrame = true;
        }

        function processIFrameClick() {
            if(isOverIFrame) {
                // replace with your function
                log("IFrame >> CLICK << detected. ");
            }
        }

        function log(message) {
            console.log(message);
        }

        function attachOnloadEvent(func, obj) {
            if(typeof window.addEventListener != 'undefined') {
                window.addEventListener('load', func, false);
            } else if (typeof document.addEventListener != 'undefined') {
                document.addEventListener('load', func, false);
            } else if (typeof window.attachEvent != 'undefined') {
                window.attachEvent('onload', func);
            } else {
                if (typeof window.onload == 'function') {
                    var oldonload = onload;
                    window.onload = function() {
                        oldonload();
                        func();
                    };
                } else {
                    window.onload = func;
                }
            }
        }

        function init() {
            var element = document.getElementsByTagName("iframe");
            console.log(element);
            for (var i=0; i<element.length; i++) {
            	 console.log(element[i]);
                element[i].onmouseover = processMouseOver;
                element[i].onmouseout = processMouseOut;
                element[i].onmouseup=processMouseUp;
            }
            if (typeof window.attachEvent != 'undefined') {
                top.attachEvent('onblur', processIFrameClick);
            }
            else if (typeof window.addEventListener != 'undefined') {
                top.addEventListener('blur', processIFrameClick, false);
            }
        }

        attachOnloadEvent(init);
    });