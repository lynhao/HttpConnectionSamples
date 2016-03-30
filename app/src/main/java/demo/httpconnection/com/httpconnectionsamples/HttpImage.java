package demo.httpconnection.com.httpconnectionsamples;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linhao on 16/3/26.
 */
public class HttpImage extends  Thread {
    private ImageView mImageView;
    private String url;
    private Handler mHandler;

    public HttpImage(String url,Handler handler,ImageView imageView){
        this.url = url;
        this.mHandler = handler;
        this.mImageView = imageView;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            InputStream in = conn.getInputStream();
            final Bitmap bitmap = BitmapFactory.decodeStream(in);
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                mImageView.setImageBitmap(bitmap);
                }
            });



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
