
var DEFAULT_INVOCATION_FREQUENCY_IN_MILLIS = 30000;

/**
* Get current consumer position
*/
function getConsumerPostion()
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
 	
	saveConsumerGeoLocation(lat,lon);
        
	//schedule for next
        setTimeout("getConsumerPostion()", getInvocationFrequencyInMillis());
}


/**
* callback method when geolocation call fails
*
*/
function onError(error)
 {
 	//TODO reporting
 	//try again
 	setTimeout("getConsumerPostion()", getInvocationFrequencyInMillis()); 	
 }

/**
* send 
*/ 
function saveConsumerGeoLocation(lat, lon)
{
	var deviceId = getConsumerDeviceId();
	var serviceUrl = getServiceUrl();
	var methodCall = serviceUrl+'/saveConsumerGeoLocation';

	$.ajax({
		type: "POST",
		url: methodCall,
		data: { DEVICE_ID: deviceId, LATITUDE: lat, LONGITUDE: lon}
		}).done(function( msg ) {
		  // alert("data saved");
		$('#lat').html('Latitude:'+lat);
                $('#lon').html('Longitude:'+lon);
		config.log("data saved");
		});
     	
}


function getInvocationFrequencyInMillis()
{
	if(config === undefined)
	{
		return DEFAULT_INVOCATION_FREQUENCY_IN_MILLIS;
	}
	else
	{	
		return config.getCallFrequency();	
	}
}

function getConsumerDeviceId()
{
	if(config === undefined)
	{
		return 0;
	}
	else
	{			
		return config.getDeviceId();	
	}
}

function getServiceUrl()
{
	if(config === undefined)
	{
		return "nothing configured";
	}
	else
	{
		return config.getWebServiceUrl();	
	}
}


