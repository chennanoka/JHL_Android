package nan.com.jobhuntlog.Thread;


import java.util.ArrayList;

import nan.com.jobhuntlog.Application.App;
import nan.com.jobhuntlog.Interface.IHistoryTableListener;
import nan.com.jobhuntlog.Objects.HistoryObject;

/**
 * Created by NAN on 2016-09-01.
 */
public class RetrieveHistoryListThread implements Runnable{
    private boolean isavailable=false;
    private IHistoryTableListener nn_listener;

    public RetrieveHistoryListThread(IHistoryTableListener listener) {
        nn_listener=listener;
    }

    @Override
    public void run(){
        ArrayList<HistoryObject> arraylist = new ArrayList<HistoryObject>();
        try{
            arraylist = App.getDataBaseManager().getHistoryList();
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
