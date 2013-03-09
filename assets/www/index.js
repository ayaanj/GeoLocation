
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
	var deviceId = config.getDeviceId();
	var webServiceUrl = config.getWebServiceUrl();
	var methodCall = webServiceUrl+'/saveConsumerGeoLocation';

	try
	{
		$.ajax({
  			type: "POST",
  			url: methodCall,
  			data: { DEVICE_ID: deviceId, LATITUDE: lat, LONGITUDE: lon}
			}).done(function( msg ) {
  			alert( "Data Saved: " + msg );
		});
     	}
     	catch(errr)
     	{
	
		alert(errr);
     	}	
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
 	//setTimeout("getCurrentPostion()", getTimeoutInMillis()); 	
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


