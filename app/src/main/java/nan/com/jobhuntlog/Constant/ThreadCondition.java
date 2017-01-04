package nan.com.jobhuntlog.Constant;

import android.os.Looper;

import nan.com.jobhuntlog.BuildConfig;

/**
 * Created by NAN on 2016-08-27.
 */
public class ThreadCondition {
    public static void checkOnMainThread(){
        if(BuildConfig.DEBUG){
            if(Thread.currentThread()!= Looper.getMainLooper().getThread()){
                throw new IllegalStateException("Other threads try to update UI");
            }
        }
    }
}
