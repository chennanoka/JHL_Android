package nan.com.jobhuntlog.Utilities;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import nan.com.jobhuntlog.DevelopKit.Util.NNUtil;


/**
 * Created by NAN on 2016-08-07.
 */
public class Util extends NNUtil {

    public Util(Context context) {
        super(context);
    }
    public static int getScreenHeight(){
        return (int)SCREEN_HEIGHT;
    }
    public static int getScreenWidth(){
        return (int)SCREEN_WIDTH;
    }
    public static int compromiseScreenHeight(int expectheight){
        if(expectheight>=(int)SCREEN_HEIGHT-10){
            return (int)SCREEN_HEIGHT-10;
        }else{
            return expectheight;
        }
    }
    public static int compromiseScreenWidth(int expectwidth){
        if(expectwidth>=(int)SCREEN_WIDTH-10){
            return (int)SCREEN_WIDTH-10;
        }else{
            return expectwidth;
        }
    }
    public static long getObjectSize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray().length;
    }
    public static void copyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;

        try {
            byte[] bytes = new byte[buffer_size];
            while(true) {
                int count = is.read(bytes, 0, buffer_size);
                if(count == -1) {
                    break;
                }
                os.write(bytes, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
