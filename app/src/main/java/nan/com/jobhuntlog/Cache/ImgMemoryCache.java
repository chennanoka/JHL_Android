package nan.com.jobhuntlog.Cache;

import android.graphics.Bitmap;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by nanchen on 2016-12-26.
 */

public class ImgMemoryCache {
    private Map<String,Bitmap> cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));
    private long size=0;
    //100MB
    private long limit=1000000;
    public ImgMemoryCache() {
        limit= (Runtime.getRuntime().maxMemory()/8)>limit?(Runtime.getRuntime().maxMemory()/8): limit;
    }
    public Bitmap get(String id){
        try{
            if(!cache.containsKey(id)){
                return null;
            }
            return cache.get(id);
        }catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
    public void put(String id,Bitmap bitmap){
        try{
            if(cache.containsKey(id)) {
                size -= getSizeInBytes(cache.get(id));
            }
            cache.put(id,bitmap);
            size+=getSizeInBytes(bitmap);
            //make sure size < limit,prune if needed
            prune();
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    private void prune() {
        if(size > limit) {
            Iterator<Map.Entry<String, Bitmap>> iter = cache.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<String, Bitmap> entry = iter.next();
                size -= getSizeInBytes(entry.getValue());
                iter.remove();
                if(size <= limit) {
                    break;
                }
            }
        }
    }
    public long getSizeInBytes(Bitmap bitmap) {
        if(bitmap == null) {
            return 0;
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
    public void clear() {
        try {
            cache.clear();
            size = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
