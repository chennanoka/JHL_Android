package nan.com.jobhuntlog.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.Objects.PositionObject;

/**
 * Created by NAN on 2016-08-13.
 */
public class DatabaseManager {
    public DatabaseHelper databaseHelper;
    public String tablename;
    public int databaseversion;

    public static final String DATABASE_NAME="jobhuntlog.db";
    public static final String POSITIONTABLE_NAME="eventtable";
    public static final String HISTORYTABLE_NAME="historytable";


    public static final String POSITION_COLUMN_ID = "id";
    public static final String POSITION_COLUMN_TITLE = "title";
    public static final String POSITION_COLUMN_LOCATION="location";
    public static final String POSITION_COLUMN_CREATETIME="createtime";
    public static final String POSITION_COLUMN_EVENTDATE="date";
    public static final String POSITION_COLUMN_EVENTTIME="time";
    public static final String POSITION_COLUMN_EVENTNOTE="note";
    public static final String POSITION_COLUMN_STAGE="stage";

    public static final String HISTORY_COLUMN_ID="id";
    public static final String HISTORY_COLUMN_TITLE="title";
    public static final String HISTORY_ACTION="action";
    public static final String HISTORY_DATE="date";



    public static final String CREATE_POSITION_TABLE="create table "
            +POSITIONTABLE_NAME+"("+POSITION_COLUMN_ID+" integer primary key autoincrement, "
            +POSITION_COLUMN_TITLE+" text not null, "
            +POSITION_COLUMN_LOCATION+" text not null, "
            +POSITION_COLUMN_CREATETIME+" text not null, "
            +POSITION_COLUMN_EVENTDATE+" text not null, "
            +POSITION_COLUMN_EVENTTIME+" text not null, "
            +POSITION_COLUMN_EVENTNOTE+" text not null, "
            +POSITION_COLUMN_STAGE+" text not null"
            +");";

    public static final String CREATE_HISTORY_TABLE="create table "
            +HISTORYTABLE_NAME+"("+HISTORY_COLUMN_ID+" integer primary key autoincrement, "
            +HISTORY_COLUMN_TITLE+" integer not null, "
            +HISTORY_ACTION+" text not null, "
            +HISTORY_DATE+" text not null "
            +");";



    public DatabaseManager(Context context,int databaseversion){
        this.databaseversion= databaseversion;
        databaseHelper = new DatabaseHelper(context,databaseversion);

    }

    /**
     *  Support actions for position table
     *
     */
    public boolean createPosition(PositionObject position){

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(POSITION_COLUMN_TITLE,position.getTitle());
        contentValues.put(POSITION_COLUMN_LOCATION, position.getLocation());
        contentValues.put(POSITION_COLUMN_CREATETIME, position.getCreateTime());
        contentValues.put(POSITION_COLUMN_EVENTDATE, position.getEventDate());
        contentValues.put(POSITION_COLUMN_EVENTTIME, position.getEventTime());
        contentValues.put(POSITION_COLUMN_EVENTNOTE, position.getEventNote());
        contentValues.put(POSITION_COLUMN_STAGE, position.getStage());
        long insertreturn=db.insert(POSITIONTABLE_NAME, null, contentValues);
        if(insertreturn==-1){
            return false;
        }else{
            return true;
        }

    }
    public boolean updatePosition(PositionObject position){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(POSITION_COLUMN_TITLE,position.getTitle());
        contentValues.put(POSITION_COLUMN_LOCATION, position.getLocation());
        //contentValues.put(POSITION_COLUMN_CREATETIME, position.getCreateTime());
        contentValues.put(POSITION_COLUMN_EVENTDATE, position.getEventDate());
        contentValues.put(POSITION_COLUMN_EVENTTIME, position.getEventTime());
        contentValues.put(POSITION_COLUMN_EVENTNOTE, position.getEventNote());
        contentValues.put(POSITION_COLUMN_STAGE, position.getStage());
        int updatereturn=db.update(POSITIONTABLE_NAME,contentValues,"id = ? ",new String[]{Long.toString(position.getID())});
        if(updatereturn<=0){
            return false;
        }else{
            return true;
        }
    }
    public boolean deletePosition(PositionObject position){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if(db.delete(POSITIONTABLE_NAME,"id = ? ",new String[] { Long.toString(position.getID()) })!=0){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<PositionObject> getPositionList(int offset){
        ArrayList<PositionObject> array_list = new ArrayList<PositionObject>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+POSITIONTABLE_NAME+" order by id desc limit 10 offset "+offset, null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            PositionObject positionObject=new PositionObject(
                    res.getLong(res.getColumnIndex(POSITION_COLUMN_ID)),
                    res.getString(res.getColumnIndex(POSITION_COLUMN_TITLE)),
                    res.getString(res.getColumnIndex(POSITION_COLUMN_LOCATION)),
                    res.getString(res.getColumnIndex(POSITION_COLUMN_CREATETIME)),
                    res.getString(res.getColumnIndex(POSITION_COLUMN_EVENTDATE)),
                    res.getString(res.getColumnIndex(POSITION_COLUMN_EVENTTIME)),
                    res.getString(res.getColumnIndex(POSITION_COLUMN_EVENTNOTE)),
                    res.getString(res.getColumnIndex(POSITION_COLUMN_STAGE))
            );
            array_list.add(positionObject);
            res.moveToNext();
        }
        return array_list;
    }

    /**
     *  Support actions for history table
     *
     */
    public boolean createHistory(HistoryObject history){

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HISTORY_COLUMN_TITLE,history.getPositionTitle());
        contentValues.put(HISTORY_ACTION, history.getAction());
        contentValues.put(HISTORY_DATE, history.getDate());
        long insertreturn=db.insert(HISTORYTABLE_NAME, null, contentValues);
        if(insertreturn==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean updateHistory(HistoryObject history){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HISTORY_COLUMN_TITLE,history.getPositionTitle());
        contentValues.put(HISTORY_ACTION, history.getAction());
        contentValues.put(HISTORY_DATE, history.getDate());
        int updatereturn=db.update(HISTORYTABLE_NAME,contentValues,"id = ? ",new String[]{Long.toString(history.getID())});
        if(updatereturn<=0){
            return false;
        }else{
            return true;
        }

    }
    public int deleteHistory(HistoryObject history){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(HISTORYTABLE_NAME,"id = ? ",new String[] { Long.toString(history.getID()) });
    }
    public ArrayList<HistoryObject> getHistoryList(){
        ArrayList<HistoryObject> array_list = new ArrayList<HistoryObject>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+HISTORYTABLE_NAME+" order by id desc", null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            HistoryObject positionObject=new HistoryObject(
                    res.getLong(res.getColumnIndex(HISTORY_COLUMN_ID)),
                    res.getString(res.getColumnIndex(HISTORY_COLUMN_TITLE)),
                    res.getString(res.getColumnIndex(HISTORY_ACTION)),
                    res.getString(res.getColumnIndex(HISTORY_DATE))
            );
            array_list.add(positionObject);
            res.moveToNext();
        }
        return array_list;
    }

}

