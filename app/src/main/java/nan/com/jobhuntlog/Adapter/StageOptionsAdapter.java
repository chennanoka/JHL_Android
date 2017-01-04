package nan.com.jobhuntlog.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-22.
 */
public class StageOptionsAdapter extends BaseAdapter implements SpinnerAdapter{
    private ArrayList<String> nn_list;
    public Context nn_context;

    public StageOptionsAdapter(Context context,ArrayList<String> list){
        nn_list=new ArrayList<String>();
        nn_list=list;
        nn_context=context;
    }

    @Override
    public int getCount() {
        return nn_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        public RelativeLayout contentView;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=new RelativeLayout(nn_context);
            AbsListView.LayoutParams param=new AbsListView.LayoutParams (AbsListView.LayoutParams.MATCH_PARENT, Util.dptopx(45));
            convertView.setLayoutParams(param);
            TextView textView =new TextView(nn_context);
            ((RelativeLayout)convertView).addView(textView);
            RelativeLayout.LayoutParams textparam=new RelativeLayout.LayoutParams (AbsListView.LayoutParams.MATCH_PARENT, Util.dptopx(30));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textparam.setMargins(Util.dptopx(5),0,0,0);
            textparam.addRule(RelativeLayout.CENTER_VERTICAL);
            textView.setLayoutParams(textparam);
            holder =new ViewHolder();
            holder.contentView=((RelativeLayout)convertView);
            holder.textView=textView;
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(nn_list.get(position));

        return holder.contentView;
    }
}
