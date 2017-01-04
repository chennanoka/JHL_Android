package nan.com.jobhuntlog.Application;

import nan.com.jobhuntlog.Constant.Constant;
import nan.com.jobhuntlog.DevelopKit.App.NNApp;
import nan.com.jobhuntlog.Database.DatabaseManager;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-07.
 */
public class App extends NNApp {

    public static Util util;
    public static DatabaseManager databaseManager;
    public static App INSTANCE;
    public static Constant constant;
    @Override
    public void onCreate(){
        super.onCreate();
        INSTANCE=this;
        util=new Util(getApplicationContext());
        databaseManager=new DatabaseManager(getApplicationContext(),Util.getVersionCode());
        constant=new Constant();
    }
    public static App getInstance(){
        return INSTANCE;
    }
    public static Constant getConstant(){
        return constant;
    }
    public static Util getUtil(){
        return util;
    }
    public static DatabaseManager getDataBaseManager(){
        return databaseManager;
    }
}
