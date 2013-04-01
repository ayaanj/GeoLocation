var DEFAULT_TIMEOUT_INTERVAL_IN_MILLIS = 900000;

/**
 * Get current consumer position
 */
function updateUserPostion() {

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(onSucess, onError, {
            maximumAge: 3000,
            timeout: 5000,
            enableHighAccuracy: true
        });
    }

}

/**
 * callback method when geolocation call is successfult
 *
 */

function onSucess(position) {

    var lat = position.coords.latitude;

    var lon = position.coords.longitude;
   
    var userId = window.localStorage.userId;

    $('#lat').html(lat);

    $('#lon').html(lon);

    $('#userId').html(userId);

    saveConsumerGeoLocation(userId, lat, lon);

    //schedule for next
    setTimeout("updateUserPostion()", getTimeoutInterval());
}


/**
 * callback method when geolocation call fails
 *
 */
function onError(error) {

    //TODO reporting
    //try again
    setTimeout("updateUserPostion()", getTimeoutInterval());
}


function saveConsumerGeoLocation(userId, lat, lon) {

    var webServiceUrl = getWebServiceMethodUrl();

    var networkConnectivityAvailable = isConnected();

    if (networkConnectivityAvailable) {
        $.ajax({
            type: "POST",
            url: webServiceUrl,
            data: {
                DEVICE_ID: userId,
                LATITUDE: lat,
                LONGITUDE: lon
            },
            error: function (xhr) {
                $('#status').html(xhr.status + " " + xhr.statusText);
                config.log("failed to post data");
            },
            success: function (result, status, xhr) {
                $('#status').html(status + " " + result);
                config.log("data successfully saved");
            }
        });
    }
}


function getTimeoutInterval() {

    if (typeof config === "undefined") {

        return DEFAULT_TIMEOUT_INTERVAL_IN_MILLIS;
    } else {
        var batteryPct = config.getBatteryPercentage();
        var temp = 100 - batteryPct;
        if (temp < 15) {
            return config.getMinInterval();
        } else if (temp > 85) {
           return config.getMaxInterval();
        } else { 	
            return config.getMaxInterval() * (temp / 100);
        }
    }
}


function getWebServiceMethodUrl() {

    if (typeof config === "undefined") {

        return "none";
    } else {
        return config.getWebServiceMethodUrl();
    }
}

function isConnected() {

    if (typeof config === "undefined") {

        return false;
    } else {
        return config.isConnected();
    }
}
