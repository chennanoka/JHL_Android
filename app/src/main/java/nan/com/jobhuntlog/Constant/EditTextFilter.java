package nan.com.jobhuntlog.Constant;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by NAN on 2016-08-24.
 */
public class EditTextFilter implements InputFilter {

    public EditTextFilter(){

    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if(source.equals("")){
            return source;
        }else if(source.toString().matches("[0-9a-zA-Z ]+")){
            return source;
        }else{
            return "";
        }
    }
}