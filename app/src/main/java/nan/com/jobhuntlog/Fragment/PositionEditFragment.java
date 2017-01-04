package nan.com.jobhuntlog.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nan.com.jobhuntlog.Cards.PositionEditCard;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.MainActivity;
import nan.com.jobhuntlog.Objects.PositionObject;

/**
 * Created by NAN on 2016-08-30.
 */
public class PositionEditFragment extends NNFragment {
    public MainActivity nn_activity;
    public PositionObject positionObject;
    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            nn_activity=(MainActivity)context;
        }
    }
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PositionEditCard positionEditCard = new PositionEditCard(nn_activity,this,positionObject);
        return positionEditCard;
    }
}
