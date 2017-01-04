package nan.com.jobhuntlog.DevelopKit.UI;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import nan.com.jobhuntlog.Constant.EditTextFilter;
import nan.com.jobhuntlog.R;
import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by NAN on 2016-08-21.
 */
public class NNEditText extends LinearLayout{
    public EditText edittext;
    private Context nn_context;
    public NNEditText(Context context){
        super(context);
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dptopx(40)));
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setBackgroundResource(R.drawable.layerlist_frame_grayround);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        edittext =new EditText(context);
        edittext.setSingleLine();
        edittext.setBackgroundResource(R.drawable.layerlist_rectangle_white);
        edittext.setCursorVisible(true);
        edittext.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        edittext.setTextColor(ContextCompat.getColor(context,R.color.black));
        edittext.setFilters(new InputFilter[]{new EditTextFilter()});
        //have to use reflection to change cursordrawable
        try {
            // https://github.com/android/platform_frameworks_base/blob/kitkat-release/core/java/android/widget/TextView.java#L562-564
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(edittext, R.drawable.shape_line_cursor);
        } catch (Exception ignored) {
        }
        LinearLayout.LayoutParams edittextparam=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,Util.dptopx(38));
        edittextparam.setMargins(Util.dptopx(5),Util.dptopx(1),Util.dptopx(5),Util.dptopx(1));
        edittext.setLayoutParams(edittextparam);
        this.addView(edittext);
    }
    public NNEditText(Context context,String hint){
        this(context);
        if(hint!=null) {
            edittext.setHint(hint);
        }
    }

    public String getText(){
        if(edittext!=null){
            return edittext.getText().toString();
        }else{
            return "";
        }
    }
    public void setText(String text){
        if(edittext!=null){
            edittext.setText(text);
        }
    }
}
