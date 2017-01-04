package nan.com.jobhuntlog.Fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import nan.com.jobhuntlog.Adapter.HistoryListAdapter;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.Interface.IHistoryImgListener;
import nan.com.jobhuntlog.Interface.IHistoryTableListener;
import nan.com.jobhuntlog.MainActivity;
import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Thread.GetHistoryImgThread;
import nan.com.jobhuntlog.Thread.RetrieveHistoryListThread;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-29.
 */
public class HistoryFragment extends NNFragment implements IHistoryTableListener,IHistoryImgListener {
    MainActivity nn_activity;
    RelativeLayout rootview;
    ListView hisotrylistView;
    HistoryListAdapter adapter;
    public ExecutorService executor;
    public volatile boolean flag_loading=false;

    public static String TEMPIMGPATH="temphistoryimg.png";
    FloatingActionButton floateeditbut;
    @Override
    public void onAttach (Context context)
    {
        super.onAttach (context);

        if (context instanceof MainActivity){
            nn_activity=(MainActivity)context;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executor = (ExecutorService) Executors.newFixedThreadPool(1);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview=new RelativeLayout(nn_activity);
        rootview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));



        floateeditbut=new FloatingActionButton(nn_activity);
        RelativeLayout.LayoutParams floateidtparam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        floateidtparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        floateidtparam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        floateidtparam.setMargins(0,0, Util.dptopx(15),Util.dptopx(15));
        floateeditbut.setLayoutParams(floateidtparam);
        floateeditbut.setImageResource(R.mipmap.ic_share_white_48dp);
        floateeditbut.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(nn_activity, R.color.indigo_500)));
        floateeditbut.setRippleColor(ContextCompat.getColor(nn_activity, R.color.indigo_400));
        floateeditbut.setClickable(true);
        floateeditbut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!flag_loading) {
                    executor.execute(new GetHistoryImgThread(nn_activity, HistoryFragment.this, hisotrylistView));
                }

                flag_loading=true;
            }
        });


        //create listview
        hisotrylistView =new ListView(nn_activity);
        RelativeLayout.LayoutParams listviewparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        hisotrylistView.setLayoutParams(listviewparam);

        adapter=new HistoryListAdapter(nn_activity,this);
        hisotrylistView.setAdapter(adapter);

        hisotrylistView.setDivider(null);
        hisotrylistView.setDividerHeight(0);

        flag_loading=true;
        adapter.addRefreshRow();
        executor.execute(new RetrieveHistoryListThread(this));

        rootview.addView(hisotrylistView);
        rootview.addView(floateeditbut);
        return rootview;
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onGetQueryListResult(boolean isavailable, ArrayList<HistoryObject> object) {
        final ArrayList<HistoryObject> theobjects=object;
        if(isavailable) {
            nn_activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.deleteRefreshRow();
                    adapter.addandupdatelist(theobjects);
                }
            });
        }
        flag_loading=false;
    }

    @Override
    public void onConvertImgResult(Boolean isavailable) {
        flag_loading=false;
        if(isavailable){
            nn_activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(nn_activity,"Success convert img",Toast.LENGTH_LONG).show();
            }
        });
        }else{
            nn_activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(nn_activity,"Failed convert img",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
