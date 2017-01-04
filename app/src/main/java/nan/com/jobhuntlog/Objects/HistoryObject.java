package nan.com.jobhuntlog.Objects;

/**
 * Created by NAN on 2016-08-13.
 */
//public static final String HISTORY_COLUMN_ID="id";
//public static final String HISTORY_COLUMN_POSITIONID="positionid";
//public static final String HISTORY_ACTION="action";
//public static final String HISTORY_DATE="date";
public class HistoryObject {
    private long id;
    private String positiontitle;
    private String action;
    private String date;
    private boolean isfiller;
    public HistoryObject(){
        isfiller=true;
    }
    public HistoryObject(String positiontitle,String action,String date){
        this.positiontitle=positiontitle;
        this.action=action;
        this.date=date;
    }
    public HistoryObject(long id,String positiontitle,String action,String date){
        this.id=id;
        this.positiontitle=positiontitle;
        this.action=action;
        this.date=date;
    }
    public boolean getIsFiller() {
        return isfiller;
    }
    public long getID() {
        return id;
    }
    public String getPositionTitle(){
        return positiontitle;
    }
    public String getAction(){
        return action;
    }
    public void setAction(String action){
        this.action=action;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }

}