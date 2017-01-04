package nan.com.jobhuntlog;

import junit.framework.TestCase;

import org.junit.Test;

import nan.com.jobhuntlog.DevelopKit.Network.INetworkResponseListener;
import nan.com.jobhuntlog.DevelopKit.Network.NetworkManager;
import nan.com.jobhuntlog.DevelopKit.Network.WebResponseObject;
import nan.com.jobhuntlog.DevelopKit.Util.NNUtil;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends TestCase{

    Mytest testobj;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testobj=new Mytest();
    }

    @Test
    public void testaddition_isCorrect() throws Exception {
        assertEquals(3, testobj.add(1,2));
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}

class Mytest{
    Mytest(){

    }
    public int add(int a, int b){
        return a+b;
    }

}