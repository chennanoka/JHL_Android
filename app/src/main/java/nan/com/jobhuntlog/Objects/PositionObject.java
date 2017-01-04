package nan.com.jobhuntlog.Objects;

/**
 * Created by NAN on 2016-08-13.
 */
//public static final String POSITION_COLUMN_ID = "id";
//public static final String POSITION_COLUMN_TITLE = "title";
//public static final String POSITION_COLUMN_LOCATION="location";
//public static final String POSITION_COLUMN_CREATETIME="createtime";
//public static final String POSITION_COLUMN_EVENTDATE="date";
//public static final String POSITION_COLUMN_EVENTTIME="time";
//public static final String POSITION_COLUMN_EVENTNOTE="note";
public class PositionObject {
    private long id;
    private String title;
    private String location;
    private String createtime;
    private String eventdate;
    private String eventtime;
    private String eventnote;
    private String stage;
    private boolean isfiller;
    public PositionObject(){
        isfiller=true;
    }
    public PositionObject(long id,String title,String location,String createtime,String date,String time,String note,String stage){
        this.id=id;
        this.title=title;
        this.location=location;
        this.createtime=createtime;
        this.eventdate=date;
        this.eventtime=time;
        this.eventnote=note;
        this.stage=stage;
        this.isfiller=false;
    }
    public PositionObject(String title,String location,String createtime,String date,String time,String note,String stage){
        this.title=title;
        this.location=location;
        this.createtime=createtime;
        this.eventdate=date;
        this.eventtime=time;
        this.eventnote=note;
        this.stage=stage;
        this.isfiller=false;
    }

    public boolean isFiller() {
        return isfiller;
    }
    public long getID() {
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public String getCreateTime(){
        return createtime;
    }
    public void setCreateTime(String createtime){
        this.createtime=createtime;
    }
    public String getEventDate(){
        return eventdate;
    }
    public void setEventDate(String date){
        this.eventdate=date;
    }
    public String getEventTime(){
        return eventtime;
    }
    public void setEventTime(String time){
        this.eventtime=time;
    }
    public String getEventNote(){
        return eventnote;
    }
    public void setEventNote(String note){
        this.eventnote=note;
    }
    public String getStage(){
        return stage;
    }
    public void setStage(String stage){
        this.stage=stage;
    }
}