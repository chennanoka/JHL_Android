package nan.com.jobhuntlog.Cards;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import nan.com.jobhuntlog.DevelopKit.UI.NNButton;
import nan.com.jobhuntlog.Objects.PositionObject;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-31.
 */
public class PositionDeleteCard extends LinearLayout {
    public TextView titleView;
    public TextView messageView;
    public NNButton positiveBut;
    public NNButton negativeBut;

    public Fragment nn_fragment;
    public FragmentActivity nn_activity;
    public PositionObject nn_positionObject;

    public PositionDeleteCard(Context context,Fragment fragment,PositionObject projectObject,String title, String message, String positive, String negative,OnClickListener positivelistener,OnClickListener negativelistener) {
        super(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        this.setPadding(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));
        this.setLayoutParams(layoutParams);
        this.setOrientation(VERTICAL);

        this.nn_fragment=fragment;
        this.nn_activity=(FragmentActivity)context;
        this.nn_positionObject=projectObject;

        titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        LayoutParams titleparam = new LayoutParams(LayoutParams.WRAP_CONTENT,Util.dptopx(45));
        titleView.setLayoutParams(titleparam);


        messageView = new TextView(context);
        messageView.setText(message);
        LayoutParams messageparam = new LayoutParams(LayoutParams.WRAP_CONTENT,Util.dptopx(60));
        messageView.setLayoutParams(messageparam);

        LinearLayout buttonContainer = new LinearLayout(context);
        buttonContainer.setOrientation(HORIZONTAL);
        buttonContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        LinearLayout.LayoutParams buttonsparam= new LinearLayout.LayoutParams(Util.dptopx(60), Util.dptopx(40));
        buttonsparam.weight=1;

        positiveBut = new NNButton(context);
        positiveBut.setText(positive);
        positiveBut.setOnClickListener(positivelistener);
        positiveBut.setLayoutParams(buttonsparam);

        negativeBut = new NNButton(context);
        negativeBut.setText(negative);
        negativeBut.setOnClickListener(negativelistener);
        negativeBut.setLayoutParams(buttonsparam);

        buttonContainer.addView(positiveBut);
        buttonContainer.addView(negativeBut);

        this.addView(titleView);
        this.addView(messageView);
        this.addView(buttonContainer);
    }
}
