package nan.com.jobhuntlog.Cards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nan.com.jobhuntlog.DevelopKit.UI.NNButton;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-09-02.
 */
public class FeedBackCard extends RelativeLayout{
    public Context nn_context;
    public FeedBackCard(Context context) {
        super(context);
        nn_context=context;

        LayoutParams param = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(param);

        LinearLayout messageContianer = new LinearLayout(context);
        messageContianer.setOrientation(LinearLayout.VERTICAL);
        messageContianer.setGravity(CENTER_HORIZONTAL);
        LayoutParams messageContianerparam= new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        messageContianerparam.setMargins(Util.dptopx(10),Util.dptopx(10),Util.dptopx(10),Util.dptopx(10));
        messageContianer.setLayoutParams(messageContianerparam);
        messageContianer.setPadding(Util.dptopx(10),Util.dptopx(10),Util.dptopx(10),Util.dptopx(10));
        messageContianer.setId(Util.generateViewId());

        //message title
        TextView textView = new TextView(context);
        textView.setText("Feedback");
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        LayoutParams textviewparam= new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textviewparam);


        //set message
        TextView messageView = new TextView(context);
        messageView.setGravity(Gravity.CENTER);
        LayoutParams messageparam= new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        messageView.setLayoutParams(messageparam);
        messageView.setText("By press the button below, the App will redirect you to send feedback to jobhuntlogapp@gmail.com. Looking forward your suggestions & reports");


        messageContianer.addView(textView);
        messageContianer.addView(messageView);


        NNButton button = new NNButton(context);
        button.setPadding(Util.dptopx(5),Util.dptopx(5),Util.dptopx(5),Util.dptopx(5));
        LayoutParams buttonparam= new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonparam.setMargins(0,Util.dptopx(10),0,0);
        buttonparam.addRule(RelativeLayout.BELOW,messageContianer.getId());
        buttonparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        button.setLayoutParams(buttonparam);
        button.setText("Send Feedback");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
                sendEmailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"jobhuntlogapp@gmail.com"});
                sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT,"FeedBack from "+android.os.Build.MODEL);
                sendEmailIntent.putExtra("exit_on_sent",true);
                sendEmailIntent.putExtra("exit_on_cancel",true);
                sendEmailIntent.setType("message/rfc822");
                ((Activity)nn_context).startActivity(sendEmailIntent);
            }
        });

        this.addView(messageContianer);
        this.addView(button);
    }
}
