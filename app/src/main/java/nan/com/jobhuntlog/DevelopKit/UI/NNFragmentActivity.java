package nan.com.jobhuntlog.DevelopKit.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by NAN on 2016-08-19.
 */
public class NNFragmentActivity extends FragmentActivity {

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //refresh the showing element of headfragment
    public void changeHeaderElement(NNFragment fragment){
    }

}