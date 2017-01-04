package nan.com.jobhuntlog.Thread;

import java.util.ArrayList;

import nan.com.jobhuntlog.Application.App;
import nan.com.jobhuntlog.Constant.ThreadCondition;
import nan.com.jobhuntlog.Interface.IPositionTableListener;
import nan.com.jobhuntlog.Objects.PositionObject;

/**
 * Created by NAN on 2016-08-27.
 */
public class RetrievePositionListThread implements Runnable {
    private boolean isavailable=false;
    private IPositionTableListener nn_listener;
    private int nn_offset;

    public RetrievePositionListThread(IPositionTableListener listener, int offset) {
        nn_listener=listener;
        nn_offset=offset;
    }

    @Override
    public void run(){
        ArrayList<PositionObject> arraylist = new ArrayList<PositionObject>();
        try{
            Thread.sleep(1000);
            arraylist = App.getDataBaseManager().getPositionList(nn_offset);
            isavailable=true;
        }catch (Exception e){
            isavailable=false;
        }finally{
            if(nn_listener!=null){
                nn_listener.onGetQueryListResult(isavailable,arraylist);
            }
        }
    }
}