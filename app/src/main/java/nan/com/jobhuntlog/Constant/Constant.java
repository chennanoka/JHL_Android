package nan.com.jobhuntlog.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NAN on 2016-08-19.
 */
//public static final int STAGE_READY=0;
//public static final int STAGE_SENT=1;
//public static final int STAGE_INTERVIEW=2;
//public static final int STAGE_DONE=3;
//public static final int STAGE_GOTCHA=4;
public class Constant {
    //the flag weather logedin or not
    public static boolean haslogin=false;
    public static ArrayList<String> stagelist;

    /**
     * Dialog tag string
     */
    public static final String TAG_HOME_DIALOG_CREATE="TAG_HOME_DIALOG_CREATE";
    public static final String TAG_HOME_DIALOG_DELETE="TAG_HOME_DIALOG_DELETE";

    /**
     * Dialog button tag
     */
    public static final String TAG_HOME_DIALOG_CREATE_POSITIVE="TAG_HOME_DIALOG_CREATE_POSITIVE";
    public static final String TAG_HOME_DIALOG_CREATE_NEGATIVE="TAG_HOME_DIALOG_CREATE_NEGATIVE";


    /**
     * History action
     */
    public enum HistoryAction {
        CREATE,UPDATE,DELETE
    }

    public Constant(){
        stagelist=new ArrayList<String>();
        stagelist.add("Sent");
        stagelist.add("Interview");
        stagelist.add("Done");
        stagelist.add("Gotcha");
    }
}
