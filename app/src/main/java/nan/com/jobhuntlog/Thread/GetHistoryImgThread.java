package nan.com.jobhuntlog.Thread;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import nan.com.jobhuntlog.Fragment.HistoryFragment;
import nan.com.jobhuntlog.Interface.IHistoryImgListener;

/**
 * Created by NAN on 2016-09-02.
 */
public class GetHistoryImgThread implements Runnable{
    private boolean isavailable=false;
    private IHistoryImgListener nn_listener;
    public ListView listview;
    public Context nn_context;

    public GetHistoryImgThread(Context context, IHistoryImgListener listener, ListView listView) {
        nn_listener=listener;
        listview=listView;
        nn_context=context;
    }

    @Override
    public void run(){
        try{

            ListAdapter adapter = listview.getAdapter();
            int itemscount = adapter.getCount();
            int allitemsheight = 0;
            List<Bitmap> bmps = new ArrayList<Bitmap>();

            for (int i = 0; i < itemscount; i++) {
                View childView = adapter.getView(i, null, listview);
                childView.measure(View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
                childView.setDrawingCacheEnabled(true);
                childView.buildDrawingCache();
                bmps.add(childView.getDrawingCache());
                allitemsheight += childView.getMeasuredHeight();
            }

            Bitmap bigbitmap= Bitmap.createBitmap(listview.getMeasuredWidth(), allitemsheight, Bitmap.Config.ARGB_8888);
            Canvas bigcanvas = new Canvas(bigbitmap);

            Paint paint = new Paint();
            int iHeight = 0;

            for (int i = 0; i < bmps.size(); i++) {
                Bitmap bmp = bmps.get(i);
                bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
                iHeight += bmp.getHeight();

                bmp.recycle();
                bmp = null;
            }

            File file=savebitmap(bigbitmap,HistoryFragment.TEMPIMGPATH);
            if(file!=null){
                Uri path = Uri.fromFile(file);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("vnd.android.cursor.dir/email");
                emailIntent.putExtra(Intent.EXTRA_STREAM, path);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "History Img from JHL");
                nn_context.startActivity(Intent.createChooser(emailIntent , "Send email..."));
            }

            isavailable=true;

        }catch (Exception e){
            isavailable=false;
        }finally {
            if(nn_listener!=null){
                nn_listener.onConvertImgResult(isavailable);
            }
        }
    }
    private File savebitmap(Bitmap bmp,String tempname) throws IOException {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        File file = new File(extStorageDirectory, tempname);
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, tempname);
        }
        outStream = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        outStream.flush();
        outStream.close();
        return file;
    }

}
