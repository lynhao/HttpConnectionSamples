package demo.httpconnection.com.httpconnectionsamples;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linhao on 16/3/23.
 */
public class HttpThread extends Thread {

    private String url;
    private WebView mWebView;
    private Handler mHandler;

    private ImageView mImageView;

    public HttpThread(String url, WebView webView, Handler handler) {
        this.url = url;
        this.mWebView = webView;
        this.mHandler = handler;
    }

    public HttpThread(String url, ImageView imageView, Handler handler) {
        this.url = url;
        this.mHandler = handler;
        this.mImageView = imageView;
    }


    @Override
    public void run() {
        try {
            URL httpCon = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpCon.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setDoInput(true);
            InputStream in = httpURLConnection.getInputStream();
            FileOutputStream fos = null;
            File downLoadFile = null;
            String filename = String.valueOf(System.currentTimeMillis());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File parent = Environment.getExternalStorageDirectory();
                downLoadFile = new File(parent, filename);
                fos = new FileOutputStream(downLoadFile);
            }
            byte[] bytes = new byte[2 * 1024];
            int len;
            if (fos != null) {
                while ((len = in.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
            }
            final Bitmap bitmap = BitmapFactory.decodeFile(downLoadFile.getPath());
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    mImageView.setImageBitmap(bitmap);
                }
            });


//            final StringBuffer sb = new StringBuffer();
//            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//            String str;
//            while ((str = br.readLine())!=null){
//                sb.append(str);
//            }
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                   mWebView.loadData(sb.toString(),"text/html;charset=utf-8",null);
//                }
//            });


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
