package nan.com.jobhuntlog.DevelopKit.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by NAN on 2016-10-22.
 */
public class NNUtil {
    public static Context nn_context;

    //screen size
    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;
    public static float SCREEN_WIDTH_PX;
    public static float SCREEN_HEIGHT_PX;

    //view id generate
    public static int idanchor = 1;
    public static Object idanchorlocker = new Object();

    //public static solid resource
    public static int defaulticon;


    public NNUtil(Context context) {
        nn_context=context;
        defaulticon = context.getResources().getIdentifier("ic_default", "drawable", context.getPackageName());
        SCREEN_WIDTH = pxtodp(nn_context.getResources().getDisplayMetrics().widthPixels);
        SCREEN_HEIGHT = pxtodp(nn_context.getResources().getDisplayMetrics().heightPixels);
        SCREEN_WIDTH_PX = nn_context.getResources().getDisplayMetrics().widthPixels;
        SCREEN_HEIGHT_PX = nn_context.getResources().getDisplayMetrics().heightPixels;
    }

    public static String getVersionName() {
        try {
            return nn_context.getPackageManager().getPackageInfo(nn_context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
    public static int getVersionCode() {
        try {
            return nn_context.getPackageManager().getPackageInfo(nn_context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static android.net.Uri resIdToUri(Context context, int name) {
        return android.net.Uri.parse("android.resource://" + context.getPackageName() + '/' + name);
    }

    public static int pxtodp(float px) {
        return (int) (px / nn_context.getResources().getDisplayMetrics().density);
    }

    public static int dptopx(float dp) {
        return (int) (dp * nn_context.getResources().getDisplayMetrics().density);
    }

    public static String getsharedpreference(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(nn_context);
        return prefs.getString(key, "");
    }

    public static void setsharedpreference(String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(nn_context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getDeviceID() {
        return android.provider.Settings.Secure.getString(nn_context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        }
        else{
            synchronized (idanchorlocker) {
                idanchor++;
                if (idanchor > 0x00FFFFFF)
                    idanchor = 1;
                return idanchor;
            }
        }
    }
    public static boolean stringIsNullorEmpty(String param){
        return param == null || param.trim().length() == 0;
    }

    //Time functions
    public static long millisecondsTimestamp() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(new Date());
        return cal.getTimeInMillis();
    }
    public static long secondsTimestamp() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(new Date());
        return cal.getTimeInMillis()/1000; }

    public static int getYear() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }
    public static int getMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(new Date());
        return cal.get(Calendar.MONTH) + 1;
    }
    public static int getDay() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * @param scaleBitmapImage original img
     * @param targetint width and height in px
     * @return
     */
    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage, int targetint) {
        int targetWidth = targetint;
        int targetHeight = targetint;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        android.graphics.Path path = new android.graphics.Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

}