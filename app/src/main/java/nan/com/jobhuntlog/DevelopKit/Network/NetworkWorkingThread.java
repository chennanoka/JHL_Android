package nan.com.jobhuntlog.DevelopKit.Network;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by NAN on 2016-10-22.
 */
public class NetworkWorkingThread  implements Runnable {
    public INetworkResponseListener nn_listener;
    public WebRequestObject nn_requestobj;
    public NetworkManager nn_manager;
    //flag for special network call.
    public enum WebCallType{
        Regular,
        Image
    }
    public WebCallType nn_webcalltype;

    public NetworkWorkingThread(NetworkManager manager,INetworkResponseListener listener,WebRequestObject requestobj){
        this(manager,listener,requestobj,WebCallType.Regular);
    }
    public NetworkWorkingThread(NetworkManager manager,INetworkResponseListener listener,WebRequestObject requestobj,NetworkWorkingThread.WebCallType type){
        nn_listener=listener;
        nn_requestobj=requestobj;
        nn_webcalltype=type;
        nn_manager=manager;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +" start ");
        String jsonstr="";
        WebResponseObject tap5050response = new WebResponseObject (nn_requestobj);
        HttpURLConnection connection = null;
        try{
            if(nn_webcalltype==WebCallType.Regular) {
                if(nn_requestobj.verb==NetworkManager.HTTPVerb.GET) {
                    String param = "";
                    for (HashMap.Entry<String,String> pair : nn_requestobj.parameters.entrySet()){
                        if (param.equals("")) {
                            param += "?"+ Uri.encode(pair.getKey()) + "=" + Uri.encode(pair.getValue());
                        }else{
                            param += "&" +Uri.encode(pair.getKey()) + "=" + Uri.encode(pair.getValue());
                        }
                    }
                    URL url = new URL(nn_requestobj.baseurl+param);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    //connection.setRequestProperty("Content-length", "0");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setUseCaches(false);
                    connection.setAllowUserInteraction(false);
                    connection.setConnectTimeout(nn_manager.nn_timeout);
                    connection.setReadTimeout(nn_manager.nn_timeout);
                    connection.connect();
                    int status = connection.getResponseCode();
                    switch (status) {
                        case 200:
                        case 201:
                            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder sb = new StringBuilder();
                            for (String line = br.readLine(); line != null; line = br.readLine()) {
                                sb.append(line + "\n");
                            }
                            br.close();
                            tap5050response.responsestr=sb.toString();
                            tap5050response.TryParse();
                            tap5050response.available=true;
                            connection.disconnect();
                            break;
                        default:
                            connection.disconnect();
                            throw new Exception("Network Error");
                    }
                }else if(nn_requestobj.verb==NetworkManager.HTTPVerb.POST){

                }
            }

        }catch(Exception e){
            Log.v("NetworkWorkingThread","exception");
            //no mater network error or parse error set false
            tap5050response.available=false;
        }finally{

            //always fire call back
            if(nn_listener!=null){
                nn_listener.OnResponseReady(tap5050response);
            }

        }

    }
}
