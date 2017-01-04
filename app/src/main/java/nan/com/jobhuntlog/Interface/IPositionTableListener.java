package nan.com.jobhuntlog.Interface;

import java.util.ArrayList;

import nan.com.jobhuntlog.Objects.PositionObject;

/**
 * Created by NAN on 2016-08-28.
 */
public interface IPositionTableListener {
    public void onGetQueryListResult(boolean isavailable,ArrayList<PositionObject> object);
}
