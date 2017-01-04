package nan.com.jobhuntlog.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import nan.com.jobhuntlog.Cards.HistoryListCard;
import nan.com.jobhuntlog.Cards.PositionListFillerCard;
import nan.com.jobhuntlog.Constant.ThreadCondition;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragmentActivity;
import nan.com.jobhuntlog.Objects.HistoryObject;

/**
 * Created by NAN on 2016-09-01.
 */
public class HistoryListAdapter extends BaseAdapter {
    public NNFragmentActivity nn_fragmentactivity;
    public NNFragment nn_fragment;
    public HistoryObject fillerobj;
    private ArrayList<HistoryObject> historyObjectList = new ArrayList<HistoryObject>();


    public HistoryListAdapter(NNFragmentActivity fragmentActivity, NNFragment fragment){
        nn_fragmentactivity=fragmentActivity;
        nn_fragment=fragment;
        fillerobj=new HistoryObject();
    }

    public void addandupdatelist(ArrayList<HistoryObject> historys){
        historyObjectList.addAll(historys);
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }
    public void addRefreshRow(){
        historyObjectList.add(fillerobj);
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }
    public void deleteRefreshRow(){
        historyObjectList.remove(fillerobj);
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }
    public void clearList(){
        historyObjectList.clear();
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return historyObjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyObjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(!historyObjectList.get(position).getIsFiller()){
            HistoryListCard historycard = new HistoryListCard(nn_fragmentactivity,historyObjectList.get(position));
            return historycard;
        }else{
            PositionListFillerCard fillerCard;
            fillerCard =new PositionListFillerCard(nn_fragmentactivity);
            return fillerCard;
        }
    }
}
