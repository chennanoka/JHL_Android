package nan.com.jobhuntlog.Fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import nan.com.jobhuntlog.Adapter.PositionListAdapter;
import nan.com.jobhuntlog.Adapter.RecycleAdapter;
import nan.com.jobhuntlog.Cards.PositionCreateCard;
import nan.com.jobhuntlog.Constant.Constant;
import nan.com.jobhuntlog.DevelopKit.UI.NNDividerItemDecoration;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.Interface.IListviewClickListener;
import nan.com.jobhuntlog.Interface.IPositionTableListener;
import nan.com.jobhuntlog.MainActivity;
import nan.com.jobhuntlog.Objects.PositionObject;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Thread.RetrievePositionListThread;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-07.
 */

public class HomeFragment extends NNFragment implements IPositionTableListener,IListviewClickListener {
    MainActivity nn_activity;
    RelativeLayout rootview;
    FloatingActionButton floateeditbut;
    PositionCreateCard positionCreateCard;
    //ListView positionlistView;
    //PositionListAdapter adapter;
    RetrievePositionListThread retrievePositionListThread;

    RecyclerView recycleview;
    RecycleAdapter adapter;
    LinearLayoutManager mLayoutManager;

    public ExecutorService executor;
    public static int offset;
    private int preLast;
    public volatile boolean flag_loading=false;
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
        offset=0;
        executor = (ExecutorService) Executors.newFixedThreadPool(1);
        retrievePositionListThread=new RetrievePositionListThread(this,offset);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview=new RelativeLayout(nn_activity);
        rootview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        floateeditbut=new FloatingActionButton(nn_activity);
        RelativeLayout.LayoutParams floateidtparam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        floateidtparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        floateidtparam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        floateidtparam.setMargins(0,0,Util.dptopx(15),Util.dptopx(15));
        floateeditbut.setLayoutParams(floateidtparam);
        floateeditbut.setImageResource(R.mipmap.ic_add_white_48dp);
        floateeditbut.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(nn_activity, R.color.indigo_500)));
        floateeditbut.setRippleColor(ContextCompat.getColor(nn_activity, R.color.indigo_400));
        floateeditbut.setClickable(true);
        floateeditbut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!isDialogTopShowing(Constant.TAG_HOME_DIALOG_CREATE)){
                    positionCreateCard =new PositionCreateCard(nn_activity,HomeFragment.this, PositionCreateCard.NEW);
                    showDialog(positionCreateCard,Constant.TAG_HOME_DIALOG_CREATE);
                }
            }
        });


        recycleview =new RecyclerView(nn_activity);
        RelativeLayout.LayoutParams listviewparam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        recycleview.setLayoutParams(listviewparam);

        adapter=new RecycleAdapter(nn_activity,this);
        recycleview.setAdapter(adapter);

        mLayoutManager = new LinearLayoutManager(nn_activity);
        recycleview.setLayoutManager(mLayoutManager);

        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0) {
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                    final int lastItem = firstVisibleItem + visibleItemCount;
                    if (lastItem == totalItemCount && totalItemCount % 10 == 0) {
                        if (!flag_loading) {
                            flag_loading = true;
                            if (preLast != lastItem) {
                                preLast = lastItem;
                                offset += 10;
                                //add loading page
                                adapter.addRefreshRow();
                                executor.execute(new RetrievePositionListThread(HomeFragment.this, offset));
                            }
                        }
                    }
                }
            }
        });
        recycleview.addItemDecoration(new NNDividerItemDecoration(getActivity(),null));
        //recycleview.setItemAnimator(new DefaultItemAnimator());


//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
//        itemTouchHelper.attachToRecyclerView(recycleview);

        rootview.addView(floateeditbut);
        rootview.addView(recycleview);

        flag_loading=true;
        adapter.addRefreshRow();
        executor.execute(retrievePositionListThread);

        return rootview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated (savedInstanceState);
    }

    @Override
    public void onGetQueryListResult(boolean isavailable, ArrayList<PositionObject> objects) {
        final ArrayList<PositionObject> theobjects=objects;
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
    public void onItemClick(Object object) {

    }

    @Override
    public void onItemChildViewClick(Object object, int flag) {

    }
    @Override
    public void onRefreshAll(){
        flag_loading=true;
        offset=0;
        adapter.clearList();
        adapter.addRefreshRow();
        executor.execute(new RetrievePositionListThread(this,offset));
    }
}