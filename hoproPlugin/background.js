// Regex-pattern to check URLs against. 
// It matches URLs like: http[s]://[...]stackoverflow.com[...]
var urlRegex = /^https?:\/\/(?:[^./?#]+\.)?horiversum\.org/;
var port=null;
var hostName = "hopro";
var connected = false;
var d_old = new Date();
var bePrepared = false;
var num_tabs=0;
var disabled = false;

// A function to use as callback
function doStuffWithDom(domContent) {
	if (disabled == false && connected == true){
		sendNativeMessage(domContent);
		console.log("I sent it");
	}	
}

function endHopro(){
	if(disabled==false){
		sendNativeMessage("!STOP!");
		port.disconnect();
		bePrepared = false;
		connected = false;
		port = null;
		disabled = true;
		chrome.browserAction.setIcon({path:"icon_off.png"});
	}
}

function beginHopro(){
	disabled = false;
	sendNativeMessage("!START!")
	chrome.browserAction.setIcon({path:"icon.png"});
}


function onNativeMessage(message) {
  console.log("Received message: <b>" + JSON.stringify(message) + "</b>");
}

function onDisconnected() {
  console.log("Failed to connect: " + chrome.runtime.lastError.message);
  console.log('disconnected from native app.');
  connected = false;
  port = null;
}

function sendNativeMessage(message) {  
	if (connected==false){
		chrome.browserAction.setIcon({path:"icon.png"});
		console.log("Connecting to native messaging host <b>" + hostName + "</b>")
		port = chrome.runtime.connectNative(hostName);
		port.onMessage.addListener(onNativeMessage);
		port.onDisconnect.addListener(onDisconnected);
		connected = true;
	}	
	port.postMessage(message);  
}

function hasOwnProperty(obj,prop){
	var proto = obj.__proto__ || obj.constructor.prototype;
    return (prop in obj) &&
        (!(prop in proto) || proto[prop] !== obj[prop]);
}

// When the browser-action button is clicked...
chrome.browserAction.onClicked.addListener(function (tab) {
	if(disabled==true){
		console.log("enable");
		beginHopro();
	}else{
		console.log("disable");
		endHopro();
	}
	
});	

// When the tab is updated
chrome.tabs.onUpdated.addListener(function (tabId,changeInfo,tab) {
	if(disabled==false){
		// get current time
		var d_new = new Date();
		console.log(changeInfo)
		if(hasOwnProperty(changeInfo,"favIconUrl")){
			bePrepared = true;
		}
		// ...check the URL of the active tab against our pattern and if the last call is long enough ago
		if ((urlRegex.test(tab.url)) && (bePrepared==true) && (changeInfo.status=="complete")){
			// send a message specifying a callback too		
			chrome.tabs.sendMessage(tab.id, {text: 'report_back'}, doStuffWithDom);
			d_old = new Date();
			bePrepared=false;
		}
	}	
});

chrome.tabs.getAllInWindow( null, function( tabs ){
    console.log("Initial tab count: " + tabs.length);
    num_tabs = tabs.length;
});

chrome.tabs.onCreated.addListener(function(tab){
    //chrome.tabs.getAllInWindow( null, function( tabs ){
		//console.log("Initial tab count: " + tabs.length);
		//num_tabs = tabs.length;
	//});
	console.log("Open tabs " + num_tabs);
    //console.log("Tab created event caught. Open tabs #: " + num_tabs);
});

chrome.tabs.onRemoved.addListener(function(tabId){
    num_tabs--;
	console.log("Remaining tabs " + num_tabs);
    //console.log("Tab removed event caught. Open tabs #: " + num_tabs);
    if( num_tabs == 0 ){
		console.log("Schließe hopro -> deactivated");
		//endHopro();
	}        
});