package nan.com.jobhuntlog.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import nan.com.jobhuntlog.Cards.PositionListCard;
import nan.com.jobhuntlog.Cards.PositionListFillerCard;
import nan.com.jobhuntlog.Constant.ThreadCondition;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragmentActivity;
import nan.com.jobhuntlog.Objects.PositionObject;

/**
 * Created by NAN on 2016-08-25.
 */
public class PositionListAdapter extends BaseAdapter {

    public NNFragmentActivity nn_fragmentactivity;
    public NNFragment nn_fragment;
    public PositionObject fillerobj;
    private ArrayList<PositionObject> positionObjectList = new ArrayList<PositionObject>();

    public PositionListAdapter(NNFragmentActivity fragmentActivity, NNFragment fragment){
        nn_fragmentactivity=fragmentActivity;
        nn_fragment=fragment;
        fillerobj=new PositionObject();
    }

    //when scroll down
    public void addandupdatelist(ArrayList<PositionObject> positionObjectArrayList){
        positionObjectList.addAll(positionObjectArrayList);
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }

    public void addRefreshRow(){
        positionObjectList.add(fillerobj);
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }
    public void deleteRefreshRow(){
        positionObjectList.remove(fillerobj);
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }
    public void clearList(){
        positionObjectList.clear();
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getCount() {
        return positionObjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return positionObjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(!positionObjectList.get(position).isFiller()){
            PositionListCard positioncard;
            positioncard =new PositionListCard(nn_fragmentactivity,nn_fragment,positionObjectList.get(position));
            return positioncard;
        }else{
            PositionListFillerCard fillerCard;
            fillerCard =new PositionListFillerCard(nn_fragmentactivity);
            return fillerCard;
        }
    }
}
