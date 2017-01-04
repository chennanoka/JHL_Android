package nan.com.jobhuntlog.Interface;

import java.util.ArrayList;

import nan.com.jobhuntlog.Objects.HistoryObject;

/**
 * Created by NAN on 2016-09-01.
 */
public interface IHistoryTableListener {
    public void onGetQueryListResult(boolean isavailable,ArrayList<HistoryObject> object);
}
