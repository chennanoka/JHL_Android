package nan.com.jobhuntlog;


import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import nan.com.jobhuntlog.Fragment.HomeFragment;

/**
 * Created by NAN on 2016-11-16.
 */
public class ActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mActivity;
    HomeFragment mfragment;
    public ActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity=getActivity();
        mfragment=new HomeFragment();
        FragmentManager fm=mActivity.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.home_content_framelayout,mfragment).commit();
    }

    @SmallTest
    public void testoffset() throws Exception {
        assertEquals(0, mfragment.offset);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
