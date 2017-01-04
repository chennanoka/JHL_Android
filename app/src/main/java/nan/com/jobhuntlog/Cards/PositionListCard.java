package nan.com.jobhuntlog.Cards;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import nan.com.jobhuntlog.Application.App;
import nan.com.jobhuntlog.Constant.Constant;
import nan.com.jobhuntlog.DevelopKit.UI.NNImageButton;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragmentActivity;
import nan.com.jobhuntlog.Fragment.HomeFragment;
import nan.com.jobhuntlog.MainActivity;
import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.Objects.PositionObject;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-08.
 */
public class PositionListCard extends RelativeLayout{

    public TextView titleLabel;
    public TextView locationLabel;
    public TextView comingeventLabel;
    public TextView stageLabel;

    public TextView titleTextView;
    public TextView locationTextView;
    public TextView comingeventDateTextView;
    public TextView comingeventTimeTextView;
    public TextView comingeventNoteTextView;
    public TextView stageTextView;


    public LinearLayout optionsContainer;
    public RelativeLayout optionContainerRelativeParent;

    public NNImageButton editimgBut;
    public NNImageButton deleteimgBut;
    public ImageView dividerImgView;


    public PositionObject nn_positionObject;
    public NNFragmentActivity nn_fragmentActivity;
    public NNFragment nn_fragment;

    public LinearLayout containerLinearLayout;

    LayoutParams contentParam;

    public PositionListCard(NNFragmentActivity fragmentActivity, NNFragment fragment, PositionObject positiobobj) {
        super(fragmentActivity);

        this.nn_fragmentActivity=fragmentActivity;
        this.nn_positionObject=positiobobj;
        this.nn_fragment=fragment;

        this.setClickable(false);

        this.setPadding(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));
        FrameLayout.LayoutParams viewgroupParam = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(viewgroupParam);


        containerLinearLayout=new LinearLayout(nn_fragmentActivity);
        containerLinearLayout.setBackgroundResource(R.drawable.layerlist_frame_grayround);
        containerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        containerLinearLayout.setPadding(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));

        LinearLayout.LayoutParams containerParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        containerLinearLayout.setLayoutParams(containerParam);

        //for child views
        LayoutParams labelParam =new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        contentParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        //render views
        titleLabel = new TextView(nn_fragmentActivity);
        titleLabel.setText("Title");
        titleLabel.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        titleLabel.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        titleTextView = new TextView(nn_fragmentActivity);
        titleTextView.setLayoutParams(contentParam);
        titleTextView.setText(positiobobj.getTitle());

        locationLabel = new TextView(nn_fragmentActivity);
        locationLabel.setText("Location");
        locationLabel.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        locationLabel.setLayoutParams(labelParam);

        locationTextView = new TextView(nn_fragmentActivity);
        locationTextView.setLayoutParams(contentParam);
        locationTextView.setText(positiobobj.getLocation());

        comingeventLabel = new TextView(nn_fragmentActivity);
        comingeventLabel.setText("Coming Event");
        comingeventLabel.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        comingeventLabel.setLayoutParams(labelParam);

        comingeventDateTextView = new TextView(nn_fragmentActivity);
        comingeventDateTextView.setLayoutParams(contentParam);
        comingeventDateTextView.setText(positiobobj.getEventDate());

        comingeventTimeTextView = new TextView(nn_fragmentActivity);
        comingeventTimeTextView.setLayoutParams(contentParam);
        comingeventTimeTextView.setText(positiobobj.getEventTime());

        comingeventNoteTextView = new TextView(nn_fragmentActivity);
        comingeventNoteTextView.setLayoutParams(contentParam);
        comingeventNoteTextView.setText(positiobobj.getEventNote());

        stageLabel = new TextView(nn_fragmentActivity);
        stageLabel.setText("Stage");
        stageLabel.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        stageLabel.setLayoutParams(labelParam);

        stageTextView = new TextView(nn_fragmentActivity);
        stageTextView.setLayoutParams(contentParam);
        stageTextView.setText(positiobobj.getStage());


        // add editor and delete buttons
        optionContainerRelativeParent = new RelativeLayout(nn_fragmentActivity);
        optionContainerRelativeParent.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        optionContainerRelativeParent.setPadding(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));

        optionsContainer = new LinearLayout(nn_fragmentActivity);
        optionsContainer.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams relativeParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        relativeParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        optionsContainer.setLayoutParams(relativeParam);

        editimgBut=new NNImageButton(nn_fragmentActivity, 24, 45, 3, R.mipmap.ic_mode_edit_black_36dp, "Edit", new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)nn_fragmentActivity).showOpportunityEditFragment(nn_positionObject);
            }
        });

        deleteimgBut=new NNImageButton(nn_fragmentActivity, 24, 45, 3, R.mipmap.ic_delete_sweep_black_36dp, "Delete", new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Context context,String title, String message, String positive, String negative,OnClickListener positivelistener,OnClickListener negativelistener
                ((HomeFragment)nn_fragment).showDialog(new PositionDeleteCard(nn_fragmentActivity,nn_fragment,nn_positionObject, "Alert", "Are you sure to DELETE the " + nn_positionObject.getTitle() + " position?", "Yes", "No",
                        new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(App.getDataBaseManager().deletePosition(nn_positionObject)){

                                    //delete history
                                    Date createdate = new Date();
                                    String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createdate);
                                    HistoryObject historyobj=new HistoryObject(nn_positionObject.getTitle(),"Action:"+Constant.HistoryAction.DELETE.toString(),cDate);
                                    App.getDataBaseManager().createHistory(historyobj);

                                    Toast.makeText(nn_fragmentActivity,"Deleted!",Toast.LENGTH_LONG).show();
                                    ((HomeFragment)nn_fragment).onRefreshAll();
                                    ((NNFragment)nn_fragment).dismissDialog(Constant.TAG_HOME_DIALOG_DELETE);
                                }
                            }
                        },
                        new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((NNFragment)nn_fragment).dismissDialog(Constant.TAG_HOME_DIALOG_DELETE);
                            }
                        }
                ), Constant.TAG_HOME_DIALOG_DELETE);
            }
        });


        dividerImgView =new ImageView(nn_fragmentActivity);
        dividerImgView.setImageResource(R.drawable.shape_line_grayline);
        LinearLayout.LayoutParams dividerImgViewparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,Util.dptopx(24));
        dividerImgViewparam.setMargins(Util.dptopx(5),0,Util.dptopx(5),0);
        dividerImgView.setLayoutParams(dividerImgViewparam);


        optionsContainer.addView(editimgBut);
        optionsContainer.addView(dividerImgView);
        optionsContainer.addView(deleteimgBut);

        optionContainerRelativeParent.addView(optionsContainer);


        containerLinearLayout.addView(titleLabel);
        containerLinearLayout.addView(titleTextView);
        containerLinearLayout.addView(locationLabel);
        containerLinearLayout.addView(locationTextView);
        containerLinearLayout.addView(comingeventLabel);
        containerLinearLayout.addView(comingeventDateTextView);
        containerLinearLayout.addView(comingeventTimeTextView);
        containerLinearLayout.addView(comingeventNoteTextView);
        containerLinearLayout.addView(stageLabel);
        containerLinearLayout.addView(stageTextView);

        this.addView(containerLinearLayout);
        this.addView(optionContainerRelativeParent);
    }
}
