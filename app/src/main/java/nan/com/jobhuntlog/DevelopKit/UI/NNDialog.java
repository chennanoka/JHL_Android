package nan.com.jobhuntlog.DevelopKit.UI;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by NAN on 2016-08-21.
 */
public class NNDialog extends Dialog
{
    private String tag;
    public NNDialog(Context context,String tag)
    {
        super(context);
        this.tag=tag;
    }
    public String getTag(){
        return tag;
    }

}