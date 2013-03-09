
var DEFAULT_TIMEOUT = 30000;

/**
* Get current position
*/
function getCurrentPostion()
{
	if (navigator.geolocation)
	{
		navigator.geolocation.getCurrentPosition(onSucess, onError, { maximumAge: 3000, timeout: 5000, enableHighAccuracy: true });
	}     
}

/**
* callback method when geolocation call is successfult
*
*/

function onSucess(position)
{
	var lat = position.coords.latitude;
 	
	var lon = position.coords.longitude;
 	
	sendCurrentPostion(lat,lon);
	
	setTimeout("getCurrentPostion()", getTimeoutInMillis()); 	
}

/**
* send 
*/ 
function sendCurrentPostion(lat, lon)
{
   alert(config.getDeviceId());	
   alert("hello amit " + lat);
}


/**
* callback method when geolocation call fails
*
*/
function onError(error)
 {
 	alert("error"+error);
 	//TODO reporting
 	//try again
 	setTimeout("getCurrentPostion()", getTimeoutInMillis()); 	
 }


function getTimeoutInMillis()
{
	if(config)
	{
		return config.getCallFrequency();
	}
	else
	{
		return DEFAULT_TIMEOUT;	
	}
}


