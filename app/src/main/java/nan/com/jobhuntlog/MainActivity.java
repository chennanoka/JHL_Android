package nan.com.jobhuntlog;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nan.com.jobhuntlog.Adapter.DrawerOpetionsAdapter;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragment;
import nan.com.jobhuntlog.DevelopKit.UI.NNFragmentActivity;
import nan.com.jobhuntlog.Fragment.FeedBackFragment;
import nan.com.jobhuntlog.Fragment.HeaderFragment;
import nan.com.jobhuntlog.Fragment.HistoryFragment;
import nan.com.jobhuntlog.Fragment.HomeFragment;
import nan.com.jobhuntlog.Fragment.PositionEditFragment;
import nan.com.jobhuntlog.Objects.DrawerOptionsItem;
import nan.com.jobhuntlog.Objects.PositionObject;
import nan.com.jobhuntlog.Utilities.Util;

public class MainActivity extends NNFragmentActivity {
    public DrawerLayout drawerlayout;
    public ListView drawermenulistview;
    public Fragment showingFragment;
    public Fragment headerFragment;
    public RelativeLayout drawerRelativeLayout;
    public ArrayList<DrawerOptionsItem> leftdraweritemslist;
    public DrawerOpetionsAdapter slideradapter;
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof HeaderFragment){
            headerFragment=fragment;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerlayout= (DrawerLayout)findViewById(R.id.leftdrawer_layout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);


        TextView namtetext = (TextView)findViewById(R.id.nametext_leftslide);

        //TextView emailtext = (TextView)findViewById (R.id.emailtext_leftslide);
        ImageView titleimg = (ImageView)findViewById(R.id.titleimg_leftslide);
        drawermenulistview=(ListView)findViewById(R.id.leftdrawer_list);

        drawerRelativeLayout = (RelativeLayout)findViewById(R.id.drawer_frame);



        namtetext.setGravity(Gravity.CENTER_VERTICAL);
        namtetext.setText("Version:"+Util.getVersionName());
        //emailtext.setText("chennanok@gmail.com");
        titleimg.setImageResource(R.mipmap.ic_launcher);



        Drawable opportunityicon = ContextCompat.getDrawable(this, R.mipmap.ic_home_gray_36dp);
        Drawable historyicon = ContextCompat.getDrawable(this, R.mipmap.ic_history_white_36dp);
        Drawable feedbackicon = ContextCompat.getDrawable(this, R.mipmap.ic_feedback_gray_36dp);


        leftdraweritemslist= new ArrayList<DrawerOptionsItem>();
        leftdraweritemslist.add(new DrawerOptionsItem(this,"Opportunity", opportunityicon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(showingFragment instanceof HomeFragment)){
                    for(DrawerOptionsItem item :leftdraweritemslist) {
                        if(item.title.equals("Opportunity")) {
                            item.view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white_smoke));
                        }
                        else{
                            item.view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        }
                    }
                    slideradapter.addandupdatelist(leftdraweritemslist);
                    showHomeFragment();
                    MainActivity.this.toggleDrawer();
                }
            }
        }));
        leftdraweritemslist.add(new DrawerOptionsItem(this,"History", historyicon,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!(showingFragment instanceof HistoryFragment)){
                for(DrawerOptionsItem item :leftdraweritemslist) {
                    if(item.title.equals("History")) {
                        item.view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white_smoke));
                    }
                    else{
                        item.view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                    }
                }
                slideradapter.addandupdatelist(leftdraweritemslist);
                showHistoryFragment();
                MainActivity.this.toggleDrawer();
            }
            }
        }));
        leftdraweritemslist.add(new DrawerOptionsItem(this,"Feedback",feedbackicon,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(DrawerOptionsItem item :leftdraweritemslist) {
                    if(item.title.equals("Feedback")) {
                        item.view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white_smoke));
                    }
                    else{
                        item.view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                    }
                }
                slideradapter.addandupdatelist(leftdraweritemslist);
                showFeedbackFragment();
                MainActivity.this.toggleDrawer();
            }
        }));

        slideradapter=new DrawerOpetionsAdapter(this,leftdraweritemslist);
        drawermenulistview.setAdapter(slideradapter);


        //set slidebar size
        int drawerwidth = (getResources().getDisplayMetrics().widthPixels)*2/3;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) drawerRelativeLayout.getLayoutParams();
        params.width = drawerwidth;
        drawerRelativeLayout.setLayoutParams(params);


        //initial content frame
        showHomeFragment();

    }

    /**
     * show fragments
     */
    public void showHistoryFragment(){
        if (!isFinishing()) {
            HistoryFragment historyFragment =new HistoryFragment();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.home_content_framelayout,historyFragment).commit();
            showingFragment=historyFragment;
        }

    }
    public void showHomeFragment(){
        if (!isFinishing()) {
            HomeFragment homeFragment =new HomeFragment();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.home_content_framelayout,homeFragment).commit();
            showingFragment=homeFragment;
        }

    }
    public void showOpportunityEditFragment(PositionObject positionObject){
        if (!isFinishing()) {
            PositionEditFragment oeditFragment =new PositionEditFragment();
            oeditFragment.isChildView=true;
            oeditFragment.positionObject=positionObject;
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.addToBackStack (null);
            ft.replace(R.id.home_content_framelayout,oeditFragment);
            ft.commitAllowingStateLoss ();
            showingFragment=oeditFragment;
        }

    }

    public void showFeedbackFragment(){
        if (!isFinishing()) {
            FeedBackFragment oeditFragment =new FeedBackFragment();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.home_content_framelayout,oeditFragment).commit();
            showingFragment=oeditFragment;
        }

    }



    // extends or listener functions
    @Override
    public void changeHeaderElement(NNFragment fragment){
        if(headerFragment!=null&&fragment!=headerFragment&&fragment.isChildView){
            ((HeaderFragment)headerFragment).updateHeaderFragment(0);
        }else if(headerFragment!=null&&fragment!=headerFragment&&!fragment.isChildView){
            ((HeaderFragment)headerFragment).updateHeaderFragment(1);
        }
    }
    public void toggleDrawer(){
        if(!drawerlayout.isDrawerOpen(drawerRelativeLayout)) {
            drawerlayout.openDrawer(Gravity.LEFT);
        }else{
            drawerlayout.closeDrawer(Gravity.LEFT);
        }
    }
}
