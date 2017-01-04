package nan.com.jobhuntlog.Adapter;

import android.content.Context;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import nan.com.jobhuntlog.Application.App;
import nan.com.jobhuntlog.Cards.PositionDeleteCard;
import nan.com.jobhuntlog.Cards.PositionListFillerCard;
import nan.com.jobhuntlog.Constant.Constant;
import nan.com.jobhuntlog.Constant.ThreadCondition;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragmentActivity;
import nan.com.jobhuntlog.Fragment.HomeFragment;
import nan.com.jobhuntlog.MainActivity;
import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.Objects.PositionObject;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-11-11.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<PositionObject> positionObjectList = new ArrayList<PositionObject>();
    //refresh indicate row
    public PositionObject fillerobj;
    public NNFragmentActivity mActivity;
    public NNFragment mFragment;
    public boolean[] isdraggedArray;
   // public MyViewControl control;
    public RecycleAdapter(NNFragmentActivity activity, NNFragment fragment){
        mActivity=activity;
        mFragment=fragment;
        fillerobj=new PositionObject();
       // control=new MyViewControl();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,location,date,note,stage;
        public Button titleavatar;
        public LinearLayout contentcontainer;
        public RelativeLayout editcontainer;
        public ImageButton edit;
        public ImageButton delete;
        public float dx=0;
        public MyViewHolder(final View view){
            super(view);
            title=(TextView)view.findViewById(R.id.positionlist_item_titleTextView);
            location=(TextView)view.findViewById(R.id.positionlist_item_locationTextView);
            date=(TextView)view.findViewById(R.id.positionlist_item_comingeventDateTextView);
            note=(TextView)view.findViewById(R.id.positionlist_item_comingeventNoteTextView);
            stage=(TextView)view.findViewById(R.id.positionlist_item_stageTextView);
            titleavatar=(Button)view.findViewById(R.id.positionlist_item_titleAvatar);
            contentcontainer=(LinearLayout)view.findViewById(R.id.positionlist_item_contentLinearLayout);
            editcontainer=(RelativeLayout)view.findViewById(R.id.positionlist_item_editRelativeLayout);
            edit=(ImageButton)view.findViewById(R.id.positionlist_item_editButton);
            edit.setOnClickListener(new MyEidtClick(this,mActivity));
            delete=(ImageButton)view.findViewById(R.id.positionlist_item_deleteButton);
            delete.setOnClickListener(new MyDeleteClick(this,mActivity,mFragment));
            contentcontainer.setOnTouchListener(new MyViewControl(this));
        }

    }
    public class MyViewHolderFiller extends RecyclerView.ViewHolder {
        public MyViewHolderFiller(View view){
            super(view);
        }

    }



    @Override
    public int getItemViewType(int position) {
        PositionObject opp = positionObjectList.get(position);
            if(opp.isFiller()){
                return 0;
            }else{
                return 1;
            }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(viewType==1){
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_position, parent, false));
        }else{
            return new MyViewHolderFiller(new PositionListFillerCard(mActivity));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PositionObject opp = positionObjectList.get(position);
        if(holder instanceof MyViewHolder){
            MyViewHolder viewholder=((MyViewHolder)holder);
            viewholder.title.setText(opp.getTitle());
            viewholder.location.setText(opp.getLocation());
            viewholder.date.setText(opp.getEventDate()+" "+opp.getEventTime());
            viewholder.note.setText(opp.getEventNote());
            viewholder.stage.setText(opp.getStage());
            viewholder.titleavatar.setText(opp.getTitle().substring(0,1));

            viewholder.titleavatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.animate().rotationYBy(360).setDuration(500).setInterpolator(new LinearInterpolator()).start();
                }
            });

            if(isdraggedArray[viewholder.getAdapterPosition()]){
                viewholder.contentcontainer.setX(-Util.dptopx(100));
            }else{
                viewholder.contentcontainer.setX(0);
            }
