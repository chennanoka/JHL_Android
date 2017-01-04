package nan.com.jobhuntlog.DevelopKit.UI;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Stack;

/**
 * Created by NAN on 2016-08-19.
 */
public class NNFragment extends Fragment {

    private Stack dialogstack;
    public NNFragmentActivity nn_activity;
    public boolean isChildView=false;
    @Override
    public void onAttach (Context context) {
        super.onAttach(context);

        if (context instanceof NNFragmentActivity){
            nn_activity=(NNFragmentActivity) context;
        }
    }
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        dialogstack= new Stack();
    }
    @Override
    public void onResume() {
        super.onResume();
        nn_activity.changeHeaderElement(this);
        hideSoftKeyboard();
    }
    public void hideSoftKeyboard() {
        if(this != null && nn_activity.getCurrentFocus() != null && nn_activity.getCurrentFocus().getWindowToken() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)nn_activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(nn_activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showDialog(View view,String tag){
            NNDialog dialog = new NNDialog(nn_activity,tag);
            dialog.setContentView(view);
            dialog.setCancelable(false);
            dialogstack.push(dialog);
            dialog.show();
    }
    public void hideAllDialog(){
        while ( !dialogstack.empty() )
        {
            ((NNDialog)dialogstack.pop()).dismiss();
        }
    }
    public NNDialog getCurrentDialog(){
        if(!dialogstack.isEmpty()){
            return (NNDialog)dialogstack.peek();
        }else{
            return  null;
        }
    }

    public boolean isDialogTopShowing(String tag){
        if(dialogstack.isEmpty()){
            return false;
        }else{
            NNDialog dialog = (NNDialog)dialogstack.peek();
            if(dialog.getTag().equals(tag)&&dialog.isShowing()){
                return true;
            }else{
                return false;
            }
        }
    }
    //dismiss all dialog until the target one
    public void dismissDialog(String tag){
        while(!dialogstack.isEmpty()){
            NNDialog dialog = (NNDialog)dialogstack.pop();
            dialog.dismiss();
            if(dialog.getTag().equals(tag)){
                break;
            }
        }
    }
    public void onRefreshAll(){
    }
}