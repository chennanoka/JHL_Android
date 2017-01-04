package nan.com.jobhuntlog.DevelopKit.Network;

/**
 * Created by NAN on 2016-10-22.
 */
public class URLManager {
    public static String nn_baseurl="http://chennan.me/";
    private static final String nn_testpost="post.php";
    private static final String nn_testget="get.php";
    private static final String nn_testput="put.php";
    private static final String nn_testdelete="delete.php";


    public static String getTestPost(){
        return nn_baseurl+nn_testpost;
    }
    public static String getTestGet(){
        return nn_baseurl+nn_testget;
    }
    public static String getTestPut(){
        return nn_baseurl+nn_testput;
    }
    public static String getTestDelete(){
        return nn_baseurl+nn_testdelete;
    }

}
