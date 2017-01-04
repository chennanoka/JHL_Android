package nan.com.jobhuntlog.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nan.com.jobhuntlog.Cards.DrawerOptionCard;
import nan.com.jobhuntlog.Constant.ThreadCondition;
import nan.com.jobhuntlog.Objects.DrawerOptionsItem;
import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.R;

/**
 * Created by CHENN on 30/10/2014.
 */
public class DrawerOpetionsAdapter extends BaseAdapter {


    private LayoutInflater nn_inflater;
    private ArrayList<DrawerOptionsItem> nn_data=new ArrayList<DrawerOptionsItem>();
    private Activity nn_activity;

    public DrawerOpetionsAdapter(Activity currentactivity, ArrayList<DrawerOptionsItem> mData) {
        nn_inflater = LayoutInflater.from(currentactivity);
        nn_data =mData;
        nn_activity=currentactivity;
    }

    public void addandupdatelist(ArrayList<DrawerOptionsItem> items){
        nn_data=items;
        ThreadCondition.checkOnMainThread();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return nn_data.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            //We must create a View:
            convertView=nn_data.get(position).view;
        }
        return convertView;
    }



}