//            final RecyclerView.ViewHolder holderCopy = holder; // make a copy
//            ((MyViewHolder)holder).contentcontainer.setOnTouchListener(new MyViewControl());
            //viewholder.contentcontainer.setOnTouchListener(control);
           // viewholder.contentcontainer.setOnTouchListener(new MyViewControl());

    }

    }

    @Override
    public int getItemCount() {
        return positionObjectList.size();
    }
    //when scroll down
    public void addandupdatelist(ArrayList<PositionObject> positionObjectArrayList){
        ThreadCondition.checkOnMainThread();
        positionObjectList.addAll(positionObjectArrayList);
        isdraggedArray=new boolean[positionObjectList.size()];
        notifyDataSetChanged();
    }

    public void addRefreshRow(){
        ThreadCondition.checkOnMainThread();
        positionObjectList.add(fillerobj);
        isdraggedArray=new boolean[positionObjectList.size()];
        notifyDataSetChanged();
    }
    public void deleteRefreshRow(){
        ThreadCondition.checkOnMainThread();
        positionObjectList.remove(fillerobj);
        isdraggedArray=new boolean[positionObjectList.size()];
        notifyDataSetChanged();
    }
    public void clearList(){
        ThreadCondition.checkOnMainThread();
        positionObjectList.clear();
        isdraggedArray=new boolean[positionObjectList.size()];
        notifyDataSetChanged();
    }

    public void deleteItem(PositionObject positionObject){
        ThreadCondition.checkOnMainThread();
        positionObjectList.remove(positionObject);
        isdraggedArray=new boolean[positionObjectList.size()];
        notifyDataSetChanged();
    }


    class MyEidtClick implements View.OnClickListener{
        public RecyclerView.ViewHolder mHolder;
        public NNFragmentActivity mActivity;
        public MyEidtClick(RecyclerView.ViewHolder hodler,NNFragmentActivity activity){
            mHolder=hodler;
            mActivity=activity;
        }
        @Override
        public void onClick(View v) {
            ((MainActivity)mActivity).showOpportunityEditFragment(positionObjectList.get(mHolder.getAdapterPosition()));
        }
    }

    class MyDeleteClick implements View.OnClickListener{
        public RecyclerView.ViewHolder mHolder;
        public NNFragmentActivity mActivity;
        public NNFragment mFragment;
        public MyDeleteClick(RecyclerView.ViewHolder hodler,NNFragmentActivity activity,NNFragment fragment){
            mHolder=hodler;
            mActivity=activity;
            mFragment=fragment;
        }
        @Override
        public void onClick(View v) {
            final PositionObject nn_positionObject=positionObjectList.get(mHolder.getAdapterPosition());
            //Context context,String title, String message, String positive, String negative,OnClickListener positivelistener,OnClickListener negativelistener
            ((HomeFragment)mFragment).showDialog(new PositionDeleteCard(mActivity,mFragment,nn_positionObject, "Alert", "Are you sure to DELETE the " + nn_positionObject.getTitle() + " position?", "Yes", "No",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(App.getDataBaseManager().deletePosition(nn_positionObject)){

                                //delete history
                                Date createdate = new Date();
                                String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createdate);
                                HistoryObject historyobj=new HistoryObject(nn_positionObject.getTitle(),"Action:"+ Constant.HistoryAction.DELETE.toString(),cDate);
                                App.getDataBaseManager().createHistory(historyobj);

                                Toast.makeText(mActivity,"Deleted!",Toast.LENGTH_LONG).show();
                                ((HomeFragment)mFragment).onRefreshAll();
                                ((NNFragment)mFragment).dismissDialog(Constant.TAG_HOME_DIALOG_DELETE);
                            }
                        }
                    },
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((NNFragment)mFragment).dismissDialog(Constant.TAG_HOME_DIALOG_DELETE);
                        }
                    }
            ), Constant.TAG_HOME_DIALOG_DELETE);
        }
    }

    class MyViewControl implements View.OnTouchListener{
        private static final int MAX_CLICK_DURATION = 200;
        private long startClickTime;
        public RecyclerView.ViewHolder mholder;
        public MyViewControl(RecyclerView.ViewHolder holder){
            mholder=holder;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setTag(v.getX() - event.getRawX());
                    startClickTime = Calendar.getInstance().getTimeInMillis();
                    Log.v("action!!!", "Down");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.v("action!!!", "UP");
//                    long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
//                    if(clickDuration > MAX_CLICK_DURATION) {
                        //click event has occurred
//                        float x = event.getRawX() + (Float) v.getTag();
//                        Log.v("action!!!", "Move"+Util.pxtodp(x));
//                        if (Math.abs(Util.pxtodp(x))>=1 && v.getX() >= 0) {
//                            if (!isdraggedArray[mholder.getAdapterPosition()]) {
//                                v.animate()
//                                        .x(-Util.dptopx(100))
//                                        .setDuration(200)
//                                        .start();
//                                isdraggedArray[mholder.getAdapterPosition()] = !isdraggedArray[mholder.getAdapterPosition()];
//                            }
//                        }
//                        if (Math.abs(Util.pxtodp(x))>=1 && v.getX() < 0) {
//                            if (isdraggedArray[mholder.getAdapterPosition()]) {
//                                v.animate()
//                                        .x(0)
//                                        .setDuration(200)
//                                        .start();
//                                isdraggedArray[mholder.getAdapterPosition()] = !isdraggedArray[mholder.getAdapterPosition()];
//                            }
//                        }
//                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_MOVE:
                    //long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                        float x = event.getRawX() + (Float) v.getTag();
                        Log.v("action!!!", "Move"+Util.pxtodp(x));
                        if (Math.abs(Util.pxtodp(x))>=10 && v.getX()==0&&x<0) {
                            if (!isdraggedArray[mholder.getAdapterPosition()]) {
                                v.animate()
                                        .x(-Util.dptopx(100))
                                        .setDuration(200)
                                        .start();
                                isdraggedArray[mholder.getAdapterPosition()] = !isdraggedArray[mholder.getAdapterPosition()];
                            }
                        }
                        if (Math.abs(Util.pxtodp(x))>=1 && v.getX()==-Util.dptopx(100)&&x<0&&x>=-Util.dptopx(100)) {
                            if (isdraggedArray[mholder.getAdapterPosition()]) {
                                v.animate()
                                        .x(0)
                                        .setDuration(200)
                                        .start();
                                isdraggedArray[mholder.getAdapterPosition()] = !isdraggedArray[mholder.getAdapterPosition()];
                            }
                        }

                    break;
                default:
                    return false;
            }
            return true;
        }
    }
}
