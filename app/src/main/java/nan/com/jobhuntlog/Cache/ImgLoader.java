package nan.com.jobhuntlog.Cache;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import nan.com.jobhuntlog.Utilities.Util;

/**
 * Created by nanchen on 2016-12-26.
 */

public class ImgLoader {
    ImgMemoryCache mImgMemoryCache = new ImgMemoryCache();
    ImgFileCache mImgFileCache;
    private Map<ImageView,String> mImgViews = Collections.synchronizedMap(new WeakHashMap<ImageView,String>());
    ExecutorService mExecutorService;
    public ImgLoader(Context context){
        mImgFileCache = new ImgFileCache(context);
        mExecutorService = Executors.newFixedThreadPool(5);
    }
    public void assempleImg(int defaultimgid,String url,ImageView imgView){
        mImgViews.put(imgView,url);
        Bitmap bitmap = mImgMemoryCache.get(url);
        if(bitmap!=null){
            imgView.setImageBitmap(bitmap);
        }else{
            queuePhoto(url,imgView,defaultimgid);
            imgView.setImageResource(defaultimgid);
        }
    }
    public void queuePhoto(String url,ImageView imgView,int defaultimgid){
        PhotoToLoad p = new PhotoToLoad(url, imgView,defaultimgid);
        mExecutorService.submit(new PhotoRunnable(p));
    }

    private class PhotoToLoad{
        public String url;
        public ImageView imageView;
        public int defaultimgId;
        public PhotoToLoad(String u,ImageView i,int id){
            url = u;
            imageView =i;
            defaultimgId=id;
        }
    }

    class PhotoRunnable implements Runnable{
        PhotoToLoad photoToLoad;
        public PhotoRunnable(PhotoToLoad photoToLoad){
            this.photoToLoad=photoToLoad;
        }

        @Override
        public void run() {
            if(checkCacheExist(photoToLoad)) {
                return;
            }

            Bitmap bmp = downloadBitmap(photoToLoad.url);
            mImgMemoryCache.put(photoToLoad.url, bmp);


            if(checkCacheExist(photoToLoad)) {
                return;
            }

            BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
            Activity a = (Activity) photoToLoad.imageView.getContext();
            a.runOnUiThread(bd);

        }
    }

    private Bitmap downloadBitmap(String url) {
        File f = mImgFileCache.getFile(url);

        Bitmap b = decodeFile(f);
        if(b != null)
            return b;

        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);

            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Util.copyStream(is, os);
            os.close();
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Throwable ex) {
            ex.printStackTrace();
            return null;
        }

    }

    private Bitmap decodeFile(File f) {
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;

            while(true) {
                if(width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }

                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    boolean checkCacheExist(PhotoToLoad photoToLoad) {
        String tag = mImgViews.get(photoToLoad.imageView);
        if(tag == null || !tag.equals(photoToLoad.url)) {
            return true;
        }
        return false;
    }

    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;

        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
            bitmap = b;
            photoToLoad = p;
        }

        @Override
        public void run() {
            if(checkCacheExist(photoToLoad))
                return;
            if(bitmap != null){
                photoToLoad.imageView.setImageBitmap(bitmap);
            } else {
                photoToLoad.imageView.setImageResource(photoToLoad.defaultimgId);
            }
        }
    }
}
