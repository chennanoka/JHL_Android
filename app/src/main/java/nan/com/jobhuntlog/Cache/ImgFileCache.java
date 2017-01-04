package nan.com.jobhuntlog.Cache;
import android.content.Context;
import android.os.Environment;

import java.io.*;

/**
 * Created by nanchen on 2016-12-26.
 */

public class ImgFileCache {
    private File mCachePath;
    public ImgFileCache(Context context){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            mCachePath=new File(Environment.getExternalStorageDirectory(),"imageCache");
        }else{
            mCachePath=context.getCacheDir();
        }
        if(!mCachePath.mkdir()){
            mCachePath.mkdir();
        }
    }
    public File getFile(String url){
        String filename=String.valueOf(url.hashCode());
        File f=new File(mCachePath,filename);
        return f;
    }
    //manually clear cache when, lack of storage
    public void clear(){
        File[] files=mCachePath.listFiles();
        if(files!=null){
            for (File f:files){
                f.delete();
            }
        }
    }
}
