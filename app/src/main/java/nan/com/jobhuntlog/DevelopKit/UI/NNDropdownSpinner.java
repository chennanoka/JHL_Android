package nan.com.jobhuntlog.DevelopKit.UI;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-21.
 */
public class NNDropdownSpinner extends RelativeLayout{
    public Spinner spinner;
    public NNDropdownSpinner(Context context, AdapterView.OnItemSelectedListener itemselectlistener){
        super(context);
        this.setBackgroundResource(R.drawable.layerlist_frame_grayround);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,Util.dptopx(45)));

        spinner=new Spinner(context);
        spinner.setOnItemSelectedListener(itemselectlistener);
        spinner.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams spinnerparam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,Util.dptopx(42));
        spinnerparam.setMargins(Util.dptopx(5),0,0,0);
        spinnerparam.addRule(RelativeLayout.CENTER_IN_PARENT);
        spinner.setLayoutParams(spinnerparam);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            spinner.setLayoutMode(Spinner.MODE_DROPDOWN);
        }

        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.ic_dropdown);
        RelativeLayout.LayoutParams imagviewparam = new RelativeLayout.LayoutParams(Util.dptopx(50),Util.dptopx(40));
        imagviewparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imagviewparam.addRule(RelativeLayout.CENTER_VERTICAL);
        imageView.setLayoutParams(imagviewparam);


        this.addView(spinner);
        this.addView(imageView);
    }
}
