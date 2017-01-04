package nan.com.jobhuntlog.DevelopKit.UI;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-30.
 */
public class NNImageButton extends RelativeLayout {

    public TextView textView;
    public ImageView imageView;
    public Context nn_context;
    public View.OnClickListener clickListener;
    public NNImageButton(Context context,int height,int textwidth,int space,int resourceid,String text,View.OnClickListener listener) {
        super(context);
        nn_context=context;

        int thewidth=height+textwidth+space;
        this.setLayoutParams(new ViewGroup.LayoutParams(Util.dptopx(thewidth),Util.dptopx(height)));
        this.clickListener=listener;
        this.setOnClickListener(clickListener);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(ContextCompat.getColor(nn_context, R.color.gray_cloud));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(ContextCompat.getColor(nn_context, R.color.white));
                    v.performClick();
                }else if (event.getAction() == MotionEvent.ACTION_MOVE){
                    v.setBackgroundColor(ContextCompat.getColor(nn_context, R.color.white));
                }
                return true;
            }
        });

        imageView = new ImageView(context);
        imageView.setId(Util.generateViewId());
        RelativeLayout.LayoutParams imgparam= new RelativeLayout.LayoutParams(Util.dptopx(height),Util.dptopx(height));
        imageView.setLayoutParams(imgparam);
        imageView.setImageResource(resourceid);

        textView= new TextView(context);
        RelativeLayout.LayoutParams buttonparam= new RelativeLayout.LayoutParams(Util.dptopx(textwidth),RelativeLayout.LayoutParams.MATCH_PARENT);
        buttonparam.addRule(RelativeLayout.RIGHT_OF,imageView.getId());
        buttonparam.setMargins(Util.dptopx(space),0,0,0);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setSingleLine();
        textView.setText(text);
        textView.setLayoutParams(buttonparam);

        this.addView(imageView);
        this.addView(textView);
    }
}
