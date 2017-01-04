package nan.com.jobhuntlog.DevelopKit.Network;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by NAN on 2016-10-22.
 */
public class WebResponseObject {
    public String baseurl;
    public Boolean available;
    public Object parsedobject;
    public Exception exception;
    public Object flagobject;
    public String responsestr;
    public WebRequestObject webrequest;
    public long timeoutstamp;
    public long createtime;


    public WebResponseObject(WebRequestObject request){
        webrequest=request;
        baseurl = webrequest.baseurl;
        createtime=new Date().getTime();
    }
    public WebResponseObject(){
        available=false;
    }

    /**
     * The function used to cacluate whether the
     */
    public boolean HasTimeOut(long currenttimestamp){
        if(currenttimestamp-createtime<timeoutstamp){
            return false;
        }else{
            return true;
        }
    }
    public void SetCreateTime(){
        createtime=new Date().getTime();
    }

    public void TryParse() {
        Gson goson = new Gson();
        if (responsestr != null && webrequest != null) {
            if(baseurl.equals (URLManager.getTestGet()) && webrequest.verb.equals(NetworkManager.HTTPVerb.GET)){
                TestResponseObject obj = goson.fromJson (responsestr, TestResponseObject.class);
                parsedobject=obj;
            }
            else if(baseurl.equals(URLManager.getTestPost()) && webrequest.verb.equals(NetworkManager.HTTPVerb.GET)){
                TestResponseObject obj = goson.fromJson (responsestr, TestResponseObject.class);
                parsedobject=obj;
            }
            else if(baseurl.equals (URLManager.getTestPut()) && webrequest.verb.equals(NetworkManager.HTTPVerb.GET)){
                TestResponseObject obj = goson.fromJson (responsestr, TestResponseObject.class);
                parsedobject=obj;
            }
            else if(baseurl.equals (URLManager.getTestDelete()) && webrequest.verb.equals(NetworkManager.HTTPVerb.GET)){
                TestResponseObject obj = goson.fromJson (responsestr, TestResponseObject.class);
                parsedobject=obj;
            }
        }else{
            available=false;
        }
    }

}
