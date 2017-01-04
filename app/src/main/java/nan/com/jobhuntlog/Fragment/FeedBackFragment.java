package nan.com.jobhuntlog.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import nan.com.jobhuntlog.Cards.FeedBackCard;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.MainActivity;

/**
 * Created by NAN on 2016-09-02.
 */
public class FeedBackFragment extends NNFragment {
    MainActivity nn_activity;
    public ScrollView rootview;
    @Override
    public void onAttach (Context context)
    {
        super.onAttach (context);

        if (context instanceof MainActivity){
            nn_activity=(MainActivity)context;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = new ScrollView(nn_activity);
        ScrollView.LayoutParams rootparam = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,ScrollView.LayoutParams.MATCH_PARENT);
        rootview.setLayoutParams(rootparam);

        FeedBackCard card=new FeedBackCard(nn_activity);
        rootview.addView(card);

        return rootview;
    }

}
