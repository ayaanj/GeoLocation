
var DEFAULT_TIMEOUT_INTERVAL_IN_MILLIS = 30000;

/**
* Get current consumer position
*/
function getConsumerPostion(){	

	if (navigator.geolocation){
		navigator.geolocation.getCurrentPosition(onSucess, onError, { maximumAge: 3000, timeout: 5000, enableHighAccuracy: true });
	} 
}

/**
* callback method when geolocation call is successfult
*
*/

function onSucess(position){

	var lat = position.coords.latitude;
 	
	var lon = position.coords.longitude;
 	
	$('#lat').html(lat);

	$('#lon').html(lon);

 	saveConsumerGeoLocation(lat,lon);
        
	//schedule for next
        setTimeout("getConsumerPostion()", getTimeoutInterval());
}


/**
* callback method when geolocation call fails
*
*/
function onError(error){
 	
	//TODO reporting
 	//try again
 	setTimeout("getConsumerPostion()", getTimeoutInterval()); 	
 }


function saveConsumerGeoLocation(lat, lon){
	
	var deviceId = getConsumerDeviceId();
	
	var webServiceUrl = getWebServiceMethodUrl();
	
	$('#device').html(deviceId);

       
	$.ajax({
		type: "POST",
		url: webServiceUrl,
		data: { DEVICE_ID: deviceId, LATITUDE: lat, LONGITUDE: lon},
		error: function(xhr){ $('#status').html(xhr.status + " " + xhr.statusText);
			//config.log("failed to post data"+xhr.status);			
			},
                success: function(result,status,xhr){ $('#status').html(status + " " + result);
			 //config.log("data successfully saved"+status);	
			}
		});
     	
}


function getTimeoutInterval(){

	if(typeof config === "undefined" ){

		return DEFAULT_TIMEOUT_INTERVAL_IN_MILLIS;
	}
	else{	
		return config.getInterval();	

	}
}

function getConsumerDeviceId(){
	if(typeof config === "undefined" ){
		
		return 0;
	}
	else{			
		return config.getDeviceId();	
	}
}

function getWebServiceMethodUrl(){

	if(typeof config === "undefined"){
		
		return "none";
	}
	else{
		return config.getWebServiceMethodUrl();	
	}
}


