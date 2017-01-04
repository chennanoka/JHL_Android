package nan.com.jobhuntlog.Objects;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import nan.com.jobhuntlog.Cards.DrawerOptionCard;
import nan.com.jobhuntlog.R;

/**
 * Created by NAN on 2016-08-06.
 */
public class DrawerOptionsItem {
    public String title;
    public Drawable drawable;
    public View.OnClickListener onlcicklistener;
    public DrawerOptionCard view;
    public Activity nn_activity;
    public DrawerOptionsItem(Activity activity, String item, Drawable drawable, View.OnClickListener listener){
        this.title=item;
        this.drawable=drawable;
        this.onlcicklistener=listener;
        this.view=new DrawerOptionCard(activity);
        this.view.titletextview.setText(item);
        this.view.titleimgview.setImageDrawable(drawable);
        nn_activity=activity;
        this.view.setOnClickListener(listener);
        this.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundColor(ContextCompat.getColor(nn_activity, R.color.white_smoke));
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setBackgroundColor(ContextCompat.getColor(nn_activity, R.color.white));
                        v.performClick();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.setBackgroundColor(ContextCompat.getColor(nn_activity, R.color.white));
                        break;
                }
                return true;
            }
        });
    }
}
