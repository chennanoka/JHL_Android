package nan.com.jobhuntlog.DevelopKit.UI;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;

import nan.com.jobhuntlog.R;

/**
 * Created by NAN on 2016-08-21.
 */
public class NNButton extends Button{

    public static final int GRAY=1;

    public NNButton(Context context) {
        super(context);
        this.setBackgroundResource(R.drawable.selector_button_bluebutton);
        this.setTextColor(Color.WHITE);
        this.setTypeface(this.getTypeface(), Typeface.BOLD);
    }
    public NNButton(Context context,int style) {
        this(context);
           switch (style){
               case GRAY:
                   this.setBackgroundResource(R.drawable.layerlist_frame_grayround);
                   this.setTypeface(null, Typeface.NORMAL);
                   this.setTextColor(Color.BLACK);
                   break;
           }
    }
}
