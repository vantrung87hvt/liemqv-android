
Sys.Net.WebServiceProxy.prototype.restInvoke = Sys$Net$WebServiceProxy$restInvoke;
	
function Sys$Net$WebServiceProxy$restInvoke(uri, httpVerb, payload, methodName, onSuccess, onFailure, userContext) {
    // Create a web request to make the method call
    var request = new Sys.Net.WebRequest();
    
    // Set the Url
    request.set_url(Sys.Net.WebRequest._createUrl(uri));
    
    // Set the appropriate verb
    request.set_httpVerb(httpVerb);
    
    // This demo will only use json
    request.get_headers()['Content-Type'] = 'application/json; charset=utf-8';

    // Use the serializer in the AJAX library to Serialize    
    var body = null;
    body = Sys.Serialization.JavaScriptSerializer.serialize(payload);
    if (body === "{}") body = "";

    // Set the body to the json string
    request.set_body(body);
    request.get_headers()["Content-Length"] = body.length;
    
    request.add_completed(onComplete);
    request.invoke();


    function onComplete(response, eventArgs) {
        if (response.get_responseAvailable()) {
            var statusCode = response.get_statusCode();
            var result = null;
           
            try {
                var contentType = response.getResponseHeader("Content-Type");
                if (contentType.startsWith("application/json")) {
                    result = response.get_object();
                }
                else if (contentType.startsWith("text/xml")) {
                    result = response.get_xml();
                }
                // Default to the response text
                else {
                    result = response.get_responseData();
                }
            } catch (ex) {
            }

            var error = response.getResponseHeader("jsonerror");
            var errorObj = (error === "true");
            if (errorObj) {
                if (result) {
                    result = new Sys.Net.WebServiceError(false, result.Message, result.StackTrace, result.ExceptionType);
                }
            }
            else if (contentType.startsWith("application/json")) {
                //DevDiv 88409: Change JSON wire format to prevent CSRF attack
                //The return value is wrapped inside an object with , 'd' field set to return value 
                if (!result || typeof(result.d) === "undefined") {
                    //throw Sys.Net.WebServiceProxy._createFailedError(methodName, String.format(Sys.Res.webServiceInvalidJsonWrapper, methodName));
                }
                else
                    result = result.d;
            }
            if (((statusCode < 200) || (statusCode >= 300)) || errorObj) {
                if (onFailure) {
                    if (!result || !errorObj) {
                        result = new Sys.Net.WebServiceError(false /*timedout*/, String.format(Sys.Res.webServiceFailedNoMsg, methodName), "", "");
                    }
                    result._statusCode = statusCode;
                    onFailure(result, userContext, methodName);
                }
                else {
                    // In debug mode, if no error was registered, display some trace information
                    var error;
                    if (result && errorObj) {
                        // If we got a result, we're likely dealing with an error in the method itself
                        error = result.get_exceptionType() + "-- " + result.get_message();
                    }
                    else {
                        // Otherwise, it's probably a 'top-level' error, in which case we dump the
                        // whole response in the trace
                        error = response.get_responseData();
                    }
                    // DevDiv 89485: throw, not alert()
                    throw Sys.Net.WebServiceProxy._createFailedError(methodName, String.format(Sys.Res.webServiceFailed, methodName, error));
                }
            }
            else if (onSuccess) {
                onSuccess(result, null, null);
            }
        }
        else {
            var msg;
            if (response.get_timedOut()) {
                msg = String.format(Sys.Res.webServiceTimedOut, null);
            }
            else {
                msg = String.format(Sys.Res.webServiceFailedNoMsg, null)
            }
            if (onFailure) {
                onFailure(new Sys.Net.WebServiceError(response.get_timedOut(), msg, "", ""), userContext, null);
            }
            else {
                // In debug mode, if no error was registered, display some trace information
                // DevDiv 89485: throw, don't alert()
                throw Sys.Net.WebServiceProxy._createFailedError(null, msg);
            }
        }
    }

    return request;
}