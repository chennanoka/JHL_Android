package nan.com.jobhuntlog.DevelopKit.Network;

import java.util.HashMap;

/**
 * Created by NAN on 2016-10-22.
 */
public class WebRequestObject {
    public String baseurl;
    public HashMap<String, String> parameters;
    public NetworkManager.HTTPVerb verb;
    public long timeoutstamp;

    public WebRequestObject(HashMap parameters, NetworkManager.HTTPVerb verb, String url, long timestamp) {
        this.parameters = parameters;
        this.timeoutstamp = timestamp;
        this.verb = verb;
        this.baseurl = url;
    }

    public WebRequestObject(long timestamp) {
        parameters = new HashMap();
        timeoutstamp = timestamp;
        this.verb = NetworkManager.HTTPVerb.GET;
    }
}
