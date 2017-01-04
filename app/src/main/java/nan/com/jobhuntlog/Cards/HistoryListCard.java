package nan.com.jobhuntlog.Cards;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nan.com.jobhuntlog.Objects.HistoryObject;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-29.
 */
public class HistoryListCard extends LinearLayout{


    public LinearLayout containderLayout;

    public RelativeLayout timeline1Container;
    public ImageView timelineline1ImageView;
    public ImageView timelinenode1ImageView;
    public TextView timeline1Textview;

    public RelativeLayout timeline2Container;
    public ImageView timelineline2ImageView;
    public TextView timeline2Textview;

    public HistoryListCard(Context context, HistoryObject historyObject) {
        super(context);

        this.setBackgroundColor(ContextCompat.getColor(context, R.color.white_smoke));


        containderLayout = new LinearLayout(context);
        containderLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containderLayout.setOrientation(LinearLayout.VERTICAL);
        //containderLayout.setPadding(Util.dptopx(10),0,0,0);

        timeline1Container=new RelativeLayout(context);
        timeline1Container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dptopx(30)));
        //timeline1Container.setPadding(Util.dptopx(10),0,Util.dptopx(10),0);

        RelativeLayout timeline1innerContainer=new RelativeLayout(context);
        timeline1innerContainer.setPadding(Util.dptopx(10),0,Util.dptopx(10),0);
        timeline1innerContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dptopx(30)));

        timelineline1ImageView = new ImageView(context);
        timelineline1ImageView.setImageResource(R.drawable.shape_line_historyline);
        timelineline1ImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Util.dptopx(30)));
        timelineline1ImageView.setId(Util.generateViewId());


        timelinenode1ImageView = new ImageView(context);
        RelativeLayout.LayoutParams timenodeparam=new RelativeLayout.LayoutParams(Util.dptopx(10),Util.dptopx(10));
        timenodeparam.setMargins(Util.dptopx(6),Util.dptopx(-5),0,0);
        timenodeparam.addRule(RelativeLayout.CENTER_VERTICAL);
        timelinenode1ImageView.setLayoutParams(timenodeparam);
        timelinenode1ImageView.setImageResource(R.drawable.shap_circle_historynode);

        timeline1Textview = new TextView(context);
        RelativeLayout.LayoutParams timeline1textparam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,Util.dptopx(30));
        timeline1Textview.setGravity(Gravity.CENTER_VERTICAL);
        timeline1textparam.setMargins(Util.dptopx(20),0,0,0);
        timeline1textparam.addRule(RelativeLayout.RIGHT_OF,timelineline1ImageView.getId());
        timeline1Textview.setLayoutParams(timeline1textparam);
        timeline1Textview.setText(historyObject.getDate());

        timeline1innerContainer.addView(timelineline1ImageView);
        timeline1innerContainer.addView(timeline1Textview);

        timeline1Container.addView(timelinenode1ImageView);
        timeline1Container.addView(timeline1innerContainer);

        // line 2
        timeline2Container=new RelativeLayout(context);
        timeline2Container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,Util.dptopx(20)));
        timeline2Container.setPadding(Util.dptopx(10),0,Util.dptopx(10),0);

        timelineline2ImageView = new ImageView(context);
        timelineline2ImageView.setImageResource(R.drawable.shape_line_historyline);
        timelineline2ImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Util.dptopx(20)));
        timelineline2ImageView.setId(Util.generateViewId());

        timeline2Textview = new TextView(context);
        RelativeLayout.LayoutParams timeline2textparam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,Util.dptopx(20));
        timeline2textparam.setMargins(Util.dptopx(20),0,0,0);
        timeline2textparam.addRule(RelativeLayout.RIGHT_OF,timelineline2ImageView.getId());
        timeline2Textview.setGravity(Gravity.CENTER_VERTICAL);
        timeline2Textview.setLayoutParams(timeline2textparam);
        timeline2Textview.setText(historyObject.getPositionTitle()+" "+historyObject.getAction());

        timeline2Container.addView(timelineline2ImageView);
        timeline2Container.addView(timeline2Textview);

        containderLayout.addView(timeline1Container);
        containderLayout.addView(timeline2Container);
        this.addView(containderLayout);
    }
}
