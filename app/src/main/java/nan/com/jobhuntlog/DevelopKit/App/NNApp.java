package nan.com.jobhuntlog.DevelopKit.App;

import android.app.Application;

import java.util.HashMap;

import nan.com.jobhuntlog.DevelopKit.Network.INetworkResponseListener;
import nan.com.jobhuntlog.DevelopKit.Network.NetworkManager;
import nan.com.jobhuntlog.DevelopKit.Util.NNUtil;

/**
 * Created by NAN on 2016-10-22.
 */
public class NNApp extends Application {
    private static NNApp nnapp;
    public static NetworkManager networknamager;
    //public static DatabaseManager databasemanager;
    public static NNApp getInstance() {
        return nnapp;
    }
    public static NNUtil util;
    @Override
    public void onCreate(){
        super.onCreate();
        networknamager = new NetworkManager(2, 1000 * 10);
        util=new NNUtil(this.getApplicationContext());
    }

    public static void networkReqeust(INetworkResponseListener responselistener, HashMap<String,String> parameters, String url, NetworkManager.HTTPVerb verb, boolean opencache){
        networknamager.request(responselistener, parameters, url, verb, opencache);
    }
    public static void networkReqeust(INetworkResponseListener responselistener, HashMap<String,String> parameters, String url, NetworkManager.HTTPVerb verb){
        networknamager.request(responselistener, parameters, url, verb);
    }
}