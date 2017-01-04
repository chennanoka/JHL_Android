package nan.com.jobhuntlog.DevelopKit.Network;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by NAN on 2016-10-22.
 */
public class NetworkManager {
    /**
     * Verbs for HTTP request
     */
    public enum HTTPVerb {
        GET,
        POST,
        PUT,
        DELETE
    }

    public static long nn_cachetime;


    //only cache GET returns
    private HashMap<String, WebResponseObject> nn_cachMap;

    //connection time out in millisecond
    public int nn_timeout;

    public ExecutorService executor;

    public NetworkManager(int threadpoolsize, int connectiontimeout) {
        executor = (ExecutorService) Executors.newFixedThreadPool(threadpoolsize);
        nn_timeout = connectiontimeout;

    }

    public NetworkManager(int threadpoolsize) {
        this(threadpoolsize, 10000);
    }

    public NetworkManager() {
        this(1);
    }


    /**
     * Start to reqeust a specific url
     *
     * @param responselistener the call back listener
     * @param parameters       hashmap for HTTP request parameters
     * @param url              request url
     * @param verb             4 verbs
     * @param opencache        the flag indicate whether to use cache
     */
    public void request(INetworkResponseListener responselistener, HashMap<String, String> parameters, String url, HTTPVerb verb, boolean opencache) {
        // try to return cache
        if (parameters.containsKey(url) && verb.equals(HTTPVerb.GET) && opencache && !(nn_cachMap.get(url)).HasTimeOut(new Date().getTime())) {
            if (responselistener != null) {
                responselistener.OnResponseReady(nn_cachMap.get(url));
                return;
            }
        }
        //create a new thread to retrieve response
        //return new WebResponseObject();
        //(HashMap parameters,NetworkManager.HTTPVerb verb,long timestamp
        WebRequestObject reqeust = new WebRequestObject(parameters, verb, url, nn_timeout);
        NetworkWorkingThread networkWorkingThread = new NetworkWorkingThread(this, responselistener, reqeust);
        executor.execute(networkWorkingThread);
    }


    public void request(INetworkResponseListener responselistener, HashMap<String, String> parameters, String url, HTTPVerb verb) {
        WebRequestObject reqeust = new WebRequestObject(parameters, verb, url, nn_timeout);
        NetworkWorkingThread networkWorkingThread = new NetworkWorkingThread(this, responselistener, reqeust);
        executor.execute(networkWorkingThread);
    }

    public void request(HashMap parameters, String url, HTTPVerb verb) {
        //    request(parameters, url, verb,false);
    }

    public void setCacheTime(long cachetime) {
        nn_cachetime = cachetime;
    }
}