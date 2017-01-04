package nan.com.jobhuntlog.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.Test.AndroidDatabaseManager;

/**
 * Created by NAN on 2016-08-08.
 */
public class FooterFragment extends NNFragment {
    Button databsetetview;
    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
    }
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        databsetetview=new Button(nn_activity);
        databsetetview.setText("databasetest");
        databsetetview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent dbmanager = new Intent(getActivity(),AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
        return databsetetview;
    }
}
